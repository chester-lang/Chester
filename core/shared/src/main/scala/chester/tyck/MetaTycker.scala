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

trait MetaTycker[Self <: TyckerBase[Self] & FunctionTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends TyckerTrait[Self] {

  def genTypeVariable(name: Option[Name] = None, ty: Option[Term] = None, meta: OptionTermMeta = None): Term = {
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
    
    solveConstraints(metas)
    
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

// TODO: rewrite
  private def solveConstraints(metas: Vector[MetaTerm]): Unit = {
    // First step: find related constraints and remove them from the state
    val relatedConstraints = tyck.updateAndMap { state =>
      val (related, remaining) = state.constraints.partition(c => 
        c.metas.exists(meta => metas.contains(meta))
      )
      (state.copy(constraints = remaining), related)
    }

    // Solve constraints
    for (constraint <- relatedConstraints) {
      constraint match {
        case Constraint.TyRange(metaVar, lower, upper) =>
          val solution = upper.orElse(lower).getOrElse(Judge(AnyType0, Type0, NoEffect))
          tyck.updateSubst(metaVar.uniqId, solution)
        case Constraint.Subtype(sub, sup) =>
          (sub, sup) match {
            case (m: MetaTerm, t) => tyck.updateSubst(m.uniqId, Judge(t, this.synthesizeTyTerm(t).ty, NoEffect))
            case (t, m: MetaTerm) => tyck.updateSubst(m.uniqId, Judge(t, this.synthesizeTyTerm(t).ty, NoEffect))
            case _ => // This case should never happen due to the Subtype constraint definition
          }
      }
    }

    // Assign AnyType0 to unsolved metas
    for (meta <- metas) {
      if (!tyck.getState.subst.contains(meta.uniqId)) {
        tyck.updateSubst(meta.uniqId, Judge(AnyType0, Type0, NoEffect))
      }
    }
  }
}
