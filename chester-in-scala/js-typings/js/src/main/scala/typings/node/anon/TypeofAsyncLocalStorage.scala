package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofAsyncLocalStorage extends StObject {
  
  /**
    * Binds the given function to the current execution context.
    * @since v19.8.0
    * @experimental
    * @param fn The function to bind to the current execution context.
    * @return A new function that calls `fn` within the captured execution context.
    */
  /* static member */
  def bind[Func /* <: js.Function1[/* repeated */ Any, Any] */](fn: Func): Func
  
  /**
    * Captures the current execution context and returns a function that accepts a
    * function as an argument. Whenever the returned function is called, it
    * calls the function passed to it within the captured context.
    *
    * ```js
    * const asyncLocalStorage = new AsyncLocalStorage();
    * const runInAsyncScope = asyncLocalStorage.run(123, () => AsyncLocalStorage.snapshot());
    * const result = asyncLocalStorage.run(321, () => runInAsyncScope(() => asyncLocalStorage.getStore()));
    * console.log(result);  // returns 123
    * ```
    *
    * AsyncLocalStorage.snapshot() can replace the use of AsyncResource for simple
    * async context tracking purposes, for example:
    *
    * ```js
    * class Foo {
    *   #runInAsyncScope = AsyncLocalStorage.snapshot();
    *
    *   get() { return this.#runInAsyncScope(() => asyncLocalStorage.getStore()); }
    * }
    *
    * const foo = asyncLocalStorage.run(123, () => new Foo());
    * console.log(asyncLocalStorage.run(321, () => foo.get())); // returns 123
    * ```
    * @since v19.8.0
    * @experimental
    * @return A new function with the signature `(fn: (...args) : R, ...args) : R`.
    */
  /* static member */
  def snapshot(): js.Function2[/* fn */ js.Function1[/* args */ js.Array[Any], Any], /* args */ js.Array[Any], Any]
}
object TypeofAsyncLocalStorage {
  
  inline def apply(
    bind: Any => Any,
    snapshot: () => js.Function2[/* fn */ js.Function1[/* args */ js.Array[Any], Any], /* args */ js.Array[Any], Any]
  ): TypeofAsyncLocalStorage = {
    val __obj = js.Dynamic.literal(bind = js.Any.fromFunction1(bind), snapshot = js.Any.fromFunction0(snapshot))
    __obj.asInstanceOf[TypeofAsyncLocalStorage]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofAsyncLocalStorage] (val x: Self) extends AnyVal {
    
    inline def setBind(value: Any => Any): Self = StObject.set(x, "bind", js.Any.fromFunction1(value))
    
    inline def setSnapshot(
      value: () => js.Function2[/* fn */ js.Function1[/* args */ js.Array[Any], Any], /* args */ js.Array[Any], Any]
    ): Self = StObject.set(x, "snapshot", js.Any.fromFunction0(value))
  }
}
