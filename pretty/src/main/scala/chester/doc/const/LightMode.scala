package chester.doc.const

import chester.utils.doc.PrettierOptionsKey

case object LightMode extends PrettierOptionsKey[Boolean] {
  val default: Boolean = false
}
