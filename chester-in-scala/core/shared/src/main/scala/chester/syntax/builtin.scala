package chester.syntax

import chester.doc.*
import chester.utils.doc.PrettierOptions
import upickle.default.*

sealed trait Builtin extends ToDoc derives ReadWriter

case object Example extends Builtin {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Example")
}