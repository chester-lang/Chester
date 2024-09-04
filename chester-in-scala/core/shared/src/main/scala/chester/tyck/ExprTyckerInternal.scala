package chester.tyck

import chester.error.*
import chester.resolve.ExprResolver
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.utils.reuse
import spire.math.Trilean
import spire.math.Trilean.{True, Unknown}

import scala.annotation.tailrec
import scala.language.implicitConversions

type Solutions = Map[VarId, JudgeMaybeEffect]

object Solutions {
  val Empty: Solutions = Map.empty
}

extension (subst: Solutions) {
  @tailrec
  def walk(term: MetaTerm): JudgeMaybeEffect = subst.get(term.id) match {
    case Some(clause) => clause.wellTyped match {
      case term: MetaTerm => subst.walk(term)
      case _ => JudgeMaybeEffect(clause.wellTyped, clause.ty, clause.effect)
    }
    case None => JudgeMaybeEffect(term, term.ty, term.effect)
  }
}

case class TyckState(subst: Solutions = Solutions.Empty)

case class LocalCtx(ctx: Context = Context.builtin) {
  def resolve(id: Id): Option[CtxItem] = ctx.get(id)

  def resolve(id: VarId): Option[CtxItem] = ctx.getByVarId(id)
}

object LocalCtx {
  val Empty = LocalCtx()

  def fromParent(parent: LocalCtx): LocalCtx = parent
}

