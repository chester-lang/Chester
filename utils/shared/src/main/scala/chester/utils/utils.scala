package chester.utils

import fastparse.ParserInput
import io.github.iltotore.iron.constraint.all.MinLength

def encodeString(x: String): String = x.replace("\n", "\\n").replace("\t", "\\t").replace("\r", "\\r").replace("\"", "\\\"")
def parserInputToLazyList(pi: ParserInput): LazyList[String] = {
  LazyList.unfold(0) { index =>
    if (pi.isReachable(index)) {
      val nextIndex = index + 1
      Some((pi.slice(index, nextIndex), nextIndex))
    } else {
      None
    }
  }
}

case class MutBox[T](var value: T) {
  def get: T = value

  def set(x: T): Unit = value = x

  def update(f: T => T): Unit = value = f(value)

  def updateAndMap[U](f: T => (T, U)): U = {
    val (newValue, result) = f(value)
    value = newValue
    result
  }
}

