package chester.tyck

import chester.error._
import chester.syntax.concrete._
import chester.syntax.core._

trait FunctionTycker[Self <: TyckerBase[Self] & FunctionTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends TyckerTrait[Self] {
  def synthesizeArg(arg: Arg, effects: Option[Effects], cause: Expr): WithCtxEffect[ArgTerm] = {
    val tyJudge = arg.ty.map(this.checkType)
    assert(tyJudge.isEmpty || tyJudge.get.effects == NoEffect)
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
    if(telescopes.isEmpty) return WithCtxEffect(this.localCtx, NoEffect, Vector.empty)
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
    assert(resultTy.isEmpty || resultTy.get.effects == NoEffect)
    val body = checker.check(f.body, resultTy.map(_.wellTyped), effects)
    val finalEffects = effects.getOrElse(this.effectUnion(defaultEff, body.effects))
    val funcTy = FunctionType(telescope = args, resultTy = body.ty, finalEffects)
    //val result = Judge(Function(funcTy, body.wellTyped), funcTy, NoEffect)
    this.cleanupFunction(Function(funcTy, body.wellTyped))
  }

  def checkCallingTelescope(f: FunctionType, telescope: Vector[MaybeTelescope], effects: Option[Effects]) = ???

  def synthesizeFunctionCall(call: DesaltFunctionCall, effects: Option[Effects]): Judge = {
    val function = this.synthesize(call.function, effects)
    function.ty match {
      case fty: FunctionType => 
        checkFunctionCall(fty, call.telescopes, effects, call).getOrElse {
          val error = FunctionCallError.NoMatchingSignature(call)
          tyck.report(error)
          Judge(ErrorTerm(error), ErrorType(error), NoEffect)
        }
      case i: Intersection => {
        val fs = i.xs.collect { case f: FunctionType => f }
        fs.map(fty => checkFunctionCall(fty, call.telescopes, effects, call))
          .find(_.isDefined)
          .flatten
          .getOrElse {
            val error = FunctionCallError.NoMatchingSignature(call)
            tyck.report(error)
            Judge(ErrorTerm(error), ErrorType(error), NoEffect)
          }
      }
      case _ => 
        val error = FunctionCallError.ExpectedFunctionType(call, function.ty)
        tyck.report(error)
        Judge(ErrorTerm(error), ErrorType(error), NoEffect)
    }
  }

  private def checkFunctionCall(fty: FunctionType, telescopes: Vector[DesaltCallingTelescope], effects: Option[Effects], call: DesaltFunctionCall): Option[Judge] = {
    val args = telescopes.flatMap(_.args)
    
    // Check if there are any duplicate named arguments
    val namedArgs = args.flatMap(_.name)
    if (namedArgs.distinct.length != namedArgs.length) {
      tyck.report(FunctionCallError.DuplicateNamedArguments(call))
      return None
    }

    // Match arguments to function parameters
    val matchedArgs = matchArguments(fty.telescope, args, call)
    
    if (matchedArgs.isEmpty) {
      return None
    }

    val (matchedArgsResult, remainingArgs) = matchedArgs.get

    // Check if there are any unmatched arguments
    if (remainingArgs.nonEmpty) {
      tyck.report(FunctionCallError.UnmatchedArguments(call, remainingArgs.map(_.expr)))
      return None
    }

    // Type check each argument
    val checkedArgs = matchedArgsResult.map { case (param, arg) =>
      this.inherit(arg.expr, param.ty, effects)
    }

    // If any argument fails type checking, return None
    if (checkedArgs.exists(_.ty.isInstanceOf[ErrorType])) {
      return None
    }

    // All arguments type checked successfully
    val argJudges = checkedArgs
    
    // Calculate the final effect
    val finalEffect = effects.getOrElse(argJudges.foldLeft(NoEffect)((acc, judge) => this.effectUnion(acc, judge.effects)))

    // Create the result term and type
    val resultTerm = FCallTerm(function.wellTyped, Vector(Calling(argJudges.map(judge => CallingArg(judge.wellTyped)))))
    val resultType = fty.resultTy

    Some(Judge(resultTerm, resultType, finalEffect))
  }

  private def matchArguments(params: Vector[ArgTerm], args: Vector[chester.syntax.core.CallingArg], call: DesaltFunctionCall): Option[(Vector[(ArgTerm, chester.syntax.core.CallingArg)], Vector[chester.syntax.core.CallingArg])] = {
    var remainingParams = params
    var remainingArgs = args
    var matchedArgs = Vector.empty[(ArgTerm, chester.syntax.core.CallingArg)]
    var varargParam: Option[ArgTerm] = None

    // First, match named arguments
    val (namedArgs, unnamedArgs) = remainingArgs.partition(_.name.isDefined)
    for (arg <- namedArgs) {
      remainingParams.find(_.bind.name == arg.name.get) match {
        case Some(param) =>
          matchedArgs = matchedArgs :+ (param, arg)
          remainingParams = remainingParams.filter(_ != param)
        case None =>
          tyck.report(FunctionCallError.NoMatchingParameter(call, arg.name.get))
          return None
      }
    }
    remainingArgs = unnamedArgs

    // Then, match positional arguments
    for (param <- remainingParams) {
      if (param.vararg) {
        varargParam = Some(param)
      } else if (remainingArgs.isEmpty) {
        if (param.default.isEmpty) {
          tyck.report(FunctionCallError.MissingArgument(call, param.bind.name))
          return None
        }
      } else {
        val arg = remainingArgs.head
        matchedArgs = matchedArgs :+ (param, arg)
        remainingArgs = remainingArgs.tail
      }
    }

    // Handle vararg parameter
    varargParam match {
      case Some(param) =>
        matchedArgs = matchedArgs ++ remainingArgs.map(arg => (param, arg))
        remainingArgs = Vector.empty
      case None =>
        // If there's no vararg parameter, all remaining args are unmatched
    }

    Some((matchedArgs, remainingArgs))
  }

}
