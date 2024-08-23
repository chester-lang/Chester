package chester.utils.env

import typings.node.osMod
import typings.node.processMod
import typings.node.processMod.global.NodeJS.Platform

  val getOS: OS = {
    val os = osMod.platform()
    if (os == Platform.win32) OS.Windows
    else if (os == Platform.darwin) OS.Mac
    else if (os == Platform.linux) OS.GNULinux
    else if (os == Platform.android) OS.Termux
    else throw new Exception(s"Unknown OS: $os")
  }
  val getArch: Architecture = {
    osMod.arch().toLowerCase match {
      case "x86_64" | "amd64" | "x64" => Architecture.Amd64
      case "x86" | "i386" => Architecture.X86
      case "arm" => Architecture.Arm
      case "aarch64" | "arm64" => Architecture.Arm64
      case _ => throw new Exception(s"Unknown architecture: ${osMod.arch()}")
    }
  }
  val getRunningOn: RunningOn = RunningOn.Nodejs(processMod.^.version)
