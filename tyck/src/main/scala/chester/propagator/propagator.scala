package chester.propagator

import cats.implicits.*
import chester.error.*
import chester.resolve.{SimpleDesalt, resolveOpSeq}
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.{Get, Reporter, SymbolTable, TyckResult, TyckResult0, VectorReporter}
import chester.utils.propagator.*
import chester.utils.*

import scala.language.implicitConversions

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


object BaseTycker {
  type Literals = Expr & (IntegerLiteral | RationalLiteral | StringLiteral | SymbolLiteral)

  case class Unify(lhs: CellId[Term], rhs: CellId[Term], cause: Expr, uniqId: UniqIdOf[Unify] = UniqId.generate[Unify])(using localCtx: LocalCtx) extends Propagator[Ck] {
    override val readingCells = Set(lhs, rhs)
    override val writingCells = Set(lhs, rhs)
    override val zonkingCells = Set(lhs, rhs)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      val lhs = state.read(this.lhs)
      val rhs = state.read(this.rhs)
      if (lhs.isDefined && rhs.isDefined) {
        unify(lhs.get, rhs.get, cause)
        return true
      }
      return false
    }

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val lhs = state.read(this.lhs)
      val rhs = state.read(this.rhs)
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
        case _ => return ZonkResult.NotYet
      }
    }
  }

  case class UnionOf(
                      lhs: CellId[Term],
                      rhs: Vector[CellId[Term]],
                      cause: Expr,
                      uniqId: UniqIdOf[UnionOf] = UniqId.generate[UnionOf]
                    )(using localCtx: LocalCtx) extends Propagator[Ck] {
    override val readingCells = Set(lhs) ++ rhs.toSet
    override val writingCells = Set(lhs)
    override val zonkingCells = Set(lhs) ++ rhs.toSet

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      val lhsValueOpt = state.read(lhs)
      val rhsValuesOpt = rhs.map(state.read)

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

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val lhsValueOpt = state.read(lhs)
      val rhsValuesOpt = rhs.map(state.read)

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

  def tryUnify(lhs: Term, rhs: Term)(using state: StateAbility[Ck],localCtx: LocalCtx): Boolean = {
    if(lhs == rhs) return true
    val lhsResolved = lhs match {
      case varCall: MaybeVarCall =>
        localCtx.getKnown(varCall) match {
          case Some(tyAndVal) =>
            state.read(tyAndVal.value).getOrElse(lhs)
          case None => lhs
        }
      case _ => lhs
    }
    val rhsResolved = rhs match {
      case varCall: MaybeVarCall =>
        localCtx.getKnown(varCall) match {
          case Some(tyAndVal) =>
            state.read(tyAndVal.value).getOrElse(rhs)
          case None => rhs
        }
      case _ => rhs
    }
    if (lhsResolved == rhsResolved) return true
    return false // TODO
  }


  case class LiteralType(x: Literals, tyLhs: CellId[Term], uniqId: UniqIdOf[LiteralType] = UniqId.generate[LiteralType])(using localCtx: LocalCtx) extends Propagator[Ck] {
    override val readingCells = Set(tyLhs)
    override val writingCells = Set(tyLhs)
    override val zonkingCells = Set(tyLhs)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      if (state.noValue(tyLhs)) return false
      val ty_ = state.read(this.tyLhs).get
      val t = x match {
        case IntegerLiteral(_, _) =>IntegerType
        case RationalLiteral(_, _) =>RationalType
        case StringLiteral(_, _) => StringType
        case SymbolLiteral(_, _) => SymbolType
      }
      x match {
        case IntegerLiteral(_,_) => {
          if(tryUnify(ty_, IntType)) return true
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

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult =
      state.fill(tyLhs, x match {
        case IntegerLiteral(_, _) => IntegerType
        case RationalLiteral(_, _) => RationalType
        case StringLiteral(_, _) => StringType
        case SymbolLiteral(_, _) => SymbolType
      })
      ZonkResult.Done
  }

  case class IsEffects(effects: CellId[Effects], uniqId: UniqIdOf[IsEffects] = UniqId.generate[IsEffects]) extends Propagator[Ck] {
    override val zonkingCells = Set(effects)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = state.hasValue(effects)

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      state.fill(effects, Effects.Empty)
      ZonkResult.Done
    }
  }

  case class IsType(ty: CellId[Term], uniqId: UniqIdOf[IsType] = UniqId.generate[IsType]) extends Propagator[Ck] {
    override val readingCells = Set(ty)
    override val zonkingCells = Set(ty)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = state.hasValue(ty)

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      if (state.readingZonkings(Vector(ty)).exists { (x: Propagator[Ck]) => !x.isInstanceOf[IsType] }) return ZonkResult.NotYet
      state.fill(ty, AnyType0)
      ZonkResult.Done
    }
  }

  def newType(using ck: Ck, state: StateAbility[Ck]): CellId[Term] = {
    val cell = OnceCell[Term]()
    state.addCell(cell)
    state.addPropagator(IsType(cell.uniqId))
    cell.uniqId
  }

  case class FlatMaping[T, U](xs: Vector[CellId[T]], f: Vector[T] => U, result: CellId[U], uniqId: UniqIdOf[FlatMaping[T, U]] = UniqId.generate[FlatMaping[T, U]]) extends Propagator[Ck] {
    override val readingCells = xs.toSet
    override val writingCells = Set(result)
    override val zonkingCells = Set(result)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      xs.traverse(state.read(_)).map(f) match {
        case Some(result) => {
          state.fill(this.result, result)
          true
        }
        case None => false
      }
    }

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val needed = xs.filter(state.noValue(_))
      if (needed.nonEmpty) return ZonkResult.Require(needed)
      val done = run
      require(done)
      ZonkResult.Done
    }
  }

  def FlatMap[T, U](xs: Vector[CellId[T]])(f: Vector[T] => U)(using ck: Ck, state: StateAbility[Ck]): CellId[U] = {
    val cell = OnceCell[U]()
    state.addCell(cell)
    state.addPropagator(FlatMaping(xs, f, cell.uniqId))
    cell.uniqId
  }

  def Map[T, U](x: CellId[T])(f: T => U)(using ck: Ck, state: StateAbility[Ck]): CellId[U] = {
    val cell = OnceCell[U]()
    state.addCell(cell)
    state.addPropagator(FlatMaping(Vector(x), (xs: Vector[T]) => f(xs.head), cell.uniqId))
    cell.uniqId
  }

  def unify(lhs: Term, rhs: Term, cause: Expr)(using localCtx: LocalCtx, ck: Ck, state: StateAbility[Ck]): Unit = {
    if (lhs == rhs) return
    val lhsResolved = lhs match {
      case varCall: MaybeVarCall =>
        localCtx.getKnown(varCall) match {
          case Some(tyAndVal) =>
            state.read(tyAndVal.value).getOrElse(lhs)
          case None => lhs
        }
      case _ => lhs
    }
    val rhsResolved = rhs match {
      case varCall: MaybeVarCall =>
        localCtx.getKnown(varCall) match {
          case Some(tyAndVal) =>
            state.read(tyAndVal.value).getOrElse(rhs)
          case None => rhs
        }
      case _ => rhs
    }
    if (lhsResolved == rhsResolved) return
    (lhsResolved, rhsResolved) match {

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
  case class ListOf(tRhs: CellId[Term], listTLhs: CellId[Term], cause: Expr, uniqId: UniqIdOf[ListOf] = UniqId.generate[ListOf])(using  ck: Ck, localCtx: LocalCtx) extends Propagator[Ck] {
    override val readingCells = Set(tRhs, listTLhs)
    override val writingCells = Set(tRhs, listTLhs)
    override val zonkingCells = Set(listTLhs)

    override def run(using state: StateAbility[Ck], more: Ck): Boolean = {
      val t1 = state.read(this.tRhs)
      val listT1 = state.read(this.listTLhs)
      (t1, listT1) match {
        case (_, Some(l)) if !l.isInstanceOf[ListType] => {
          ck.reporter.apply(TypeMismatch(l, ListType(t1.get), cause))
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
          unify(this.listTLhs, ListType(t1):Term, cause)
          true
        }
        case (_, _) => false
      }
    }

    override def naiveZonk(needed: Vector[UniqIdOf[Cell[?]]])(using state: StateAbility[Ck], more: Ck): ZonkResult = {
      val t1 = state.read(this.tRhs)
      val listT1 = state.read(this.listTLhs)
      if (!t1.isDefined) return ZonkResult.Require(Vector(this.tRhs))
      val ty = t1.get
      assert(listT1.isEmpty)
      state.fill(this.listTLhs, ListType(ty))
      ZonkResult.Done
    }
  }

  // TODO: add something for implicit conversion

  /** ty is lhs */
  def check(expr: Expr, ty: CellId[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term] = state.toId {
    resolve(expr, localCtx) match {
      case expr@Identifier(name, meta) => {
        localCtx.get(name) match {
          case Some(c:ContextItem) => {
            state.addPropagator(Unify(ty, c.ty, expr))
            c.asTerm
          }
          case None => {
            val problem = UnboundVariable(name, expr)
            ck.reporter.apply(problem)
            ErrorTerm(problem)
          }
        }
      }
      case expr@IntegerLiteral(value, meta) => {
        state.addPropagator(LiteralType(expr, ty))
        AbstractIntTerm.from(value)
      }
      case expr@RationalLiteral(value, meta) => {
        state.addPropagator(LiteralType(expr, ty))
        RationalTerm(value)
      }
      case expr@StringLiteral(value, meta) => {
        state.addPropagator(LiteralType(expr, ty))
        StringTerm(value)
      }
      case expr@SymbolLiteral(value, meta) => {
        state.addPropagator(LiteralType(expr, ty))
        SymbolTerm(value)
      }
      case expr@ListExpr(terms, meta) => {
        val t = newType
        // Relate the list type 'ty' to 'ListType(t)'
        state.addPropagator(ListOf(t, ty, expr))

        // For each term, check it with its own type variable and collect the results
        val termResults = terms.map { term =>
          val elemTy = newType
          val wellTypedTerm = check(term, elemTy, effects)
          (wellTypedTerm, elemTy)
        }

        // Collect the types of the elements
        val elemTypes = termResults.map(_._2).toVector

        // Ensure that 't' is the union of the element types
        if (elemTypes.nonEmpty) state.addPropagator(UnionOf(t, elemTypes, expr))

        // Build the ListTerm with the well-typed terms
        FlatMap(termResults.map(_._1)) { xs =>
          ListTerm(xs)
        }
      }
      case expr@TypeAnotationNoEffects(innerExpr, tyExpr, meta) =>
        // Check the type annotation expression to get its type
        val declaredTyTerm = checkType(tyExpr)
        
        unify(ty, declaredTyTerm, expr)

        check(innerExpr, declaredTyTerm, effects)
      case expr@Block(heads, tail, meta) => {
        case class DefInfo(expr: LetDefStmt, id: UniqIdOf[LocalV], tyAndVal: TyAndVal, item: ContextItem)
        val defs = heads.collect {
          case expr@LetDefStmt(LetDefType.Def,defined,_,_,_,_) =>
            val name = defined match {
              // TODO: support other defined patterns
              case DefinedPattern(PatternBind(name, _)) => name.name
            }
            val tyandval = TyAndVal.create()
            val id = UniqId.generate[LocalV]
            val localv = Map(tyandval.ty)(LocalV(name, _, id))
            DefInfo(expr, UniqId.generate[LocalV], tyandval, ContextItem(name, id, localv, tyandval.ty))
        }
        var ctx = localCtx.knownAdd(defs.map(info=>(info.id, info.tyAndVal)))
        val stmts: Seq[CellId[StmtTerm]] = heads.flatMapOrdered {
          case expr@LetDefStmt(LetDefType.Def,_,_,_,_,_) => ???
          case expr@LetDefStmt(LetDefType.Let,_,_,_,_,_) => ???
          case expr => {
            val ty = newType
            Vector(FlatMap(Vector(check(expr, ty, effects), ty)) { case Vector(wellTyped, ty) => ExprStmtTerm(wellTyped, ty) })
          }
        }
          ???
      }
      case expr: Expr => {
        val problem = NotImplemented(expr)
        ck.reporter.apply(problem)
        ErrorTerm(problem)
      }
    }
  }

  def checkType(expr: Expr)(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term] = {
    // Create a new type cell representing the kind Typeω (the type of types)
    val kindType = literal(Typeω: Term)

    // Create a new effects cell to capture any effects from the type expression
    val effectsCell = literal(NoEffect)

    check(expr, kindType, effectsCell)
  }
}

type Ck = Get[TyckProblem, CkState]

given ckToReport(using ck: Ck): Reporter[TyckProblem] = ck.reporter

case class CkState(
                    symbols: SymbolTable = Set.empty,
                  )

object Cker {
  def check(expr: Expr, ty: Option[Term] = None, effects: Option[Effects] = None): TyckResult[CkState, Judge] = {
    val reporter = new VectorReporter[TyckProblem]
    implicit val get: Ck = new Get(reporter, new MutBox(CkState()))
    implicit val able: StateAbility[Ck] = new StateCells[Ck]()
    val ty1: CellId[Term] = ty match {
      case Some(ty) => {
        val cell = literal[Term](ty)
        cell
      }
      case None => {
        val cell = OnceCell.create[Term]()
        able.addPropagator(BaseTycker.IsType(cell))
        cell
      }
    }
    val effects1: CellId[Effects] = effects match {
      case Some(effects) => {
        val cell = LiteralCell[Effects](effects)
        able.addCell(cell)
        cell.uniqId
      }
      case None => {
        val cell = OnceCell[Effects]()
        able.addCell(cell)
        cell.uniqId
      }
    }
    able.addPropagator(BaseTycker.IsEffects(effects1))
    implicit val ctx: LocalCtx = LocalCtx.default
    val references = CollectionCell[Reference]()
    able.addCell(references)
    implicit val recording: Global = Global(references.uniqId)
    val wellTyped = BaseTycker.check(expr, ty1, effects1)
    able.naiveZonk(Vector(ty1, effects1, wellTyped))
    TyckResult0(get.getState, Judge(able.read(wellTyped).get, able.read(ty1).get, able.read(effects1).get), reporter.getReports)
  }
}