package chester.pretty.const

import chester.pretty.doc.{Color, PrettierOptions, PrettierOptionsKey}


object Colors {
  val REPLPrompt = Color.LightCyan
}

class ColorProfile {
  def literalColor: Color = Color.Red
}

object ColorProfile {
  val Default = new ColorProfile

  def get(implicit options: PrettierOptions): ColorProfile = options.getOrElse(ColorProfileKey, Default)

  def literalColor(implicit options: PrettierOptions): Color = get.literalColor
}

case object ColorProfileKey extends PrettierOptionsKey[ColorProfile]
