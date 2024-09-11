package chester.utils.doc

import kiama2.output.AbstractPrettyPrinter
import org.bitbucket.inkytonik.kiama.util.Trampolines._

trait StylePrettyPrinter extends AbstractPrettyPrinter {
  type Attribute = Style

  def noAttribute: Attribute = Style.Empty
}
