package chester.doc

import chester.utils.doc.PrettierOptions

trait ToDoc {
  def toDoc(implicit options: PrettierOptions = PrettierOptions.Default): Doc
}
