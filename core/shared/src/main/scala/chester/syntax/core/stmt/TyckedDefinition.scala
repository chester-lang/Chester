package chester.syntax.core.stmt

import chester.syntax.{Id, QualifiedIDString}
import chester.syntax.core._
import chester.tyck._

import scala.collection.immutable.HashMap

case class TyckedSpace(modules: HashMap[QualifiedIDString, TyckedModule]) extends AnyVal

case class TyckedModule(id: QualifiedIDString, definitions: HashMap[Id, TyckedDefinitionNamed], lastExpr: Option[Judge])

sealed trait TyckedDefinition {
  def meta: Option[TermMeta]
  def ctx: LocalCtx = ???
}

sealed trait TyckedDefinitionNamed extends TyckedDefinition {
  def name: Id
  def varId: UniqId
}

case class TyckedExpression(judge: Judge, meta: Option[TermMeta] = None) extends TyckedDefinition

case class RecordMember()

case class TyckedRecord(name: Id, varId: UniqId, members: Vector[RecordMember], meta: Option[TermMeta] = None) extends TyckedDefinitionNamed

case class TyckedDef(name: Id, varId: UniqId, body: Judge, meta: Option[TermMeta] = None) extends TyckedDefinitionNamed
