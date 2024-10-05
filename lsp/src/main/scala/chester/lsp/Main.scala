package chester.lsp

import org.eclipse.lsp4j.launch.LSPLauncher

import java.net.ServerSocket
import java.io.InputStream
import java.io.OutputStream
import org.log4s.*
import org.slf4j.simple.SimpleLogger

def enableDebug(): Unit = {
  System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Debug")
}

object Main {
  enableDebug()
  private val logger = getLogger
  def main(args: Array[String]): Unit = {
    val port = if (args.nonEmpty) args(0).toInt else 1044
    logger.info(s"Starting Chester Language Server on port $port")
    logger.debug("Debugging enabled")

    val serverSocket = new ServerSocket(port)
    logger.info(s"Server socket created, waiting for client connection...")

    val clientSocket = serverSocket.accept()
    logger.info(s"Client connected from ${clientSocket.getInetAddress}")

    val in: InputStream = clientSocket.getInputStream
    val out: OutputStream = clientSocket.getOutputStream

    val server = new ChesterLanguageServer()
    logger.info("ChesterLanguageServer instance created")

    val launcher = LSPLauncher.createServerLauncher(server, in, out)
    logger.info("LSP Launcher created")

    val client = launcher.getRemoteProxy
    server.connect(client)
    logger.info("Client connected to the server")

    launcher.startListening()
    logger.info("Server started listening")
  }
}