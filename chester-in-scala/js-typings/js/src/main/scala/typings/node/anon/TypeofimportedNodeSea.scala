package typings.node.anon

import org.scalajs.dom.Blob
import typings.node.nodeColonseaMod.AssetKey
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedNodeSea extends StObject {
  
  /**
    * This method can be used to retrieve the assets configured to be bundled into the
    * single-executable application at build time.
    * An error is thrown when no matching asset can be found.
    * @since v20.12.0
    */
  def getAsset(key: AssetKey): js.typedarray.ArrayBuffer = js.native
  def getAsset(key: AssetKey, encoding: String): String = js.native
  
  /**
    * Similar to `sea.getAsset()`, but returns the result in a [`Blob`](https://developer.mozilla.org/en-US/docs/Web/API/Blob).
    * An error is thrown when no matching asset can be found.
    * @since v20.12.0
    */
  def getAssetAsBlob(key: AssetKey): Blob = js.native
  def getAssetAsBlob(key: AssetKey, options: TypeString): Blob = js.native
  
  /**
    * This method can be used to retrieve the assets configured to be bundled into the
    * single-executable application at build time.
    * An error is thrown when no matching asset can be found.
    *
    * Unlike `sea.getRawAsset()` or `sea.getAssetAsBlob()`, this method does not
    * return a copy. Instead, it returns the raw asset bundled inside the executable.
    *
    * For now, users should avoid writing to the returned array buffer. If the
    * injected section is not marked as writable or not aligned properly,
    * writes to the returned array buffer is likely to result in a crash.
    * @since v20.12.0
    */
  def getRawAsset(key: AssetKey): String | js.typedarray.ArrayBuffer = js.native
  
  /**
    * @since v20.12.0
    * @return Whether this script is running inside a single-executable application.
    */
  def isSea(): Boolean = js.native
}
