package chester.cli

import chester.cli.Main.CliConfig
import chester.tyck.SemanticDBGenerator
import chester.utils.*

import java.nio.file.{Files, Paths}

object PlatformSpecific {
  def genSemanticDB(config: CliConfig): Unit = {
    val inputPath = config.input.getOrElse {
      println("Error: Input path is required.")
      return
    }

    val path = os2.path(inputPath)
    if (!os.exists(path)) {
      println(s"Error: Input path does not exist: $inputPath")
      return
    }
    if(path.ext != "chester") {
      println(s"Error: Input path must be a .chester file: $inputPath")
      return
    }

    // Create a new SemanticDBGenerator instance
    val generator = new SemanticDBGenerator()

    // Process the input path
    generator.processPath(path)

    // Save the SemanticDB file
    val outputPath = os.Path(path.baseName + ".semanticdb")
    generator.saveSemanticDB(path.toUri.toString, outputPath.toString)

    println(s"SemanticDB generated at: $outputPath")
  }
}
