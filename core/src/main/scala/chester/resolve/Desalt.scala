package chester.resolve

import cats.implicits.*
import chester.error.*
import chester.syntax.Const
import chester.syntax.concrete.*
import chester.tyck.*
import chester.utils.reuse
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.constraint.collection.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.upickle.given

case class DesugarInfo()

private object DesaltCaseClauseMatch {
  def unapply(x: Expr)(using reporter: Reporter[TyckProblem]): Option[DesaltCaseClause] = x match {
    case OpSeq(Vector(Identifier(Const.Case, _), pattern, Identifier(Const.Arrow2, _), returning), meta) => 
      Some(DesaltCaseClause(pattern, returning, meta))
    case OpSeq(Vector(Identifier(Const.Case, _), _*), _) => 
      val error = ExpectCase(x)
      reporter(error)
      None
    case _ => None
  }
}

private object MatchDeclarationTelescope {
  def unapply(x: Expr)(using reporter: Reporter[TyckProblem]): Option[DefTelescope] = x match {
    case id: Identifier => Some(DefTelescope(Vector(Arg(name = id, meta = id.meta))))
    case _ => ???
  }
}

def opSeq(xs: Seq[Expr])(using reporter: Reporter[TyckProblem]): Expr = SimpleDesalt.desugar(OpSeq(xs.toVector))

@scala.deprecated("use opSeq")
private object SingleExpr {
  def unapply(xs: Seq[Expr])(using reporter: Reporter[TyckProblem]): Option[Expr] = {
    Some(opSeq(xs))
  }

  object Expect {
    def unapply(xs: Seq[Expr])(using reporter: Reporter[TyckProblem]): Some[Expr] = Some(opSeq(xs))
  }
}

private object DesaltSimpleFunction {
  def predicate(x: Expr): Boolean = x match {
    case Identifier(Const.Arrow2, _) => true
    case _ => false
  }

  def unapply(x: Expr)(using reporter: Reporter[TyckProblem]): Option[Expr] = x match {
    case OpSeq(xs, meta) if xs.exists(predicate) => {
      val index = xs.indexWhere(predicate)
      assert(index >= 0)
      val before = xs.take(index)
      val after = xs.drop(index + 1)
      (before.traverse(MatchDeclarationTelescope.unapply), after) match {
        case (Some(Vector(telescope*)), SingleExpr.Expect(body)) => 
          Some(FunctionExpr(telescope = telescope.toVector, body = body, meta = meta))
        case _ => 
          val error = ExpectLambda(x)
          reporter(error)
          Some(DesaltFailed(x, error, meta))
      }
    }
    case _ => None
  }
}

// TODO: this implementation looks ugly
private object ObjectDesalt {
  def desugarQualifiedName(qname: QualifiedName): Vector[String] = qname match {
    case Identifier(name, _) => Vector(name)
    case DotCall(expr, field: Identifier, _, _) => desugarQualifiedName(expr.asInstanceOf[QualifiedName]) :+ field.name
    case _ => throw new IllegalArgumentException("Invalid QualifiedName structure")
  }

  def desugarObjectExpr0(expr: ObjectExpr): ObjectExpr = {
    def insertNested(fields: Vector[(Vector[String], Expr)], base: ObjectExpr): ObjectExpr = {
      fields.foldLeft(base) {
        case (acc, (Vector(k), v)) =>
          val updatedClauses = acc.clauses :+ ObjectExprClause(Identifier(k), v)
          acc.copy(clauses = updatedClauses)
        case (acc, (Vector(k, ks@_*), v)) =>
          val nestedExpr = acc.clauses.collectFirst {
            case ObjectExprClause(id: Identifier, nestedObj: ObjectExpr) if id.name == k =>
              nestedObj
          } match {
            case Some(existingNestedObj) =>
              insertNested(Vector((ks.toVector, v)), existingNestedObj)
            case None =>
              insertNested(Vector((ks.toVector, v)), ObjectExpr(Vector.empty))
          }
          val updatedClauses = acc.clauses.filter {
            case ObjectExprClause(id: Identifier, _) => id.name != k
            case _ => true
          } :+ ObjectExprClause(Identifier(k), nestedExpr)
          acc.copy(clauses = updatedClauses)
        case (acc, (Vector(), _)) => acc
      }
    }

    var moreClauses: Vector[ObjectExprClauseOnValue] = Vector()
    val desugaredClauses = expr.clauses.flatMap {
      case ObjectExprClause(qname, expr) => Some(desugarQualifiedName(qname), expr)
      case clause: ObjectExprClauseOnValue => {
        moreClauses = moreClauses :+ clause
        None
      }
    }
    expr.copy(clauses = insertNested(desugaredClauses, ObjectExpr(Vector.empty)).clauses ++ moreClauses)
  }

