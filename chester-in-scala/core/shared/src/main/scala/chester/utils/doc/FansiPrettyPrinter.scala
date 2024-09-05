package chester.utils.doc

import kiama.output.ParenPrettyPrinter

trait FansiPrettyPrinter extends StylePrettyPrinter {
  type Layout = fansi.Str
  type Builder = fansi.Str
  override def newBuilder: Builder = ""
  override def BuilderAppend(builder: Builder, text: Text): Builder = builder ++ fansi.Str(text.s).overlay(text.attr.toFansi)
  override def BuilderResult(builder: Builder): Layout = builder
}
object FansiPrettyPrinter extends FansiPrettyPrinter with ParenPrettyPrinter