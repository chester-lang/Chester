package chester.resolve

import cats.implicits.*
import chester.error.*
import chester.syntax.Const
import chester.syntax.concrete.*

case class DesugarInfo()

private object DesaltCaseClauseMatch {
  @throws[TyckError]
  def unapply(x: Expr): Option[DesaltCaseClause] = x match {
    case OpSeq(Vector(Identifier(Const.Case, _), pattern, Identifier(Const.>=, _), returning), meta) => Some(DesaltCaseClause(pattern, returning, meta))
    case OpSeq(Vector(Identifier(Const.Case, _), _*), _) => throw ExpectCase(x)
    case _ => None
  }
}

private object MatchDeclarationTelescope {
  @throws[TyckError]
  def unapply(x: Expr): Option[Telescope] = ???
}

private object MatchApplyingTelescope {
  @throws[TyckError]
  def unapply(x: Expr): Option[Telescope] = ???
}

private object SingleExpr {
  def unapply(xs: Seq[Expr]): Option[Expr] = {
    if (xs.isEmpty) return None
    if (xs.tail.isEmpty) return Some(xs.head)
    val xs0 = xs.tail.traverse(MatchApplyingTelescope.unapply)
    if (xs0.isDefined) return Some(FunctionCall.calls(xs.head, xs0.get))
    None
  }

  object Expect {
    @throws[TyckError]
    def unapply(xs: Seq[Expr]): Some[Expr] = SingleExpr.unapply(xs) match {
      case Some(x) => Some(x)
      case None => throw ExpectSingleExpr(xs)
    }
  }
}

private object DesaltSimpleFunction {
  def predicate(x: Expr): Boolean = x match {
    case Identifier(Const.Arrow, _) => true
    case _ => false
  }

  @throws[TyckError]
  def unapply(x: Expr) = x match {
    case OpSeq(xs, meta) if xs.exists(predicate) => {
      val index = xs.indexWhere(predicate)
      val before = xs.take(index)
      val after = xs.drop(index + 1)
      (before.traverse(MatchDeclarationTelescope.unapply), after) match {
        case (Some(Vector(telescope*)), SingleExpr.Expect(body)) => FunctionExpr(telescope = telescope.toVector, body = body, meta = meta)
        case _ => throw ExpectLambda(x)
      }
    }
    case _ => None
  }
}

case object SimpleDesalt {
  @throws[TyckError]
  def desugar(expr: Expr): Expr = expr.descentAndApply {
    case DesaltCaseClauseMatch(x) => x
    case b@Block(heads, tail, meta) if heads.contains(DesaltCaseClause) || tail.contains(DesaltCaseClause) => {
      if (heads.isEmpty || tail.nonEmpty || !heads.forall(_.isInstanceOf[DesaltCaseClause])) throw ExpectFullCaseBlock(b)
      val heads1: Vector[DesaltCaseClause] = heads.map(_.asInstanceOf[DesaltCaseClause])
      DesaltMatching(heads1, meta)
    }
    case default => default
  }

  def desugarEither(expr: Expr): Either[TyckError, Expr] = try {
    Right(desugar(expr))
  } catch {
    case e: TyckError => Left(e)
  }
}

case object OpSeqDesalt {
  @throws[TyckError]
  def desugar(expr: Expr): Expr = ???
}
