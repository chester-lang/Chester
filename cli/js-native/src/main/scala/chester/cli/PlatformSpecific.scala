package chester.cli

import chester.cli.Main.{CliConfig, Config}
object PlatformSpecific {
  def genSemanticDB(config: CliConfig): Unit = {
    println("Error: SemanticDB generation is not supported on this platform.")
  }
}