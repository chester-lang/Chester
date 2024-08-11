package chester.syntax.core.stmt

import chester.syntax.QualifiedIDString
import chester.syntax.concrete.FilePath
import chester.syntax.core.{Term, TermMeta}
import chester.tyck.Judge

case class TyckedModule(id: QualifiedIDString, files: Vector[TyckedModuleFile])

case class TyckedModuleFile(fileName: FilePath, content: TyckedBlock)

case class TyckedBlock(definitions: Vector[TyckedDefinition])

sealed trait TyckedDefinition {
  def meta: Option[TermMeta]
}

case class TyckedExpression(judge: Judge, meta: Option[TermMeta] = None) extends TyckedDefinition

case class TyckedClass(name: String, members: Vector[TyckedDefinition], meta: Option[TermMeta] = None) extends TyckedDefinition

case class TyckedFunction(name: String, params: Vector[Term], body: Judge, meta: Option[TermMeta] = None) extends TyckedDefinition
