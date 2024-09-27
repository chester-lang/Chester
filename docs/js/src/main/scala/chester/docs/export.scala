package chester.docs

import chester.doc.const.LightMode
import chester.parser.{FileNameAndContent, Parser}
import chester.tyck.{ExprTycker, TyckResult}
import chester.utils.doc.*

import scala.scalajs.js.annotation.JSExportTopLevel
import chester.utils.doc.ColorfulToHtml.colorfulToHtml

@JSExportTopLevel("chesterRunFile")
def chesterRunFile(content: String): String = {
  implicit val options: PrettierOptions = PrettierOptions.Default.updated(LightMode, false)
  Parser.parseTopLevel(FileNameAndContent("playground.chester", content)) match {
    case Right(parsedBlock) =>
      ExprTycker.synthesize(parsedBlock) match {
        case TyckResult.Success(result, status, warnings) =>
          colorfulToHtml(ColorfulPrettyPrinter.render(result.wellTyped))
        case TyckResult.Failure(errors, warnings, state, result) =>
          s"Failed to type check file: $errors"
      }
    case Left(error) =>
      error.message
  }
}
