package chester.syntax.core

import chester.error.{SourcePos, WithPos}

sealed trait Term extends WithPos {
  def sourcePos: Option[SourcePos]
}

case class Object(clauses: Map[String, Term], sourcePos: Option[SourcePos] = None) extends Term

case class ListTerm(terms: Vector[Term], sourcePos: Option[SourcePos] = None) extends Term

sealed trait Sort extends Term {

}

case class Type(level: BigInt, sourcePos: Option[SourcePos] = None) extends Sort

val Type0 = Type(0)

// Referencing Setω in Agda
case class Typeω(sourcePos: Option[SourcePos] = None) extends Sort


case class IntegerTerm(value: BigInt, sourcePos: Option[SourcePos] = None) extends Term

sealed trait TypeTerm extends Term {

}

case class IntegerType(sourcePos: Option[SourcePos] = None) extends TypeTerm

case class DoubleTerm(value: BigDecimal, sourcePos: Option[SourcePos] = None) extends Term
case class StringTerm(value: String, sourcePos: Option[SourcePos] = None) extends Term

case class DoubleType(sourcePos: Option[SourcePos] = None) extends TypeTerm
case class StringType(sourcePos: Option[SourcePos] = None) extends TypeTerm
