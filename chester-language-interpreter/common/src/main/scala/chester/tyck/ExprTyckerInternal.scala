package chester.tyck

import chester.syntax.concrete.*
import chester.syntax.core.*

case class TyckState()

object BuiltinCtx {
  val builtinSyntax = Vector("data", "module")
}

case class LocalCtx()

object LocalCtx {
  val Empty = LocalCtx()
}

case class Judge(wellTyped: Term, ty: Term)

case class TyckError(message: String)

object TyckError {
  val emptyResults = TyckError("Empty Results")

  def unifyFailed(subType: Term, superType: Term): TyckError = TyckError(s"Unification failed: $subType is not a subtype of $superType")
}

case class Getting[T](xs: TyckState => LazyList[Either[TyckError, (TyckState, T)]]) {

  def map[U](f: T => U): Getting[U] = Getting { state =>
    xs(state).map {
      case Left(err) => Left(err)
      case Right((nextState, value)) => Right((nextState, f(value)))
    }
  }

  def flatMap[U](f: T => Getting[U]): Getting[U] = Getting { state =>
    xs(state).flatMap {
      case Left(err) => LazyList(Left(err))
      case Right((nextState, value)) => f(value).xs(nextState)
    }
  }

  def getOne(state: TyckState): Either[TyckError, (TyckState, T)] = {
    xs(state).collectFirst {
      case right@Right(_) => right
    }.getOrElse(xs(state).headOption.getOrElse(Left(TyckError.emptyResults)))
  }

  def explainError(explain: TyckError => TyckError): Getting[T] = Getting { state =>
    xs(state).map {
      case Left(err) => Left(explain(err))
      case right => right
    }
  }

  def ||(other: => Getting[T]): Getting[T] = Getting { state =>
    xs(state) #::: other.xs(state)
  }
}

object Getting {
  def pure[T](x: T): Getting[T] = Getting(state => LazyList(Right((state, x))))

  def error[T](err: TyckError): Getting[T] = Getting(_ => LazyList(Left(err)))

  def read: Getting[TyckState] = Getting(state => LazyList(Right((state, state))))

  def write(newState: TyckState): Getting[Unit] = Getting(_ => LazyList(Right((newState, ()))))
}

case class ExprTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty) {
  def unify(subType: Term, superType: Term): Getting[Term] = {
    if (subType == superType) return Getting.pure(subType)
    (subType, superType) match {
      case (_, AnyTerm(_)) => Getting.pure(subType) // AnyTerm matches any subtype
      case _ => Getting.error(TyckError.unifyFailed(subType, superType))
    }
  }

  def desugarQualifiedName(qname: QualifiedName): Vector[String] = qname match {
    case Identifier(name, _, _) => Vector(name)
    case DotCall(expr, field: Identifier, _, _, _) => desugarQualifiedName(expr.asInstanceOf[QualifiedName]) :+ field.name
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

  def synthesizeObjectExpr(clauses: Vector[(QualifiedName, Expr)]): Getting[ObjectTerm] = {
    for {
      typedClauses <- clauses.foldLeft(Getting.pure(Vector.empty[(String, Term)])) { (acc, clause) =>
        for {
          typedClauses <- acc
          (qualifiedName, expr) = clause
          Judge(wellTypedExpr, _) <- synthesize(expr)
        } yield typedClauses :+ (desugarQualifiedName(qualifiedName).mkString("."), wellTypedExpr)
      }
    } yield ObjectTerm(typedClauses)
  }

  def synthesizeObjectType(clauses: Vector[(QualifiedName, Expr)]): Getting[ObjectType] = {
    for {
      typedClauses <- clauses.foldLeft(Getting.pure(Vector.empty[(String, Term)])) { (acc, clause) =>
        for {
          typedClauses <- acc
          (qualifiedName, expr) = clause
          Judge(_, exprType) <- synthesize(expr)
        } yield typedClauses :+ (desugarQualifiedName(qualifiedName).mkString("."), exprType)
      }
    } yield ObjectType(typedClauses)
  }

  def synthesize(expr: Expr): Getting[Judge] = expr match {
    case IntegerLiteral(value, sourcePos, _) => Getting.pure(Judge(IntegerTerm(value, sourcePos), IntegerType(sourcePos)))
    case DoubleLiteral(value, sourcePos, _) => Getting.pure(Judge(DoubleTerm(value, sourcePos), DoubleType(sourcePos)))
    case StringLiteral(value, sourcePos, _) => Getting.pure(Judge(StringTerm(value, sourcePos), StringType(sourcePos)))
    case objExpr: ObjectExpr =>
      val desugaredExpr = desugarObjectExpr(objExpr)
      for {
        objTerm <- synthesizeObjectExpr(desugaredExpr.clauses)
        objType <- synthesizeObjectType(desugaredExpr.clauses)
      } yield Judge(objTerm, objType)
    case _ => Getting.error(TyckError("Unsupported expression type"))
  }

  def inheritObjectFields(clauses: Vector[(QualifiedName, Expr)], fieldTypes: Vector[(String, Term)]): Getting[Vector[(String, Term)]] = {
    clauses.foldLeft(Getting.pure(Vector.empty[(String, Term)])) { (acc, clause) =>
      for {
        typedClauses <- acc
        (qualifiedName, expr) = clause
        fieldType <- fieldTypes.find(_._1 == desugarQualifiedName(qualifiedName).head).map(_._2) match {
          case Some(ft) => inherit(expr, ft).map(_.wellTyped)
          case None => Getting.error[Term](TyckError(s"Field type not found for ${qualifiedName.toString}"))
        }
      } yield typedClauses :+ (desugarQualifiedName(qualifiedName).head, fieldType)
    }
  }

  def inherit0(expr: Expr, ty: Term): Getting[Judge] = expr match {
    case ObjectExpr(clauses, sourcePos, _) =>
      ty match {
        case ObjectType(fieldTypes, _) =>
          for {
            inheritedFields <- inheritObjectFields(clauses, fieldTypes)
          } yield Judge(ObjectTerm(inheritedFields, sourcePos), ty)
        case _ => Getting.error(TyckError("Expected an ObjectType for inheritance"))
      }
    case default => Getting.error(TyckError("Unsupported expression type"))
  }

  def inherit(expr: Expr, ty: Term): Getting[Judge] = inherit0(expr, ty) || (for {
    Judge(wellTyped, judgeTy) <- synthesize(expr)
    ty1 <- unify(judgeTy, ty)
  } yield Judge(wellTyped, ty1))
}

object ExprTycker {
  def unify(subType: Term, superType: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[TyckError, Term] = {
    ExprTyckerInternal(ctx).unify(subType, superType).getOne(state).map(_._2)
  }

  def inherit(expr: Expr, ty: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[TyckError, Judge] = {
    ExprTyckerInternal(ctx).inherit(expr, ty).getOne(state).map(_._2)
  }

  def synthesize(expr: Expr, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): Either[TyckError, Judge] = {
    ExprTyckerInternal(ctx).synthesize(expr).getOne(state).map(_._2)
  }
}
