package chester.syntax.core.stmt

import chester.syntax.{Id, QualifiedIDString}
import chester.syntax.concrete.FilePath
import chester.syntax.core.{Term, TermMeta}
import chester.tyck.Judge

import scala.collection.immutable.HashMap

case class TyckedSpace(modules: HashMap[QualifiedIDString, TyckedModule])

case class TyckedModule(id: QualifiedIDString, content: TyckedBlock)

case class TyckedBlock(definitions: HashMap[Id, TyckedDefinitionNamed], others: Vector[TyckedDefinition])

sealed trait TyckedDefinition {
  def meta: Option[TermMeta]
}

sealed trait TyckedDefinitionNamed extends TyckedDefinition {
  def name: Id
}

case class TyckedExpression(judge: Judge, meta: Option[TermMeta] = None) extends TyckedDefinition

case class TyckedClass(name: Id, members: Vector[TyckedDefinition], meta: Option[TermMeta] = None) extends TyckedDefinitionNamed

case class TyckedFunction(name: Id, params: Vector[Term], body: Judge, meta: Option[TermMeta] = None) extends TyckedDefinitionNamed
