// TODO: More correctly implement toDoc
package chester.syntax.core

import chester.doc.*
import chester.doc.Doc.group
import chester.doc.const.{ColorProfile, Docs}
import chester.error.*
import chester.syntax.{Builtin, Id, QualifiedIDString}
import chester.utils.{encodeString, reuse}
import spire.math.Rational

import scala.language.implicitConversions

case class TermMeta(sourcePos: Option[SourcePos])

// so that it is not used in compare
case class OptionTermMeta(meta: Option[TermMeta] = None) {
  override def equals(obj: Any): Boolean = {
    obj match {
      case _: OptionTermMeta => true
      case _ => false
    }
  }
}

implicit def toOptionTermMeta(meta: Option[TermMeta]): OptionTermMeta = new OptionTermMeta(meta)
implicit def fromOptionTermMeta(meta: OptionTermMeta): Option[TermMeta] = meta.meta

sealed trait TermWithMeta extends Term with WithPos {
  def meta: OptionTermMeta

  def sourcePos: Option[SourcePos] = meta.flatMap(_.sourcePos)
}

/** CallTerm has meta to trace runtime errors and debug */
sealed trait MaybeCallTerm extends TermWithMeta {
  override def whnf: Boolean = false
}

case class CallingArg(value: Term, name: Option[Id] = None, vararg: Boolean = false) extends ToDoc {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    if (name.isEmpty && !vararg) return value.toDoc
    if (name.isEmpty && vararg) return group(value.toDoc <> Docs.`...`)
    val nameDoc = name.get
    val valueDoc = value.toDoc
    val varargDoc = if (vararg) Docs.`...` else Doc.empty
    group(nameDoc <+> valueDoc <+> varargDoc)
  }
}

case class Calling(args: Vector[CallingArg], implicitly: Boolean = false) extends ToDoc {
  def toDoc(implicit options: PrettierOptions): Doc = {
    val argsDoc = args.map(_.toDoc).reduce(_ <+> _)
    if (implicitly) Docs.`(` <> argsDoc <> Docs.`)` else argsDoc
  }
}

case class FCallTerm(f: Term, args: Vector[Calling], meta: OptionTermMeta = None) extends MaybeCallTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val fDoc = f.toDoc
    val argsDoc = args.map(_.toDoc).reduce(_ <+> _)
    group(fDoc <+> argsDoc)
  }

  override def whnf: Boolean = f match {
    case ListF => true
    case _ => false
  }
}

object FCallTerm {
  object call {
    def apply(f: Term, args: Term*): FCallTerm = FCallTerm(f, Vector(Calling(args.toVector.map(CallingArg(_)))))

    def unapplySeq(f: Term): Option[Seq[Term]] = f match {
      case FCallTerm(f, Seq(Calling(args, false)), _) if args.forall {
        case CallingArg(_, None, false) => true
        case _ => false
      } => Some(f +: args.map {
        case CallingArg(value, _, _) => value
      })
      case _ => None
    }
  }
}

sealed trait Pat extends ToDoc {
  override def toDoc(implicit options: PrettierOptions): Doc = toString

  def descent(patOp: Pat => Pat, termOp: Term => Term): Pat = this
}

sealed trait Term extends ToDoc {
  override def toDoc(implicit options: PrettierOptions): Doc = toString

  def whnf: Boolean = true

  protected final inline def thisOr(inline x: Term): this.type = reuse(this, x.asInstanceOf[this.type])

  def descent(f: Term => Term): Term = this

  def doElevate(level: IntegerTerm): Term = descent(_.doElevate(level))

  final def elevate(level: IntegerTerm): Term = {
    require(level.value >= 0)
    if (level.value == 0) this else doElevate(level)
  }
}

case class ListTerm(terms: Vector[Term]) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`[`, Docs.`]`, ",")(terms *)

  override def descent(f: Term => Term): Term = thisOr(ListTerm(terms.map(f)))
}

sealed trait Sort extends Term

case class Type(level: Term) extends Sort with Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("Type" <> Docs.`(`, Docs.`)`)(level)

  override def descent(f: Term => Term): Term = thisOr(Type(f(level)))
}

case object LevelType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("LevelType")

  override def descent(f: Term => Term): Term = this
}

case class Level(n: Term) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Level" + n)

  override def descent(f: Term => Term): Term = thisOr(Level(f(n)))
}

val Level0 = Level(IntegerTerm(0))

val Type0 = Type(Level0)

// Referencing Setω in Agda
case object Typeω extends Sort with Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Typeω").colored(ColorProfile.typeColor)
}

case class Prop(level: Term) extends Sort with Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("Prop" <> Docs.`(`, Docs.`)`)(level)
}

sealed trait LiteralTerm extends Term

case class IntTerm(value: Int) extends LiteralTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString).colored(ColorProfile.literalColor)
}

