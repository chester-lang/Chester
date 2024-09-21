package chester.parser

sealed trait InputStatus

object InputStatus {

  case object Complete extends InputStatus

  case object Incomplete extends InputStatus

  case class Error(message: String) extends InputStatus

}
