package chester.cli

import chester.core.parseCheckTAST
import chester.error.*
import chester.error.Problem.Severity
import chester.integrity.IntegrityCheck
import chester.parser.*
import chester.repl.{REPLEngine}
import chester.tyck.Reporter
import chester.utils.fileExists
import chester.utils.io.Runner
import chester.utils.term.{Terminal, TerminalInit}
import scopt.OParser
import chester.utils.env.DefaultEnv
import chester.utils.io.*
import chester.utils.term.*

import java.nio.file.{Files, Paths}

object Main {

  case class Config(
    command: String = "run", // Default command is "run"
    input: Option[String] = None,
    output: Option[String] = None // Added output option
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

        // Command for "compile"
        cmd("compile")
          .action((_, c) => c.copy(command = "compile"))
          .text("Compile a Chester source file")
          .children(
            arg[String]("input")
              .required()
              .action((x, c) => c.copy(input = Some(x)))
              .validate {
                case path if fileExists(path) => success
                case path => failure(s"Invalid input. Provide a valid file. Provided: $path")
              }
              .text("Input source file."),
            opt[String]("output")
              .optional()
              .action((x, c) => c.copy(output = Some(x)))
              .text("Output binary file (defaults to input file with .tast extension)")
          ),

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

    Runner.spawn {
      val program = new Program
      OParser.parse(parser, args, Config()) match {
        case Some(config) =>
          config.command match {
            case "run" =>
              config.input match {
                case None => program.spawnREPLEngine()
                case Some("-") => program.spawnREPLEngine()
                case Some(fileOrDir) => program.runFileOrDirectory(fileOrDir)
              }
            case "integrity" =>
              program.runIntegrityCheck()
            case "compile" =>
              config.input match {
                case Some(inputFile) =>
                  val outputFile = config.output.getOrElse(inputFile.replaceAll("\\.chester$", ".tast"))
                  program.compileFile(inputFile, outputFile)
                case None =>
                  println("No input file provided to compile.")
                  program.noop()
              }
            case _ =>
              program.spawnREPLEngine() // Default action if no valid command is provided
          }
        case _ =>
        // Arguments are bad, error message will have been displayed
          program.noop()
      }
    }
  }

}