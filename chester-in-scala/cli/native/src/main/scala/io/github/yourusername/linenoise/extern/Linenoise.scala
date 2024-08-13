package io.github.yourusername.linenoise.extern

import scala.scalanative.unsafe._
import scala.scalanative.annotation._

@extern
object Linenoise {

  type linenoiseCompletions = CStruct2[CSize, Ptr[CString]]

  def linenoise(prompt: CString): CString = extern
  def linenoiseFree(ptr: Ptr[Byte]): Unit = extern
  def linenoiseHistoryAdd(line: CString): CInt = extern
  def linenoiseHistorySetMaxLen(len: CInt): CInt = extern
  def linenoiseHistorySave(filename: CString): CInt = extern
  def linenoiseHistoryLoad(filename: CString): CInt = extern
  def linenoiseClearScreen(): Unit = extern
  def linenoiseSetMultiLine(ml: CInt): Unit = extern
  def linenoiseSetCompletionCallback(callback: CFuncPtr2[CString, Ptr[linenoiseCompletions], Unit]): Unit = extern
  def linenoiseSetHintsCallback(callback: CFuncPtr3[CString, Ptr[CInt], Ptr[CInt], CString]): Unit = extern
  def linenoiseAddCompletion(completions: Ptr[linenoiseCompletions], completion: CString): Unit = extern
}
