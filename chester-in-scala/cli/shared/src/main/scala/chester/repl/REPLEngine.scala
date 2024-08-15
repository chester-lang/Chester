package chester.repl

import chester.parser.{InputStatus, ParseError, ParserEngine}
import chester.pretty.const.Colors
import chester.pretty.doc.*
import chester.syntax.concrete.Expr
import chester.tyck.{ExprTycker, Judge, LocalCtx, TyckState}
import fansi.*

import scala.concurrent.{Future, ExecutionContext}

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
          case Right(judge) => println(prettyPrintJudge(judge))
          case Left(errors) => println(s"Type Error: ${errors.head.message}")
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
          case Right(judge) => println(prettyPrintJudgeWellTyped(judge))
          case Left(errors) => println(s"Type Error: ${errors.head.message}")
        }
      case Left(error) =>
        println(s"Parse Error: ${error.message}")
    }
    Future.successful(())
  }

  private def typeCheck(expr: Expr): Either[Vector[chester.error.TyckError], Judge] = {
    val initialState = TyckState()
    val initialCtx = LocalCtx.Empty
    ExprTycker.synthesizeV0(expr, initialState, initialCtx)
  }

  private def prettyPrintJudge(judge: Judge): String = {
    val termDoc = judge.wellTyped
    val typeDoc = judge.ty
    val effectDoc = judge.effect

    val checkOnEffect: String = render(effectDoc)
    val doc = if (checkOnEffect == "NoEffect") termDoc <+> Doc.text(":") <+> typeDoc
    else termDoc <+> Doc.text(":") <+> effectDoc <+> typeDoc

    FansiRenderer.render(doc, 80, useCRLF = false).render
  }

  private def prettyPrintJudgeWellTyped(judge: Judge): String = {
    val termDoc = judge.wellTyped
    FansiRenderer.render(termDoc, 80, useCRLF = false).render
  }
}

object REPLEngine {
  def apply(terminalFactory: TerminalFactory)(implicit ec: ExecutionContext): REPLEngine = new REPLEngine(terminalFactory)
}
