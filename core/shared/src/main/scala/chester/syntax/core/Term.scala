// TODO: More correctly implement toDoc
package chester.syntax.core

import chester.doc.*
import chester.doc.const.{ColorProfile, Docs}
import chester.error.*
import chester.syntax.{Builtin, Id, QualifiedIDString}
import chester.utils.doc.*
import chester.utils.{encodeString, reuse}
import spire.math.Rational
import upickle.default.*
import chester.utils.impls.*

import java.util.concurrent.atomic.AtomicInteger
import scala.language.implicitConversions

case class TermMeta(sourcePos: Option[SourcePos])derives ReadWriter

type OptionTermMeta = Option[TermMeta]

sealed trait TermWithMeta extends Term with WithPos derives ReadWriter {
  def meta: OptionTermMeta

  def sourcePos: Option[SourcePos] = meta.flatMap(_.sourcePos)
}

/** CallTerm has meta to trace runtime errors and debug */
sealed trait MaybeCallTerm extends TermWithMeta derives ReadWriter {
  override def whnf: Boolean = false
}

case class CallingArg(value: Term, name: Option[Id] = None, vararg: Boolean = false) extends ToDoc derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    if (name.isEmpty && !vararg) return value.toDoc
    if (name.isEmpty && vararg) return group(value.toDoc <> Docs.`...`)
    val nameDoc = name.get
    val valueDoc = value.toDoc
    val varargDoc = if (vararg) Docs.`...` else Doc.empty
    group(nameDoc <+> valueDoc <+> varargDoc)
  }
}

case class Calling(args: Vector[CallingArg], implicitly: Boolean = false) extends ToDoc derives ReadWriter {
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

sealed trait Pat extends ToDoc derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = toString

  def descent(patOp: Pat => Pat, termOp: Term => Term): Pat = this

  def thisOr(x: Pat): this.type = reuse(this, x.asInstanceOf[this.type])
}

case class Bind(bind: LocalVar, ty: Term) extends Pat {
  override def descent(patOp: Pat => Pat, termOp: Term => Term): Pat = thisOr(copy(ty = termOp(ty)))
}

object Bind {
  def from(bind: LocalVar): Bind = Bind(bind, bind.ty)
}

sealed trait Term extends ToDoc derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = toString

  def whnf: Boolean = true

  protected final inline def thisOr(inline x: Term): this.type = reuse(this, x.asInstanceOf[this.type])

  def descent(f: Term => Term): Term = this

  final def descentRecursive(f: Term => Term): Term = thisOr {
    f(descent(_.descentRecursive(f)))
  }

  def doElevate(level: IntegerTerm): Term = descent(_.doElevate(level))

  final def elevate(level: IntegerTerm): Term = {
    require(level.value >= 0)
    if (level.value == 0) this else doElevate(level)
  }

  final def substitute(from: Term & HasVarId, to: Term): Term = {
    if (from == to) return this
    descentRecursive {
      case x: HasVarId if x.varId == from.varId => to
      case x => x
    }
  }
}

case class ListTerm(terms: Vector[Term]) extends Term derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`[`, Docs.`]`, ",")(terms *)

  override def descent(f: Term => Term): Term = thisOr(ListTerm(terms.map(f)))
}

sealed trait Sort extends Term derives ReadWriter {
  def level: Term
}

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

val Levelω = Level(IntegerTerm(-11111)) // TODO

val Level0 = Level(IntegerTerm(0))

val Type0 = Type(Level0)

// Referencing Setω in Agda
val Typeω = Type(Levelω)

case class Prop(level: Term) extends Sort with Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("Prop" <> Docs.`(`, Docs.`)`)(level)
}

sealed trait LiteralTerm extends Term derives ReadWriter

case class IntTerm(value: Int) extends LiteralTerm derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString, ColorProfile.literalColor)
}

case class IntegerTerm(value: BigInt) extends LiteralTerm derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString, ColorProfile.literalColor)
}

type AbstractIntTerm = IntegerTerm | IntTerm

object AbstractIntTerm {
  def from(value: BigInt): AbstractIntTerm = if (value.isValidInt) IntTerm(value.toInt) else IntegerTerm(value)
}

object NaturalTerm {
  def apply(value: BigInt): AbstractIntTerm = AbstractIntTerm.from(value)
}

sealed trait TypeTerm extends Term derives ReadWriter

case object IntegerType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Integer", ColorProfile.typeColor)
}

// int of 64 bits or more
case object IntType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Int", ColorProfile.typeColor)
}

