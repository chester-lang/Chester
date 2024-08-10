package chester.cli

import cats.implicits.*
import chester.cli.Main.*
import com.monovore.decline.*
import chester.repl.REPLMainRun
import chester.integrity.IntegrityCheck

import java.io.File

object Main extends CommandApp(
  name = "chester",
  header = "Chester CLI Tool",
  main = {

    // Define the `input` argument
    val inputOpt = Opts.argument[String]("input")
      .orNone
      .validate("Invalid input. Provide '-' for stdin, or a valid file/directory.") {
        case Some("-") => true
        case Some(path) => new File(path).exists()
        case None => true
      }

    // Define the `run` subcommand
    val runCmd = Command("run", "Run expressions") {
      inputOpt.map {
        case None => REPLMainRun() // Run interactive REPL when no input is provided
        case Some("-") => REPLMainRun()
        case Some(fileOrDir) => runFileOrDirectory(fileOrDir) // Evaluate from file or directory
      }
    }

    // Define the `integrity` subcommand
    val integrityCmd = Command("integrity", "Run integrity check") {
      Opts.unit.map(_ => runIntegrityCheck())
    }

    // Parse subcommands or default to the `run` command
    Opts.subcommand(runCmd) orElse
      Opts.subcommand(integrityCmd) orElse {
      // Default behavior if no subcommand is specified
      inputOpt.map {
        case None => REPLMainRun() // Run interactive REPL by default
        case Some("-") => REPLMainRun()
        case Some(fileOrDir) => runFileOrDirectory(fileOrDir) // Evaluate from file or directory
      }
    }
  }
) {

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
