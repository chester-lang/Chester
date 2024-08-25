package chester.up

import cats.implicits.*
import chester.utils.env
import chester.utils.io.*

def update(using fileOps: FileOps): fileOps.M[Unit] = for {
  result <- uninstallAll.catchErrors
  _ = if (result.isLeft) println(s"Failed to uninstall chester ${result.left.get}")
  result <- installRecommended.catchErrors
  _ = if (result.isLeft) println(s"Failed to install chester ${result.left.get}")
} yield ()

def getBaseDir(using fileOps: FileOps): fileOps.M[fileOps.P] = for {
  home <- Files.getHomeDir
  binPath = if (env.isWindows) home / ".chester" / "bin" else home / ".local" / "bin"
  _ <- Files.createDirIfNotExists(binPath)
} yield binPath
def uninstallAll(using fileOps: FileOps): fileOps.M[Unit] = for {
  binPath <- getBaseDir
  _ <- Files.removeWhenExists(binPath / "chester")
  _ <- Files.removeWhenExists(binPath / "chester.exe")
  _ <- Files.removeWhenExists(binPath / "chester.jar")
  _ <- Files.removeWhenExists(binPath / "chester.js")
  _ <- Files.removeWhenExists(binPath / "chester.bat")
} yield ()

def writeWrapper(unixSh: String, windowsBat: String)(using fileOps: FileOps): fileOps.M[Unit] =
  if (env.isUNIX) {
    for {
      binPath <- getBaseDir
      _ <- Files.writeString(binPath / "chester", unixSh)
      _ <- Files.chmodExecutable(binPath / "chester")
    } yield ()
  } else if (env.isWindows) {
    for {
      binPath <- getBaseDir
      _ <- Files.writeString(binPath / "chester.bat", windowsBat)
    } yield ()
  } else {
    throw new IllegalStateException(s"Unsupported platform ${env.getOS}")
  }

object URLs {
  val windows = "https://github.com/chester-lang/chester/releases/download/snapshot-windows/chester.exe"
  val macX64 = "https://github.com/chester-lang/chester/releases/download/snapshot-macos-intel/chester"
  val macArm64 = "https://github.com/chester-lang/chester/releases/download/snapshot-macos/chester"
  val linuxX64 = "https://github.com/chester-lang/chester/releases/download/snapshot-linux/chester"
}

def installRecommended(using fileOps: FileOps): fileOps.M[Unit] = Version.getRecommended match {
  case Version.NodeJS => for {
    binPath <- getBaseDir
    js <- Files.getAbsolutePath(binPath / "chester.js")
    _ <- Files.downloadToFile("https://github.com/chester-lang/chester/releases/download/snapshot-linux/chester.js", js)
    _ <- writeWrapper(
      s"""#!/bin/sh
         |exec node ${js} "$$@""".stripMargin,
      s"""@echo off
         |node ${js} %*""".stripMargin)
  } yield ()
  case Version.Jar => for {
    binPath <- getBaseDir
    jar <- Files.getAbsolutePath(binPath / "chester.jar")
    _ <- Files.downloadToFile("https://github.com/chester-lang/chester/releases/download/snapshot-linux/chester.jar", jar)
    _ <- writeWrapper(
      s"""#!/bin/sh
         |exec java -jar ${jar} "$$@""".stripMargin,
      s"""@echo off
         |java -jar ${jar} %*""".stripMargin)
  } yield ()
  case version: Version.NativeImage => {
    val path = if (version == Version.NativeImage.WindowsAmd64) "chester.exe" else "chester"
    val url = version match {
      case Version.NativeImage.WindowsAmd64 => URLs.windows
      case Version.NativeImage.MacAmd64 => URLs.macX64
      case Version.NativeImage.LinuxAmd64 => URLs.linuxX64
      case Version.NativeImage.MacArm64 => URLs.macArm64
    }
    for {
      binPath <- getBaseDir
      exe <- Files.getAbsolutePath(binPath / path)
      _ <- Files.downloadToFile(url, exe)
    } yield ()
  }
}