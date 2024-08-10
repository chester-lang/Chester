package chester.parser
import munit.FunSuite
import chester.syntax.concrete._
import chester.parser._
import java.nio.file.{Files, Paths}
import scala.util.Try
import scala.jdk.CollectionConverters._

class FileParserTest extends FunSuite {

  val testDir = "parserTests"

  val inputFiles = Files.list(Paths.get(testDir))
    .iterator()
    .asScala
    .filter(_.toString.endsWith(".chester"))
    .toSeq

  inputFiles.foreach { inputFile =>
    val baseName = inputFile.getFileName.toString.stripSuffix(".chester")
    test(baseName) {
      // Check if the operating system is Windows
      assume(!System.getProperty("os.name").toLowerCase.contains("win"), "Skipping test on Windows")

      val expectedFile = Paths.get(testDir, s"$baseName.expected")

      val input = new String(Files.readAllBytes(inputFile))
      val expectedExists = Files.exists(expectedFile)

      Parser.parseTopLevel(FileNameAndContent(inputFile.toString, input)) match {
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
