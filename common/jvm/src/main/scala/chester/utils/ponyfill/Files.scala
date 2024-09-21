package chester.utils.ponyfill

import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.Path

object Files {
  def readString(path: Path, charset: Charset = StandardCharsets.UTF_8): String = {
    new String(java.nio.file.Files.readAllBytes(path), charset)
  }
  export java.nio.file.Files.*
}
