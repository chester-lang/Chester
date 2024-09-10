package chester.syntax.core

case class Judge(wellTyped: Term, ty: Term, effect: Effects = NoEffect)

case class JudgeNoEffect(wellTyped: Term, ty: Term)

case class JudgeMaybeEffect(wellTyped: Term, ty: Term, effect: Option[Effects] = None)