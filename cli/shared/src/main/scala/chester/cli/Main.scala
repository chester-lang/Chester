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

  case class CompileConfig(
    inputs: Seq[String],
    targetDir: String = "."
  ) extends Config

  // Add this new case class for decompilation
  case class DecompileConfig(input: String) extends Config

  // Parsing state class with default command set to "run"
  case class CliConfig(
    command: String = "run", // Default command is "run"
    input: Option[String] = None,
    inputs: Seq[String] = Seq(),
    targetDir: String = "."
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
          .text("Compile Chester source files")
          .children(
            arg[String]("inputs...")
              .unbounded()
              .required()
              .validate {
                case path if fileExists(path) => success
                case path => failure(s"Invalid input. Provide a valid file. Provided: $path")
              }
              .action((x, c) => c.copy(inputs = c.inputs :+ x))
              .text("Input source files."),
            opt[String]("target-dir")
              .abbr("d")
              .optional()
              .action((x, c) => c.copy(targetDir = x))
              .text("Target directory for compiled outputs (defaults to current directory).")
          ),

        // Command for "decompile"
        cmd("decompile")
          .action((_, c) => c.copy(command = "decompile"))
          .text("Decompile a .tast binary file")
          .children(
            arg[String]("input")
              .required()
              .validate {
                case path if fileExists(path) => success
                case path => failure(s"Invalid input. Provide a valid .tast file. Provided: $path")
              }
              .action((x, c) => c.copy(input = Some(x)))
              .text("Input .tast binary file.")
          ),
          // Handle case where user might omit "run" and just provide input directly
          arg[String] ("input")
          .optional()
          .validate {
            case "-" => success
            case path if fileExists(path) => success
            case path => failure(s"Invalid input. Provide '-' for stdin, or a valid file/directory. Provided: $path")
          }
          .action((x, c) => c.copy(input = Some(x)))
            .hidden()
      )
    }

    // Parse the arguments
    OParser.parse(parser, argsPlatform(args), CliConfig()) match {
      case Some(cliConfig) =>
        val config: Config = cliConfig.command match {
          case "run" =>
            RunConfig(cliConfig.input)
          case "integrity" =>
            IntegrityConfig
          case "compile" =>
            if (cliConfig.inputs.nonEmpty) {
              CompileConfig(cliConfig.inputs, cliConfig.targetDir)
            } else {
              println("Error: At least one input file is required for compile command.")
              return
            }
          // Add this case for decompile
          case "decompile" =>
            cliConfig.input match {
              case Some(inputFile) =>
                DecompileConfig(inputFile)
              case None =>
                println("Error: Input file is required for decompile command.")
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
}