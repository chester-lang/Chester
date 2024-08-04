package chester.syntax.concrete

import chester.error.{SourcePos, WithPos}
import chester.syntax.IdentifierRules.strIsOperator
import chester.utils.encodeString

enum CommentType {
  case OneLine
  case MultiLine
}

case class Comment(content: String, typ: CommentType, sourcePos: Option[SourcePos])

case class CommentInfo(commentBefore: Vector[Comment], commentInBegin: Vector[Comment] = Vector.empty, commentInEnd: Vector[Comment] = Vector.empty) {
  if (commentBefore.isEmpty && commentInBegin.isEmpty && commentInEnd.isEmpty) {
    throw new IllegalArgumentException("At least one comment should be present")
  }
}

case class ExprMeta(sourcePos: Option[SourcePos], commentInfo: Option[CommentInfo]) {
  assert(sourcePos.isDefined || commentInfo.isDefined)
}

sealed trait Expr extends WithPos {
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
}

// infix prefix postfix
case class OpSeq(seq: Vector[Expr], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    OpSeq(seq.map(_.descentAndApply(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): OpSeq = copy(meta = updater(meta))
}

case class Infix(op: Expr, left: Expr, right: Expr, meta: Option[ExprMeta] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Infix(op.descentAndApply(operator), left.descentAndApply(operator), right.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Infix = copy(meta = updater(meta))
}

case class Prefix(op: Expr, operand: Expr, meta: Option[ExprMeta] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Prefix(op.descentAndApply(operator), operand.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Prefix = copy(meta = updater(meta))
}

case class Postfix(op: Expr, operand: Expr, meta: Option[ExprMeta] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    Postfix(op.descentAndApply(operator), operand.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Postfix = copy(meta = updater(meta))
}

case class Block(heads: Vector[Expr], tail: Option[Expr], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    Block(heads.map(_.descentAndApply(operator)), tail.map(_.descentAndApply(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Block = copy(meta = updater(meta))
}

object Block {
  def apply(heads: Vector[Expr], tail: Expr): Block = Block(heads, Some(tail), None)

  def apply(heads: Vector[Expr], tail: Expr, meta: Option[ExprMeta]): Block = Block(heads, Some(tail), meta)
}

case class MacroCall(macroName: Expr, args: Vector[Expr], meta: Option[ExprMeta] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    MacroCall(macroName.descentAndApply(operator), args.map(_.descentAndApply(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): MacroCall = copy(meta = updater(meta))
}

// maybe argument in function call or in function declaration
case class Arg(decorations: Vector[Identifier] = Vector(), name: Option[Identifier], ty: Option[Expr], exprOrDefault: Option[Expr], vararg: Boolean = false) {
  assert(name.isDefined || exprOrDefault.isDefined)

  def descentAndApply(operator: Expr => Expr): Arg = {
    Arg(decorations, name, ty.map(_.descentAndApply(operator)), exprOrDefault.map(_.descentAndApply(operator)))
  }

  override def toString: String = this match {
    case Arg(Vector(), None, None, Some(expr), false) => s"Arg.of($expr)"
    case Arg(decorations, name, ty, exorOrDefault, false) => s"Arg($decorations,$name,$ty,$exorOrDefault)"
    case Arg(decorations, name, ty, exorOrDefault, vararg) => s"Arg($decorations,$name,$ty,$exorOrDefault,$vararg)"
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
}

case class Telescope(args: Vector[Arg], implicitly: Boolean = false, meta: Option[ExprMeta] = None) extends MaybeTelescope {
  override def descent(operator: Expr => Expr): Telescope = {
    Telescope(args.map(_.descentAndApply(operator)), implicitly, meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Telescope = copy(meta = updater(meta))
}

object Telescope {
  def of(args: Arg*)(implicit meta: Option[ExprMeta] = None): Telescope = Telescope(args.toVector, meta = meta)
}

case class FunctionCall(function: Expr, telescope: MaybeTelescope, meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    FunctionCall(function.descentAndApply(operator), telescope.descent(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): FunctionCall = copy(meta = updater(meta))
}

case class DotCall(expr: Expr, field: Expr, telescope: Vector[MaybeTelescope], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    DotCall(expr.descentAndApply(operator), expr.descentAndApply(operator), telescope.map(_.descent(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): DotCall = copy(meta = updater(meta))

  def isField: Boolean = telescope.isEmpty

  def isQualifiedName: Boolean = {
    if (telescope.nonEmpty) return false
    if (!field.isInstanceOf[Identifier]) return false
    expr match {
      case Identifier(_, _) => true
      case DotCall(_, _, _, _) => expr.asInstanceOf[DotCall].isQualifiedName
      case _ => false
    }
  }
}

type QualifiedName = DotCall | Identifier

object QualifiedName {
  def build(x: QualifiedName, field: Identifier, meta: Option[ExprMeta] = None): QualifiedName = DotCall(x, field, Vector(), meta)
}

sealed trait NumberLiteral extends ParsedExpr

case class IntegerLiteral(value: BigInt, meta: Option[ExprMeta] = None) extends NumberLiteral {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Expr = copy(meta = updater(meta))
}

case class DoubleLiteral(value: BigDecimal, meta: Option[ExprMeta] = None) extends NumberLiteral {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Expr = copy(meta = updater(meta))
}

case class StringLiteral(value: String, meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def toString: String = meta.flatMap(_.sourcePos) match {
    case None => s"StringLiteral(\"${encodeString(value)}\")"
    case Some(pos) => s"StringLiteral(\"${encodeString(value)}\", ${pos.toString})"
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Expr = copy(meta = updater(meta))
}

case class ListExpr(terms: Vector[Expr], meta: Option[ExprMeta] = None) extends ParsedMaybeTelescope {
  override def descent(operator: Expr => Expr): ListExpr = {
    ListExpr(terms.map(_.descentAndApply(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): ListExpr = copy(meta = updater(meta))
}

case class HoleExpr(description: String, meta: Option[ExprMeta] = None) extends Expr {
  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): HoleExpr = copy(meta = updater(meta))
}

case class TypeAnnotation(expr: Expr, ty: Expr, meta: Option[ExprMeta] = None) extends Expr {
  override def descent(operator: Expr => Expr): Expr = {
    TypeAnnotation(expr.descentAndApply(operator), ty.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): TypeAnnotation = copy(meta = updater(meta))
}

case class AnnotatedExpr(annotation: Identifier, telescope: Vector[MaybeTelescope], expr: Expr, meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    AnnotatedExpr(annotation, telescope.map(_.descent(operator)), expr.descentAndApply(operator), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): AnnotatedExpr = copy(meta = updater(meta))
}


case class ObjectExpr(clauses: Vector[(QualifiedName, Expr)], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    ObjectExpr(clauses.map { case (k, v) => (k, v.descentAndApply(operator)) }, meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): ObjectExpr = copy(meta = updater(meta))
}

case class Keyword(key: Identifier, telescope: Vector[MaybeTelescope], meta: Option[ExprMeta] = None) extends ParsedExpr {
  override def descent(operator: Expr => Expr): Expr = {
    Keyword(key, telescope.map(_.descent(operator)), meta)
  }

  override def updateMeta(updater: Option[ExprMeta] => Option[ExprMeta]): Keyword = copy(meta = updater(meta))
}
