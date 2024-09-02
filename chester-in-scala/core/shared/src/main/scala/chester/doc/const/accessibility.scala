package chester.doc.const

import chester.doc.PrettierOptionsKey

// will work better with Windows Narrator on Windows Terminal
case object ReplaceBracketsWithWord extends PrettierOptionsKey[Boolean] {
  val default: Boolean = false

}