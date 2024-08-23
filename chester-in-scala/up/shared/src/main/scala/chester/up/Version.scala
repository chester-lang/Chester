package chester.up

import chester.utils.env
import chester.utils.env.RunningOn
import chester.utils.env.{Architecture, OS}

sealed trait Version

object Version {
  case object NodeJS extends Version

  case object Jar extends Version

  sealed trait NativeImage extends Version

  object NativeImage {
    case object WindowsAmd64 extends NativeImage

    case object MacAmd64 extends NativeImage

    case object LinuxAmd64 extends NativeImage

    case object MacArm64 extends NativeImage
  }

  def getRecommended: Version = {
    (env.getOS, env.getArch) match {
      case (OS.Windows, Architecture.Amd64) => return NativeImage.WindowsAmd64
      case (OS.Mac, Architecture.Amd64) => return NativeImage.MacAmd64
      case (OS.GNULinux, Architecture.Amd64) => return NativeImage.LinuxAmd64
      case (OS.Mac, Architecture.Arm64) => return NativeImage.MacArm64
      case _ => {}
    }
    env.getRunningOn match {
      case _: RunningOn.Nodejs => return NodeJS
      case _: RunningOn.JVM => return Jar
      case _ => {}
    }
    throw new IllegalStateException("Unsupported platform or runtime, os: " + env.getOS + ", arch: " + env.getArch + ", runningOn: " + env.getRunningOn)
  }
}

