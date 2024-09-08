package chester.syntax.core

case class Judge(wellTyped: Term, ty: Term, effect: Term = NoEffect)

case class JudgeNoEffect(wellTyped: Term, ty: Term)

case class JudgeMaybeEffect(wellTyped: Term, ty: Term, effect: Option[Term] = None)