package chester.utils.io

import effekt.*

import cats.free.*
import cats.free.Free.*
import cats._

import scala.language.implicitConversions

trait PathOps[T] {
  def of(path: String): T

  def join(p1: T, p2: T): T

  def asString(p: T): String
}

extension [T](p: T)(using ops: PathOps[T]) {
  def /(p2: T): T = ops.join(p, p2)
}

implicit object PathOpsString extends PathOps[String] {
  def of(path: String): String = path

  def join(p1: String, p2: String): String = p1 + "/" + p2

  def asString(p: String): String = p
}

implicit def summonPathOps[F <: FileOps](using fileOps: F): PathOps[fileOps.P] = fileOps.pathOps

object Path {
  def of[T](path: String)(using ops: PathOps[T]): T = ops.of(path)
}

trait FileOps {
  type P
  type M[_]

  def pathOps: PathOps[P]

  def read(path: P): M[String]

  def write(path: P, content: String): M[Unit]

  def append(path: P, content: String): M[Unit]

  def remove(path: P): M[Unit]

  def getHomeDir: M[P]
}

trait FileOpsEff extends FileOps {
  type M[x] = Control[x]
}

trait FileOpsFree extends FileOps {
  sealed trait Op[A]

  type M[x] = Op[x]

  case class Read(path: P) extends Op[String]

  case class Write(path: P, content: String) extends Op[Unit]

  case class Append(path: P, content: String) extends Op[Unit]

  case class Remove(path: P) extends Op[Unit]

  case object GetHomeDir extends Op[P]

  def read(path: P): M[String] = Read(path)

  def write(path: P, content: String): M[Unit] = Write(path, content)

  def append(path: P, content: String): M[Unit] = Append(path, content)

  def remove(path: P): M[Unit] = Remove(path)

  def getHomeDir: M[P] = GetHomeDir
}