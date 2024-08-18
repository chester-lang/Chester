package chester.doc.const

import chester.doc._


object Colors {
  def REPLPrompt: Vector[Attribute] = Vector(Color.LightBlue, Attribute.BoldOn)
}

class ColorProfile {
  def literalColor: Color = Color.Red

  def typeColor: Color = Color.LightBlue
}


case object ColorProfile extends PrettierOptionsKey[ColorProfile] {
  
    val default = new ColorProfile

    def literalColor(implicit options: PrettierOptions): Color = get.literalColor

    def typeColor(implicit options: PrettierOptions): Color = get.typeColor
  
}
