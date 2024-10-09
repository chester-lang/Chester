package chester.parser.Parser
import chester.parser.*
import chester.syntax.QualifiedIDString
import chester.syntax.concrete.*
import chester.utils.StringIndex
import fastparse.*
import chester.error.*
import io.github.iltotore.iron.*

import scala.collection.immutable
import scala.util.*

private def parseFromSource[T](
    source: ParserSource,
    parserFunc: ParserInternal => P[T],
    ignoreLocation: Boolean = false
): Either[ParseError, T] = {
  source.readContent match {
    case Right(content) =>
      val indexer = StringIndex(content)
      parse(
        content,
        x =>
          parserFunc(
            ParserInternal(
              SourceOffset(source),
              ignoreLocation = ignoreLocation,
              defaultIndexer = Some(indexer)
            )(x)
          )
      ) match {
        case Parsed.Success(result, _) => Right(result)
        case Parsed.Failure(_, index, extra) =>
          val pos = indexer.charIndexToLineAndColumnWithUTF16(index)
          val p = Pos(
            indexer.charIndexToWithUTF16(index.refineUnsafe),
            pos.line,
            pos.column
          )
          Left(ParseError(s"Parsing failed: ${extra.trace().longMsg}", p))
      }
    case Left(error) => Left(error)
  }
}

def parseStatements(
    source: ParserSource,
    ignoreLocation: Boolean = false
): Either[ParseError, Vector[ParsedExpr]] = {
  parseFromSource(source, _.statementsEntrance, ignoreLocation)
}

def parseTopLevel(
    source: ParserSource,
    ignoreLocation: Boolean = false
): Either[ParseError, Block] = {
  parseFromSource(source, _.toplevelEntrance, ignoreLocation)
}

def parseExpr(
    source: ParserSource,
    ignoreLocation: Boolean = false
): Either[ParseError, ParsedExpr] = {
  parseFromSource(source, _.exprEntrance, ignoreLocation)
}

@deprecated("Create new module representation")
def extractModuleName(block: Block): Either[ParseError, QualifiedIDString] = {
  block.heads.headOption match {
    case Some(OpSeq(Vector(Identifier("module", _), identifiers*), _)) =>
      val names = identifiers.collect { case Identifier(name, _) =>
        name
      }.toVector
      if (names.nonEmpty) Right(names)
      else Left(ParseError("Module identifiers could not be parsed", Pos.Zero))
    case _ => Right(Vector.empty)
  }
}

@deprecated("Create new module representation")
def parseModule(
    source: ParserSource,
    modules: ResolvingModules = ResolvingModules.Empty,
    ignoreLocation: Boolean = false
): Either[ParseError, ResolvingModules] = {
  parseTopLevel(source, ignoreLocation).flatMap { block =>
    extractModuleName(block).map { id =>
      val moduleFile = ResolvingModuleFile(
        id,
        source.fileName,
        ResolvingBlock.fromParsed(block)
      )
      modules.addModuleFile(id, moduleFile)
    }
  }
}
