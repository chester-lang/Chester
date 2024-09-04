package chester.syntax

import chester.doc._
import upickle.default._

sealed trait Builtin extends ToDoc derives ReadWriter

case object Example extends Builtin {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Example")
}