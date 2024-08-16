package chester.utils

import fastparse.ParserInput

def encodeString(x: String): String = x.replace("\n", "\\n").replace("\t", "\\t").replace("\r", "\\r").replace("\"", "\\\"")

def parserInputToLazyList(pi: ParserInput): LazyList[String] = {
  LazyList.iterate(0)(_ + pi.innerLength).takeWhile(pi.isReachable).map(pi.slice(_, pi.length))
}