// unsigned int of 64 bits or more
case object UIntType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("UInt", ColorProfile.typeColor)
}

case object NaturalType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Natural", ColorProfile.typeColor)
}

case class RationalTerm(value: Rational) extends LiteralTerm derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString, ColorProfile.literalColor)
}

case class StringTerm(value: String) extends LiteralTerm derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("\"" + encodeString(value) + "\"", ColorProfile.literalColor)
}

case class SymbolTerm(value: String) extends Term derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(":" + value, ColorProfile.literalColor)
}

case object RationalType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Rational", ColorProfile.typeColor)
}

// float of 32 bits or more
case object FloatType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Float", ColorProfile.typeColor)
}

case object StringType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("String", ColorProfile.typeColor)
}

case object SymbolType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Symbol", ColorProfile.typeColor)
}

case class AnyType(level: Term) extends TypeTerm derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Any", ColorProfile.typeColor)
}

val AnyType0 = AnyType(Level0)

case object NothingType extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Nothing", ColorProfile.typeColor)
}

implicit val rwUnionHere: ReadWriter[IntegerTerm | SymbolTerm | StringTerm | RationalTerm] = union4RW[IntegerTerm, SymbolTerm, StringTerm, RationalTerm]

case class LiteralType(literal: IntegerTerm | SymbolTerm | StringTerm | RationalTerm) extends TypeTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(literal.toString, ColorProfile.typeColor)
}

case class ArgTerm(bind: LocalVar, ty: Term, default: Option[Term] = None, vararg: Boolean = false) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val patternDoc = bind.toDoc
    val tyDoc = ty.toDoc
    val defaultDoc = default.map(_.toDoc).getOrElse(Doc.empty)
    val varargDoc = if (vararg) Doc.text("...") else Doc.empty
    Doc.wrapperlist(Docs.`(`, Docs.`)`, Docs.`:`)(patternDoc <+> tyDoc <+> defaultDoc <+> varargDoc)
  }
}

object ArgTerm {
  def from(bind: LocalVar): ArgTerm = ArgTerm(bind, bind.ty)
}

object TelescopeTerm {
  def from(x: ArgTerm*): TelescopeTerm = TelescopeTerm(x.toVector)
}

case class TelescopeTerm(args: Vector[ArgTerm], implicitly: Boolean = false) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val argsDoc = args.map(_.toDoc).reduce(_ <+> _)
    Docs.`(` <> argsDoc <> Docs.`)`
  }
}

case class ScopeId(id: VarId)derives ReadWriter

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

case class MatchingClause()derives ReadWriter {

}

case class Matching(scope: ScopeId, ty: FunctionType, clauses: Vector[MatchingClause], meta: OptionTermMeta = None) extends TermWithMeta {
  require(clauses.nonEmpty, "Matching should have at least one clause")

  override def toDoc(implicit options: PrettierOptions): Doc = toString // TODO
}

// Note that effect and result can use variables from telescope
case class FunctionType(telescope: Vector[TelescopeTerm], resultTy: Term, effect: Effects = NoEffect, restrictInScope: Vector[ScopeId] = Vector(), meta: OptionTermMeta = None) extends TermWithMeta {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val telescopeDoc = if(telescope.isEmpty) Doc.empty else telescope.map(_.toDoc).reduce(_ <+> _)
    val effectDoc = effect.toDoc
    val resultDoc = resultTy.toDoc
    Doc.wrapperlist(Docs.`(`, Docs.`)`, Docs.`->`)(telescopeDoc <+> effectDoc <+> resultDoc)
  }
}


object FunctionType {
  def apply(telescope: Vector[TelescopeTerm], resultTy: Term, effect: Effects = NoEffect, restrictInScope: Vector[ScopeId] = Vector(), meta: OptionTermMeta = None): FunctionType = {
    new FunctionType(telescope, resultTy, effect, restrictInScope, meta)
  }

  def apply(telescope: TelescopeTerm, resultTy: Term): FunctionType = {
    new FunctionType(Vector(telescope), resultTy)
  }
}

def TyToty: FunctionType = {
  val ty = LocalVar.generate("x", Type0)
  FunctionType(TelescopeTerm.from(ArgTerm.from(ty)), ty)
}


case class ObjectClauseValueTerm(key: Term, value: Term)derives ReadWriter {
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
  def from(xs: Vector[Term]): Term = {
    val flattened = xs.flatMap {
      case Union(ys) => ys
      case x => Vector(x)
    }.distinct.filter(_ != NothingType)
    if (flattened.size == 1) return flattened.head
    if (flattened.nonEmpty) new Union(flattened) else NothingType
  }
}

