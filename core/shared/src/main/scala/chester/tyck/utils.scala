package chester.tyck

import chester.syntax.concrete.ExprMeta
import chester.syntax.core.TermMeta

import chester.error.*
import chester.resolve.ExprResolver
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.utils.reuse
import spire.math.Trilean
import spire.math.Trilean.{True, Unknown}

import scala.annotation.tailrec
import scala.language.implicitConversions

def convertMeta(meta: Option[ExprMeta]): Option[TermMeta] = {
  meta.map(exprMeta => TermMeta(exprMeta.sourcePos))
}

sealed trait Constraint

object Constraint {
  case class Is(judge: Judge) extends Constraint

  case class TyRange(lower: Option[Judge], upper: Option[Judge]) extends Constraint {
    require(lower.isDefined || upper.isDefined)
  }
}

type Solutions = Map[UniqId, Constraint]

object Solutions {
  val Empty: Solutions = Map.empty
}

extension (subst: Solutions) {
  @tailrec
  def walk(term: MetaTerm): Judge = subst.get(term.id) match {
    case Some(Constraint.Is(clause)) => clause.wellTyped match {
      case term: MetaTerm => subst.walk(term)
      case _ => Judge(clause.wellTyped, clause.ty, clause.effects)
    }
    case Some(Constraint.TyRange(lower, upper)) => Judge(term, term.ty, term.effect) // TODO
    case None => Judge(term, term.ty, term.effect)
  }
  def isDefined(term: MetaTerm): Boolean = subst.contains(term.id)
  def read(term: MetaTerm): Option[Constraint] = subst.get(term.id) match {
    case some@Some(Constraint.Is(Judge(meta2: MetaTerm, ty, effect))) => read(meta2).orElse(some)
    case Some(x) => Some(x)
    case None => None
  }
}

case class TyckState(subst: Solutions = Solutions.Empty)

case class LocalCtx(ctx: Context = Context.builtin) {
  def resolve(id: Name): Option[CtxItem] = ctx.get(id)

  def resolve(id: UniqId): Option[CtxItem] = ctx.getByVarId(id)

  def extend(name: LocalVar): LocalCtx = copy(ctx = ctx.extend(name))
}

object LocalCtx {
  val Empty = LocalCtx()

  def fromParent(parent: LocalCtx): LocalCtx = parent
}

case class WithCtxEffect[T](ctx: LocalCtx, effect: Effects, value: T)

// https://www.alessandrolacava.com/blog/scala-self-recursive-types/
trait Tycker[Self <: Tycker[Self]] {
  def ev: this.type <:< Self

  protected def thisToSelfTypesafe(x: this.type): Self = ev.apply(x)

  implicit protected inline def thisToSelfImplementation(ignored: this.type): Self = this.asInstanceOf[Self]

  def copy(localCtx: LocalCtx = localCtx, tyck: Tyck = tyck): Self

  inline final def rec(localCtx: LocalCtx): Self = copy(localCtx = localCtx)

  final def tryOne[A](xs: Self => A): TyckResult[TyckState, A] = {
    Tyck.run(tyck => xs(copy(tyck = tyck)))(tyck.getState)
  }

  private final def writeResult[A](x: TyckResult[TyckState, A]): A = {
    tyck.state.set(x.state)
    x.result
  }

  final def tryAll[A](xs: Seq[Self => A]): A = {
    require(xs.nonEmpty)
    var firstTry: Option[TyckResult[TyckState, A]] = None
    for (x <- xs) {
      val result = tryOne(x)
      if (result.errorsEmpty) {
        return writeResult(result)
      }
      if (firstTry.isEmpty) {
        firstTry = Some(result)
      }
    }
    writeResult(firstTry.get)
  }

  def localCtx: LocalCtx

  def tyck: Tyck
}

case class ExprTyckerInternal(localCtx: LocalCtx = LocalCtx.Empty, tyck: Tyck) extends TyckerBase[ExprTyckerInternal] with TelescopeTycker[ExprTyckerInternal] with EffTycker[ExprTyckerInternal] with MetaTycker[ExprTyckerInternal] {
  override def ev: this.type <:< ExprTyckerInternal = implicitly[this.type <:< ExprTyckerInternal]
}
