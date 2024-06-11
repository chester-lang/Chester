package chester.lang

sealed trait Expr {
  def location: Option[SourceLocation]
  def isWHNF: Boolean = false
  lazy val whnf: Expr = Reduce(this)
}


case class TableExpr(location: Option[SourceLocation], entries: List[TableEntry]) extends Expr


sealed trait TableEntry {
  def isWHNF: Boolean = false
}

case class SimpleTableEntry(key: Expr, value: Expr) extends TableEntry

case class PrimitiveExpr(location: Option[SourceLocation], name: String) extends Expr

case class StringExpr(location: Option[SourceLocation], value: String) extends Expr

case class IntegerExpr(location: Option[SourceLocation], value: BigInt) extends Expr

case class CallExpr(location: Option[SourceLocation], callee: Expr, list: List[Expr]) extends Expr

case class MacroCallExpr(location: Option[SourceLocation], callee: Expr, list: List[Expr]) extends Expr

case class QuasiquoteExpr(location: Option[SourceLocation], expr: Expr) extends Expr

case class QuoteExpr(location: Option[SourceLocation], expr: Expr) extends Expr

case class UnquoteExpr(location: Option[SourceLocation], expr: Expr) extends Expr

case class UnquoteSplicing(location: Option[SourceLocation], expr: Expr) extends Expr