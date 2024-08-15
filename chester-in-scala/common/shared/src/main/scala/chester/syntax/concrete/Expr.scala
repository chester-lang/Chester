package chester.syntax.concrete

import chester.error.{SourcePos, WithPos}
import chester.pretty.doc._
import chester.syntax.{Id, QualifiedIDString}
import chester.utils.encodeString

enum CommentType {
  case OneLine
  case MultiLine
}

case class Comment(content: String, typ: CommentType, sourcePos: Option[SourcePos])

case class CommentInfo(commentBefore: Vector[Comment], commentInBegin: Vector[Comment] = Vector.empty, commentInEnd: Vector[Comment] = Vector.empty, commentEndInThisLine: Vector[Comment] = Vector.empty) {
  if (commentBefore.isEmpty && commentInBegin.isEmpty && commentInEnd.isEmpty && commentEndInThisLine.isEmpty) {
    throw new IllegalArgumentException("At least one comment should be present")
  }
}

case class ExprMeta(sourcePos: Option[SourcePos], commentInfo: Option[CommentInfo]) {
  require(sourcePos.isDefined || commentInfo.isDefined)
}

object MetaFactory {
  def add(commentBefore: Vector[Comment] = Vector(), commentEndInThisLine: Vector[Comment] = Vector())(updateOn: Option[ExprMeta]): Option[ExprMeta] = (commentBefore, commentEndInThisLine, updateOn) match {
    case (Vector(), Vector(), _) => updateOn
    case (before, end, Some(ExprMeta(sourcePos, None))) => Some(ExprMeta(sourcePos, Some(CommentInfo(commentBefore = before, commentEndInThisLine = end))))
    case (before, end, Some(ExprMeta(sourcePos, Some(commentInfo)))) => Some(ExprMeta(sourcePos, Some(commentInfo.copy(commentBefore = before ++ commentInfo.commentBefore, commentEndInThisLine = commentInfo.commentEndInThisLine ++ end))))
  }
}

sealed trait Expr extends WithPos with ToDoc {
  def descent(operator: Expr => Expr): Expr = this

  def descentAndApply(operator: Expr => Expr): Expr = operator(this.descent(operator))

  def meta: Option[ExprMeta]

  def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Expr

  def commentAtStart(comment: Comment): Expr = updateMeta {
    case Some(meta) => Some(meta.copy(commentInfo = meta.commentInfo.map(info => info.copy(commentBefore = info.commentBefore :+ comment))))
    case None => Some(ExprMeta(None, Some(CommentInfo(Vector(comment)))))
  }

  def commentAtStart(comment: Vector[Comment]): Expr = if (comment.isEmpty) this else updateMeta {
    case Some(meta) => Some(meta.copy(commentInfo = meta.commentInfo.map(info => info.copy(commentBefore = info.commentBefore ++ comment))))
    case None => Some(ExprMeta(None, Some(CommentInfo(comment))))
  }

  def sourcePos: Option[SourcePos] = meta.flatMap(_.sourcePos)

  def commentInfo: Option[CommentInfo] = meta.flatMap(_.commentInfo)
}

sealed trait ParsedExpr extends Expr

case class Identifier(name: String, meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def toString: String = meta.flatMap(_.sourcePos) match {
    case None => s"Identifier(\"${encodeString(name)}\")"
    case Some(pos) => s"Identifier(\"${encodeString(name)}\", ${pos.toString})"
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Identifier = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(name)
}

case class ResolvedIdentifier(module: QualifiedIDString, name: Id, meta: Option[ExprMeta] = None) extends Expr {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): ResolvedIdentifier = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(module.toString) <> Doc.text(".") <> Doc.text(name.toString)
}

