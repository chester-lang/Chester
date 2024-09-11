package chester.syntax.core

import upickle.default._

import java.util.concurrent.atomic.AtomicInteger

private val varIdCounter = AtomicInteger(0)

opaque type VarId = Int

implicit val rwVarID: ReadWriter[VarId] = readwriter[Int].bimap(_.toInt, _.toInt)

trait HasVarId extends Any {
  def varId: VarId
}

object VarId {
  def generate: VarId = varIdCounter.getAndIncrement()
}
