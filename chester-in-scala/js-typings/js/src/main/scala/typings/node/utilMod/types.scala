package typings.node.utilMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object types {
  
  @JSImport("util", "types")
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * Returns `true` if the value is a built-in [`ArrayBuffer`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer) or
    * [`SharedArrayBuffer`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/SharedArrayBuffer) instance.
    *
    * See also `util.types.isArrayBuffer()` and `util.types.isSharedArrayBuffer()`.
    *
    * ```js
    * util.types.isAnyArrayBuffer(new ArrayBuffer());  // Returns true
    * util.types.isAnyArrayBuffer(new SharedArrayBuffer());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isAnyArrayBuffer(`object`: Any): /* is std.ArrayBufferLike */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isAnyArrayBuffer")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.ArrayBufferLike */ Boolean]
  
  /**
    * Returns `true` if the value is an `arguments` object.
    *
    * ```js
    * function foo() {
    *   util.types.isArgumentsObject(arguments);  // Returns true
    * }
    * ```
    * @since v10.0.0
    */
  inline def isArgumentsObject(`object`: Any): /* is std.IArguments */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isArgumentsObject")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.IArguments */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`ArrayBuffer`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer) instance.
    * This does _not_ include [`SharedArrayBuffer`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/SharedArrayBuffer) instances. Usually, it is
    * desirable to test for both; See `util.types.isAnyArrayBuffer()` for that.
    *
    * ```js
    * util.types.isArrayBuffer(new ArrayBuffer());  // Returns true
    * util.types.isArrayBuffer(new SharedArrayBuffer());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isArrayBuffer(`object`: Any): /* is std.ArrayBuffer */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isArrayBuffer")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.ArrayBuffer */ Boolean]
  
  /**
    * Returns `true` if the value is an instance of one of the [`ArrayBuffer`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer) views, such as typed
    * array objects or [`DataView`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/DataView). Equivalent to
    * [`ArrayBuffer.isView()`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer/isView).
    *
    * ```js
    * util.types.isArrayBufferView(new Int8Array());  // true
    * util.types.isArrayBufferView(Buffer.from('hello world')); // true
    * util.types.isArrayBufferView(new DataView(new ArrayBuffer(16)));  // true
    * util.types.isArrayBufferView(new ArrayBuffer());  // false
    * ```
    * @since v10.0.0
    */
  inline def isArrayBufferView(`object`: Any): /* is node.node/globals.<global>.NodeJS.ArrayBufferView */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isArrayBufferView")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is node.node/globals.<global>.NodeJS.ArrayBufferView */ Boolean]
  
  /**
    * Returns `true` if the value is an [async function](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/async_function).
    * This only reports back what the JavaScript engine is seeing;
    * in particular, the return value may not match the original source code if
    * a transpilation tool was used.
    *
    * ```js
    * util.types.isAsyncFunction(function foo() {});  // Returns false
    * util.types.isAsyncFunction(async function foo() {});  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isAsyncFunction(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isAsyncFunction")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if the value is a `BigInt64Array` instance.
    *
    * ```js
    * util.types.isBigInt64Array(new BigInt64Array());   // Returns true
    * util.types.isBigInt64Array(new BigUint64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isBigInt64Array(value: Any): /* is node.node/globals.<global>.BigInt64Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isBigInt64Array")(value.asInstanceOf[js.Any]).asInstanceOf[/* is node.node/globals.<global>.BigInt64Array */ Boolean]
  
  /**
    * Returns `true` if the value is a `BigUint64Array` instance.
    *
    * ```js
    * util.types.isBigUint64Array(new BigInt64Array());   // Returns false
    * util.types.isBigUint64Array(new BigUint64Array());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isBigUint64Array(value: Any): /* is node.node/globals.<global>.BigUint64Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isBigUint64Array")(value.asInstanceOf[js.Any]).asInstanceOf[/* is node.node/globals.<global>.BigUint64Array */ Boolean]
  
  /**
    * Returns `true` if the value is a boolean object, e.g. created
    * by `new Boolean()`.
    *
    * ```js
    * util.types.isBooleanObject(false);  // Returns false
    * util.types.isBooleanObject(true);   // Returns false
    * util.types.isBooleanObject(new Boolean(false)); // Returns true
    * util.types.isBooleanObject(new Boolean(true));  // Returns true
    * util.types.isBooleanObject(Boolean(false)); // Returns false
    * util.types.isBooleanObject(Boolean(true));  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isBooleanObject(`object`: Any): /* is std.Boolean */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isBooleanObject")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Boolean */ Boolean]
  
  /**
    * Returns `true` if the value is any boxed primitive object, e.g. created
    * by `new Boolean()`, `new String()` or `Object(Symbol())`.
    *
    * For example:
    *
    * ```js
    * util.types.isBoxedPrimitive(false); // Returns false
    * util.types.isBoxedPrimitive(new Boolean(false)); // Returns true
    * util.types.isBoxedPrimitive(Symbol('foo')); // Returns false
    * util.types.isBoxedPrimitive(Object(Symbol('foo'))); // Returns true
    * util.types.isBoxedPrimitive(Object(BigInt(5))); // Returns true
    * ```
    * @since v10.11.0
    */
  inline def isBoxedPrimitive(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isBoxedPrimitive")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if `value` is a `CryptoKey`, `false` otherwise.
    * @since v16.2.0
    */
  inline def isCryptoKey(`object`: Any): /* is node.crypto.webcrypto.CryptoKey */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isCryptoKey")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is node.crypto.webcrypto.CryptoKey */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`DataView`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/DataView) instance.
    *
    * ```js
    * const ab = new ArrayBuffer(20);
    * util.types.isDataView(new DataView(ab));  // Returns true
    * util.types.isDataView(new Float64Array());  // Returns false
    * ```
    *
    * See also [`ArrayBuffer.isView()`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer/isView).
    * @since v10.0.0
    */
  inline def isDataView(`object`: Any): /* is std.DataView */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isDataView")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.DataView */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Date`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Date) instance.
    *
    * ```js
    * util.types.isDate(new Date());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isDate(`object`: Any): /* is std.Date */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isDate")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Date */ Boolean]
  
  /**
    * Returns `true` if the value is a native `External` value.
    *
    * A native `External` value is a special type of object that contains a
    * raw C++ pointer (`void*`) for access from native code, and has no other
    * properties. Such objects are created either by Node.js internals or native
    * addons. In JavaScript, they are [frozen](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/freeze) objects with a`null` prototype.
    *
    * ```c
    * #include <js_native_api.h>
    * #include <stdlib.h>
    * napi_value result;
    * static napi_value MyNapi(napi_env env, napi_callback_info info) {
    *   int* raw = (int*) malloc(1024);
    *   napi_status status = napi_create_external(env, (void*) raw, NULL, NULL, &#x26;result);
    *   if (status != napi_ok) {
    *     napi_throw_error(env, NULL, "napi_create_external failed");
    *     return NULL;
    *   }
    *   return result;
    * }
    * ...
    * DECLARE_NAPI_PROPERTY("myNapi", MyNapi)
    * ...
    * ```
    *
    * ```js
    * const native = require('napi_addon.node');
    * const data = native.myNapi();
    * util.types.isExternal(data); // returns true
    * util.types.isExternal(0); // returns false
    * util.types.isExternal(new String('foo')); // returns false
    * ```
    *
    * For further information on `napi_create_external`, refer to `napi_create_external()`.
    * @since v10.0.0
    */
  inline def isExternal(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isExternal")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Float32Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Float32Array) instance.
    *
    * ```js
    * util.types.isFloat32Array(new ArrayBuffer());  // Returns false
    * util.types.isFloat32Array(new Float32Array());  // Returns true
    * util.types.isFloat32Array(new Float64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isFloat32Array(`object`: Any): /* is std.Float32Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isFloat32Array")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Float32Array */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Float64Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Float64Array) instance.
    *
    * ```js
    * util.types.isFloat64Array(new ArrayBuffer());  // Returns false
    * util.types.isFloat64Array(new Uint8Array());  // Returns false
    * util.types.isFloat64Array(new Float64Array());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isFloat64Array(`object`: Any): /* is std.Float64Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isFloat64Array")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Float64Array */ Boolean]
  
  /**
    * Returns `true` if the value is a generator function.
    * This only reports back what the JavaScript engine is seeing;
    * in particular, the return value may not match the original source code if
    * a transpilation tool was used.
    *
    * ```js
    * util.types.isGeneratorFunction(function foo() {});  // Returns false
    * util.types.isGeneratorFunction(function* foo() {});  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isGeneratorFunction(`object`: Any): /* is std.GeneratorFunction */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isGeneratorFunction")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.GeneratorFunction */ Boolean]
  
  /**
    * Returns `true` if the value is a generator object as returned from a
    * built-in generator function.
    * This only reports back what the JavaScript engine is seeing;
    * in particular, the return value may not match the original source code if
    * a transpilation tool was used.
    *
    * ```js
    * function* foo() {}
    * const generator = foo();
    * util.types.isGeneratorObject(generator);  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isGeneratorObject(`object`: Any): /* is std.Generator<unknown, any, unknown> */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isGeneratorObject")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Generator<unknown, any, unknown> */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Int16Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Int16Array) instance.
    *
    * ```js
    * util.types.isInt16Array(new ArrayBuffer());  // Returns false
    * util.types.isInt16Array(new Int16Array());  // Returns true
    * util.types.isInt16Array(new Float64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isInt16Array(`object`: Any): /* is std.Int16Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isInt16Array")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Int16Array */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Int32Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Int32Array) instance.
    *
    * ```js
    * util.types.isInt32Array(new ArrayBuffer());  // Returns false
    * util.types.isInt32Array(new Int32Array());  // Returns true
    * util.types.isInt32Array(new Float64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isInt32Array(`object`: Any): /* is std.Int32Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isInt32Array")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Int32Array */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Int8Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Int8Array) instance.
    *
    * ```js
    * util.types.isInt8Array(new ArrayBuffer());  // Returns false
    * util.types.isInt8Array(new Int8Array());  // Returns true
    * util.types.isInt8Array(new Float64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isInt8Array(`object`: Any): /* is std.Int8Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isInt8Array")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Int8Array */ Boolean]
  
  /**
    * Returns `true` if `value` is a `KeyObject`, `false` otherwise.
    * @since v16.2.0
    */
  inline def isKeyObject(`object`: Any): /* is node.node:crypto.KeyObject */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isKeyObject")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is node.node:crypto.KeyObject */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Map`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map) instance.
    *
    * ```js
    * util.types.isMap(new Map());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isMap[T](`object`: T): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isMap")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  inline def isMap[T](`object`: js.Object): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isMap")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if the value is an iterator returned for a built-in [`Map`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Map) instance.
    *
    * ```js
    * const map = new Map();
    * util.types.isMapIterator(map.keys());  // Returns true
    * util.types.isMapIterator(map.values());  // Returns true
    * util.types.isMapIterator(map.entries());  // Returns true
    * util.types.isMapIterator(map[Symbol.iterator]());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isMapIterator(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isMapIterator")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if the value is an instance of a [Module Namespace Object](https://tc39.github.io/ecma262/#sec-module-namespace-exotic-objects).
    *
    * ```js
    * import * as ns from './a.js';
    *
    * util.types.isModuleNamespaceObject(ns);  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isModuleNamespaceObject(value: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isModuleNamespaceObject")(value.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if the value was returned by the constructor of a [built-in `Error` type](https://tc39.es/ecma262/#sec-error-objects).
    *
    * ```js
    * console.log(util.types.isNativeError(new Error()));  // true
    * console.log(util.types.isNativeError(new TypeError()));  // true
    * console.log(util.types.isNativeError(new RangeError()));  // true
    * ```
    *
    * Subclasses of the native error types are also native errors:
    *
    * ```js
    * class MyError extends Error {}
    * console.log(util.types.isNativeError(new MyError()));  // true
    * ```
    *
    * A value being `instanceof` a native error class is not equivalent to `isNativeError()` returning `true` for that value. `isNativeError()` returns `true` for errors
    * which come from a different [realm](https://tc39.es/ecma262/#realm) while `instanceof Error` returns `false` for these errors:
    *
    * ```js
    * const vm = require('node:vm');
    * const context = vm.createContext({});
    * const myError = vm.runInContext('new Error()', context);
    * console.log(util.types.isNativeError(myError)); // true
    * console.log(myError instanceof Error); // false
    * ```
    *
    * Conversely, `isNativeError()` returns `false` for all objects which were not
    * returned by the constructor of a native error. That includes values
    * which are `instanceof` native errors:
    *
    * ```js
    * const myError = { __proto__: Error.prototype };
    * console.log(util.types.isNativeError(myError)); // false
    * console.log(myError instanceof Error); // true
    * ```
    * @since v10.0.0
    */
  inline def isNativeError(`object`: Any): /* is std.Error */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isNativeError")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Error */ Boolean]
  
  /**
    * Returns `true` if the value is a number object, e.g. created
    * by `new Number()`.
    *
    * ```js
    * util.types.isNumberObject(0);  // Returns false
    * util.types.isNumberObject(new Number(0));   // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isNumberObject(`object`: Any): /* is std.Number */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isNumberObject")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Number */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Promise`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise).
    *
    * ```js
    * util.types.isPromise(Promise.resolve(42));  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isPromise(`object`: Any): /* is std.Promise<unknown> */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isPromise")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Promise<unknown> */ Boolean]
  
  /**
    * Returns `true` if the value is a [`Proxy`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Proxy) instance.
    *
    * ```js
    * const target = {};
    * const proxy = new Proxy(target, {});
    * util.types.isProxy(target);  // Returns false
    * util.types.isProxy(proxy);  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isProxy(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isProxy")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if the value is a regular expression object.
    *
    * ```js
    * util.types.isRegExp(/abc/);  // Returns true
    * util.types.isRegExp(new RegExp('abc'));  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isRegExp(`object`: Any): /* is std.RegExp */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isRegExp")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.RegExp */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Set`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Set) instance.
    *
    * ```js
    * util.types.isSet(new Set());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isSet[T](`object`: T): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isSet")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  inline def isSet[T](`object`: js.Object): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isSet")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if the value is an iterator returned for a built-in [`Set`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Set) instance.
    *
    * ```js
    * const set = new Set();
    * util.types.isSetIterator(set.keys());  // Returns true
    * util.types.isSetIterator(set.values());  // Returns true
    * util.types.isSetIterator(set.entries());  // Returns true
    * util.types.isSetIterator(set[Symbol.iterator]());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isSetIterator(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isSetIterator")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`SharedArrayBuffer`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/SharedArrayBuffer) instance.
    * This does _not_ include [`ArrayBuffer`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer) instances. Usually, it is
    * desirable to test for both; See `util.types.isAnyArrayBuffer()` for that.
    *
    * ```js
    * util.types.isSharedArrayBuffer(new ArrayBuffer());  // Returns false
    * util.types.isSharedArrayBuffer(new SharedArrayBuffer());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isSharedArrayBuffer(`object`: Any): /* is / * import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify SharedArrayBuffer * / any */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isSharedArrayBuffer")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is / * import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify SharedArrayBuffer * / any */ Boolean]
  
  /**
    * Returns `true` if the value is a string object, e.g. created
    * by `new String()`.
    *
    * ```js
    * util.types.isStringObject('foo');  // Returns false
    * util.types.isStringObject(new String('foo'));   // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isStringObject(`object`: Any): /* is std.String */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isStringObject")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.String */ Boolean]
  
  /**
    * Returns `true` if the value is a symbol object, created
    * by calling `Object()` on a `Symbol` primitive.
    *
    * ```js
    * const symbol = Symbol('foo');
    * util.types.isSymbolObject(symbol);  // Returns false
    * util.types.isSymbolObject(Object(symbol));   // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isSymbolObject(`object`: Any): /* is std.Symbol */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isSymbolObject")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Symbol */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`TypedArray`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/TypedArray) instance.
    *
    * ```js
    * util.types.isTypedArray(new ArrayBuffer());  // Returns false
    * util.types.isTypedArray(new Uint8Array());  // Returns true
    * util.types.isTypedArray(new Float64Array());  // Returns true
    * ```
    *
    * See also [`ArrayBuffer.isView()`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/ArrayBuffer/isView).
    * @since v10.0.0
    */
  inline def isTypedArray(`object`: Any): /* is node.node/globals.<global>.NodeJS.TypedArray */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isTypedArray")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is node.node/globals.<global>.NodeJS.TypedArray */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Uint16Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Uint16Array) instance.
    *
    * ```js
    * util.types.isUint16Array(new ArrayBuffer());  // Returns false
    * util.types.isUint16Array(new Uint16Array());  // Returns true
    * util.types.isUint16Array(new Float64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isUint16Array(`object`: Any): /* is std.Uint16Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isUint16Array")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Uint16Array */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Uint32Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Uint32Array) instance.
    *
    * ```js
    * util.types.isUint32Array(new ArrayBuffer());  // Returns false
    * util.types.isUint32Array(new Uint32Array());  // Returns true
    * util.types.isUint32Array(new Float64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isUint32Array(`object`: Any): /* is std.Uint32Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isUint32Array")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Uint32Array */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Uint8Array`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Uint8Array) instance.
    *
    * ```js
    * util.types.isUint8Array(new ArrayBuffer());  // Returns false
    * util.types.isUint8Array(new Uint8Array());  // Returns true
    * util.types.isUint8Array(new Float64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isUint8Array(`object`: Any): /* is std.Uint8Array */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isUint8Array")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Uint8Array */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`Uint8ClampedArray`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Uint8ClampedArray) instance.
    *
    * ```js
    * util.types.isUint8ClampedArray(new ArrayBuffer());  // Returns false
    * util.types.isUint8ClampedArray(new Uint8ClampedArray());  // Returns true
    * util.types.isUint8ClampedArray(new Float64Array());  // Returns false
    * ```
    * @since v10.0.0
    */
  inline def isUint8ClampedArray(`object`: Any): /* is std.Uint8ClampedArray */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isUint8ClampedArray")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Uint8ClampedArray */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`WeakMap`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/WeakMap) instance.
    *
    * ```js
    * util.types.isWeakMap(new WeakMap());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isWeakMap(`object`: Any): /* is std.WeakMap<object, unknown> */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isWeakMap")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.WeakMap<object, unknown> */ Boolean]
  
  /**
    * Returns `true` if the value is a built-in [`WeakSet`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/WeakSet) instance.
    *
    * ```js
    * util.types.isWeakSet(new WeakSet());  // Returns true
    * ```
    * @since v10.0.0
    */
  inline def isWeakSet(`object`: Any): /* is std.WeakSet<object> */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isWeakSet")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.WeakSet<object> */ Boolean]
}
