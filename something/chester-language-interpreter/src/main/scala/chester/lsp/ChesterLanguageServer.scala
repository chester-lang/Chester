package chester.lsp

import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.services.*

import java.util.concurrent.CompletableFuture
import chester.parser.Parser
import chester.error.*
import chester.syntax.concrete.*
import chester.utils.StringIndex
import fastparse.Parsed
import scala.jdk.CollectionConverters._

class ChesterLanguageServer extends LanguageServer with TextDocumentService with WorkspaceService {
  override def initialize(params: InitializeParams): CompletableFuture[InitializeResult] = {
    val capabilities = new ServerCapabilities()
    capabilities.setTextDocumentSync(TextDocumentSyncKind.Full)
    // Add other capabilities here

    CompletableFuture.completedFuture(new InitializeResult(capabilities))
  }

  override def getTextDocumentService: TextDocumentService = this

  override def getWorkspaceService: WorkspaceService = this

  override def didOpen(params: DidOpenTextDocumentParams): Unit = {
    val text = params.getTextDocument.getText
    val fileName = params.getTextDocument.getUri
    val diagnostics = parseAndGenerateDiagnostics(fileName, text)
    // Send diagnostics to client
  }

  override def didChange(params: DidChangeTextDocumentParams): Unit = {
    val text = params.getContentChanges.get(0).getText
    val fileName = params.getTextDocument.getUri
    val diagnostics = parseAndGenerateDiagnostics(fileName, text)
    // Send diagnostics to client
  }

  private def parseAndGenerateDiagnostics(fileName: String, text: String): List[Diagnostic] = {
    val parsed = Parser.parseExpression(fileName, text)
    val index = StringIndex(text)

    parsed match {
      case Parsed.Success(expr, index) =>
        List() // No errors
      case Parsed.Failure(label, charIndex, extra) =>
        val start = index.charIndexToUnicodeLineAndColumn(charIndex)
        val end = index.charIndexToUnicodeLineAndColumn(charIndex)

        val diagnostic = new Diagnostic(
          new Range(
            new Position(start.line, start.column),
            new Position(end.line, end.column)
          ),
          label,
          DiagnosticSeverity.Error,
          "ChesterLanguageServer"
        )
        List(diagnostic)
    }
  }

  import org.eclipse.lsp4j.jsonrpc.messages.Either
  import java.util.concurrent.CompletableFuture

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
    val textDocument = params.getTextDocument.getUri
    val hoverInfo = provideHoverInfo(textDocument, position)
    CompletableFuture.completedFuture(new Hover(hoverInfo))
  }

  private def provideHoverInfo(uri: String, position: Position): MarkupContent = {
    // Implement logic to provide hover information based on the position
    val content = new MarkupContent()
    content.setKind("markdown")
    content.setValue("Example hover information")
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
