package chester.parser

import fastparse._
import NoWhitespace._
import chester.error._
import chester.syntax.concrete._
import chester.utils.StringIndex
import chester.utils.parse._

import java.lang.Character.{isDigit, isLetter}
import scala.util._

object REPL {

  sealed trait REPLResult
  case class Complete(result: Either[ParseError, ParsedExpr]) extends REPLResult
  case object Incomplete extends REPLResult
  case class UnmatchedPair(error: ParseError) extends REPLResult

  // Function to parse a single line and determine if more lines are needed
  private def parseLine(line: String): Either[ParseError, ParsedExpr] = {
    parse(line, p => new ParserInternal("repl", ignoreLocation = true)(p).exprEntrance) match {
      case Parsed.Success(expr, _) => Right(expr)
      case f: Parsed.Failure if f.index == line.length => Left(ParseError(f.msg, Pos.Zero))
      case f: Parsed.Failure => Left(ParseError(f.msg, Pos.Zero))
    }
  }

  // Function to handle multiple lines of input and parse them as a single expression
  private def handleInput(lines: List[String]): Either[ParseError, ParsedExpr] = {
    val input = lines.mkString("\n")
    parse(input, p => new ParserInternal("repl", ignoreLocation = true)(p).exprEntrance) match {
      case Parsed.Success(expr, _) => Right(expr)
      case f: Parsed.Failure => Left(ParseError(f.msg, Pos.Zero))
    }
  }

  // Function to determine if the input has unclosed brackets, parentheses, or braces
  private def checkUnclosedPairs(input: String): Option[ParseError] = {
    val stack = scala.collection.mutable.Stack[Char]()
    for ((char, index) <- input.zipWithIndex) {
      char match {
        case '(' | '[' | '{' => stack.push(char)
        case ')' =>
          if (stack.isEmpty || stack.pop() != '(') return Some(ParseError(s"Unmatched parenthesis at position $index", Pos.Zero))
        case ']' =>
          if (stack.isEmpty || stack.pop() != '[') return Some(ParseError(s"Unmatched bracket at position $index", Pos.Zero))
        case '}' =>
          if (stack.isEmpty || stack.pop() != '{') return Some(ParseError(s"Unmatched brace at position $index", Pos.Zero))
        case _ =>
      }
    }
    if (stack.nonEmpty) Some(ParseError("Unclosed pair found", Pos.Zero)) else None
  }

  // Function to determine if a line indicates the start of a multi-line input
  private def isMultiLineStart(line: String): Boolean = {
    line.trim.endsWith("\\") || line.contains("(") || line.contains("[") || line.contains("{")
  }

  // Function to add a line to the current input and check if it forms a complete expression
  def addLine(currentLines: List[String], newLine: String): (List[String], REPLResult) = {
    val updatedLines = currentLines :+ newLine
    val input = updatedLines.mkString("\n")

    checkUnclosedPairs(input) match {
      case Some(error) => (currentLines, UnmatchedPair(error))
      case None =>
        if (isMultiLineStart(newLine) || !parseLine(input).isRight) {
          (updatedLines, Incomplete)
        } else {
          val result = handleInput(updatedLines)
          (List.empty[String], Complete(result))
        }
    }
  }
}
