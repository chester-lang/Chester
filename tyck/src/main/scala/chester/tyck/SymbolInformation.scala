package chester.tyck

import chester.error.SourcePos
import chester.syntax.Name
import chester.syntax.core.UniqId

case class TyckSymbol(
  uniqId: UniqId,
  name: Name,
  definitionPos: SourcePos,
  scopePath: List[UniqId],
  references: Set[SourcePos] = Set.empty
)

type SymbolTable = Set[TyckSymbol]