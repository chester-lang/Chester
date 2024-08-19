package chester.repl

import chester.doc.const.Colors
import chester.doc._
import chester.parser.{InputStatus, ParseError, ParserEngine}
import chester.syntax.concrete.Expr
import chester.syntax.core._
import chester.tyck._
import fansi.*

import scala.concurrent.{ExecutionContext, Future}

class REPLEngine(terminalFactory: TerminalFactory)(implicit ec: ExecutionContext) {

  val mainPrompt: Str = Str("Chester> ").overlay(Colors.REPLPrompt)
  val continuationPrompt0: Str = Str("...      ").overlay(Colors.REPLPrompt)

  val terminalInfo = new TerminalInfo {
    override def checkInputStatus(input: String): InputStatus = ParserEngine.checkInputStatus(input)

    override def defaultPrompt: String = mainPrompt.render

    override def continuationPrompt: String = continuationPrompt0.render
  }

  def start(): Future[Unit] = {
    val terminal: Terminal = terminalFactory()
    println("Welcome to the Chester REPL!")
    println("Type your expressions below. Type 'exit' or ':q' to quit, ':?' for help.")
    runREPL(terminal).map(_ => terminal.close())
  }

  private def runREPL(terminal: Terminal): Future[Unit] = {
    terminal.readLine(terminalInfo).flatMap {
      case LineRead(line) =>
        processLine(terminal, line).flatMap {
          case true => runREPL(terminal)
          case false => Future.successful(())
        }
      case UserInterrupted =>
        println("User interrupted the input. Continuing the REPL.")
        runREPL(terminal)
      case EndOfFile =>
        println("End of input detected. Exiting REPL.")
        Future.successful(())
      case chester.repl.StatusError(_) =>
        println("Error reading input. Continuing the REPL.")
        runREPL(terminal)
    }
  }

  private def processLine(terminal: Terminal, line: String): Future[Boolean] = {
    line match {
      case "exit" | ":q" =>
        println("Exiting REPL.")
        Future.successful(false)
      case ":?" =>
        println("Commands:")
        println(":t <expr> - Check the type of an expression")
        println(":q - Quit the REPL")
        println("You can also just type any expression to evaluate it.")
        Future.successful(true)
      case _ =>
        if (line.startsWith(":t ")) {
          val exprStr = line.drop(3)
          handleTypeCheck(terminal, exprStr).map(_ => true)
        } else {
          handleExpression(terminal, line).map(_ => true)
        }
    }
  }

  private def handleTypeCheck(terminal: Terminal, exprStr: String): Future[Unit] = {
    ParserEngine.parseInput(terminal.getHistory, exprStr) match {
      case Right(parsedExpr) =>
        typeCheck(parsedExpr) match {
          case TyckResult.Success(judge,_,_) => println(prettyPrintJudge(judge))
          case TyckResult.Failure(errors,_,_,_) => printErrors(errors)
        }
      case Left(error) =>
        println(s"Parse Error: ${error.message}")
    }
    Future.successful(())
  }

  private def handleExpression(terminal: Terminal, line: String): Future[Unit] = {
    ParserEngine.parseInput(terminal.getHistory, line) match {
      case Right(parsedExpr) =>
        typeCheck(parsedExpr) match {
          case TyckResult.Success(judge,_,_) => println(prettyPrintJudgeWellTyped(judge))
          case TyckResult.Failure(errors,_,_,_) => printErrors(errors)
        }
      case Left(error) =>
        println(s"Parse Error: ${error.message}")
    }
    Future.successful(())
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

object REPLEngine {
  def apply(terminalFactory: TerminalFactory)(implicit ec: ExecutionContext): REPLEngine = new REPLEngine(terminalFactory)
}
