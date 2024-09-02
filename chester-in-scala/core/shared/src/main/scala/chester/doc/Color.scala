package chester.doc

sealed trait Attribute

object Attribute {
  case object BoldOn extends Attribute

  case object BoldOff extends Attribute
}

sealed trait Color extends Attribute

object Color:
  case object Black extends Color

  case object Red extends Color

  case object Green extends Color

  case object Yellow extends Color

  case object Blue extends Color

  case object Magenta extends Color

  case object Cyan extends Color

  case object LightGray extends Color

  case object DarkGray extends Color

  case object LightRed extends Color

  case object LightGreen extends Color

  case object LightYellow extends Color

  case object LightBlue extends Color

  case object LightMagenta extends Color

  case object LightCyan extends Color

  case object White extends Color

  case object Reset extends Color

sealed trait BackgroundColor extends Color

object BackgroundColor:
  case object Black extends BackgroundColor

  case object Red extends BackgroundColor

  case object Green extends BackgroundColor

  case object Yellow extends BackgroundColor

  case object Blue extends BackgroundColor

  case object Magenta extends BackgroundColor

  case object Cyan extends BackgroundColor

  case object LightGray extends BackgroundColor

  case object DarkGray extends BackgroundColor

  case object LightRed extends BackgroundColor

  case object LightGreen extends BackgroundColor

  case object LightYellow extends BackgroundColor

  case object LightBlue extends BackgroundColor

  case object LightMagenta extends BackgroundColor

  case object LightCyan extends BackgroundColor

  case object White extends BackgroundColor

  case object Reset extends BackgroundColor

import fansi.{Attr, Attrs}

object ColorMapping:
  def toFansiAttrs(colors: Vector[Attribute]): Attrs = Attrs(colors.map(toFansiAttr) *)

  def toFansiAttr(color: Attribute): Attr = color match
    case Attribute.BoldOn => fansi.Bold.On
    case Attribute.BoldOff => fansi.Bold.Off
    case Color.Black => fansi.Color.Black
    case Color.Red => fansi.Color.Red
    case Color.Green => fansi.Color.Green
    case Color.Yellow => fansi.Color.Yellow
    case Color.Blue => fansi.Color.Blue
    case Color.Magenta => fansi.Color.Magenta
    case Color.Cyan => fansi.Color.Cyan
    case Color.LightGray => fansi.Color.LightGray
    case Color.DarkGray => fansi.Color.DarkGray
    case Color.LightRed => fansi.Color.LightRed
    case Color.LightGreen => fansi.Color.LightGreen
    case Color.LightYellow => fansi.Color.LightYellow
    case Color.LightBlue => fansi.Color.LightBlue
    case Color.LightMagenta => fansi.Color.LightMagenta
    case Color.LightCyan => fansi.Color.LightCyan
    case Color.White => fansi.Color.White
    case Color.Reset => fansi.Color.Reset
    case BackgroundColor.Black => fansi.Back.Black
    case BackgroundColor.Red => fansi.Back.Red
    case BackgroundColor.Green => fansi.Back.Green
    case BackgroundColor.Yellow => fansi.Back.Yellow
    case BackgroundColor.Blue => fansi.Back.Blue
    case BackgroundColor.Magenta => fansi.Back.Magenta
    case BackgroundColor.Cyan => fansi.Back.Cyan
    case BackgroundColor.LightGray => fansi.Back.LightGray
    case BackgroundColor.DarkGray => fansi.Back.DarkGray
    case BackgroundColor.LightRed => fansi.Back.LightRed
    case BackgroundColor.LightGreen => fansi.Back.LightGreen
    case BackgroundColor.LightYellow => fansi.Back.LightYellow
    case BackgroundColor.LightBlue => fansi.Back.LightBlue
    case BackgroundColor.LightMagenta => fansi.Back.LightMagenta
    case BackgroundColor.LightCyan => fansi.Back.LightCyan
    case BackgroundColor.White => fansi.Back.White
    case BackgroundColor.Reset => fansi.Back.Reset

  def toHtmlCss(color: Attribute): String = color match
    case Color.Black => "black"
    case Color.Red => "red"
    case Color.Green => "green"
    case Color.Yellow => "yellow"
    case Color.Blue => "blue"
    case Color.Magenta => "magenta"
    case Color.Cyan => "cyan"
    case Color.LightGray => "lightgray"
    case Color.DarkGray => "darkgray"
    case Color.LightRed => "lightcoral"
    case Color.LightGreen => "lightgreen"
    case Color.LightYellow => "lightyellow"
    case Color.LightBlue => "lightblue"
    case Color.LightMagenta => "lightpink"
    case Color.LightCyan => "lightcyan"
    case Color.White => "white"
    case Color.Reset => "initial"
    case BackgroundColor.Black => "black"
    case BackgroundColor.Red => "red"
    case BackgroundColor.Green => "green"
    case BackgroundColor.Yellow => "yellow"
    case BackgroundColor.Blue => "blue"
    case BackgroundColor.Magenta => "magenta"
    case BackgroundColor.Cyan => "cyan"
    case BackgroundColor.LightGray => "lightgray"
    case BackgroundColor.DarkGray => "darkgray"
    case BackgroundColor.LightRed => "lightcoral"
    case BackgroundColor.LightGreen => "lightgreen"
    case BackgroundColor.LightYellow => "lightyellow"
    case BackgroundColor.LightBlue => "lightblue"
    case BackgroundColor.LightMagenta => "lightpink"
    case BackgroundColor.LightCyan => "lightcyan"
    case BackgroundColor.White => "white"
    case BackgroundColor.Reset => "initial"
