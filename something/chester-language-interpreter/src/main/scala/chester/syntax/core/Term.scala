package chester.syntax.core

import chester.error.{SourcePos, WithPos}

sealed trait Term extends WithPos {

}


sealed trait Sort extends Term {

}

case class Type(level: BigInt, sourcePos: Option[SourcePos] = None) extends Sort

val Type0 = Type(0)