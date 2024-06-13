package chester.lang.reduce

import chester.lang.term.*
import chester.lang.ast.*


class TyckState{

}


case class The(expr: Term, itsType: Term, effects: List[Term])


class Tyck(state: TyckState) {

  def infer(context: LocalContext, ast: AST): The = ast match {
    case AnnotationAST(expr, itsType, _) => {
      val typeTerm = ???
      check(context, expr, typeTerm)
    }
    case _ => ???
  }
  def check(context: LocalContext, ast: AST, itsType: Term): The = ???
}
