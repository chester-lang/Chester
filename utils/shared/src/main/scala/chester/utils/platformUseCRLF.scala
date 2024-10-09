package chester.utils

val platformUseCRLF: Boolean = System.lineSeparator() match {
  case "\r\n" => true
  case "\n"   => false
  case _      => throw new Exception("Unknown line separator!")
}
