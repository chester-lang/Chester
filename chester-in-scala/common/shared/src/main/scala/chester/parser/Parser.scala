package chester.parser
import chester.parser._
import chester.error.*
import chester.syntax.IdentifierRules.*
import chester.syntax.QualifiedIDString
import chester.syntax.concrete.*
import chester.utils.parse.*
import chester.utils._
import fastparse.*
import fastparse.NoWhitespace.*

import java.nio.file.{Files, Paths}
import scala.collection.immutable
import scala.util.*
import scala.scalajs.js.annotation._


case class FilePath(path: String) extends ParserSource {
  override def getContentFromSource: Either[ParseError, (String, String)] = {
    Try(readFileFrom(path)) match {
      case Success(content) =>
        val fileName = normalizeFilePath(path)
        Right((fileName, content))
      case Failure(exception) =>
        Left(ParseError(s"Failed to read file: ${exception.getMessage}", Pos.Zero))
    }
  }
}
