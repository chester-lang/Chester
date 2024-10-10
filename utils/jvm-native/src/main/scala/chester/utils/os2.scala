package chester.utils

object os2 {
  lazy val pwd = {
    val result = os.Path(java.nio.file.Paths.get(".").toAbsolutePath)
    os.dynamicPwd.value = result
    result
  }
  def path(x: String): os.Path = os.Path(x, pwd)
}
