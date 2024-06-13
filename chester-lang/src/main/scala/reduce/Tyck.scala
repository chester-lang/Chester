package chester.lang.reduce

import chester.lang.term.*
import chester.lang.ast.*


class TyckState{

}


case class The(term: Term, itsType: Term, effects: Iterable[Term])

def Pure = Vector()


class Tyck(state: TyckState) {

  def infer(context: LocalContext, ast: AST, baseEffects: Iterable[Term] = Pure): Result[The] = ast match {
    case AnnotationAST(expr, itsType, effectsAST, location) => for {
       typeTerm <- check(context, itsType, SortTerm(None, UniverseSort()), Pure)


        result <- check(context, expr, typeTerm.term, baseEffects)
    } yield result
    case IdentifierAST(name, location) => context.getType(name) match {
      case Some(itsType) => Right(The(IdentifierTerm(location, name), itsType, Vector()))
      case None => Left(UnboundIdentifier(location, name))
    }
    case _ => ???
  }
  def check(context: LocalContext, ast: AST, itsType: Term, baseEffects: Iterable[Term]): Result[The] = ???
}