// infix prefix postfix
case class OpSeq(seq: Vector[Expr], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    OpSeq(seq.map(_.descentAndApply(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): OpSeq = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = seq.map(_.toDoc).reduce(_ </> _)
}

case class Infix(op: Expr, left: Expr, right: Expr, meta: Option[ExprMeta] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Infix(op.descentAndApply(operator), left.descentAndApply(operator), right.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Infix = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = left.toDoc <> Doc.text(" ") <> op.toDoc <> Doc.text(" ") <> right.toDoc
}

case class Prefix(op: Expr, operand: Expr, meta: Option[ExprMeta] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Prefix(op.descentAndApply(operator), operand.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Prefix = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = op.toDoc <> Doc.text(" ") <> operand.toDoc
}

case class Postfix(op: Expr, operand: Expr, meta: Option[ExprMeta] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Postfix(op.descentAndApply(operator), operand.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Postfix = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = operand.toDoc <> Doc.text(" ") <> op.toDoc
}

case class Block(heads: Vector[Expr], tail: Option[Expr], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    Block(heads.map(_.descentAndApply(operator)), tail.map(_.descentAndApply(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Block = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = {
    val headDocs = heads.map(_.toDoc).reduceOption(_ <> Doc.text(";") </> _).getOrElse(Doc.empty)
    val tailDoc = tail.map(_.toDoc).getOrElse(Doc.empty)
    Doc.text("{") </> Doc.indented(Doc.concat(headDocs, tailDoc)) </> Doc.text("}")
  }
}

object Block {
  def apply(heads: Vector[Expr], tail: Expr): Block = Block(heads, Some(tail), None)

  def apply(heads: Vector[Expr], tail: Expr, meta: Option[ExprMeta]): Block = Block(heads, Some(tail), meta)
}

// maybe argument in function call or in function declaration
case class Arg(decorations: Vector[Identifier] = Vector(), name: Option[Identifier], ty: Option[Expr], exprOrDefault: Option[Expr], vararg: Boolean = false) {
  require(name.isDefined || exprOrDefault.isDefined)

  def descentAndApply(operator: Expr => Expr): Arg = {
    Arg(decorations, name, ty.map(_.descentAndApply(operator)), exprOrDefault.map(_.descentAndApply(operator)))
  }

  override def toString: String = this match {
    case Arg(Vector(), None, None, Some(expr), false) => s"Arg.of($expr)"
    case Arg(decorations, name, ty, exorOrDefault, false) => s"Arg($decorations,$name,$ty,$exorOrDefault)"
    case Arg(decorations, name, ty, exorOrDefault, vararg) => s"Arg($decorations,$name,$ty,$exorOrDefault,$vararg)"
  }

  def toDoc(implicit options: PrettierOptions): Doc = {
    val decDoc = if (decorations.nonEmpty) decorations.map(_.toDoc).reduce(_ </> _) <> Doc.text(" ") else Doc.empty
    val nameDoc = name.map(_.toDoc).getOrElse(Doc.empty)
    val tyDoc = ty.map(t => Doc.text(": ") <> t.toDoc).getOrElse(Doc.empty)
    val exprDoc = exprOrDefault.map(e => Doc.text(" = ") <> e.toDoc).getOrElse(Doc.empty)
    val varargDoc = if (vararg) Doc.text("...") else Doc.empty
    decDoc <> nameDoc <> tyDoc <> exprDoc <> varargDoc
  }
}

object Arg {
  def of(expr: Expr): Arg = Arg(Vector.empty, None, None, Some(expr))
}

sealed trait MaybeTelescope extends Expr {
  override def descent(operator: Expr => Expr): MaybeTelescope = super.descent(operator).asInstanceOf[MaybeTelescope]
}

sealed trait ParsedMaybeTelescope extends MaybeTelescope with ParsedExpr

case class Tuple(terms: Vector[Expr], meta: Option[ExprMeta] = None) extends ParsedMaybeTelescope {
  override def descent(operator: Expr => Expr): Tuple = {
    Tuple(terms.map(_.descentAndApply(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Tuple = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("(", ")", ", ")(terms: _*)
}

case class Telescope(args: Vector[Arg], implicitly: Boolean = false, meta: Option[ExprMeta] = None) extends MaybeTelescope with DesaltExpr {
  override def descent(operator: Expr => Expr): Telescope = {
    Telescope(args.map(_.descentAndApply(operator)), implicitly, meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Telescope = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("(", ")", ", ")(args.map(_.toDoc)*)
}

object Telescope {
  def of(args: Arg*)(implicit meta: Option[ExprMeta] = None): Telescope = Telescope(args.toVector, meta = meta)
}

case class FunctionCall(function: Expr, telescope: MaybeTelescope, meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    FunctionCall(function.descentAndApply(operator), telescope.descent(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): FunctionCall = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = function.toDoc <> Doc.text(" ") <> telescope.toDoc
}

case class DotCall(expr: Expr, field: Expr, telescope: Vector[MaybeTelescope], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    DotCall(expr.descentAndApply(operator), field.descentAndApply(operator), telescope.map(_.descent(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): DotCall = copy(meta = updater(meta))

  def isField: Boolean = telescope.isEmpty

  def isQualifiedName: Boolean = {
    if (telescope.nonEmpty) return false
    if (!field.isInstanceOf[Identifier]) return false
    expr.match {
      case Identifier(_, _) => true
      case DotCall(_, _, _, _) => expr.asInstanceOf[DotCall].isQualifiedName
      case _ => false
    }
  }

  override def toDoc(implicit options: PrettierOptions): Doc = expr.toDoc <> Doc.text(".") <> field.toDoc <> telescope.map(_.toDoc).reduceOption(_ <> _).getOrElse(Doc.empty)
}

type QualifiedName = DotCall | Identifier

object QualifiedName {
  def build(x: QualifiedName, field: Identifier, meta: Option[ExprMeta] = None): QualifiedName = DotCall(x, field, Vector(), meta)
}

sealed trait NumberLiteral extends ParsedExpr

case class IntegerLiteral(value: BigInt, meta: Option[ExprMeta] = None) extends NumberLiteral {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Expr = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString)
}

case class DoubleLiteral(value: BigDecimal, meta: Option[ExprMeta] = None) extends NumberLiteral {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Expr = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(value.toString)
}

case class StringLiteral(value: String, meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def toString: String = meta.flatMap(_.sourcePos) match {
    case None => s"StringLiteral(\"${encodeString(value)}\")"
    case Some(pos) => s"StringLiteral(\"${encodeString(value)}\", ${pos.toString})"
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Expr = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("\"" + encodeString(value) + "\"")
}

case class SymbolLiteral(value: String, meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Expr = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text(":" + value)
}

case class ListExpr(terms: Vector[Expr], meta: Option[ExprMeta] = None) extends ParsedMaybeTelescope {
  override def descent(operator: Expr => Expr): ListExpr = {
    ListExpr(terms.map(_.descentAndApply(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): ListExpr = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("[", "]", ", ")(terms: _*)
}

case class HoleExpr(description: String, meta: Option[ExprMeta] = None) extends Expr {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): HoleExpr = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("?") <> Doc.text(description)
}

case class TypeAnnotation(expr: Expr, ty: Expr, meta: Option[ExprMeta] = None) extends DesaltExpr {
  override def descent(operator: Expr => Expr): Expr = {
    TypeAnnotation(expr.descentAndApply(operator), ty.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): TypeAnnotation = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = expr.toDoc <> Doc.text(": ") <> ty.toDoc
}

case class AnnotatedExpr(annotation: Identifier, telescope: Vector[MaybeTelescope], expr: Expr, meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    AnnotatedExpr(annotation, telescope.map(_.descent(operator)), expr.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): AnnotatedExpr = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = annotation.toDoc <> telescope.map(_.toDoc).reduceOption(_ <> _).getOrElse(Doc.empty) <> expr.toDoc
}

sealed trait ObjectClause {
  def descent(operator: Expr => Expr): ObjectClause = this match {
    case ObjectExprClause(k, v) => ObjectExprClause(k, v.descentAndApply(operator))
    case ObjectExprClauseOnValue(k, v) => ObjectExprClauseOnValue(k.descentAndApply(operator), v.descentAndApply(operator))
  }
}

case class ObjectExprClause(key: QualifiedName, value: Expr) extends ObjectClause {
}

case class ObjectExprClauseOnValue(key: Expr, value: Expr) extends ObjectClause {
}

object ObjectExprClause {
  def apply(key: QualifiedName, value: Expr): ObjectExprClause = new ObjectExprClause(key, value)

  def apply(pair: (QualifiedName, Expr)): ObjectExprClause = new ObjectExprClause(pair._1, pair._2)
}

implicit def toObjectExprClause(pair: (QualifiedName, Expr)): ObjectExprClause = ObjectExprClause(pair._1, pair._2)

case class ObjectExpr(clauses: Vector[ObjectClause], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    ObjectExpr(clauses.map(_.descent(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): ObjectExpr = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("{", "}", ", ") (
    clauses.map {
      case ObjectExprClause(key, value) => key.toDoc <> Doc.text("=") <> value.toDoc
      case ObjectExprClauseOnValue(key, value) => key.toDoc <> Doc.text("=>") <> value.toDoc
    }*
  )
}

case class Keyword(key: Id, telescope: Vector[MaybeTelescope], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    Keyword(key, telescope.map(_.descent(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Keyword = copy(meta = updater(meta))

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("#" + key) <> telescope.map(_.toDoc).reduceOption(_ <> _).getOrElse(Doc.empty)
}

sealed trait DesaltExpr extends Expr

case class DesaltCaseClause(pattern: Expr, returning: Expr, meta: Option[ExprMeta] = None) extends DesaltExpr {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): DesaltCaseClause = copy(meta = updater(meta))

  override def descent(operator: Expr => Expr): DesaltCaseClause = DesaltCaseClause(pattern.descentAndApply(operator), returning.descentAndApply(operator), meta)

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.text("case ") <> pattern.toDoc <> Doc.text(" => ") <> returning.toDoc
}

case class DesaltMatching(clauses: Vector[DesaltCaseClause], meta: Option[ExprMeta] = None) extends DesaltExpr {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): DesaltMatching = copy(meta = updater(meta))

  override def descent(operator: Expr => Expr): Expr = DesaltMatching(clauses.map(_.descent(operator)), meta)

  override def toDoc(implicit options: PrettierOptions): Doc = Doc.wrapperlist("{", "}", ";") (
    clauses.map(_.toDoc)*
  )
}

case class FunctionExpr(telescope: Vector[MaybeTelescope], effect: Option[Expr], result: Option[Expr], body: Expr, meta: Option[ExprMeta] = None) extends DesaltExpr {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): FunctionExpr = copy(meta = updater(meta))

  override def descent(operator: Expr => Expr): Expr = FunctionExpr(telescope.map(_.descent(operator)), effect.map(_.descentAndApply(operator)), result.map(_.descentAndApply(operator)), body.descentAndApply(operator), meta)

  override def toDoc(implicit options: PrettierOptions): Doc = {
    val telescopeDoc = telescope.map(_.toDoc).reduceOption(_ <+> _).getOrElse(Doc.empty)
    val effectDoc = effect.map(e => Doc.text(" ") <> e.toDoc).getOrElse(Doc.empty)
    val resultDoc = result.map(r => Doc.text(" -> ") <> r.toDoc).getOrElse(Doc.empty)
    val bodyDoc = body.toDoc
    telescopeDoc <> effectDoc <> resultDoc <> Doc.text(" {") </> Doc.indented(bodyDoc) </> Doc.text("}")
  }
}
