package chester.parser

import java.nio.file.{Files, Path, Paths}
import chester.syntax.concrete.*
import munit.FunSuite

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.*
import scala.util.Try

def getInputFiles(testDir: String): (Path, Seq[Path] ) = {
  val path = Paths.get(testDir)

  val inputFiles = Files.list(path)
    .iterator()
    .asScala
    .filter(_.toString.endsWith(".chester"))
    .toSeq

  (path, inputFiles)
}