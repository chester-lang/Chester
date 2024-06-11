package chester.lang

sealed trait StateAction

case class CreateStateVar() extends StateAction

case class ReadStateVar(expr: Expr) extends StateAction

case class WriteStateVar(iovar: Expr, value: Expr) extends StateAction

case class StateVar(var value: Expr)

sealed trait IOAction

case class Print(expr: Expr) extends IOAction

object Execute {

}
