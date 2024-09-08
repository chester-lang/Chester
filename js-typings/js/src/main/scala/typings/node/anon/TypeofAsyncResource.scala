package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofAsyncResource extends StObject {
  
  /**
    * Binds the given function to the current execution context.
    * @since v14.8.0, v12.19.0
    * @param fn The function to bind to the current execution context.
    * @param type An optional name to associate with the underlying `AsyncResource`.
    */
  /* static member */
  def bind[Func /* <: js.ThisFunction1[/* this */ ThisArg, /* repeated */ Any, Any] */, ThisArg](fn: Func): Func = js.native
  def bind[Func /* <: js.ThisFunction1[/* this */ ThisArg, /* repeated */ Any, Any] */, ThisArg](fn: Func, `type`: String): Func = js.native
  def bind[Func /* <: js.ThisFunction1[/* this */ ThisArg, /* repeated */ Any, Any] */, ThisArg](fn: Func, `type`: String, thisArg: ThisArg): Func = js.native
  def bind[Func /* <: js.ThisFunction1[/* this */ ThisArg, /* repeated */ Any, Any] */, ThisArg](fn: Func, `type`: Unit, thisArg: ThisArg): Func = js.native
}
