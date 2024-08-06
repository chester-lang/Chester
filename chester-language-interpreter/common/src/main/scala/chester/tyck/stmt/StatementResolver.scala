package chester.tyck.stmt

import chester.syntax.concrete._
import chester.syntax.concrete.stmt._
import chester.tyck.{TyckError, TyckWarning, UnsupportedExpressionError}

object StatementResolver {
  def resolveStatements(exprs: Vector[Expr]): (Vector[TyckWarning], Vector[TyckError], Vector[Statement]) = {
    var warnings = Vector.empty[TyckWarning]
    var errors = Vector.empty[TyckError]
    var statements = Vector.empty[Statement]

    def processExpr(expr: Expr): Unit = {
      val (ws, es, stmtOpt) = resolveStatement(expr)
      warnings ++= ws
      errors ++= es
      stmtOpt.foreach(statements :+= _)
    }

    exprs.foreach(processExpr)

    val groupedStatements = groupDeclarationsAndDefinitions(statements)

    (warnings, errors, groupedStatements)
  }

  private def resolveStatement(expr: Expr): (Vector[TyckWarning], Vector[TyckError], Option[Statement]) = expr match {
    case opSeq: OpSeq =>
      opSeq.seq match {
        case Vector(Identifier("data", _), xs @ _*) =>
          return (Vector.empty, Vector.empty, Some(DataStatement(xs.toVector, opSeq.meta)))

        case Vector(Identifier("trait", _), xs @ _*) =>
          return (Vector.empty, Vector.empty, Some(TraitStatement(xs.toVector, opSeq.meta)))

        case Vector(Identifier("implement", _), xs @ _*) =>
          return (Vector.empty, Vector.empty, Some(ImplementStatement(xs.toVector, opSeq.meta)))

        case _ =>
      }

      if (opSeq.seq.exists {
        case Identifier(":", _) => true
        case Identifier("=", _) => true
        case _ => false
      }) {
        resolveTypeOrDefinition(opSeq)
      } else {
        (Vector.empty, Vector.empty, Some(ExprStatement(opSeq)))
      }

    case _ => (Vector.empty, Vector.empty, Some(ExprStatement(expr)))
  }

  private def resolveTypeOrDefinition(opSeq: OpSeq): (Vector[TyckWarning], Vector[TyckError], Option[Statement]) = {
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
        (Vector.empty, Vector.empty, Some(DeclarationAndDefinition(Some(name), beforeColon, afterColon, afterEqual, opSeq.meta)))

      case (Some(name), cIdx, _) if cIdx != -1 =>
        val beforeColon = opSeq.seq.take(cIdx).toVector
        val afterColon = opSeq.seq.drop(cIdx + 1).toVector
        (Vector.empty, Vector.empty, Some(TypeDeclaration(Some(name), beforeColon, afterColon, opSeq.meta)))

      case (Some(name), _, eIdx) if eIdx != -1 =>
        val beforeEqual = opSeq.seq.take(eIdx).toVector
        val afterEqual = opSeq.seq.drop(eIdx + 1).toVector
        (Vector.empty, Vector.empty, Some(Definition(Some(name), beforeEqual, afterEqual, opSeq.meta)))

      case (None, cIdx, eIdx) if cIdx != -1 && eIdx != -1 =>
        val beforeColon = opSeq.seq.take(cIdx).toVector
        val afterColon = opSeq.seq.slice(cIdx + 1, eIdx).toVector
        val afterEqual = opSeq.seq.drop(eIdx + 1).toVector
        (Vector.empty, Vector.empty, Some(DeclarationAndDefinition(None, beforeColon, afterColon, afterEqual, opSeq.meta)))

      case (None, cIdx, _) if cIdx != -1 =>
        val beforeColon = opSeq.seq.take(cIdx).toVector
        val afterColon = opSeq.seq.drop(cIdx + 1).toVector
        (Vector.empty, Vector.empty, Some(TypeDeclaration(None, beforeColon, afterColon, opSeq.meta)))

      case (None, _, eIdx) if eIdx != -1 =>
        val beforeEqual = opSeq.seq.take(eIdx).toVector
        val afterEqual = opSeq.seq.drop(eIdx + 1).toVector
        (Vector.empty, Vector.empty, Some(Definition(None, beforeEqual, afterEqual, opSeq.meta)))

      case _ =>
        (Vector.empty, Vector(UnsupportedExpressionError(opSeq)), None)
    }
  }

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
