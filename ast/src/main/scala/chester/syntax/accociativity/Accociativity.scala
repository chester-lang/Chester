package chester.syntax.accociativity

import chester.syntax.{Name, QualifiedIDString}
import chester.syntax.concrete.stmt.QualifiedID
import upickle.default.*

case class PrecedenceGroup(
                            name: QualifiedIDString,
                            higherThan: Vector[PrecedenceGroup] = Vector(),
                            lowerThan: Vector[PrecedenceGroup] = Vector(),
                            associativity: Associativity = Associativity.None,
                          )

enum Associativity derives ReadWriter {
  case None
  case Left
  case Right
}

trait OpInfo {
}

trait OpUnary extends OpInfo {

}

case class Prefix(name: Name) extends OpUnary

case class Postfix(name: Name) extends OpUnary

case class Infix(name: Name, group: PrecedenceGroup = DefaultPrecedenceGroup) extends OpInfo

case class Mixfix(name: Vector[Name], group: PrecedenceGroup = DefaultPrecedenceGroup) extends OpInfo

case class InfixDefitions(opinfos: Vector[OpInfo])
