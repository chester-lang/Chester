package chester.repl

import chester.doc.const.Colors
import chester.doc.*
import chester.io._
import chester.parser.{InputStatus, ParseError, ParserEngine}
import chester.syntax.concrete.Expr
import chester.syntax.core.*
import chester.tyck.*
import fansi.*
import chester.utils.env
import cats.*
import cats.implicits._

import scala.concurrent.{ExecutionContext, Future}

class REPLEngine[F[_]](using runner: Runner[F],terminal: Terminal[F]) {

  val mainPrompt: Str = Str("Chester> ").overlay(Colors.REPLPrompt)
  val continuationPrompt0: Str = Str("...      ").overlay(Colors.REPLPrompt)

  val terminalInfo = new TerminalInfo {
    override def checkInputStatus(input: String): InputStatus = ParserEngine.checkInputStatus(input)

    override def defaultPrompt: String = mainPrompt.render

    override def continuationPrompt: String = continuationPrompt0.render
  }
  var count = 0
  // TODO: Add Out(n) to refer history evaluation
  var inputs: Vector[Expr] = Vector()
  var outputs: Vector[Judge] = Vector()
  def Out: Judge = ??? // add to the environment of evaluation

  def startF: F[Unit] = {
    println("Welcome to the Chester REPL!")
    println("Type your expressions below. Type 'exit' or ':q' to quit, ':?' for help.")
    println(s"OS: ${env.getOS} Arch: ${env.getArch} Environment: ${env.getRunningOn}")
    Terminal.runTerminal(TerminalInit.Default) {
      runREPL
    }
  }

  private def runREPL(using interminal: InTerminal[F]): F[Unit] = {
    InTerminal.readline(terminalInfo).flatMap {
      case LineRead(line) =>
        processLine(line).flatMap {
          case true => runREPL
          case false => Runner.pure(())
        }
      case UserInterrupted =>
        println("User interrupted the input. Continuing the REPL.")
        runREPL
      case EndOfFile =>
        println("End of input detected. Exiting REPL.")
        Runner.pure(())
      case chester.repl.StatusError(_) =>
        println("Error reading input. Continuing the REPL.")
        runREPL
    }
  }

  private def processLine(line: String)(using interminal: InTerminal[F]): F[Boolean] = {
    line match {
      case "exit" | ":q" =>
        println("Exiting REPL.")
        Runner.pure(false)
      case ":?" =>
        println("Commands:")
        println(":t <expr> - Check the type of an expression")
        println(":q - Quit the REPL")
        println("You can also just type any expression to evaluate it.")
        Runner.pure(true)
      case _ =>
        if (line.startsWith(":t ")) {
          val exprStr = line.drop(3)
          handleTypeCheck(exprStr).map(_ => true)
        } else {
          handleExpression(line).map(_ => true)
        }
    }
  }

  private def handleTypeCheck(exprStr: String)(using interminal: InTerminal[F]): F[Unit] = InTerminal.getHistory.flatMap { history =>
    ParserEngine.parseInput(history, exprStr) match {
      case Right(parsedExpr) =>
        typeCheck(parsedExpr) match {
          case TyckResult.Success(judge,_,_) => println(prettyPrintJudge(judge))
          case TyckResult.Failure(errors,_,_,_) => printErrors(errors)
        }
      case Left(error) =>
        println(s"Parse Error: ${error.message}")
    }
    Runner.pure(())
  }

  private def handleExpression(line: String)(using interminal: InTerminal[F]): F[Unit] = InTerminal.getHistory.flatMap { history =>
    ParserEngine.parseInput(history, line) match {
      case Right(parsedExpr) =>
        typeCheck(parsedExpr) match {
          case TyckResult.Success(judge,_,_) => println(prettyPrintJudgeWellTyped(judge))
          case TyckResult.Failure(errors,_,_,_) => printErrors(errors)
        }
      case Left(error) =>
        println(s"Parse Error: ${error.message}")
    }
    Runner.pure(())
  }

  private def typeCheck(expr: Expr): TyckResult[TyckState, Judge] = {
    val initialState = TyckState()
    val initialCtx = LocalCtx.Empty
    ExprTycker.synthesize(expr, initialState, initialCtx)
  }

  private def printErrors(er: Vector[chester.error.TyckError], wr: Vector[chester.error.TyckWarning] = Vector()): Unit = {
    er.foreach(x => {
      println(FansiRenderer.render(x.renderWithLocation, maxWidth, useCRLF = false).render)
    })
    wr.foreach(x => {
      println(FansiRenderer.render(x.renderWithLocation, maxWidth, useCRLF = false).render)
    })
  }

  val maxWidth = 80

  private def prettyPrintJudge(judge: Judge): String = {
    val termDoc = judge.wellTyped
    val typeDoc = judge.ty
    val effectDoc = judge.effect

    val checkOnEffect: String = render(effectDoc)
    val doc = if (checkOnEffect == "NoEffect") termDoc <+> Doc.text(":") <+> typeDoc
    else termDoc <+> Doc.text(":") <+> effectDoc <+> typeDoc

    FansiRenderer.render(doc, maxWidth, useCRLF = false).render
  }

  private def prettyPrintJudgeWellTyped(judge: Judge): String = {
    val termDoc = judge.wellTyped
    FansiRenderer.render(termDoc, maxWidth, useCRLF = false).render
  }
}
