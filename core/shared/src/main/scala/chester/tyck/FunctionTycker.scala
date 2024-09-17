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
      case fty: FunctionType => ???
      case i: Intersection => {
        val fs = i.xs.filter {
          case f: FunctionType => true
          case _ => false
        }
        ???
      }
      case _ => ???
    }
  }

}
