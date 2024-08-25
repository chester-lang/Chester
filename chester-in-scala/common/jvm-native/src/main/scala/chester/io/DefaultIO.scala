package chester.io

import cats.Id

import scala.annotation.tailrec
import scala.util.Try

implicit object DefaultSpawn extends Spawn[Id] {
  @inline
  override inline def spawn(x: =>Unit): Unit = x
}

implicit object DefaultIO extends IO[Id] {
  @inline
  override inline def doTry[T](IO: T): Try[T] = Try(IO)

  @inline
  override inline def println(x: String): Unit = Predef.println(x)

}
