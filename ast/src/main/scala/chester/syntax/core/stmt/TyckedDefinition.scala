package chester.syntax.core.stmt

import chester.syntax.{Name, QualifiedIDString}
import chester.syntax.core._

import scala.collection.immutable.HashMap

case class TyckedSpace(modules: HashMap[QualifiedIDString, TyckedModule]) extends AnyVal

case class TyckedModule(id: QualifiedIDString, definitions: HashMap[Name, TyckedDefinitionNamed], lastExpr: Option[Judge])

sealed trait TyckedDefinition {
  def meta: Option[TermMeta]
  //def ctx: LocalCtx = ???
}

sealed trait TyckedDefinitionNamed extends TyckedDefinition {
  def name: Name
  def varId: UniqId
}

case class TyckedExpression(judge: Judge, meta: Option[TermMeta] = None) extends TyckedDefinition

case class RecordMember()

case class TyckedRecord(name: Name, varId: UniqId, members: Vector[RecordMember], meta: Option[TermMeta] = None) extends TyckedDefinitionNamed

case class TyckedDef(name: Name, varId: UniqId, body: Judge, meta: Option[TermMeta] = None) extends TyckedDefinitionNamed
