package chester.syntax.core

case class Judge(wellTyped: Term, ty: Term, effect: Effects = NoEffect) {
  def toMaybe: JudgeMaybeEffect = JudgeMaybeEffect(wellTyped, ty, Some(effect))
}

case class JudgeNoEffect(wellTyped: Term, ty: Term) {
  implicit def toJudge: Judge = Judge(wellTyped, ty)
}

case class JudgeMaybeEffect(wellTyped: Term, ty: Term, effect: Option[Effects] = None) {
  @throws[NoSuchElementException]
  def get: Judge = Judge(wellTyped, ty, effect.get)
}