package chester.tyck

import chester.error.EffectUnifyError
import chester.syntax.concrete.Expr
import chester.syntax.core.*

trait EffTycker[Self <: TyckerBase[Self] & TelescopeTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends Tycker[Self] {

  def checkEffect(effectExpr: Expr): Effects = NoEffect // TODO

  def unifyEff(lhs: Option[Effects], rhs: Judge): Judge =
    if (lhs.isEmpty) rhs
    else unifyEff(lhs.get, rhs)

  def unifyEff(lhs: Effects, rhs: Judge): Judge = {
    val rhsEffects = rhs.effects
    val errored = if (!lhs.containAll(rhsEffects.getEffects)) {
      tyck.report(EffectUnifyError(lhs, rhs))
      true
    } else {
      false
    }

    def fallback[A](x: A): A = {
      assert(errored)
      x
    }

    val rebuiltEffects = rhsEffects.mapOnVars((effect, names) => Vector(lhs.lookup(effect).getOrElse(fallback(names)).head))
    var result = rhs.copy(effects = rebuiltEffects)
    for ((effect, names) <- rhsEffects.effects) {
      for (name <- names) {
        result = result.substitute(name, rebuiltEffects.lookup(effect).getOrElse(fallback(names)).head)
      }
    }
    result
  }

  def cleanupFunction(function: Function): Judge = {
    val oldEffects = function.ty.effects
    val body = cleanupEffects(Judge(function.body, function.ty.resultTy, function.ty.effects))
    val effects = body.effects
    val args = function.ty.telescope.map(telescope => telescope.copy(args = telescope.args.map(arg => arg.copy(default = arg.default.map(default => unifyEff(effects, default, arg.ty, oldEffects))))))
    val resultTy = unifyEff(effects, function.ty.resultTy, this.synthesizeTyTerm(function.ty.resultTy).ty, oldEffects)
    Judge(function.copy(ty = function.ty.copy(telescope = args, resultTy = resultTy), body = body.wellTyped), resultTy)
  }

  def unifyEff(effects: Effects, term: Term, ty: Term, oldEffects: Effects): Term = {
    unifyEff(effects, Judge(term, ty, oldEffects)).wellTyped
  }

  /** do cleanup on Effects to only use one variable for an effect */
  def cleanupEffects(judge: Judge): Judge = {
    val newEffects = Effects.unchecked(judge.effects.effects.map { case (effect, names) =>
      effect -> Vector(names.head)
    })
    var wellTyped = judge.wellTyped
    var ty = judge.ty
    for ((effect, names) <- judge.effects.effects; name <- names.tail) {
      wellTyped = wellTyped.substitute(name, names.head)
      ty = ty.substitute(name, names.head)
    }
    Judge(wellTyped, ty, newEffects)
  }
}
