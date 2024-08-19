package chester.syntax

import chester.doc._

sealed trait Builtin extends ToDoc

case object Example extends Builtin {
  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("Example")
}