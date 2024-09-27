package chester.utils.doc

import spire.math.Trilean

import scala.language.implicitConversions

case class Style(foreground: Option[Foreground] = None, background: Option[Background] = None, styling: Stylings = Stylings.Empty) {
  def ++(other: Style): Style = {
    Style(
      foreground = other.foreground.orElse(foreground),
      background = other.background.orElse(background),
      styling = styling ++ other.styling
    )
  }

  def toFansi: fansi.Attrs = {
    val fg = foreground.map(_.toFansi).getOrElse(fansi.Attrs.Empty)
    val bg = background.map(_.toFansi).getOrElse(fansi.Attrs.Empty)
    val style = styling.toFansi
    fg ++ bg ++ style
  }
}

object Style {
  val Empty: Style = Style()
}

sealed trait Foreground {
  def toFansi: fansi.Attrs

  implicit final inline def toStyle: Style = Style(foreground = Some(this))
}

implicit inline def ForegroundToStyle(fg: Foreground): Style = fg.toStyle

object Foreground {
  case object Black extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.Black
  }

  case object Red extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.Red
  }

  case object Green extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.Green
  }

  case object Yellow extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.Yellow
  }

  case object Blue extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.Blue
  }

  case object Magenta extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.Magenta
  }

  case object Cyan extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.Cyan
  }

  case object LightGray extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.LightGray
  }

  case object DarkGray extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.DarkGray
  }

  case object LightRed extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.LightRed
  }

  case object LightGreen extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.LightGreen
  }

  case object LightYellow extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.LightYellow
  }

  case object LightBlue extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.LightBlue
  }

  case object LightMagenta extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.LightMagenta
  }

  case object LightCyan extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.LightCyan
  }

  case object White extends Foreground {
    def toFansi: fansi.Attrs = fansi.Color.White
  }
}

sealed trait Background {
  def toFansi: fansi.Attrs

  implicit final inline def toStyle: Style = Style(background = Some(this))
}

implicit inline def BackgroundToStyle(bg: Background): Style = bg.toStyle

object Background {
  case object Black extends Background {
    def toFansi: fansi.Attrs = fansi.Back.Black
  }

  case object Red extends Background {
    def toFansi: fansi.Attrs = fansi.Back.Red
  }

  case object Green extends Background {
    def toFansi: fansi.Attrs = fansi.Back.Green
  }

  case object Yellow extends Background {
    def toFansi: fansi.Attrs = fansi.Back.Yellow
  }

  case object Blue extends Background {
    def toFansi: fansi.Attrs = fansi.Back.Blue
  }

  case object Magenta extends Background {
    def toFansi: fansi.Attrs = fansi.Back.Magenta
  }

  case object Cyan extends Background {
    def toFansi: fansi.Attrs = fansi.Back.Cyan
  }

  case object LightGray extends Background {
    def toFansi: fansi.Attrs = fansi.Back.LightGray
  }

  case object DarkGray extends Background {
    def toFansi: fansi.Attrs = fansi.Back.DarkGray
  }

  case object LightRed extends Background {
    def toFansi: fansi.Attrs = fansi.Back.LightRed
  }

  case object LightGreen extends Background {
    def toFansi: fansi.Attrs = fansi.Back.LightGreen
  }

  case object LightYellow extends Background {
    def toFansi: fansi.Attrs = fansi.Back.LightYellow
  }

  case object LightBlue extends Background {
    def toFansi: fansi.Attrs = fansi.Back.LightBlue
  }

  case object LightMagenta extends Background {
    def toFansi: fansi.Attrs = fansi.Back.LightMagenta
  }

  case object LightCyan extends Background {
    def toFansi: fansi.Attrs = fansi.Back.LightCyan
  }

  case object White extends Background {
    def toFansi: fansi.Attrs = fansi.Back.White
  }
}

sealed trait Styling {
  def toFansi: fansi.Attrs

  implicit final inline def toStyle: Style = Style(styling = applyOn())

  def applyOn(x: Stylings = Stylings.Empty): Stylings
}

implicit inline def StylingToStyle(styling: Styling): Style = styling.toStyle

object Stylings {
  val Empty = Stylings()
}

case class Stylings
(
  bold: Trilean = Trilean.Unknown
) {
  private inline def field(t: Trilean, onTrue: Styling, onFalse: Styling): fansi.Attrs = t match {
    case Trilean.True => onTrue.toFansi
    case Trilean.False => onFalse.toFansi
    case Trilean.Unknown => fansi.Attrs.Empty
  }

  def toFansi: fansi.Attrs = {
    import Styling.*
    var result = fansi.Attrs.Empty
    result = result ++ field(bold, BoldOn, BoldOff)
    result
  }

  private inline def m(a: Trilean, b: Trilean): Trilean = b match {
    case Trilean.Unknown => a
    case _ => b
  }

  def ++(other: Stylings): Stylings = Stylings(bold = m(bold, other.bold))

  def isEmpty: Boolean = this == Stylings.Empty
}

object Styling {
  case object BoldOn extends Styling {
    override def toFansi: fansi.Attrs = fansi.Bold.On

    override def applyOn(x: Stylings): Stylings = x.copy(bold = Trilean.True)
  }

  case object BoldOff extends Styling {
    override def toFansi: fansi.Attrs = fansi.Bold.Off

    override def applyOn(x: Stylings): Stylings = x.copy(bold = Trilean.False)
  }
}
