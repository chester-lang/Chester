package chester.tyck

import cats.data.State
import chester.error.SourcePos
import chester.syntax.concrete.*
import chester.syntax.core.*

import scala.language.implicitConversions

case class TyckState()

object BuiltinCtx {
  val builtinSyntax = Vector("data", "module")
}

case class LocalCtx()

object LocalCtx {
  val Empty = LocalCtx()
}

case class Judge(wellTyped: Term, ty: Term, effect: Term)

sealed trait TyckError {
  def message: String

  def cause: Option[Term | Expr]

  def location: Option[SourcePos] = cause match {
    case Some(term: Term) => term.sourcePos
    case Some(expr: Expr) => expr.sourcePos
    case _ => None
  }

  val stack: Array[StackTraceElement] = new Exception().getStackTrace
}

sealed trait TyckWarning {

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

case class ExprTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty) {
  def unify(subType: Term, superType: Term): TyckGetting[Term] = {
    if (subType == superType) return Getting.pure(subType)
    (subType, superType) match {
      case (_, AnyTerm(_)) => Getting.pure(subType) // AnyTerm matches any subtype
      case _ => Getting.error(UnifyFailedError(subType, superType))
    }
  }

  def unifyEffect(subEffect: Term, superEffect: Term): TyckGetting[Term] = {
    (subEffect, superEffect) match {
      case (_, NoEffect(_)) => Getting.pure(subEffect)
      case (NoEffect(_), _) => Getting.pure(superEffect)
      case _ if subEffect == superEffect => Getting.pure(subEffect)
      case _ => Getting.error(UnifyFailedError(subEffect, superEffect))
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
          val updatedClauses = acc.clauses :+ (Identifier(k) -> v)
          acc.copy(clauses = updatedClauses)
        case (acc, (Vector(k, ks*), v)) =>
          val nestedExpr = acc.clauses.find(_._1 == Identifier(k)) match {
            case Some((_, nestedObj: ObjectExpr)) =>
              insertNested(Vector((ks.toVector, v)), nestedObj)
            case _ =>
              insertNested(Vector((ks.toVector, v)), ObjectExpr(Vector.empty))
          }
          val updatedClauses = acc.clauses.filterNot(_._1 == Identifier(k)) :+ (Identifier(k) -> nestedExpr)
          acc.copy(clauses = updatedClauses)
        case (acc, (Vector(), _)) => acc
      }
    }

    val desugaredClauses = expr.clauses.map {
      case (qname, expr) => (desugarQualifiedName(qname), expr)
    }
    insertNested(desugaredClauses, ObjectExpr(Vector.empty))
  }

  def synthesizeObjectExpr(clauses: Vector[(QualifiedName, Expr)]): TyckGetting[ObjectTerm] = {
    for {
      typedClauses <- clauses.foldLeft(Getting.pure(Vector.empty[(String, Term)])) { (acc, clause) =>
        for {
          typedClauses <- acc
          (qualifiedName, expr) = clause
          Judge(wellTypedExpr, _, _) <- synthesize(expr)
        } yield typedClauses :+ (desugarQualifiedName(qualifiedName).mkString("."), wellTypedExpr)
      }
    } yield ObjectTerm(typedClauses)
  }

  def synthesizeObjectType(clauses: Vector[(QualifiedName, Expr)]): TyckGetting[ObjectType] = {
    for {
      typedClauses <- clauses.foldLeft(Getting.pure(Vector.empty[(String, Term)])) { (acc, clause) =>
        for {
          typedClauses <- acc
          (qualifiedName, expr) = clause
          Judge(_, exprType, _) <- synthesize(expr)
        } yield typedClauses :+ (desugarQualifiedName(qualifiedName).mkString("."), exprType)
      }
    } yield ObjectType(typedClauses)
  }

  def synthesize(expr: Expr): TyckGetting[Judge] = expr match {
    case IntegerLiteral(value, meta) =>
      val termMeta = convertMeta(meta)
      Getting.pure(Judge(IntegerTerm(value, termMeta), IntegerType(termMeta), NoEffect(termMeta)))
    case DoubleLiteral(value, meta) =>
      val termMeta = convertMeta(meta)
      Getting.pure(Judge(DoubleTerm(value, termMeta), DoubleType(termMeta), NoEffect(termMeta)))
    case StringLiteral(value, meta) =>
      val termMeta = convertMeta(meta)
      Getting.pure(Judge(StringTerm(value, termMeta), StringType(termMeta), NoEffect(termMeta)))
    case objExpr: ObjectExpr =>
      val desugaredExpr = desugarObjectExpr(objExpr)
      for {
        objTerm <- synthesizeObjectExpr(desugaredExpr.clauses)
        objType <- synthesizeObjectType(desugaredExpr.clauses)
      } yield Judge(objTerm, objType, NoEffect(convertMeta(objExpr.meta)))
    case _ => Getting.error(UnsupportedExpressionError(expr))
  }

  def inheritObjectFields(clauses: Vector[(QualifiedName, Expr)], fieldTypes: Vector[(String, Term)], effect: Option[Term]): TyckGetting[Vector[(String, Term)]] = {
    clauses.foldLeft(Getting.pure(Vector.empty[(String, Term)])) { (acc, clause) =>
      for {
        typedClauses <- acc
        (qualifiedName, expr) = clause
        fieldType <- fieldTypes.find(_._1 == desugarQualifiedName(qualifiedName).head).map(_._2) match {
          case Some(ft) => inherit(expr, ft, effect).map(_.wellTyped)
          case None => Getting.error(FieldTypeNotFoundError(qualifiedName))
        }
      } yield typedClauses :+ (desugarQualifiedName(qualifiedName).head, fieldType)
    }
  }

  val normalizer = new Normalizer()
  val whnf: Term => TyckGetting[Term] = normalizer.apply

  def inherit0(expr: Expr, ty: Term, effect: Option[Term]): TyckGetting[Judge] = {
    whnf(ty).flatMap { ty =>
      expr match {
        case ObjectExpr(clauses, meta) =>
          ty match {
            case ObjectType(fieldTypes, _) =>
              for {
                inheritedFields <- inheritObjectFields(clauses, fieldTypes, effect)
                effect <- effect match {
                  case Some(eff) => Getting.pure(eff)
                  case None => Getting.pure(NoEffect(convertMeta(meta)))
                }
              } yield Judge(ObjectTerm(inheritedFields, convertMeta(meta)), ty, effect)
            case _ => Getting.error(ExpectedObjectTypeError())
          }
        case default => Getting.error(UnsupportedExpressionError(default))
      }
    }
  }

  def inherit(expr: Expr, ty: Term, effect: Option[Term] = None): TyckGetting[Judge] = effect match {
    case Some(eff) => inherit0(expr, ty, Some(eff)) || (for {
      Judge(wellTyped, judgeTy, judgeEffect) <- synthesize(expr)
      ty1 <- unify(judgeTy, ty)
      effect1 <- unifyEffect(judgeEffect, eff)
    } yield Judge(wellTyped, ty1, effect1))
    case None => inherit0(expr, ty, None) || (for {
      Judge(wellTyped, judgeTy, judgeEffect) <- synthesize(expr)
      ty1 <- unify(judgeTy, ty)
    } yield Judge(wellTyped, ty1, judgeEffect))
  }
}

