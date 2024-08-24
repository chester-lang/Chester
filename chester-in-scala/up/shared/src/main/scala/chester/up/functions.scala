package chester.up

import chester.utils.io._
import chester.utils.env
import effekt.Control
import cats.implicits._

def update(using fileOps: FileOps): fileOps.M[Unit] = for {
  result <- uninstallAll.catchErrors
  _ = if(result.isLeft) println(s"Failed to uninstall chester ${result.left.get}")
} yield ()

def getBaseDir(using fileOps: FileOps): fileOps.M[fileOps.P] = for {
  home <- Files.getHomeDir
  binPath = if(env.isUNIX) home / ".local" / "bin" else home / ".local" / "bin"
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
  if(env.isUNIX){
    for {
      binPath <- getBaseDir
      _ <-Files.writeString(binPath / "chester", unixSh)
      _ <- Files.chmodExecutable(binPath / "chester")
    } yield ()
  } else {
    for {
      binPath <- getBaseDir
      _ <- Files.writeString(binPath / "chester.bat", windowsBat)
    } yield ()
  }

def installRecommended(using fileOps: FileOps): fileOps.M[Unit] = Version.getRecommended match {
  case Version.NodeJS => for {
    binPath <- getBaseDir
    js <- Files.getAbsolutePath(binPath / "chester.js")
    _ <- Files.downloadToFile("https://github.com/chester-lang/chester/releases/download/snapshot-linux/chester.js",js)
    _ <- writeWrapper(
      s"""#!/bin/sh
        |exec node ${js} "$$@""".stripMargin,
    s"""@echo off
        |node ${js} %*""".stripMargin)
  } yield ()
  case _ => ???
}