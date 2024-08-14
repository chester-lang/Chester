package chester.resolve

import chester.syntax.Const
import chester.syntax.concrete.*
import chester.syntax.concrete.stmt.*
import chester.tyck.{TyckError, TyckWarning, UnsupportedExpressionError}

import scala.annotation.tailrec

object BasicStatementResolver {
  def resolveStatements(exprs: Vector[Expr]): (Vector[TyckWarning], Vector[TyckError], Vector[Statement]) = {
    var warnings = Vector.empty[TyckWarning]
    var errors = Vector.empty[TyckError]
    var statements = Vector.empty[Statement]

    def processExpr(expr: Expr): Unit = {
      val (ws, es, stmtOpt) = resolveStatement(expr)
      warnings ++= ws
      errors ++= es
      statements :+= stmtOpt
    }

    exprs.foreach(processExpr)

    val groupedStatements = groupDeclarationsAndDefinitions(statements)

    (warnings, errors, groupedStatements)
  }

  def resolveStatement(expr: Expr): (Vector[TyckWarning], Vector[TyckError], Statement) = expr match {
    case opSeq: OpSeq =>
      opSeq.seq match {
        case Vector(Identifier(Const.Data, _), xs@_*) =>
          return (Vector.empty, Vector.empty, DataStatement(xs.toVector, opSeq.meta))

        case Vector(Identifier(Const.Trait, _), xs@_*) =>
          return (Vector.empty, Vector.empty, TraitStatement(xs.toVector, opSeq.meta))

        case Vector(Identifier(Const.Implement, _), xs@_*) =>
          return (Vector.empty, Vector.empty, ImplementStatement(xs.toVector, opSeq.meta))

        case _ =>
      }

      if (opSeq.seq.exists {
        case Identifier(":", _) => true
        case Identifier("=", _) => true
        case _ => false
      }) {
        resolveTypeOrDefinition(opSeq)
      } else {
        (Vector.empty, Vector.empty, ExprStatement(opSeq))
      }

    case _ => (Vector.empty, Vector.empty, ExprStatement(expr))
  }

  private def resolveTypeOrDefinition(opSeq: OpSeq): (Vector[TyckWarning], Vector[TyckError], Statement) = {
    val colonIndex = opSeq.seq.indexWhere {
      case Identifier(":", _) => true
      case _ => false
    }
    val equalIndex = opSeq.seq.indexWhere {
      case Identifier("=", _) => true
      case _ => false
    }

    val nameOpt = extractName(opSeq.seq.head)

    (nameOpt, colonIndex, equalIndex) match {
      case (Some(name), cIdx, eIdx) if cIdx != -1 && eIdx != -1 =>
        val beforeColon = opSeq.seq.take(cIdx).toVector
        val afterColon = opSeq.seq.slice(cIdx + 1, eIdx).toVector
        val afterEqual = opSeq.seq.drop(eIdx + 1).toVector
        (Vector.empty, Vector.empty, DeclarationAndDefinition(Some(name), beforeColon, afterColon, afterEqual, opSeq.meta))

      case (Some(name), cIdx, _) if cIdx != -1 =>
        val beforeColon = opSeq.seq.take(cIdx).toVector
        val afterColon = opSeq.seq.drop(cIdx + 1).toVector
        (Vector.empty, Vector.empty, TypeDeclaration(Some(name), beforeColon, afterColon, opSeq.meta))

      case (Some(name), _, eIdx) if eIdx != -1 =>
        val beforeEqual = opSeq.seq.take(eIdx).toVector
        val afterEqual = opSeq.seq.drop(eIdx + 1).toVector
        (Vector.empty, Vector.empty, Definition(Some(name), beforeEqual, afterEqual, opSeq.meta))

      case (None, cIdx, eIdx) if cIdx != -1 && eIdx != -1 =>
        val beforeColon = opSeq.seq.take(cIdx).toVector
        val afterColon = opSeq.seq.slice(cIdx + 1, eIdx).toVector
        val afterEqual = opSeq.seq.drop(eIdx + 1).toVector
        (Vector.empty, Vector.empty, DeclarationAndDefinition(None, beforeColon, afterColon, afterEqual, opSeq.meta))

      case (None, cIdx, _) if cIdx != -1 =>
        val beforeColon = opSeq.seq.take(cIdx).toVector
        val afterColon = opSeq.seq.drop(cIdx + 1).toVector
        (Vector.empty, Vector.empty, TypeDeclaration(None, beforeColon, afterColon, opSeq.meta))

      case (None, _, eIdx) if eIdx != -1 =>
        val beforeEqual = opSeq.seq.take(eIdx).toVector
        val afterEqual = opSeq.seq.drop(eIdx + 1).toVector
        (Vector.empty, Vector.empty, Definition(None, beforeEqual, afterEqual, opSeq.meta))

      case _ =>
        (Vector.empty, Vector(UnsupportedExpressionError(opSeq)), ErrorStatement(None, "Unsupported expression"))
    }
  }

  @tailrec
  private def extractName(expr: Expr): Option[String] = expr match {
    case Identifier(name, _) => Some(name)
    case FunctionCall(function, _, _) => extractName(function)
    case _ => None
  }

  private def groupDeclarationsAndDefinitions(statements: Vector[Statement]): Vector[Statement] = {
    val grouped = statements.foldLeft(Vector.empty[Statement]) {
      case (acc, stmt: TypeDeclaration) =>
        acc :+ stmt

      case (acc, stmt: Definition) =>
        val (decls, rest) = acc.partition {
          case TypeDeclaration(Some(name), _, _, _) if stmt.name.contains(name) => true
          case _ => false
        }
        if (decls.nonEmpty) {
          val TypeDeclaration(name, declNameExprs, types, meta) = decls.head
          rest :+ DeclarationAndDefinition(name, declNameExprs, types, stmt.exprs, meta)
        } else {
          acc :+ stmt
        }

      case (acc, stmt) => acc :+ stmt
    }
    grouped
  }
}
