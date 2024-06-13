package chester.lang

import term.*
import ast.*


class TyckMutableState{

}


case class The(expr: Term, itsType: Term, effects: List[Term])


class Tyck(state: TyckMutableState) {

  def infer(context: Context, ast: AST): The = ast match {
    case AnnotationAST(expr, itsType, _) => {
      val typeTerm = ???
      check(context, expr, typeTerm)
    }
    case _ => ???
  }
  def check(context: Context, ast: AST, itsType: Term): The = ???
}
