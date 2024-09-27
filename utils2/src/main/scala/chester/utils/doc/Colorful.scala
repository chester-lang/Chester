package chester.utils.doc

case class ColorfulPiece(text: String, style: Style)

case class Colorful(pieces: Vector[ColorfulPiece]) extends AnyVal {
  def ++(other: Colorful): Colorful = Colorful(pieces ++ other.pieces)
  def +:(piece: ColorfulPiece): Colorful = Colorful(piece +: pieces)
  def :+(piece: ColorfulPiece): Colorful = Colorful(pieces :+ piece)
}

object Colorful {
  val Empty: Colorful = Colorful(Vector.empty)
}