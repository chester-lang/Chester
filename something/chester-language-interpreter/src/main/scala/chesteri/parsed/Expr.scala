package chesteri.parsed

import chesteri.common.SourceLocation

sealed abstract class Expr {
  private var sourceLocationVar: Option[SourceLocation] = None
  def sourceLocation: Option[SourceLocation] = sourceLocationVar
  def withSourceLocation(location: SourceLocation): this.type = {
    assert(sourceLocationVar.isEmpty, "Source location already set")
    sourceLocationVar = Some(location)
    this
  }
}


case class Identifier(name: String) extends Expr

// infix prefix postfix
case class BinOpSeq(seq: Seq[Expr]) extends Expr

case class Block(heads: Vector[Expr], tail: Expr) extends Expr

case class MacroCall(macroName: Identifier, args: Vector[Expr]) extends Expr

case class FunctionCall(function: Expr, implicitArgs: Vector[Expr], args: Vector[Expr]) extends Expr {
  def hasImplicitArgs: Boolean = implicitArgs.nonEmpty
}

case class IntegerLiteral(value: Int) extends Expr

case class StringLiteral(value: String) extends Expr

case class VectorExpr(terms: Vector[Expr]) extends Expr

sealed trait NamedTyped extends Expr

case class Named(name: Identifier, value: Expr) extends Expr with NamedTyped

case class Typed(name: Identifier, typeExpr: Expr) extends Expr with NamedTyped

case class NameTyped(name: Identifier, typeExpr: Expr, value: Expr) extends Expr with NamedTyped
