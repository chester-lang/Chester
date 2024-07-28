package chester.syntax.concrete

import chester.error.{SourcePos, WithPos}


sealed trait Expr extends WithPos {
  def descent(operator: Expr => Expr): Expr = this

  def descentAndApply(operator: Expr => Expr): Expr = operator(this.descent(operator))
}


sealed trait Salt


case class Identifier(name: String, sourcePos: Option[SourcePos] = None) extends Expr

// infix prefix postfix
case class BinOpSeq(seq: Vector[Expr], sourcePos: Option[SourcePos] = None) extends Expr with Salt {
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

// maybe argument in function call or in function declaration
case class Arg(decorations: Vector[Identifier], name: Option[Identifier], ty: Option[Expr], exprOrDefault: Option[Expr]) {
  assert(name.isDefined || exprOrDefault.isDefined)

  def descentAndApply(operator: Expr => Expr): Arg = {
    Arg(decorations, name, ty.map(_.descentAndApply(operator)), exprOrDefault.map(_.descentAndApply(operator)))
  }
}

object Arg {
  def of(expr: Expr): Arg = Arg(Vector.empty, None, None, Some(expr))
}

case class Telescope(args: Vector[Arg], sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Telescope = {
    Telescope(args.map(_.descentAndApply(operator)), sourcePos)
  }
}

object Telescope {
  def of(args: Arg*)(implicit sourcePos: Option[SourcePos] = None): Telescope = Telescope(args.toVector, sourcePos)
}

case class FunctionCall(function: Expr, telescope: Telescope, sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    FunctionCall(function.descentAndApply(operator), telescope.descent(operator), sourcePos)
  }
}

case class DotCall(expr: Expr, field: Expr, telescope: Vector[Telescope], sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    DotCall(expr.descentAndApply(operator), expr.descentAndApply(operator), telescope.map(_.descent(operator)), sourcePos)
  }
}

sealed trait NumberLiteral extends Expr

case class IntegerLiteral(value: BigInt, sourcePos: Option[SourcePos] = None) extends NumberLiteral

case class DoubleLiteral(value: BigDecimal, sourcePos: Option[SourcePos] = None) extends NumberLiteral

case class StringLiteral(value: String, sourcePos: Option[SourcePos] = None) extends Expr

case class ListExpr(terms: Vector[Expr], sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    ListExpr(terms.map(_.descentAndApply(operator)), sourcePos)
  }
}

case class HoleExpr(description: String, sourcePos: Option[SourcePos] = None) extends Expr

case class TypeAnnotation(expr: Expr, ty: Expr, sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    TypeAnnotation(expr.descentAndApply(operator), ty.descentAndApply(operator), sourcePos)
  }
}

case class AnnotatedExpr(annotation: Identifier, telescope: Option[Telescope], expr: Expr, sourcePos: Option[SourcePos] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    AnnotatedExpr(annotation, telescope.map(_.descent(operator)), expr.descentAndApply(operator), sourcePos)
  }
}
