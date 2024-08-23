package chester.up

import chester.utils.io._
import effekt.Control
import cats.implicits._

def uninstallAll(using fileOps: FileOps): fileOps.M[Unit] = for {
  home <- fileOps.getHomeDir
  
} yield ()