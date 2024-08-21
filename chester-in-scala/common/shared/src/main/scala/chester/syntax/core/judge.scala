package chester.syntax.core

case class Judge(wellTyped: Term, ty: Term, effect: Term = NoEffect)

case class JudgeNoEffect(wellTyped: Term, ty: Term)
