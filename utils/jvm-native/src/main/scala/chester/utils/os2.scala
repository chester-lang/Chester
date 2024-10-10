package chester.utils

import os.Path

object os2 {
  // https://github.com/com-lihaoyi/os-lib/issues/318
  lazy val pwd: Path = {
    val result = os.Path(java.nio.file.Paths.get(".").toAbsolutePath)
    os.dynamicPwd.value = result
    result
  }
  def path(x: String): os.Path = os.Path(x, pwd)
}
