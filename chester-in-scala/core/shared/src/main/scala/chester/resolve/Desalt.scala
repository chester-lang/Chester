package chester.resolve

import cats.implicits.*
import chester.error.*
import chester.syntax.Const
import chester.syntax.concrete.*
import chester.tyck.*
import chester.utils.reuse

case class DesugarInfo()

private object ExpectDesaltPattern {
  @throws[TyckError]
  def unapply(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Some[DesaltPattern] = x match {
    case _ => throw ExpectPattern(x)
  }
}

private object DesaltCaseClauseMatch {
  @throws[TyckError]
  def unapply(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Option[DesaltCaseClause] = x match {
    case OpSeq(Vector(Identifier(Const.Case, _), pattern, Identifier(Const.Arrow2, _), returning), meta) => Some(DesaltCaseClause(pattern, returning, meta))
    case OpSeq(Vector(Identifier(Const.Case, _), _*), _) => throw ExpectCase(x)
    case _ => None
  }
}

private object MatchDeclarationTelescope {
  @throws[TyckError]
  def unapply(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Option[Telescope] = x match {
    case id: Identifier => Some(Telescope(Vector(Arg(name = Some(id)))))
    case _ => ???
  }
}

private object MatchApplyingTelescope {
  @throws[TyckError]
  def unapply(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Option[Telescope] = ???
}

private object SingleExpr {
  def unapply(xs: Seq[Expr])(using reporter: Reporter[TyckErrorOrWarning]): Option[Expr] = {
    if (xs.isEmpty) return None
    if (xs.tail.isEmpty) return Some(xs.head)
    val xs0 = xs.tail.traverse(MatchApplyingTelescope.unapply)
    if (xs0.isDefined) return Some(FunctionCall.calls(xs.head, xs0.get))
    None
  }

  object Expect {
    @throws[TyckError]
    def unapply(xs: Seq[Expr])(using reporter: Reporter[TyckErrorOrWarning]): Some[Expr] = SingleExpr.unapply(xs) match {
      case Some(x) => Some(x)
      case None => throw ExpectSingleExpr(xs)
    }
  }
}

private object DesaltSimpleFunction {
  def predicate(x: Expr): Boolean = x match {
    case Identifier(Const.Arrow2, _) => true
    case _ => false
  }

