package chester.tyck

import chester.syntax.core.*

trait EffTycker[Self <: TyckerBase[Self] & TelescopeTycker[Self] & EffTycker[Self]] extends Tycker[Self] {

  def unifyEff(lhs: Option[Effects], rhs: JudgeMaybeEffect): JudgeMaybeEffect = rhs // TODO

  def unifyEff(lhs: Option[Effects], rhs: Judge): Judge = unifyEff(lhs, rhs.toMaybe).get

}
