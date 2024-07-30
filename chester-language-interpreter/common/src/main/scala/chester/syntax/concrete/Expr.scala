package chester.syntax.concrete

import chester.error.{SourcePos, WithPos}
import chester.syntax.IdentifierRules.strIsOperator
import chester.utils.encodeString

enum CommentType {
  case OneLine
  case MultiLine
}

case class Comment(content: String, typ: CommentType, sourcePos: SourcePos)

case class CommentInfo(commentBefore: Option[Comment], commentAfter: Option[Comment]) {
  if (commentBefore.isEmpty && commentAfter.isEmpty) {
    throw new IllegalArgumentException("At least one comment should be present")
  }
}

sealed trait Expr extends WithPos {
  def descent(operator: Expr => Expr): Expr = this

  def descentAndApply(operator: Expr => Expr): Expr = operator(this.descent(operator))

  def commentInfo: Option[CommentInfo] = None

  def sourcePos: Option[SourcePos]
}

sealed trait ParsedExpr extends Expr


case class Identifier(name: String, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedExpr {
  override def toString: String = sourcePos match {
    case None => s"Identifier(\"${encodeString(name)}\")"
    case Some(pos) => s"Identifier(\"${encodeString(name)}\", ${pos.toString})"
  }
}

// infix prefix postfix
case class OpSeq(seq: Vector[Expr], sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    OpSeq(seq.map(_.descentAndApply(operator)), sourcePos)
  }

  def flatten: OpSeq = copy(seq = seq.flatMap {
    case opseq: OpSeq => opseq.flatten.seq
    case expr => Vector(expr)
  })
}

case class Infix(op: Expr, left: Expr, right: Expr, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Infix(op.descentAndApply(operator), left.descentAndApply(operator), right.descentAndApply(operator), sourcePos)
  }
}

case class Prefix(op: Expr, operand: Expr, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Prefix(op.descentAndApply(operator), operand.descentAndApply(operator), sourcePos)
  }
}

case class Postfix(op: Expr, operand: Expr, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Postfix(op.descentAndApply(operator), operand.descentAndApply(operator), sourcePos)
  }
}

case class Block(heads: Vector[Expr], tail: Option[Expr], sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    Block(heads.map(_.descentAndApply(operator)), tail.map(_.descentAndApply(operator)), sourcePos)
  }
}

object Block {
  def apply(heads: Vector[Expr], tail: Expr): Block = Block(heads, Some(tail), None)

  def apply(heads: Vector[Expr], tail: Expr, sourcePos: Option[SourcePos]): Block = Block(heads, Some(tail), sourcePos)
}

case class MacroCall(macroName: Expr, args: Vector[Expr], sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    MacroCall(macroName.descentAndApply(operator), args.map(_.descentAndApply(operator)), sourcePos)
  }
}

// maybe argument in function call or in function declaration
case class Arg(decorations: Vector[Identifier] = Vector(), name: Option[Identifier], ty: Option[Expr], exprOrDefault: Option[Expr], vararg: Boolean = false) {
  assert(name.isDefined || exprOrDefault.isDefined)

  def descentAndApply(operator: Expr => Expr): Arg = {
    Arg(decorations, name, ty.map(_.descentAndApply(operator)), exprOrDefault.map(_.descentAndApply(operator)))
  }

  override def toString: String = this match {
    case Arg(Vector(), None, None, Some(expr), false) => s"Arg.of($expr)"
    case Arg(decorations, name, ty, exorOrDefault, false) => s"Arg($decorations,$name,$ty,$exorOrDefault)"
    case Arg(decorations, name, ty, exorOrDefault, vararg) => s"Arg($decorations,$name,$ty,$exorOrDefault,$vararg)"
  }
}

object Arg {
  def of(expr: Expr): Arg = Arg(Vector.empty, None, None, Some(expr))
}

sealed trait MaybeTelescope extends Expr {
  override def descent(operator: Expr => Expr): MaybeTelescope = super.descent(operator).asInstanceOf[MaybeTelescope]
}

sealed trait ParsedMaybeTelescope extends MaybeTelescope with ParsedExpr

case class Tuple(terms: Vector[Expr], sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedMaybeTelescope {
  override def descent(operator: Expr => Expr): Tuple = {
    Tuple(terms.map(_.descentAndApply(operator)), sourcePos)
  }
}

case class Telescope(args: Vector[Arg], implicitly: Boolean = false, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends MaybeTelescope {
  override def descent(operator: Expr => Expr): Telescope = {
    Telescope(args.map(_.descentAndApply(operator)), implicitly, sourcePos)
  }
}

object Telescope {
  def of(args: Arg*)(implicit sourcePos: Option[SourcePos] = None): Telescope = Telescope(args.toVector, sourcePos = sourcePos)
}

case class FunctionCall(function: Expr, telescope: MaybeTelescope, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    FunctionCall(function.descentAndApply(operator), telescope.descent(operator), sourcePos)
  }
}

case class DotCall(expr: Expr, field: Expr, telescope: Vector[MaybeTelescope], sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    DotCall(expr.descentAndApply(operator), expr.descentAndApply(operator), telescope.map(_.descent(operator)), sourcePos)
  }
}

sealed trait NumberLiteral extends ParsedExpr

case class IntegerLiteral(value: BigInt, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends NumberLiteral

case class DoubleLiteral(value: BigDecimal, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends NumberLiteral

case class StringLiteral(value: String, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedExpr {
  override def toString: String = sourcePos match {
    case None => s"StringLiteral(\"${encodeString(value)}\")"
    case Some(pos) => s"StringLiteral(\"${encodeString(value)}\", ${pos.toString})"
  }
}

case class ListExpr(terms: Vector[Expr], sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedMaybeTelescope {
  override def descent(operator: Expr => Expr): ListExpr = {
    ListExpr(terms.map(_.descentAndApply(operator)), sourcePos)
  }
}

case class HoleExpr(description: String, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends Expr

case class TypeAnnotation(expr: Expr, ty: Expr, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    TypeAnnotation(expr.descentAndApply(operator), ty.descentAndApply(operator), sourcePos)
  }
}

case class AnnotatedExpr(annotation: Identifier, telescope: Vector[MaybeTelescope], expr: Expr, sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    AnnotatedExpr(annotation, telescope.map(_.descent(operator)), expr.descentAndApply(operator), sourcePos)
  }
}


case class ObjectExpr(clauses: Vector[(Identifier, Expr)], sourcePos: Option[SourcePos] = None, override val commentInfo: Option[CommentInfo] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    ObjectExpr(clauses.map { case (k, v) => (k, v.descentAndApply(operator)) }, sourcePos)
  }
}