package chester.tyck

import cats.data.State
import chester.error.SourcePos
import chester.syntax.concrete.*
import chester.syntax.core.*

import scala.annotation.targetName
import scala.language.implicitConversions
import scala.language.implicitConversions


case class MutBox[T](var value: T)
case class TyckState()

object BuiltinCtx {
  val builtinSyntax = Vector("data", "module")
}

case class LocalCtx()

object LocalCtx {
  val Empty = LocalCtx()
}

trait Reporter[T] {
  def report(value: T): Unit
}

class VectorReporter[T] extends Reporter[T] {
  private val buffer = scala.collection.mutable.ArrayBuffer[T]()

  def report(value: T): Unit = buffer += value

  def getReports: Vector[T] = buffer.toVector
}

case class Judge(wellTyped: Term, ty: Term, effect: Term)

case class Get[W, E, S](warnings: Reporter[W], errors: Reporter[E], state: MutBox[S])

type Tyck = Get[TyckWarning, TyckError, TyckState]

trait TyckErrorOrWarning {
  def message: String

  def cause: Option[Term | Expr]

  def location: Option[SourcePos] = cause match {
    case Some(term: Term) => term.sourcePos
    case Some(expr: Expr) => expr.sourcePos
    case _ => None
  }

  val stack: Array[StackTraceElement] = new Exception().getStackTrace
}

trait TyckError extends TyckErrorOrWarning {
}

trait TyckWarning extends TyckErrorOrWarning {

}

case class EmptyResultsError() extends TyckError {
  def message: String = "Empty Results"

  def cause: Option[Term | Expr] = None
}

case class UnifyFailedError(subType: Term, superType: Term) extends TyckError {
  def message: String = s"Unification failed: $subType is not a subtype of $superType"

  def cause: Option[Term | Expr] = Some(subType)
}

case class UnsupportedExpressionError(expr: Expr) extends TyckError {
  def message: String = s"Unsupported expression type: $expr"

  def cause: Option[Term | Expr] = Some(expr)
}

case class FieldTypeNotFoundError(qualifiedName: QualifiedName) extends TyckError {
  def message: String = s"Field type not found for $qualifiedName"

  def cause: Option[Term | Expr] = Some(qualifiedName)
}

case class ExpectedObjectTypeError() extends TyckError {
  def message: String = "Expected an ObjectType for inheritance"

  def cause: Option[Term | Expr] = None
}

case class Getting[W, E, S, T](run: S => LazyList[(Vector[W], Vector[E], Option[(S, T)])]) {

  def map[U](f: T => U): Getting[W, E, S, U] = Getting { state =>
    run(state).map {
      case (warnings, errors, Some((nextState, value))) => (warnings, errors, Some((nextState, f(value))))
      case (warnings, errors, None) => (warnings, errors, None)
    }
  }

  def flatMap[U](f: T => Getting[W, E, S, U]): Getting[W, E, S, U] = Getting { state =>
    run(state).flatMap {
      case (warnings, errors, Some((nextState, value))) => f(value).run(nextState).map {
        case (w2, e2, res2) => (warnings ++ w2, errors ++ e2, res2)
      }
      case (warnings, errors, None) => LazyList((warnings, errors, None))
    }
  }

  @deprecated("some error information might be lost")
  def getOne(state: S): Either[Vector[E], (S, T)] = {
    val xs = run(state)
    // Try to collect the first non-error result
    xs.collectFirst {
      case (_, errors, Some(result)) if errors.isEmpty => Right(result)
    }.orElse(
      // Try to collect the first item regardless of errors
      xs.collectFirst {
        case (_, _, Some(result)) => Right(result)
      }
    ).getOrElse(
      // Fallback to an empty results error
      xs.collectFirst {
        case (_, errors, None) if errors.nonEmpty => Left(errors)
      }.getOrElse(Left(Vector(EmptyResultsError().asInstanceOf[E])))
    )
  }

  def getSome(state: S): (Vector[W], Vector[E], Option[(S, T)]) = {
    val xs = run(state)

    // 1. Try to find the first result with no errors and a defined state and value
    xs.collectFirst {
      case (warnings, errors, Some(result)) if errors.isEmpty => (warnings, errors, Some(result))
    }.orElse(
      // 2. If not found, try to find the first result with a defined state and value, regardless of errors
      xs.collectFirst {
        case (warnings, errors, Some(result)) => (warnings, errors, Some(result))
      }
    ).orElse(
      // 3. If not found, return the first result in xs
      xs.headOption
    ).getOrElse(
      // 4. If still not found, fallback to returning an EmptyResultsError
      (Vector.empty, Vector(EmptyResultsError().asInstanceOf[E]), None)
    )
  }

  def explainError(explain: E => E): Getting[W, E, S, T] = Getting { state =>
    run(state).map {
      case (warnings, errors, result) => (warnings, errors.map(explain), result)
    }
  }

  def ||(other: => Getting[W, E, S, T]): Getting[W, E, S, T] = Getting { state =>
    run(state) #::: other.run(state)
  }
}

