package typings.node.anon

import typings.node.fsMod.BufferEncodingOption
import typings.node.fsMod.EncodingOption
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.nodeStrings.buffer_
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofmkdtempPromisify extends StObject {
  
  def apply(prefix: String, callback: js.Function2[/* err */ ErrnoException | Null, /* folder */ String, Unit]): Unit = js.native
  def apply(
    prefix: String,
    options: `2`,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* folder */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def apply(
    prefix: String,
    options: EncodingOption,
    callback: js.Function2[
      ErrnoException | Null, 
      typings.node.bufferMod.global.Buffer | (/* folder */ String), 
      Unit
    ]
  ): Unit = js.native
  def apply(
    prefix: String,
    options: buffer_,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* folder */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  
  /**
    * Asynchronously creates a unique temporary directory.
    * Generates six random characters to be appended behind a required prefix to create a unique temporary directory.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def __promisify__(prefix: String): js.Promise[String] = js.native
  /**
    * Asynchronously creates a unique temporary directory.
    * Generates six random characters to be appended behind a required prefix to create a unique temporary directory.
    * @param options The encoding (or an object specifying the encoding), used as the encoding of the result. If not provided, `'utf8'` is used.
    */
  def __promisify__(prefix: String, options: BufferEncodingOption): js.Promise[typings.node.bufferMod.global.Buffer] = js.native
  def __promisify__(prefix: String, options: EncodingOption): js.Promise[String] = js.native
}
