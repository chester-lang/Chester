package chester.io

import chester.parser.InputStatus.{Complete, Error, Incomplete}
import chester.parser.ParserEngine
import chester.repl.*

abstract class AbstractInTerminal[F[_]](using runner: Runner[F]) extends InTerminal[F] {
  private var history: Vector[String] = Vector() // TODO: implement
  private var currentInputs: String = ""

  def initHistoryFromInit(init: TerminalInit): Seq[String] = Nil // TODO: implement

  def initHistory: F[Seq[String]]

  def readALine(prompt: fansi.Str): F[String]

  override def readline(info: TerminalInfo): F[ReadLineResult] = {
    def loop(prompt: fansi.Str): F[ReadLineResult] = {
      readALine(prompt).flatMap { line =>
        if (line == null) {
          Runner.pure(EndOfFile)
        } else if (line.forall(_.isWhitespace)) {
          loop(prompt) // continue reading
        } else {
          currentInputs = if (currentInputs.isEmpty) line else s"$currentInputs\n$line"
          ParserEngine.checkInputStatus(currentInputs) match {
            case Complete =>
              history = history :+ currentInputs
              val result = LineRead(currentInputs)
              currentInputs = ""
              Runner.pure(result)
            case Incomplete =>
              loop(info.continuationPrompt.render) // continue with continuation prompt
            case Error(message) =>
              currentInputs = ""
              Runner.pure(StatusError(message))
          }
        }
      }
    }

    loop(info.defaultPrompt.render)
  }

  override def getHistory: F[Seq[String]] = Runner.pure(history)
}
