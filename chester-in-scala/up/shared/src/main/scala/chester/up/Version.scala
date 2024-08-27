package chester.up

import chester.utils.env
import chester.utils.env.{Architecture, OS, RunningOn}

import scala.sys.process.*
import scala.util.Try
import scala.util.matching.Regex

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

def checkNodeVersion(describe: Boolean = false): Boolean = {
  try {
    val nodeVersionOutput = checkCommand("node -v")
    nodeVersionOutput match {
      case Some(version) =>
        val nodeVersion = parseVersion(version, "v")
        val result = nodeVersion.exists(_(0) >= 18)
        if (!result && describe) println(s"NodeJS version $version is not supported, please upgrade to NodeJS 18 or above")
        result
      case None => {
        if(describe) println("NodeJS is not found")
        false
      }
    }
  } catch {
    case e: ArrayIndexOutOfBoundsException => false
  }
}

def checkJavaVersion(describe: Boolean = false): Boolean = {
  try {
    val javaVersionOutput = checkCommand("java -version")
    javaVersionOutput match {
      case Some(version) =>
        // Regular expression to extract the Java version from the output
        val versionRegex: Regex = "\"(\\d+)(\\.\\d+)?(\\.\\d+)?(_\\d+)?\"".r

        // Extract the version number using the regex
        val versionMatch = versionRegex.findFirstMatchIn(version)

        versionMatch match {
          case Some(m) =>
            val major = m.group(1).toInt
            val minor = Try(m.group(2).drop(1).toInt).getOrElse(0)

            val result = if (major == 1) minor >= 8 else major >= 8
            if (!result && describe) println(s"Java version ${m.matched} is not supported, please upgrade to Java 8 or above")
            result
          case None =>
            if (describe) println("Could not determine Java version from output.")
            false
        }

      case None =>
        if (describe) println("Java is not found")
        false
    }
  } catch {
    case e: ArrayIndexOutOfBoundsException =>
      if (describe) println(s"An error occurred on JVM check: ${e.getMessage}")
      false
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

  def getRecommended(describe: Boolean = false): Version = {
    (env.getOS, env.getArch) match {
      case (OS.Windows, Architecture.Amd64) => return NativeImage.WindowsAmd64
      case (OS.Mac, Architecture.Amd64) => return NativeImage.MacAmd64
      case (OS.GNULinux, Architecture.Amd64) => return NativeImage.LinuxAmd64
      case (OS.Mac, Architecture.Arm64) => return NativeImage.MacArm64
      case _ => {
        if (describe) println(s"No native image available for the current platform, falling back to JAR or NodeJS. os: ${env.getOS}, arch: ${env.getArch}")
      }
    }
    if (checkJavaVersion(describe = describe)) {
      if(describe) println("Java version is supported, falling back to JAR")
      return Jar
    }
    if (checkNodeVersion(describe = describe)) {
      if(describe) println("NodeJS version is supported, falling back to NodeJS")
      return NodeJS
    }
    env.getRunningOn match {
      case _: RunningOn.Nodejs => {
        if(describe) println("Running on NodeJS, falling back to NodeJS")
        return NodeJS
      }
      case _: RunningOn.JVM => {
        if(describe) println("Running on JVM, falling back to JAR")
        return Jar
      }
      case _ => {}
    }
    throw new IllegalStateException("Unsupported platform or runtime, os: " + env.getOS + ", arch: " + env.getArch + ", runningOn: " + env.getRunningOn)
  }
}

