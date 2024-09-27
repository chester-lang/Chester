package chester.syntax.accociativity

import chester.syntax.*
import chester.syntax.concrete.stmt.QualifiedID
import upickle.default.*

import Associativity.*

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

case class PrecedenceGroupCtx(precedenceGroups: Map[Name, PrecedenceGroup])

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
