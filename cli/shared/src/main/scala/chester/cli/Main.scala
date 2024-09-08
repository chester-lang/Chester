package chester.cli

import chester.integrity.IntegrityCheck
import chester.utils.fileExists
import chester.repl.spawnREPLEngine
import scopt.OParser
import chester.io._

object Main {

  case class Config(
                     command: String = "run", // Default command is "run"
                     input: Option[String] = None
                   )

  def main(args: Array[String]): Unit = {

    val builder = OParser.builder[Config]
    val parser = {
      import builder._
      OParser.sequence(
        programName("chester"),
        head("Chester CLI Tool", "1.0"),

        // Command for "run"
        cmd("run")
          .action((_, c) => c.copy(command = "run"))
          .text("Run expressions")
          .children(
            arg[String]("input")
              .optional()
              .action((x, c) => c.copy(input = Some(x)))
              .validate {
                case "-" => success
                case path if fileExists(path) => success
                case path => failure(s"Invalid input. Provide '-' for stdin, or a valid file/directory. Provided: $path")
              }
              .text("Input file or directory. Use '-' for stdin.")
          ),

        // Command for "integrity"
        cmd("integrity")
          .action((_, c) => c.copy(command = "integrity"))
          .text("Run integrity check"),

        // Handle case where user might omit "run" and just provide input directly
        arg[String]("<input>")
          .optional()
          .action((x, c) => c.copy(command = "run", input = Some(x)))
          .validate {
            case "-" => success
            case path if fileExists(path) => success
            case path => failure(s"Invalid input. Provide '-' for stdin, or a valid file/directory. Provided: $path")
          }
          .text("Input file or directory. Use '-' for stdin.")
      )
    }

    // Parse the arguments
    OParser.parse(parser, args, Config()) match {
      case Some(config) =>
        config.command match {
          case "run" =>
            config.input match {
              case None => spawnREPLEngine()
              case Some("-") => spawnREPLEngine()
              case Some(fileOrDir) => runFileOrDirectory(fileOrDir)
            }
          case "integrity" =>
            runIntegrityCheck()
          case _ =>
            spawnREPLEngine() // Default action if no valid command is provided
        }
      case _ =>
      // Arguments are bad, error message will have been displayed
    }
  }

  // Evaluate from file or directory
  def runFileOrDirectory(fileOrDir: String): Unit = {
    println(s"Running from $fileOrDir...")
    ???
  }

  def runIntegrityCheck(): Unit = {
    println("Running integrity check...")
    IntegrityCheck()
  }
}
