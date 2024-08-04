package chester.parser

import munit.FunSuite
import fastparse.*
import chester.syntax.concrete._
import chester.parser._

class VarargParserTest extends FunSuite {

  test("parse function call with single vararg") {
    val input = "func(1, 2, xs*)"
    val expected =
      FunctionCall(
        function = Identifier(
          name = "func",
          
        ),
        telescope = Tuple(
          terms = Vector(
            IntegerLiteral(
              value = 1,
              
            ),
            IntegerLiteral(
              value = 2,
              
            ),
            OpSeq(
              seq = Vector(
                Identifier(
                  name = "xs",
                  
                ),
                Identifier(
                  name = "*",
                  
                )
              ),
              
            )
          ),
          
        ),
        
      )
    parseAndCheck(input, expected)
  }

  test("parse function def with single vararg") {
    val input = "func(x : Integer *)"
    val expected =
      FunctionCall(
        function = Identifier(
          name = "func",
          
        ),
        telescope = Tuple(
          terms = Vector(
            OpSeq(
              seq = Vector(
                Identifier(
                  name = "x",
                  
                ),
                Identifier(
                  name = ":",
                  
                ),
                Identifier(
                  name = "Integer",
                  
                ),
                Identifier(
                  name = "*",
                  
                )
              ),
              
            )
          ),
          
        ),
        
      )
    parseAndCheck(input, expected)
  }

}
