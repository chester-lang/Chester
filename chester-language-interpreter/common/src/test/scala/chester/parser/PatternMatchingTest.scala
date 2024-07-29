package chester.parser

import munit.FunSuite
import chester.syntax.concrete._
import chester.parser._

class PatternMatchingTest extends FunSuite {
  test("match") {
    val input =
      """
        |  notification match {
        |    case Email(sender, title, _) => A;
        |    case SMS(number, message) => B;
        |    case VoiceRecording(name, link) => C;
        |  }
        |""".stripMargin
    val expected = Parser.parseContent("testFile", input, ignoreLocation = true)
    parseAndCheck(input, expected.right.get)
  }
}
