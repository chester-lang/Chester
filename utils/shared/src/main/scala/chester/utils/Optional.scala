package chester.utils

import scala.language.implicitConversions

opaque type Optional[+A] = A

object Optional {
  inline def Empty[A]: Optional[A] = null
}

implicit def OptionToOptional[A >: Null](option: Option[A]): Optional[A] = option.orNull

extension [A >: Null](self: Optional[A]) {
  def toOption: Option[A] = Option(self)
  def isEmpty: Boolean = self == null
  def isDefined: Boolean = self != null
  def get: A = if(isDefined) self else throw new NoSuchElementException("Optional.empty.get")
  def getOrElse[B >: A](default: => B): B = if(isDefined) self else default
  def map[B](f: A => B): Optional[B] = if(isDefined) f(self) else null
}