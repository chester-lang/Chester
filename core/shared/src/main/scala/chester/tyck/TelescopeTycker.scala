package chester.tyck

import chester.error._
import chester.syntax.concrete._
import chester.syntax.core._

trait TelescopeTycker[Self <: TyckerBase[Self] & TelescopeTycker[Self] & EffTycker[Self]] extends Tycker[Self] {
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
}
