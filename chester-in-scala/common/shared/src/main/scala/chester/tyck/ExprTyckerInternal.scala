package chester.tyck

import chester.error.*
import chester.resolve.ExprResolver
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import cps.*
import cps.monads.given

import scala.annotation.tailrec
import scala.language.implicitConversions

type Substitution = Map[VarId, JudgeMaybeEffect]

object Substitution {
  val Empty: Substitution = Map.empty
}

extension (subst: Substitution) {
  @tailrec
  def walk(term: MetaTerm): JudgeMaybeEffect = subst.get(term.id) match {
    case Some(clause) => clause.wellTyped match {
      case term: MetaTerm => subst.walk(term)
      case _ => JudgeMaybeEffect(clause.wellTyped, clause.ty, clause.effect)
    }
    case None => JudgeMaybeEffect(term, term.ty, term.effect)
  }
}

case class TyckState(subst: Substitution = Substitution.Empty)

case class LocalCtx(ctx: Context = Context.builtin) {
  def resolve(id: Id): Option[CtxItem] = ctx.get(id)

  def resolve(id: VarId): Option[CtxItem] = ctx.getByVarId(id)
}

object LocalCtx {
  val Empty = LocalCtx()

  def fromParent(parent: LocalCtx): LocalCtx = parent
}

type F = [X] =>> Trying[TyckState, X]
implicit def convertF[T](x: Trying[TyckState, T]): F[T] = x
implicit def unconvertF[T](x: F[T]): Trying[TyckState, T] = x

case class ExprTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty) {

  /** assume a subtype relationship and get a subtype back */
  def unify(subType: Term, superType: Term): F[Term] = async[F] {
    if (subType == superType) subType
    else (subType, superType) match {
      case (_, AnyType) => subType
      case _ =>
        await(Trying.error(UnifyFailedError(subType, superType)))
        new ErrorTerm(UnifyFailedError(subType, superType))
    }
  }

  /** get the most sub common super type */
  def common(ty1: Term, ty2: Term): F[Term] = async[F] {
    if (ty1 == ty2) ty1
    else (ty1, ty2) match {
      case (_, AnyType) => AnyType
      case (AnyType, _) => AnyType
      case _ =>
        OrType(Vector(ty1, ty2))
    }
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

  extension [A](xs: Seq[A]) {
    def foldLeftM[B](z: B)(f: (B, A) => F[B]): F[B] = async[F] {
      var acc = z
      for (elem <- xs) {
        acc = await(f(acc, elem))
      }
      acc
    }
    def reduceM(f: (A, A) => F[A]): F[A] = xs.tail.foldLeftM(xs.head)(f)
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
        await(Trying.error(UnifyFailedError(subEffect, superEffect)))
        new ErrorTerm(UnifyFailedError(subEffect, superEffect))
    }
  }

  def synthesizeObjectExpr(x: ObjectExpr): F[Judge] = async[F] {
    val synthesizedClausesWithEffects: Vector[EffectWith[(Term, Term, Term)]] = x.clauses.map {
      case ObjectExprClauseOnValue(keyExpr, valueExpr) => {
        val Judge(wellTypedExpr, exprType, exprEffect) = await(synthesize(valueExpr))
        val Judge(keyWellTyped, _, keyEffect) = await(synthesize(keyExpr))
        val combinedEffect = await(effectUnion(exprEffect, keyEffect))
        EffectWith(combinedEffect, (keyWellTyped, wellTypedExpr, exprType))
      }
      case _ => throw new IllegalArgumentException("Unexpected clause type, maybe no desalted")
    }

    val combinedEffect = await(effectFold(synthesizedClausesWithEffects.map(_.effect)))
    val objectClauses = synthesizedClausesWithEffects.map(_.value)

    val objectTerm = ObjectTerm(objectClauses.map { case (key, value, _) => ObjectClauseValueTerm(key, value) })
    val objectType = ObjectType(objectClauses.map { case (key, _, ty) => ObjectClauseValueTerm(key, ty) })

    Judge(objectTerm, objectType, combinedEffect)
  }

  def synthesizeBlock(block: Block): F[Judge] = async[F] {
    ???
  }

  def synthesize(expr: Expr): F[Judge] = async[F] {
    await(resolve(expr)) match {
      case IntegerLiteral(value, meta) =>
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
          await(synthesize(term))
        }
        val ty = await(tyFold(judges.map(_.ty)))
        val effect = await(effectFold(judges.map(_.effect)))
        Judge(ListTerm(judges.map(_.wellTyped)), ListType(ty), effect)
      case objExpr: ObjectExpr =>
        await(synthesizeObjectExpr(objExpr))
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

  val normalizer = new Normalizer()

  def whnf(judge: Judge): F[Judge] = async[F] {
    val state = await(Trying.state)
    val (newState, normalizedTerm) = normalizer.apply(judge).run(state).value
    await(Trying.state = newState)
    normalizedTerm
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

  def inherit(expr: Expr, ty: Term, effect: Option[Term] = None): F[Judge] = async[F] {
    val expr1: Expr = await(resolve(expr))
    val jdg1: Judge = await(whnf(Judge(ty, Typeω)))
    val ty1: Term = jdg1.wellTyped
    (expr1, ty1) match {
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
      case _ =>
        val Judge(wellTypedExpr, exprType, exprEffect) = await(synthesize(expr))
        val ty1 = await(unify(exprType, ty))
        val effect1 = await(inheritEffect(effect, exprEffect))
        Judge(wellTypedExpr, ty1, effect1)
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
    val results = ExprTyckerInternal(ctx).unify(subType, superType).run(state)
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
