package chester.cli

import chester.cli.Main.Config
import chester.core.parseCheckTAST
import chester.error.Problem
import chester.error.Problem.Severity
import chester.integrity.IntegrityCheck
import chester.parser.{FilePath, FilePathImpl}
import chester.repl.REPLEngine
import chester.tyck.Reporter
import chester.utils.env.Environment
import chester.utils.io.Runner
import chester.utils.term.{Terminal, TerminalInit}

import java.nio.file.{Files, Paths}

object Program {
  def spawn[F[_]](config: Option[Config])(using runner: Runner[F], terminal: Terminal[F], env: Environment, path: FilePathImpl): Unit ={
    Runner.spawn {
      (new Program[F]).run(config)
    }
  }
}

class Program[F[_]](using runner: Runner[F], terminal: Terminal[F], env: Environment, path: FilePathImpl) {
  def run(config: Option[Config]) : F[Unit] = {
    config match {
      case Some(config) =>
        config.command match {
          case "run" =>
            config.input match {
              case None => this.spawnREPLEngine()
              case Some("-") => this.spawnREPLEngine()
              case Some(fileOrDir) => this.runFileOrDirectory(fileOrDir)
            }
          case "integrity" =>
            this.runIntegrityCheck()
          case "compile" =>
            config.input match {
              case Some(inputFile) =>
                val outputFile = config.output.getOrElse(inputFile.replaceAll("\\.chester$", ".tast"))
                this.compileFile(inputFile, outputFile)
              case None =>
                println("No input file provided to compile.")
                this.noop()
            }
          case _ =>
            this.spawnREPLEngine() // Default action if no valid command is provided
        }
      case _ =>
        // Arguments are bad, error message will have been displayed
        this.noop()
    }
  }
  def noop(): F[Unit] = {
    Runner.pure(())
  }
  def spawnREPLEngine(): F[Unit] = {
    Terminal.runTerminal(TerminalInit.Default) {
      REPLEngine[F]
    }
  }

  // Evaluate from file or directory
  def runFileOrDirectory(fileOrDir: String): F[Unit] = {
    println(s"Running from $fileOrDir...")
    // Implement your logic here
    Runner.pure(())
  }

  def runIntegrityCheck(): F[Unit] = {
    println("Running integrity check...")
    IntegrityCheck()
    Runner.pure(())
  }

  // Implement the compileFile method
  def compileFile(inputFile: String, outputFile: String): F[Unit] = {
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

    Runner.pure(())
  }
}
