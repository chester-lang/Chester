package chester.doc.const

import chester.doc.*
import chester.utils.doc.*


object Colors {
  def REPLPrompt: Style = Foreground.LightBlue ++ Styling.BoldOn
}

class ColorProfile {
  def literalColor: Style = Foreground.Red

  def typeColor: Style = Foreground.LightBlue
}


case object ColorProfile extends PrettierOptionsKey[ColorProfile] {
  
    val default = new ColorProfile

    def literalColor(implicit options: PrettierOptions): Style = get.literalColor

    def typeColor(implicit options: PrettierOptions): Style = get.typeColor
  
}
