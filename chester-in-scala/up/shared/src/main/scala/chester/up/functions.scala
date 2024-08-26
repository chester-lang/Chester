package chester.up

import cats.implicits.*
import chester.io._
import chester.utils.env

inline def update[F[_]](using inline io: IO[F], inline runner: Runner[F]): F[Unit] = for {
  result <- Runner.doTry(uninstallAll)
  _ = if (result.isFailure) println(s"Failed to uninstall chester ${result.failed.get}")
  result <- Runner.doTry(installRecommended)
  _ = if (result.isFailure) println(s"Failed to install chester ${result.failed.get}")
} yield ()

inline def getBaseDir[F[_]](using io: IO[F], inline runner: Runner[F]): F[io.Path] = for {
  home <- IO.getHomeDir
  binPath = if (env.isWindows) home / ".chester" / "bin" else home / ".local" / "bin"
  _ <- IO.createDirRecursiveIfNotExists(binPath)
} yield binPath
inline def uninstallAll[F[_]](using io: IO[F], inline runner: Runner[F]): F[Unit] = for {
  binPath <- getBaseDir
  _ <- IO.removeWhenExists(binPath / "chester")
  _ <- IO.removeWhenExists(binPath / "chester.exe")
  _ <- IO.removeWhenExists(binPath / "chester.jar")
  _ <- IO.removeWhenExists(binPath / "chester.js")
  _ <- IO.removeWhenExists(binPath / "chester.bat")
} yield ()

inline def writeWrapper[F[_]](unixSh: String, windowsBat: String)(using io: IO[F], inline runner: Runner[F]): F[Unit] =
  if (env.isUNIX) {
    for {
      binPath <- getBaseDir
      _ <- IO.writeString(binPath / "chester", unixSh)
      _ <- IO.chmodExecutable(binPath / "chester")
    } yield ()
  } else if (env.isWindows) {
    for {
      binPath <- getBaseDir
      _ <- IO.writeString(binPath / "chester.bat", windowsBat)
    } yield ()
  } else {
    throw new IllegalStateException(s"Unsupported platform ${env.getOS}")
  }

object URLs {
  inline val windows = "https://github.com/chester-lang/chester/releases/download/snapshot-windows/chester.exe"
  inline val macX64 = "https://github.com/chester-lang/chester/releases/download/snapshot-macos-intel/chester"
  inline val macArm64 = "https://github.com/chester-lang/chester/releases/download/snapshot-macos/chester"
  inline val linuxX64 = "https://github.com/chester-lang/chester/releases/download/snapshot-linux/chester"
}

inline def installRecommended[F[_]](using io: IO[F], inline runner: Runner[F]): F[Unit] = Version.getRecommended(describe = true) match {
  case Version.NodeJS => for {
    binPath <- getBaseDir
    js <- IO.getAbsolutePath(binPath / "chester.js")
    _ <- IO.downloadToFile("https://github.com/chester-lang/chester/releases/download/snapshot-linux/chester.js", js)
    _ <- writeWrapper(
      s"""#!/bin/sh
         |exec node ${js} "$$@""".stripMargin,
      s"""@echo off
         |node ${js} %*""".stripMargin)
  } yield ()
  case Version.Jar => for {
    binPath <- getBaseDir
    jar <- IO.getAbsolutePath(binPath / "chester.jar")
    _ <- IO.downloadToFile("https://github.com/chester-lang/chester/releases/download/snapshot-linux/chester.jar", jar)
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
      exe <- IO.getAbsolutePath(binPath / path)
      _ <- IO.downloadToFile(url, exe)
      _ <- IO.chmodExecutable(exe)
    } yield ()
  }
}