package chester.tyck

import chester.syntax.*
import chester.syntax.core.*
import io.github.iltotore.iron.refineUnsafe
import spire.math.Trilean

import scala.language.implicitConversions

trait MetaTycker[Self <: TyckerBase[Self] & FunctionTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends TyckerTrait[Self] {

  def genTypeVariable(name: Option[Name] = None, ty: Option[Term] = None, meta: OptionTermMeta = None): MetaTerm = {
    val id = name.getOrElse("t")
    val varid = UniqId.generate
    MetaTerm(id, varid, ty.getOrElse(Typeω), meta = meta)
  }

  inline final def walk(term: MetaTerm): Judge = {
    val state = tyck.getState
    val result = state.subst.walk(term)
    result
  }

  inline final def walkOption(term: MetaTerm): Option[Judge] = {
    val state = tyck.getState
    val result = state.subst.walkOption(term)
    result
  }

  private def zonkMeta(term: MetaTerm): Judge = {
    val state = tyck.getState
    state.subst.get(term.uniqId) match {
      case Some(judge) => judge
      case None =>
        throw new IllegalStateException(s"MetaTerm ${term.uniqId} not found in substitution")
    }
  }

  def zonkMetas(term: Term): Term = {
    val metas = term.mapFlatten {
      case meta: MetaTerm => Vector(meta)
      case _ => Vector()
    }.distinctBy(_.uniqId)

    val relatedConstraints = tyck.getState.constraints.filter(c => metas.exists(_.uniqId == c.metaVar.uniqId))
    solveConstraints(metas, relatedConstraints)

    val subst: Seq[(MetaTerm, Term)] = metas.map { meta =>
      val judge = zonkMeta(meta)
      meta -> judge.wellTyped
    }
    term.substitute(subst)
  }

  def zonkMetas(judge: Judge): Judge = {
    val wellTyped = zonkMetas(judge.wellTyped)
    val ty = zonkMetas(judge.ty)
    val effects = judge.effects.descent(zonkMetas)
    Judge(wellTyped, ty, effects)
  }

  private def solveConstraints(metas: Vector[MetaTerm], constraints: Vector[Constraint]): Unit = {
    val results = metas.map { meta =>
      val metaConstraints = constraints.collect {
        case c @ Constraint.TyRange(`meta`, _, _) => c
      }

      val (lowers, uppers) = metaConstraints.foldLeft((Vector.empty[Judge], Vector.empty[Judge])) {
        case ((lowerAcc, upperAcc), Constraint.TyRange(_, lower, upper)) =>
          (lowerAcc ++ lower.toVector, upperAcc ++ upper.toVector)
      }

      val lowerBound = lowers.reduceOption { (a, b) =>
        val commonTy = this.common(a.wellTyped, b.wellTyped)
        Judge(commonTy, Typeω, NoEffect)
      }

      val upperBound = uppers.reduceOption { (a, b) =>
        val unifiedTy = this.unifyTy(a.wellTyped, b.wellTyped)
        Judge(unifiedTy, Typeω, NoEffect)
      }

      val solution = (lowerBound, upperBound) match {
        case (Some(lower), Some(upper)) =>
          // Ensure lower ≤ solution ≤ upper
          val candidate = if (this.compareTy(lower.wellTyped, upper.wellTyped) == Trilean.True) lower.wellTyped else upper.wellTyped
          Judge(candidate, Typeω, NoEffect)
        case (Some(bound), None) => bound
        case (None, Some(bound)) => bound
        case (None, None) => Judge(AnyType0, Type0, NoEffect)
      }

      (meta, solution)
    }

    tyck.updateState { state =>
      val newSubst = state.subst ++ results.map { case (meta, judge) => meta.uniqId -> judge }
      val remainingConstraints = state.constraints.filterNot(c => metas.exists(_.uniqId == c.metaVar.uniqId))
      state.copy(subst = newSubst, constraints = remainingConstraints)
    }
  }

  // TODO: clarify the implementation
  def deferredTermOperation(term: Term, operation: Term => Term): Term = {
    term match {
      case meta: MetaTerm =>
        val walked = walk(meta)
        walked.wellTyped match {
          case _: MetaTerm =>
            val resultMeta = genTypeVariable(name = Some(s"${meta.description}_result"))
            val action = DeferredAction(
              dependsOn = Vector(meta).refineUnsafe,
              affects = Vector(resultMeta),
              computation = { tyck =>
                val result = operation(tyck.getState.subst.walk(meta).wellTyped)
                tyck.updateSubst(resultMeta.uniqId, Judge(result, Typeω, NoEffect))
              }
            )
            tyck.updateState { state =>
              state.copy(deferredActions = state.deferredActions :+ action)
            }
            resultMeta
          case _ =>
            operation(walked.wellTyped)
        }
      case _ =>
        operation(term)
    }
  }
}
