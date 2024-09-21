package chester.tyck

import chester.error.*
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.{HasUniqId, *}
import spire.math.Trilean
import spire.math.Trilean.{True, Unknown}

import scala.annotation.tailrec
import scala.language.implicitConversions
import chester.syntax.core.*
import io.github.iltotore.iron.autoRefine
import io.github.iltotore.iron.refineUnsafe

trait MetaTycker[Self <: TyckerBase[Self] & FunctionTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends TyckerTrait[Self] {

  def genTypeVariable(name: Option[Name] = None, ty: Option[Term] = None, meta: OptionTermMeta = None): MetaTerm = {
    val id = name.getOrElse("t")
    val varid = UniqId.generate
    MetaTerm(id, varid, ty.getOrElse(Typeω), meta = meta)
  }

  def walk(term: MetaTerm): Judge = {
    val state = tyck.getState
    val result = state.subst.walk(term)
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
      val metaConstraints = constraints.filter(_.metaVar == meta)
      val result = metaConstraints match {
        case Vector() => Judge(AnyType0, Type0, NoEffect)
        case Vector(Constraint.TyRange(_, lower, upper)) =>
          upper.orElse(lower).getOrElse(Judge(AnyType0, Type0, NoEffect))
        case _ =>
          throw new UnsupportedOperationException("Multiple constraints for a single meta variable are not yet supported.")
      }
      (meta, result)
    }

    tyck.updateState { state =>
      val newSubst = state.subst ++ results.map(r => r._1.uniqId -> r._2)
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
