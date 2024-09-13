package chester.tyck

import chester.error.*
import chester.resolve.ExprResolver
import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.{HasUniqId, *}
import spire.math.Trilean
import spire.math.Trilean.{True, Unknown}

import scala.annotation.tailrec
import scala.language.implicitConversions
import chester.syntax.core.*

trait MetaTycker[Self <: TyckerBase[Self] & TelescopeTycker[Self] & EffTycker[Self] & MetaTycker[Self]] extends Tycker[Self] {

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
  def freezeMeta(term: MetaTerm): Judge = {
    val state = tyck.getState
    state.subst.read(term) match {
      case Some(Constraint.Is(judge)) => judge
      case Some(Constraint.TyRange(lower, upper)) =>
        val result = upper.orElse(lower).get
        val newSubst = state.subst.update(term.uniqId, Constraint.Is(result))
        tyck.state.set(state.copy(subst = newSubst))
        result
      case None =>
        val result = Judge(AnyType0, Type0, NoEffect) // TODO: level
        val newSubst = state.subst.update(term.uniqId, Constraint.Is(result))
        tyck.state.set(state.copy(subst = newSubst))
        result
    }
  }

  def freezeMetas(term: Term): Term = {
    val metas = term.mapFlatten {
      case meta: MetaTerm => Vector(meta)
      case _ => Vector()
    }.distinctBy(_.uniqId)
    val subst: Seq[(MetaTerm, Term)] = metas.map { meta =>
      val judge = freezeMeta(meta)
      meta -> judge.wellTyped
    }
    term.substitute(subst)
  }

  def freezeMetas(judge: Judge): Judge = {
    val wellTyped = freezeMetas(judge.wellTyped)
    val ty = freezeMetas(judge.ty)
    val effects = judge.effects.descent(freezeMetas)
    Judge(wellTyped, ty, effects)
  }
}
