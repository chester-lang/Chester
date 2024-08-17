package chester.syntax.concrete.stmt

import chester.syntax.concrete.stmt.accociativity.Associativity
import chester.syntax.concrete.{Expr, ExprMeta}
import chester.syntax.{Id, UnresolvedID}
import chester.error._

sealed trait TopLevelStmt {
  def meta: Option[ExprMeta]

  def reduceOnce: (Vector[TyckWarning], Vector[TyckError], TopLevelStmt) = (Vector.empty, Vector.empty, this)

  def reduce: (Vector[TyckWarning], Vector[TyckError], TopLevelStmt) = {
    val (warnings, errors, stmt) = reduceOnce
    if (stmt == this) (Vector.empty, Vector.empty, stmt)
    else {
      val (newWarnings, newErrors, newStmt) = stmt.reduce
      (warnings ++ newWarnings, errors ++ newErrors, newStmt)
    }
  }

  def getName: Option[Id]
}

private sealed trait NameUnknown extends TopLevelStmt {
  def getName: Option[Id] = None
}

private sealed trait NameKnown extends TopLevelStmt {
  def name: Id

  def getName: Option[Id] = Some(name)
}

private sealed trait NameOption extends TopLevelStmt {
  def name: Option[Id]

  def getName: Option[Id] = name
}

case class DataTopLevelStmt(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends TopLevelStmt with NameUnknown

case class TraitTopLevelStmt(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends TopLevelStmt with NameUnknown

case class ImplementTopLevelStmt(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends TopLevelStmt with NameUnknown

case class TypeDeclaration(name: Option[String], exprs: Vector[Expr], types: Vector[Expr], meta: Option[ExprMeta] = None) extends NameOption

case class Definition(name: Option[String], exprs: Vector[Expr], defns: Vector[Expr], meta: Option[ExprMeta] = None) extends NameOption

case class DeclarationAndDefinition(name: Option[String], declNameExprs: Vector[Expr], types: Vector[Expr], defns: Vector[Expr], meta: Option[ExprMeta] = None) extends NameOption

case class ExprTopLevelStmt(expr: Expr, meta: Option[ExprMeta] = None) extends TopLevelStmt with NameUnknown

case class GroupedTopLevelStmt(name: Option[Id], declaration: TypeDeclaration, definitions: Vector[Definition], meta: Option[ExprMeta] = None) extends NameOption

case class ErrorTopLevelStmt(name: Option[Id], message: String, meta: Option[ExprMeta] = None) extends NameOption

sealed trait PrecedenceGroup

case class PrecedenceGroupResolving(
                                     name: Id,
                                     higherThan: Vector[UnresolvedID] = Vector(),
                                     lowerThan: Vector[UnresolvedID] = Vector(),
                                     associativity: Associativity = Associativity.None, meta: Option[ExprMeta] = None
                                   ) extends TopLevelStmt with PrecedenceGroup {
  def getName: Option[Id] = Some(name)
}

case class PrecedenceGroupResolved(
                                    name: QualifiedID,
                                    higherThan: Vector[PrecedenceGroupResolved] = Vector(),
                                    lowerThan: Vector[PrecedenceGroupResolved] = Vector(),
                                    associativity: Associativity = Associativity.None, meta: Option[ExprMeta] = None
                                  ) extends TopLevelStmt with PrecedenceGroup {
  def getName: Option[Id] = Some(name.name)
}


case class LetTopLevelStmt(name: Id, ty: Option[Expr], expr: Expr, meta: Option[ExprMeta] = None) extends NameKnown