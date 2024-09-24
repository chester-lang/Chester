package chester.tyck

import chester.error.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.constraint.collection.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.upickle.given
import chester.utils.*

import scala.util.boundary
import scala.util.boundary.break

trait FunctionTycker[Self <: TyckerBase[Self] & FunctionTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends TyckerTrait[Self] {
  def synthesizeArg(arg: Arg, effects: Option[Effects], cause: Expr): WithCtxEffect[ArgTerm] = {
    val tyJudge = arg.ty.map(this.checkType)
    assert(tyJudge.isEmpty || tyJudge.get.effects.isEmpty)
    val ty = tyJudge.map(_.wellTyped)
    val ty1 = ty.getOrElse(this.genTypeVariable(name = Some(arg.getName + "_t")))
    val default = arg.exprOrDefault.map(this.inherit(_, ty1, effects))
    val id = arg.name match {
      case id: Identifier => id
      case _ => ???
    }
    val idVar = LocalVar.generate(id.name, ty1)
    val newCtx = localCtx.extend(idVar)
    val effect = default.map(_.effects).getOrElse(NoEffect)
    WithCtxEffect(newCtx, effect, ArgTerm(idVar, ty1, default.map(_.wellTyped)))
  }

  def synthesizeDefTelescope(args: Vector[Arg], effects: Option[Effects], cause: Expr): WithCtxEffect[Vector[ArgTerm]] = {
    if (args.flatMap(_.decorations).nonEmpty) tyck.report(UnsupportedDecorationError(cause))
    var checker = this
    var results = Vector.empty[ArgTerm]
    var effect = NoEffect
    for (arg <- args) {
      val WithCtxEffect(newCtx, argEffect, argTerm) = checker.synthesizeArg(arg, effects, cause)
      checker = checker.rec(newCtx)
      results = results :+ argTerm
      effect = this.effectUnion(effect, argEffect)
    }
    WithCtxEffect(checker.localCtx, effect, results)
  }

  def synthesizeTelescopes(telescopes: Vector[DefTelescope], effects: Option[Effects], cause: Expr): WithCtxEffect[Vector[TelescopeTerm]] = {
    if (telescopes.isEmpty) return WithCtxEffect(this.localCtx, NoEffect, Vector.empty)
    var checker = this
    var results = Vector.empty[TelescopeTerm]
    var effect = NoEffect
    for (tele <- telescopes) {
      val WithCtxEffect(newCtx, teleEffect, teleTerm) = checker.synthesizeDefTelescope(tele.args, effects, cause)
      checker = checker.rec(newCtx)
      results = results :+ TelescopeTerm(teleTerm, tele.implicitly)
      effect = this.effectUnion(effect, teleEffect)
    }
    WithCtxEffect(checker.localCtx, effect, results)
  }

  def telescopePrecheck(telescopes: Vector[DefTelescope], cause: Expr): Unit = {
    var ids = telescopes.flatMap(_.args).map(_.getName)
    if (ids.distinct.length != ids.length) {
      tyck.report(DuplicateTelescopeArgError(cause))
    }
    var inits = telescopes.filter(_.args.nonEmpty).flatMap(_.args.init)
    if (inits.exists(_.vararg)) {
      tyck.report(UnsupportedVarargError(cause))
    }
    if (telescopes.exists(_.args.isEmpty)) {
      if (telescopes.length != 1) {
        tyck.report(UnsupportedEmptyTelescopeError(cause))
      }
    }
  }

  def synthesizeFunction(f: FunctionExpr, effects: Option[Effects]): Judge = {
    this.telescopePrecheck(f.telescope, f)
    val effects = f.effect.map(this.checkEffect)
    val WithCtxEffect(newCtx, defaultEff, args) = this.synthesizeTelescopes(f.telescope, effects, f)
    val checker = rec(newCtx)
    val resultTy = f.resultTy.map(checker.checkType)
    assert(resultTy.isEmpty || resultTy.get.effects.isEmpty)
    val body = checker.check(f.body, resultTy.map(_.wellTyped), effects)
    val finalEffects = effects.getOrElse(this.effectUnion(defaultEff, body.effects))
    val funcTy = FunctionType(telescope = args, resultTy = body.ty, finalEffects)
    //val result = Judge(Function(funcTy, body.wellTyped), funcTy, NoEffect)
    this.cleanupFunction(Function(funcTy, body.wellTyped))
  }

  // Definition of TelescopeMatchingResult
  sealed trait TelescopeMatchingResult

  object TelescopeMatchingResult {
    case class Valid(matchedArgs: Vector[Option[CallingArg]]) extends TelescopeMatchingResult

    case object Invalid extends TelescopeMatchingResult
  }

  // Written by AI, TODO - check
  def handleSingleTelescope(
                             telescope: TelescopeTerm,
                             calling: DesaltCallingTelescope,
                           cause: Expr,
                           ): TelescopeMatchingResult = {
    val paramArgs = telescope.args
    val callArgs = calling.args

    // Mapping from parameter names to their positions
    val paramNameToIndex = paramArgs.zipWithIndex.map {
      case (arg, idx) => arg.bind.id -> idx
    }.toMap

    // Initialize structures for matching
    val matchedArgs = new Array[Option[CallingArg]](paramArgs.length)
    var varargIndex: Option[Int] = None

    // Identify vararg parameter
    for ((paramArg, idx) <- paramArgs.zipWithIndex) {
      if (paramArg.vararg) {
        if (varargIndex.isDefined) {
          // Multiple varargs not supported
          tyck.report(MultipleVarargsError(calling))
          return TelescopeMatchingResult.Invalid
        }
        varargIndex = Some(idx)
      }
    }

    // Handle positional arguments
    var positionalIndex = 0
    var callArgIndex = 0
    while (callArgIndex < callArgs.length && positionalIndex < paramArgs.length) {
      val callArg = callArgs(callArgIndex)
      val paramArg = paramArgs(positionalIndex)

      if (callArg.name.isEmpty) {
        if (paramArg.vararg) {
          // Collect all remaining positional arguments into vararg
          val varargArgs = callArgs.drop(callArgIndex).filter(_.name.isEmpty)
          matchedArgs(positionalIndex) = Some(
            CallingArg(expr = Tuple(varargArgs.map(_.expr)), vararg = true)
          )
          callArgIndex = callArgs.length
          positionalIndex += 1
        } else {
          matchedArgs(positionalIndex) = Some(callArg)
          callArgIndex += 1
          positionalIndex += 1
        }
      } else {
        // Named argument encountered, break to handle named arguments
        callArgIndex += 1
      }
    }

    // Handle named arguments
    val namedArgs = callArgs.drop(callArgIndex).filter(_.name.isDefined)
    for (callArg <- namedArgs) {
      val argName = callArg.name.get.name
      paramNameToIndex.get(argName) match {
        case Some(idx) =>
          if (matchedArgs(idx).isDefined) {
            tyck.report(DuplicateArgumentError(cause))
            return TelescopeMatchingResult.Invalid
          } else {
            matchedArgs(idx) = Some(callArg)
          }
        case None =>
          tyck.report(UnknownArgumentError(cause))
          return TelescopeMatchingResult.Invalid
      }
    }

    // Check for missing required arguments
    for ((paramArg, idx) <- paramArgs.zipWithIndex) {
      if (matchedArgs(idx).isEmpty && paramArg.default.isEmpty && !paramArg.vararg) {
        tyck.report(MissingArgumentError(cause))
        return TelescopeMatchingResult.Invalid
      }
    }

    TelescopeMatchingResult.Valid(matchedArgs.toVector)
  }

  def synthesizeCall(function: Judge, fTy: FunctionType, telescopes: Vector[DesaltCallingTelescope], effects: Option[Effects], cause: Expr): Judge = {
    val functionTelescopes = fTy.telescope
    val callTelescopes = telescopes

    def determineApplicationCase(): (ApplicationCase, TelescopeMatchingInfo) = {
      var funcIndex = 0
      var callIndex = 0
      var matchedTelescopes = Vector.empty[(Int, Int)]
      var skippedImplicits = Vector.empty[Int]
      var providedImplicits = Vector.empty[(Int, Int)]

      boundary {
        while (funcIndex < functionTelescopes.length) {
          val funcTelescope = functionTelescopes(funcIndex)
          val callTelescope = if (callIndex < callTelescopes.length) Some(callTelescopes(callIndex)) else None

          (funcTelescope.implicitly, callTelescope.map(_.implicitly)) match {
            case (false, Some(false)) =>
              // Explicit telescope in both function definition and call
              matchedTelescopes :+= (funcIndex, callIndex)
              funcIndex += 1
              callIndex += 1
            case (false, Some(true)) =>
              // Explicit telescope in function definition, but implicit in call
              tyck.report(ExplicitTelescopeRequiredError(callTelescopes(callIndex)))
              return (ApplicationCase.Invalid, TelescopeMatchingInfo(Vector.empty, Vector.empty, Vector.empty, Vector.empty, Vector.empty))
            case (false, None) =>
              // Partially application
              break()
            case (true, Some(true)) =>
              // Implicit telescope provided in the call
              providedImplicits :+= (funcIndex, callIndex)
              funcIndex += 1
              callIndex += 1
            case (true, Some(false)) =>
              // Implicit telescope in function definition, explicit in call (allowed)
              matchedTelescopes :+= (funcIndex, callIndex)
              funcIndex += 1
              callIndex += 1
            case (true, None) =>
              if (funcIndex == functionTelescopes.length - 1) {
                // Implicit telescope in function definition, not provided in call, only allowed in the last telescope
                skippedImplicits :+= funcIndex
                funcIndex += 1
              } else {
                // Partially application
                break()
              }
          }
        }
      }

      val remainingFuncTelescopes = (funcIndex until functionTelescopes.length).toVector
      val extraCallTelescopes = (callIndex until callTelescopes.length).toVector

      val matchingInfo = TelescopeMatchingInfo(
        matchedTelescopes,
        skippedImplicits,
        providedImplicits,
        remainingFuncTelescopes,
        extraCallTelescopes
      )

      val applicationCase =
        if (funcIndex == functionTelescopes.length && callIndex == callTelescopes.length) {
          ApplicationCase.FullyApplied
        } else if (funcIndex < functionTelescopes.length) {
          ApplicationCase.PartiallyApplied
        } else if (callIndex < callTelescopes.length) {
          ApplicationCase.OverApplied
        } else {
          ApplicationCase.Invalid
        }

      (applicationCase, matchingInfo)
    }

    val (applicationCase, matchingInfo) = determineApplicationCase()

    applicationCase match {
      case ApplicationCase.PartiallyApplied =>
        // TODO: Handle partial application
        ???
      case ApplicationCase.FullyApplied =>
        handleFullApplication(function, fTy, matchingInfo, callTelescopes, effects, cause)
      case ApplicationCase.OverApplied =>
        // TODO: Handle over-application (more arguments than expected)
        ???
      case ApplicationCase.Invalid =>
        // Error has already been reported, return an error term
        Judge(ErrorTerm(ExplicitTelescopeRequiredError(callTelescopes.head)), ErrorType(ExplicitTelescopeRequiredError(callTelescopes.head)), NoEffect)
    }
  }

  def handleFullApplication(
                             function: Judge,
                             fTy: FunctionType,
                             matchingInfo: TelescopeMatchingInfo,
                             callTelescopes: Vector[DesaltCallingTelescope],
                             effects: Option[Effects],
                             cause: Expr
                           ): Judge = {
    // Initialize variables
    var checker: Self = this
    var combinedEffect = NoEffect

    // A vector to collect Calling terms for each telescope
    val callingTerms = matchingInfo.matchedTelescopes.map { case (funcIndex, callIndex) =>
      val funcTelescope = fTy.telescope(funcIndex) // Now this is a TelescopeTerm
      val callTelescope = callTelescopes(callIndex)

      handleSingleTelescope(funcTelescope, callTelescope, cause) match {
        case TelescopeMatchingResult.Valid(matchedArgs) =>
          var argTerms = Vector.empty[CallingArgTerm]

          // Process each argument in the telescope
          for ((paramArg, maybeCallArg) <- funcTelescope.args.zip(matchedArgs)) {
            val argResult = maybeCallArg match {
              case Some(callArg) =>
                // Type-check the call argument against the parameter type
                val result = checker.inherit(callArg.expr, paramArg.ty, effects)
                combinedEffect = checker.effectUnion(combinedEffect, result.effects)
                CallingArgTerm(result.wellTyped, callArg.name.map(_.name), callArg.vararg)

              case None =>
                paramArg.default match {
                  case Some(defaultExpr) =>
                    // Use the default value if no argument is provided
                    ???
                  case None =>
                    // Missing required argument; report error
                    val error = MissingArgumentError(cause)
                    tyck.report(error)
                    return Judge(ErrorTerm(error), ErrorType(error), NoEffect)
                }
            }
            // Update the context with the new variable if needed
            checker = checker.rec(checker.localCtx.extend(paramArg.bind))
            argTerms :+= argResult
          }
          // Build the Calling term for this telescope
          Calling(argTerms, funcTelescope.implicitly)

        case TelescopeMatchingResult.Invalid =>
          // Matching failed; an error has already been reported
          val error = InvalidTelescopeError(callTelescope)
          tyck.report(error)
          return Judge(ErrorTerm(error), ErrorType(error), NoEffect)
      }
    }

    // Construct the full application term
    val applicationTerm = FCallTerm(function.wellTyped, callingTerms)
    // The result type is the function's result type
    Judge(applicationTerm, fTy.resultTy, combinedEffect)
  }

  def synthesizeFunctionCall(call: DesaltFunctionCall, effects: Option[Effects]): Judge = {
    val function = this.synthesize(call.function, effects)
    function.ty match {
      case fty: FunctionType =>
        synthesizeCall(function, fty, call.telescopes, effects, call)
      case i: Intersection => {
        val fs = i.xs.filter {
          case f: FunctionType => true
          case _ => false
        }
        tryAll(fs.map(fty => (self: Self) => self.synthesizeCall(function, fty.asInstanceOf[FunctionType], call.telescopes, effects, call)).assumeNonEmpty)
      }
      case meta: MetaTerm =>
        // Handle the MetaTerm case by generating a fresh FunctionType
        val fty = instantiateFunctionTypeFromMeta(meta, call.telescopes, effects, call)
        // Link the meta variable to the generated FunctionType
        this.linkTyOn(meta, fty)
        // Proceed with function type synthesis
        synthesizeCall(function, fty, call.telescopes, effects, call)
      case _ =>
        val error = NotAFunctionError(call)
        tyck.report(error)
        Judge(ErrorTerm(error), ErrorType(error), NoEffect)
    }
  }

  def instantiateFunctionTypeFromMeta(
  meta: MetaTerm,
  telescopes: Vector[DesaltCallingTelescope],
  effects: Option[Effects],
  cause: Expr
): FunctionType = {
  // Generate meta variables for each argument in the telescopes
  val argMetaTypes = telescopes.flatMap { telescope =>
    telescope.args.map { _ =>
      this.genTypeVariable(name = Some("arg"))
    }
  }

  // Generate a meta variable for the return type
  val returnTypeMeta = this.genTypeVariable(name = Some("ReturnType"))

  // Create ArgTerms for the function's parameters
  val argTerms = argMetaTypes.map { argType =>
    val idVar = LocalVar.generate("param", argType)
    ArgTerm(idVar, argType)
  }

  // Create TelescopeTerms
  val telescopeTerms = Vector(TelescopeTerm(argTerms))

  // Generate the function's effect (could be NoEffect or another meta variable)
  val functionEffect = effects.getOrElse(NoEffect)

  // Construct the FunctionType
  FunctionType(telescope = telescopeTerms, resultTy = returnTypeMeta, effect = functionEffect)
}
}

enum ApplicationCase {
  case PartiallyApplied
  case FullyApplied
  case OverApplied
  case Invalid
}

case class TelescopeMatchingInfo(
                                  matchedTelescopes: Vector[(Int, Int)], // (funcIndex, callIndex) for matched telescopes
                                  skippedImplicits: Vector[Int], // funcIndex of skipped implicit telescopes
                                  providedImplicits: Vector[(Int, Int)], // (funcIndex, callIndex) of provided implicit telescopes
                                  remainingFuncTelescopes: Vector[Int], // funcIndex of remaining function telescopes (for partial application)
                                  extraCallTelescopes: Vector[Int] // callIndex of extra call telescopes (for over-application)
                                )
