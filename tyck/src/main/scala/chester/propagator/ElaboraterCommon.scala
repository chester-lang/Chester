package chester.propagator

import chester.error.{TyckProblem, TypeMismatch}
import chester.resolve.{SimpleDesalt, resolveOpSeq}
import chester.syntax.Name
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.Reporter
import chester.utils.*
import chester.utils.propagator.CommonPropagator

trait ElaboraterCommon extends ProvideCtx with ElaboraterBase with CommonPropagator[Ck] {

  trait EffectsCell extends Cell[Effects] {
    def requireEffect(effect: Term)(using ck: Ck, state: StateAbility[Ck]): LocalV = {
      ???
    }
  }


  def toEffectsM(x: CellIdOr[Effects])(using state: StateAbility[Ck]): EffectsM = x match {
    case x: Effects => x
    case x: EffectsCell => Meta(x.asInstanceOf[CellId[Effects]])
  }

  def toEffectsCell(x: EffectsM)(using state: StateAbility[Ck]): CIdOf[EffectsCell] = x match {
    case x: Effects => state.addCell(FixedEffectsCell(x))
    case Meta(x) => x.asInstanceOf[CIdOf[EffectsCell]]
  }


  case class DynamicEffectsCell(effects: Map[LocalV, Term]) extends BaseMapCell[LocalV, Term] with EffectsCell with UnstableCell[Effects] with NoFill[Effects] {
    override def add(key: LocalV, value: Term): DynamicEffectsCell = {
      require(!effects.contains(key))
      copy(effects = effects + (key -> value))
    }

    override def readUnstable: Option[Effects] = Some(Effects(effects))
  }

  case class FixedEffectsCell(effects: Effects) extends EffectsCell with NoFill[Effects] {
    override def readStable: Option[Effects] = Some(effects)
  }

  def resolve(expr: Expr, localCtx: LocalCtx)(using reporter: Reporter[TyckProblem]): Expr = {
    val result = SimpleDesalt.desugarUnwrap(expr) match {
      case opseq: OpSeq => {
        val result = resolveOpSeq(reporter, localCtx.operators, opseq)
        result
      }
      case default => default
    }
    reuse(expr, result)
  }


  type Literals = Expr & (IntegerLiteral | RationalLiteral | StringLiteral | SymbolLiteral)

