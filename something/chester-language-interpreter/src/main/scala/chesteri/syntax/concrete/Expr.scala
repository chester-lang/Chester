package chesteri.syntax.concrete

import chesteri.common.SourceLocation

sealed abstract class Expr {
  private var sourceLocationVar: Option[SourceLocation] = None

  def sourceLocation: Option[SourceLocation] = sourceLocationVar

  def withSourceLocation(location: SourceLocation): this.type = {
    assert(sourceLocationVar.isEmpty, "Source location already set")
    sourceLocationVar = Some(location)
    this
  }

  def descent(operator: Expr => Expr): Expr = this
  def descentAndApply(operator: Expr => Expr): Expr = operator(this.descent(operator))
}

sealed trait Salt


case class Identifier(name: String) extends Expr

// infix prefix postfix
case class BinOpSeq(seq: Seq[Expr]) extends Expr with Salt{
  override def descent(operator: Expr => Expr): Expr = {
    BinOpSeq(seq.map(_.descentAndApply(operator)))
  }
}

case class Infix(op: Expr, left: Expr, right: Expr) extends Expr{
  override def descent(operator: Expr => Expr): Expr = {
    Infix(op.descentAndApply(operator), left.descentAndApply(operator), right.descentAndApply(operator))
  }
}

case class Prefix(op: Expr, operand: Expr) extends Expr{
  override def descent(operator: Expr => Expr): Expr = {
    Prefix(op.descentAndApply(operator), operand.descentAndApply(operator))
  }
}

case class Postfix(op: Expr, operand: Expr) extends Expr{
  override def descent(operator: Expr => Expr): Expr = {
    Postfix(op.descentAndApply(operator), operand.descentAndApply(operator))
  }
}

case class Block(heads: Vector[Expr], tail: Expr) extends Expr{
  override def descent(operator: Expr => Expr): Expr = {
    Block(heads.map(_.descentAndApply(operator)), tail.descentAndApply(operator))
  }
}

case class MacroCall(macroName: Expr, args: Vector[Expr]) extends Expr{
  override def descent(operator: Expr => Expr): Expr = {
    MacroCall(macroName.descentAndApply(operator), args.map(_.descentAndApply(operator)))
  }
}

case class FunctionCall(function: Expr, implicitArgs: Vector[Expr], args: Vector[Expr]) extends Expr {
  def hasImplicitArgs: Boolean = implicitArgs.nonEmpty
  override def descent(operator: Expr => Expr): Expr = {
    FunctionCall(function.descentAndApply(operator), implicitArgs.map(_.descentAndApply(operator)), args.map(_.descentAndApply(operator)))
  }
}

case class IntegerLiteral(value: Int) extends Expr

case class StringLiteral(value: String) extends Expr

case class VectorExpr(terms: Vector[Expr]) extends Expr
