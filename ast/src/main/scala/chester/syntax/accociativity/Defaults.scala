package chester.syntax.accociativity

import chester.syntax.*
import chester.syntax.accociativity.Associativity.*
import chester.syntax.concrete.stmt.QualifiedID
import upickle.default.*

case class OperatorsContext(opinfos: InfixDefitions, groups: PrecedenceGroupCtx) {
  def resolveInfix(name: Name): Option[Infix] = opinfos.resolveInfix(name)

  def resolvePrefix(name: Name): Option[Prefix] = opinfos.resolvePrefix(name)

  def resolvePostfix(name: Name): Option[Postfix] = opinfos.resolvePostfix(name)
}

object OperatorsContext {
  val Default: OperatorsContext = OperatorsContext(defaultInfixDefitions, defaultPrecedenceGroup)
}

case class InfixDefitions(infix: Map[Name, Infix], other: Vector[Prefix|Postfix] = Vector.empty) {
  def resolveInfix(name: Name): Option[Infix] = infix.get(name)

  def resolvePrefix(name: Name): Option[Prefix] = (other.find {
    case Prefix(`name`) => true
    case _ => false
  }).asInstanceOf[Option[Prefix]]

  def resolvePostfix(name: Name): Option[Postfix] = (other.find {
    case Postfix(`name`) => true
    case _ => false
  }).asInstanceOf[Option[Postfix]]
}

object InfixDefitions {
  def apply(opinfos: Vector[OpInfo]): InfixDefitions = {
    val (infix, other) = opinfos.partition {
      case Infix(name, _) => true
      case _ => false
    }
    InfixDefitions(infix.collect { case Infix(name, group) => name -> Infix(name, group) }.toMap, other.asInstanceOf[Vector[Prefix|Postfix]])
  }
}

val defaultInfixDefitions = InfixDefitions(Vector(
  // Multiplicative Operators
  Infix(Name("*"), multiplicativeGroup),
  Infix(Name("/"), multiplicativeGroup),
  Infix(Name("%"), multiplicativeGroup),

  // Additive Operators
  Infix(Name("+"), additiveGroup),
  Infix(Name("-"), additiveGroup),

  // Range Operator
  Infix(Name(":"), rangeGroup),

  // Relational Operators
  Infix(Name("<"), relationalGroup),
  Infix(Name(">"), relationalGroup),

  // Equality Operators
  Infix(Name("="), equalityGroup),
  Infix(Name("!"), equalityGroup),

  // Logical AND Operator
  Infix(Name("&"), logicalAndGroup),

  // Logical XOR Operator
  Infix(Name("^"), logicalXorGroup),

  // Logical OR Operator
  Infix(Name("|"), logicalOrGroup)
))

val defaultPrecedenceGroup = PrecedenceGroupCtx(Map(
  Names.Multiplicative.name -> multiplicativeGroup,
  Names.Additive.name -> additiveGroup,
  Names.Range.name -> rangeGroup,
  Names.Relational.name -> relationalGroup,
  Names.Equality.name -> equalityGroup,
  Names.LogicalAnd.name -> logicalAndGroup,
  Names.LogicalXor.name -> logicalXorGroup,
  Names.LogicalOr.name -> logicalOrGroup
))

case class PrecedenceGroupCtx(precedenceGroups: Map[Name, PrecedenceGroup]) {
  def groups: Seq[PrecedenceGroup] = precedenceGroups.values.toVector
}

object Names {
  val Multiplicative = QualifiedIDString.from("Multiplicative")
  val Additive = QualifiedIDString.from("Additive")
  val Range = QualifiedIDString.from("Range")
  val Relational = QualifiedIDString.from("Relational")
  val Equality = QualifiedIDString.from("Equality")
  val LogicalAnd = QualifiedIDString.from("LogicalAnd")
  val LogicalXor = QualifiedIDString.from("LogicalXor")
  val LogicalOr = QualifiedIDString.from("LogicalOr")
  val Default = QualifiedIDString.from("Default")
}

lazy val DefaultPrecedenceGroup: PrecedenceGroup = PrecedenceGroup(Names.Default)

// Define the precedence groups with their associativity and precedence
lazy val logicalOrGroup: PrecedenceGroup = PrecedenceGroup(
  name = Names.LogicalOr,
  associativity = Left,
  higherThan = Vector(DefaultPrecedenceGroup)
)

lazy val logicalXorGroup: PrecedenceGroup = PrecedenceGroup(
  name = Names.LogicalXor,
  associativity = Left,
  higherThan = Vector(logicalOrGroup)
)

lazy val logicalAndGroup: PrecedenceGroup = PrecedenceGroup(
  name = Names.LogicalAnd,
  associativity = Left,
  higherThan = Vector(logicalXorGroup)
)

lazy val equalityGroup: PrecedenceGroup = PrecedenceGroup(
  name = Names.Equality,
  associativity = None,
  higherThan = Vector(logicalAndGroup)
)

lazy val relationalGroup: PrecedenceGroup = PrecedenceGroup(
  name = Names.Relational,
  associativity = None,
  higherThan = Vector(equalityGroup)
)

lazy val rangeGroup: PrecedenceGroup = PrecedenceGroup(
  name = Names.Range,
  associativity = None,
  higherThan = Vector(relationalGroup)
)

lazy val additiveGroup: PrecedenceGroup = PrecedenceGroup(
  name = Names.Additive,
  associativity = Left,
  higherThan = Vector(rangeGroup)
)

lazy val multiplicativeGroup: PrecedenceGroup = PrecedenceGroup(
  name = Names.Multiplicative,
  associativity = Left,
  higherThan = Vector(additiveGroup)
)
