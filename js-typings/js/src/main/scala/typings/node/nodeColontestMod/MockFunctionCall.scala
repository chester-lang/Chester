package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MockFunctionCall[F /* <: js.Function */, ReturnType, Args] extends StObject {
  
  /**
    * An array of the arguments passed to the mock function.
    */
  var arguments: Args
  
  /**
    * If the mocked function threw then this property contains the thrown value.
    */
  var error: js.UndefOr[Any] = js.undefined
  
  /**
    * The value returned by the mocked function.
    *
    * If the mocked function threw, it will be `undefined`.
    */
  var result: js.UndefOr[ReturnType] = js.undefined
  
  /**
    * An `Error` object whose stack can be used to determine the callsite of the mocked function invocation.
    */
  var stack: js.Error
  
  /**
    * If the mocked function is a constructor, this field contains the class being constructed.
    * Otherwise this will be `undefined`.
    */
  var target: /* import warning: importer.ImportType#apply Failed type conversion: F extends abstract new (args : any): any ? F : undefined */ js.Any
  
  /**
    * The mocked function's `this` value.
    */
  var `this`: Any
}
object MockFunctionCall {
  
  inline def apply[F /* <: js.Function */, ReturnType, Args](
    arguments: Args,
    stack: js.Error,
    target: /* import warning: importer.ImportType#apply Failed type conversion: F extends abstract new (args : any): any ? F : undefined */ js.Any,
    `this`: Any
  ): MockFunctionCall[F, ReturnType, Args] = {
    val __obj = js.Dynamic.literal(arguments = arguments.asInstanceOf[js.Any], stack = stack.asInstanceOf[js.Any], target = target.asInstanceOf[js.Any])
    __obj.updateDynamic("this")(`this`.asInstanceOf[js.Any])
    __obj.asInstanceOf[MockFunctionCall[F, ReturnType, Args]]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MockFunctionCall[?, ?, ?], F /* <: js.Function */, ReturnType, Args] (val x: Self & (MockFunctionCall[F, ReturnType, Args])) extends AnyVal {
    
    inline def setArguments(value: Args): Self = StObject.set(x, "arguments", value.asInstanceOf[js.Any])
    
    inline def setError(value: Any): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setErrorUndefined: Self = StObject.set(x, "error", js.undefined)
    
    inline def setResult(value: ReturnType): Self = StObject.set(x, "result", value.asInstanceOf[js.Any])
    
    inline def setResultUndefined: Self = StObject.set(x, "result", js.undefined)
    
    inline def setStack(value: js.Error): Self = StObject.set(x, "stack", value.asInstanceOf[js.Any])
    
    inline def setTarget(
      value: /* import warning: importer.ImportType#apply Failed type conversion: F extends abstract new (args : any): any ? F : undefined */ js.Any
    ): Self = StObject.set(x, "target", value.asInstanceOf[js.Any])
    
    inline def setThis(value: Any): Self = StObject.set(x, "this", value.asInstanceOf[js.Any])
  }
}