case class IntegerTerm(value: BigInt) extends LiteralTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString).colored(ColorProfile.literalColor)
}

sealed trait TypeTerm extends Term

case object IntegerType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Integer").colored(ColorProfile.typeColor)
}

// int of 64 bits or more
case object IntType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Int").colored(ColorProfile.typeColor)
}

// unsigned int of 64 bits or more
case object UIntType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("UInt").colored(ColorProfile.typeColor)
}

case object NaturalType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Natural").colored(ColorProfile.typeColor)
}

case class RationalTerm(value: Rational) extends LiteralTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString).colored(ColorProfile.literalColor)
}

case class StringTerm(value: String) extends LiteralTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("\"" + encodeString(value) + "\"").colored(ColorProfile.literalColor)
}

case class SymbolTerm(value: String) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(":" + value).colored(ColorProfile.literalColor)
}

case object RationalType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Rational").colored(ColorProfile.typeColor)
}

// float of 32 bits or more
case object FloatType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Float").colored(ColorProfile.typeColor)
}

case object StringType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("String").colored(ColorProfile.typeColor)
}

case object SymbolType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Symbol").colored(ColorProfile.typeColor)
}

case class AnyType(level: Term) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Any").colored(ColorProfile.typeColor)
}

val AnyType0 = AnyType(Level0)

case object NothingType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Nothing").colored(ColorProfile.typeColor)
}

case class LiteralType(literal: IntegerTerm | SymbolTerm | StringTerm | RationalTerm) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(literal.toString).colored(ColorProfile.typeColor)
}

case class ArgTerm(pattern: Term, ty: Term, default: Option[Term] = None, vararg: Boolean = false) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val patternDoc = pattern.toDoc
    val tyDoc = ty.toDoc
    val defaultDoc = default.map(_.toDoc).getOrElse(Doc.empty)
    val varargDoc = if (vararg) Doc.text("...") else Doc.empty
    Doc.wrapperlist(Docs.`(`, Docs.`)`, Docs.`:`)(patternDoc <+> tyDoc <+> defaultDoc <+> varargDoc)
  }
}

object TelescopeTerm {
  def from(x: ArgTerm*): VisibleTelescopeTerm = VisibleTelescopeTerm(x.toVector)
}

sealed trait TelescopeTerm extends Term

case class VisibleTelescopeTerm(args: Vector[ArgTerm], implicitly: Boolean = false) extends TelescopeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Telescope")
}

case class InvisibleTelescopeTerm() extends TelescopeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("InvisibleTelescopeTerm")
}

case class ScopeId(id: VarId)

object ScopeId {
  def generate: ScopeId = ScopeId(VarId.generate)
}

case class Function(ty: FunctionType, body: Term, scope: Option[ScopeId] = None, meta: OptionTermMeta = None) extends TermWithMeta {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val tyDoc = ty.toDoc
    val bodyDoc = body.toDoc
    Doc.wrapperlist(Docs.`(`, Docs.`)`, Docs.`->`)(tyDoc <+> bodyDoc)
  }
}

case class MatchingClause() {

}

case class Matching(scope: ScopeId, ty: FunctionType, clauses: Vector[MatchingClause], meta: OptionTermMeta = None) extends TermWithMeta {
  require(clauses.nonEmpty, "Matching should have at least one clause")

  override def toDoc(implicit options: PrettierOptions): Doc = toString // TODO
}

// Note that effect and result can use variables from telescope
case class FunctionType(telescope: Vector[TelescopeTerm], resultTy: Term, effect: Term = NoEffect, restrictInScope: Vector[ScopeId] = Vector(), meta: OptionTermMeta = None) extends TermWithMeta {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val telescopeDoc = telescope.map(_.toDoc).reduce(_ <+> _)
    val effectDoc = effect.toDoc
    val resultDoc = resultTy.toDoc
    Doc.wrapperlist(Docs.`(`, Docs.`)`, Docs.`->`)(telescopeDoc <+> effectDoc <+> resultDoc)
  }
}


object FunctionType {
  def apply(telescope: Vector[TelescopeTerm], resultTy: Term, effect: Term = NoEffect, restrictInScope: Vector[ScopeId] = Vector(), meta: OptionTermMeta = None): FunctionType = {
    new FunctionType(telescope, resultTy, effect, restrictInScope, meta)
  }

  def apply(telescope: TelescopeTerm, resultTy: Term): FunctionType = {
    new FunctionType(Vector(telescope), resultTy)
  }
}

def TyToty: FunctionType = {
  val ty = LocalVar.generate("x", Type0)
  FunctionType(TelescopeTerm.from(ArgTerm(ty, Type0)), ty)
}


case class ObjectClauseValueTerm(key: Term, value: Term) {
  def toDoc(implicit options: PrettierOptions): Doc = group(key <+> Doc.text("=") <+> value)
}

