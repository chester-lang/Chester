package chester.doc.const

import chester.doc.{Doc, PrettierOptions}


object Docs {
  def begin(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("begin") else Doc.text("{")
  def end(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("end") else Doc.text("}")
  def beginList(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("beginList") else Doc.text("[")
  def endList(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("endList") else Doc.text("]")
  def beginTuple(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("beginTuple") else Doc.text("(")
  def endTuple(implicit options: PrettierOptions): Doc = if(ReplaceBracketsWithWord.get) Doc.text("endTuple") else Doc.text(")")
}