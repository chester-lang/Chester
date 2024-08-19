package chester.syntax.core.stmt

import chester.syntax.{Id, QualifiedIDString}
import chester.syntax.concrete.FilePath
import chester.syntax.core._
import chester.tyck._

import scala.collection.immutable.HashMap

case class TyckedSpace(modules: HashMap[QualifiedIDString, TyckedModule])

case class TyckedModule(id: QualifiedIDString, definitions: HashMap[Id, TyckedDefinitionNamed], lastExpr: Option[Judge])

sealed trait TyckedDefinition {
  def meta: Option[TermMeta]
  def ctx: LocalCtx = ???
}

sealed trait TyckedDefinitionNamed extends TyckedDefinition {
  def name: Id
}

case class TyckedExpression(judge: Judge, meta: Option[TermMeta] = None) extends TyckedDefinition

case class TyckedClass(name: Id, members: Vector[TyckedDefinition], meta: Option[TermMeta] = None) extends TyckedDefinitionNamed

case class TyckedFunction(name: Id, params: Vector[Term], body: Judge, meta: Option[TermMeta] = None) extends TyckedDefinitionNamed