// deterministic logic with a resolution system that try all candidates
case class ExprTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty, tyck: Tyck) {

  implicit val reporter1: Reporter[TyckErrorOrWarning] = {
    case e: TyckError => tyck.errorsReporter.apply(e)
    case e: TyckWarning => tyck.warningsReporter.apply(e)
  }

  def superTypes(ty: Term): Option[Vector[Term]] = {
    ty match {
      case Intersection(ts) => Some(ts)
      case _ => None
    }
  }

  def compareTy(rhs: Term, lhs: Term): Trilean = {
    val subType1 = whnfNoEffect(rhs)
    val superType1 = whnfNoEffect(lhs)
    if (subType1 == superType1) True
    else (subType1, superType1) match {
      case (subType, AnyType(level)) => True // TODO: level
      case _ => Unknown
    }
  }

  def unifyLevel(rhs: Term, lhs: Term): Term = {
    rhs // TODO
  }

  /** assume a subtype relationship and get a subtype back */
  def unifyTy(lhs: Term, rhs: Term, failed: => Term = null): Term = {
    val rhs1 = whnfNoEffect(rhs)
    val lhs1 = whnfNoEffect(lhs)
    if (rhs1 == lhs1) rhs1
    else (lhs1, rhs1) match {
      case (AnyType(level), subType) => subType // TODO: level
      case (Union(superTypes), subType) => Union.from(superTypes.map(x => unifyTyOrNothingType(rhs = subType, lhs = x)))
      case (superType, Union(subTypes)) => {
        val results = subTypes.map(rhs => unifyTy(rhs = rhs, lhs = superType))
        Union.from(results)
      }
      case (Intersection(superTypes), subType) => {
        val results = superTypes.map(x => unifyTy(rhs = subType, lhs = x))
        Intersection.from(results)
      }
      case (superTypes, Intersection(subTypes)) => {
        val results = subTypes.map(x => unifyTy(rhs = x, lhs = superTypes))
        Intersection.from(results)
      }
      case (IntegerType, IntType) => IntType
      case (superType, subType) =>
        if (failed != null) failed else {
          val err = UnifyFailedError(rhs = subType, lhs = superType)
          tyck.error(err)
          new ErrorTerm(err)
        }
    }
  }

  def unifyTyOrNothingType(lhs: Term, rhs: Term): Term = unifyTy(rhs = rhs, lhs = lhs, failed = NothingType)

  def unifyEff(lhs: Option[Term], rhs: Option[Term]): Option[Term] = {
    if (rhs == lhs) rhs
    else if (lhs.isEmpty) rhs
    else if (rhs.isEmpty) lhs // TODO: what does this mean?
    else {
      rhs // TODO: correct logic
    }
  }

  def unifyEff(lhs: Option[Term], rhs: Term): Term = unifyEff(lhs, Some(rhs)).get

  /** get the most sub common super type */
  def common(ty1: Term, ty2: Term): Term = {
    if (ty1 == ty2) ty1
    else (ty1, ty2) match {
      case (_, AnyType(level)) => AnyType0 // TODO: level
      case (AnyType(level), _) => AnyType0 // TODO: level
      case (NothingType, ty) => ty
      case (ty, NothingType) => ty
      case (ListType(ty1), ListType(ty2)) => ListType(common(ty1, ty2))
      case (Union(ts1), ty2) => Union(ts1.map(common(_, ty2)))
      case (ty1, Union(ts2)) => Union(ts2.map(common(ty1, _)))
      case (ty1, ty2) =>
        Union.from(Vector(ty1, ty2))
    }
  }

  def common(seq: Seq[Term]): Term = {
    seq.reduce(common)
  }

  def effectUnion_impl(e1: Term, e2: Term): Term = {
    (e1, e2) match {
      case (NoEffect, _) => e2
      case (_, NoEffect) => e1
      case _ if e1 == e2 => e1
      case _ => EffectUnion.from(Vector(e1, e2))
    }
  }

  def effectUnion(e1: Term, e2: Term): Term = {
    effectUnion_impl(e1, e2)
  }

  def effectFold(es: Seq[Term]): Term = {
    es.reduceOption(effectUnion_impl).getOrElse(NoEffect)
  }

  def collectLevel(xs: Seq[Term]): Term = {
    Level0 // TODO
  }

  def tyFold(types: Vector[Term]): Term = {
    types.reduce((ty1, ty2) => common(ty1, ty2))
  }

  def synthesizeObjectExpr(x: ObjectExpr): Judge = {
    val synthesizedClausesWithEffects: Vector[EffectWith[(Term, Term, Term)]] = x.clauses.map {
      case ObjectExprClauseOnValue(keyExpr, valueExpr) => {
        val Judge(wellTypedExpr, exprType, exprEffect) = synthesize(valueExpr)
        val Judge(keyWellTyped, _, keyEffect) = synthesize(keyExpr)
        val combinedEffect = effectUnion(exprEffect, keyEffect)
        EffectWith(combinedEffect, (keyWellTyped, wellTypedExpr, exprType))
      }
      case _ => throw new IllegalArgumentException("Unexpected clause type, maybe no desalted")
    }

    val combinedEffect = effectFold(synthesizedClausesWithEffects.map(_.effect))
    val objectClauses = synthesizedClausesWithEffects.map(_.value)

    val objectTerm = ObjectTerm(objectClauses.map { case (key, value, _) => ObjectClauseValueTerm(key, value) })
    val objectType = ObjectType(objectClauses.map { case (key, _, ty) => ObjectClauseValueTerm(key, ty) })

    Judge(objectTerm, objectType, combinedEffect)
  }

  def synthesizeBlock(block: Block): Judge = {
    ???
  }

  def synthesize(expr: Expr): Judge = {
    resolve(expr) match {
      case IntegerLiteral(value, meta) =>
        if (value.isValidInt)
          Judge(IntTerm(value.toInt), IntType, NoEffect)
        else
          Judge(IntegerTerm(value), IntegerType, NoEffect)
      case RationalLiteral(value, meta) =>
        Judge(RationalTerm(value), RationalType, NoEffect)
      case StringLiteral(value, meta) =>
        Judge(StringTerm(value), StringType, NoEffect)
      case SymbolLiteral(value, meta) =>
        Judge(SymbolTerm(value), SymbolType, NoEffect)
      case ListExpr(terms, meta) if terms.isEmpty =>
        Judge(ListTerm(Vector()), ListType(NothingType), NoEffect)
      case ListExpr(terms, meta) =>
        val judges: Vector[Judge] = terms.map { term =>
          synthesize(term)
        }
        val ty = tyFold(judges.map(_.ty))
        val effect = effectFold(judges.map(_.effect))
        Judge(ListTerm(judges.map(_.wellTyped)), ListType(ty), effect)
      case objExpr: ObjectExpr =>
        synthesizeObjectExpr(objExpr)
      case block: Block => (synthesizeBlock(block))
      case expr: Stmt => {
        val err = UnexpectedStmt(expr)
        tyck.error(err)
        Judge(new ErrorTerm(err), UnitType, NoEffect)
      }
      case Identifier(id, meta) => {
        val resolved = localCtx.resolve(id)
        resolved match {
          case Some(CtxItem(name, JudgeNoEffect(wellTyped, ty))) =>
            Judge(name, ty, NoEffect)
          case None =>
            val err = IdentifierNotFoundError(expr)
            tyck.error(err)
            Judge(new ErrorTerm(err), new ErrorTerm(err), NoEffect)
        }
      }

      case _ =>
        val err = UnsupportedExpressionError(expr)
        tyck.error(err)
        Judge(new ErrorTerm(err), new ErrorTerm(err), NoEffect)
    }
  }

  def synthesizeTerm(term: Term): JudgeNoEffect = {
    term match {
      case _ => ???
    }
  }

  case class EffectWith[T](effect: Term, value: T)

  def inheritObjectFields(clauses: Vector[ObjectClause], fieldTypes: Vector[ObjectClauseValueTerm], effect: Option[Term]): EffectWith[Vector[ObjectClauseValueTerm]] = {
    ??? // TODO
  }

  /** part of whnf */
  def normalizeNoEffect(term: Term): Term = {
    term match {
      case Union(xs) => {
        val xs1 = xs.map(x => whnfNoEffect(x))
        Union.from(xs1)
      }
      case wellTyped => wellTyped
    } match {
      case Union(xs) if xs.exists(_.isInstanceOf[AnyType]) =>
        val level = collectLevel(xs)
        AnyType(level)
      case wellTyped => reuse(term, wellTyped)
    }
  }

  def walk(term: MetaTerm): JudgeMaybeEffect = {
    val state = tyck.getState
    val result = state.subst.walk(term)
    result
  }

  def whnfNoEffect(term: Term): Term = {
    val result = normalizeNoEffect(term)
    result match
      case term: MetaTerm => {
        val walked = walk(term)
        require(walked.effect == NoEffect)
        whnfNoEffect(walked.wellTyped)
      }
      case _ => result
  }

  def resolve(expr: Expr): Expr = {
    ExprResolver.resolve(localCtx, expr)
  }

  /** possibly apply an implicit conversion */
  def inheritFallbackUnify(judge: Judge, ty: Term, effect: Option[Term] = None): Judge = {
    val Judge(wellTypedExpr, exprType, exprEffect) = judge
    val ty1 = (unifyTy(ty, exprType))
    val effect1 = (unifyEff(effect, exprEffect))
    Judge(wellTypedExpr, ty1, effect1)
  }

  def inherit(expr: Expr, ty: Term, effect: Option[Term] = None): Judge = {
    val expr1: Expr = (resolve(expr))
    val ty1: Term = whnfNoEffect(ty)
    (expr1, ty1) match {
      case (expr, Union(xs)) => ??? // TODO
      case (expr, Intersection(xs)) => ??? // TODO
      case (objExpr: ObjectExpr, ObjectType(fieldTypes, _)) =>
        val EffectWith(inheritedEffect, inheritedFields) = (inheritObjectFields(clauses = objExpr.clauses, fieldTypes = fieldTypes, effect = effect))
        Judge(ObjectTerm(inheritedFields), ty, (unifyEff(effect, inheritedEffect)))
      case (ListExpr(terms, meta), lstTy@ListType(ty)) =>
        val checkedTerms: Vector[EffectWith[Term]] = terms.map { term =>
          val Judge(wellTypedTerm, termType, termEffect) = (inherit(term, ty))
          EffectWith(termEffect, wellTypedTerm)
        }
        val effect1 = (unifyEff(effect, (effectFold(checkedTerms.map(_.effect)))))
        Judge(ListTerm(checkedTerms.map(_.value)), lstTy, effect1)
      case (expr, ty) =>
        inheritFallbackUnify(synthesize(expr), ty, effect)
    }
  }

}

