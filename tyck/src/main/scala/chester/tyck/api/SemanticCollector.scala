package chester.tyck.api

import chester.syntax.*
import chester.syntax.concrete.*
import chester.syntax.core.*
import upickle.default.*

import scala.collection.mutable

trait SymbolCollector {
  def referencedOn(expr: Expr): Unit
}

trait SemanticCollector {
  def newSymbol(call: MaybeVarCall,
                 id: UniqIdOf[? <: MaybeVarCall],
                 definedOn: Expr) : SymbolCollector
}

private implicit inline def rwUniqIDOfVar[T]: ReadWriter[UniqIdOf[? <: MaybeVarCall]] = rwUniqIDOf.asInstanceOf[ReadWriter[UniqIdOf[? <: MaybeVarCall]]]

// TODO: handle when call's ty is MetaTerm
case class CollectedSymbol(call: MaybeVarCall,
                           id: UniqIdOf[? <: MaybeVarCall],
                           definedOn: Expr,
                           referencedOn: Vector[Expr]) derives ReadWriter {
  def name: Name = call.name
}

class VectorSemanticCollector extends SemanticCollector {
  private var builder: mutable.ArrayDeque[CollectedSymbol] = new mutable.ArrayDeque[CollectedSymbol]()
  override def newSymbol(call: MaybeVarCall,
                          id: UniqIdOf[? <: MaybeVarCall],
                          definedOn: Expr): SymbolCollector = {
    val index = builder.length
    builder.append(CollectedSymbol(call, id, definedOn, Vector()))
    assert(builder.length == index + 1)
    new SymbolCollector {
      override def referencedOn(expr: Expr): Unit = {
        val symbol = builder(index)
        builder(index) = symbol.copy(referencedOn = symbol.referencedOn :+ expr)
      }
    }
  }
  def get: Vector[CollectedSymbol] = builder.toVector
}

object NoopSemanticCollector extends SemanticCollector {
  override def newSymbol(call: MaybeVarCall,
                         id: UniqIdOf[? <: MaybeVarCall],
                         definedOn: Expr): SymbolCollector = new SymbolCollector {
    override def referencedOn(expr: Expr): Unit = ()
  }
}

class UnusedVariableWarningWrapper(x: SemanticCollector) extends SemanticCollector {
  private var unusedVariables: Vector[CollectedSymbol] = Vector()
  override def newSymbol(call: MaybeVarCall,
                          id: UniqIdOf[? <: MaybeVarCall],
                          definedOn: Expr): SymbolCollector = {
    val symbolCollector = x.newSymbol(call, id, definedOn)
    val c = CollectedSymbol(call, id, definedOn, Vector())
    unusedVariables = unusedVariables :+ c
    new SymbolCollector {
      override def referencedOn(expr: Expr): Unit = {
        symbolCollector.referencedOn(expr)
        unusedVariables = unusedVariables.filterNot(_ == c)
      }
    }
  }
  def foreachUnused(f: CollectedSymbol => Unit): Unit = unusedVariables.foreach(f)
}