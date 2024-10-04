package chester.propagator

import cats.implicits.*
import chester.error.*
import chester.resolve.{SimpleDesalt, resolveOpSeq}
import chester.syntax.Name
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.*
import chester.utils.*
import chester.utils.propagator.*

import scala.language.implicitConversions
import scala.util.boundary
import scala.util.boundary.break

type Ck = Get[TyckProblem, Unit]

trait Elaborater extends ProvideCtx with ElaboraterCommon {

  def checkType(expr: Expr)(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): Term = {
    // Create a new type cell representing the kind Typeω (the type of types)
    val kindType = literal(Typeω: Term)

    // Create a new effects cell to capture any effects from the type expression
    val effectsCell = literal(NoEffect)

    elab(expr, kindType, effectsCell)
  }

  def checkTypeId(expr: Expr)(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term] = {
    toId(checkType(expr))
  }
  
  def elabTy(expr: Option[Expr])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): Term =
    expr match {
      case Some(expr) => checkType(expr)
      case None => Meta(newType)
    }

  def elab(expr: Expr, ty: CellIdOr[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): Term
  def elabId(expr: Expr, ty: CellIdOr[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): CellId[Term] = {
    val term = elab(expr, ty, effects)
    toId(term)
  }
}

trait ProvideElaborater extends ProvideCtx with Elaborater with ElaboraterFunction with ElaboraterFunctionCall {

  // TODO: add something for implicit conversion

  /** ty is lhs */
  override def elab(expr: Expr, ty0: CellIdOr[Term], effects: CellId[Effects])(using localCtx: LocalCtx, parameter: Global, ck: Ck, state: StateAbility[Ck]): Term = toTerm {
    val ty = state.toId(ty0)
    resolve(expr, localCtx) match {
      case expr@Identifier(name, meta) => {
        localCtx.get(name) match {
          case Some(c: ContextItem) => {
            if(c.reference.isDefined){
              state.add(c.reference.get.referencedOn, expr)
            }
            state.addPropagator(Unify(ty, c.tyId, expr))
            c.ref
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
      case expr@UnitExpr(meta) => {
        unify(ty, UnitType, expr)
        UnitTerm
      }
      case expr@ListExpr(terms, meta) => {
        val t = newType
        // Relate the list type 'ty' to 'ListType(t)'
        state.addPropagator(ListOf(t, ty, expr))

        // For each term, check it with its own type variable and collect the results
        val termResults = terms.map { term =>
          val elemTy = newType
          val wellTypedTerm = elab(term, elemTy, effects)
          (wellTypedTerm, elemTy)
        }

        // Collect the types of the elements
        val elemTypes = termResults.map(_._2).toVector

        // Ensure that 't' is the union of the element types
        if (elemTypes.nonEmpty) state.addPropagator(UnionOf(t, elemTypes, expr))

        ListTerm(termResults.map(_._1))
      }
      case expr@TypeAnotationNoEffects(innerExpr, tyExpr, meta) =>
        // Check the type annotation expression to get its type
        val declaredTyTerm = checkType(tyExpr)

        unify(ty, declaredTyTerm, expr)

        elab(innerExpr, declaredTyTerm, effects)
      case expr: FunctionExpr => elabFunction(expr, ty, effects)
      case expr@Block(heads, tail, meta) => {
        case class DefInfo(expr: LetDefStmt, id: UniqIdOf[LocalV], tyAndVal: TyAndVal, item: ContextItem)

        val defs = heads.collect {
          case expr@LetDefStmt(LetDefType.Def, defined, _, _, _, _) =>
            val name = defined match {
              // TODO: support other defined patterns
              case DefinedPattern(PatternBind(name, _)) => name.name
            }
            val tyandval = TyAndVal.create()
            val id = UniqId.generate[LocalV]
            val localv = newLocalv(name, tyandval.ty, id, meta)
            val r = Reference.create(localv, id, expr)
            state.add(parameter.references, r)
            DefInfo(expr, UniqId.generate[LocalV], tyandval, ContextItem(name, id, localv, tyandval.ty, Some(r)))
        }
        val defsMap = defs.map(info => (info.expr, info)).toMap
        var ctx = localCtx.add(defs.map(_.item))
        val names = heads.collect {
          case expr: LetDefStmt => expr.defined match {
            case DefinedPattern(PatternBind(name, _)) => name
          }
        }
        if (names.hasDuplication) {
          val problem = DuplicateDefinition(expr)
          ck.reporter.apply(problem)
        }
        val stmts: Seq[StmtTerm] = heads.flatMapOrdered {
          case expr@LetDefStmt(LetDefType.Def, _, _, _, _, _) => {
            implicit val localCtx: LocalCtx = ctx
            val d = defsMap.apply(expr)
            val ty = expr.ty match {
              case Some(tyExpr) => {
                val t = checkTypeId(tyExpr)
                merge(t, d.tyAndVal.tyId)
                t
              }
              case None => d.tyAndVal.ty
            }
            val wellTyped = elabId(expr.body.get, ty, effects)
            merge(d.tyAndVal.valueId, wellTyped)
            ctx = ctx.knownAdd(d.id, TyAndVal(ty, wellTyped))
            Vector(DefStmtTerm(d.item.name, Meta(wellTyped), toTerm(ty)))
          }
          case expr@LetDefStmt(LetDefType.Let, _, _, _, _, _) => {
            implicit val localCtx: LocalCtx = ctx
            val name = expr.defined match {
              // TODO: support other defined patterns
              case DefinedPattern(PatternBind(name, _)) => name.name
            }
            val id = UniqId.generate[LocalV]
            val ty = expr.ty match {
              case Some(tyExpr) => checkType(tyExpr)
              case None => newTypeTerm
            }
            val localv = newLocalv(name, ty, id, meta)
            val r = Reference.create(localv, id, expr)
            state.add(parameter.references, r)
            val wellTyped = elab(expr.body.get, ty, effects)
            ctx = ctx.add(ContextItem(name, id, localv, ty, Some(r))).knownAdd(id, TyAndVal(ty, wellTyped))
            Vector(LetStmtTerm(name, wellTyped, ty))
          }
          case expr => {
            implicit val localCtx: LocalCtx = ctx
            val ty = newType
            Vector(ExprStmtTerm(elab(expr, ty, effects), Meta(ty)))
          }
        }
        {
          implicit val localCtx: LocalCtx = ctx
          val tailExpr = tail.getOrElse(UnitExpr(meta))
          val wellTyped = elab(tailExpr, ty, effects)
          BlockTerm(stmts, elab(tailExpr, ty, effects))
        }
      }
      case expr: DesaltFunctionCall => elabFunctionCall(expr, ty, effects)
      case expr: Expr => {
        val problem = NotImplemented(expr)
        ck.reporter.apply(problem)
        ErrorTerm(problem)
      }
    }
  }

  given ckToReport(using ck: Ck): Reporter[TyckProblem] = ck.reporter

  case class CkState(
                      symbols: Seq[FinalReference] = Vector.empty[FinalReference]
                    )

  object CkState {
    val Empty: CkState = CkState()
  }

}

trait DefaultImpl extends ProvideElaborater with ProvideImpl with ProvideElaboraterFunction with ProvideElaboraterFunctionCall {

  def check(expr: Expr, ty: Option[Term] = None, effects: Option[Effects] = None): TyckResult[CkState, Judge] = {
    val reporter = new VectorReporter[TyckProblem]
    implicit val get: Ck = new Get(reporter, new MutBox(()))
    implicit val able: StateAbility[Ck] = stateAbilityImpl
    val ty1: CellId[Term] = ty match {
      case Some(ty) => {
        val cell = literal[Term](ty)
        cell
      }
      case None => {
        val cell = newType
        cell
      }
    }
    val effects1: CellId[Effects] = effects match {
      case Some(effects) => {
        val cell = able.addCell(LiteralCell[Effects](effects))
        cell
      }
      case None => {
        val cell = able.addCell(OnceCell[Effects]())
        cell
      }
    }
    able.addPropagator(IsEffects(effects1))
    implicit val ctx: LocalCtx = LocalCtx.default
    val references = able.addCell(CollectionCell[Reference]())
    implicit val recording: Global = Global(references)
    val wellTyped = elabId(expr, ty1, effects1)
    able.naiveZonk(Vector(ty1, effects1, wellTyped))
    var judge = Judge(able.read(wellTyped).get, able.read(ty1).get, able.read(effects1).get)
    boundary{
      while (true) {
        val metas = judge.collectMeta
        if (metas.isEmpty) break()
        able.naiveZonk(metas.map(x=>x.unsafeRead[CellId[Term]]))
        judge = judge.replaceMeta(x => able.read(x.unsafeRead[CellId[Term]]).get)
      }
    }
    val symbols = able.read(references).get.map { ref =>
      val call = able.read(ref.callAsMaybeVarCall).get
      val definedOn = able.read(ref.definedOn).get
      val referencedOn = able.read(ref.referencedOn).get
      FinalReference(call, ref.id, definedOn, referencedOn)
    }
    TyckResult0(CkState(symbols), judge, reporter.getReports)
  }
}

object Cker extends DefaultImpl with ProvideMutable {
}

export Cker.{check, CkState, FinalReference}