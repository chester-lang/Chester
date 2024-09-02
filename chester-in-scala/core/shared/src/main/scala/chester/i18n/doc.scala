package chester.i18n

import chester.doc.{PrettierOptions, PrettierOptionsKey}
import chester.i18n.Language

case object LanguageKey extends PrettierOptionsKey[Language] {
  val default: Language = Language.from("en_NZ")
}

implicit def languageInPretty(using options: PrettierOptions): Language = LanguageKey.get