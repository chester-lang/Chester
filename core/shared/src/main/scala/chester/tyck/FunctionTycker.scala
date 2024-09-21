package chester.tyck

import chester.error.*
import chester.syntax.concrete.*
import chester.syntax.core.*

import scala.util.boundary
import scala.util.boundary.break

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
  
  def synthesizeCall(function: Judge, fTy: FunctionType, telescopes: Vector[DesaltCallingTelescope], effects: Option[Effects]): Judge = {
    val functionTelescopes = fTy.telescope
    val callTelescopes = telescopes

    def determineApplicationCase(): (ApplicationCase, ImplicitTelescopeInfo) = {
      var funcIndex = 0
      var callIndex = 0
      var skippedImplicits = Vector.empty[Int]
      var providedImplicits = Vector.empty[Int]
      boundary{
        while (funcIndex < functionTelescopes.length) {
          val funcTelescope = functionTelescopes(funcIndex)
          val callTelescope = if (callIndex < callTelescopes.length) Some(callTelescopes(callIndex)) else None

          (funcTelescope.implicitly, callTelescope.map(_.implicitly)) match {
            case (false, Some(false)) =>
              // Explicit telescope in both function definition and call
              funcIndex += 1
              callIndex += 1
            case (false, Some(true)) =>
              // Explicit telescope in function definition, but implicit or missing in call
              tyck.report(ExplicitTelescopeRequiredError(callTelescopes(callIndex)))
              return (ApplicationCase.Invalid, ImplicitTelescopeInfo(Vector.empty, Vector.empty))
            case (false, None) =>
              // Partially application
              break()
            case (true, Some(true)) =>
              // Implicit telescope provided in the call
              providedImplicits :+= funcIndex
              funcIndex += 1
              callIndex += 1
            case (true, Some(false)) =>
              // Implicit telescope in function definition, not provided in call
              skippedImplicits :+= funcIndex
              funcIndex += 1
            case (true, None) =>
              if(funcIndex == functionTelescopes.length - 1) {
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

      val implicitInfo = ImplicitTelescopeInfo(skippedImplicits, providedImplicits)

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

      (applicationCase, implicitInfo)
    }

    val (applicationCase, implicitInfo) = determineApplicationCase()

    applicationCase match {
      case ApplicationCase.PartiallyApplied =>
        // TODO: Handle partial application
        ???
      case ApplicationCase.FullyApplied =>
        // TODO: Handle full application
        ???
      case ApplicationCase.OverApplied =>
        // TODO: Handle over-application (more arguments than expected)
        ???
      case ApplicationCase.Invalid =>
        // Error has already been reported, return an error term
        Judge(ErrorTerm(ExplicitTelescopeRequiredError(callTelescopes.head)), ErrorType(ExplicitTelescopeRequiredError(callTelescopes.head)), NoEffect)
    }

    ???
  }

  def synthesizeFunctionCall(call: DesaltFunctionCall, effects: Option[Effects]): Judge = {
    val function = this.synthesize(call.function, effects)
    function.ty match {
      case fty: FunctionType => 
        synthesizeCall(function, fty, call.telescopes, effects)
      case i: Intersection => {
        val fs = i.xs.filter {
          case f: FunctionType => true
          case _ => false
        }
        tryAll(fs.map(fty => _.synthesizeCall(function, fty.asInstanceOf[FunctionType], call.telescopes, effects)))
      }
      case _ => 
        val error = NotAFunctionError(call)
        tyck.report(error)
        Judge(ErrorTerm(error), ErrorType(error), NoEffect)
    }
  }

}

enum ApplicationCase {
  case PartiallyApplied
  case FullyApplied
  case OverApplied
  case Invalid
}

case class ImplicitTelescopeInfo(
  skippedImplicits: Vector[Int],
  providedImplicits: Vector[Int]
)
