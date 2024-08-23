package chester.up

import chester.utils.io._
import chester.utils.env
import effekt.Control
import cats.implicits._

def update(using fileOps: FileOps): fileOps.M[Unit] = for {
  result <- uninstallAll.catchErrors
  _ = if(result.isLeft) println(s"Failed to uninstall chester ${result.left.get}")
} yield ()

def uninstallAll(using fileOps: FileOps): fileOps.M[Unit] = for {
  home <- Files.getHomeDir
  binPath = if(env.getOS.isUNIX) home / ".local" / "bin" else ???
  _ <- Files.createDirIfNotExists(binPath)
  _ <- Files.removeWhenExists(binPath / "chester")
  _ <- Files.removeWhenExists(binPath / "chester.exe")
  _ <- Files.removeWhenExists(binPath / "chester.jar")
  _ <- Files.removeWhenExists(binPath / "chester.js")
  _ <- Files.removeWhenExists(binPath / "chester.bat")
} yield ()