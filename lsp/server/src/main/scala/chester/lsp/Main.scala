package chester.lsp

import org.eclipse.lsp4j.launch.LSPLauncher
import java.net.ServerSocket
import java.io.InputStream
import java.io.OutputStream

object Main {
  def main(args: Array[String]): Unit = {
    val port = if (args.nonEmpty) args(0).toInt else 1044
    val serverSocket = new ServerSocket(port)
    println(s"Chester Language Server listening on port $port")
    val clientSocket = serverSocket.accept()
    val in: InputStream = clientSocket.getInputStream
    val out: OutputStream = clientSocket.getOutputStream

    val server = new ChesterLanguageServer()
    val launcher = LSPLauncher.createServerLauncher(server, in, out)
    val client = launcher.getRemoteProxy
    server.connect(client)
    launcher.startListening()
  }
}