case class StateAndResult[S, T](state: S, result: T)

case class TyckResult[S, T](stateAndResult: Option[StateAndResult[S, T]], warnings: Vector[TyckWarning], errors: Vector[TyckError])
object ExprTycker {
  @deprecated("some error information might be lost")
  def unifyV0(subType: Term, superType: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Term] = {
    ExprTyckerInternal(ctx).unify(subType, superType).getOne(state).map(_._2)
  }

  @deprecated("some error information might be lost")
  def unifyEffectV0(subEffect: EffectTerm, superEffect: EffectTerm, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Term] = {
    ExprTyckerInternal(ctx).unifyEffect(subEffect, superEffect).getOne(state).map(_._2)
  }

  @deprecated("some error information might be lost")
  def inheritV0(expr: Expr, ty: Term, effect: Option[EffectTerm] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Judge] = {
    ExprTyckerInternal(ctx).inherit(expr, ty, effect).getOne(state).map(_._2)
  }

  @deprecated("some error information might be lost")
  def synthesizeV0(expr: Expr, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[Vector[TyckError], Judge] = {
    ExprTyckerInternal(ctx).synthesize(expr).getOne(state).map(_._2)
  }

  def unifyFull(subType: Term, superType: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Term] = {
    val result = ExprTyckerInternal(ctx).unify(subType, superType).getSome(state)
    TyckResult(result._3.map { case (s, r) => StateAndResult(s, r) }, result._1, result._2)
  }

  def unifyEffectFull(subEffect: EffectTerm, superEffect: EffectTerm, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Term] = {
    val result = ExprTyckerInternal(ctx).unifyEffect(subEffect, superEffect).getSome(state)
    TyckResult(result._3.map { case (s, r) => StateAndResult(s, r) }, result._1, result._2)
  }

  def inheritFull(expr: Expr, ty: Term, effect: Option[EffectTerm] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    val result = ExprTyckerInternal(ctx).inherit(expr, ty, effect).getSome(state)
    TyckResult(result._3.map { case (s, r) => StateAndResult(s, r) }, result._1, result._2)
  }

  def synthesizeFull(expr: Expr, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    val result = ExprTyckerInternal(ctx).synthesize(expr).getSome(state)
    TyckResult(result._3.map { case (s, r) => StateAndResult(s, r) }, result._1, result._2)
  }
}