object ExprTycker {
  private def convertToEither[T](result: TyckResult[TyckState, T]): Either[Vector[TyckError], T] = {
    if (result.errors.nonEmpty) {
      Left(result.errors)
    } else {
      Right(result.result)
    }
  }

  @deprecated("error information are lost")
  def unifyV0(subType: Term, superType: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Term] = {
    val result = unifyTy(superType, subType, state, ctx)
    convertToEither(result)
  }

  @deprecated("error information are lost")
  def inheritV0(expr: Expr, ty: Term, effect: Option[EffectTerm] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Judge] = {
    val result = inherit(expr, ty, effect, state, ctx)
    convertToEither(result)
  }

  @deprecated("error information are lost")
  def synthesizeV0(expr: Expr, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Judge] = {
    val result = synthesize(expr, state, ctx)
    convertToEither(result)
  }

  def unifyTy(lhs: Term, rhs: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Term] = {
    Tyck.run(ExprTyckerInternal(ctx, _).unifyTy(lhs, rhs))(state)
  }

  def inherit(expr: Expr, ty: Term, effect: Option[EffectTerm] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    Tyck.run(ExprTyckerInternal(ctx, _).inherit(expr, ty, effect))(state)
  }

  def synthesize(expr: Expr, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    Tyck.run(ExprTyckerInternal(ctx, _).synthesize(expr))(state)
  }
}
