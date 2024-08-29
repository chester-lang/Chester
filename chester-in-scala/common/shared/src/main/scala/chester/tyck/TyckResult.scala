package chester.tyck

import chester.error.{TyckError, TyckWarning}

// maybe add marks about what item is used when there is a multiple choice. Maybe report some warning when two or more candidates are equally good
case class TyckResult0[TyckWarning, TyckError, +S, +T](state: S, result: T, warnings: Vector[TyckWarning] = Vector(), errors: Vector[TyckError] = Vector()) {
  def errorsEmpty: Boolean = errors.isEmpty

  def >>[S, T](next: TyckResult0[TyckWarning, TyckError, S, T]): TyckResult0[TyckWarning, TyckError, S, T] = {
    TyckResult0(
      state = next.state,
      result = next.result,
      warnings = warnings ++ next.warnings,
      errors = errors ++ next.errors
    )
  }
}

type TyckResult[+S, +T] = TyckResult0[TyckWarning, TyckError, S, T]

object TyckResult {
  def apply[S, T](state: S, result: T, warnings: Vector[TyckWarning] = Vector(), errors: Vector[TyckError] = Vector()): TyckResult[S, T] = {
    TyckResult0(state, result, warnings, errors)
  }

  object Success {
    def unapply[S, T](x: TyckResult[S, T]): Option[(T, S, Vector[TyckWarning])] = {
      if (x.errors.isEmpty) Some((x.result, x.state, x.warnings))
      else None
    }
  }

  object Failure {
    def unapply[S, T](x: TyckResult[S, T]): Option[(Vector[TyckError], Vector[TyckWarning], S, T)] = {
      if (x.errors.nonEmpty) Some((x.errors, x.warnings, x.state, x.result))
      else None
    }
  }
}
