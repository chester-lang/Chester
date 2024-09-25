// TODO: More correctly implement toDoc
package chester.syntax.core

import cats.data.*
import chester.doc.*
import chester.doc.const.{ColorProfile, Docs}
import chester.error.*
import chester.syntax.{Name, QualifiedIDString}
import chester.utils.doc.*
import chester.utils.impls.*
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.upickle.given
import chester.utils.*
import spire.math.Rational
import upickle.default.*

import scala.collection.immutable.HashMap
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

case class CallingArgTerm(value: Term, name: Option[Name] = None, vararg: Boolean = false) extends ToDoc derives ReadWriter {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    if (name.isEmpty && !vararg) return value.toDoc
    if (name.isEmpty && vararg) return group(value.toDoc <> Docs.`...`)
    val nameDoc = name.get
    val valueDoc = value.toDoc
    val varargDoc = if (vararg) Docs.`...` else Doc.empty
    group(nameDoc <+> valueDoc <+> varargDoc)
  }

  def descent(f: Term => Term): CallingArgTerm = (copy(value = f(value)))
}

case class Calling(args: Vector[CallingArgTerm], implicitly: Boolean = false) extends ToDoc derives ReadWriter {
  def toDoc(implicit options: PrettierOptions): Doc = {
    val argsDoc = args.map(_.toDoc).reduce(_ <+> _)
    if (implicitly) Docs.`(` <> argsDoc <> Docs.`)` else argsDoc
  }

  def descent(f: Term => Term): Calling = copy(args = args.map(_.descent(f)))
}

case class FCallTerm(f: Term, args: Vector[Calling], meta: OptionTermMeta = None) extends MaybeCallTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val fDoc = f.toDoc
    val argsDoc = args.map(_.toDoc).reduce(_ <+> _)
    group(fDoc <+> argsDoc)
  }

  override def whnf: Boolean = f match {
    case _ => false
  }

  override def descent(op: Term => Term): FCallTerm = thisOr(copy(f = f.descent(op), args = args.map(_.descent(op))))
}

object FCallTerm {
  object call {
    def apply(f: Term, args: Term*): FCallTerm = FCallTerm(f, Vector(Calling(args.toVector.map(CallingArgTerm(_)))))

