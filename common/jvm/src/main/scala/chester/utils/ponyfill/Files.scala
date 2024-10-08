package chester.utils.ponyfill

import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.Path

object Files {
  export java.nio.file.Files.*
}