object Getting {
  def pure[W, E, S, T](x: T): Getting[W, E, S, T] = Getting(state => LazyList((Vector.empty, Vector.empty, Some((state, x)))))

  def error[W, E, S, T](err: E): Getting[W, E, S, T] = Getting(_ => LazyList((Vector.empty, Vector(err), None)))

  def errors[W, E, S, T](errs: Vector[E]): Getting[W, E, S, T] = Getting(_ => LazyList((Vector.empty, errs, None)))

  def read[W, E, S]: Getting[W, E, S, S] = Getting(state => LazyList((Vector.empty, Vector.empty, Some((state, state)))))

  def write[W, E, S](newState: S): Getting[W, E, S, Unit] = Getting(_ => LazyList((Vector.empty, Vector.empty, Some((newState, ())))))
}

implicit def stateToGetting[W, E, T, U](state: State[T, U]): Getting[W, E, T, U] = Getting { s =>
  LazyList((Vector.empty, Vector.empty, Some(state.run(s).value)))
}

type TyckGetting[T] = Getting[TyckWarning, TyckError, TyckState, T]

case class ExprTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty)(implicit S: Tyck) {

  def unify(subType: Term, superType: Term): Term = {
    if (subType == superType) subType
    else (subType, superType) match {
      case (_, AnyTerm(_)) => subType
      case _ =>
        S.errors.report(UnifyFailedError(subType, superType))
        new ErrorTerm(UnifyFailedError(subType, superType))
    }
  }

  def unifyEffect(subEffect: Term, superEffect: Term): Term = {
    (subEffect, superEffect) match {
      case (_, NoEffect(_)) => subEffect
      case (NoEffect(_), _) => superEffect
      case _ if subEffect == superEffect => subEffect
      case _ =>
        S.errors.report(UnifyFailedError(subEffect, superEffect))
        new ErrorTerm(UnifyFailedError(subEffect, superEffect))
    }
  }

  def desugarQualifiedName(qname: QualifiedName): Vector[String] = qname match {
    case Identifier(name, _) => Vector(name)
    case DotCall(expr, field: Identifier, _, _) => desugarQualifiedName(expr.asInstanceOf[QualifiedName]) :+ field.name
    case _ => throw new IllegalArgumentException("Invalid QualifiedName structure")
  }

  def desugarObjectExpr(expr: ObjectExpr): ObjectExpr = {
    def insertNested(fields: Vector[(Vector[String], Expr)], base: ObjectExpr): ObjectExpr = {
      fields.foldLeft(base) {
        case (acc, (Vector(k), v)) =>
          val updatedClauses = acc.clauses :+ ObjectExprClause(Identifier(k) -> v)
          acc.copy(clauses = updatedClauses)
        case (acc, (Vector(k, ks@_*), v)) =>
          val nestedExpr = acc.clauses.find(_._1 == Identifier(k)) match {
            case Some(ObjectExprClause(_, nestedObj: ObjectExpr)) =>
              insertNested(Vector((ks.toVector, v)), nestedObj)
            case _ =>
              insertNested(Vector((ks.toVector, v)), ObjectExpr(Vector.empty))
          }
          val updatedClauses = acc.clauses.filterNot(_._1 == Identifier(k)) :+ ObjectExprClause(Identifier(k) -> nestedExpr)
          acc.copy(clauses = updatedClauses)
        case (acc, (Vector(), _)) => acc
      }
    }

    val desugaredClauses = expr.clauses.map {
      case ObjectExprClause(qname, expr) => (desugarQualifiedName(qname), expr)
    }
    insertNested(desugaredClauses, ObjectExpr(Vector.empty))
  }

  def synthesizeObjectExpr(clauses: Vector[(QualifiedName, Expr)]): ObjectTerm = {
    val typedClauses = clauses.map {
      case (qualifiedName, expr) =>
        synthesize(expr) match {
          case Judge(wellTypedExpr, _, _) => desugarQualifiedName(qualifiedName).mkString(".") -> wellTypedExpr
          case _ => desugarQualifiedName(qualifiedName).mkString(".") -> new ErrorTerm(UnsupportedExpressionError(expr))
        }
    }
    ObjectTerm(typedClauses)
  }

  def synthesizeObjectType(clauses: Vector[(QualifiedName, Expr)]): ObjectType = {
    val typedClauses = clauses.map {
      case (qualifiedName, expr) =>
        synthesize(expr) match {
          case Judge(_, exprType, _) => desugarQualifiedName(qualifiedName).mkString(".") -> exprType
          case _ => desugarQualifiedName(qualifiedName).mkString(".") -> new ErrorTerm(UnsupportedExpressionError(expr))
        }
    }
    ObjectType(typedClauses)
  }

  def synthesize(expr: Expr): Judge = expr match {
    case IntegerLiteral(value, meta) =>
      val termMeta = convertMeta(meta)
      Judge(IntegerTerm(value, termMeta), IntegerType(termMeta), NoEffect(termMeta))
    case DoubleLiteral(value, meta) =>
      val termMeta = convertMeta(meta)
      Judge(DoubleTerm(value, termMeta), DoubleType(termMeta), NoEffect(termMeta))
    case StringLiteral(value, meta) =>
      val termMeta = convertMeta(meta)
      Judge(StringTerm(value, termMeta), StringType(termMeta), NoEffect(termMeta))
    case objExpr: ObjectExpr =>
      val desugaredExpr = desugarObjectExpr(objExpr)
      val objTerm = synthesizeObjectExpr(desugaredExpr.clauses.map(_.toPair))
      val objType = synthesizeObjectType(desugaredExpr.clauses.map(_.toPair))
      Judge(objTerm, objType, NoEffect(convertMeta(objExpr.meta)))
    case _ =>
      S.errors.report(UnsupportedExpressionError(expr))
      Judge(new ErrorTerm(UnsupportedExpressionError(expr)), new ErrorTerm(UnsupportedExpressionError(expr)), NoEffect(None))
  }

  def inheritObjectFields(clauses: Vector[(QualifiedName, Expr)], fieldTypes: Vector[(String, Term)], effect: Option[Term]): Vector[(String, Term)] = {
    clauses.flatMap {
      case (qualifiedName, expr) =>
        fieldTypes.find(_._1 == desugarQualifiedName(qualifiedName).head) match {
          case Some((name, ft)) =>
            inherit(expr, ft, effect) match {
              case Judge(wellTyped, _, _) => Some(name -> wellTyped)
              case _ => None
            }
          case None =>
            S.errors.report(FieldTypeNotFoundError(qualifiedName))
            None
        }
    }
  }

  val normalizer = new Normalizer()

  def whnf(term: Term): Term = {
    val state = S.state.value
    val (newState, normalizedTerm) = normalizer.apply(term).run(state).value
    S.state.value = newState // Update the state with the new state from the normalizer
    normalizedTerm
  }

  def inherit(expr: Expr, ty: Term, effect: Option[Term] = None): Judge = {
    whnf(ty) match {
      case ObjectType(fieldTypes, _) =>
        val inheritedFields = inheritObjectFields(clauses = expr.asInstanceOf[ObjectExpr].clauses.map(_.toPair), fieldTypes = fieldTypes, effect = effect)
        Judge(ObjectTerm(inheritedFields), ty, effect.getOrElse(NoEffect(None)))
      case _ =>
        // call synthesis on the expression
        val Judge(wellTypedExpr, exprType, exprEffect) = synthesize(expr)
        val ty1 = unify(exprType, ty)
        val effect1 = if(effect.isDefined) unifyEffect(exprEffect, effect.getOrElse(NoEffect(None))) else exprEffect
        Judge(wellTypedExpr, ty1, effect1)
    }
  }
}