  case class Unify(lhs: CellId[Term], rhs: CellId[Term], cause: Expr)(using localCtx: LocalCtx) extends Propagator[Ck] {
    override val readingCells = Set(lhs, rhs)
    override val writingCells = Set(lhs, rhs)
    override val zonkingCells = Set(lhs, rhs)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      val lhs = state.readStable(this.lhs)
      val rhs = state.readStable(this.rhs)
      if (lhs.isDefined && rhs.isDefined) {
        unify(lhs.get, rhs.get, cause)
        return true
      }
      (lhs, rhs) match {
        case (Some(Meta(lhs)), _) => {
          state.addPropagator(Unify(lhs, this.rhs, cause))
          return true
        }
        case (_, Some(Meta(rhs))) => {
          state.addPropagator(Unify(this.lhs, rhs, cause))
          return true
        }
        case _ => ()
      }
      return false
    }

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val lhs = state.readStable(this.lhs)
      val rhs = state.readStable(this.rhs)
      (lhs, rhs) match {
        case (Some(lhs), Some(rhs)) if lhs == rhs => return ZonkResult.Done
        case (Some(lhs), None) => {
          state.fill(this.rhs, lhs)
          return ZonkResult.Done
        }
        case (None, Some(rhs)) => {
          state.fill(this.lhs, rhs)
          return ZonkResult.Done
        }
        case _ => return ZonkResult.Require(Vector(this.lhs, this.rhs))
      }
    }
  }

  case class UnionOf(
                      lhs: CellId[Term],
                      rhs: Vector[CellId[Term]],
                      cause: Expr,
                    )(using localCtx: LocalCtx) extends Propagator[Ck] {
    override val readingCells = Set(lhs) ++ rhs.toSet
    override val writingCells = Set(lhs)
    override val zonkingCells = Set(lhs) ++ rhs.toSet

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      val lhsValueOpt = state.readStable(lhs)
      val rhsValuesOpt = rhs.map(state.readStable)

      if (lhsValueOpt.isDefined && rhsValuesOpt.forall(_.isDefined)) {
        val lhsValue = lhsValueOpt.get
        val rhsValues = rhsValuesOpt.map(_.get)

        // Check that each rhsValue is assignable to lhsValue
        val assignable = rhsValues.forall { rhsValue =>
          unify(lhsValue, rhsValue, cause)
          true // Assuming unify reports errors internally
        }
        assignable
      } else {
        // Not all values are available yet
        false
      }
    }

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val lhsValueOpt = state.readStable(lhs)
      val rhsValuesOpt = rhs.map(state.readStable)

      val unknownRhs = rhs.zip(rhsValuesOpt).collect { case (id, None) => id }
      if (unknownRhs.nonEmpty) {
        // Wait for all rhs values to be known
        ZonkResult.Require(unknownRhs.toVector)
      } else {
        val rhsValues = rhsValuesOpt.map(_.get)

        lhsValueOpt match {
          case Some(lhsValue) =>
            // LHS is known, unify each RHS with LHS
            rhsValues.foreach { rhsValue =>
              unify(lhsValue, rhsValue, cause)
            }
            ZonkResult.Done
          case None =>
            // LHS is unknown, create UnionType from RHS values and set LHS
            val unionType = Union.from(rhsValues.assumeNonEmpty)
            state.fill(lhs, unionType)
            ZonkResult.Done
        }
      }
    }
  }

  def tryUnify(lhs: Term, rhs: Term)(using state: StateAbility[Ck], localCtx: LocalCtx): Boolean = {
    if (lhs == rhs) return true
    val lhsResolved = lhs match {
      case varCall: MaybeVarCall =>
        localCtx.getKnown(varCall) match {
          case Some(tyAndVal) =>
            state.readStable(tyAndVal.valueId).getOrElse(lhs)
          case None => lhs
        }
      case _ => lhs
    }
    val rhsResolved = rhs match {
      case varCall: MaybeVarCall =>
        localCtx.getKnown(varCall) match {
          case Some(tyAndVal) =>
            state.readStable(tyAndVal.valueId).getOrElse(rhs)
          case None => rhs
        }
      case _ => rhs
    }
    if (lhsResolved == rhsResolved) return true
    return false // TODO
  }


  case class LiteralType(x: Literals, tyLhs: CellId[Term])(using localCtx: LocalCtx) extends Propagator[Ck] {
    override val readingCells = Set(tyLhs)
    override val writingCells = Set(tyLhs)
    override val zonkingCells = Set(tyLhs)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      if (state.noValue(tyLhs)) return false
      val ty_ = state.readStable(this.tyLhs).get
      ty_ match {
        case Meta(ty) => {
          state.addPropagator(LiteralType(x, ty))
          return true
        }
        case _ => ()
      }
      val t = x match {
        case IntegerLiteral(_, _) => IntegerType
        case RationalLiteral(_, _) => RationalType
        case StringLiteral(_, _) => StringType
        case SymbolLiteral(_, _) => SymbolType
      }
      x match {
        case IntegerLiteral(_, _) => {
          if (tryUnify(ty_, IntType)) return true
        }
        case _ => {

        }
      }
      if (ty_ == t) {
        return true
      } else {
        unify(ty_, t, x)
        return true
      }
    }

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], more: Ck): ZonkResult =
      state.fill(tyLhs, x match {
        case IntegerLiteral(_, _) => IntegerType
        case RationalLiteral(_, _) => RationalType
        case StringLiteral(_, _) => StringType
        case SymbolLiteral(_, _) => SymbolType
      })
      ZonkResult.Done
  }

  case class IsEffects(effects: CellId[Effects]) extends Propagator[Ck] {
    override val readingCells = Set(effects)
    override val zonkingCells = Set(effects)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = state.hasValue(effects)

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      state.fill(effects, Effects.Empty)
      ZonkResult.Done
    }
  }

  def newType(using ck: Ck, state: StateAbility[Ck]): CellId[Term] = {
    val cell = state.addCell(OnceCell[Term](default = Some(AnyType0Debug)))
    cell
  }

  def newTypeTerm(using ck: Ck, state: StateAbility[Ck]): Term = {
    Meta(newType)
  }

  def newEffects(using ck: Ck, state: StateAbility[Ck]): CellId[Effects] = {
    val cell = state.addCell(OnceCell[Effects](default = Some(NoEffect)))
    cell
  }

  def newEffectsTerm(using ck: Ck, state: StateAbility[Ck]): Effects | MetaTerm = {
    Meta(newEffects)
  }

  def readVar(x: Term)(using localCtx: LocalCtx, ck: Ck, state: StateAbility[Ck]): Term = {
    var result = x
    while (true) {
      result match {
        case varCall: MaybeVarCall =>
          localCtx.getKnown(varCall) match {
            case Some(tyAndVal) =>
              result = state.readStable(tyAndVal.valueId).getOrElse {
                return result
              }
            case None => return result
          }
        case _ => return result
      }
    }
    result
  }

  def readMetaVar(x: Term)(using localCtx: LocalCtx, ck: Ck, state: StateAbility[Ck]): Term = {
    var result = x
    while (true) {
      result match {
        case varCall: MaybeVarCall =>
          localCtx.getKnown(varCall) match {
            case Some(tyAndVal) =>
              result = state.readStable(tyAndVal.valueId).getOrElse {
                return result
              }
            case None => return result
          }
        case Meta(id) =>
          state.readStable(id) match {
            case Some(x) => result = x
            case None => return result
          }
        case _ => return result
      }
    }
    result
  }

  def unify(lhs: Term, rhs: Term, cause: Expr)(using localCtx: LocalCtx, ck: Ck, state: StateAbility[Ck]): Unit = {
    if (lhs == rhs) return
    val lhsResolved = readVar(lhs)
    val rhsResolved = readVar(rhs)
    if (lhsResolved == rhsResolved) return
    (lhsResolved, rhsResolved) match {
      case (Meta(lhs), rhs) => unify(lhs, rhs, cause)
      case (lhs, Meta(rhs)) => unify(lhs, rhs, cause)

      // Structural unification for ListType
      case (ListType(elem1), ListType(elem2)) =>
        unify(elem1, elem2, cause)

      case (Type(Levelω), Type(LevelFinite(_))) => ()

      // THIS IS INCORRECT, TODO: FIX
      case (Union(types1), Union(types2)) =>
        val minLength = math.min(types1.length, types2.length)
        (types1.take(minLength), types2.take(minLength)).zipped.foreach { (ty1, ty2) =>
          unify(ty1, ty2, cause)
        }
        if (types1.length != types2.length) {
          ck.reporter.apply(TypeMismatch(lhs, rhs, cause))
        }

      // Base case: types do not match
      case _ =>
        ck.reporter.apply(TypeMismatch(lhs, rhs, cause))

    }
  }

  def unify(t1: Term, t2: CellId[Term], cause: Expr)(using localCtx: LocalCtx, ck: Ck, state: StateAbility[Ck]): Unit = {
    state.addPropagator(Unify(literal(t1), t2, cause))
  }

  def unify(t1: CellId[Term], t2: Term, cause: Expr)(using localCtx: LocalCtx, ck: Ck, state: StateAbility[Ck]): Unit = {
    state.addPropagator(Unify(t1, literal(t2), cause))
  }

  def unify(t1: CellId[Term], t2: CellId[Term], cause: Expr)(using localCtx: LocalCtx, ck: Ck, state: StateAbility[Ck]): Unit = {
    state.addPropagator(Unify(t1, t2, cause))
  }

  /** t is rhs, listT is lhs */
  case class ListOf(tRhs: CellId[Term], listTLhs: CellId[Term], cause: Expr)(using ck: Ck, localCtx: LocalCtx) extends Propagator[Ck] {
    override val readingCells = Set(tRhs, listTLhs)
    override val writingCells = Set(tRhs, listTLhs)
    override val zonkingCells = Set(listTLhs)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      val t1 = state.readStable(this.tRhs)
      val listT1 = state.readStable(this.listTLhs)
      (t1, listT1) match {
        case (_, Some(Meta(listTLhs))) => {
          state.addPropagator(ListOf(tRhs, listTLhs, cause))
          true
        }
        case (_, Some(l)) if !l.isInstanceOf[ListType] => {
          ck.reporter.apply(TypeMismatch(ListType(AnyType0), l, cause))
          true
        }
        case (Some(t1), Some(ListType(t2))) => {
          unify(t2, t1, cause)
          true
        }
        case (_, Some(ListType(t2))) => {
          unify(t2, tRhs, cause)
          true
        }
        case (Some(t1), None) => {
          unify(this.listTLhs, ListType(t1): Term, cause)
          true
        }
        case (None, None) => {
          unify(this.listTLhs, ListType(Meta(tRhs)): Term, cause)
          true
        }
      }
    }

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val t1 = state.readStable(this.tRhs)
      val listT1 = state.readStable(this.listTLhs)
      if (!t1.isDefined) return ZonkResult.Require(Vector(this.tRhs))
      val ty = t1.get
      assert(listT1.isEmpty)
      state.fill(this.listTLhs, ListType(ty))
      ZonkResult.Done
    }
  }

  class MutableLocalCtx(var ctx: LocalCtx) {
    def update(f: LocalCtx => LocalCtx): Unit = {
      ctx = f(ctx)
    }
  }

  given mutL(using m: MutableLocalCtx): LocalCtx = m.ctx

}

