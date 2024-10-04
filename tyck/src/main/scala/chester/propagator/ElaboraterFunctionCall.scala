package chester.propagator

import chester.error.FunctionCallUnificationError
import chester.syntax.concrete._
import chester.syntax.core._

trait ElaboraterFunctionCall extends ProvideCtx with Elaborater {
  def elabFunctionCall(
    expr: DesaltFunctionCall,
    ty: CellId[Term],
    effects: CellId[Effects]
  )(using
    ctx: LocalCtx,
    parameter: Global,
    ck: Ck,
    state: StateAbility[Ck]
  ): Term
}

trait ProvideElaboraterFunctionCall extends ElaboraterFunctionCall {
  override def elabFunctionCall(
    expr: DesaltFunctionCall,
    ty: CellId[Term],
    effects: CellId[Effects]
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
      state.read(functionTy) match {
        case Some(FunctionType(telescopes, retTy, _, _, _)) =>
          // Unify the arguments with the function's parameters
          unifyTelescopes(telescopes, callings, cause)
          // Unify the result type
          unify(resultTy, retTy, cause)
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
    ): Unit = {
      // Check that the number of telescopes matches
      if (expected.length != actual.length) {
        val argTypes = actual.flatMap(_.args.map(_.value))
        val functionType = FunctionType(expected, newTypeTerm)
        ck.reporter(FunctionCallUnificationError(functionType, argTypes, cause))
        return
      }

      expected.zip(actual).foreach { case (expectedTele, actualCalling) =>
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
    ): Unit = {
      // Check that the number of arguments matches
      if (expectedArgs.length != actualArgs.length) {
        val argTypes = actualArgs.map(_.value)
        val expectedTypes = expectedArgs.map(_.ty)
        ck.reporter(FunctionCallUnificationError(FunctionType(Vector.empty, newTypeTerm), argTypes, cause))
        return
      }

      expectedArgs.zip(actualArgs).foreach { case (expectedArg, actualArg) =>
        // Unify argument types
        unify(actualArg.value, expectedArg.ty, cause)
      }
    }

    override def naiveZonk(needed: Vector[CellId[?]])(using state: StateAbility[Ck], ck: Ck): ZonkResult = {
      // Implement zonking logic if needed
      ZonkResult.Done
    }
  }
}