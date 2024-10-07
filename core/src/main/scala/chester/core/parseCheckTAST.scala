package chester.core

import chester.error.*
import chester.parser.*
import chester.syntax.*
import chester.syntax.core.*
import chester.syntax.tyck.*
import chester.tyck.*

def parseCheckTAST(
                    source: ParserSource,
                    ignoreLocation: Boolean = false
                  )(using reporter: Reporter[Problem]): chester.syntax.TAST = {
  // Parse the source code into an Expr using parseTopLevel
  Parser.parseTopLevel(source, ignoreLocation) match {
    case Right(expr) =>
      // Type-check the parsed expression
      checkTop(source.fileName, expr, reporter)
    case Left(error) =>
      // Report the parsing error
      reporter(error)

      // Return an empty TAST or handle accordingly
      TAST(
        fileName = source.fileName,
        module = DefaultModule,
        ast = BlockTerm(Vector.empty, UnitTerm()),
        meta = TyckMeta(Vector.empty),
        ty = UnitType(),
        effects = NoEffect,
        problems = SeverityMap.Empty.copy(error=true)
      )
  }
}