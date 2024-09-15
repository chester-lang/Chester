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

  /** Make it concrete values, also store in state */
  def zonkMeta(term: MetaTerm): Judge = {
    val state = tyck.getState
    state.subst.get(term.uniqId) match {
      case Some(judge) => judge
      case None =>
        val relatedConstraints = state.constraints.filter(_.metaVar == term)
        relatedConstraints match {
          case Vector() =>
            val result = Judge(AnyType0, Type0, NoEffect) // TODO: level
            val newSubst = state.subst.update(term.uniqId, result)
            tyck.state.set(state.copy(subst = newSubst))
            result
          case Vector(Constraint.TyRange(_, lower, upper)) =>
            val result = upper.orElse(lower).get
            val newSubst = state.subst.update(term.uniqId, result)
            tyck.state.set(state.copy(subst = newSubst))
            result
          case _ =>
            throw new UnsupportedOperationException("Multiple constraints for a single meta variable are not yet supported.")
        }
    }
  }

  def zonkMetas(term: Term): Term = {
    val metas = term.mapFlatten {
      case meta: MetaTerm => Vector(meta)
      case _ => Vector()
    }.distinctBy(_.uniqId)
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
}
