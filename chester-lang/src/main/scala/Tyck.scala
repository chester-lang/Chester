package chester.lang

import term.*
import ast.*


class TyckMutableState{

}


case class The(expr: Term, itsType: Term, effects: List[Term])


class Tyck(state: TyckMutableState) {

  def infer(ast: AST): The = ast match {
    case AnnotationAST(expr, itsType, _) => {
      val typeTerm = ???
      check(expr, typeTerm)
    }
    case _ => ???
  }
  def check(ast: AST, itsType: Term): The = ???
}
