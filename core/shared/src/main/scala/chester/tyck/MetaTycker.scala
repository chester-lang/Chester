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
  def finishMeta(term: MetaTerm): Judge = {
    ??? // TODO
  }

  def finishMetas(term: Term): Term = {
    val metas = term.mapFlatten {
      case meta: MetaTerm => Vector(meta)
      case _ => Vector()
    }.distinctBy(_.uniqId)
    val subst: Seq[(MetaTerm, Term)] = metas.map { meta =>
      val judge = finishMeta(meta)
      meta -> judge.wellTyped
    }
    term.substitute(subst)
  }

  def finishMetas(judge: Judge): Judge = {
    val wellTyped = finishMetas(judge.wellTyped)
    val ty = finishMetas(judge.ty)
    val effects = judge.effects.descent(finishMetas)
    Judge(wellTyped, ty, effects)
  }
}
