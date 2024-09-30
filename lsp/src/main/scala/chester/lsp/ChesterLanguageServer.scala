package chester.lsp

import chester.error.*
import chester.parser.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.tyck.{ExprTycker, TyckResult, TyckState, TyckSymbol}
import chester.utils.{StringIndex, WithUTF16}
import fastparse.Parsed
import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.services.*

import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.jdk.CollectionConverters.*
import scala.compiletime.uninitialized
import io.github.iltotore.iron.*
import java.util.concurrent.CompletableFuture
import org.eclipse.lsp4j.jsonrpc.messages.Either
import java.util.{List => JList}

class ChesterLanguageServer extends LanguageServer with TextDocumentService with WorkspaceService {

  private var client: LanguageClient = uninitialized

  // Store documents' content
  private val documents = mutable.Map[String, DocumentInfo]()

  def connect(client: LanguageClient): Unit = {
    this.client = client
    println("Language client connected to the server")
  }

  override def initialize(params: InitializeParams): CompletableFuture[InitializeResult] = {
    println(s"Initializing with params: $params")
    val capabilities = new ServerCapabilities()
    capabilities.setTextDocumentSync(TextDocumentSyncKind.Incremental)
    capabilities.setCompletionProvider(new CompletionOptions(false, List(".", ":").asJava))
    capabilities.setHoverProvider(true)
    capabilities.setDefinitionProvider(true)
    capabilities.setReferencesProvider(true)
    capabilities.setDocumentSymbolProvider(true)
    capabilities.setWorkspaceSymbolProvider(true)
    capabilities.setCodeActionProvider(true)
    CompletableFuture.completedFuture(new InitializeResult(capabilities))
  }

  override def getTextDocumentService: TextDocumentService = this

  override def getWorkspaceService: WorkspaceService = this

  override def didOpen(params: DidOpenTextDocumentParams): Unit = {
    println(s"Document opened: ${params.getTextDocument.getUri}")
    val uri = params.getTextDocument.getUri
    val text = params.getTextDocument.getText

    // Process the document and get TyckResult and diagnostics
    val (tyckResult, diagnostics, symbols) = processDocument(uri, text)

    // Store the DocumentInfo in the documents map
    documents.synchronized {
      documents(uri) = DocumentInfo(content = text, tyckResult = tyckResult, symbols = symbols)
    }

    // Publish diagnostics
    client.publishDiagnostics(new PublishDiagnosticsParams(uri, diagnostics.asJava))
  }

  override def didChange(params: DidChangeTextDocumentParams): Unit = {
    println(s"Document changed: ${params.getTextDocument.getUri}")
    val uri = params.getTextDocument.getUri
    val changes = params.getContentChanges.asScala.toSeq

    // Update the document content
    val updatedText = applyChanges(uri, changes)

    // Process the updated document
    val (tyckResult, diagnostics, symbols) = processDocument(uri, updatedText)

    // Update the DocumentInfo with the new content and TyckResult
    documents.synchronized {
      documents.get(uri).foreach { _ =>
        documents(uri) = DocumentInfo(content = updatedText, tyckResult = tyckResult, symbols = symbols)
      }
    }

    // Publish diagnostics
    client.publishDiagnostics(new PublishDiagnosticsParams(uri, diagnostics.asJava))
  }

  private def applyChanges(uri: String, changes: Seq[TextDocumentContentChangeEvent]): String = {
    val currentText = documents.synchronized {
      documents.get(uri).map(_.content).getOrElse("")
    }

    val updatedText = changes.foldLeft(currentText) { (text, change) =>
      if (change.getRange == null) {
        // The change is the new full content
        change.getText
      } else {
        // Apply incremental change
        val startOffset = getOffset(text, change.getRange.getStart)
        val endOffset = getOffset(text, change.getRange.getEnd)
        text.substring(0, startOffset) + change.getText + text.substring(endOffset)
      }
    }

    updatedText // Return the updated text without modifying documents
  }

  private def getOffset(text: String, position: Position): Int = {
    var offset = 0
    var line = 0
    var charIndex = 0

    while (offset < text.length && line < position.getLine) {
      if (text.charAt(offset) == '\n') {
        line += 1
      }
      offset += 1
    }

    while (offset < text.length && charIndex < position.getCharacter) {
      if (text.charAt(offset) == '\n') {
        // Shouldn't happen, but safeguard
        return offset
      }
      offset += 1
      charIndex += 1
    }

    offset
  }

  private def getLineStartOffset(text: String, lineNumber: Int): Int = {
    val lines = text.split('\n')
    val safeLineNumber = lineNumber.min(lines.length - 1).max(0)
    lines.take(safeLineNumber).map(_.length + 1).sum // +1 for '\n' character
  }