    def unapplySeq(f: Term): Option[Seq[Term]] = f match {
      case FCallTerm(f, Seq(Calling(args, false)), _) if args.forall {
        case CallingArgTerm(_, None, false) => true
        case _ => false
      } => Some(f +: args.map {
        case CallingArgTerm(value, _, _) => value
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

  def descent(f: Term => Term): Term

  final def descentRecursive(f: Term => Term): Term = thisOr {
    f(descent(_.descentRecursive(f)))
  }
  
  def inspect(f: Term => Unit): Unit = {
    descent { x =>
      f(x)
      x
    }
    ()
  }

  def foreach(f: Term => Unit): Unit = {
    inspect(_.foreach(f))
    f(this)
  }

  def mapFlatten[B](f: Term => Seq[B]): Vector[B] = {
    var result = Vector.empty[B]
    foreach { term =>
      result ++= f(term)
    }
    result
  }

  def doElevate(level: IntegerTerm): Term = descent(_.doElevate(level))

  final def elevate(level: IntegerTerm): Term = {
    require(level.value >= 0)
    if (level.value == 0) this else doElevate(level)
  }

  // TODO: optimize
  final def substitute[A <: Term & HasUniqId](mapping: Seq[(A, Term)]): Term = {
    mapping.foldLeft(this) { case (acc, (from, to)) => acc.substitute(from, to) }
  }

  final def substitute(from: Term & HasUniqId, to: Term): Term = {
    if (from == to) return this
    if (to match {
      case to: HasUniqId => from.uniqId == to.uniqId
      case _ => false
    }) return this
    descentRecursive {
      case x: HasUniqId if x.uniqId == from.uniqId => to
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

sealed trait Level extends Term derives ReadWriter

case class LevelFinite(n: Term) extends Level {
  override def toDoc(implicit options: PrettierOptions): Doc =
    Doc.text("Level(") <> n.toDoc <> Doc.text(")")

  override def descent(f: Term => Term): LevelFinite =
    thisOr(LevelFinite(f(n)))
}

case object Levelω extends Level {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Levelω")

  override def descent(f: Term => Term): Levelω.type = this
}

// Define Level0 using LevelFinite
val Level0 = LevelFinite(IntegerTerm(0))

val Type0 = Type(Level0)

// Referencing Setω in Agda
val Typeω = Type(Levelω)

case class Prop(level: Term) extends Sort with Term {
  override def descent(f: Term => Term): Term = thisOr(Prop(f(level)))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("Prop" <> Docs.`(`, Docs.`)`)(level)
}

// fibrant types
case class FType(level: Term) extends Sort with Term {
  override def descent(f: Term => Term): Term = thisOr(FType(f(level)))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("FType" <> Docs.`(`, Docs.`)`)(level)
}

sealed trait LiteralTerm extends Term derives ReadWriter

case class IntTerm(value: Int) extends LiteralTerm derives ReadWriter {
  override def descent(f: Term => Term): IntTerm = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString, ColorProfile.literalColor)
}

case class IntegerTerm(value: BigInt) extends LiteralTerm derives ReadWriter {
  override def descent(f: Term => Term): IntegerTerm = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString, ColorProfile.literalColor)
}

type AbstractIntTerm = IntegerTerm | IntTerm

object AbstractIntTerm {
  def from(value: BigInt): AbstractIntTerm = if (value.isValidInt) IntTerm(value.toInt) else IntegerTerm(value)

  def unapply(term: Term): Option[BigInt] = term match {
    case IntTerm(value) => Some(BigInt(value))
    case IntegerTerm(value) => Some(value)
    case _ => None
  }
}

object NaturalTerm {
  def apply(value: BigInt): AbstractIntTerm = AbstractIntTerm.from(value)
}

sealed trait TypeTerm extends Term derives ReadWriter

case object IntegerType extends TypeTerm {
  override def descent(f: Term => Term): IntegerType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Integer", ColorProfile.typeColor)
}

// int of 64 bits or more
case object IntType extends TypeTerm {
  override def descent(f: Term => Term): IntType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Int", ColorProfile.typeColor)
}

// unsigned int of 64 bits or more
case object UIntType extends TypeTerm {
  override def descent(f: Term => Term): UIntType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("UInt", ColorProfile.typeColor)
}

case object NaturalType extends TypeTerm {
  override def descent(f: Term => Term): NaturalType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Natural", ColorProfile.typeColor)
}

case class RationalTerm(value: Rational) extends LiteralTerm derives ReadWriter {
  override def descent(f: Term => Term): RationalTerm = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString, ColorProfile.literalColor)
}

case class StringTerm(value: String) extends LiteralTerm derives ReadWriter {
  override def descent(f: Term => Term): StringTerm = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("\"" + encodeString(value) + "\"", ColorProfile.literalColor)
}

case class SymbolTerm(value: String) extends Term derives ReadWriter {
  override def descent(f: Term => Term): SymbolTerm = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(":" + value, ColorProfile.literalColor)
}

case object RationalType extends TypeTerm {
  override def descent(f: Term => Term): RationalType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Rational", ColorProfile.typeColor)
}

// float of 32 bits or more
case object FloatType extends TypeTerm {
  override def descent(f: Term => Term): FloatType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Float", ColorProfile.typeColor)
}

case object StringType extends TypeTerm {
  override def descent(f: Term => Term): StringType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("String", ColorProfile.typeColor)
}

case object SymbolType extends TypeTerm {
  override def descent(f: Term => Term): SymbolType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Symbol", ColorProfile.typeColor)
}

case class AnyType(level: Term) extends TypeTerm derives ReadWriter {
  override def descent(f: Term => Term): AnyType = thisOr(copy(level = f(level)))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Any", ColorProfile.typeColor)
}

def AnyType0 = AnyType(Level0)

case object NothingType extends TypeTerm {
  override def descent(f: Term => Term): NothingType.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Nothing", ColorProfile.typeColor)
}

implicit val rwUnionHere: ReadWriter[IntegerTerm | SymbolTerm | StringTerm | RationalTerm] = union4RW[IntegerTerm, SymbolTerm, StringTerm, RationalTerm]

case class LiteralType(literal: IntegerTerm | SymbolTerm | StringTerm | RationalTerm) extends TypeTerm {
  override def descent(f: Term => Term): LiteralType = copy(literal = literal.descent(f).asInstanceOf[IntegerTerm | SymbolTerm | StringTerm | RationalTerm])

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(literal.toString, ColorProfile.typeColor)
}

case class ArgTerm(bind: LocalVar, ty: Term, default: Option[Term] = None, vararg: Boolean = false) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val varargDoc = if (vararg) Docs.`...` else Doc.empty
    val defaultDoc = default.map(d => Docs.`=` <+> d.toDoc).getOrElse(Doc.empty)
    bind.toDoc <> varargDoc <> Docs.`:` <+> ty.toDoc <> defaultDoc
  }

  def name: Name = bind.id

  override def descent(f: Term => Term): ArgTerm = thisOr(copy(ty = f(ty), default = default.map(f)))
}

object ArgTerm {
  def from(bind: LocalVar): ArgTerm = ArgTerm(bind, bind.ty)
}

object TelescopeTerm {
  def from(x: ArgTerm*): TelescopeTerm = TelescopeTerm(x.toVector)
}

case class TelescopeTerm(args: Vector[ArgTerm], implicitly: Boolean = false) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val argsDoc = args.map(_.toDoc).reduceLeftOption(_ <+> _).getOrElse(Doc.empty)
    if (implicitly) {
      Docs.`[` <> argsDoc <> Docs.`]`
    } else {
      Docs.`(` <> argsDoc <> Docs.`)`
    }
  }

