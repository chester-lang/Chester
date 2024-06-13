package chester.lang.term

import chester.lang.ast.SourceLocation
import chester.lang.reduce.Reduce

sealed trait Term {
  def location: Option[SourceLocation]

  def isWHNF: Boolean = false
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

type Identifier = String

case class IdentifierTerm(location: Option[SourceLocation], name: Identifier) extends Term

sealed trait TypeTerm extends Term

sealed trait Sort
case class TypeSort(level: Int) extends Sort
case class UniverseSort() extends Sort

case class SortTerm(location: Option[SourceLocation], sort: Sort) extends TypeTerm

case class AnyType(location: Option[SourceLocation]) extends TypeTerm

case class UnionType(location: Option[SourceLocation], types: List[Term]) extends TypeTerm

case class IntersectionType(location: Option[SourceLocation], types: List[Term]) extends TypeTerm

case class TableType(location: Option[SourceLocation], entries: List[TableEntryType]) extends TypeTerm
case class TableEntryType(key: Term, valueType: Term)

case class FunctionType(location: Option[SourceLocation], params: List[(String, Term)], returnType: Term) extends TypeTerm

sealed trait EffectTerm extends Term

trait OpaqueTerm extends Term

trait TransplantTerm extends Term {
  def unroll: Term
}