  override def didClose(params: DidCloseTextDocumentParams): Unit = {
    val uri = params.getTextDocument.getUri

    // Remove the document from the store
    documents.remove(uri)

    // Clear diagnostics for the closed document
    client.publishDiagnostics(new PublishDiagnosticsParams(uri, List.empty[Diagnostic].asJava))
  }

  def processDocument(uri: String, text: String): (TyckResult[TyckState, Judge], List[Diagnostic], Set[TyckSymbol]) = {
    val parseResult = Parser.parseTopLevel(FileNameAndContent(uri, text))

    parseResult match {
      case Right(parsedExpr) =>
        val tyckResult = ExprTycker.synthesize(parsedExpr)

        val symbols = tyckResult.state.symbols

        // Generate diagnostics from the TyckResult
        val diagnostics = tyckResult match {
          case TyckResult.Success(_, _, warnings) =>
            // Handle warnings if needed
            List()
          case TyckResult.Failure(errors, warnings, _, _) =>
            errors.map { error =>
              val range = error.location.map { pos =>
                rangeFromSourcePos(pos)
              }.getOrElse(new Range(new Position(0, 0), new Position(0, 0)))

              new Diagnostic(
                range,
                error.getMessage,
                DiagnosticSeverity.Error,
                "ChesterLanguageServer"
              )
            }.toList
        }

        (tyckResult, diagnostics, symbols)

      case Left(parseError) =>
        val range = new Range(
          new Position(parseError.index.line, parseError.index.column.utf16),
          new Position(parseError.index.line, parseError.index.column.utf16)
        )
        val diagnostic = new Diagnostic(
          range,
          parseError.message,
          DiagnosticSeverity.Error,
          "ChesterLanguageServer"
        )
        val tyckResult = TyckResult(
          state = TyckState(),
          result = Judge(ErrorTerm(parseError), ErrorTerm(parseError), NoEffect)
        )
        (tyckResult, List(diagnostic), Set.empty)
    }
  }

  override def completion(params: CompletionParams): CompletableFuture[Either[JList[CompletionItem], CompletionList]] = {
    val position = params.getPosition
    val textDocument = params.getTextDocument.getUri
    val completions = provideCompletions(textDocument, position)
    val completionList = new CompletionList(completions.asJava)
    CompletableFuture.completedFuture(Either.forRight(completionList))
  }

  private def provideCompletions(uri: String, position: Position): List[CompletionItem] = {
    // Implement logic to provide code completions based on the position
    List(new CompletionItem("exampleCompletion"))
  }

  override def hover(params: HoverParams): CompletableFuture[Hover] = {
    val position = params.getPosition
    val uri = params.getTextDocument.getUri
    val contents = provideHoverInformation(uri, position)
    CompletableFuture.completedFuture(new Hover(contents))
  }

  private def provideHoverInformation(uri: String, position: Position): MarkupContent = {
    // Logic to fetch hover information based on position
    val content = new MarkupContent()
    content.setKind("markdown")
    content.setValue("Detailed information about the symbol at the cursor.")
    content
  }

  // LanguageServer methods
  override def exit(): Unit = {
    // Handle server exit logic here
  }

  override def shutdown(): CompletableFuture[Object] = {
    // Handle server shutdown logic here
    CompletableFuture.completedFuture(null)
  }

  // TextDocumentService methods
  override def didSave(params: DidSaveTextDocumentParams): Unit = {
    // Handle text document save event here
  }

  // WorkspaceService methods
  override def didChangeConfiguration(params: DidChangeConfigurationParams): Unit = {
    // Handle workspace configuration change here
  }

  override def didChangeWatchedFiles(params: DidChangeWatchedFilesParams): Unit = {
    // Handle watched files change event here
  }

  private def sourcePosFromLSP(uri: String, position: Position): Option[SourcePos] = {
    documents.synchronized {
      documents.get(uri)
    } match {
      case Some(document) =>
        val text = document.content
        val stringIndex = StringIndex(text)

        // LSP positions are in UTF-16 code units
        val line = position.getLine
        val utf16Column = position.getCharacter

        // Calculate the character index in the text
        val lineStartOffset = getLineStartOffset(text, line)
        val charIndexUtf16 = lineStartOffset + utf16Column
        val codepointIndex = stringIndex.charIndexToUnicodeIndex(charIndexUtf16.refineUnsafe)

        // Create WithUTF16 instance
        val withUTF16 = WithUTF16(codepointIndex, charIndexUtf16.refineUnsafe)

        val pos = Pos(
          index = withUTF16,
          line = line.refineUnsafe,
          column = withUTF16
        )
        val range = RangeInFile(start = pos, end = pos)

        val source = SourceOffset(FileNameAndContent(uri, text))
        Some(SourcePos(source, range))

      case None =>
        None
    }
  }

