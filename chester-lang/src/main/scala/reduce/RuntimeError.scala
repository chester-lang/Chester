package chester.lang.reduce

import chester.lang.ast.SourceLocation
import chester.lang.term.Term

sealed trait RuntimeError {
  def location: Option[SourceLocation]
  def asTerm: Term = ???
}

trait TyckError extends RuntimeError {

}

case class UnboundIdentifier(location: Option[SourceLocation], name: String) extends RuntimeError
