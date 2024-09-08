package typings.node

import org.scalajs.dom.Blob
import typings.node.anon.TypeString
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object nodeColonseaMod {
  
  @JSImport("node:sea", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * This method can be used to retrieve the assets configured to be bundled into the
    * single-executable application at build time.
    * An error is thrown when no matching asset can be found.
    * @since v20.12.0
    */
  inline def getAsset(key: AssetKey): js.typedarray.ArrayBuffer = ^.asInstanceOf[js.Dynamic].applyDynamic("getAsset")(key.asInstanceOf[js.Any]).asInstanceOf[js.typedarray.ArrayBuffer]
  inline def getAsset(key: AssetKey, encoding: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("getAsset")(key.asInstanceOf[js.Any], encoding.asInstanceOf[js.Any])).asInstanceOf[String]
  
  /**
    * Similar to `sea.getAsset()`, but returns the result in a [`Blob`](https://developer.mozilla.org/en-US/docs/Web/API/Blob).
    * An error is thrown when no matching asset can be found.
    * @since v20.12.0
    */
  inline def getAssetAsBlob(key: AssetKey): Blob = ^.asInstanceOf[js.Dynamic].applyDynamic("getAssetAsBlob")(key.asInstanceOf[js.Any]).asInstanceOf[Blob]
  inline def getAssetAsBlob(key: AssetKey, options: TypeString): Blob = (^.asInstanceOf[js.Dynamic].applyDynamic("getAssetAsBlob")(key.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Blob]
  
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
  inline def getRawAsset(key: AssetKey): String | js.typedarray.ArrayBuffer = ^.asInstanceOf[js.Dynamic].applyDynamic("getRawAsset")(key.asInstanceOf[js.Any]).asInstanceOf[String | js.typedarray.ArrayBuffer]
  
  /**
    * @since v20.12.0
    * @return Whether this script is running inside a single-executable application.
    */
  inline def isSea(): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isSea")().asInstanceOf[Boolean]
  
  type AssetKey = String
}
