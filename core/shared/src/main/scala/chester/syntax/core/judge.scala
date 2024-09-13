package chester.syntax.core

case class Judge(wellTyped: Term, ty: Term, effects: Effects = NoEffect) {
  def toMaybe: JudgeMaybeEffect = JudgeMaybeEffect(wellTyped, ty, Some(effects))
  def substitute(from: Term & HasUniqId, to: Term): Judge = Judge(wellTyped.substitute(from, to), ty.substitute(from, to), effects)
}

case class JudgeNoEffect(wellTyped: Term, ty: Term) {
  implicit def toJudge: Judge = Judge(wellTyped, ty)
}

case class JudgeMaybeEffect(wellTyped: Term, ty: Term, effects: Option[Effects] = None) {
  @throws[NoSuchElementException]
  def get: Judge = Judge(wellTyped, ty, effects.get)
}