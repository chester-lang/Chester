package chester.pretty.doc

import fansi.Attr

object Implicits {
  implicit def stringToDoc(s: String): Doc = Doc.text(s)
  implicit def toFansiAttr(color: Color): Attr = ColorMapping.toFansiAttr(color)
}
