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
    val expected = OpSeq(Vector(Identifier("notification"), Identifier("match"), Block(Vector(OpSeq(Vector(Identifier("case"), FunctionCall(Identifier("Email"),Tuple(Vector(Identifier("sender"), Identifier("title"), Identifier("_")),None),None), Identifier("=>"), Identifier("A")),None), OpSeq(Vector(Identifier("case"), FunctionCall(Identifier("SMS"),Tuple(Vector(Identifier("number"), Identifier("message")),None),None), Identifier("=>"), Identifier("B")),None), OpSeq(Vector(Identifier("case"), FunctionCall(Identifier("VoiceRecording"),Tuple(Vector(Identifier("name"), Identifier("link")),None),None), Identifier("=>"), Identifier("C")),None)),None,None)),None)
    parseAndCheck(input, expected)
  }

  // TODO
  test("match2") {
    val input =
      """
        |  notification match {
        |    case Email(sender, title, _) => {
        |      println(sender);
        |      println(title);
        |    }
        |    case SMS(number, message) => B;
        |    case VoiceRecording(name, link) => C;
        |    case _ => D;
        |  }
        |""".stripMargin
    val expected = getParsed(input)
    parseAndCheck(input, expected)
  }
}
