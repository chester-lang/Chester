package io.github.yourusername.linenoise

import io.github.yourusername.linenoise.extern.Linenoise
import scala.scalanative.unsafe._
import scala.scalanative.libc.stdlib._

object facade {

  private var completionCallback: Option[(String, List[String]) => Unit] = None
  private var hintsCallback: Option[String => Option[(String, Int, Boolean)]] = None

  def linenoise(prompt: String): Option[String] = Zone {
    val cPrompt = toCString(prompt)
    val result = Linenoise.linenoise(cPrompt)
    if (result != null) {
      val str = fromCString(result)
      Linenoise.linenoiseFree(result.asInstanceOf[Ptr[Byte]])
      Some(str)
    } else {
      None
    }
  }

  def linenoiseHistoryAdd(line: String): Int = Zone {
    Linenoise.linenoiseHistoryAdd(toCString(line))
  }

  def linenoiseHistorySetMaxLen(len: Int): Int = Linenoise.linenoiseHistorySetMaxLen(len)

  def linenoiseHistorySave(filename: String): Int = Zone {
    Linenoise.linenoiseHistorySave(toCString(filename))
  }

  def linenoiseHistoryLoad(filename: String): Int = Zone {
    Linenoise.linenoiseHistoryLoad(toCString(filename))
  }

  def linenoiseClearScreen(): Unit = Linenoise.linenoiseClearScreen()

  def linenoiseSetMultiLine(ml: Boolean): Unit = Linenoise.linenoiseSetMultiLine(if (ml) 1 else 0)

  def linenoiseSetCompletionCallback(callback: (String, List[String]) => Unit): Unit = {
    completionCallback = Some(callback)
    val cb: CFuncPtr2[CString, Ptr[Linenoise.linenoiseCompletions], Unit] =
      (buf: CString, lc: Ptr[Linenoise.linenoiseCompletions]) => Zone {
        completionCallback.foreach { cb =>
          val bufStr = fromCString(buf)
          val completions = scala.collection.mutable.ListBuffer[String]()
          cb(bufStr, completions.toList)
          completions.foreach { completion =>
            Linenoise.linenoiseAddCompletion(lc, toCString(completion))
          }
        }
      }
    Linenoise.linenoiseSetCompletionCallback(cb)
  }

  def linenoiseSetHintsCallback(callback: String => Option[(String, Int, Boolean)]): Unit = {
    hintsCallback = Some(callback)
    val cb: CFuncPtr3[CString, Ptr[CInt], Ptr[CInt], CString] =
      (buf: CString, colorPtr: Ptr[CInt], boldPtr: Ptr[CInt]) => Zone {
        hintsCallback.flatMap(cb => cb(fromCString(buf))) match {
          case Some((hintStr, hintColor, hintBold)) =>
            !colorPtr = hintColor
            !boldPtr = if (hintBold) 1 else 0
            toCString(hintStr)
          case None => null
        }
      }
    Linenoise.linenoiseSetHintsCallback(cb)
  }
}
