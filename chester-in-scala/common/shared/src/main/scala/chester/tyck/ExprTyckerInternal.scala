package chester.tyck

import cats.data.State
import chester.error.*
import chester.resolve.ExprResolver
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.utils.MutBox
import cps.monads.{*, given}
import cps.*

import scala.annotation.targetName
import scala.language.implicitConversions


case class TyckState()

case class LocalCtx(ctx: Context = Context.builtin) {
  def resolve(id: Id): Option[CtxItem] = ctx.get(id)

  def resolve(id: VarId): Option[CtxItem] = ctx.getByVarId(id)
}

object LocalCtx {
  val Empty = LocalCtx()

  def fromParent(parent: LocalCtx): LocalCtx = parent
}

case class ExprTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty)(implicit S: Tyck) {

  def unify(subType: Term, superType: Term): Term = {
    if (subType == superType) subType
    else (subType, superType) match {
      case (_, AnyType) => subType
      case _ =>
        S.errors.report(UnifyFailedError(subType, superType))
        new ErrorTerm(UnifyFailedError(subType, superType))
    }
  }

  def effectUnion(e1: Term, e2: Term): Term = (e1, e2) match {
    case (NoEffect, _) => e2
    case (_, NoEffect) => e1
    case _ if e1 == e2 => e1
    case _ => EffectList(Vector(e1, e2))
  }

  def unifyEffect(subEffect: Term, superEffect: Term): Term = {
    (subEffect, superEffect) match {
      case (_, NoEffect) => subEffect
      case (NoEffect, _) => superEffect
      case _ if subEffect == superEffect => subEffect
      case _ =>
        S.errors.report(UnifyFailedError(subEffect, superEffect))
        new ErrorTerm(UnifyFailedError(subEffect, superEffect))
    }
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

    val combinedEffect = synthesizedClausesWithEffects.map(_.effect).reduceOption(effectUnion).getOrElse(NoEffect)
    val objectClauses = synthesizedClausesWithEffects.map(_.value)

    val objectTerm = ObjectTerm(objectClauses.map { case (key, value, _) => ObjectClauseValueTerm(key, value) })
    val objectType = ObjectType(objectClauses.map { case (key, _, ty) => ObjectClauseValueTerm(key, ty) })

    Judge(objectTerm, objectType, combinedEffect)
  }

  def synthesizeBlock(block: Block): Judge = {
    ???
  }

  def synthesize(expr: Expr): Judge = resolve(expr) match {
    case IntegerLiteral(value, meta) =>
      Judge(IntegerTerm(value), IntegerType, NoEffect)

    case RationalLiteral(value, meta) =>
      Judge(RationalTerm(value), RationalType, NoEffect)

    case StringLiteral(value, meta) =>
      Judge(StringTerm(value), StringType, NoEffect)

    case SymbolLiteral(value, meta) =>
      Judge(SymbolTerm(value), SymbolType, NoEffect)

    case objExpr: ObjectExpr =>
      synthesizeObjectExpr(objExpr)
    case block: Block => synthesizeBlock(block)
    case expr: Stmt => {
      val err = UnexpectedStmt(expr)
      S.errors.report(err)
      Judge(new ErrorTerm(UnsupportedExpressionError(expr)), UnitType, NoEffect)
    }

    case Identifier(id, meta) => {
      val resolved = localCtx.resolve(id)
      resolved match {
        case Some(CtxItem(name, JudgeNoEffect(wellTyped, ty))) =>
          Judge(name, ty, NoEffect)
        case None =>
          S.errors.report(IdentifierNotFoundError(expr))
          Judge(new ErrorTerm(IdentifierNotFoundError(expr)), new ErrorTerm(IdentifierNotFoundError(expr)), NoEffect)
      }
    }

    case _ =>
      S.errors.report(UnsupportedExpressionError(expr))
      Judge(new ErrorTerm(UnsupportedExpressionError(expr)), new ErrorTerm(UnsupportedExpressionError(expr)), NoEffect)
  }

  def synthesizeTerm(term: Term): JudgeNoEffect = term match {
    case _ => ???
  }

  case class EffectWith[T](effect: Term, value: T)

  def inheritObjectFields(clauses: Vector[ObjectClause], fieldTypes: Vector[ObjectClauseValueTerm], effect: Option[Term]): EffectWith[Vector[ObjectClauseValueTerm]] = {
    val inheritedFieldsWithEffects = clauses.flatMap {
      case ObjectExprClauseOnValue(keyExpr, valueExpr) =>
        val synthesizedKey = synthesize(keyExpr)
        val fieldType = fieldTypes.collectFirst {
          case ObjectClauseValueTerm(k, _) if k == synthesizedKey.wellTyped => k
        }
        fieldType match {
          case Some(_) =>
            inherit(valueExpr, fieldType.get, effect) match {
              case Judge(wellTyped, _, fieldEffect) =>
                Some(EffectWith(fieldEffect, ObjectClauseValueTerm(synthesizedKey.wellTyped, wellTyped)))
              case _ => None
            }
          case None =>
            S.errors.report(FieldTypeNotFoundError(synthesizedKey.wellTyped match {
              case k: SymbolTerm =>
                k.value
              case _ => synthesizedKey.wellTyped.toString
            }))
            None
        }

      case _ => throw new IllegalArgumentException("Unexpected clause type")
    }

    val combinedEffect = inheritedFieldsWithEffects.map(_.effect).reduce(effectUnion)
    val inheritedFields = inheritedFieldsWithEffects.map(_.value)

    EffectWith(combinedEffect, inheritedFields)
  }

  val normalizer = new Normalizer()

  def whnf(judge: Judge): Judge = {
    val state = S.state.value
    val (newState, normalizedTerm) = normalizer.apply(judge).run(state).value
    S.state.value = newState // Update the state with the new state from the normalizer
    normalizedTerm
  }

  def resolve(expr: Expr): Expr = {
    ExprResolver.resolve(localCtx, expr, S.errors)
  }

  def inherit(expr: Expr, ty: Term, effect: Option[Term] = None): Judge = {
    (resolve(expr), whnf(Judge(ty, Typeω)).wellTyped) match {
      case (objExpr: ObjectExpr, ObjectType(fieldTypes, _)) =>
        val EffectWith(inheritedEffect, inheritedFields) = inheritObjectFields(clauses = objExpr.clauses, fieldTypes = fieldTypes, effect = effect)
        Judge(ObjectTerm(inheritedFields), ty, effectUnion(inheritedEffect, effect.getOrElse(NoEffect)))

      case _ =>
        val Judge(wellTypedExpr, exprType, exprEffect) = synthesize(expr)
        val ty1 = unify(exprType, ty)
        val effect1 = effect.map(eff => effectUnion(exprEffect, eff)).getOrElse(exprEffect)
        Judge(wellTypedExpr, ty1, effect1)
    }
  }

}

