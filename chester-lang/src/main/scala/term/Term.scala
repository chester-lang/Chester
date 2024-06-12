package chester.lang.term

import chester.lang.ast.SourceLocation
import chester.lang.reduce.Reduce

sealed trait Term {
  def location: Option[SourceLocation]

  def isWHNF: Boolean = false

  lazy val whnf: Term = Reduce(this)
}


case class TableTerm(location: Option[SourceLocation], entries: List[TableEntry]) extends Term


sealed trait TableEntry {
  def isWHNF: Boolean = false
}

case class SimpleTableEntry(key: Term, value: Term) extends TableEntry

case class PrimitiveTerm(location: Option[SourceLocation], name: String) extends Term

case class StringTerm(location: Option[SourceLocation], value: String) extends Term

case class IntegerTerm(location: Option[SourceLocation], value: BigInt) extends Term

case class CallTerm(location: Option[SourceLocation], callee: Term, list: List[Term]) extends Term

case class MacroCallTerm(location: Option[SourceLocation], callee: Term, list: List[Term]) extends Term

case class QuasiquoteTerm(location: Option[SourceLocation], expr: Term) extends Term

case class QuoteTerm(location: Option[SourceLocation], expr: Term) extends Term

case class UnquoteTerm(location: Option[SourceLocation], expr: Term) extends Term

case class UnquoteSplicing(location: Option[SourceLocation], expr: Term) extends Term

case class The(location: Option[SourceLocation], expr: Term, itsType: Term) extends Term

sealed trait TypeTerm extends Term

case class AnyType(location: Option[SourceLocation]) extends TypeTerm

case class UnionType(location: Option[SourceLocation], types: List[Term]) extends TypeTerm

case class IntersectionType(location: Option[SourceLocation], types: List[Term]) extends TypeTerm