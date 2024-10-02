package chester.propagator

import chester.syntax.concrete.ExprMeta
import chester.syntax.core.TermMeta

def convertMeta(meta: Option[ExprMeta]): Option[TermMeta] = {
  meta.map(exprMeta => TermMeta(exprMeta.sourcePos))
}
