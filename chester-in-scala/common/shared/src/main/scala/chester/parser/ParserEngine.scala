package chester.parser

import chester.error.*
import chester.syntax.concrete.*
import chester.utils.StringIndex
import chester.utils.parse.*
import fastparse.*
import fastparse.NoWhitespace.*

import java.lang.Character.{isDigit, isLetter}
import scala.util.*


sealed trait InputStatus

object InputStatus {

  case object Complete extends InputStatus

  case object Incomplete extends InputStatus

  case class Error(message: String) extends InputStatus

}

import chester.parser.InputStatus.*

object ParserEngine {

  def parseInput(history: Seq[String], currentInput: String, useCRLF: Boolean = false): Either[ParseError, ParsedExpr] = {
    assert(history.last == currentInput)
    val linesOffset = history.init.map(x => x.count(_ == '\n') + 1).sum
    val posOffset = history.init.map(x => x.length + (if(useCRLF) 2 else 1)).sum

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