case class TyckResult[S, T](state: S, result: T, warnings: Vector[TyckWarning], errors: Vector[TyckError])

object TyckResult {
  object Success {
    def unapply[S, T](x: TyckResult[S, T]): Option[(T, S, Vector[TyckWarning])] = {
      if (x.errors.isEmpty) Some((x.result, x.state, x.warnings))
      else None
    }
  }

  object Failure {
    def unapply[S, T](x: TyckResult[S, T]): Option[(Vector[TyckError], Vector[TyckWarning], S, T)] = {
      if (x.errors.nonEmpty) Some((x.errors, x.warnings, x.state, x.result))
      else None
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
    val reporterW = new VectorReporter[TyckWarning]()
    val reporterE = new VectorReporter[TyckError]()
    val mutBox = MutBox(state)
    implicit val tyck: Tyck = Get(reporterW, reporterE, mutBox)

    val result = ExprTyckerInternal(ctx).unify(subType, superType)

    TyckResult(mutBox.value, result, reporterW.getReports, reporterE.getReports)
  }

  def unifyEffect(subEffect: EffectTerm, superEffect: EffectTerm, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Term] = {
    val reporterW = new VectorReporter[TyckWarning]()
    val reporterE = new VectorReporter[TyckError]()
    val mutBox = MutBox(state)
    implicit val tyck: Tyck = Get(reporterW, reporterE, mutBox)

    val result = ExprTyckerInternal(ctx).unifyEffect(subEffect, superEffect)

    TyckResult(mutBox.value, result, reporterW.getReports, reporterE.getReports)
  }

  def inherit(expr: Expr, ty: Term, effect: Option[EffectTerm] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    val reporterW = new VectorReporter[TyckWarning]()
    val reporterE = new VectorReporter[TyckError]()
    val mutBox = MutBox(state)
    implicit val tyck: Tyck = Get(reporterW, reporterE, mutBox)

    val result = ExprTyckerInternal(ctx).inherit(expr, ty, effect)

    TyckResult(mutBox.value, result, reporterW.getReports, reporterE.getReports)
  }

  def synthesize(expr: Expr, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    val reporterW = new VectorReporter[TyckWarning]()
    val reporterE = new VectorReporter[TyckError]()
    val mutBox = MutBox(state)
    implicit val tyck: Tyck = Get(reporterW, reporterE, mutBox)

    val result = ExprTyckerInternal(ctx).synthesize(expr)

    TyckResult(mutBox.value, result, reporterW.getReports, reporterE.getReports)
  }

}
