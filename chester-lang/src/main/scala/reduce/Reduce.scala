package chester.lang.reduce

import chester.lang.ast.{AST, SourceLocation}
import chester.lang.term.{IdentifierTerm, Term}

sealed trait RuntimeError {
  def location: Option[SourceLocation]
  def asTerm: Term = ???
}

case class UnboundIdentifier(location: Option[SourceLocation], name: String) extends RuntimeError

type Result[A] = Either[RuntimeError, A]

class Reduce(state: TyckState) {
  def apply(context: LocalContext, expr: Term): Result[Term] = expr match
    case IdentifierTerm(location, name) =>
      context.get(name) match
        case Some(term) => Right(term)
        case None => Left(UnboundIdentifier(location, name))
    case _ => ???
}



def parseAST(context: LocalContext, astInTerm: Term): Result[AST] = ???