  def desugarObjectExprStep2(expr: ObjectExpr): ObjectExpr = expr.copy(clauses = expr.clauses.map {
    case clause: ObjectExprClauseOnValue => clause
    case ObjectExprClause(key: Identifier, value) => ObjectExprClauseOnValue(SymbolLiteral(key.name, key.meta), value)
    case _ => throw new IllegalArgumentException("This is second step")
  })

  def desugarObjectExpr(expr: ObjectExpr): ObjectExpr = desugarObjectExprStep2(desugarObjectExpr0(expr))
}

case object PatternDesalt {
  def desugar(x: Expr)(using reporter: Reporter[TyckProblem]): Option[DesaltPattern] = x match {
    case id@Identifier(_, meta) => Some(PatternBind(id, meta))
    case _ => None // TODO: more
  }
}

case object MatchDefinedTelescope {
  def unapply(x: Expr)(using reporter: Reporter[TyckProblem]): Option[DefTelescope] = x match {
    // TODO
    case _ => None
  }
}

case object StmtDesalt {
  def desugar(x: Expr)(using reporter: Reporter[TyckProblem]): Expr = x match {
    case StmtDesalt(x) => x
    case _ => x
  }

  def defined(xs: Vector[Expr])(using reporter: Reporter[TyckProblem]): Option[Defined] = {
    if (xs.length < 1) return None
    if (xs.length == 1) return PatternDesalt.desugar(xs.head).map(DefinedPattern(_))
    xs.head match
      case identifier: Identifier =>
        val telescopes = xs.tail
        telescopes.traverse(MatchDefinedTelescope.unapply).map { telescopes =>
          DefinedFunction(identifier, telescopes.refineUnsafe)
        }
      case _ =>
        return None
  }

  def letdef(decorations: Vector[Expr], kw: Identifier, xs: Vector[Expr], cause: Expr)(using reporter: Reporter[TyckProblem]): Stmt = {
    val typeAnnotation = xs.indexWhere {
      case Identifier(Const.`:`, meta) => true
      case _ => false
    }
    val valueAnnotation = xs.indexWhere {
      case Identifier(Const.`=`, meta) => true
      case _ => false
    }
    val kind = kw.name match {
      case Const.Let => LetDefType.Let
      case Const.Def => LetDefType.Def
      case _ => unreachable(s"Unknown keyword ${kw.name}")
    }
    if (xs.length < 1) {
      val error = ExpectLetDef(cause)
      reporter(error)
      return DesaltFailed(cause, error, cause.meta)
    }
    if (typeAnnotation == -1 && valueAnnotation == -1) {
      val on = defined(xs).getOrElse {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      return LetDefStmt(kind, on, decorations = decorations, meta = cause.meta)
    }
    if (typeAnnotation != -1 && valueAnnotation == -1) {
      val on = defined(xs.take(typeAnnotation)).getOrElse {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      val typeExpr = SingleExpr.unapply(xs.drop(typeAnnotation + 1)).getOrElse {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      return LetDefStmt(kind, on, ty = Some(typeExpr), decorations = decorations, meta = cause.meta)
    }
    if (typeAnnotation == -1 && valueAnnotation != -1) {
      val on = defined(xs.take(valueAnnotation)).getOrElse {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      val valueExpr = SingleExpr.unapply(xs.drop(valueAnnotation + 1)).getOrElse {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      return LetDefStmt(kind, on, body = Some(valueExpr), decorations = decorations, meta = cause.meta)
    }
    if (typeAnnotation != -1 && valueAnnotation != -1) {
      if (typeAnnotation > valueAnnotation) {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      val on = defined(xs.take(typeAnnotation)).getOrElse {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      val typeExpr = SingleExpr.unapply(xs.slice(typeAnnotation + 1, valueAnnotation)).getOrElse {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      val valueExpr = SingleExpr.unapply(xs.drop(valueAnnotation + 1)).getOrElse {
        val error = ExpectLetDef(cause)
        reporter(error)
        return DesaltFailed(cause, error, cause.meta)
      }
      return LetDefStmt(kind, on, ty = Some(typeExpr), body = Some(valueExpr), decorations = decorations, meta = cause.meta)
    }
    val error = ExpectLetDef(cause)
    reporter(error)
    DesaltFailed(cause, error, cause.meta)
  }

  def unapply(x: Expr)(using reporter: Reporter[TyckProblem]): Option[Stmt] = x match {
    case opseq@OpSeq(seq, meta) => {
      val kw = seq.indexWhere {
        case Identifier(id, meta) if Const.kw1.contains(id) => true
        case _ => false
      }
      if (kw == -1) return None
      val kwId = seq(kw).asInstanceOf[Identifier]
      val beforeKw = seq.take(kw)
      val afterKw = seq.drop(kw + 1)
      val beforeKWIsAllIdentifier = beforeKw.forall {
        case Identifier(_, _) => true
        case _ => false
      }
      if (!beforeKWIsAllIdentifier) return None
      kwId.name match {
        case Const.Let | Const.Def => Some(letdef(beforeKw, kwId, afterKw, opseq))
        case _ => unreachable(s"Unknown keyword ${kwId.name}")
      }
    }
    case _ => None
  }
}

case object SimpleDesalt {
  def desugar(expr: Expr)(using reporter: Reporter[TyckProblem]): Expr = expr.descentRecursive {
    case OpSeq(xs, meta) if xs.length == 1 => xs.head
    case DesaltCaseClauseMatch(x) => x
    case b@Block(heads, tail, meta) if heads.exists(_.isInstanceOf[DesaltCaseClause]) || tail.exists(_.isInstanceOf[DesaltCaseClause]) => {
      val seq: Vector[Expr] = heads ++ tail.toVector
      if (seq.isEmpty || !seq.forall(_.isInstanceOf[DesaltCaseClause])) {
        val error = ExpectFullCaseBlock(b)
        reporter(error)
        DesaltFailed(b, error, meta)
      } else {
        val heads1: Vector[DesaltCaseClause] = seq.map(_.asInstanceOf[DesaltCaseClause])
        DesaltMatching(heads1, meta)
      }
    }
    case b@Block(heads, tail, meta) => 
      reuse(b, Block(heads.map(StmtDesalt.desugar), tail.map(StmtDesalt.desugar), meta))
    case DesaltSimpleFunction(x) => x
    case obj: ObjectExpr => ObjectDesalt.desugarObjectExpr(obj)
    case default => default
    case FunctionCall(function, telescopes, meta) =>
      val desugaredFunction = desugar(function)
      val desugaredTelescopes = telescopes.map {
        case t: Tuple => DesaltCallingTelescope(t.terms.map(term => CallingArg(expr = desugar(term))), meta = t.meta)
        case other => 
          reporter(UnexpectedTelescope(other))
          DesaltCallingTelescope(Vector(CallingArg(expr = desugar(other))), meta = other.meta)
      }
      DesaltFunctionCall(desugaredFunction, desugaredTelescopes, meta)
  }
}

case object OpSeqDesalt {
  def desugar(expr: Expr): Expr = ???
}
