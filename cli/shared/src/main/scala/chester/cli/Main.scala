package chester.cli

import chester.core.parseCheckTAST
import chester.error.*
import chester.error.Problem.Severity
import chester.integrity.IntegrityCheck
import chester.parser.*
import chester.repl.REPLEngine
import chester.tyck.Reporter
import chester.utils.fileExists
import scopt.OParser
import chester.utils.env.DefaultEnv
import chester.utils.io.*
import chester.utils.io.impl.*
import chester.utils.term.*

object Main {

  sealed trait Config

  case class RunConfig(input: Option[String]) extends Config

  case object IntegrityConfig extends Config

  case class CompileConfig(input: String, output: Option[String]) extends Config

  // Parsing state class with default command set to "run"
  case class CliConfig(
    command: String = "run", // Default command is "run"
    input: Option[String] = None,
    output: Option[String] = None
  )

  def main(args: Array[String]): Unit = {

    val builder = OParser.builder[CliConfig]
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
              .validate {
                case "-" => success
                case path if fileExists(path) => success
                case path => failure(s"Invalid input. Provide '-' for stdin, or a valid file/directory. Provided: $path")
              }
              .action((x, c) => c.copy(input = Some(x)))
              .text("Input file or directory. Use '-' for stdin.")
          ),

        // Command for "integrity"
        cmd("integrity")
          .action((_, c) => c.copy(command = "integrity"))
          .text("Run integrity check"),

        // Command for "compile"
        cmd("compile")
          .action((_, c) => c.copy(command = "compile"))
          .text("Compile a Chester source file")
          .children(
            arg[String]("input")
              .required()
              .validate {
                case path if fileExists(path) => success
                case path => failure(s"Invalid input. Provide a valid file. Provided: $path")
              }
              .action((x, c) => c.copy(input = Some(x)))
              .text("Input source file."),
            opt[String]("output")
              .optional()
              .action((x, c) => c.copy(output = Some(x)))
              .text("Output binary file (defaults to input file with .tast extension)")
          ),

        // Handle case where user might omit "run" and just provide input directly
        arg[String]("input")
          .optional()
          .validate {
            case "-" => success
            case path if fileExists(path) => success
            case path => failure(s"Invalid input. Provide '-' for stdin, or a valid file/directory. Provided: $path")
          }
          .action((x, c) => c.copy(input = Some(x)))
          .text("Input file or directory. Use '-' for stdin.")
      )
    }

    // Parse the arguments
    OParser.parse(parser, args, CliConfig()) match {
      case Some(cliConfig) =>
        val config: Config = cliConfig.command match {
          case "run" =>
            RunConfig(cliConfig.input)
          case "integrity" =>
            IntegrityConfig
          case "compile" =>
            cliConfig.input match {
              case Some(inputFile) =>
                CompileConfig(inputFile, cliConfig.output)
              case None =>
                println("Error: Input file is required for compile command.")
                return
            }
          case _ =>
            println("Invalid command")
            return
        }
        Program.spawn(Some(config))
      case None =>
        // If parsing fails, default to "run" command when no args are provided
        if (args.isEmpty) {
          Program.spawn(Some(RunConfig(None)))
        } else {
          // Arguments are bad, error message will have been displayed
          Program.spawn(None)
        }
    }
  }

  // Helper sealed trait for parsing
  sealed trait ParsedCommand {
    def toConfig: Config
  }

  object ParsedCommand {
    case class Run(input: Option[String]) extends ParsedCommand {
      def toConfig: Config = RunConfig(input)
    }

    case object Integrity extends ParsedCommand {
      def toConfig: Config = IntegrityConfig
    }

    case class Compile(input: String, output: Option[String]) extends ParsedCommand {
      def toConfig: Config = CompileConfig(input, output)
    }
  }
}