  @throws[TyckError]
  def unapply(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Option[Expr] = x match {
    case OpSeq(xs, meta) if xs.exists(predicate) => {
      val index = xs.indexWhere(predicate)
      val before = xs.take(index)
      val after = xs.drop(index + 1)
      (before.traverse(MatchDeclarationTelescope.unapply), after) match {
        case (Some(Vector(telescope*)), SingleExpr.Expect(body)) => {
          Some(FunctionExpr(telescope = telescope.toVector, body = body, meta = meta))
        }
        case _ => throw ExpectLambda(x)
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

case object StmtDesaltDeprecated {
  @throws[TyckWarning]
  @throws[TyckError]
  def desugar(expr: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Expr = {
    val (w, e, result) = BasicStatementResolver.resolveStatement(expr)
    reporter.report(w)
    reporter.report(e)
    result match {
      case ExprStmt(x, _) => x
      case result => result
    }
  }
}

case object PatternDesalt {
  def desugar(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Option[DesaltPattern] = x match {
    case id@Identifier(_, meta) => Some(PatternBind(id, meta))
    case _ => None // TODO: more
  }
}

case object MatchDefinedTelescope {
  def unapply(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Option[Telescope] = x match {
    // TODO
    case _ => None
  }
}

case object StmtDesalt {
  def desugar(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Expr = x match {
    case StmtDesalt(x) => x
    case _ => x
  }

  def defined(xs: Vector[Expr])(using reporter: Reporter[TyckErrorOrWarning]): Option[Defined] = {
    if (xs.length < 1) return None
    if (xs.length == 1) return PatternDesalt.desugar(xs.head).map(DefinedPattern(_))
    xs.head match
      case identifier: Identifier =>
        val telescopes = xs.tail
        telescopes.traverse(MatchDefinedTelescope.unapply).map { telescopes =>
          DefinedFunction(identifier, telescopes)
        }
      case _ =>
        return None
  }

  @throws[TyckWarning]
  @throws[TyckError]
  def letdef(decorations: Vector[Expr], kw: Identifier, xs: Vector[Expr], cause: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Stmt = {
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
    if (xs.length < 1) throw ExpectLetDef(cause)
    if (typeAnnotation == -1 && valueAnnotation == -1) {
      val on = defined(xs).getOrElse(throw ExpectLetDef(cause))
      return LetDefStmt(kind, on, decorations = decorations, meta = cause.meta)
    }
    if (typeAnnotation != -1 && valueAnnotation == -1) {
      val on = defined(xs.take(typeAnnotation)).getOrElse(throw ExpectLetDef(cause))
      val typeExpr = SingleExpr.unapply(xs.drop(typeAnnotation + 1)).getOrElse(throw ExpectLetDef(cause))
      return LetDefStmt(kind, on, ty = Some(typeExpr), decorations = decorations, meta = cause.meta)
    }
    if (typeAnnotation == -1 && valueAnnotation != -1) {
      val on = defined(xs.take(valueAnnotation)).getOrElse(throw ExpectLetDef(cause))
      val valueExpr = SingleExpr.unapply(xs.drop(valueAnnotation + 1)).getOrElse(throw ExpectLetDef(cause))
      return LetDefStmt(kind, on, body = Some(valueExpr), decorations = decorations, meta = cause.meta)
    }
    if (typeAnnotation != -1 && valueAnnotation != -1) {
      if (typeAnnotation > valueAnnotation) throw ExpectLetDef(cause)
      val on = defined(xs.take(typeAnnotation)).getOrElse(throw ExpectLetDef(cause))
      val typeExpr = SingleExpr.unapply(xs.slice(typeAnnotation + 1, valueAnnotation)).getOrElse(throw ExpectLetDef(cause))
      val valueExpr = SingleExpr.unapply(xs.drop(valueAnnotation + 1)).getOrElse(throw ExpectLetDef(cause))
      return LetDefStmt(kind, on, ty = Some(typeExpr), body = Some(valueExpr), decorations = decorations, meta = cause.meta)
    }
    throw ExpectLetDef(cause)
  }

  @throws[TyckWarning]
  @throws[TyckError]
  def unapply(x: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Option[Stmt] = x match {
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
  @throws[TyckWarning]
  @throws[TyckError]
  def desugar(expr: Expr)(using reporter: Reporter[TyckErrorOrWarning]): Expr = expr.descentRecursive {
    case DesaltCaseClauseMatch(x) => x
    case b@Block(heads, tail, meta) if heads.exists(_.isInstanceOf[DesaltCaseClause]) || tail.exists(_.isInstanceOf[DesaltCaseClause]) => {
      val seq: Vector[Expr] = heads ++ tail.toVector
      if (seq.isEmpty || !seq.forall(_.isInstanceOf[DesaltCaseClause])) throw ExpectFullCaseBlock(b)
      val heads1: Vector[DesaltCaseClause] = seq.map(_.asInstanceOf[DesaltCaseClause])
      DesaltMatching(heads1, meta)
    }
    case b@Block(heads, tail, meta) => {
      reuse(b, Block(heads.map(StmtDesalt.desugar), tail.map(StmtDesalt.desugar), meta))
    }
    case DesaltSimpleFunction(x) => x
    case obj: ObjectExpr => ObjectDesalt.desugarObjectExpr(obj)
    case default => default
  }
}

case object OpSeqDesalt {
  @throws[TyckError]
  def desugar(expr: Expr): Expr = ???
}
