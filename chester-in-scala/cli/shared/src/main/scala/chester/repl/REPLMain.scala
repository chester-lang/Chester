package chester.repl

import chester.parser.REPL.{Complete, REPLResult, UnmatchedPair}
import chester.parser.{REPL, ReplLines}
import chester.pretty.const.Colors
import chester.syntax.concrete.Expr
import chester.tyck.{ExprTycker, Judge, LocalCtx, TyckState}
import chester.pretty.doc.*
import chester.pretty.doc.{Doc, render}
import chester.pretty.doc.Implicits._

object REPLMain {
  val mainPrompt: fansi.Str = fansi.Str("Chester> ").overlay(Colors.REPLPrompt)
  val continuationPrompt: String = "...      "
  assert(mainPrompt.length == continuationPrompt.length)

  def runREPL(inputHandler: REPLInputHandler): Unit = {
    println("Welcome to the Chester REPL!")
    println("Type your expressions below. Type 'exit' or ':q' to quit, ':?' for help.")

    val replLines = new ReplLines()
    var currentPrompt = mainPrompt

    def render(x: fansi.Str): String = x.render

    while (true) {
      inputHandler.readLine(render(currentPrompt)) match {
        case LineRead(line) =>
          line match {
            case "exit" | ":q" =>
              println("Exiting REPL.")
              inputHandler.close()
              return
            case ":?" =>
              println("Commands:")
              println(":t <expr> - Check the type of an expression")
              println(":q - Quit the REPL")
              println("You can also just type any expression to evaluate it.")
            case _ =>
              if (line.startsWith(":t ")) {
                val exprStr = line.drop(3)
                REPL.addLine(replLines, exprStr) match {
                  case Left(_) =>
                    println("Incomplete expression.")
                  case Right(UnmatchedPair(error)) =>
                    println(s"Error: ${error.message} at ${error.index}")
                  case Right(Complete(result)) =>
                    result match {
                      case Left(parseError) =>
                        println(s"Parse Error: ${parseError.message} at ${parseError.index}")
                      case Right(parsedExpr) =>
                        val typeCheckResult = typeCheck(parsedExpr)
                        typeCheckResult match {
                          case Left(errors) =>
                            println(s"Type Error: ${errors.head.message}") // Print the first error
                          case Right(judge) =>
                            println(prettyPrintJudge(judge))
                        }
                    }
                }
              } else {
                REPL.addLine(replLines, line) match {
                  case Left(_) =>
                    currentPrompt = continuationPrompt // Update prompt to indicate multi-line input

                  case Right(UnmatchedPair(error)) =>
                    println(s"Error: ${error.message} at ${error.index}")
                    replLines.clearPendingLines()
                    currentPrompt = mainPrompt // Reset prompt

                  case Right(Complete(result)) =>
                    result match {
                      case Left(parseError) =>
                        println(s"Parse Error: ${parseError.message} at ${parseError.index}")
                      case Right(parsedExpr) =>

                        // Type-check the parsed expression
                        val typeCheckResult = typeCheck(parsedExpr)
                        typeCheckResult match {
                          case Left(errors) =>
                            println(s"Type Error: ${errors.head.message}") // Print the first error
                          case Right(judge) =>
                            println(prettyPrintJudgeWellTyped(judge))
                        }
                    }
                }
              }
          }
        case UserInterrupted =>
          println("User interrupted the input. Continuing the REPL.")
          currentPrompt = mainPrompt // Reset prompt after interrupt
        case EndOfFile =>
          println("End of input detected. Exiting REPL.")
          inputHandler.close()
          return
      }
    }
  }

  def typeCheck(expr: Expr): Either[Vector[chester.tyck.TyckError], Judge] = {
    val initialState = TyckState()
    val initialCtx = LocalCtx.Empty
    ExprTycker.synthesize(expr, initialState, initialCtx)
  }

  def prettyPrintJudge(judge: Judge): String = {
    val termDoc = judge.wellTyped
    val typeDoc = judge.ty
    val effectDoc = judge.effect

    val checkOnEffect: String = render(effectDoc)
    val doc = if (checkOnEffect == "NoEffect") then termDoc <+> Doc.text(":") <+> typeDoc else termDoc <+> Doc.text(":") <+> effectDoc <+> typeDoc
    FansiRenderer.render(doc, 80, useCRLF = false).render
  }

  def prettyPrintJudgeWellTyped(judge: Judge): String = {
    val termDoc = judge.wellTyped
    FansiRenderer.render(judge.wellTyped, 80, useCRLF = false).render
  }
}
