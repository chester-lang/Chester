package chester.up
import scopt.OParser

object Main {

  case class Config(
                     command: String = "update" // Default command is "update"
                   )

  def main(args: Array[String]): Unit = {

    val builder = OParser.builder[Config]
    val parser = {
      import builder._
      OParser.sequence(
        programName("chesterup"),
        head("ChesterUp CLI Tool", "1.0"),

        cmd("update")
          .action((_, c) => c.copy(command = "update"))
          .text("Update Chester application")
      )
    }

    OParser.parse(parser, args, Config()) match {
      case Some(config) =>
        config.command match {
          case "update" =>
            runUpdate()
          case _ =>
            runUpdate() // Default to update if no valid command is provided
        }
      case None =>
        // If parsing fails, also default to running the update
        runUpdate()
    }
  }

  def runUpdate(): Unit = {
    println("Updating Chester...")
  }
}
