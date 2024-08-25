package chester.up

import chester.utils.env
import chester.utils.env.{Architecture, OS, RunningOn}

import scala.sys.process.*
import scala.util.Try

def checkCommand(command: String): Option[String] = {
  // Capture both stdout and stderr output explicitly
  val outputBuilder = new StringBuilder
  val processLogger = ProcessLogger(
    line => outputBuilder.append(line).append("\n"), // handle stdout
    line => outputBuilder.append(line).append("\n") // handle stderr
  )

  Try(command ! processLogger).toOption match {
    case Some(0) => Some(outputBuilder.toString().trim) // command succeeded
    case _ => None // command failed
  }
}

def parseVersion(versionOutput: String, versionPrefix: String): Option[Seq[Int]] = {
  // Extract the version number using the prefix, split by dots, and convert to integers
  versionOutput.stripPrefix(versionPrefix).split("\\.").toSeq.flatMap(s => Try(s.toInt).toOption).headOption match {
    case Some(major) => Some(Seq(major))
    case None => None
  }
}

def checkNodeVersion(): Boolean = {
  try {
    val nodeVersionOutput = checkCommand("node -v")
    nodeVersionOutput match {
      case Some(version) =>
        val nodeVersion = parseVersion(version, "v")
        nodeVersion.exists(_(0) >= 18)
      case None =>
        false
    }
  } catch {
    case e: ArrayIndexOutOfBoundsException => false
  }
}

def checkJavaVersion(): Boolean = {
  try {
    val javaVersionOutput = checkCommand("java -version")
    javaVersionOutput match {
      case Some(version) =>
        // Java version output usually includes lines, we need to extract the first line
        val firstLine = version.split("\n").head
        // Extract the Java version number from the first line
        val javaVersion = firstLine.split("\"")(1).split("\\.").toSeq.flatMap(s => Try(s.toInt).toOption)
        javaVersion match {
          case Seq(1, minor, _*) => minor >= 8 // For Java 1.x (legacy versions)
          case Seq(major, _*) => major >= 8 // For Java 9 and above
          case _ => false
        }
      case None =>
        false
    }
  } catch {
    case e: ArrayIndexOutOfBoundsException => false
  }
}

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
    if (checkJavaVersion()) return Jar
    if (checkNodeVersion()) return NodeJS
    env.getRunningOn match {
      case _: RunningOn.Nodejs => return NodeJS
      case _: RunningOn.JVM => return Jar
      case _ => {}
    }
    throw new IllegalStateException("Unsupported platform or runtime, os: " + env.getOS + ", arch: " + env.getArch + ", runningOn: " + env.getRunningOn)
  }
}

