package chester.lang

import term.{Identifier, Term}

case class Context(maps: Map[Identifier, Term]) {
  def get(name: Identifier): Option[Term] = maps.get(name)
}
