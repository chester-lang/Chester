package chester.tyck

import chester.parser.*
import chester.syntax.concrete.*
import munit.FunSuite

import java.nio.file.Files

class FilesTyckTest extends FunSuite {
  val (testDir, inputFiles) = getInputFiles("tyckTests")

  inputFiles.foreach { inputFile =>
    val baseName = inputFile.getFileName.toString.stripSuffix(".chester")
    test(baseName) {

    }
  }
}
