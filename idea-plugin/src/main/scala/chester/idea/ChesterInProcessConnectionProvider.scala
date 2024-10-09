package chester.idea

import chester.lsp.ChesterLanguageServer
import com.redhat.devtools.lsp4ij.server.StreamConnectionProvider
import org.eclipse.lsp4j.jsonrpc.Launcher
import org.eclipse.lsp4j.services.LanguageClient

import java.io.{PipedInputStream, PipedOutputStream}
import java.util.concurrent.Executors

class ChesterInProcessConnectionProvider extends StreamConnectionProvider {
  private val executorService = Executors.newCachedThreadPool()

  private val inputToServer = new PipedInputStream()
  private val outputFromClient = new PipedOutputStream(inputToServer)

  private val inputFromServer = new PipedInputStream()
  private val outputToClient = new PipedOutputStream(inputFromServer)

  private var connected: Boolean = false

  override def start(): Unit = {
    val server = new ChesterLanguageServer()
    val launcher = new Launcher.Builder[LanguageClient]()
      .setLocalService(server)
      .setRemoteInterface(classOf[LanguageClient])
      .setInput(inputToServer)
      .setOutput(outputToClient)
      .setExecutorService(executorService)
      .create()

    server.connect(launcher.getRemoteProxy)

    val listening = launcher.startListening()

    connected = true

    // Handle exceptions without using 'exceptionally'
    executorService.submit(new Runnable {
      override def run(): Unit = {
        try {
          listening.get()
        } catch {
          case ex: Exception =>
            ex.printStackTrace()
        }
      }
    })
  }

  override def stop(): Unit = {
    executorService.shutdownNow()
    connected = false
  }

  override def isAlive: Boolean = connected

  override def getInputStream: java.io.InputStream = inputFromServer

  override def getOutputStream: java.io.OutputStream = outputFromClient

  // override def getErrorStream: java.io.InputStream = null
}
