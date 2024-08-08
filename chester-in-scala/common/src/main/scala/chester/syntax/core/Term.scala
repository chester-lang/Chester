package chester.syntax.core

import chester.error.{SourcePos, WithPos}
import chester.petty.doc._
import chester.petty.doc.Implicits._

case class TermMeta(sourcePos: Option[SourcePos])

sealed trait Term extends WithPos with ToDoc {
  def meta: Option[TermMeta]
  def sourcePos: Option[SourcePos] = meta.flatMap(_.sourcePos)
}

case class ListTerm(terms: Vector[Term], meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("[", "]", ",")(terms*)
}

sealed trait Sort extends Term

case class Type(level: Term, meta: Option[TermMeta] = None) extends Sort {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("Type(", ")")(level)
}

val Type0 = Type(IntegerTerm(0))

// Referencing Setω in Agda
case class Typeω(meta: Option[TermMeta] = None) extends Sort {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Typeω")
}

case class IntegerTerm(value: BigInt, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString)
}

sealed trait TypeTerm extends Term

case class IntegerType(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Integer")
}

case class DoubleTerm(value: BigDecimal, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString)
}

case class StringTerm(value: String, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value)
}

case class DoubleType(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Double")
}

case class StringType(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("String")
}

case class AnyTerm(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Any")
}

type Id = String

case class ObjectTerm(clauses: Vector[(Id, Term)], meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("{", "}", ",")(clauses.map { case (id, term) => Doc.text(id) <+> Doc.text("=") <+> term }: _*)
}

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

case class ObjectType(fieldTypes: Vector[(Id, Term)], meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("{", "}", ",")(fieldTypes.map { case (id, term) => Doc.text(id) <+> Doc.text(":") <+> term }: _*)
}

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
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("(", ")", " | ")(xs: _*)
}

case class AndType(xs: Vector[Term], meta: Option[TermMeta] = None) extends Term {
  require(xs.nonEmpty)
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("(", ")", " & ")(xs: _*)
}

sealed trait EffectTerm extends Term

case class EffectList(xs: Vector[Term], meta: Option[TermMeta] = None) extends EffectTerm {
  require(xs.nonEmpty)
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("[", "]", ",")(xs: _*)
}

case class NoEffect(meta: Option[TermMeta] = None) extends EffectTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("NoEffect")
}

case class PartialEffect(meta: Option[TermMeta] = None) extends EffectTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("PartialEffect")
}

case class DivergeEffect(meta: Option[TermMeta] = None) extends EffectTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("DivergeEffect")
}
