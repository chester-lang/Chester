package chester.tyck

import chester.error.{TyckError, TyckWarning}

case class TyckResult[+S, +T](state: S, result: T, warnings: Vector[TyckWarning] = Vector(), errors: Vector[TyckError] = Vector()) {
  def errorsEmpty: Boolean = errors.isEmpty
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
