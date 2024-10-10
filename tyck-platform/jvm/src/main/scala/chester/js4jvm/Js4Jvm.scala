package chester.js4jvm

import org.graalvm.polyglot.*

object Js4Jvm {
  // https://www.graalvm.org/latest/reference-manual/js/Modules/
  val context = Context.newBuilder("js")
    .allowIO(IOAccess.ALL)
    .option("js.esm-eval-returns-exports", "true")
    .build();
  private val source = Source.newBuilder("js", chester.generated.GeneratedJS.jsCode, "main.mjs").mimeType("application/javascript+module").build()
  val exports  = context.eval(source)
}
