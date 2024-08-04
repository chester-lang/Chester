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

type Id = String

case class ObjectTerm(clauses: Vector[(Id, Term)], sourcePos: Option[SourcePos] = None) extends Term

object ObjectTerm {
  @deprecated("Use the constructor with Vector[(String, Term)] instead of Map[String, Term]", "1.0")
  def apply(clauses: Map[String, Term], sourcePos: Option[SourcePos]): ObjectTerm = {
    new ObjectTerm(clauses.toVector, sourcePos)
  }

  @deprecated("Use the constructor with Vector[(String, Term)] instead of Map[String, Term]", "1.0")
  def apply(clauses: Map[String, Term]): ObjectTerm = {
    new ObjectTerm(clauses.toVector, None)
  }
}

case class ObjectType(fieldTypes: Vector[(Id, Term)], sourcePos: Option[SourcePos] = None) extends Term

object ObjectType {
  @deprecated("Use the constructor with Vector[(String, Term)] instead of Map[String, Term]", "1.0")
  def apply(fieldTypes: Map[String, Term], sourcePos: Option[SourcePos]): ObjectType = {
    new ObjectType(fieldTypes.toVector, sourcePos)
  }

  @deprecated("Use the constructor with Vector[(String, Term)] instead of Map[String, Term]", "1.0")
  def apply(fieldTypes: Map[String, Term]): ObjectType = {
    new ObjectType(fieldTypes.toVector, None)
  }
}

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