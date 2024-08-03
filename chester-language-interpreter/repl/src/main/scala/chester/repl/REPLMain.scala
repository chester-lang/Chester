package chester.repl

import chester.parser.REPL
import chester.parser.REPL.{REPLResult, UnmatchedPair, Complete}
import chester.syntax.concrete.Expr
import chester.tyck.{ExprTycker, TyckState, LocalCtx, Judge}
import scala.io.StdIn

object REPLMain {
  def main(args: Array[String]): Unit = {
    println("Welcome to the Chester REPL!")
    println("Type your expressions below. Type 'exit' to quit.")

    var inputLines: List[String] = List()

    while (true) {
      print("chester> ")
      val line = StdIn.readLine()

      if (line == "exit") {
        println("Exiting REPL.")
        return
      }

      REPL.addLine(inputLines, line) match {
        case Left(updatedLines) =>
          inputLines = updatedLines

        case Right(UnmatchedPair(error)) =>
          println(s"Error: ${error.message} at ${error.index}")

        case Right(Complete(result)) =>
          result match {
            case Left(parseError) =>
              println(s"Parse Error: ${parseError.message} at ${parseError.index}")
            case Right(parsedExpr) =>
              println(s"Parsed Expression: $parsedExpr")

              // Type-check the parsed expression
              val typeCheckResult = typeCheck(parsedExpr)
              typeCheckResult match {
                case Left(error) =>
                  println(s"Type Error: ${error.message}")
                case Right(judge) =>
                  println(s"Type Check Successful: ${judge}")
              }
          }
          inputLines = List()
      }
    }
  }

  // Function to type-check the parsed expression
  def typeCheck(expr: Expr): Either[chester.tyck.TyckError, Judge] = {
    val initialState = TyckState()
    val initialCtx = LocalCtx.Empty
    ExprTycker.synthesize(expr, initialState, initialCtx)
  }
}
