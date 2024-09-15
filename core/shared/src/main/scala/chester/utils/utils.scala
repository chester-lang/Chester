package chester.utils

import fastparse.ParserInput

def encodeString(x: String): String = x.replace("\n", "\\n").replace("\t", "\\t").replace("\r", "\\r").replace("\"", "\\\"")

def parserInputToLazyList(pi: ParserInput): LazyList[String] = {
  LazyList.iterate(0)(_ + pi.innerLength).takeWhile(pi.isReachable).map(pi.slice(_, pi.length))
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
