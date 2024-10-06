package chester.tyck

import chester.parser.*
import chester.syntax.concrete.*
import chester.utils.doc.*
import chester.utils.ponyfill.Files
import munit.FunSuite

import java.nio.charset.StandardCharsets
import java.nio.file.Path

class FilesTyckTest extends FunSuite {
  val (testDir, inputFiles) = getInputFiles("tyckTests")

  inputFiles.foreach { inputFile =>
    val baseName = inputFile.getFileName.toString.stripSuffix(".chester")
    test(baseName) {
      val expectedFile = testDir.resolve(s"$baseName.expected")
      val expectedExists = Files.exists(expectedFile)

      FilePathImplJVM.load

      Parser.parseTopLevel(FilePath(inputFile.toString)) match {
        case Right(parsedBlock) =>
          Cker.check(parsedBlock) match {
            case TyckResult.Success(result, status, warnings) =>
              val actual = StringPrinter.render(result.wellTyped)(using PrettierOptions.Default)

              if (!expectedExists) {
                Files.write(expectedFile, actual.getBytes)
                println(s"Created expected file: $expectedFile")
              } else {
                val expected = Files.readString(expectedFile, StandardCharsets.UTF_8)
                assertEquals(actual, expected)
              }

            case TyckResult.Failure(errors, warnings, state, result) =>
              fail(s"Failed to type check file: $inputFile, errors: $errors")
          }
        case Left(error) =>
          fail(s"Failed to parse file: $inputFile, error: $error")
      }
    }
  }
}
