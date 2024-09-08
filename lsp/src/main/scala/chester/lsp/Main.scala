package chester.lsp

import org.eclipse.lsp4j.launch.LSPLauncher

import java.io.{InputStream, OutputStream}
import java.util.concurrent.Executors

object Main {
  def main(args: Array[String]): Unit = {
    val executorService = Executors.newCachedThreadPool()
    val server = new ChesterLanguageServer()
    val launcher = LSPLauncher.createServerLauncher(server, System.in, System.out)
    val client = launcher.getRemoteProxy
    server.connect(client)
    launcher.startListening()
  }
}