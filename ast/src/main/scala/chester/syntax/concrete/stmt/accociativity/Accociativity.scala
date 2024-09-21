package chester.syntax.concrete.stmt.accociativity
import chester.syntax.concrete.stmt.QualifiedID
import upickle.default._

case class PrecedenceGroup(
                            name: QualifiedID,
                            higherThan: Vector[PrecedenceGroup] = Vector(),
                            lowerThan: Vector[PrecedenceGroup] = Vector(),
                            associativity: Associativity = Associativity.None,
                          )

object Names {
  val Default = "Default"
  val Define = "Define"
  val Type = "Type"
}

val DefaultPrecedenceGroup = PrecedenceGroup(QualifiedID.builtin(Names.Default))

val DefinePrecedenceGroup = PrecedenceGroup(QualifiedID.builtin(Names.Define), lowerThan = Vector(DefaultPrecedenceGroup))

val TypePrecedenceGroup = PrecedenceGroup(QualifiedID.builtin(Names.Type), higherThan = Vector(DefinePrecedenceGroup), lowerThan = Vector(DefaultPrecedenceGroup))

enum Associativity derives ReadWriter {
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

case class PrecedenceGroupCtx(precedenceGroups: Map[String, PrecedenceGroup])


val defaultInfixDefitions = InfixDefitions(Vector(
  Infix("=", DefinePrecedenceGroup),
  Infix(":", TypePrecedenceGroup),
))

val defaultPrecedenceGroup = PrecedenceGroupCtx(Map(
  (Names.Default -> DefaultPrecedenceGroup),
  (Names.Define -> DefinePrecedenceGroup),
  (Names.Type -> TypePrecedenceGroup),
))