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
    val expected = BinOpSeq(Vector(Identifier("notification"), Identifier("match"), Block(Vector(BinOpSeq(Vector(Identifier("case"), FunctionCall(Identifier("Email"),Telescope(Vector(Arg(Vector(),None,None,Some(Identifier("sender")),false), Arg(Vector(),None,None,Some(Identifier("title")),false), Arg(Vector(),None,None,Some(Identifier("_")),false)),false,None),None), Identifier("=>"), Identifier("A")),None), BinOpSeq(Vector(Identifier("case"), FunctionCall(Identifier("SMS"),Telescope(Vector(Arg(Vector(),None,None,Some(Identifier("number")),false), Arg(Vector(),None,None,Some(Identifier("message")),false)),false,None),None), Identifier("=>"), Identifier("B")),None), BinOpSeq(Vector(Identifier("case"), FunctionCall(Identifier("VoiceRecording"),Telescope(Vector(Arg(Vector(),None,None,Some(Identifier("name")),false), Arg(Vector(),None,None,Some(Identifier("link")),false)),false,None),None), Identifier("=>"), Identifier("C")),None)),None,None)),None)
    parseAndCheck(input, expected)
  }

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
    val expected = Parser.parseContent("testFile", input, ignoreLocation = true).toOption.get
    parseAndCheck(input, expected)
  }
}
