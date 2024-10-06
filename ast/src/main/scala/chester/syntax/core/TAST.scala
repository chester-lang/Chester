package chester.syntax.core

import chester.syntax.*
import upickle.default.*

// Typed Abstract Syntax Trees
// files
case class TAST(fileName: String, module: ModuleRef, ast: BlockTerm, ty: Term, effects: Effects) derives ReadWriter
