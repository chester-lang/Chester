package chester.i18n
import scala.quoted.*


extension (sc: StringContext)
  def t(args: Any*): String = {
    sc.s(args: _*)
  }

