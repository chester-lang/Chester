package chester.utils.doc

case class Style(foreground: Option[Foreground], background: Option[Background], styling: Vector[Styling]) {
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
    val style = styling.map(_.toFansi).foldLeft(fansi.Attrs.Empty)(_ ++ _)
    fg ++ bg ++ style
  }
}

sealed trait Foreground {
  def toFansi: fansi.Attrs
}

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
}

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
}

object Styling {
  case object BoldOn extends Styling {
    def toFansi: fansi.Attrs = fansi.Bold.On
  }

  case object BoldOff extends Styling {
    def toFansi: fansi.Attrs = fansi.Bold.Off
  }
}
