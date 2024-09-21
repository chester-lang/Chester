package chester.tyck

import chester.syntax.concrete.ExprMeta
import chester.syntax.core.TermMeta
import chester.error.*
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import chester.utils.reuse
import io.github.iltotore.iron.constraint.all.MinLength
import spire.math.Trilean
import spire.math.Trilean.{True, Unknown}
import scala.annotation.tailrec
import scala.language.implicitConversions
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.constraint.collection.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.numeric.*
import io.github.iltotore.iron.*
import io.github.iltotore.iron.constraint.all.*
import io.github.iltotore.iron.upickle.given

def convertMeta(meta: Option[ExprMeta]): Option[TermMeta] = {
  meta.map(exprMeta => TermMeta(exprMeta.sourcePos))
}

sealed trait Constraint {
  def metaVar: MetaTerm
  def contains(meta: MetaTerm): Boolean = metaVar == meta
}

object Constraint {
  case class TyRange(metaVar: MetaTerm, lower: Option[Judge], upper: Option[Judge]) extends Constraint {
    require(lower.isDefined || upper.isDefined)
  }
}

type Substitutions = Map[UniqId, Judge]
type Constraints = Vector[Constraint]

object Substitutions {
  val Empty: Substitutions = Map.empty
}

object Constraints {
  val Empty: Constraints = Vector.empty
}

extension (subst: Substitutions) {
  def walk(term: MetaTerm): Judge = subst.get(term.uniqId) match {
    case Some(judge) => judge
    case None => Judge(term, term.ty, term.effect)
  }

  def isDefined(term: MetaTerm): Boolean = subst.contains(term.uniqId)

  def update(id: UniqId, judge: Judge): Substitutions = {
    require(!subst.contains(id))
    subst + (id -> judge)
  }
}

extension (constraints: Constraints) {
  def contains(meta: MetaTerm): Boolean = constraints.exists(_.contains(meta))
}

case class TyckState(
  subst: Substitutions = Substitutions.Empty, 
  constraints: Constraints = Constraints.Empty,
  deferredActions: DeferredActions = Vector.empty
)

extension (tyck: Tyck) {
  def updateSubst(id: UniqId, judge: Judge): Unit = {
    // Update substitution
    tyck.updateState { state =>
      require(!state.subst.contains(id))
      val newSubst = state.subst + (id -> judge)
      state.copy(subst = newSubst)
    }

    runDeferredActions()
  }

  @tailrec
  private def runDeferredActions(): Unit = {
    val actionsToRun = tyck.updateAndMap { state =>
      val (toRun, remaining) = state.deferredActions.partition { action =>
        action.dependsOn.forall(meta => state.subst.contains(meta.uniqId))
      }
      (state.copy(deferredActions = remaining), toRun)
    }

    if (actionsToRun.nonEmpty) {
      actionsToRun.foreach(_.computation(tyck))
      runDeferredActions() // Recursive call to handle newly enabled actions
    }
  }
}

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
trait TyckerTrait[Self <: TyckerTrait[Self]] {
  def ev: this.type <:< Self

  protected def thisToSelfTypesafe(x: this.type): Self = ev.apply(x)

  implicit protected inline def thisToSelfImplementation(ignored: this.type): Self = this.asInstanceOf[Self]

  def copy(localCtx: LocalCtx = localCtx, tyck: Tyck = tyck): Self

  inline final def rec(localCtx: LocalCtx): Self = copy(localCtx = localCtx)

  final def tryOne[A](xs: Self => A): TyckResult[TyckState, A] = {
    Tyck.run(tyck => xs(copy(tyck = tyck)))(tyck.getState)
  }

  private final def uncheckedWriteResult[A](x: TyckResult[TyckState, A]): A = {
    tyck.uncheckedSetState(x.state)
    x.result
  }

  final def tryAll[A](xs: Seq[Self => A] :| MinLength[1]): A = {
    var firstTry: Option[TyckResult[TyckState, A]] = None
    for (x <- xs) {
      val result = tryOne(x)
      if (result.errorsEmpty) {
        return uncheckedWriteResult(result)
      }
      if (firstTry.isEmpty) {
        firstTry = Some(result)
      }
    }
    uncheckedWriteResult(firstTry.get)
  }

  def localCtx: LocalCtx

  def tyck: Tyck
}

case class Tycker(localCtx: LocalCtx = LocalCtx.Empty, tyck: Tyck) extends TyckerBase[Tycker] with FunctionTycker[Tycker] with EffTycker[Tycker] with MetaTycker[Tycker] {
  override def ev: this.type <:< Tycker = implicitly[this.type <:< Tycker]
}

case class DeferredAction(
  dependsOn: Vector[MetaTerm] :| MinLength[1],
  affects: Vector[MetaTerm],
  computation: Tyck => Unit
) {
}

type DeferredActions = Vector[DeferredAction]