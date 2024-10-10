package chester

import org.graalvm.polyglot.*
import org.graalvm.polyglot.io.IOAccess

object Js4Jvm {
  // https://www.graalvm.org/latest/reference-manual/js/Modules/
  val context = Context
    .newBuilder("js")
    .allowIO(IOAccess.ALL)
    .option("js.esm-eval-returns-exports", "true")
    .option("engine.WarnInterpreterOnly", "false")
    .build();
  private val source = Source.newBuilder("js", chester.generated.GeneratedJS.jsCode, "main.mjs").mimeType("application/javascript+module").build()
  val exports = context.eval(source)
}
