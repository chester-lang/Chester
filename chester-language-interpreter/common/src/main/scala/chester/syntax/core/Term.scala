package chester.syntax.core

import chester.error.{SourcePos, WithPos}

sealed trait Term extends WithPos {
  def sourcePos: Option[SourcePos]
}

case class ListTerm(terms: Vector[Term], sourcePos: Option[SourcePos] = None) extends Term

sealed trait Sort extends Term {

}

case class Type(level: Term, sourcePos: Option[SourcePos] = None) extends Sort

val Type0 = Type(IntegerTerm(0))

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

case class AnyTerm(sourcePos: Option[SourcePos] = None) extends TypeTerm


case class ObjectTerm(clauses: Map[String, Term], sourcePos: Option[SourcePos] = None) extends Term


case class ObjectType(fieldTypes: Map[String, Term], sourcePos: Option[SourcePos] = None) extends Term


case class OrType(xs: Vector[Term], sourcePos: Option[SourcePos] = None) extends Term {
  assert(xs.nonEmpty)
}

case class AndType(xs: Vector[Term], sourcePos: Option[SourcePos] = None) extends Term {
  assert(xs.nonEmpty)
}

sealed trait EffectTerm extends Term {
}

case class EffectList(xs: Vector[Term], sourcePos: Option[SourcePos] = None) extends EffectTerm {
  assert(xs.nonEmpty)
}

case class NoEffect(sourcePos: Option[SourcePos] = None) extends EffectTerm

case class PartialEffect(sourcePos: Option[SourcePos] = None) extends EffectTerm

case class DivergeEffect(sourcePos: Option[SourcePos] = None) extends EffectTerm