package chester.lang

sealed trait StateAction

case class CreateStateVar() extends StateAction

case class ReadStateVar(expr: Term) extends StateAction

case class WriteStateVar(iovar: Term, value: Term) extends StateAction

case class StateVar(var value: Term)

sealed trait IOAction

case class Print(expr: Term) extends IOAction

object Execute {

}
