package chester.parser

import chester.parser.*
import chester.syntax.concrete.*
import munit.FunSuite

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
    val expected = OpSeq(
      Vector(
        Identifier("notification"),
        Identifier("match"),
        Block(
          Vector(
            OpSeq(
              Vector(
                Identifier("case"),
                FunctionCall(
                  Identifier("Email"),
                  Tuple(
                    Vector(
                      Identifier("sender"),
                      Identifier("title"),
                      Identifier("_")
                    ),
                    None
                  ),
                  None
                ),
                Identifier("=>"),
                Identifier("A")
              ),
              None
            ),
            OpSeq(
              Vector(
                Identifier("case"),
                FunctionCall(
                  Identifier("SMS"),
                  Tuple(
                    Vector(Identifier("number"), Identifier("message")),
                    None
                  ),
                  None
                ),
                Identifier("=>"),
                Identifier("B")
              ),
              None
            ),
            OpSeq(
              Vector(
                Identifier("case"),
                FunctionCall(
                  Identifier("VoiceRecording"),
                  Tuple(Vector(Identifier("name"), Identifier("link")), None),
                  None
                ),
                Identifier("=>"),
                Identifier("C")
              ),
              None
            )
          ),
          None,
          None
        )
      ),
      None
    )
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
    val expected = OpSeq(
      Vector(
        Identifier("notification"),
        Identifier("match"),
        Block(
          Vector(
            OpSeq(
              Vector(
                Identifier("case"),
                FunctionCall(
                  Identifier("Email"),
                  Tuple(
                    Vector(
                      Identifier("sender"),
                      Identifier("title"),
                      Identifier("_")
                    ),
                    None
                  ),
                  None
                ),
                Identifier("=>"),
                Block(
                  Vector(
                    FunctionCall(
                      Identifier("println"),
                      Tuple(Vector(Identifier("sender")), None),
                      None
                    ),
                    FunctionCall(
                      Identifier("println"),
                      Tuple(Vector(Identifier("title")), None),
                      None
                    )
                  ),
                  None,
                  None
                )
              ),
              None
            ),
            OpSeq(
              Vector(
                Identifier("case"),
                FunctionCall(
                  Identifier("SMS"),
                  Tuple(
                    Vector(Identifier("number"), Identifier("message")),
                    None
                  ),
                  None
                ),
                Identifier("=>"),
                Identifier("B")
              ),
              None
            ),
            OpSeq(
              Vector(
                Identifier("case"),
                FunctionCall(
                  Identifier("VoiceRecording"),
                  Tuple(Vector(Identifier("name"), Identifier("link")), None),
                  None
                ),
                Identifier("=>"),
                Identifier("C")
              ),
              None
            ),
            OpSeq(
              Vector(
                Identifier("case"),
                Identifier("_"),
                Identifier("=>"),
                Identifier("D")
              ),
              None
            )
          ),
          None,
          None
        )
      ),
      None
    )
    parseAndCheck(input, expected)
  }
}
