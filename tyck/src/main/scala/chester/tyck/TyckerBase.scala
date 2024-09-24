package chester.tyck

import chester.error.*
import chester.resolve.SimpleDesalt
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.core.CoreTycker
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.constraint.collection.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.upickle.given
import chester.utils.*
import spire.math.Trilean
import spire.math.Trilean.{True, Unknown}

import scala.annotation.tailrec
import scala.language.implicitConversions

trait TyckerBase[Self <: TyckerBase[Self] & FunctionTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends TyckerTrait[Self] {
  implicit val reporter1: Reporter[TyckProblem] = tyck.reporter

  def superTypes(ty: Term): Option[Vector[Term]] = {
    ty match {
      case Intersection(ts) => Some(ts)
      case _ => None
    }
  }

  def compareTy(rhs: Term, lhs: Term): Trilean = {
    val subType1 = whnfNoEffect(rhs)
    val superType1 = whnfNoEffect(lhs)
    if (subType1 == superType1) True
    else (subType1, superType1) match {
      case (subType, AnyType(level)) => True // TODO: level
      case _ => Unknown
    }
  }

  def isDefined(x: MetaTerm): Boolean = {
    val state = tyck.getState
    state.subst.isDefined(x)
  }

  def linkTy(from: MetaTerm, to: Term): Unit = link(from, synthesizeTyTerm(to).toJudge)

  def link(from: MetaTerm, to: Judge): Unit = ???

  /** assume a subtype relationship and get a subtype back */
  def unifyTy(lhs: Term, rhs: Term, failed: => Term = null): Term = {
    val rhs1 = whnfNoEffect(rhs)
    val lhs1 = whnfNoEffect(lhs)
    if (rhs1 == lhs1) rhs1
    else (lhs1, rhs1) match {
      case (lhs, rhs: MetaTerm) if !isDefined(rhs) =>
        addConstraint(MetaConstraint.TyRange(rhs, lower = Some(Judge(lhs, Typeω, NoEffect)), upper = None))
        lhs
      case (lhs: MetaTerm, rhs) if !isDefined(lhs) =>
        addConstraint(MetaConstraint.TyRange(lhs, lower = None, upper = Some(Judge(rhs, Typeω, NoEffect))))
        rhs
      case (lhs: MetaTerm, rhs: MetaTerm) =>
        if (isDefined(rhs)) {
          if (!isDefined(lhs)) {
            addConstraint(MetaConstraint.TyRange(lhs, lower = None, upper = Some(Judge(rhs, Typeω, NoEffect))))
            rhs
          } else {
            // Both lhs and rhs are defined, unify their solutions
            {
              val lhsSolution = getSolution(lhs)
              val rhsSolution = getSolution(rhs)
              unifyTy(lhsSolution, rhsSolution)
            }
          }
        } else {
          // Neither is defined, create a constraint linking them
          addConstraint(MetaConstraint.TyRange(rhs, lower = Some(Judge(lhs, Typeω, NoEffect)), upper = None))
          lhs
        }
      case (AnyType(level), subType) => subType // TODO: level
      case (Union(superTypes), subType) => Union.from(superTypes.map(x => unifyTyOrNothingType(rhs = subType, lhs = x)))
      case (superType, Union(subTypes)) => Union.from(subTypes.map(rhs => unifyTy(rhs = rhs, lhs = superType)))
      case (Intersection(superTypes), subType) => Intersection.from(superTypes.map(x => unifyTy(rhs = subType, lhs = x)))
      case (superTypes, Intersection(subTypes)) => Intersection.from(subTypes.map(x => unifyTy(rhs = x, lhs = superTypes)))
      case (IntegerType, IntType) => IntType
      case (superType, subType) =>
        if (failed != null) failed else {
          val err = UnifyFailedError(rhs = subType, lhs = superType)
          tyck.report(err)
          new ErrorTerm(err)
        }
    }
  }

  def unifyTyOrNothingType(lhs: Term, rhs: Term): Term = unifyTy(rhs = rhs, lhs = lhs, failed = NothingType)

  /** get the most sub common super type */
  def common(ty1: Term, ty2: Term): Term = {
    if (ty1 == ty2) ty1
    else (ty1, ty2) match {
      case (_, AnyType(level)) => AnyType0 // TODO: level
      case (AnyType(level), _) => AnyType0 // TODO: level
      case (NothingType, ty) => ty
      case (ty, NothingType) => ty
      case (ListType(ty1), ListType(ty2)) => ListType(common(ty1, ty2))
      case (Union(ts1), ty2) => Union(ts1.map(common(_, ty2)))
      case (ty1, Union(ts2)) => Union(ts2.map(common(ty1, _)))
      case (ty1, ty2) =>
        Union.from(Vector(ty1, ty2))
    }
  }

  def common(seq: Seq[Term]): Term = {
    seq.reduce(common)
  }

  def effectFold(es: Seq[Effects]): Effects = {
    Effects.merge(es.assumeNonEmpty)
  }

  def effectUnion(e1: Effects, e2: Effects): Effects = e1.merge(e2)

  def collectLevel(levels: Seq[Term]): Term = {
    require(levels.nonEmpty)
    if (levels.contains(Levelω)) {
      Levelω
    } else {
      levels.reduceLeft {
        case (LevelFinite(AbstractIntTerm(max)), LevelFinite(AbstractIntTerm(n))) =>
          LevelFinite(AbstractIntTerm.from(max.max(n)))
        case (level, LevelFinite(v: LocalVar)) =>
          // Handle LocalVar case appropriately
          ???
        case (level, _) =>
          // Handle other cases or throw an error
          ???
      }
    }
  }

  def tyFold(types: Vector[Term]): Term = {
    types.reduce((ty1, ty2) => common(ty1, ty2))
  }

  def synthesizeObjectExpr(x: ObjectExpr, effects: Option[Effects]): Judge = {
    val synthesizedClausesWithEffects: Vector[EffectWith[(Term, Term, Term)]] = x.clauses.map {
      case ObjectExprClauseOnValue(keyExpr, valueExpr) => {
        val Judge(wellTypedExpr, exprType, exprEffect) = synthesize(valueExpr, effects)
        val Judge(keyWellTyped, _, keyEffect) = synthesize(keyExpr, effects)
        val combinedEffect = effectUnion(exprEffect, keyEffect)
        EffectWith(combinedEffect, (keyWellTyped, wellTypedExpr, exprType))
      }
      case _ => throw new IllegalArgumentException("Unexpected clause type, maybe no desalted")
    }

    val combinedEffect = effectFold(synthesizedClausesWithEffects.map(_.effect))
    val objectClauses = synthesizedClausesWithEffects.map(_.value)

    val objectTerm = ObjectTerm(objectClauses.map { case (key, value, _) => ObjectClauseValueTerm(key, value) })
    val objectType = ObjectType(objectClauses.map { case (key, _, ty) => ObjectClauseValueTerm(key, ty) })

    Judge(objectTerm, objectType, combinedEffect)
  }

  def synthesizeBlock(block: Block, effects: Option[Effects]): Judge = {
    val heads: Vector[Stmt] = block.heads.map {
      case stmt: Stmt => stmt
      case expr => ExprStmt(expr, expr.meta)
    }
    val bindings = Bindings.reduce(heads.map(_.bindings))
    ???
  }

  def synthesize(expr: Expr, effects: Option[Effects]): Judge = {
    resolve(expr) match {
      case IntegerLiteral(value, meta) =>
        Judge(AbstractIntTerm.from(value), IntegerType, NoEffect)
      case RationalLiteral(value, meta) =>
        Judge(RationalTerm(value), RationalType, NoEffect)
      case StringLiteral(value, meta) =>
        Judge(StringTerm(value), StringType, NoEffect)
      case SymbolLiteral(value, meta) =>
        Judge(SymbolTerm(value), SymbolType, NoEffect)
      case ListExpr(terms, meta) if terms.isEmpty =>
        Judge(ListTerm(Vector()), ListType(NothingType), NoEffect)
      case ListExpr(terms, meta) =>
        val judges: Vector[Judge] = terms.map { term =>
          synthesize(term, effects)
        }
        val ty = tyFold(judges.map(_.ty))
        val effect = effectFold(judges.map(_.effects))
        Judge(ListTerm(judges.map(_.wellTyped)), ListType(ty), effect)
      case objExpr: ObjectExpr =>
        synthesizeObjectExpr(objExpr, effects)
      case block: Block => (synthesizeBlock(block, effects))
      case expr: Stmt => {
        val err = UnexpectedStmt(expr)
        tyck.report(err)
        Judge(new ErrorTerm(err), UnitType, NoEffect)
      }
      case Identifier(id, meta) => {
        val resolved = localCtx.resolve(id)
        resolved match {
          case Some(CtxItem(name, JudgeNoEffect(wellTyped, ty))) =>
            Judge(name, ty, NoEffect)
          case None =>
            val err = IdentifierNotFoundError(expr)
            tyck.report(err)
            Judge(new ErrorTerm(err), new ErrorTerm(err), NoEffect)
        }
      }
      case f: FunctionExpr => this.synthesizeFunction(f, effects)
      case call: DesaltFunctionCall => this.synthesizeFunctionCall(call, effects)

      case _ =>
        val err = UnsupportedExpressionError(expr)
        tyck.report(err)
        Judge(new ErrorTerm(err), new ErrorTerm(err), NoEffect)
    }
  }

  def checkType(expr: Expr): Judge = inherit(expr, Typeω)
  
  private val coreTycker = CoreTycker(localCtx, tyck.reporter)

  def synthesizeTyTerm(term: Term): JudgeNoEffect = {
    term match {
      case _ => JudgeNoEffect(term, coreTycker.inferTyNoEffect(term)) // TODO
    }
  }

  case class EffectWith[T](effect: Effects, value: T)

  // TODO: maybe incorrect
  def inheritObjectFields(clauses: Vector[ObjectClause], fieldTypes: Vector[ObjectClauseValueTerm], effects: Option[Effects]): EffectWith[Vector[ObjectClauseValueTerm]] = {
    val inheritedClauses = clauses.flatMap { clause =>
      clause match {
        case ObjectExprClauseOnValue(key, value) =>
          val Judge(keyTerm, _, keyEffect) = synthesize(key, effects)
          fieldTypes.find(ft => ft.key == keyTerm) match {
            case Some(fieldType) =>
              val Judge(wellTypedValue, _, valueEffect) = inherit(value, fieldType.value, effects)
              val combinedEffect = effectUnion(keyEffect, valueEffect)
              Some(EffectWith(combinedEffect, ObjectClauseValueTerm(keyTerm, wellTypedValue)))
            case None =>
              tyck.report(FieldNotFoundInObjectTypeError(key, ObjectType(fieldTypes)))
              None
          }
        case _ => throw new IllegalArgumentException("Unexpected clause type, maybe not desalted")
      }
    }

    val combinedEffect = effectFold(inheritedClauses.map(_.effect))
    EffectWith(combinedEffect, inheritedClauses.map(_.value))
  }

  /** part of whnf */
  def normalizeNoEffect(term: Term): Term = {
    term match {
      case Union(xs) => {
        val xs1 = xs.map(x => whnfNoEffect(x))
        Union.from(xs1)
      }
      case wellTyped => wellTyped
    } match {
      case Union(xs) if xs.exists(_.isInstanceOf[AnyType]) =>
        val level = collectLevel(xs)
        AnyType(level)
      case wellTyped => reuse(term, wellTyped)
    }
  }

  def whnfNoEffect(term: Term): Term = {
    val result = normalizeNoEffect(term)
    result match
      case term: MetaTerm => {
        this.walkOption(term) match {
          case Some(j@Judge(wellTyped, ty, effects)) => {
            require(effects.isEmpty)
            whnfNoEffect(wellTyped)
          }
          case None => term
        }
      }
      case _ => result
  }

  def resolve(expr: Expr): Expr = {
    reuse(expr, SimpleDesalt.desugarUnwrap(expr))
  }

  /** possibly apply an implicit conversion */
  def inheritFallbackUnify(judge: Judge, ty: Term, effects: Option[Effects] = None): Judge = {
    val Judge(wellTypedExpr, exprType, exprEffect) = this.unifyEff(effects, judge)
    val ty1 = (unifyTy(ty, exprType))
    Judge(wellTypedExpr, ty1, exprEffect)
  }

  def inheritBySynthesize(expr: Expr, ty: Term, effects: Option[Effects] = None): Judge = {
    val expr1: Expr = (resolve(expr))
    val ty1: Term = whnfNoEffect(ty)
    (expr1, ty1) match {
      case (expr, ty) =>
        inheritFallbackUnify(synthesize(expr, effects), ty, effects)
    }
  }

  def inherit(expr: Expr, ty: Term, effects: Option[Effects] = None): Judge = {
    val expr1: Expr = (resolve(expr))
    val ty1: Term = whnfNoEffect(ty)
    (expr1, ty1) match {
      case (IntegerLiteral(value, meta), IntType) => {
        if (value.isValidInt)
          Judge(IntTerm(value.toInt), IntType, NoEffect)
        else {
          tyck.report(InvalidIntError(expr))
          Judge(IntegerTerm(value.toInt), IntType, NoEffect)
        }
      }
      case (IntegerLiteral(value, meta), NaturalType) => {
        if (value > 0)
          Judge(NaturalTerm(value), NaturalType, NoEffect)
        else {
          tyck.report(InvalidNaturalError(expr))
          Judge(IntegerTerm(value.toInt), NaturalType, NoEffect)
        }
      }
      case (expr, ty@Union(xs)) => {
        def firstTry(x: Self): Judge = x.inheritBySynthesize(expr, ty, effects)

        val tries: Seq[Self => Judge] = xs.map(ty => (x: Self) => x.inheritBySynthesize(expr, ty, effects))
        tryAll((firstTry +: tries).assumeNonEmpty)
      }
      case (objExpr: ObjectExpr, ObjectType(fieldTypes, _)) =>
        val EffectWith(inheritedEffect, inheritedFields) = (inheritObjectFields(clauses = objExpr.clauses, fieldTypes = fieldTypes, effects = effects))
        Judge(ObjectTerm(inheritedFields), ty, inheritedEffect)
      case (ListExpr(terms, meta), lstTy@ListType(ty)) =>
        val checkedTerms: Vector[EffectWith[Term]] = terms.map { term =>
          val Judge(wellTypedTerm, termType, termEffect) = (inherit(term, ty, effects))
          EffectWith(termEffect, wellTypedTerm)
        }
        val effect1 = effectFold(checkedTerms.map(_.effect))
        Judge(ListTerm(checkedTerms.map(_.value)), lstTy, effect1)
      case (expr, ty) => inheritBySynthesize(expr, ty, effects)
    }
  }

  def check(expr: Expr, ty: Option[Term] = None, effects: Option[Effects] = None): Judge = ty match {
    case Some(ty) => inherit(expr, ty, effects)
    case None => {
      val Judge(wellTypedExpr, exprType, exprEffect) = this.unifyEff(effects, synthesize(expr, effects))
      Judge(wellTypedExpr, exprType, exprEffect)
    }
  }

  def zonkCheck(expr: Expr, ty: Option[Term] = None, effects: Option[Effects] = None): Judge = this.zonkMetas(check(expr, ty, effects))
  def zonkInherit(expr: Expr, ty: Term, effects: Option[Effects] = None): Judge = this.zonkMetas(inherit(expr, ty, effects))

  def addConstraint(constraint: MetaConstraint): Unit = {
    tyck.updateState { state =>
      state.copy(constraints = state.constraints :+ constraint)
    }
  }

  def getSolution(meta: MetaTerm): Term = {
    val judge = tyck.getState.subst.getOrElse(meta.uniqId, {
      throw new IllegalStateException(s"MetaTerm ${meta.uniqId} not solved")
    })
    judge.wellTyped
  }
}

object ExprTycker {
  def unifyTy(lhs: Term, rhs: Term, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Term] = {
    Tyck.run(Tycker(ctx, _).unifyTy(lhs, rhs))(state)
  }

  def inherit(expr: Expr, ty: Term, effects: Option[Effects] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    Tyck.run(Tycker(ctx, _).zonkInherit(expr, ty, effects))(state)
  }

  def synthesize(expr: Expr, effects: Option[Effects] = None, state: TyckState = TyckState(), ctx: LocalCtx = LocalCtx.Empty): TyckResult[TyckState, Judge] = {
    Tyck.run(Tycker(ctx, _).zonkCheck(expr, effects=effects))(state)
  }
}
