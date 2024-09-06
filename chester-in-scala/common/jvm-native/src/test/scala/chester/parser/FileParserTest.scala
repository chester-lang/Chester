package chester.parser

import chester.parser.*
import chester.syntax.concrete.*
import munit.FunSuite

import java.nio.file.Files

class FileParserTest extends FunSuite {
  val (testDir, inputFiles) = getInputFiles("parserTests")

  inputFiles.foreach { inputFile =>
    val baseName = inputFile.getFileName.toString.stripSuffix(".chester")
    test(baseName) {
      // Check if the operating system is Windows. we are having / \ path issues on Windows
      assume(!System.getProperty("os.name").toLowerCase.contains("win"), "Skipping test on Windows")

      val expectedFile = testDir.resolve(s"$baseName.expected")

      val expectedExists = Files.exists(expectedFile)
      FilePathImplJVM.load

      Parser.parseTopLevel(FilePath(inputFile.toString)) match {
        case Right(parsedBlock) =>
          val actual: String = pprint.apply(parsedBlock, width = 128, height = Integer.MAX_VALUE).plainText

          if (!expectedExists) {
            Files.write(expectedFile, actual.getBytes)
            println(s"Created expected file: $expectedFile")
          } else {
            val expected = new String(Files.readAllBytes(expectedFile))
            assertEquals(actual, expected)
          }

        case Left(error) =>
          fail(s"Failed to parse file: $inputFile, error: $error")
      }
    }
  }
}
