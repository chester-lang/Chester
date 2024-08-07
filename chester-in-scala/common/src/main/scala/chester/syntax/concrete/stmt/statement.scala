package chester.syntax.concrete.stmt

import chester.syntax.concrete.{Expr, ExprMeta}

sealed trait Statement {
  def meta: Option[ExprMeta]
}

case class DataStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class TraitStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class ImplementStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class TypeDeclaration(name: Option[String], exprs: Vector[Expr], types: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class Definition(name: Option[String], exprs: Vector[Expr], defns: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class DeclarationAndDefinition(name: Option[String], declNameExprs: Vector[Expr], types: Vector[Expr], defns: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class ExprStatement(expr: Expr, meta: Option[ExprMeta] = None) extends Statement

case class GroupedStatement(name: Option[String], declaration: TypeDeclaration, definitions: Vector[Definition], meta: Option[ExprMeta] = None) extends Statement
