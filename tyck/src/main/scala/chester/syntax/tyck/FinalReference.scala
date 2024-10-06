package chester.syntax.tyck

import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import upickle.default.*

implicit inline def rwUniqIDOfVar[T]: ReadWriter[UniqIdOf[? <: MaybeVarCall]] = rwUniqIDOf.asInstanceOf[ReadWriter[UniqIdOf[? <: MaybeVarCall]]]

case class FinalReference(
                           call: MaybeVarCall,
                           id: UniqIdOf[? <: MaybeVarCall],
                           definedOn: Expr,
                           referencedOn: Seq[Expr]
                         ) derives ReadWriter {
  def name: Name = call.name
}