trait ElaboraterBase extends CommonPropagator[Ck] {

  object Meta {
    def rec(x: CellId[Term], default: Term)(using state: StateAbility[Ck]): Term = {
      state.readStable(x) match {
        case Some(x) => x
        case None => default
      }
    }

    def apply[T <: Term](x: CellId[T])(using state: StateAbility[Ck]): T | MetaTerm = {
      state.readStable(x) match {
        case Some(x@Meta(id)) => rec(id, x).asInstanceOf[T | MetaTerm]
        case Some(x) => x
        case None => MetaTerm.from(x)
      }
    }

    def unapply(x: Term)(using state: StateAbility[Ck]): Option[CellId[Term]] = x match {
      case m: MetaTerm => {
        var result: CellId[Term] = m.unsafeRead[CellId[Term]]
        while (true) {
          state.readStable(result) match {
            case Some(m: MetaTerm) => result = m.unsafeRead[CellId[Term]]
            case _ => return Some(result)
          }
        }
        throw new IllegalStateException("Unreachable")
      }
      case _ => None
    }
  }


  def newLocalv(name: Name, ty: CellIdOr[Term], id: UniqIdOf[LocalV], meta: Option[ExprMeta])(using ck: Ck, state: StateAbility[Ck]): LocalV = {
    val m = convertMeta(meta)
    LocalV(name, toTerm(ty), id, m)
  }

  def toTerm[T <: Term](x: CellIdOr[T])(using state: StateAbility[Ck]): T | MetaTerm = x match {
    case x: Term => x match {
      case Meta(x) => Meta(x).asInstanceOf[T | MetaTerm]
      case x => x.asInstanceOf[T | MetaTerm]
    }
    case x => Meta(x.asInstanceOf[CellId[Term]]).asInstanceOf[T | MetaTerm]
  }

  def toId[T <: Term](x: CellIdOr[T])(using state: StateAbility[Ck]): CellId[T] = x match {
    case Meta(id) => id.asInstanceOf[CellId[T]]
    case x => state.toId(x)
  }

  def merge(a: CellIdOr[Term], b: CellIdOr[Term])(using state: StateAbility[Ck]): Unit = {
    if (a == b) return
    val t1 = toTerm(a)
    val t2 = toTerm(b)
    if (a == b) return
    (t1, t2) match {
      case (Meta(t1), t2) => state.fill(t1, t2)
      case (t1, Meta(t2)) => state.fill(t2, t1)
      case _ => ???
    }
  }
}