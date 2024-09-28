package chester.lsp

import chester.error.*
import chester.parser.*
import chester.syntax.concrete.*
import chester.tyck.{ExprTycker, TyckResult}
import chester.utils.StringIndex
import fastparse.Parsed
import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.services.*

import java.util.concurrent.CompletableFuture
import scala.compiletime.uninitialized
import scala.jdk.CollectionConverters.*
import org.eclipse.lsp4j.jsonrpc.messages.Either

import java.util.concurrent.CompletableFuture

class ChesterLanguageServer extends LanguageServer with TextDocumentService with WorkspaceService {

  private var client: LanguageClient = uninitialized

  def connect(client: LanguageClient): Unit = {
    this.client = client
  }

  override def initialize(params: InitializeParams): CompletableFuture[InitializeResult] = {
    val capabilities = new ServerCapabilities()
    capabilities.setTextDocumentSync(TextDocumentSyncKind.Full)
    capabilities.setCompletionProvider(new CompletionOptions())
    capabilities.setHoverProvider(true)
    // Declare other supported features
    CompletableFuture.completedFuture(new InitializeResult(capabilities))
  }

  override def getTextDocumentService: TextDocumentService = this

  override def getWorkspaceService: WorkspaceService = this

  override def didOpen(params: DidOpenTextDocumentParams): Unit = {
    val text = params.getTextDocument.getText
    val uri = params.getTextDocument.getUri
    val diagnostics = parseAndGenerateDiagnostics(uri, text)
    client.publishDiagnostics(new PublishDiagnosticsParams(uri, diagnostics.asJava))
  }

  override def didChange(params: DidChangeTextDocumentParams): Unit = {
    val text = params.getContentChanges.get(0).getText
    val uri = params.getTextDocument.getUri
    val diagnostics = parseAndGenerateDiagnostics(uri, text)
    client.publishDiagnostics(new PublishDiagnosticsParams(uri, diagnostics.asJava))
  }

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
                  new Position(pos.range.start.line, pos.range.start.column),
                  new Position(pos.range.end.line, pos.range.end.column)
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
          new Position(parseError.index.line, parseError.index.column),
          new Position(parseError.index.line, parseError.index.column)
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
  override def didClose(params: DidCloseTextDocumentParams): Unit = {
    // Handle text document close event here
  }

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
}
