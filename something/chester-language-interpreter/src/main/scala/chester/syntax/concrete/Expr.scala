package chester.syntax.concrete

import chester.error.{SourcePos, WithPos}



sealed trait Expr extends WithPos {
  def descent(operator: Expr => Expr): Expr = this

  def descentAndApply(operator: Expr => Expr): Expr = operator(this.descent(operator))
}


sealed trait Salt


case class Identifier(sourcePos: Option[SourcePos], name: String) extends Expr

// infix prefix postfix
case class BinOpSeq(seq: Seq[Expr], sourcePos: Option[SourcePos] = None) extends Expr with Salt {
  override def descent(operator: Expr => Expr): Expr = {
    BinOpSeq(seq.map(_.descentAndApply(operator)), sourcePos)
  }
}

case class Infix(op: Expr, left: Expr, right: Expr, sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Infix(op.descentAndApply(operator), left.descentAndApply(operator), right.descentAndApply(operator), sourcePos)
  }
}

case class Prefix(op: Expr, operand: Expr, sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Prefix(op.descentAndApply(operator), operand.descentAndApply(operator), sourcePos)
  }
}

case class Postfix(op: Expr, operand: Expr, sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Postfix(op.descentAndApply(operator), operand.descentAndApply(operator), sourcePos)
  }
}

case class Block(heads: Vector[Expr], tail: Expr, sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Block(heads.map(_.descentAndApply(operator)), tail.descentAndApply(operator), sourcePos)
  }
}

case class MacroCall(macroName: Expr, args: Vector[Expr], sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    MacroCall(macroName.descentAndApply(operator), args.map(_.descentAndApply(operator)), sourcePos)
  }
}

case class FunctionCall(function: Expr, implicitArgs: Vector[Expr], args: Vector[Expr], sourcePos: Option[SourcePos] = None) extends Expr {
  def hasImplicitArgs: Boolean = implicitArgs.nonEmpty

  override def descent(operator: Expr => Expr): Expr = {
    FunctionCall(function.descentAndApply(operator), implicitArgs.map(_.descentAndApply(operator)), args.map(_.descentAndApply(operator)), sourcePos)
  }
}

case class IntegerLiteral(value: BigInt, sourcePos: Option[SourcePos] = None) extends Expr

case class StringLiteral(value: String, sourcePos: Option[SourcePos] = None) extends Expr

case class ListExpr(terms: Vector[Expr], sourcePos: Option[SourcePos] = None) extends Expr

case class HoleExpr(description: String, sourcePos: Option[SourcePos] = None) extends Expr
