package chester.build

import ch.epfl.scala.bsp4j._
import org.eclipse.lsp4j.jsonrpc.Launcher
import java.util.concurrent.Executors

object Main {
  def main(args: Array[String]): Unit = {
    val server = new ChesterBuildServer()

    val executorService = Executors.newFixedThreadPool(4)

    val launcher = new Launcher.Builder[BuildClient]()
      .setOutput(System.out)
      .setInput(System.in)
      .setLocalService(server)
      .setRemoteInterface(classOf[BuildClient])
      .setExecutorService(executorService)
      .create()

    val client = launcher.getRemoteProxy
    server.onConnectWithClient(client)

    val listening = launcher.startListening()

    // Wait for the listening to complete (e.g., when the client disconnects)
    listening.get()

    // Shutdown the executor service
    executorService.shutdown()
  }
}