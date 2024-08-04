package chester.syntax.core

import chester.error.{SourcePos, WithPos}

case class TermMeta(sourcePos: Option[SourcePos])

sealed trait Term extends WithPos {
  def meta: Option[TermMeta]
  def sourcePos: Option[SourcePos] = meta.flatMap(_.sourcePos)
}

case class ListTerm(terms: Vector[Term], meta: Option[TermMeta] = None) extends Term

sealed trait Sort extends Term

case class Type(level: Term, meta: Option[TermMeta] = None) extends Sort

val Type0 = Type(IntegerTerm(0))

// Referencing Setω in Agda
case class Typeω(meta: Option[TermMeta] = None) extends Sort

case class IntegerTerm(value: BigInt, meta: Option[TermMeta] = None) extends Term

sealed trait TypeTerm extends Term

case class IntegerType(meta: Option[TermMeta] = None) extends TypeTerm

case class DoubleTerm(value: BigDecimal, meta: Option[TermMeta] = None) extends Term

case class StringTerm(value: String, meta: Option[TermMeta] = None) extends Term

case class DoubleType(meta: Option[TermMeta] = None) extends TypeTerm

case class StringType(meta: Option[TermMeta] = None) extends TypeTerm

case class AnyTerm(meta: Option[TermMeta] = None) extends TypeTerm

type Id = String

case class ObjectTerm(clauses: Vector[(Id, Term)], meta: Option[TermMeta] = None) extends Term

object ObjectTerm {
  @deprecated("Use the constructor with Vector[(String, Term)] instead of Map[String, Term]", "1.0")
  def apply(clauses: Map[String, Term], meta: Option[TermMeta]): ObjectTerm = {
    new ObjectTerm(clauses.toVector, meta)
  }

  @deprecated("Use the constructor with Vector[(String, Term)] instead of Map[String, Term]", "1.0")
  def apply(clauses: Map[String, Term]): ObjectTerm = {
    new ObjectTerm(clauses.toVector, None)
  }
}

case class ObjectType(fieldTypes: Vector[(Id, Term)], meta: Option[TermMeta] = None) extends Term

object ObjectType {
  @deprecated("Use the constructor with Vector[(String, Term)] instead of Map[String, Term]", "1.0")
  def apply(fieldTypes: Map[String, Term], meta: Option[TermMeta]): ObjectType = {
    new ObjectType(fieldTypes.toVector, meta)
  }

  @deprecated("Use the constructor with Vector[(String, Term)] instead of Map[String, Term]", "1.0")
  def apply(fieldTypes: Map[String, Term]): ObjectType = {
    new ObjectType(fieldTypes.toVector, None)
  }
}

case class OrType(xs: Vector[Term], meta: Option[TermMeta] = None) extends Term {
  require(xs.nonEmpty)
}

case class AndType(xs: Vector[Term], meta: Option[TermMeta] = None) extends Term {
  require(xs.nonEmpty)
}

sealed trait EffectTerm extends Term

case class EffectList(xs: Vector[Term], meta: Option[TermMeta] = None) extends EffectTerm {
  require(xs.nonEmpty)
}

case class NoEffect(meta: Option[TermMeta] = None) extends EffectTerm

case class PartialEffect(meta: Option[TermMeta] = None) extends EffectTerm

case class DivergeEffect(meta: Option[TermMeta] = None) extends EffectTerm
