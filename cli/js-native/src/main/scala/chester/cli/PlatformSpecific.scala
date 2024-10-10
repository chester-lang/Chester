package chester.cli

import chester.cli.Main.CliConfig
object PlatformSpecific {
  def genSemanticDB(config: CliConfig): Unit = {
    println("Error: SemanticDB generation is not supported on this platform.")
  }
  def testLoadingJS():Unit={
    println("On JS or Native")
  }
}