case class ObjectTerm(clauses: Vector[ObjectClauseValueTerm]) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc =
    Doc.wrapperlist(Docs.`{`, Docs.`}`, ",")(clauses.map(_.toDoc): _*)
}


// exactFields is a hint: subtype relationship should not include different number of fields. Otherwise, throw a warning (only warning no error)
case class ObjectType(fieldTypes: Vector[ObjectClauseValueTerm], exactFields: Boolean = false) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc =
    Doc.wrapperlist("Object" </> Docs.`{`, Docs.`}`, ",")(fieldTypes.map(_.toDoc): _*)
}

case object ListF extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = "List"
}

object ListType {
  def apply(ty: Term): Term = FCallTerm.call(ListF, ty)

  def unapply(ty: Term): Option[Term] = ty match {
    case FCallTerm.call(ListF, x) => Some(x)
    case _ => None
  }
}

case class Union(xs: Vector[Term]) extends Term {
  require(xs.nonEmpty)

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`(`, Docs.`)`, " | ")(xs: _*)
}


private inline def flatList[T <: Term](inline constructor: Vector[Term] => T, inline unapply: Term => Option[Vector[Term]], inline post: Vector[Term] => Vector[Term] = x => x)(inline xs: Vector[Term]) = {
  val flattened = post(xs.flatMap { item =>
    unapply(item).getOrElse(Vector(item))
  })
  constructor(flattened)
}

object Union {
  //def apply(xs: Vector[Term]): OrType = flatList[OrType]((x => new OrType(x)), { case OrType(x) => Some(x); case _ => None }, _.distinct)(xs)
  def apply(xs: Vector[Term]): Term = {
    val flattened = xs.flatMap {
      case Union(ys) => ys
      case x => Vector(x)
    }.distinct.filter(_ != NothingType)
    if (flattened.size == 1) return flattened.head
    if (flattened.nonEmpty) new Union(flattened) else NothingType
  }
}

case class Intersection(xs: Vector[Term]) extends Term {
  require(xs.nonEmpty)

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`(`, Docs.`)`, " & ")(xs: _*)
}

object Intersection {
  //def apply(xs: Vector[Term]): AndType = flatList[AndType]((x => new AndType(x)), { case AndType(x) => Some(x); case _ => None })(xs)
  def apply(xs: Vector[Term]): Term = {
    val flattened = xs.flatMap {
      case Intersection(ys) => ys
      case x => Vector(x)
    }.distinct
    if (flattened.size == 1) return flattened.head
    new Intersection(flattened)
  }
}

sealed trait EffectTerm extends Term

case class EffectUnion(xs: Vector[Term]) extends EffectTerm {
  require(xs.nonEmpty)

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`[`, Docs.`]`, ",")(xs: _*)
}

object EffectUnion {
  // do flatten
  def apply(xs: Vector[Term]): EffectTerm = {
    val flattened = xs.flatMap {
      case EffectUnion(ys) => ys
      case x => Vector(x)
    }.filter {
      case NoEffect => false
      case _ => true
    }
    val distinct = flattened.distinct
    if (distinct.nonEmpty) new EffectUnion(distinct) else NoEffect
  }
}

case object NoEffect extends EffectTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("NoEffect")
}

// may raise an exception
case object ExceptionEffect extends EffectTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("PartialEffect")
}

// may not terminate
case object DivergeEffect extends EffectTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("DivergeEffect")
}

// whatever IO: console, file, network, ...
case object IOEffect extends EffectTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("IOEffect")
}

case object STEffect extends EffectTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("STEffect")
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

sealed trait MaybeVarCall extends MaybeCallTerm {
  def varId: VarId

  def id: Id
}

case class LocalVar(id: Id, ty: Term, varId: VarId, meta: OptionTermMeta = None) extends MaybeVarCall {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(id)
}

object LocalVar {
  def generate(id: Id, ty: Term): LocalVar = LocalVar(id, ty, VarId.generate)
}

case class ToplevelVarCall(module: QualifiedIDString, id: Id, ty: Term, varId: VarId, meta: OptionTermMeta = None) extends MaybeVarCall {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(module.mkString(".") + "." + id)
}

case class ErrorTerm(val error: TyckError) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(error.message)
}

case class MetaTerm(description: String, id: VarId, ty: Term, effect: Option[Term] = None, meta: OptionTermMeta = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("MetaTerm#" + id)
}

object MetaTerm {
  def generate(description: String, ty: Term): MetaTerm = MetaTerm(description, VarId.generate, ty)
}

sealed trait StmtTerm {

}

case class NonlocalOrLocalReturn(scope: ScopeId, value: Term, meta: OptionTermMeta = None) extends StmtTerm {

}

case class BuiltinTerm(builtin: Builtin, meta: OptionTermMeta = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = builtin.toDoc
}


// TODO: tuple?
val UnitType = ObjectType(Vector.empty)