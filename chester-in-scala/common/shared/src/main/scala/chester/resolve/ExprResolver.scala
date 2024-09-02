package chester.resolve

import chester.error.*
import chester.syntax.concrete.*
import chester.tyck.{LocalCtx, Reporter, VectorReporter}
import chester.utils.reuse

object ExprResolver {
  def resolve(localCtx: LocalCtx, expr: Expr, warning: Reporter[TyckWarning], reporter: Reporter[TyckError]): Expr = {
    val expr0 = try {
      val reporter1: Reporter[TyckErrorOrWarning] = {
        case e: TyckError => reporter.apply(e)
        case e: TyckWarning => warning.apply(e)
      }
      reuse(expr, SimpleDesalt.desugar(expr)(using reporter1))
    } catch {
      case e: TyckError =>
        reporter.apply(e)
        DesaltFailed(expr, e, expr.meta)
      case e: TyckWarning =>
        warning.apply(e)
        DesaltFailed(expr, e, expr.meta)
    }
    expr0
  }
  def resolveFunctional(localCtx: LocalCtx, expr: Expr): (Vector[TyckWarning], Vector[TyckError], Expr) = {
    val reporter = new VectorReporter[TyckError]
    val warning = new VectorReporter[TyckWarning]
    val expr0 = resolve(localCtx, expr, warning, reporter)
    (warning.getReports, reporter.getReports, expr0)
  }
}