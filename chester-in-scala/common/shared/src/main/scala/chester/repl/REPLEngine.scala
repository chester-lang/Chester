package chester.repl

import cats.implicits.*
import chester.doc.*
import chester.doc.const.Colors
import chester.io.*
import chester.parser.{InputStatus, ParseError, ParserEngine}
import chester.syntax.concrete.Expr
import chester.syntax.core.*
import chester.tyck.*
import chester.utils.env
import fansi.*

class REPLEngine[F[_]](using runner: Runner[F], terminal: Terminal[F]) {

  val mainPrompt: Str = Str("Chester> ").overlay(Colors.REPLPrompt)
  val continuationPrompt0: Str = Str("...      ").overlay(Colors.REPLPrompt)

  val terminalInfo = new TerminalInfo {
    override def checkInputStatus(input: String): InputStatus = ParserEngine.checkInputStatus(input)

    override def defaultPrompt: fansi.Str = mainPrompt

    override def continuationPrompt: fansi.Str = continuationPrompt0
  }
  var count = 0
  // TODO: Add Out(n) to refer history evaluation
  var inputs: Vector[Expr] = Vector()
  var outputs: Vector[Judge] = Vector()

  def Out: Judge = ??? // add to the environment of evaluation

  def startF: F[Unit] = {
    Terminal.runTerminal(TerminalInit.Default) {
      for {
        _ <- InTerminal.writeln("Welcome to the Chester REPL!")
        _ <- InTerminal.writeln("Type your expressions below. Type 'exit' or ':q' to quit, ':?' for help.")
        _ <- InTerminal.writeln(s"OS: ${env.getOS} Arch: ${env.getArch} Environment: ${env.getRunningOn}")
        _ <- runREPL
      } yield ()
    }
  }

  private def runREPL(using interminal: InTerminal[F]): F[Unit] = {
    InTerminal.readline(terminalInfo).flatMap {
      case LineRead(line) =>
        processLine(line).flatMap {
          case true => runREPL
          case false => Runner.pure(())
        }
      case UserInterrupted => for {
        _ <- InTerminal.writeln("User interrupted the input. Continuing the REPL.")
        _ <- runREPL
      } yield ()
      case EndOfFile =>
        InTerminal.writeln("End of input detected. Exiting REPL.")
      case chester.repl.StatusError(_) => for {
        _ <- InTerminal.writeln("Error reading input. Continuing the REPL.")
        _ <- runREPL
      } yield ()
    }
  }

  private def processLine(line: String)(using interminal: InTerminal[F]): F[Boolean] = {
    line match {
      case "exit" | ":q" => for {
        _ <-
          InTerminal.writeln("Exiting REPL.")
      } yield false
      case ":?" =>
        for {
          _ <- InTerminal.writeln("Commands:")
          _ <- InTerminal.writeln(":t <expr> - Check the type of an expression")
          _ <- InTerminal.writeln(":q - Quit the REPL")
          _ <- InTerminal.writeln("You can also just type any expression to evaluate it.")
        } yield true
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
          case TyckResult.Success(judge, _, _) => InTerminal.writeln(prettyPrintJudge(judge))
          case TyckResult.Failure(errors, _, _, _) => printErrors(errors)
        }
      case Left(error) =>
        InTerminal.writeln(s"Parse Error: ${error.message}")
    }
  }

  private def handleExpression(line: String)(using interminal: InTerminal[F]): F[Unit] = InTerminal.getHistory.flatMap { history =>
    ParserEngine.parseInput(history, line) match {
      case Right(parsedExpr) =>
        typeCheck(parsedExpr) match {
          case TyckResult.Success(judge, _, _) => InTerminal.writeln(prettyPrintJudgeWellTyped(judge))
          case TyckResult.Failure(errors, _, _, _) => printErrors(errors)
        }
      case Left(error) =>
        InTerminal.writeln(s"Parse Error: ${error.message}")
    }
  }

  private def typeCheck(expr: Expr): TyckResult[TyckState, Judge] = {
    val initialState = TyckState()
    val initialCtx = LocalCtx.Empty
    ExprTycker.synthesize(expr, initialState, initialCtx)
  }

  private def printErrors(er: Vector[chester.error.TyckError], wr: Vector[chester.error.TyckWarning] = Vector())(using interminal: InTerminal[F]): F[Unit] = {
    for {
      _ <- er.traverse(x => {
        InTerminal.writeln(FansiRenderer.render(x.renderWithLocation, maxWidth, useCRLF = false))
      })
      _ <- wr.traverse(x => {
        InTerminal.writeln(FansiRenderer.render(x.renderWithLocation, maxWidth, useCRLF = false))
      })
    } yield ()
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
