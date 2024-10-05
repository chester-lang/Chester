package chester.propagator

import chester.error.*
import chester.syntax.concrete._
import chester.syntax.core._

trait ElaboraterFunctionCall extends ProvideCtx with Elaborater {
  def elabFunctionCall(
    expr: DesaltFunctionCall,
    ty: CellId[Term],
    effects: CIdOf[EffectsCell]
  )(using
    ctx: LocalCtx,
    parameter: Global,
    ck: Ck,
    state: StateAbility[Ck]
  ): Term
}

// TODO: incorrect, fix this
trait ProvideElaboraterFunctionCall extends ElaboraterFunctionCall {
  override def elabFunctionCall(
    expr: DesaltFunctionCall,
    ty: CellId[Term],
    effects: CIdOf[EffectsCell]
  )(using
    ctx: LocalCtx,
    parameter: Global,
    ck: Ck,
    state: StateAbility[Ck]
  ): Term = {

    // Elaborate the function expression to get its term and type
    val functionTy = newType
    val functionTerm = elab(expr.function, functionTy, effects)

    // Elaborate the arguments in the telescopes
    val callings = expr.telescopes.map { telescope =>
      val callingArgs = telescope.args.map { arg =>
        val argTy = newType
        val argTerm = elab(arg.expr, argTy, effects)
        CallingArgTerm(argTerm, arg.name.map(_.name), arg.vararg)
      }
      Calling(callingArgs, telescope.implicitly)
    }

    // Create the function call term
    val functionCallTerm = FCallTerm(functionTerm, callings.toVector)

    // Create a new type variable for the function's result type
    val resultTy = newType

    // Add a propagator to unify the function type with the arguments
    state.addPropagator(
      UnifyFunctionCall(functionTy, callings.toVector, resultTy, expr)
    )

    // Unify the result type with the expected type
    unify(ty, resultTy, expr)

    functionCallTerm
  }

  case class UnifyFunctionCall(
    functionTy: CellId[Term],
    callings: Vector[Calling],
    resultTy: CellId[Term],
    cause: Expr
  )(using localCtx: LocalCtx)
      extends Propagator[Ck] {

    override val readingCells: Set[CellId[?]] = Set(functionTy)
    override val writingCells: Set[CellId[?]] = Set(resultTy)
    override val zonkingCells: Set[CellId[?]] = Set(functionTy, resultTy)

    override def run(using state: StateAbility[Ck], ck: Ck): Boolean = {
      state.readStable(functionTy) match {
        case Some(FunctionType(telescopes, retTy, _, _, _)) =>
          // Unify the arguments with the function's parameters
          if (unifyTelescopes(telescopes, callings, cause)) {
            // Unify the result type
            unify(resultTy, retTy, cause)
          }
          true
        case Some(Meta(id)) =>
          // If the function type is a meta variable, delay until it is known
          state.addPropagator(UnifyFunctionCall(id, callings, resultTy, cause))
          true
        case Some(other) =>
          // Report specific function call unification error
          val argTypes = callings.flatMap(_.args.map(_.value))
          ck.reporter(FunctionCallUnificationError(other, argTypes, cause))
          true
        case None =>
          // Function type is not yet known, cannot proceed
          false
      }
    }

    def unifyTelescopes(
      expected: Vector[TelescopeTerm],
      actual: Vector[Calling],
      cause: Expr
    )(using
      state: StateAbility[Ck],
      ck: Ck
    ): Boolean = {
      // Check that the number of telescopes matches
      if (expected.length != actual.length) {
        val argTypes = actual.flatMap(_.args.map(_.value))
        val functionType = FunctionType(expected, newTypeTerm)
        ck.reporter(FunctionCallArityMismatchError(expected.length, actual.length, cause))
        return false
      }

      expected.zip(actual).forall { case (expectedTele, actualCalling) =>
        unifyArgs(expectedTele.args, actualCalling.args, cause)
      }
    }

    def unifyArgs(
      expectedArgs: Vector[ArgTerm],
      actualArgs: Vector[CallingArgTerm],
      cause: Expr
    )(using
      state: StateAbility[Ck],
      ck: Ck
    ): Boolean = {
      // Check that the number of arguments matches
      if (expectedArgs.length != actualArgs.length) {
        ck.reporter(FunctionCallArgumentMismatchError(expectedArgs.length, actualArgs.length, cause))
        return false
      }

      expectedArgs.zip(actualArgs).foreach { case (expectedArg, actualArg) =>
        // Unify argument types
        unify(actualArg.value, expectedArg.ty, cause)
      }
      true
    }

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], ck: Ck): ZonkResult = {
      // Implement zonking logic if needed
      ZonkResult.NotYet
    }
  }
}