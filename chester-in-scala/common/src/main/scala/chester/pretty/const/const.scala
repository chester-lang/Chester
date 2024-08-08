package chester.pretty.const

import chester.pretty.doc.{Color, PrettierOptionsKey}


object Colors {
  val REPLPrompt = Color.LightCyan
}

class ColorProfile {
  def literalColor: Color = Color.Red
}

case object ColorProfileKey extends PrettierOptionsKey[ColorProfile]
