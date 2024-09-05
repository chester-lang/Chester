package chester.utils.doc

import kiama.output.AbstractPrettyPrinter
import org.bitbucket.inkytonik.kiama.util.Trampolines._

trait StylePrettyPrinter extends AbstractPrettyPrinter {
  type Attribute = Style

  def noAttribute: Attribute = Style.Empty

  extension (doc: Doc) {
    def styled(style: Attribute): Doc = new Doc(
      (iw: IW) =>
        (c1: TreeCont) =>
          More(() =>
            for {
              c2 <- doc(iw)(c1)
            } yield {
              (p: PPosition, dq: Dq) =>
                Done((r: Remaining) => {
                  for {
                    buffer <- c2(p, dq)
                    result <- (
                      for {
                        a <- buffer(r)
                      } yield a.map {
                        case Text(content, currentStyle) =>
                          // Merge the current style with the new one
                          Text(content, currentStyle ++ style)
                        case other => other // Leave other entries unchanged
                      }
                      )
                  } yield result
                })
            }
          )
    )
  }
}
