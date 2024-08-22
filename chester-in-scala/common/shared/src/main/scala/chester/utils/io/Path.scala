package chester.utils.io

import effekt.*

import scala.language.implicitConversions

trait PathOps[T] {
  def of(path: String): T

  def join(p1: T, p2: T): T

  def asString(p: T): String
}

extension [T](p: T)(using ops: PathOps[T]) {
  def /(p2: T): T = ops.join(p, p2)
}

implicit val PathOpsString: PathOps[String] = new PathOps[String] {
  def of(path: String): String = path

  def join(p1: String, p2: String): String = p1 + "/" + p2

  def asString(p: String): String = p
}

implicit def summonPathOps[F <: FileOps](using fileOps: F) : PathOps[fileOps.P] = fileOps.pathOps

object Path {
  def of[T](path: String)(using ops: PathOps[T]): T = ops.of(path)
}

trait FileOps {
  type P

  def pathOps: PathOps[P]

  def read(path: P): Control[String]

  def write(path: P, content: String): Control[Unit]

  def append(path: P, content: String): Control[Unit]

  def remove(path: P): Control[Unit]

  def getHomeDir: Control[P]
}