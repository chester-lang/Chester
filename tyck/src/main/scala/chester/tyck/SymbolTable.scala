package chester.tyck

import chester.error.SourcePos
import chester.syntax.Name
import chester.syntax.core.UniqId

case class TyckSymbol(
  uniqId: UniqId,
  name: Name,
  definitionPos: SourcePos,
  references: Set[SourcePos] = Set.empty
)

type SymbolTable = Set[TyckSymbol]