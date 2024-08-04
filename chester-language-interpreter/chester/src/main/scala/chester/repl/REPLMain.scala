package chester.repl
import chester.parser.REPL.{Complete, REPLResult, UnmatchedPair}
import chester.parser.{REPL, ReplLines}
import chester.syntax.concrete.Expr
import chester.tyck.{ExprTycker, Judge, LocalCtx, TyckState}
import org.jline.reader.impl.history.DefaultHistory
import org.jline.reader.{EndOfFileException, LineReader, LineReaderBuilder, UserInterruptException}
import org.jline.terminal.TerminalBuilder

object REPLMain {
  // Define prompts as constants with the same length
  val mainPrompt: String = "chester> "
  val continuationPrompt: String = "...      "
  assert(mainPrompt.length == continuationPrompt.length)

  def runREPL(): Unit = {
    val terminal = TerminalBuilder.terminal()
    val history = new DefaultHistory()
    val reader = LineReaderBuilder.builder()
      .terminal(terminal)
      .history(history)
      .build()

    println("Welcome to the Chester REPL!")
    println("Type your expressions below. Type 'exit' to quit.")

    val replLines = new ReplLines()
    var currentPrompt = mainPrompt

    while (true) {
      try {
        val line = reader.readLine(currentPrompt)

        if (line == "exit") {
          println("Exiting REPL.")
          return
        }

        REPL.addLine(replLines, line) match {
          case Left(_) =>
            currentPrompt = continuationPrompt  // Update prompt to indicate multi-line input

          case Right(UnmatchedPair(error)) =>
            println(s"Error: ${error.message} at ${error.index}")
            replLines.clearPendingLines()
            currentPrompt = mainPrompt  // Reset prompt

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
            currentPrompt = mainPrompt  // Reset prompt
        }
      } catch {
        case _: UserInterruptException => // Ignore, continue REPL loop
        case _: EndOfFileException =>
          println("Exiting REPL.")
          return
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
