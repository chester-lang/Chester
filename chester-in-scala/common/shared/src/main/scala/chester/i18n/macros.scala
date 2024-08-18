package chester.i18n

import scala.quoted.*

trait T {
  def t(args: Any*)(implicit lang: Language = Language.from("en_US")): String
}

private def tMacro(sc: Expr[StringContext])(using Quotes): Expr[T] = {
  println(sc.show)
  '{
    new T {
      def t(args: Any*)(implicit lang: Language): String = {
        $sc.s(args: _*)
      }
    } }
}
implicit inline def t(inline sc: StringContext): T = ${ tMacro('sc) }
