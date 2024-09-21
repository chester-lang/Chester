package chester.parser

import upickle.default.*
import chester.error.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.upickle.given
import chester.utils.doc.{Doc, PrettierOptions}

case class ParseError(message: String, index: Pos) extends Problem {
  override def level: Problem.Severity = Problem.Severity.ERROR
  override def stage: Problem.Stage = Problem.Stage.PARSE

  override def toDoc(using options: PrettierOptions): Doc = Doc.text(message)
}

sealed trait ParserSource derives ReadWriter {
  def fileName: String

  def readContent: Either[ParseError, String]
}

case class FileNameAndContent(fileName: String, content: String) extends ParserSource derives ReadWriter {
  override def readContent: Either[ParseError, String] = Right(content)
}

trait FilePathImpl {
  def load: Unit
  def readContent(fileName: String): Either[ParseError, String]

  def absolute(fileName: String): String
}

private var impl: FilePathImpl = null

object FilePath {
  def apply(fileName: String)(using used: FilePathImpl): FilePath = {
    used.load
    new FilePath(fileName)
  }
  def from(fileName: String)(using used: FilePathImpl): FilePath = {
    used.load
    val path = if (impl == null) fileName else impl.absolute(fileName)
    new FilePath(path)
  }
}

case class FilePath private (fileName: String) extends ParserSource {
  override lazy val readContent: Either[ParseError, String] = {
    if (impl == null) Left(ParseError("No FilePathImpl provided", Pos.Zero))
    else impl.readContent(fileName)
  }
}

case class SourceOffset(source: ParserSource, linesOffset: Int :| Positive0 = 0, posOffset: Int :| Positive0 = 0)derives ReadWriter {
  def fileName: String = source.fileName

  def readContent: Either[ParseError, String] = source.readContent
}