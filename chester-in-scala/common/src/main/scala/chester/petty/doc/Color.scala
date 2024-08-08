package chester.petty.doc

sealed trait Color

sealed trait ForegroundColor extends Color

object ForegroundColor:
  case object Black extends ForegroundColor

  case object Red extends ForegroundColor

  case object Green extends ForegroundColor

  case object Yellow extends ForegroundColor

  case object Blue extends ForegroundColor

  case object Magenta extends ForegroundColor

  case object Cyan extends ForegroundColor

  case object LightGray extends ForegroundColor

  case object DarkGray extends ForegroundColor

  case object LightRed extends ForegroundColor

  case object LightGreen extends ForegroundColor

  case object LightYellow extends ForegroundColor

  case object LightBlue extends ForegroundColor

  case object LightMagenta extends ForegroundColor

  case object LightCyan extends ForegroundColor

  case object White extends ForegroundColor

  case object Reset extends ForegroundColor

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

import fansi.Attr

object ColorMapping:
  def toFansiAttr(color: ForegroundColor): Attr = color match
    case ForegroundColor.Black => fansi.Color.Black
    case ForegroundColor.Red => fansi.Color.Red
    case ForegroundColor.Green => fansi.Color.Green
    case ForegroundColor.Yellow => fansi.Color.Yellow
    case ForegroundColor.Blue => fansi.Color.Blue
    case ForegroundColor.Magenta => fansi.Color.Magenta
    case ForegroundColor.Cyan => fansi.Color.Cyan
    case ForegroundColor.LightGray => fansi.Color.LightGray
    case ForegroundColor.DarkGray => fansi.Color.DarkGray
    case ForegroundColor.LightRed => fansi.Color.LightRed
    case ForegroundColor.LightGreen => fansi.Color.LightGreen
    case ForegroundColor.LightYellow => fansi.Color.LightYellow
    case ForegroundColor.LightBlue => fansi.Color.LightBlue
    case ForegroundColor.LightMagenta => fansi.Color.LightMagenta
    case ForegroundColor.LightCyan => fansi.Color.LightCyan
    case ForegroundColor.White => fansi.Color.White
    case ForegroundColor.Reset => fansi.Color.Reset

  def toHtmlCss(color: ForegroundColor): String = color match
    case ForegroundColor.Black => "black"
    case ForegroundColor.Red => "red"
    case ForegroundColor.Green => "green"
    case ForegroundColor.Yellow => "yellow"
    case ForegroundColor.Blue => "blue"
    case ForegroundColor.Magenta => "magenta"
    case ForegroundColor.Cyan => "cyan"
    case ForegroundColor.LightGray => "lightgray"
    case ForegroundColor.DarkGray => "darkgray"
    case ForegroundColor.LightRed => "lightcoral"
    case ForegroundColor.LightGreen => "lightgreen"
    case ForegroundColor.LightYellow => "lightyellow"
    case ForegroundColor.LightBlue => "lightblue"
    case ForegroundColor.LightMagenta => "lightpink"
    case ForegroundColor.LightCyan => "lightcyan"
    case ForegroundColor.White => "white"
    case ForegroundColor.Reset => "initial"

  def toFansiAttr(color: BackgroundColor): Attr = color match
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

  def toHtmlCss(color: BackgroundColor): String = color match
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
