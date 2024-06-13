package chester.lang.reduce

import chester.lang.term.*
import chester.lang.ast.*


class TyckState{

}


case class The(expr: Term, itsType: Term, effects: Iterable[Term])


class Tyck(state: TyckState) {

  def infer(context: LocalContext, ast: AST): Result[The] = ast match {
    case AnnotationAST(expr, itsType, _, effectsAST) => {
      val typeTerm = ???
      
      check(context, expr, typeTerm, ???)
    }
    case IdentifierAST(location, name) => context.getType(name) match {
      case Some(itsType) => Right(The(IdentifierTerm(location, name), itsType, Vector()))
      case None => Left(UnboundIdentifier(location, name))
    }
    case _ => ???
  }
  def check(context: LocalContext, ast: AST, itsType: Term, effects: List[Term]): Result[The] = ???
}
