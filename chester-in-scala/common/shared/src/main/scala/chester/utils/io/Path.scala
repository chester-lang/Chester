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

implicit def stringToPath[T](path: String)(using ops: PathOps[T]): T = ops.of(path)

implicit object PathOpsString extends PathOps[String] {
  def of(path: String): String = path

  def join(p1: String, p2: String): String = p1 + "/" + p2

  def asString(p: String): String = p
}

implicit def summonPathOps[F <: FileOps](using fileOps: F): PathOps[fileOps.P] = fileOps.pathOps
implicit def summonMonad[F <: FileOps](using fileOps: F): Monad[fileOps.M] = fileOps.m

object Path {
  def of[T](path: String)(using ops: PathOps[T]): T = ops.of(path)
}

trait FileOps {
  type P
  type M[_]

  def m: Monad[M]

  def catchErrors[A](eff: => M[A]): M[Either[Throwable, A]]

  def pathOps: PathOps[P]

  def read(path: P): M[String]

  def writeString(path: P, content: String, append: Boolean = false): M[Unit] = write(path, content.getBytes, append)

  def write(path: P, content: Array[Byte], append: Boolean = false): M[Unit]

  def removeWhenExists(path: P): M[Boolean]

  def getHomeDir: M[P]

  def exists(path: P): M[Boolean]

  def createDirIfNotExists(path: P): M[Unit]

  def downloadToFile(url: String, path: P): M[Unit]
}

object Files

extension (_files: Files.type)(using fileOps: FileOps) {
  def read(path: fileOps.P): fileOps.M[String] = fileOps.read(path)
  def write(path: fileOps.P, content: Array[Byte], append: Boolean = false): fileOps.M[Unit] = fileOps.write(path, content, append)
  def writeString(path: fileOps.P, content: String, append: Boolean = false): fileOps.M[Unit] = fileOps.writeString(path, content, append)
  def removeWhenExists(path: fileOps.P): fileOps.M[Boolean] = fileOps.removeWhenExists(path)
  def getHomeDir: fileOps.M[fileOps.P] = fileOps.getHomeDir
  def exists(path: fileOps.P): fileOps.M[Boolean] = fileOps.exists(path)
  def createDirIfNotExists(path: fileOps.P): fileOps.M[Unit] = fileOps.createDirIfNotExists(path)
}

implicit object MonadControl extends Monad[Control] {
  override def pure[A](a: A): Control[A] = effekt.pure(a)

  override def flatMap[A, B](fa: Control[A])(f: A => Control[B]): Control[B] = fa.flatMap(f)

  override def tailRecM[A, B](a: A)(f: A => Control[Either[A, B]]): Control[B] = f(a).flatMap {
    case Left(a) => tailRecM(a)(f)
    case Right(b) => pure(b)
  }
}

trait FileOpsEff extends FileOps {
  type M[x] = Control[x]

  def m = MonadControl

}

trait FileOpsEff1 extends FileOpsEff {
  def errorFilter(e: Throwable): Boolean

  def catchErrors[A](eff: => M[A]): M[Either[Throwable, A]] = eff map { a => Right(a) } _catch {
    case e if errorFilter(e) => effekt.pure(Left(e))
  }
}

extension [A, M[_]](x: M[A])(using fileOps: FileOps, ev: M[A] =:= fileOps.M[A]) {
  def catchErrors: fileOps.M[Either[Throwable, A]] = fileOps.catchErrors(x)
}

trait FileOpsFree extends FileOps {
  sealed trait Op[A]

  type M[x] = Free[Op, x]

  def m = implicitly

  case class CatchErrors[A](body: M[A]) extends Op[Either[Throwable, A]]

  case class Read(path: P) extends Op[String]

  case class WriteString(path: P, content: String, append: Boolean) extends Op[Unit]

  case class Write(path: P, content: Array[Byte], append: Boolean) extends Op[Unit]

  case class RemoveWhenExists(path: P) extends Op[Boolean]

  case object GetHomeDir extends Op[P]

  case class Exists(path: P) extends Op[Boolean]

  case class CreateDirIfNotExists(path: P) extends Op[Unit]
  
  case class DownloadToFile(url: String, path: P) extends Op[Unit]
  
  override def catchErrors[A](body: =>M[A]): M[Either[Throwable, A]] = liftF(CatchErrors(body))

  def read(path: P): M[String] = liftF(Read(path))

  override def writeString(path: P, content: String, append: Boolean): M[Unit] = liftF(WriteString(path, content, append))

  def write(path: P, content: Array[Byte], append: Boolean): M[Unit] = liftF(Write(path, content, append))

  def removeWhenExists(path: P): M[Boolean] = liftF(RemoveWhenExists(path))

  def getHomeDir: M[P] = liftF(GetHomeDir)

  def exists(path: P): M[Boolean] = liftF(Exists(path))

  def createDirIfNotExists(path: P): M[Unit] = liftF(CreateDirIfNotExists(path))
  
  def downloadToFile(url: String, path: P): M[Unit] = liftF(DownloadToFile(url, path))
}