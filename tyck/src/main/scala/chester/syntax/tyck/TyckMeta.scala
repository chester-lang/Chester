package chester.syntax.tyck

import upickle.default.*
case class TyckMeta(
                     symbols: Seq[FinalReference] = Vector.empty[FinalReference]
                   )derives ReadWriter

object TyckMeta {
  val Empty: TyckMeta = TyckMeta()
}