case class TyckResult[S, T](state: S, result: T, warnings: Vector[TyckWarning], errors: Vector[TyckError])

object ExprTycker {

  // Deprecated API using the old `Getting` monadic approach
  @deprecated("some error information might be lost")
  def unifyV0(subType: Term, superType: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Term] = {
    convertToGetting(ctx) { implicit tyck =>
      ExprTyckerInternal().unify(subType, superType)
    }.getOne(state).map(_._2)
  }

  @deprecated("some error information might be lost")
  def unifyEffectV0(subEffect: EffectTerm, superEffect: EffectTerm, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Term] = {
    convertToGetting(ctx) { implicit tyck =>
      ExprTyckerInternal().unifyEffect(subEffect, superEffect)
    }.getOne(state).map(_._2)
  }

  @deprecated("some error information might be lost")
  def inheritV0(expr: Expr, ty: Term, effect: Option[EffectTerm] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Judge] = {
    convertToGetting(ctx) { implicit tyck =>
      ExprTyckerInternal().inherit(expr, ty, effect)
    }.getOne(state).map(_._2)
  }

  @deprecated("some error information might be lost")
  def synthesizeV0(expr: Expr, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Judge] = {
    convertToGetting(ctx) { implicit tyck =>
      ExprTyckerInternal().synthesize(expr)
    }.getOne(state).map(_._2)
  }

  // New API using the `Get` approach
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

  private def convertToGetting[T](ctx: LocalCtx)(f: Tyck => T): Getting[TyckWarning, TyckError, TyckState, T] = {
    Getting { state =>
      val reporterW = new VectorReporter[TyckWarning]()
      val reporterE = new VectorReporter[TyckError]()
      val mutBox = MutBox(state)
      implicit val tyck: Tyck = Get(reporterW, reporterE, mutBox)
      val result = f(tyck)
      LazyList((reporterW.getReports, reporterE.getReports, Some((mutBox.value, result))))
    }
  }
}
