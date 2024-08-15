package chester.syntax.core

import chester.error._
import chester.pretty.const.ColorProfile
import chester.pretty.doc.*
import chester.pretty.doc.Implicits.*
import chester.syntax.{Id, QualifiedIDString}
import chester.utils.encodeString

case class TermMeta(sourcePos: Option[SourcePos])

sealed trait Term extends WithPos with ToDoc {
  def meta: Option[TermMeta]

  def sourcePos: Option[SourcePos] = meta.flatMap(_.sourcePos)
}

case class ListTerm(terms: Vector[Term], meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("[", "]", ",")(terms *)
}

sealed trait Sort extends Term

case class Type(level: Term, meta: Option[TermMeta] = None) extends Sort {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("Type(", ")")(level)
}

val Type0 = Type(IntegerTerm(0))

// Referencing Setω in Agda
case class Typeω(meta: Option[TermMeta] = None) extends Sort {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Typeω").colored(ColorProfile.typeColor)
}

case class IntegerTerm(value: BigInt, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString).colored(ColorProfile.literalColor)
}

sealed trait TypeTerm extends Term

case class IntegerType(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Integer").colored(ColorProfile.typeColor)
}

case class DoubleTerm(value: BigDecimal, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString).colored(ColorProfile.literalColor)
}

case class StringTerm(value: String, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("\"" + encodeString(value) + "\"").colored(ColorProfile.literalColor)
}

case class SymbolTerm(value: String, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(":" + value).colored(ColorProfile.literalColor)
}

case class DoubleType(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Double")
}

case class StringType(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("String")
}

case class SymbolType(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Symbol")
}

case class AnyTerm(meta: Option[TermMeta] = None) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Any").colored(ColorProfile.typeColor)
}

case class ObjectClauseValueTerm(key: Term, value: Term, meta: Option[TermMeta] = None) {
  def toDoc(implicit options: PrettierOptions): Doc = key <+> Doc.text("=") <+> value
}

case class ObjectTerm(clauses: Vector[ObjectClauseValueTerm], meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc =
    Doc.wrapperlist("{", "}", ",")(clauses.map(_.toDoc): _*)
}


// TODO: add a modifier to disallow subtyping on fields - that is fields must be exact
case class ObjectType(fieldTypes: Vector[ObjectClauseValueTerm], meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc =
    Doc.wrapperlist("{", "}", ",")(fieldTypes.map(_.toDoc): _*)
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

object EffectList {
  // do flatten
  def apply(xs: Vector[Term], meta: Option[TermMeta] = None): EffectTerm = {
    val flattened = xs.flatMap {
      case EffectList(ys, _) => ys
      case x => Vector(x)
    } .filter {
      case NoEffect(_) => false
      case _ => true
    }
    if(flattened.nonEmpty) new EffectList(flattened, meta) else NoEffect(meta)
  }
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

private object ResolvedVarCounter {
  var varIdCounter = 0
}

case class VarId(id: Int)

object VarId {
  def generate: VarId = ResolvedVarCounter.synchronized {
    ResolvedVarCounter.varIdCounter += 1
    VarId(ResolvedVarCounter.varIdCounter)
  }
}

case class LocalVar(id: Id, ty: Term, varId: VarId, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(id)
}

case class ResolvedIdenifier(module: QualifiedIDString, Id: Id, ty: Term, varId: VarId, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(module.mkString(".") + "." + Id)
}

case class ErrorTerm(val error: TyckError) extends Term {
  override def meta: Option[TermMeta] = None

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(error.message)
}

case class MetaTerm(id: VarId, meta: Option[TermMeta] = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("MetaTerm#" + id)
}
