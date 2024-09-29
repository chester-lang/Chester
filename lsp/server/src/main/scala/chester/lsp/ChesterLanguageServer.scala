package chester.lsp

import chester.error.*
import chester.parser.*
import chester.syntax.concrete.*
import chester.tyck.{ExprTycker, TyckResult}
import chester.utils.StringIndex
import fastparse.Parsed
import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.services.*
import scala.collection.mutable
import org.eclipse.lsp4j.{SymbolInformation, WorkspaceSymbolParams}
import java.util.concurrent.CompletableFuture
import scala.jdk.CollectionConverters.*

import java.util.concurrent.CompletableFuture
import scala.compiletime.uninitialized
import scala.jdk.CollectionConverters.*
import org.eclipse.lsp4j.jsonrpc.messages.Either

import java.util.concurrent.CompletableFuture
import scala.concurrent.ExecutionContext.Implicits.global

class ChesterLanguageServer extends LanguageServer with TextDocumentService with WorkspaceService {

  private var client: LanguageClient = uninitialized

  // Store documents' content
  private val documents = mutable.HashMap[String, String]()

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

    // Store the document content
    documents(uri) = text

    val diagnostics = parseAndGenerateDiagnostics(uri, text)
    client.publishDiagnostics(new PublishDiagnosticsParams(uri, diagnostics.asJava))
  }

  override def didChange(params: DidChangeTextDocumentParams): Unit = {
    println(s"Document changed: ${params.getTextDocument.getUri}")
    val uri = params.getTextDocument.getUri
    val changes = params.getContentChanges.asScala.toSeq

    val updatedText = applyChanges(uri, changes)

    val diagnostics = parseAndGenerateDiagnostics(uri, updatedText)
    client.publishDiagnostics(new PublishDiagnosticsParams(uri, diagnostics.asJava))
  }

  private def applyChanges(uri: String, changes: Seq[TextDocumentContentChangeEvent]): String = {
    val currentText = documents.getOrElse(uri, "")

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

    documents(uri) = updatedText
    updatedText
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

  override def didClose(params: DidCloseTextDocumentParams): Unit = {
    val uri = params.getTextDocument.getUri

    // Remove the document from the store
    documents.remove(uri)

    // Clear diagnostics for the closed document
    client.publishDiagnostics(new PublishDiagnosticsParams(uri, List.empty[Diagnostic].asJava))
  }

  // TODO: lsp protocol used UTF-16 but we used unicode index
  private def parseAndGenerateDiagnostics(fileName: String, text: String): List[Diagnostic] = {
    Parser.parseTopLevel(FileNameAndContent(fileName, text)) match {
      case Right(parsedBlock) =>
        ExprTycker.synthesize(parsedBlock) match {
          case TyckResult.Success(_, _, warnings) =>
            // Optionally handle warnings here
            List()
          case TyckResult.Failure(errors, warnings, _, _) =>
            errors.map { error =>
              val range = error.location.map { pos =>
                new Range(
                  new Position(pos.range.start.line, pos.range.start.column.utf16),
                  new Position(pos.range.end.line, pos.range.end.column.utf16)
                )
              }.getOrElse(new Range(new Position(0, 0), new Position(0, 0)))

              new Diagnostic(
                range,
                error.getMessage,
                DiagnosticSeverity.Error,
                "ChesterLanguageServer"
              )
            }.toList
        }
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
        List(diagnostic)
    }
  }


  override def completion(params: CompletionParams): CompletableFuture[Either[java.util.List[CompletionItem], CompletionList]] = {
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

  override def definition(params: DefinitionParams): CompletableFuture[Either[java.util.List[? <: Location], java.util.List[? <: LocationLink]]] = {
    CompletableFuture.supplyAsync(() => {
      // Implement definition lookup logic here
      Either.forLeft(new java.util.ArrayList[Location]())
    })
  }

  override def references(params: ReferenceParams): CompletableFuture[java.util.List[? <: Location]] = {
    CompletableFuture.supplyAsync(() => {
      // Implement references lookup logic here
      new java.util.ArrayList[Location]()
    })
  }

  override def documentSymbol(params: DocumentSymbolParams): CompletableFuture[java.util.List[Either[SymbolInformation, DocumentSymbol]]] = {
    CompletableFuture.supplyAsync(() => {
      // Implement document symbol lookup logic here
      new java.util.ArrayList[Either[SymbolInformation, DocumentSymbol]]()
    })
  }

  override def codeAction(params: CodeActionParams): CompletableFuture[java.util.List[Either[Command, CodeAction]]] = {
    CompletableFuture.supplyAsync(() => {
      // Implement code action logic here
      new java.util.ArrayList[Either[Command, CodeAction]]()
    })
  }

  private val workspaceSymbols = new mutable.HashMap[String, mutable.Set[SymbolInformation]]()

  private def addWorkspaceSymbol(uri: String, symbol: SymbolInformation): Unit = {
    workspaceSymbols.getOrElseUpdate(uri, mutable.Set.empty) += symbol
  }

  private def removeWorkspaceSymbols(uri: String): Unit = {
    workspaceSymbols.remove(uri)
  }

  private def workspaceSymbol(params: WorkspaceSymbolParams): CompletableFuture[java.util.List[? <: SymbolInformation]] = {
    CompletableFuture.supplyAsync(() => {
      val query = params.getQuery.toLowerCase
      val matchingSymbols = workspaceSymbols.values.flatten.filter(_.getName.toLowerCase.contains(query))
      matchingSymbols.toList.asJava
    })
  }
}