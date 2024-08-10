package chester.pretty.doc

implicit object StringRenderer extends Renderer[String]:
  override def renderTokens(tokens: Vector[Token], useCRLF: Boolean = false): String =
    val newline = if (useCRLF) "\r\n" else "\n"
    tokens.map {
      case TokenText(content) => content
      case TokenNewLine => newline
      case TokenColor(innerTokens, _) => renderTokens(innerTokens, useCRLF)
    }.mkString

implicit object FansiRenderer extends Renderer[fansi.Str]:
  override def renderTokens(tokens: Vector[Token], useCRLF: Boolean = false): fansi.Str =
    val newline = if (useCRLF) "\r\n" else "\n"
    tokens.foldLeft(fansi.Str("")) {
      case (acc, TokenText(content)) => acc ++ fansi.Str(content)
      case (acc, TokenNewLine) => acc ++ fansi.Str(newline)
      case (acc, TokenColor(innerTokens, color: Color)) =>
        acc ++ innerTokens.foldLeft(fansi.Str("")) {
          case (innerAcc, TokenText(content)) => innerAcc ++ ColorMapping.toFansiAttr(color)(content)
          case (innerAcc, TokenNewLine) => innerAcc ++ fansi.Str(newline)
          case (innerAcc, TokenColor(innerInnerTokens, innerColor)) =>
            innerAcc ++ innerInnerTokens.foldLeft(fansi.Str("")) {
              case (innermostAcc, TokenText(content)) => innermostAcc ++ ColorMapping.toFansiAttr(innerColor.asInstanceOf[Color])(content)
              case (innermostAcc, TokenNewLine) => innermostAcc ++ fansi.Str(newline)
              case (innermostAcc, TokenColor(_, _)) => innermostAcc // This would rarely be the case, you might want to throw an exception here
            }
        }
    }

object HtmlRenderer extends Renderer[String]:
  override def renderTokens(tokens: Vector[Token], useCRLF: Boolean = false): String =
    val newline = "<br />"
    tokens.map {
      case TokenText(content) => content
      case TokenNewLine => newline
      case TokenColor(innerTokens, color: Color) => s"<span style='color: ${ColorMapping.toHtmlCss(color)};'>${renderTokens(innerTokens, useCRLF)}</span>"
    }.mkString