  private def rangeFromSourcePos(sourcePos: SourcePos): Range = {
    val startLine = sourcePos.range.start.line
    val startCharacter = sourcePos.range.start.column.utf16
    val endLine = sourcePos.range.end.line
    val endCharacter = sourcePos.range.end.column.utf16

    val start = new Position(startLine, startCharacter)
    val end = new Position(endLine, endCharacter)
    new Range(start, end)
  }

  override def definition(
                           params: DefinitionParams
                         ): CompletableFuture[Either[JList[? <: Location], JList[? <: LocationLink]]] = {
    CompletableFuture.supplyAsync { () =>
      val uri = params.getTextDocument.getUri
      val position = params.getPosition

      val documentOpt = documents.synchronized {
        documents.get(uri)
      }

      documentOpt match {
        case Some(document) =>
          sourcePosFromLSP(uri, position) match {
            case Some(sourcePos) =>
              val symbolOpt = document.symbols.find { sym =>
                positionsEqual(sym.definitionPos, sourcePos) || sym.references.exists(ref => positionsEqual(ref, sourcePos))
              }
              symbolOpt match {
                case Some(symbolInfo) =>
                  val location = new Location(
                    symbolInfo.definitionPos.fileName,
                    rangeFromSourcePos(symbolInfo.definitionPos)
                  )
                  val locations = java.util.Collections.singletonList(location)
                  Either.forLeft(locations)
                case None =>
                  Either.forLeft(java.util.Collections.emptyList())
              }
            case None =>
              Either.forLeft(java.util.Collections.emptyList())
          }

        case None =>
          Either.forLeft(java.util.Collections.emptyList())
      }
    }
  }

  private def positionsEqual(pos1: SourcePos, pos2: SourcePos): Boolean = {
    pos1.source.fileName == pos2.source.fileName &&
      pos1.range.start.line == pos2.range.start.line &&
      pos1.range.start.column.utf16 == pos2.range.start.column.utf16 &&
      pos1.range.end.line == pos2.range.end.line &&
      pos1.range.end.column.utf16 == pos2.range.end.column.utf16
  }

  override def references(params: ReferenceParams): CompletableFuture[JList[? <: Location]] = {
    CompletableFuture.supplyAsync { () =>
      val uri = params.getTextDocument.getUri
      val position = params.getPosition

      val documentOpt = documents.synchronized {
        documents.get(uri)
      }

      documentOpt match {
        case Some(document) =>
          sourcePosFromLSP(uri, position) match {
            case Some(sourcePos) =>
              val symbolOpt = document.symbols.find { sym =>
                positionsEqual(sym.definitionPos, sourcePos) || sym.references.exists(ref => positionsEqual(ref, sourcePos))
              }
              symbolOpt match {
                case Some(symbolInfo) =>
                  val locations = (symbolInfo.references + symbolInfo.definitionPos).map { refPos =>
                    new Location(refPos.fileName, rangeFromSourcePos(refPos))
                  }
                  new java.util.ArrayList(locations.toList.asJava)
                case None =>
                  java.util.Collections.emptyList()
              }
            case None =>
              java.util.Collections.emptyList()
          }

        case None =>
          java.util.Collections.emptyList()
      }
    }
  }

  override def documentSymbol(
                               params: DocumentSymbolParams
                             ): CompletableFuture[JList[Either[SymbolInformation, DocumentSymbol]]] = {
    CompletableFuture.supplyAsync(() => {
      // Implement document symbol lookup logic here
      new java.util.ArrayList[Either[SymbolInformation, DocumentSymbol]]()
    })
  }

  override def codeAction(
                           params: CodeActionParams
                         ): CompletableFuture[JList[Either[Command, CodeAction]]] = {
    CompletableFuture.supplyAsync(() => {
      // Implement code action logic here
      new java.util.ArrayList[Either[Command, CodeAction]]()
    })
  }

  override def symbol(
                       params: WorkspaceSymbolParams
                     ): CompletableFuture[Either[JList[? <: SymbolInformation], JList[? <: WorkspaceSymbol]]] = {
    CompletableFuture.supplyAsync(() => {
      val query = params.getQuery.toLowerCase
      val allSymbols = documents.synchronized {
        documents.values.flatMap(_.symbols).toList
      }

      val matchingSymbols = allSymbols.filter { symbol =>
        symbol.name.toString.toLowerCase.contains(query)
      }.map(tyckSymbolToLSP)

      Either.forLeft(matchingSymbols.asJava)
    })
  }

  private def tyckSymbolToLSP(symbol: TyckSymbol): SymbolInformation = {
    val location = new Location(
      symbol.definitionPos.fileName,
      rangeFromSourcePos(symbol.definitionPos)
    )
    new SymbolInformation(
      symbol.name.toString,
      SymbolKind.Variable,
      location
    )
  }
}

case class DocumentInfo(
                         content: String,
                         tyckResult: TyckResult[TyckState, Judge],
                         symbols: Set[TyckSymbol]
                       )