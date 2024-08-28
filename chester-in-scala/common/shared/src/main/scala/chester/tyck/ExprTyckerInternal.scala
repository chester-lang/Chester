package chester.tyck

import chester.error.*
import chester.resolve.ExprResolver
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.utils.reuse
import cps.*

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

type F = [X] =>> Trying[TyckState, X]
implicit inline def convertF[T](inline x: Trying[TyckState, T]): F[T] = x
implicit inline def unconvertF[T](inline x: F[T]): Trying[TyckState, T] = x

extension [F[_], T, G[_]](f: F[T]) {
  transparent inline def !(using inline ctx: _root_.cps.CpsMonadContext[G], inline conversion: _root_.cps.CpsMonadConversion[F, G]): T = await(f)
}

case class ExprTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty) {

  /** assume a subtype relationship and get a subtype back */
  def unifyTy(subType: Term, superType: Term): F[Term] = async[F] {
    val subType1 = whnfTy(subType).!
    val superType1 = whnfTy(superType).!
    if (subType1 == superType1) subType1
    else (subType1, superType1) match {
      case (subType, AnyType(level)) => subType // TODO: level
      case (subType, OrType(superTypes)) => OrType(superTypes.mapM(unifyTyOrNothingType(subType, _)).!)
      case (OrType(subTypes), superType) => {
        val results = subTypes.mapM(unifyTy(_, superType)).!
        OrType(results)
      }
      case (subType, AndType(superTypes)) => {
        val results = superTypes.mapM(unifyTy(subType, _)).!
        AndType(results)
      }
      case (AndType(subTypes),superTypes) => {
        val results = subTypes.mapM(unifyTy(_, superTypes)).!
        AndType(results)
      }
      case (subType, superType) =>
        await(Trying.error(UnifyFailedError(subType, superType)))
        new ErrorTerm(UnifyFailedError(subType, superType))
    }
  }

  def unifyTyOrNothingType(ty1: Term, ty2: Term): F[Term] = unifyTy(ty1, ty2) || async[F] {NothingType}

  def unifyEff(subEff: Option[Term], superEff: Option[Term]): F[Option[Term]] = async[F] {
    if (subEff == superEff) subEff
    else if (superEff.isEmpty) subEff
    else if (subEff.isEmpty) superEff // TODO: what does this mean?
    else {
      subEff // TODO: correct logic
    }
  }

  def unifyEff(subEff: Term, superEff: Term): F[Term] = unifyEff(Some(subEff), Some(superEff)).map(_.get)

  def unifyEff(subEff: Option[Term], superEff: Term): F[Term] = unifyEff(subEff, Some(superEff)).map(_.get)

  def unifyEff(subEff: Term, superEff: Option[Term]): F[Term] = unifyEff(Some(subEff), superEff).map(_.get)

  /** get the most sub common super type */
  def common(ty1: Term, ty2: Term): F[Term] = async[F] {
    if (ty1 == ty2) ty1
    else (ty1, ty2) match {
      case (_, AnyType(level)) => AnyType0 // TODO: level
      case (AnyType(level), _) => AnyType0 // TODO: level
      case (NothingType, ty) => ty
      case (ty, NothingType) => ty
      case (ListType(ty1), ListType(ty2)) => ListType(common(ty1, ty2).!)
      case (OrType(ts1), ty2) => OrType(ts1.map(common(_, ty2).!))
      case (ty1, OrType(ts2)) => OrType(ts2.map(common(ty1, _).!))
      case (ty1, ty2) =>
        OrType(Vector(ty1, ty2))
    }
  }

  def common(seq: Seq[Term]): F[Term] = async[F] {
    seq.reduceM(common).!
  }

  def effectUnion_impl(e1: Term, e2: Term): Term = {
    (e1, e2) match {
      case (NoEffect, _) => e2
      case (_, NoEffect) => e1
      case _ if e1 == e2 => e1
      case _ => EffectList(Vector(e1, e2))
    }
  }

  def effectUnion(e1: Term, e2: Term): F[Term] = async[F] {
    effectUnion_impl(e1, e2)
  }

  def effectFold(es: Seq[Term]): F[Term] = async[F] {
    es.reduceOption(effectUnion_impl).getOrElse(NoEffect)
  }

  def collectLevel(xs: Seq[Term]): F[Term] = async[F] {
    Level0 // TODO
  }

  extension [A](xs: Seq[A]) {
    def foldLeftM[B](z: B)(f: (B, A) => F[B]): F[B] = async[F] {
      var acc = z
      for (elem <- xs) {
        acc = f(acc, elem).!
      }
      acc
    }
    def reduceM(f: (A, A) => F[A]): F[A] = xs.tail.foldLeftM(xs.head)(f)
    def mapM[B](f: A => F[B]): F[Vector[B]] = async[F] {
      var acc = Vector.empty[B]
      for (elem <- xs) {
        acc = acc :+ f(elem).!
      }
      acc
    }
  }

  def tyFold(types: Vector[Term]): F[Term] = {
    types.reduceM((ty1, ty2) => common(ty1, ty2))
  }

  def unifyEffect(subEffect: Term, superEffect: Term): F[Term] = async[F] {
    (subEffect, superEffect) match {
      case (_, NoEffect) => subEffect
      case (NoEffect, _) => superEffect
      case _ if subEffect == superEffect => subEffect
      case _ =>
        Trying.error(UnifyFailedError(subEffect, superEffect)).!
        new ErrorTerm(UnifyFailedError(subEffect, superEffect))
    }
  }

  def synthesizeObjectExpr(x: ObjectExpr): F[Judge] = async[F] {
    val synthesizedClausesWithEffects: Vector[EffectWith[(Term, Term, Term)]] = x.clauses.map {
      case ObjectExprClauseOnValue(keyExpr, valueExpr) => {
        val Judge(wellTypedExpr, exprType, exprEffect) = synthesize(valueExpr).!
        val Judge(keyWellTyped, _, keyEffect) = synthesize(keyExpr).!
        val combinedEffect = effectUnion(exprEffect, keyEffect).!
        EffectWith(combinedEffect, (keyWellTyped, wellTypedExpr, exprType))
      }
      case _ => throw new IllegalArgumentException("Unexpected clause type, maybe no desalted")
    }

    val combinedEffect = effectFold(synthesizedClausesWithEffects.map(_.effect)).!
    val objectClauses = synthesizedClausesWithEffects.map(_.value)

    val objectTerm = ObjectTerm(objectClauses.map { case (key, value, _) => ObjectClauseValueTerm(key, value) })
    val objectType = ObjectType(objectClauses.map { case (key, _, ty) => ObjectClauseValueTerm(key, ty) })

    Judge(objectTerm, objectType, combinedEffect)
  }

  def synthesizeBlock(block: Block): F[Judge] = async[F] {
    ???
  }

  def synthesize(expr: Expr): F[Judge] = async[F] {
    resolve(expr).! match {
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
        val a = MetaTerm.generate("listElement", Typeω)
        Judge(ListTerm(Vector()), ListType(a), NoEffect)
      case ListExpr(terms, meta) =>
        val judges: Vector[Judge] = terms.map { term =>
          synthesize(term).!
        }
        val ty = tyFold(judges.map(_.ty)).!
        val effect = effectFold(judges.map(_.effect)).!
        Judge(ListTerm(judges.map(_.wellTyped)), ListType(ty), effect)
      case objExpr: ObjectExpr =>
        synthesizeObjectExpr(objExpr).!
      case block: Block => await(synthesizeBlock(block))
      case expr: Stmt => {
        val err = UnexpectedStmt(expr)
        await(Trying.error(err))
        Judge(new ErrorTerm(UnsupportedExpressionError(expr)), UnitType, NoEffect)
      }
      case Identifier(id, meta) => {
        val resolved = localCtx.resolve(id)
        resolved match {
          case Some(CtxItem(name, JudgeNoEffect(wellTyped, ty))) =>
            Judge(name, ty, NoEffect)
          case None =>
            await(Trying.error(IdentifierNotFoundError(expr)))
            Judge(new ErrorTerm(IdentifierNotFoundError(expr)), new ErrorTerm(IdentifierNotFoundError(expr)), NoEffect)
        }
      }

      case _ =>
        await(Trying.error(UnsupportedExpressionError(expr)))
        Judge(new ErrorTerm(UnsupportedExpressionError(expr)), new ErrorTerm(UnsupportedExpressionError(expr)), NoEffect)
    }
  }

  def synthesizeTerm(term: Term): F[JudgeNoEffect] = async[F] {
    term match {
      case _ => ???
    }
  }

  case class EffectWith[T](effect: Term, value: T)

  def inheritObjectFields(clauses: Vector[ObjectClause], fieldTypes: Vector[ObjectClauseValueTerm], effect: Option[Term]): F[EffectWith[Vector[ObjectClauseValueTerm]]] = async[F] {
    ??? // TODO
  }

  /** part of whnf */
  def normalize(judge: Judge): F[Judge] = async[F] {
    val ty0 = judge.ty
    val ty = if (ty0.whnf) ty0 else whnfTy(ty0).!
    val effect = judge.effect
    val wellTyped = judge.wellTyped match {
      case OrType(xs) => {
        if (ty.whnf) assert(ty.isInstanceOf[Sort])
        assert(effect.isInstanceOf[NoEffect.type])
        val xs1 = xs.mapM(x => whnf(Judge(x, ty))).!.map(_.wellTyped)
        OrType(xs1)
      }
      case wellTyped => wellTyped
    }
    wellTyped match {
      case OrType(xs) if xs.exists(_.isInstanceOf[AnyType]) =>
        val level = collectLevel(xs).!
        Judge(AnyType(level), unifyTy(Type(level), ty).!, effect)
      case wellTyped => reuse(judge, Judge(wellTyped, ty, effect))
    }
  }

  def walk(term: MetaTerm): F[JudgeMaybeEffect] = async[F] {
    val state = Trying.state.!
    val result = state.subst.walk(term)
    result
  }

  def whnf(judge: Judge): F[Judge] = async[F] {
    val result = normalize(judge).!
    result.wellTyped match
      case term: MetaTerm => {
        val walked = walk(term).!
        whnf(Judge(walked.wellTyped, unifyTy(walked.ty, result.ty).!, unifyEff(walked.effect, result.effect).!)).!
      }
      case _ => result
  }

  def whnfTy(term: Term): F[Term] = async[F] {
    val result = whnf(Judge(term, Typeω)).!
    result.wellTyped
  }

  def resolve(expr: Expr): F[Expr] = async[F] {
    val (errors, result) = ExprResolver.resolveFunctional(localCtx, expr)
    await(Trying.errors(errors))
    result
  }

  // TODO
  def inheritEffect(target: Option[Term] = None, eff: Term): F[Term] = async[F] {
    eff
  }

  /** possibly apply an implicit conversion */
  def conversion(judge: Judge, ty: Term, effect: Option[Term] = None): F[Judge] = async[F] {
    val Judge(wellTypedExpr, exprType, exprEffect) = judge
    val ty1 = await(unifyTy(exprType, ty))
    val effect1 = await(inheritEffect(effect, exprEffect))
    Judge(wellTypedExpr, ty1, effect1)
  }

  def inherit(expr: Expr, ty: Term, effect: Option[Term] = None): F[Judge] = async[F] {
    val expr1: Expr = await(resolve(expr))
    val ty1: Term = whnfTy(ty).!
    (expr1, ty1) match {
      case (expr, OrType(xs)) => Trying.or(xs.map(inherit(expr, _, effect))).!
      case (expr, AndType(xs)) => ??? // TODO
      case (objExpr: ObjectExpr, ObjectType(fieldTypes, _)) =>
        val EffectWith(inheritedEffect, inheritedFields) = await(inheritObjectFields(clauses = objExpr.clauses, fieldTypes = fieldTypes, effect = effect))
        Judge(ObjectTerm(inheritedFields), ty, await(inheritEffect(effect, inheritedEffect)))
      case (ListExpr(terms, meta), lstTy@ListType(ty)) =>
        val checkedTerms: Vector[EffectWith[Term]] = terms.map { term =>
          val Judge(wellTypedTerm, termType, termEffect) = await(inherit(term, ty))
          EffectWith(termEffect, wellTypedTerm)
        }
        val effect1 = await(inheritEffect(effect, await(effectFold(checkedTerms.map(_.effect)))))
        Judge(ListTerm(checkedTerms.map(_.value)), lstTy, effect1)
      case (expr, ty) =>
        conversion(synthesize(expr).!, ty, effect).!
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
    val result = unify(subType, superType, state, ctx)
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

  def unify(subType: Term, superType: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Term] = {
    val results = ExprTyckerInternal(ctx).unifyTy(subType, superType).run(state)
    results.head
  }

  def inherit(expr: Expr, ty: Term, effect: Option[EffectTerm] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    val results = ExprTyckerInternal(ctx).inherit(expr, ty, effect).run(state)
    results.head
  }

  def synthesize(expr: Expr, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    val results = ExprTyckerInternal(ctx).synthesize(expr).run(state)
    results.head
  }

}
