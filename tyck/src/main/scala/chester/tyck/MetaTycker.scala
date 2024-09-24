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

  def collectMetaTerms(term: Term): Set[MetaTerm] = {
    var metas = Set.empty[MetaTerm]

    def traverse(t: Term): Unit = t match {
      case meta: MetaTerm =>
        metas += meta
      case _ =>
        t.descent(x=>{
          traverse(x)
          x
        })
        ()
    }

    traverse(term)
    metas
  }

  def zonkMetas(term: Term): Term = {
    // Collect all MetaTerms in the term
    val metas = collectMetaTerms(term).toVector.distinctBy(_.uniqId)

    // Fetch constraints related to these MetaTerms
    val relatedConstraints = tyck.getState.constraints.filter(c => metas.exists(_.uniqId == c.metaVar.uniqId))

    // Solve the constraints before substitution
    solveConstraints(metas, relatedConstraints)

    val subst = tyck.getState.subst

    def substitute(term: Term, visited: Set[UniqId] = Set.empty): Term = term match {
      case meta: MetaTerm =>
        if (visited.contains(meta.uniqId)) {
          // Prevent infinite recursion in case of cyclic substitutions
          meta
        } else {
          subst.get(meta.uniqId) match {
            case Some(judge) => substitute(judge.wellTyped, visited + meta.uniqId)
            case None => meta
          }
        }
      case _ => term.descent(subTerm => substitute(subTerm, visited))
    }

    substitute(term)
  }

  def zonkMetas(judge: Judge): Judge = {
    val wellTyped = zonkMetas(judge.wellTyped)
    val ty = zonkMetas(judge.ty)
    val effects = judge.effects.descent(zonkMetas)
    Judge(wellTyped, ty, effects)
  }

  private def solveConstraints(metas: Vector[MetaTerm], constraints: Vector[MetaConstraint]): Unit = {
    val results = metas.map { meta =>
      tyck.getState.subst.get(meta.uniqId) match {
        case Some(existingJudge) =>
          (meta, existingJudge)
        case None =>
          val metaConstraints = constraints.collect {
            case c @ MetaConstraint.TyRange(`meta`, _, _) => c
          }

          val (lowers, uppers) = metaConstraints.foldLeft((Vector.empty[Judge], Vector.empty[Judge])) {
            case ((lowerAcc, upperAcc), MetaConstraint.TyRange(_, lower, upper)) =>
              (lowerAcc ++ lower.toVector, upperAcc ++ upper.toVector)
          }

          val solution = if (lowers.nonEmpty || uppers.nonEmpty) {
            computeSolution(meta, lowers, uppers)
          } else {
            // Default to AnyType0 when no constraints or substitutions exist
            Judge(AnyType0, Typeω, NoEffect)
          }

          (meta, solution)
      }
    }

    tyck.updateState { state =>
      val newSubst = state.subst ++ results.collect {
        case (meta, judge) if judge.wellTyped != meta => meta.uniqId -> judge
      }
      val remainingConstraints = state.constraints.filterNot(c => metas.exists(_.uniqId == c.metaVar.uniqId))
      state.copy(subst = newSubst, constraints = remainingConstraints)
    }
  }

  def computeSolution(meta: MetaTerm, lowers: Vector[Judge], uppers: Vector[Judge]): Judge = {
    (lowers, uppers) match {
      case (Nil, Nil) =>
        // No constraints; this case should not occur here
        Judge(AnyType0, Typeω, NoEffect)
      case _ =>
        // Combine lower and upper bounds to find the best solution
        val lowerType = lowers.map(_.wellTyped).reduceOption(this.common)
        val upperType = uppers.map(_.wellTyped).headOption // TODO

        (lowerType, upperType) match {
          case (Some(lower), Some(upper)) =>
            if (this.compareTy(lower, upper) == Trilean.True) {
              Judge(lower, Typeω, NoEffect)
            } else {
              // If lower is not a subtype of upper, default to AnyType0
              Judge(AnyType0, Typeω, NoEffect)
            }
          case (Some(ty), None) => Judge(ty, Typeω, NoEffect)
          case (None, Some(ty)) => Judge(ty, Typeω, NoEffect)
          case _ => Judge(AnyType0, Typeω, NoEffect)
        }
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
