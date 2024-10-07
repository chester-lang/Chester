package chester.cli

import typings.node.processMod
import scala.scalajs.js
import js.JSConverters._

inline def argsPlatform(args: Array[String]): Array[String] = {
  val argv = processMod.^.argv.toArray
  argv.slice(2, argv.length)
}
