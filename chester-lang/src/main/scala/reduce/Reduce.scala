package chester.lang.reduce

import chester.lang.Context
import chester.lang.ast.AST
import chester.lang.term.Term

case class RuntimeError()

type Result[A] = Either[RuntimeError, A]

object Reduce {
  def apply(context: Context, expr: Term): Term = expr match
    case _ => ???
}



def parseAST(context: Context, astInTerm: Term): Result[AST] = ???