package chester.idea

import com.intellij.openapi.project.Project
import com.redhat.devtools.lsp4ij.LanguageServerFactory
import com.redhat.devtools.lsp4ij.client.LanguageClientImpl
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider

class ChesterLanguageServerFactory extends LanguageServerFactory {
  override def createConnectionProvider(
      project: Project
  ): StreamConnectionProvider = {
    new ChesterInProcessConnectionProvider()
  }

  override def createLanguageClient(project: Project): LanguageClientImpl = {
    new ChesterLanguageClient(project)
  }
}
