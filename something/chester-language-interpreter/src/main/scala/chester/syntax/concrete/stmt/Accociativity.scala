package chester.syntax.concrete.stmt

import chester.parser.*

case class PrecedenceGroup(
                            name: QualifiedID,
                            higherThan: Vector[PrecedenceGroup] = Vector(),
                            lowerThan: Vector[PrecedenceGroup] = Vector(),
                            associativity: Associativity = Associativity.None,
                          )

val DefinePrecedenceGroup = PrecedenceGroup(QualifiedID.builtin("Define"))

val TypePrecedenceGroup = PrecedenceGroup(QualifiedID.builtin("Type"), higherThan = Vector(DefinePrecedenceGroup))

val DefaultPrecedenceGroup = PrecedenceGroup(QualifiedID.builtin("Default"), higherThan = Vector(TypePrecedenceGroup))

enum Associativity {
  case None
  case Left
  case Right
}

trait OpInfo {
}

trait OpUnary extends OpInfo {

}

case class Prefix(name: String) extends OpUnary

case class Postfix(name: String) extends OpUnary

case class Infix(name: String, group: PrecedenceGroup = DefaultPrecedenceGroup) extends OpInfo

case class Mixfix(name: Vector[String], group: PrecedenceGroup = DefaultPrecedenceGroup) extends OpInfo

case class InfixDefitions(opinfos: Vector[OpInfo])


val defaultInfixDefitions = InfixDefitions(Vector(
  Infix("=", DefinePrecedenceGroup),
  Infix(":", TypePrecedenceGroup),
))