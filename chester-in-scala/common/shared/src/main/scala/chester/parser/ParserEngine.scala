package chester.parser

import chester.syntax.concrete.ParsedExpr
import chester.error._
import fastparse._
import NoWhitespace._
import chester.error._
import chester.syntax.concrete._
import chester.utils.StringIndex
import chester.utils.parse._

import java.lang.Character.{isDigit, isLetter}
import scala.util._


sealed trait InputStatus

object InputStatus {

  case object Complete extends InputStatus

  case object Incomplete extends InputStatus

  case class Error(message: String) extends InputStatus

}
import InputStatus._

object ParserEngine {

  def parseInput(history: Seq[String], currentInput: String): Either[ParseError, ParsedExpr] = {
    val linesOffset = history.length
    val posOffset = history.map(_.length).sum

    parseCompleteExpression(currentInput, linesOffset, posOffset)
  }

  def checkInputStatus(currentInput: String): InputStatus = {
    checkUnclosedPairs(currentInput)
  }

  private def checkUnclosedPairs(input: String): InputStatus = {
    val stack = scala.collection.mutable.Stack[Char]()
    for ((char, index) <- input.zipWithIndex) {
      char match {
        case '(' | '{' | '[' => stack.push(char)
        case ')' =>
          if (stack.isEmpty || stack.pop() != '(')
            return Error(s"Unmatched closing parenthesis at position $index")
        case ']' =>
          if (stack.isEmpty || stack.pop() != '[')
            return Error(s"Unmatched closing bracket at position $index")
        case '}' =>
          if (stack.isEmpty || stack.pop() != '{')
            return Error(s"Unmatched closing brace at position $index")
        case _ => // Ignore other characters
      }
    }
    if (stack.nonEmpty) Incomplete else Complete
  }

  private def parseCompleteExpression(input: String, linesOffset: Int, posOffset: Int): Either[ParseError, ParsedExpr] = {
    parse(input, p => new ParserInternal("repl", linesOffset = linesOffset, posOffset = posOffset)(p).exprEntrance) match {
      case Parsed.Success(expr, _) => Right(expr)
      case f: Parsed.Failure => Left(ParseError(f.msg, Pos.Zero))
    }
  }
}
