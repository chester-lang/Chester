package chester.pretty.const

import chester.pretty.doc._


object Colors {
  def REPLPrompt: Vector[Attribute] = Vector(Color.LightBlue, Attribute.BoldOn)
}

class ColorProfile {
  def literalColor: Color = Color.Red
  def typeColor: Color = Color.LightBlue
}

object ColorProfile {
  val Default = new ColorProfile

  def get(implicit options: PrettierOptions): ColorProfile = options.getOrElse(ColorProfileKey, Default)

  def literalColor(implicit options: PrettierOptions): Color = get.literalColor
}

case object ColorProfileKey extends PrettierOptionsKey[ColorProfile]
