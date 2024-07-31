package chester.tyck

import chester.syntax.concrete.Expr
import chester.syntax.core.Term

case class TyckState()

case class LocalCtx()

case class Judge(wellTyped: Term, ty: Term)

case class TyckError(message: String)

object TyckError {
  val emptyResults = TyckError("Empty Results")
}

case class Results[T](xs: LazyList[Either[TyckError, T]]) {
  def map[U](f: T => U): Results[U] = Results(xs.map(_.map(f)))

  def flatMap[U](f: T => Results[U]): Results[U] = Results(xs.flatMap {
    case Left(err) => LazyList(Left(err))
    case Right(x) => f(x).xs
  })

  def getEither: Either[TyckError, T] = xs.find(_.isRight).getOrElse(xs.headOption.getOrElse(Left(TyckError.emptyResults)))
}


case class ExprTycker(state: TyckState, localCtx: LocalCtx) {
  def inherit(expr: Expr, ty: Term): Results[Judge] = ???

  def synthesize(expr: Expr): Results[Judge] = ???
}
