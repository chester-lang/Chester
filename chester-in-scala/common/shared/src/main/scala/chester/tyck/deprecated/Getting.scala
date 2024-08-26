package chester.tyck.deprecated

import cats.data.State
import chester.error.EmptyResultsError

@deprecated
case class Getting[W, E, S, T](run: S => LazyList[(Vector[W], Vector[E], Option[(S, T)])]) {

  def map[U](f: T => U): Getting[W, E, S, U] = Getting { state =>
    run(state).map {
      case (warnings, errors, Some((nextState, value))) => (warnings, errors, Some((nextState, f(value))))
      case (warnings, errors, None) => (warnings, errors, None)
    }
  }

  def flatMap[U](f: T => Getting[W, E, S, U]): Getting[W, E, S, U] = Getting { state =>
    run(state).flatMap {
      case (warnings, errors, Some((nextState, value))) => f(value).run(nextState).map {
        case (w2, e2, res2) => (warnings ++ w2, errors ++ e2, res2)
      }
      case (warnings, errors, None) => LazyList((warnings, errors, None))
    }
  }

  @deprecated("some error information might be lost")
  def getOne(state: S): Either[Vector[E], (S, T)] = {
    val xs = run(state)
    // Try to collect the first non-error result
    xs.collectFirst {
      case (_, errors, Some(result)) if errors.isEmpty => Right(result)
    }.orElse(
      // Try to collect the first item regardless of errors
      xs.collectFirst {
        case (_, _, Some(result)) => Right(result)
      }
    ).getOrElse(
      // Fallback to an empty results error
      xs.collectFirst {
        case (_, errors, None) if errors.nonEmpty => Left(errors)
      }.getOrElse(Left(Vector(EmptyResultsError().asInstanceOf[E])))
    )
  }

  def getSome(state: S): (Vector[W], Vector[E], Option[(S, T)]) = {
    val xs = run(state)

    // 1. Try to find the first result with no errors and a defined state and value
    xs.collectFirst {
      case (warnings, errors, Some(result)) if errors.isEmpty => (warnings, errors, Some(result))
    }.orElse(
      // 2. If not found, try to find the first result with a defined state and value, regardless of errors
      xs.collectFirst {
        case (warnings, errors, Some(result)) => (warnings, errors, Some(result))
      }
    ).orElse(
      // 3. If not found, return the first result in xs
      xs.headOption
    ).getOrElse(
      // 4. If still not found, fallback to returning an EmptyResultsError
      (Vector.empty, Vector(EmptyResultsError().asInstanceOf[E]), None)
    )
  }

  def explainError(explain: E => E): Getting[W, E, S, T] = Getting { state =>
    run(state).map {
      case (warnings, errors, result) => (warnings, errors.map(explain), result)
    }
  }

  def ||(other: => Getting[W, E, S, T]): Getting[W, E, S, T] = Getting { state =>
    run(state) #::: other.run(state)
  }
}

@deprecated
object Getting {
  def pure[W, E, S, T](x: T): Getting[W, E, S, T] = Getting(state => LazyList((Vector.empty, Vector.empty, Some((state, x)))))

  def error[W, E, S, T](err: E): Getting[W, E, S, T] = Getting(_ => LazyList((Vector.empty, Vector(err), None)))

  def errors[W, E, S, T](errs: Vector[E]): Getting[W, E, S, T] = Getting(_ => LazyList((Vector.empty, errs, None)))

  def read[W, E, S]: Getting[W, E, S, S] = Getting(state => LazyList((Vector.empty, Vector.empty, Some((state, state)))))

  def write[W, E, S](newState: S): Getting[W, E, S, Unit] = Getting(_ => LazyList((Vector.empty, Vector.empty, Some((newState, ())))))
}

implicit def stateToGetting[W, E, T, U](state: State[T, U]): Getting[W, E, T, U] = Getting { s =>
  LazyList((Vector.empty, Vector.empty, Some(state.run(s).value)))
}
