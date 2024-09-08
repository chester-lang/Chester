package chester.utils.env

import scala.sys.process._
import scala.util.Try

object WindowsNarratorChecker {
  def apply(): Boolean = {
    val processList = Try("tasklist".!!).getOrElse("")

    processList.linesIterator.exists(_.startsWith("Narrator.exe"))
  }
}
