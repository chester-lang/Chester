package typings.node.anon

import typings.node.utilMod.CustomPromisify
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofpromisify extends StObject {
  
  def apply[TCustom /* <: js.Function */](fn: CustomPromisify[TCustom]): TCustom = js.native
  
  /**
    * That can be used to declare custom promisified variants of functions.
    */
  val custom: js.Symbol = js.native
}
