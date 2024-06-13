package chester.lang

import term.{Identifier, Term}


case class LocalContextEntry(term: Term, itsType: Term)
case class LocalContext(maps: Map[Identifier, LocalContextEntry]) {
  def get(name: Identifier): Option[Term] = maps.get(name).map(_.term)
  def getType(name: Identifier): Option[Term] = maps.get(name).map(_.itsType)
}
