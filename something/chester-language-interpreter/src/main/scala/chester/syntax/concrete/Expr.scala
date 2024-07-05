package chester.syntax.concrete

import chester.error.{SourcePos, WithPos}



sealed trait Expr extends WithPos {
  def descent(operator: Expr => Expr): Expr = this

  def descentAndApply(operator: Expr => Expr): Expr = operator(this.descent(operator))
}

sealed trait Salt


case class Identifier(sourcePos: Option[SourcePos], name: String) extends Expr

// infix prefix postfix
case class BinOpSeq(sourcePos: Option[SourcePos], seq: Seq[Expr]) extends Expr with Salt {
  override def descent(operator: Expr => Expr): Expr = {
    BinOpSeq(sourcePos, seq.map(_.descentAndApply(operator)))
  }
}

case class Infix(sourcePos: Option[SourcePos], op: Expr, left: Expr, right: Expr) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Infix(sourcePos, op.descentAndApply(operator), left.descentAndApply(operator), right.descentAndApply(operator))
  }
}

case class Prefix(sourcePos: Option[SourcePos], op: Expr, operand: Expr) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Prefix(sourcePos, op.descentAndApply(operator), operand.descentAndApply(operator))
  }
}

case class Postfix(sourcePos: Option[SourcePos], op: Expr, operand: Expr) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Postfix(sourcePos, op.descentAndApply(operator), operand.descentAndApply(operator))
  }
}

case class Block(sourcePos: Option[SourcePos], heads: Vector[Expr], tail: Expr) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Block(sourcePos, heads.map(_.descentAndApply(operator)), tail.descentAndApply(operator))
  }
}

case class MacroCall(sourcePos: Option[SourcePos], macroName: Expr, args: Vector[Expr]) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    MacroCall(sourcePos, macroName.descentAndApply(operator), args.map(_.descentAndApply(operator)))
  }
}

case class FunctionCall(sourcePos: Option[SourcePos], function: Expr, implicitArgs: Vector[Expr], args: Vector[Expr]) extends Expr {
  def hasImplicitArgs: Boolean = implicitArgs.nonEmpty

  override def descent(operator: Expr => Expr): Expr = {
    FunctionCall(sourcePos, function.descentAndApply(operator), implicitArgs.map(_.descentAndApply(operator)), args.map(_.descentAndApply(operator)))
  }
}

case class IntegerLiteral(sourcePos: Option[SourcePos], value: Int) extends Expr

case class StringLiteral(sourcePos: Option[SourcePos], value: String) extends Expr

case class VectorExpr(sourcePos: Option[SourcePos], terms: Vector[Expr]) extends Expr
