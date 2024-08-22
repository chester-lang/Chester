package chester.utils.env

object Env {
  lazy val getOS: OS = {
    val os = System.getProperty("os.name").toLowerCase
    if (os.contains("win")) OS.Windows
    else if (os.contains("mac")) OS.Mac
    else if (os.contains("linux")) OS.GNULinux
    else throw new Exception(s"Unknown OS: $os")
  }
  lazy val getArch: Architecture = {
    System.getProperty("os.arch").toLowerCase match {
      case "x86_64" | "amd64" => Architecture.Amd64
      case "x86" | "i386" => Architecture.X86
      case "arm" => Architecture.Arm
      case "aarch64" => Architecture.Arm64
      case _ => throw new Exception(s"Unknown architecture: ${System.getProperty("os.arch")}")
    }
  }
}
