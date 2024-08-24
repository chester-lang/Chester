package chester.utils.io

import cats.*
import cats.free.*
import cats.instances.future.*
import effekt.{Control, Handler}
import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod.MakeDirectoryOptions
import typings.node.{fsMod, fsPromisesMod, osMod}

import scala.concurrent.{ExecutionContext, Future}
import scala.scalajs.js
import scala.scalajs.js.JavaScriptException
import scala.scalajs.js.Thenable.Implicits.*
import scala.scalajs.js.typedarray.Uint8Array

object NodeFileOpsFree extends FileOpsFree {
  type P = String

  def pathOps = PathOpsString
}

def nodeCompiler(implicit
                 ec: ExecutionContext
                ): NodeFileOpsFree.Op ~> Future = new(NodeFileOpsFree.Op ~> Future) {
  def apply[A](fa: NodeFileOpsFree.Op[A]): Future[A] = fa match {
    case NodeFileOpsFree.CatchErrors(body) => {
      body.map(Right(_)).foldMap(nodeCompiler).recover { case e: js.JavaScriptException => Left(e) }
    }
    case NodeFileOpsFree.Read(path) => fsPromisesMod.readFile(path, BufferEncoding.utf8)
    case NodeFileOpsFree.WriteString(path, content, append) => {
      if (append) {
        fsPromisesMod.appendFile(path, content)
      } else {
        fsPromisesMod.writeFile(path, content)
      }
    }
    case NodeFileOpsFree.Write(path, content, append) => {
      val result = Uint8Array.from(js.Array(content.map(x => x.toShort) *)) // TODO: too slow
      if (append) {
        fsPromisesMod.appendFile(path, result)
      } else {
        fsPromisesMod.writeFile(path, result)
      }
    }
    case NodeFileOpsFree.RemoveWhenExists(path) => fsPromisesMod.unlink(path).map(_ => true).recover { case e: js.JavaScriptException => false }
    case NodeFileOpsFree.GetHomeDir => Future.successful(osMod.homedir())
    case NodeFileOpsFree.Exists(path) => Future.successful(fsMod.existsSync(path))
    case NodeFileOpsFree.CreateDirIfNotExists(path) => fsPromisesMod.mkdir(path, MakeDirectoryOptions().setRecursive(true)).map(_ => ())
    case NodeFileOpsFree.DownloadToFile(url, path) => ???
  }
}

def nodeCompile0[R](prog: FileOpsEff ?=> Control[R])(implicit ec: ExecutionContext): NodeFileOpsFree.M[R] = new Handler[NodeFileOpsFree.M[R]] with FileOpsEff {
  type P = NodeFileOpsFree.P

  def pathOps = NodeFileOpsFree.pathOps

  def catchErrors[A](eff: => M[A]) = use {
    k =>
      effekt.pure(NodeFileOpsFree.catchErrors(nodeCompile0({
        eff
      })).flatMap(x => k(x).run()))
  }

  def read(path: P) = use {
    k => effekt.pure(NodeFileOpsFree.read(path).flatMap(x => k(x).run()))
  }

  override def writeString(path: P, content: String, append: Boolean) = use {
    k => effekt.pure(NodeFileOpsFree.writeString(path, content, append).flatMap(x => k(x).run()))
  }

  override def write(path: P, content: Array[Byte], append: Boolean) = use {
    k => effekt.pure(NodeFileOpsFree.write(path, content, append).flatMap(x => k(x).run()))
  }

  def removeWhenExists(path: P) = use {
    k => effekt.pure(NodeFileOpsFree.removeWhenExists(path).flatMap(x => k(x).run()))
  }

  def getHomeDir = use {
    k => effekt.pure(NodeFileOpsFree.getHomeDir.flatMap(x => k(x).run()))
  }

  def exists(path: P) = use {
    k => effekt.pure(NodeFileOpsFree.exists(path).flatMap(x => k(x).run()))
  }

  def createDirIfNotExists(path: P) = use {
    k => effekt.pure(NodeFileOpsFree.createDirIfNotExists(path).flatMap(x => k(x).run()))
  }

  def downloadToFile(url: String, path: P) = use {
    k => effekt.pure(NodeFileOpsFree.downloadToFile(url, path).flatMap(x => k(x).run()))
  }

  def chmodExecutable(path: P) = use {
    k => effekt.pure(NodeFileOpsFree.chmodExecutable(path).flatMap(x => k(x).run()))
  }

  def getAbsolutePath(path: P) = use {
    k => effekt.pure(NodeFileOpsFree.getAbsolutePath(path).flatMap(x => k(x).run()))
  }
}.handle((a: FileOpsEff) => prog(using a).map(x => Free.pure(x))).run()


def nodeCompile[A](prog: NodeFileOpsFree.M[A])(implicit ec: ExecutionContext): Future[A] = prog.foldMap(nodeCompiler)
def nodeCompile[A](prog: FileOpsEff ?=> Control[A])(implicit ec: ExecutionContext): Future[A] = nodeCompile(nodeCompile0(prog))