package chester.cli

import scopt.OParser
import chester.repl.REPLMain
import chester.integrity.IntegrityCheck // Import the IntegrityCheck class
import java.io.File

object Main {

  // Command line arguments case class
  case class Config(
                     subcommand: String = "run",
                     input: Option[String] = None
                   )

  def main(args: Array[String]): Unit = {

    // scopt parser definition
    val builder = OParser.builder[Config]
    val parser = {
      import builder._
      OParser.sequence(
        programName("chester"),
        head("chester", "1.0"),

        // Subcommands
        cmd("run")
          .action((_, c) => c.copy(subcommand = "run"))
          .text("run expressions")
          .children(
            arg[String]("input")
              .optional()
              .action((x, c) => c.copy(input = Some(x)))
              .text("input can be '-', a file, or a directory")
          ),

        cmd("integrity")
          .action((_, c) => c.copy(subcommand = "integrity"))
          .text("run integrity check"),

        cmd("help")
          .text("display help")
          .action((_, c) => c.copy(subcommand = "help")),

        // Help options
        help("help").text("prints this usage text"),
        checkConfig { c =>
          if (c.subcommand == "integrity" || c.input.isEmpty || c.input.exists(x => x == "-" || new File(x).exists())) success
          else failure("Invalid input. Provide '-' for stdin, or a valid file/directory.")
        }
      )
    }

    // Parse CLI arguments
    OParser.parse(parser, args, Config()) match {
      case Some(config) =>
        config.subcommand match {
          case "run" =>
            config.input match {
              case None => REPLMain.runREPL() // Run interactive REPL when no input is provided
              case Some("-") => REPLMain.runREPL()
              case Some(fileOrDir) => runFileOrDirectory(fileOrDir) // Evaluate from file or directory
            }
          case "integrity" =>
            runIntegrityCheck() // Call the integrity check
          case "help" =>
            OParser.usage(parser)
        }
      case _ => // Arguments are bad, error message will have been displayed
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
