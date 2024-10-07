package chester.cli

import chester.cli.Main.{Config, RunConfig, IntegrityConfig, CompileConfig}
import chester.core.parseCheckTAST
import chester.error.Problem
import chester.error.Problem.Severity
import chester.integrity.IntegrityCheck
import chester.parser.{FilePath, FilePathImpl}
import chester.repl.REPLEngine
import chester.tyck.Reporter
import chester.utils.env.Environment
import chester.utils.io.{IO, Runner, Spawn, stringToPath, summonPathOpsFromIO, write}
import chester.utils.term.{Terminal, TerminalInit}
import cats.implicits.*

object Program {
  def spawn[F[_]](config: Option[Config])(using runner: Runner[F], terminal: Terminal[F], env: Environment, path: FilePathImpl, spawn: Spawn[F], io: IO[F]): Unit ={
    Spawn.spawn {
      (new Program[F]).run(config)
    }
  }
}

class Program[F[_]](using runner: Runner[F], terminal: Terminal[F], env: Environment, path: FilePathImpl, io: IO[F]) {
  def run(configOpt: Option[Config]) : F[Unit] = {
    configOpt match {
      case Some(config) =>
        config match {
          case RunConfig(inputOpt) =>
            inputOpt match {
              case None => this.spawnREPLEngine()
              case Some("-") => this.spawnREPLEngine()
              case Some(fileOrDir) => this.runFileOrDirectory(fileOrDir)
            }
          case IntegrityConfig =>
            this.runIntegrityCheck()
          case CompileConfig(inputFile, outputOpt) =>
            val outputFile = outputOpt.getOrElse(inputFile.replaceAll("\\.chester$", ".tast"))
            this.compileFile(inputFile, outputFile)
        }
      case None =>
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
      Runner.pure(())
    } else {
      val outputPath = stringToPath(outputFile)
      for {
        _ <- IO.write(outputPath, tast.writeBinary)
        _ <- IO.println(s"Compiled $inputFile to $outputFile")
      } yield ()
    }

  }
}
