package chester.repl

import chester.parser.InputStatus.*
import chester.parser.ParserEngine
import typings.node.{processMod, readlineMod, readlinePromisesMod}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.Thenable.Implicits.*
import scala.concurrent.{Future, Promise}

class NodejsSimpleTerminal extends Terminal {
  private var history: Vector[String] = Vector()
  private var currentInputs: String = ""

  private val rl = readlineMod.createInterface(processMod.^.stdin.asInstanceOf, processMod.^.stdout.asInstanceOf)

  private var live: Boolean = true

  private var reading: Promise[String] = null

  private def closeCallback(): Unit = {
    if (live) {
      if (reading != null) {
        val r = reading
        reading = null
        r.success(null)
      }
    }
  }

  rl.on("close", x=>closeCallback())

  private def nodeReadLine(prompt: String): Future[String] = {
    assert(reading == null)
    assert(live)
    val p = Promise[String]()
    reading = p
    rl.question(prompt, { result =>
      assert(reading == p)
      reading = null
      p.success(result)
    })
    p.future
  }

  def readLine(info: TerminalInfo): Future[ReadLineResult] = {
    def loop(prompt: String): Future[ReadLineResult] = {
      nodeReadLine(prompt).flatMap { line =>
        if (line == null) {
          Future.successful(EndOfFile)
        } else if (line.forall(_.isWhitespace)) {
          loop(prompt) // continue reading
        } else {
          currentInputs = if (currentInputs.isEmpty) line else s"$currentInputs\n$line"
          ParserEngine.checkInputStatus(currentInputs) match {
            case Complete =>
              history = history :+ currentInputs
              val result = LineRead(currentInputs)
              currentInputs = ""
              Future.successful(result)
            case Incomplete =>
              loop(info.continuationPrompt) // continue with continuation prompt
            case Error(message) =>
              currentInputs = ""
              Future.successful(StatusError(message))
          }
        }
      }
    }

    loop(info.defaultPrompt)
  }

  def close(): Unit = {
    live = false
    rl.close()
  }

  def getHistory: Seq[String] = history
}

object NodejsSimpleTerminal extends TerminalFactory {
  def apply(): NodejsSimpleTerminal = new NodejsSimpleTerminal()
}

object NodejsCLIRunner extends CLIRunnerMonad[Future] {
  def apply(init: TerminalInit): CLIHandler[Future] = {
    val t = new NodejsSimpleTerminal()
    new CLIHandler[Future] {
      override def readline(info: TerminalInfo): Future[ReadLineResult] = t.readLine(info)
      override def getHistory: Future[Seq[String]] = Future.successful(t.getHistory)
      override def close: Future[Unit] = Future.successful(t.close())
    }
  }
  def spawn[T](x: => Future[T]): Unit = {
    x
    ()
  }
}