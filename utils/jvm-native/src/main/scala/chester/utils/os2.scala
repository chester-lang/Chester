package chester.utils

import os.Path

object os2 {
  lazy val pwd: Path = {
    val result = os.Path(java.nio.file.Paths.get(".").toAbsolutePath)
    os.dynamicPwd.value = result
    result
  }
  def path(x: String): os.Path = os.Path(x, pwd)
}
