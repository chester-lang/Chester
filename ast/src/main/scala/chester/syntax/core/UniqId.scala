package chester.syntax.core

import upickle.default._

import java.util.concurrent.atomic.AtomicInteger

private val uniqIdCounter = AtomicInteger(0)

opaque type UniqIdOf[+A] = Int

type UniqId = UniqIdOf[?]

extension (x: UniqId) {
  def asof[T] : UniqIdOf[T] = x.asInstanceOf[UniqIdOf[T]]
}

extension [T](x: UniqIdOf[T]) {
  def asid: UniqId = x
}

implicit val rwUniqID: ReadWriter[UniqId] = readwriter[java.lang.Integer].bimap(_.toInt, _.toInt)

trait HasUniqId extends Any {
  def uniqId: UniqId
}

object UniqId {
  def generate[T]: UniqIdOf[T] = uniqIdCounter.getAndIncrement()
}
