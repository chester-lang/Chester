package chester.cli

import chester.cli.Main.CliConfig
import chester.tyck.SemanticDBGenerator

import java.nio.file.{Files, Paths}

object PlatformSpecific {
  def genSemanticDB(config: CliConfig): Unit = {
    val inputPath = config.input.getOrElse {
      println("Error: Input path is required.")
      return
    }

    val path = Paths.get(inputPath)
    if (!Files.exists(path)) {
      println(s"Error: Input path does not exist: $inputPath")
      return
    }

    // Create a new SemanticDBGenerator instance
    val generator = new SemanticDBGenerator()

    // Process the input path
    generator.processPath(os.FilePath(path).resolveFrom(os.Path(java.nio.file.Paths.get(".").toAbsolutePath)))

    // Save the SemanticDB file
    val outputPath = path.resolveSibling("semanticdb")
    generator.saveSemanticDB(path.toUri.toString, outputPath.toString)

    println(s"SemanticDB generated at: $outputPath")
  }
}