  override def descent(f: Term => Term): TelescopeTerm = thisOr(copy(args = args.map(_.descent(f))))
}

case class ScopeId(id: UniqId)derives ReadWriter

object ScopeId {
  def generate: ScopeId = ScopeId(UniqId.generate)
}

case class Function(
  ty: FunctionType,
  body: Term,
  scope: Option[ScopeId] = None,
  meta: OptionTermMeta = None
) extends TermWithMeta {

  override def toDoc(implicit options: PrettierOptions): Doc = {
    val paramsDoc = ty.telescope.map(_.toDoc).reduceLeftOption(_ <+> _).getOrElse(Doc.empty)
    val returnTypeDoc = Docs.`:` <+> ty.resultTy.toDoc
    val effectsDoc = if (ty.effects.nonEmpty) {
      Docs.`/` <+> ty.effects.toDoc
    } else {
      Doc.empty
    }
    val bodyDoc = body.toDoc
    group(paramsDoc <> returnTypeDoc <+> Docs.`=>` <+> bodyDoc <> effectsDoc)
  }

  override def descent(f: Term => Term): Term = thisOr(copy(
    ty = ty.descent(f),
    body = f(body)
  ))
}

case class MatchingClause()derives ReadWriter {

}

case class Matching(scope: ScopeId, ty: FunctionType, clauses: NonEmptyVector[MatchingClause], meta: OptionTermMeta = None) extends TermWithMeta {
  override def toDoc(implicit options: PrettierOptions): Doc = toString // TODO

  override def descent(f: Term => Term): Term = thisOr(copy(ty = ty.descent(f)))
}

// Note that effect and result can use variables from telescope
case class FunctionType(telescope: Vector[TelescopeTerm], resultTy: Term, effects: Effects = NoEffect, restrictInScope: Vector[ScopeId] = Vector(), meta: OptionTermMeta = None) extends TermWithMeta {
  override def toDoc(implicit options: PrettierOptions): Doc = {
    val telescopeDoc = telescope.map(_.toDoc).reduceLeftOption(_ <+> _).getOrElse(Doc.empty)
    val effectsDoc = if (effects.nonEmpty) {
      Docs.`/` <+> effects.toDoc
    } else {
      Doc.empty
    }
    group(telescopeDoc <+> Docs.`->` <+> resultTy.toDoc <> effectsDoc)
  }

  override def descent(f: Term => Term): FunctionType = thisOr(copy(telescope = telescope.map(_.descent(f)), effects = effects.descent(f), resultTy = f(resultTy)))
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

  def descent(f: Term => Term): ObjectClauseValueTerm = (copy(key = f(key), value = f(value)))
}

case class ObjectTerm(clauses: Vector[ObjectClauseValueTerm]) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc =
    Doc.wrapperlist(Docs.`{`, Docs.`}`, ",")(clauses.map(_.toDoc) *)

  override def descent(f: Term => Term): ObjectTerm = thisOr(copy(clauses = clauses.map(_.descent(f))))
}


// exactFields is a hint: subtype relationship should not include different number of fields. Otherwise, throw a warning (only warning no error)
case class ObjectType(fieldTypes: Vector[ObjectClauseValueTerm], exactFields: Boolean = false) extends Term {
  override def descent(f: Term => Term): ObjectType = thisOr(copy(fieldTypes = fieldTypes.map(_.descent(f))))

  override def toDoc(implicit options: PrettierOptions): Doc =
    Doc.wrapperlist("Object" </> Docs.`{`, Docs.`}`, ",")(fieldTypes.map(_.toDoc) *)
}

sealed trait Builtin extends Term derives ReadWriter

