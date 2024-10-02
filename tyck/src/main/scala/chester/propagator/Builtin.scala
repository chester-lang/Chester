package chester.propagator

import chester.syntax.Name
import chester.syntax.core.*

object BuiltIn {
  case class BuiltinItem(id: Name, value: Term, ty: Term){
  }

  val builtinItems: Seq[BuiltinItem] = Vector(
    BuiltinItem("Int", IntType, Type0),
    BuiltinItem("Integer", IntegerType, Type0),
    BuiltinItem("Float", FloatType, Type0),
    BuiltinItem("Rational", RationalType, Type0),
    BuiltinItem("String", StringType, Type0),
    BuiltinItem("Symbol", SymbolType, Type0),
    BuiltinItem("List", ListF, TyToty)
  )

}