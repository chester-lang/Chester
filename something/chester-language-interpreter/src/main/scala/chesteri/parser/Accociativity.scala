package chesteri.parser

case class PrecedenceGroup(
                            name: String,
                            higherThan: Vector[PrecedenceGroup] = Vector(),
                            lowerThan: Vector[PrecedenceGroup] = Vector(),
                            associativity: Associativity = Associativity.None,
                          )

val DefinePrecedenceGroup = PrecedenceGroup("Define")

val TypePrecedenceGroup = PrecedenceGroup("Type", higherThan = Vector(DefinePrecedenceGroup))

val DefaultPrecedenceGroup = PrecedenceGroup("Default", higherThan = Vector(TypePrecedenceGroup))

enum Associativity {
  case None
  case Left
  case Right
}

case class Prefix(name: String)

case class Postfix(name: String)

case class Infix(name: String, group: PrecedenceGroup = DefaultPrecedenceGroup)

case class Mixfix(name: Vector[String], group: PrecedenceGroup = DefaultPrecedenceGroup)

case class InfixDefitions(prefixes: Vector[Prefix] = Vector(),
                          postfixes: Vector[Postfix] = Vector(),
                          infixes: Vector[Infix] = Vector(),
                          mixfixes: Vector[Mixfix] = Vector())


val defaultInfixDefitions = InfixDefitions(infixes = Vector(
  Infix("=", DefinePrecedenceGroup),
  Infix(":", TypePrecedenceGroup),
))