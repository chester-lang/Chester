package chester.syntax.core

case class Judge(wellTyped: Term, ty: Term, effect: Term)

case class JudgeNoEffect(wellTyped: Term, ty: Term)