case object ListF extends Builtin {
  override def descent(f: Term => Term): ListF.type = this

  override def toDoc(implicit options: PrettierOptions): Doc = "List"
}

sealed trait Constructed extends Term derives ReadWriter

case class ListType(ty: Term) extends Constructed with TypeTerm {
  override def descent(f: Term => Term): ListType = thisOr(copy(ty = f(ty)))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("List") <> Docs.`(` <> ty <> Docs.`)`
}

case class Union(xs: NonEmptyVector[Term]) extends TypeTerm {
  override def descent(f: Term => Term): Union = thisOr(copy(xs = xs.map(f)))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`(`, Docs.`)`, " | ")(xs *)
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
    if (flattened.nonEmpty) new Union(flattened.assumeNonEmpty) else NothingType
  }
}

case class Intersection(xs: NonEmptyVector[Term]) extends TypeTerm derives ReadWriter {
  override def descent(f: Term => Term): Intersection = thisOr(copy(xs = xs.map(f)))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist(Docs.`(`, Docs.`)`, " & ")(xs *)
}

object Intersection {
  //def apply(xs: Vector[Term]): AndType = flatList[AndType]((x => new AndType(x)), { case AndType(x) => Some(x); case _ => None })(xs)
  def from(xs: Vector[Term]): Term = {
    val flattened = xs.flatMap {
      case Intersection(ys) => ys
      case x => Vector(x)
    }.distinct
    if (flattened.size == 1) return flattened.head
    new Intersection(flattened.assumeNonEmpty)
  }
}

/** Effect needs to have reasonable equals and hashcode for simple comparison, whereas they are not requirements for other Terms */
sealed trait Effect extends ToDoc derives ReadWriter {
  def name: String
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(name)
}

implicit val rwEffects: ReadWriter[Effects] = readwriter[Map[Effect, Vector[LocalVar]]].bimap(_.effects, Effects(_))

case class Effects private[syntax](effects: Map[Effect, Vector[LocalVar]]) extends AnyVal with ToDoc {
  override def toDoc(implicit options: PrettierOptions): Doc =
    effects.keys.map(_.toDoc).reduceLeftOption((a, b) => a <> Docs.`,` <+> b).getOrElse(Doc.empty)

  def descentEffects(f: Effect => Effect): Effects =
    Effects(effects.map { case (effect, names) => f(effect) -> names })

  def descent(f: Term => Term): Effects = this // TODO

  def add(effect: Effect, name: LocalVar): Effects =
    Effects(effects.updated(effect, effects.getOrElse(effect, Vector.empty) :+ name))

  def lookup(effect: Effect): Option[Vector[LocalVar]] = effects.get(effect)

  def lookupPair(effect: Effect): Option[(Effect, Vector[LocalVar])] = effects.get(effect).map(effect -> _)

  def mapOnVars(f: (Effect, Vector[LocalVar]) => Vector[LocalVar]): Effects =
    Effects(effects.map { case (effect, names) => effect -> f(effect, names) })

  def getEffects: Seq[Effect] = effects.keys.toSeq

  def contains(effect: Effect): Boolean = effects.contains(effect)

  def containAll(effects: Seq[Effect]): Boolean = effects.forall(this.contains)

  def merge(other: Effects): Effects =
    Effects(other.effects.foldLeft(effects) { case (acc, (effect, names)) =>
      acc.updated(effect, acc.getOrElse(effect, Vector.empty) ++ names)
    })

  def isEmpty: Boolean = (effects eq NoEffect.effects) || effects.isEmpty

  def nonEmpty: Boolean = (effects ne NoEffect.effects) || effects.nonEmpty
}

object Effects {
  val Empty: Effects = Effects(HashMap.empty)

  def merge(xs: NonEmptySeq[Effects]): Effects = {
    xs.reduce(_.merge(_))
  }

  def unchecked(xs: Map[Effect, Vector[LocalVar]]): Effects = Effects(xs)
}

val NoEffect = Effects.Empty

// may raise an exception
case object ExceptionEffect extends Effect {
  val name = "Exception"
}

// may not terminate
case object DivergeEffect extends Effect {
  val name = "Diverge"
}

// whatever IO: console, file, network, ...
case object IOEffect extends Effect {
  val name = "IO"
}

case object STEffect extends Effect {
  val name = "ST"
}

sealed trait MaybeVarCall extends MaybeCallTerm derives ReadWriter {
  def uniqId: UniqId

  def id: Name
}