case class Intersection(xs: Vector[Term]) extends Term derives ReadWriter {
  require(xs.nonEmpty)

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`(`, Docs.`)`, " & ")(xs: _*)
}

object Intersection {
  //def apply(xs: Vector[Term]): AndType = flatList[AndType]((x => new AndType(x)), { case AndType(x) => Some(x); case _ => None })(xs)
  def from(xs: Vector[Term]): Term = {
    val flattened = xs.flatMap {
      case Intersection(ys) => ys
      case x => Vector(x)
    }.distinct
    if (flattened.size == 1) return flattened.head
    new Intersection(flattened)
  }
}

sealed trait EffectTerm extends Term derives ReadWriter

case class NamedEffect(name: Vector[LocalVar], effect: Term) extends ToDoc derives ReadWriter {
  require(name.nonEmpty)

  override def toDoc(implicit options: PrettierOptions): Doc = {
    val nameDoc = name.map(_.toDoc).reduce(_ <+> _)
    val effectDoc = effect.toDoc
    Doc.wrapperlist(Docs.`(`, Docs.`)`, Docs.`->`)(nameDoc <+> effectDoc)
  }

  def descent(f: Term => Term): NamedEffect = copy(effect = f(effect))
}

implicit val rwNamedEffect: ReadWriter[Effects] = readwriter[Vector[NamedEffect]].bimap(_.xs, Effects(_))

// TODO: maybe a hashmap?
case class Effects private[syntax](xs: Vector[NamedEffect]) extends AnyVal with ToDoc {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`[`, Docs.`]`, ",")(xs.map(_.toDoc): _*)

  def descent(f: Term => Term): Effects = copy(xs = xs.map(_.descent(f)))

  def add(one: NamedEffect): Effects = {
    val found = xs.indexWhere { case NamedEffect(name, effect) => effect == one.effect }
    if (found != -1) {
      val NamedEffect(name, effect) = xs(found)
      copy(xs = xs.updated(found, NamedEffect(name ++ one.name, effect)))
    } else {
      copy(xs = xs :+ one)
    }
  }

  def lookup(effect: Term): Option[Vector[LocalVar]] = {
    var results = xs.filter(_.effect == effect)
    assert(results.length <= 1)
    results.headOption.map(_.name)
  }

  def merge(other: Effects): Effects = {
    var result = this
    for (one <- other.xs) {
      result = result.add(one)
    }
    result
  }
}

object Effects {
  val Empty: Effects = Effects(Vector.empty)

  def merge(xs: Seq[Effects]): Effects = {
    require(xs.nonEmpty)
    xs.reduce(_.merge(_))
  }

  def unchecked(xs: Seq[NamedEffect]): Effects = Effects(xs.toVector)
}

val NoEffect = Effects.Empty

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

sealed trait MaybeVarCall extends MaybeCallTerm derives ReadWriter {
  def varId: VarId

  def id: Id
}

case class LocalVar(id: Id, ty: Term, varId: VarId, meta: OptionTermMeta = None) extends MaybeVarCall with HasVarId {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(id)
}

object LocalVar {
  def generate(id: Id, ty: Term): LocalVar = LocalVar(id, ty, VarId.generate)
}

case class ToplevelVarCall(module: QualifiedIDString, id: Id, ty: Term, varId: VarId, meta: OptionTermMeta = None) extends MaybeVarCall {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(module.mkString(".") + "." + id)
}

case class ErrorTerm(val error: TyckError) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = error.toDoc
}

case class MetaTerm(description: String, id: VarId, ty: Term, effect: Effects = NoEffect, meta: OptionTermMeta = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("MetaTerm#" + id)
}

object MetaTerm {
  def generate(description: String, ty: Term): MetaTerm = MetaTerm(description, VarId.generate, ty)
}

sealed trait StmtTerm derives ReadWriter {

}

case class NonlocalOrLocalReturn(scope: ScopeId, value: Term, meta: OptionTermMeta = None) extends StmtTerm {

}

case class BuiltinTerm(builtin: Builtin, meta: OptionTermMeta = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = builtin.toDoc
}


// TODO: tuple?
val UnitType = ObjectType(Vector.empty)

sealed trait ErasedType extends TypeTerm derives ReadWriter

case object ErasedInteger extends ErasedType

case object ErasedString extends ErasedType

case object ErasedList extends ErasedType

case object ErasedTuple extends ErasedType

case object ErasedObject extends ErasedType

case object ErasedErasedType extends ErasedType