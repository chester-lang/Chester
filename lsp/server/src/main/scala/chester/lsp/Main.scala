package chester.lsp

import org.eclipse.lsp4j.launch.LSPLauncher
import java.util.concurrent.{Executors, ExecutorService}

object Main {
  private var executorService: ExecutorService = scala.compiletime.uninitialized

  def main(args: Array[String]): Unit = {
    executorService = Executors.newCachedThreadPool()
    val server = new ChesterLanguageServer()
    val launcher = LSPLauncher.createServerLauncher(server, System.in, System.out, executorService, null)
    val client = launcher.getRemoteProxy
    server.connect(client)
    launcher.startListening()
  }

  def shutdown(): Unit = {
    if (executorService != null && !executorService.isShutdown) {
      executorService.shutdown()
    }
  }
}