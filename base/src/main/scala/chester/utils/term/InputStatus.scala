package chester.utils.term

import chester.utils.io.*
import chester.utils.term.*

sealed trait InputStatus

object InputStatus {

  case object Complete extends InputStatus

  case object Incomplete extends InputStatus

  case class Error(message: String) extends InputStatus

}
