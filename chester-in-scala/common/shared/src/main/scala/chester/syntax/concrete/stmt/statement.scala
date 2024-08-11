package chester.syntax.concrete.stmt

import chester.syntax.Id
import chester.syntax.concrete.{Expr, ExprMeta}
import chester.tyck.{TyckError, TyckWarning}

sealed trait Statement {
  def meta: Option[ExprMeta]
  def reduceOnce: (Vector[TyckWarning], Vector[TyckError], Statement) = (Vector.empty, Vector.empty, this)
  def reduce: (Vector[TyckWarning], Vector[TyckError], Statement) = {
    val (warnings, errors, stmt) = reduceOnce
    if(stmt == this) (Vector.empty, Vector.empty, stmt)
    else {
      val (newWarnings, newErrors, newStmt) = stmt.reduce
      (warnings ++ newWarnings, errors ++ newErrors, newStmt)
    }
  }
  def name: Option[Id]
}

private sealed trait NameUnknown {
  def name: Option[Id] = None
}

case class DataStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement with NameUnknown

case class TraitStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement with NameUnknown

case class ImplementStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement with NameUnknown

case class TypeDeclaration(name: Option[String], exprs: Vector[Expr], types: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class Definition(name: Option[String], exprs: Vector[Expr], defns: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class DeclarationAndDefinition(name: Option[String], declNameExprs: Vector[Expr], types: Vector[Expr], defns: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class ExprStatement(expr: Expr, meta: Option[ExprMeta] = None) extends Statement with NameUnknown

case class GroupedStatement(name: Option[Id], declaration: TypeDeclaration, definitions: Vector[Definition], meta: Option[ExprMeta] = None) extends Statement

case class ErrorStatement(name: Option[Id], message: String, meta: Option[ExprMeta] = None) extends Statement