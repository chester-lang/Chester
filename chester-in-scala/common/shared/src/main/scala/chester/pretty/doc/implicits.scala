package chester.pretty.doc

import fansi.{Attr, Attrs}

implicit def stringToDoc(s: String): Doc = Doc.text(s)

implicit def toFansiAttr(color: Attribute): Attr = ColorMapping.toFansiAttr(color)

implicit def toFansiAttrs(colors: Vector[Attribute]): Attrs = ColorMapping.toFansiAttrs(colors)