package chester.repl

import chester.parser.{InputStatus, ParseError, ParserEngine}
import chester.pretty.const.Colors
import chester.syntax.concrete.Expr
import chester.tyck.{ExprTycker, Judge, LocalCtx, TyckState}
import chester.pretty.doc.*
import chester.pretty.doc.Implicits.*
import fansi.*

class REPLEngine(terminalFactory: TerminalFactory) {

  val mainPrompt: Str = Str("Chester> ").overlay(Colors.REPLPrompt)
  val continuationPrompt0: Str = Str("...      ").overlay(Colors.REPLPrompt)

  def start(): Unit = {
    val terminalInfo = new TerminalInfo {
      override def checkInputStatus(input: String): InputStatus = ParserEngine.checkInputStatus(input)
      override def defaultPrompt: String = mainPrompt.render
      override def continuationPrompt: String = continuationPrompt0.render
    }

    val terminal: Terminal = terminalFactory.apply(terminalInfo)
    runREPL(terminal)
  }

  private def runREPL(terminal: Terminal): Unit = {
    println("Welcome to the Chester REPL!")
    println("Type your expressions below. Type 'exit' or ':q' to quit, ':?' for help.")

    var continue = true

    while (continue) {
      terminal.readLine() match {
        case LineRead(line) =>
          line match {
            case "exit" | ":q" =>
              println("Exiting REPL.")
              continue = false
            case ":?" =>
              println("Commands:")
              println(":t <expr> - Check the type of an expression")
              println(":q - Quit the REPL")
              println("You can also just type any expression to evaluate it.")
            case _ =>
              if (line.startsWith(":t ")) {
                val exprStr = line.drop(3)
                handleTypeCheck(terminal, exprStr)
              } else {
                handleExpression(terminal, line)
              }
          }
        case UserInterrupted =>
          println("User interrupted the input. Continuing the REPL.")
        case EndOfFile =>
          println("End of input detected. Exiting REPL.")
          continue = false
      }
    }

    terminal.close()
  }

  private def handleTypeCheck(terminal: Terminal, exprStr: String): Unit = {
    ParserEngine.parseInput(terminal.getHistory, exprStr) match {
      case Right(parsedExpr) =>
        typeCheck(parsedExpr) match {
          case Right(judge) => println(prettyPrintJudge(judge))
          case Left(errors) => println(s"Type Error: ${errors.head.message}")
        }
      case Left(error) =>
        println(s"Parse Error: ${error.message}")
    }
  }

  private def handleExpression(terminal: Terminal, line: String): Unit = {
    ParserEngine.parseInput(terminal.getHistory, line) match {
      case Right(parsedExpr) =>
        typeCheck(parsedExpr) match {
          case Right(judge) => println(prettyPrintJudgeWellTyped(judge))
          case Left(errors) => println(s"Type Error: ${errors.head.message}")
        }
      case Left(error) =>
        println(s"Parse Error: ${error.message}")
    }
  }

  private def typeCheck(expr: Expr): Either[Vector[chester.tyck.TyckError], Judge] = {
    val initialState = TyckState()
    val initialCtx = LocalCtx.Empty
    ExprTycker.synthesize(expr, initialState, initialCtx)
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
  def apply(terminalFactory: TerminalFactory): REPLEngine = new REPLEngine(terminalFactory)
}
