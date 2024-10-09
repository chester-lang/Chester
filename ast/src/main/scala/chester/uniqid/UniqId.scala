package chester.uniqid

import upickle.default._

import java.util.concurrent.atomic.AtomicInteger

private val uniqIdCounter = AtomicInteger(0)

opaque type UniqIdOf[+A] = Int

opaque type UniqIdOffset = Int

private implicit val UniqIdOffsetRW: ReadWriter[UniqIdOffset] = readwriter[java.lang.Integer].bimap(_.toInt, _.toInt)

extension (id: UniqIdOffset) {
  def <=(that: UniqIdOffset): Boolean = id <= that
  private[uniqid] def +(offset: Int): UniqIdOffset = id + offset
}

/** start <= x < end */
case class UniqIdRange(start: UniqIdOffset, end: UniqIdOffset) derives ReadWriter {
  require(start <= end, s"Invalid range: $start > $end")
}

type UniqId = UniqIdOf[Any]

extension (x: UniqId) {
  def asof[T] : UniqIdOf[T] = x.asInstanceOf[UniqIdOf[T]]
}

extension [T](x: UniqIdOf[T]) {
  def asid: UniqId = x
  def rerange(current: UniqIdRange, target: UniqIdRange): UniqIdOf[T] = {
    require(current.start <= x && x < current.end, s"Invalid range: $current, $x")
    val offset = x - current.start
    target.start + offset
  }
}

private val rwUniqID: ReadWriter[UniqIdOf[?]] = readwriter[java.lang.Integer].bimap(_.toInt, _.toInt)

implicit inline def rwUniqIDOf[T]: ReadWriter[UniqIdOf[T]]= rwUniqID

trait CollectorU {
  def apply[T](x:UniqIdOf[T]): Unit = ()
}

trait CollectUniqId  extends Any  {
  def collectU(collector: CollectorU): Unit
}

trait RerangerU {
  def apply[T](x:UniqIdOf[T]): UniqIdOf[T] = x
}

trait RerangeUniqId  extends Any  {
  def rerangeU(reranger: RerangerU): Any
}

trait ContainsUniqId extends Any with CollectUniqId with RerangeUniqId {

}

trait OnlyHasUniqId extends Any {
  def uniqId: UniqId
}

trait HasUniqId extends Any with ContainsUniqId with OnlyHasUniqId {

}

object UniqId {
  def generate[T]: UniqIdOf[T] = uniqIdCounter.getAndIncrement()
  def currentOffset(): UniqIdOffset = uniqIdCounter.get()
  def captureRange[T](f: =>T): (UniqIdRange, T) = {
    val start = currentOffset()
    val result = f
    val end = currentOffset()
    (UniqIdRange(start, end), result)
  }

  def is(x: Any): Boolean = x.isInstanceOf[Int] || x.isInstanceOf[Integer] || x.isInstanceOf[UniqIdOf[?]]
}
