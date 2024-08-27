package chester.resolve

import chester.error.TyckError
import chester.syntax.concrete.*
import chester.tyck.{LocalCtx, Reporter, VectorReporter}
import chester.utils.reuse

object ExprResolver {
  def resolve(localCtx: LocalCtx, expr: Expr, reporter: Reporter[TyckError]): Expr = {
    val expr0 = try {
      reuse(expr, SimpleDesalt.desugar(expr))
    } catch {
      case e: TyckError =>
        reporter.report(e)
        DesaltFailed(expr, e, expr.meta)
    }
    expr0
  }
  def resolveFunctional(localCtx: LocalCtx, expr: Expr): (Vector[TyckError], Expr) = {
    val reporter = new VectorReporter[TyckError]
    val expr0 = resolve(localCtx, expr, reporter)
    (reporter.getReports, expr0)
  }
}