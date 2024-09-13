package chester.tyck

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

}
