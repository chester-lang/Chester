package chester.cli

import chester.core.parseCheckTAST
import chester.error.*
import chester.error.Problem.Severity
import chester.integrity.IntegrityCheck
import chester.parser.*
import chester.repl.spawnREPLEngine
import chester.tyck.Reporter
import chester.utils.fileExists
import scopt.OParser

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
          case "compile" =>
            config.input match {
              case Some(inputFile) =>
                val outputFile = config.output.getOrElse(inputFile.replaceAll("\\.chester$", ".tast"))
                compileFile(inputFile, outputFile)
              case None =>
                println("No input file provided to compile.")
            }
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
    // Implement your logic here
  }

  def runIntegrityCheck(): Unit = {
    println("Running integrity check...")
    IntegrityCheck()
  }

  // Implement the compileFile method
  def compileFile(inputFile: String, outputFile: String): Unit = {
    val source = FilePath(inputFile)
    implicit object reporter extends Reporter[Problem] {
      private var varErrors: Boolean = false
      private var varWarnings: Boolean = false

      override def apply(problem: Problem): Unit = {
        problem.severity match {
          case Severity.Error =>
            varErrors = true
            println(s"Error: ${problem}")
          case Severity.Warning =>
            varWarnings = true
            println(s"Warning: ${problem}")
          case _ =>
            println(s"Info: ${problem}")
        }
      }

      def hasErrors: Boolean = varErrors
      def hasWarnings: Boolean = varWarnings
    }

    val tast = parseCheckTAST(source)

    if (reporter.hasErrors) {
      println(s"Compilation failed with errors.")
    } else {
      val outputPath = Paths.get(outputFile)
      Files.write(outputPath, tast.writeBinary)
      println(s"Compiled $inputFile to $outputFile")
    }
  }
}