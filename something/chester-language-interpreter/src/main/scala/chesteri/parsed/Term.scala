package chesteri.parsed

import chesteri.common.SourceLocation

sealed abstract class Term {
  private var sourceLocationVar: Option[SourceLocation] = None
  def sourceLocation: Option[SourceLocation] = sourceLocationVar
  def withSourceLocation(location: SourceLocation): this.type = {
    assert(sourceLocationVar.isEmpty, "Source location already set")
    sourceLocationVar = Some(location)
    this
  }
}


case class Identifier(name: String) extends Term

// infix prefix postfix
case class BinOpSeq(seq: Seq[Term]) extends Term

case class Block(heads: Vector[Term], tail: Term) extends Term

case class MacroCall(macroName: Identifier, args: Vector[Term]) extends Term

case class FunctionCall(function: Term, implicitArgs: Vector[Term], args: Vector[Term]) extends Term {
  def hasImplicitArgs: Boolean = implicitArgs.nonEmpty
}

case class IntegerLiteral(value: Int) extends Term

case class StringLiteral(value: String) extends Term

case class VectorTerm(terms: Vector[Term]) extends Term