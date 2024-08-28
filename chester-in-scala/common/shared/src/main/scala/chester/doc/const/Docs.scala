package chester.doc.const

import chester.doc.{Doc, PrettierOptions}


object Docs {
  def `{`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("begin") else Doc.text("{")
  def `}`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("end") else Doc.text("}")
  def `[`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("beginList") else Doc.text("[")
  def `]`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("endList") else Doc.text("]")
  def `(`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("beginTuple") else Doc.text("(")
  def `)`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("endTuple") else Doc.text(")")
  def `->`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("to") else Doc.text("->")
  def `:`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("is") else Doc.text(":")
  def `...`(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("ellipsis") else Doc.text("...")
}