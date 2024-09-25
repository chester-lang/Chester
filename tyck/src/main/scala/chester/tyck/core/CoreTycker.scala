package chester.tyck.core

import chester.error.{TyckError, TyckProblem, UnsupportedTermError}
import chester.syntax.core.*
import chester.tyck.{LocalCtx, Reporter}
import chester.utils.*

case class CoreTycker(localCtx: LocalCtx = LocalCtx.Empty, reporter: Reporter[TyckError]) {
  def check(judge: Judge): Unit = ???

  def inferNoEffect(term: Term): Term = term match {
    case ty: WithType => ty.ty
    case ListType(t)=>inferNoEffect(t)
    case IntegerTerm(_) => IntegerType
    case IntTerm(_) => IntType
    case StringTerm(_) => StringType
    case RationalTerm(_) => RationalType
    case SymbolTerm(_) => SymbolType
    case TupleTerm(terms) =>
      val types = terms.map(inferNoEffect)
      TupleType(types)
    case ListTerm(terms) =>
      val elementType = inferCommonType(terms)
      ListType(elementType)
    case ObjectTerm(clauses) =>
      val fieldTypes = clauses.map {
        case ObjectClauseValueTerm(key, value) =>
          val keyType = inferNoEffect(key)
          val valueType = inferNoEffect(value)
          ObjectClauseValueTerm(key, valueType)
      }
      ObjectType(fieldTypes)
    case LocalVar(_, ty, _, _) =>
      ty
    case FunctionType(telescope, resultTy, _, _, _) => Typeω // TODO
    case _ =>
      reporter.apply(UnsupportedTermError(term))
      ErrorTerm(UnsupportedTermError(term))
  }

  // TODO: clarify the implementation
  def inferCommonType(terms: Seq[Term]): Term = {
    val types = terms.map(inferNoEffect)
    if (types.isEmpty) {
      // If the list is empty, we can default to AnyType or NothingType
      AnyType(Level0)
    } else {
      common(types)
    }
  }

  // TODO: clarify the implementation
  def common(types: Seq[Term]): Term = {
    // Remove duplicates for efficiency
    val uniqueTypes = types.distinct

    uniqueTypes match {
      case Seq(singleType) =>
        // Only one type, return it
        singleType
      case _ =>
        // Check if all types are the same
        if (uniqueTypes.tail.forall(_ == uniqueTypes.head)) {
          uniqueTypes.head
        } else {
          // Create a Union of all types
          Union(uniqueTypes.toVector.assumeNonEmpty)
        }
    }
  }
}
