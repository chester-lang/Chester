package chester.lang.reduce

import chester.lang.term.{Identifier, Term}


case class LocalContextEntry(term: Term, itsType: Term)
case class LocalContext(maps: Map[Identifier, LocalContextEntry]) {
  def getValue(name: Identifier): Option[Term] = maps.get(name).map(_.term)
  def getType(name: Identifier): Option[Term] = maps.get(name).map(_.itsType)
  def get(name: Identifier): Option[LocalContextEntry] = maps.get(name)
}
