package chester.tyck

import chester.error.*
import chester.resolve.{SimpleDesalt, resolveOpSeq}
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.core.CoreTycker
import io.github.iltotore.iron.constraint.numeric.*
import chester.utils.*
import spire.math.Trilean
import spire.math.Trilean.{False, True, Unknown}

import scala.language.implicitConversions

trait TyckerBase[Self <: TyckerBase[Self] & FunctionTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends TyckerTrait[Self] {
  implicit val reporter1: Reporter[TyckProblem] = tyck.reporter

  def superTypes(ty: Term): Option[Vector[Term]] = {
    ty match {
      case Intersection(ts) => Some(ts)
      case _ => None
    }
  }

  def compareTy(lhs: Term, rhs: Term): Trilean = {
    val superType1 = whnfNoEffect(lhs) // Supertype
    val subType1 = whnfNoEffect(rhs) // Subtype
    if (subType1 == superType1) True
    else (superType1, subType1) match {
      case (AnyType(_), _) => True // AnyType is the top type
      case (Type(levelSuper), Type(levelSub)) =>
        compareLevel(levelSuper, levelSub)
      case _ => Unknown
    }
  }

  def compareLevel(lhsLevel: Term, rhsLevel: Term): Trilean = {
    (lhsLevel, rhsLevel) match {
      case (LevelFinite(AbstractIntTerm(vSuper)), LevelFinite(AbstractIntTerm(vSub))) =>
        if (vSub <= vSuper) True else False
      case (Levelω, _) => True // Levelω is the top level; any level is a subtype of Levelω
      case (_, Levelω) =>
        if (lhsLevel == Levelω) True else False // Levelω is only a subtype of itself
      case _ => Unknown
    }
  }

  def isDefined(x: MetaTerm): Boolean = {
    val state = tyck.getState
    state.subst.isDefined(x)
  }

  def linkTy(from: MetaTerm, to: Term): Unit = link(from, synthesizeTyTerm(to).toJudge)

  def link(from: MetaTerm, to: Judge): Unit = ???

  def linkTyOn(meta: MetaTerm, ty: Term): Unit = {
    tyck.updateSubst(meta.uniqId, Judge(ty, Typeω, NoEffect))
  }
   def resolveKnownTerm(term: Term): Term = term match {
     case varCall: HasUniqId =>
       localCtx.ctx.getKnownJudge(varCall.uniqId) match {
         case Some(judge) => judge.wellTyped
         case None        => varCall
       }
     case _ => term
   }
  /** assume a subtype relationship and get a subtype (rhs) back */
  def unifyTy(lhs: Term, rhs: Term, failed: => Term = null): Term = {
    val lhs1 = whnfNoEffect(lhs)
    val rhs1 = whnfNoEffect(rhs)
    if (lhs1 == rhs1) rhs1
    else (lhs1, rhs1) match {
      case (lhsVar: HasUniqId, _) if !lhsVar.isInstanceOf[MetaTerm] =>
        resolveAndUnifyLhs(lhsVar, rhs1, lhs, failed)

      case (_, rhsVar: HasUniqId) if !rhsVar.isInstanceOf[MetaTerm] =>
        resolveAndUnifyRhs(rhsVar, lhs1, rhs, failed)

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
      case (Type(levelSuper), Type(levelSub)) =>
        compareLevel(levelSuper, levelSub) match {
          case True => Type(levelSuper)
          case False =>
            if (failed != null) failed else {
              val err = UnifyFailedError(rhs = rhs1, lhs = lhs1)
              tyck.report(err)
              new ErrorTerm(err)
            }
          case Unknown =>
            // Handle unknown comparison, possibly by introducing a constraint or reporting an error
            if (failed != null) failed else {
              val err = UnifyFailedError(rhs = rhs1, lhs = lhs1)
              tyck.report(err)
              new ErrorTerm(err)
            }
        }
      case (superType, subType) =>
        if (failed != null) failed else {
          val err = UnifyFailedError(rhs = subType, lhs = superType)
          tyck.report(err)
          new ErrorTerm(err)
        }
    }
  }

  def resolveAndUnifyLhs(lhsVar: HasUniqId & Term, rhs: Term, originalLhs: Term, failed: => Term): Term = {
    localCtx.ctx.getKnownJudge(lhsVar.uniqId) match {
      case Some(judge) =>
        val resolvedLhs = judge.wellTyped
        val result = unifyTy(resolvedLhs, rhs, failed = failed)
        if (result.isInstanceOf[ErrorTerm]) result else originalLhs
      case None =>
        failedOrError(lhsVar, rhs, failed)
    }
  }

  def resolveAndUnifyRhs(rhsVar: HasUniqId & Term, lhs: Term, originalRhs: Term, failed: => Term): Term = {
    localCtx.ctx.getKnownJudge(rhsVar.uniqId) match {
      case Some(judge) =>
        val resolvedRhs = judge.wellTyped
        val result = unifyTy(lhs, resolvedRhs, failed = failed)
        if (result.isInstanceOf[ErrorTerm]) result else originalRhs
      case None =>
        failedOrError(lhs, rhsVar, failed)
    }
  }

  def failedOrError(lhs: Term, rhs: Term, failed: => Term): Term = {
    if (failed != null) failed
    else {
      val err = UnifyFailedError(lhs = lhs, rhs = rhs)
      tyck.report(err)
      new ErrorTerm(err)
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
        case (LevelFinite(AbstractIntTerm(v1)), LevelFinite(AbstractIntTerm(v2))) =>
          LevelFinite(AbstractIntTerm.from(v1.max(v2)))
        case (levelFinite: LevelFinite, _) =>
          Levelω // If other level is not finite, result is Levelω
        case (_, levelFinite: LevelFinite) =>
          Levelω // If other level is not finite, result is Levelω
        case _ =>
          Levelω // Default to Levelω if levels are incomparable
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

  // Helper functions to collect all identifiers used in an expression
  // TODO: correctly implement this. This is a naive implementation that collects all identifiers
  def traverse(identifiers: collection.mutable.Set[Name], e: Expr): Unit = e match {
    case Identifier(name, _) =>
      identifiers += name
    case FunctionExpr(telescope, resultTy, body, _, _) =>
      telescope.foreach(_.args.foreach(arg => traverse(identifiers, arg.name)))
      resultTy.foreach(_.inspect(traverse(identifiers,_)))
      body.foreach(_.inspect(traverse(identifiers,_)))
    case Block(heads, lastExpr, _) =>
      heads.foreach {
        case stmt: Stmt => traverseStmt(identifiers, stmt)
        case expr: Expr => traverse(identifiers, expr)
      }
      lastExpr.foreach(_.inspect(traverse(identifiers,_)))
    case _ =>
      e.inspect(traverse(identifiers,_))
  }
  def traverseStmt(identifiers: collection.mutable.Set[Name], stmt: Stmt): Unit = stmt match {
    case LetDefStmt(_, defined, tyOpt, bodyOpt, _, _) =>
      defined.bindings.foreach(b => identifiers += b.name)
      tyOpt.foreach(traverse(identifiers,_))
      bodyOpt.foreach(traverse(identifiers,_))
    case ExprStmt(expr, _) => traverse(identifiers,expr)
    case _ => ()
  }
  def collectIdentifiers(expr: Expr): Set[Name] = {
    val identifiers = collection.mutable.Set[Name]()
  
    traverse(identifiers, expr)
    identifiers.toSet
  }
  def collectIdentifiersInStmt(stmt: Stmt): Set[Name] = stmt match {
    case ExprStmt(expr, _) => collectIdentifiers(expr)
    case LetDefStmt(_, _, tyOpt, bodyOpt, _, _) =>
      tyOpt.map(collectIdentifiers).getOrElse(Set.empty) ++
        bodyOpt.map(collectIdentifiers).getOrElse(Set.empty)
    case _ => Set.empty
  }
  def synthesizeBlock(block: Block, effects: Option[Effects]): Judge = {
    val scopeId = UniqId.generate
    var checker = this.copy(localCtx = this.localCtx.enterScope(scopeId))

    // Convert expressions to ExprStmt and collect all statements
    val heads: Vector[Stmt] = block.heads.map {
      case stmt: Stmt => stmt
      case expr => ExprStmt(expr, expr.meta)
    }

    // First pass: Collect all `def` bindings and detect forward references
    val defBindings = collection.mutable.Map[Name, (Option[Term], UniqId, LetDefStmt)]()
    val usedNames = collection.mutable.Set[Name]()
    val allNames = collection.mutable.Set[Name]()
    var foundForwardReferences = false

    // Collect all defined names (to detect uses)
    for (stmt <- heads) {
      stmt match {
        case LetDefStmt(LetDefType.Def, defined, _, _, _, _) =>
          val name = defined.bindings.headOption.map(_.name).getOrElse("unknown")
          allNames += name
        case _ => ()
      }
    }

    // Analyze statements to find forward references
    for ((stmt, index) <- heads.zipWithIndex) {
      stmt match {
        case letDef@LetDefStmt(LetDefType.Def, defined, tyOpt, _, _, _) =>
          val name = defined.bindings.headOption.map(_.name).getOrElse("unknown")
          val varId = UniqId.generate
          val tyOptTerm = tyOpt.map(synthesize(_, Some(NoEffect)).wellTyped)
          defBindings(name) = (tyOptTerm, varId, letDef)

          // Check if the def is used before its definition
          val remainingStmts = heads.slice(index + 1, heads.length)
          val usedInRemaining = remainingStmts.exists { stmt =>
            collectIdentifiersInStmt(stmt).contains(name)
          }

          if (usedInRemaining) {
            usedNames += name
            foundForwardReferences = true
          }
        case _ => ()
      }
    }

    // Second pass: Process statements
    var effect = NoEffect
    var localCtx = checker.localCtx
    var stmts: Vector[StmtTerm] = Vector.empty

    // Update defBindings with actual types if needed
    for ((name, (tyOpt, varId, letDef)) <- defBindings) {
      val ty = tyOpt match {
        case Some(_) => // Type annotation exists
          tyOpt
        case None => // Type annotation missing
          if (usedNames.contains(name)) {
            // Forward referenced def without type annotation
            val err = MissingTypeAnnotationError(Identifier(name))
            tyck.report(err)
            return Judge(ErrorTerm(err), UnitType, NoEffect)
          } else {
            None // Type will be inferred when processing the def body
          }
      }
      // Add to local context with a placeholder type if necessary
      val placeholderTy = ty.getOrElse(this.genTypeVariable())
      val idVar = LocalVar(name, placeholderTy, varId)
      localCtx = localCtx.extend(idVar)
      checker = checker.copy(localCtx = localCtx)
      defBindings(name) = (ty, varId, letDef) // Update with actual type

      // **New code to record symbol definitions**
      letDef.meta.flatMap(_.sourcePos).foreach { position =>
        val symbolInfo = SymbolInformation(
          uniqId = varId,
          name = name,
          definitionPos = position,
          scopePath = localCtx.scopePath
        )
        
        tyck.updateState { state =>
          val updatedSymbols = state.symbols + symbolInfo
          state.copy(symbols = updatedSymbols)
        }
      }
    }

    // Process statements in order
    for (stmt <- heads) {
      stmt match {
        case LetDefStmt(kind, defined, tyOpt, bodyOpt, _, _) =>
          val name = defined.bindings.headOption.map(_.name).getOrElse("unknown")

          kind match {
            case LetDefType.Let =>
              // Process let binding
              val ty = tyOpt match {
                case Some(tyExpr) =>
                  val Judge(wellTypedTy, _, _) = checker.checkType(tyExpr.asInstanceOf[Expr])
                  wellTypedTy
                case None =>
                  bodyOpt match {
                    case Some(expr) =>
                      val judge = checker.synthesize(expr, effects)
                      effect = checker.effectUnion(effect, judge.effects)
                      judge.ty
                    case None =>
                      val err = MissingTypeAnnotationError(Identifier(name))
                      tyck.report(err)
                      return Judge(ErrorTerm(err), UnitType, NoEffect)
                  }
              }

              // Typecheck the body
              val body = bodyOpt match {
                case Some(expr) =>
                  val judge = checker.inherit(expr, ty, effects)
                  effect = checker.effectUnion(effect, judge.effects)
                  judge.wellTyped
                case None =>
                  val err = MissingBodyError(Identifier(name))
                  tyck.report(err)
                  return Judge(ErrorTerm(err), UnitType, NoEffect)
              }

              // Update local context with new let binding
              val varId = UniqId.generate
              val idVar = LocalVar(name, ty, varId)
              localCtx = localCtx.extend(idVar)
              // Add the known judgment to knownMap
              localCtx = localCtx.addKnownJudge(varId, JudgeNoEffect(body, ty))
              checker = checker.copy(localCtx = localCtx)

              stmts = stmts :+ LetStmtTerm(name, body, ty)

            case LetDefType.Def =>
              // Process def binding
              val (tyAnnotationOpt, varId, _) = defBindings(name)
              val ty = tyAnnotationOpt match {
                case Some(tyExpr) =>
                  tyExpr
                case None =>
                  bodyOpt match {
                    case Some(expr) =>
                      val judge = checker.synthesize(expr, effects)
                      effect = checker.effectUnion(effect, judge.effects)
                      judge.ty
                    case None =>
                      val err = MissingTypeAnnotationError(Identifier(name))
                      tyck.report(err)
                      return Judge(ErrorTerm(err), UnitType, NoEffect)
                  }
              }

              // Typecheck the body
              val body = bodyOpt match {
                case Some(expr) =>
                  val judge = checker.inherit(expr, ty, effects)
                  effect = checker.effectUnion(effect, judge.effects)
                  judge.wellTyped
                case None =>
                  val err = MissingBodyError(Identifier(name))
                  tyck.report(err)
                  return Judge(ErrorTerm(err), UnitType, NoEffect)
              }

              // Update the substitution for the def's MetaTerm if needed
              if (tyAnnotationOpt.isEmpty) {
                // Update the type in localCtx and add to knownMap
                val idVar = LocalVar(name, ty, varId)
                localCtx = localCtx.extendOrSet(idVar)
                localCtx = localCtx.addKnownJudge(varId, JudgeNoEffect(body, ty))
                checker = checker.copy(localCtx = localCtx)
              }

              stmts = stmts :+ DefStmtTerm(name, body, ty)
          }

        case ExprStmt(expr, _) =>
          val judge = checker.synthesize(expr, effects)
          effect = checker.effectUnion(effect, judge.effects)
          stmts = stmts :+ ExprStmtTerm(judge.wellTyped)

        case _ => // Handle other statement types if needed
          ()
      }
    }

    // Process the last expression in the block if it exists
    val (lastExpr, blockTy) = block.tail match {
      case Some(lastExpr) =>
        val judge = checker.synthesize(lastExpr, effects)
        effect = checker.effectUnion(effect, judge.effects)
        (judge.wellTyped, judge.ty)
      case None =>
        (UnitTerm, UnitType)
    }

    val blockTerm = BlockTerm(stmts, lastExpr)
    Judge(blockTerm, blockTy, effect)
  }

  def synthesize(expr: Expr, effects: Option[Effects]): Judge = {
    resolve(expr) match {
      case Tuple(exprTerms, meta) =>
        val checkedTerms: Vector[Judge] = exprTerms.map { term =>
          synthesize(term, effects)
        }
        val types = checkedTerms.map(_.ty)
        val tupleType = TupleType(types)
        val effect = effectFold(checkedTerms.map(_.effects))
        val wellTypedTerms = checkedTerms.map(_.wellTyped)
        Judge(TupleTerm(wellTypedTerms), tupleType, effect)

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
      case Identifier(id, meta) =>
        localCtx.resolve(id) match {
          case Some(CtxItem(name, JudgeNoEffect(wellTyped, ty))) =>
            // **New code to record symbol references**
            meta.flatMap(_.sourcePos).foreach { position =>
              tyck.updateState { state =>
                val updatedSymbols = state.symbols.map { sym =>
                  if (sym.uniqId == name.uniqId && sym.scopePath == localCtx.scopePath) {
                    sym.copy(references = sym.references + position)
                  } else {
                    sym
                  }
                }
                state.copy(symbols = updatedSymbols)
              }
            }
            // Proceed with existing logic
            Judge(name, ty, NoEffect)
          case None =>
            val err = IdentifierNotFoundError(expr)
            tyck.report(err)
            Judge(new ErrorTerm(err), new ErrorTerm(err), NoEffect)
        }
      case f: FunctionExpr => this.synthesizeFunction(f, effects)
      case call: DesaltFunctionCall => this.synthesizeFunctionCall(call, effects)

      case expr =>
        val err = UnsupportedExpressionError(expr)
        tyck.report(err)
        Judge(new ErrorTerm(err), new ErrorTerm(err), NoEffect)
    }
  }

  def checkType(expr: Expr): Judge = inherit(expr, Typeω)

  private val coreTycker = CoreTycker(localCtx, tyck.reporter)

  def synthesizeTyTerm(term: Term): JudgeNoEffect = {
    term match {
      case _ => JudgeNoEffect(term, coreTycker.inferNoEffect(term)) // TODO
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
    val result = SimpleDesalt.desugarUnwrap(expr) match {
      case opseq: OpSeq => {
        val result = resolveOpSeq(tyck.reporter, localCtx.ctx.operators, opseq)
        result
      }
      case default => default
    }
    reuse(expr, result)
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
    val ty2: Term = ty1 match {
      case i: HasUniqId => localCtx.ctx.getKnownJudge(i.uniqId) match {
        case Some(judge) => judge.wellTyped
        case None => ty1
      }
      case _ => ty1
    }
    (expr1, ty2) match {
      case (Tuple(exprTerms, meta), tupleType@TupleType(typeTerms)) =>
        if (exprTerms.length != typeTerms.length) {
          val err = TupleArityMismatchError(exprTerms.length, typeTerms.length, expr)
          tyck.report(err)
          Judge(ErrorTerm(err), ty1, NoEffect)
        } else {
          val checkedTerms: Vector[Judge] = exprTerms.zip(typeTerms).map { case (exprTerm, expectedType) =>
            inherit(exprTerm, expectedType)
          }
          val effect = effectFold(checkedTerms.map(_.effects))
          val wellTypedTerms = checkedTerms.map(_.wellTyped)
          Judge(TupleTerm(wellTypedTerms), ty1, effect)
        }
      case (IntegerLiteral(value, meta), IntType) => {
        if (value.isValidInt)
          Judge(IntTerm(value.toInt), ty1, NoEffect)
        else {
          tyck.report(InvalidIntError(expr))
          Judge(IntegerTerm(value.toInt), ty1, NoEffect)
        }
      }
      case (IntegerLiteral(value, meta), NaturalType) => {
        if (value > 0)
          Judge(NaturalTerm(value), ty1, NoEffect)
        else {
          tyck.report(InvalidNaturalError(expr))
          Judge(IntegerTerm(value.toInt), ty1, NoEffect)
        }
      }
      case (expr, ty@Union(xs)) => {
        def firstTry(x: Self): Judge = x.inheritBySynthesize(expr, ty1, effects)

        val tries: Seq[Self => Judge] = xs.map(ty => (x: Self) => x.inheritBySynthesize(expr, ty, effects))
        tryAll((firstTry +: tries).assumeNonEmpty)
      }
      case (objExpr: ObjectExpr, ObjectType(fieldTypes, _)) =>
        val EffectWith(inheritedEffect, inheritedFields) = (inheritObjectFields(clauses = objExpr.clauses, fieldTypes = fieldTypes, effects = effects))
        Judge(ObjectTerm(inheritedFields), ty1, inheritedEffect)
      case (ListExpr(terms, meta), lstTy@ListType(ty)) =>
        val checkedTerms: Vector[EffectWith[Term]] = terms.map { term =>
          val Judge(wellTypedTerm, termType, termEffect) = (inherit(term, ty, effects))
          EffectWith(termEffect, wellTypedTerm)
        }
        val effect1 = effectFold(checkedTerms.map(_.effect))
        Judge(ListTerm(checkedTerms.map(_.value)), ty1, effect1)
      case (expr, ty) => inheritBySynthesize(expr, ty1, effects)
    }
  }

  def check(expr: Expr, ty: Option[Term] = None, effects: Option[Effects] = None): Judge = ty match {
    case Some(ty) => inherit(expr, ty, effects)
    case None => {
      val Judge(wellTypedExpr, exprType, exprEffect) = this.unifyEff(effects, synthesize(expr, effects = effects))
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
    Tyck.run(Tycker(ctx, _).zonkCheck(expr, effects = effects))(state)
  }
}