case class LocalVar(id: Name, ty: Term, uniqId: UniqId, meta: OptionTermMeta = None) extends MaybeVarCall with HasUniqId {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(id.toString)

  override def descent(f: Term => Term): LocalVar = thisOr(copy(ty = f(ty)))
}

object LocalVar {
  def generate(id: Name, ty: Term): LocalVar = LocalVar(id, ty, UniqId.generate)
}

case class ToplevelVarCall(module: QualifiedIDString, id: Name, ty: Term, uniqId: UniqId, meta: OptionTermMeta = None) extends MaybeVarCall {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(module.mkString(".") + "." + id)

  override def descent(f: Term => Term): ToplevelVarCall = thisOr(copy(ty = f(ty)))
}

case class ErrorTerm(val error: TyckError) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = error.toDoc

  override def descent(f: Term => Term): ErrorTerm = this
}

def ErrorType(error: TyckError): ErrorTerm = ErrorTerm(error)

case class MetaTerm(description: String, uniqId: UniqId, ty: Term, effect: Effects = NoEffect, meta: OptionTermMeta = None) extends Term with HasUniqId {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("MetaTerm#" + uniqId)

  override def descent(f: Term => Term): MetaTerm = thisOr(copy(ty = f(ty)))
}

object MetaTerm {
  def generate(description: String, ty: Term): MetaTerm = MetaTerm(description, UniqId.generate, ty)
}

sealed trait StmtTerm extends ToDoc derives ReadWriter {
  def descent(f: Term => Term): StmtTerm = ???
}

case class LetStmtTerm(name: Name, value: Term, ty: Term) extends StmtTerm {
  override def descent(f: Term => Term): StmtTerm = copy(value = f(value), ty = f(ty))
  override def toDoc(implicit options: PrettierOptions): Doc = {
    Doc.text(s"let $name: ") <> ty.toDoc <> Doc.text(s" = ") <> value.toDoc // TODO: fix this
  }
}

case class DefStmtTerm(name: Name, value: Term, ty: Term) extends StmtTerm {
  override def descent(f: Term => Term): StmtTerm = copy(value = f(value), ty = f(ty))
  override def toDoc(implicit options: PrettierOptions): Doc = {
    Doc.text(s"def $name: ") <> ty.toDoc <> Doc.text(s" = ") <> value.toDoc // TODO: fix this
  }
}

case class ExprStmtTerm(expr: Term) extends StmtTerm {
  override def descent(f: Term => Term): StmtTerm = copy(expr = f(expr))
  override def toDoc(implicit options: PrettierOptions): Doc = expr.toDoc
}

case class NonlocalOrLocalReturn(scope: ScopeId, value: Term, meta: OptionTermMeta = None) extends StmtTerm {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("return") <+> value.toDoc
}

case class BuiltinTerm(builtin: Builtin, meta: OptionTermMeta = None) extends Term {
  override def toDoc(implicit options: PrettierOptions): Doc = builtin.toDoc

  override def descent(f: Term => Term): BuiltinTerm = this
}
case class TupleType(types: Vector[Term]) extends TypeTerm {
  override def descent(f: Term => Term): TupleType = thisOr(copy(types = types.map(f)))

  override def toDoc(implicit options: PrettierOptions): Doc = {
    Doc.wrapperlist("Tuple" <> Docs.`[`, Docs.`]`, ",")(types *)
  }
}

case class TupleTerm(values: Vector[Term]) extends Term {
  override def descent(f: Term => Term): TupleTerm = thisOr(copy(values = values.map(f)))

  override def toDoc(implicit options: PrettierOptions): Doc = {
    Doc.wrapperlist(Docs.`(`, Docs.`)`, ",")(values *)
  }
}

case class BlockTerm(stmts: Vector[StmtTerm], value: Term) extends Term {
  override def descent(f: Term => Term): BlockTerm = thisOr(copy(stmts = stmts.map {
    _.descent(f)
  }, value = f(value)))

  override def toDoc(implicit options: PrettierOptions): Doc = {
    Doc.wrapperlist(Docs.`{`, Docs.`}`, ";")((stmts.map(_.toDoc) :+ value.toDoc) *)
  }
}

// TODO: tuple?
val UnitType = ObjectType(Vector.empty)
val UnitTerm = ObjectTerm(Vector.empty)

sealed trait ErasedType extends TypeTerm derives ReadWriter {
  override def descent(f: Term => Term): ErasedType = this
}

case object ErasedInteger extends ErasedType

case object ErasedString extends ErasedType

case object ErasedList extends ErasedType

case object ErasedTuple extends ErasedType

case object ErasedObject extends ErasedType

case object ErasedErasedType extends ErasedType