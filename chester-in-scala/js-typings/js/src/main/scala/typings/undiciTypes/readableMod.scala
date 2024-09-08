package typings.undiciTypes

import org.scalajs.dom.Blob
import typings.undiciTypes.anon.Limit
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object readableMod {
  
  @JSImport("undici-types/readable", JSImport.Default)
  @js.native
  open class default ()
    extends StObject
       with BodyReadable {
    def this(resume: js.ThisFunction1[
            /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Readable */ /* this */ Any, 
            /* size */ Double, 
            Unit | Null
          ]) = this()
    def this(
      resume: js.ThisFunction1[
            /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Readable */ /* this */ Any, 
            /* size */ Double, 
            Unit | Null
          ],
      abort: js.Function0[Unit | Null]
    ) = this()
    def this(resume: Unit, abort: js.Function0[Unit | Null]) = this()
    def this(
      resume: js.ThisFunction1[
            /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Readable */ /* this */ Any, 
            /* size */ Double, 
            Unit | Null
          ],
      abort: js.Function0[Unit | Null],
      contentType: String
    ) = this()
    def this(
      resume: js.ThisFunction1[
            /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Readable */ /* this */ Any, 
            /* size */ Double, 
            Unit | Null
          ],
      abort: Unit,
      contentType: String
    ) = this()
    def this(resume: Unit, abort: js.Function0[Unit | Null], contentType: String) = this()
    def this(resume: Unit, abort: Unit, contentType: String) = this()
  }
  
  /* import warning: RemoveDifficultInheritance.summarizeChanges 
  - Dropped / * import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify Readable * / any */ @js.native
  trait BodyReadable extends StObject {
    
    /** Consumes and returns the body as an ArrayBuffer
      *  https://fetch.spec.whatwg.org/#dom-body-arraybuffer
      */
    def arrayBuffer(): js.Promise[js.typedarray.ArrayBuffer] = js.native
    
    /** Consumes and returns the body as a Blob
      *  https://fetch.spec.whatwg.org/#dom-body-blob
      */
    def blob(): js.Promise[Blob] = js.native
    
    /** 
      * If body is null, it should return null as the body
      *
      *  If body is not null, should return the body as a ReadableStream
      *
      *  https://fetch.spec.whatwg.org/#dom-body-body
      */
    val body: js.UndefOr[scala.Nothing] = js.native
    
    /** Returns true if the body is not null and the body has been consumed
      *
      *  Otherwise, returns false
      *
      * https://fetch.spec.whatwg.org/#dom-body-bodyused
      */
    val bodyUsed: Boolean = js.native
    
    /** Dumps the response body by reading `limit` number of bytes.
      * @param opts.limit Number of bytes to read (optional) - Default: 262144
      */
    def dump(): js.Promise[Unit] = js.native
    def dump(opts: Limit): js.Promise[Unit] = js.native
    
    /** Not implemented
      *
      *  https://fetch.spec.whatwg.org/#dom-body-formdata
      */
    def formData(): js.Promise[scala.Nothing] = js.native
    
    /** Consumes and returns the body as a JavaScript Object
      *  https://fetch.spec.whatwg.org/#dom-body-json
      */
    def json(): js.Promise[Any] = js.native
    
    /** Consumes and returns the body as a string
      *  https://fetch.spec.whatwg.org/#dom-body-text
      */
    def text(): js.Promise[String] = js.native
  }
}
