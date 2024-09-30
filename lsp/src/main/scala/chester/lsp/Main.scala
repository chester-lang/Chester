package chester.lsp

import org.eclipse.lsp4j.launch.LSPLauncher
import java.net.ServerSocket
import java.io.InputStream
import java.io.OutputStream

object Main {
  def main(args: Array[String]): Unit = {
    val port = if (args.nonEmpty) args(0).toInt else 1044
    println(s"Starting Chester Language Server on port $port")

    val serverSocket = new ServerSocket(port)
    println(s"Server socket created, waiting for client connection...")

    val clientSocket = serverSocket.accept()
    println(s"Client connected from ${clientSocket.getInetAddress}")

    val in: InputStream = clientSocket.getInputStream
    val out: OutputStream = clientSocket.getOutputStream

    val server = new ChesterLanguageServer()
    println("ChesterLanguageServer instance created")

    val launcher = LSPLauncher.createServerLauncher(server, in, out)
    println("LSP Launcher created")

    val client = launcher.getRemoteProxy
    server.connect(client)
    println("Client connected to the server")

    launcher.startListening()
    println("Server started listening")
  }
}