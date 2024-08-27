package chester.tyck

import chester.error.{TyckError, TyckWarning}

// maybe add marks about what item is used when there is a multiple choice. Maybe report some warning when two or more candidates are equally good
case class TyckResult[+S, +T](state: S, result: T, warnings: Vector[TyckWarning] = Vector(), errors: Vector[TyckError] = Vector()) {
  def errorsEmpty: Boolean = errors.isEmpty

  def >>[S, T](next: TyckResult[S, T]): TyckResult[S, T] = {
    TyckResult(
      state = next.state,
      result = next.result,
      warnings = warnings ++ next.warnings,
      errors = errors ++ next.errors
    )
  }
}

object TyckResult {
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
