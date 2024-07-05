package chesteri.resolver;

case class PrecedenceGroup(
                            name: String,
                            higherThan: Vector[PrecedenceGroup] = Vector(),
                            lowerThan: Vector[PrecedenceGroup] = Vector(),
                            associativity: Associativity = Associativity.None,
                          )

enum Associativity {
  case None
  case Left
  case Right
}

case class Prefix(name: String)

case class Postfix(name: String)

case class Infix(name: String, group: PrecedenceGroup)

case class InfixDefitions(prefixes: Vector[Prefix] = Vector(),
                          postfixes: Vector[Postfix] = Vector(),
                          infixes: Vector[Infix] = Vector())
