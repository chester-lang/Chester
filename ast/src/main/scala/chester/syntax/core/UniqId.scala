package chester.syntax.core

import upickle.default._

import java.util.concurrent.atomic.AtomicInteger

private val uniqIdCounter = AtomicInteger(0)

opaque type UniqId = Int
type UniqIdOf[+A] = UniqId

implicit val rwUniqID: ReadWriter[UniqId] = readwriter[java.lang.Integer].bimap(_.toInt, _.toInt)

trait HasUniqId extends Any {
  def uniqId: UniqIdOf[this.type]
}

object UniqId {
  def generate: UniqId = uniqIdCounter.getAndIncrement()
}
