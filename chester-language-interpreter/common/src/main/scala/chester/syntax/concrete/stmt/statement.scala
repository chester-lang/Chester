package chester.syntax.concrete.stmt

import chester.syntax.concrete.{Expr, ExprMeta}

sealed trait Statement

case class DataStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class TraitStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class ImplementStatement(rest: Vector[Expr], meta: Option[ExprMeta] = None) extends Statement

case class ExprStatement(expr: Expr, meta: Option[ExprMeta] = None) extends Statement
