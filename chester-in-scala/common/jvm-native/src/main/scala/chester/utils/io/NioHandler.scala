package chester.utils.io

import effekt.{Control, Handler}

class NioHandler[R] extends Handler[R] with NioOps

inline def nioHandler[R](inline prog: FileOpsEff ?=> Control[R]):Control[R] = {
  trait NioOps extends FileOpsEff {
    type P = java.nio.file.Path
    def pathOps: PathOps[P] = new PathOps[P] {
      def of(path: String): P = java.nio.file.Paths.get(path)

      def join(p1: P, p2: P): P = p1.resolve(p2)

      def asString(p: P): String = p.toString
    }
  }
  new Handler[R] with NioOps {
    
  }
}