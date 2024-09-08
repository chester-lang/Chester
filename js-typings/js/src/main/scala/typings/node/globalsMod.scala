package typings.node

import org.scalablytyped.runtime.Shortcut
import org.scalablytyped.runtime.StringDictionary
import org.scalajs.dom.Event
import org.scalajs.dom.URL
import typings.node.anon.End
import typings.node.anon.Paths
import typings.node.anon.Transfer
import typings.node.bufferMod.global.Buffer
import typings.node.bufferMod.global.BufferEncoding
import typings.node.eventsMod.DefaultEventMap
import typings.node.eventsMod.global.NodeJS.EventEmitter
import typings.node.globalsMod.global.NodeJS.CallSite
import typings.node.globalsMod.global.NodeJS.Module
import typings.node.globalsMod.global.NodeJS.Require
import typings.node.globalsMod.global.NodeJS.WritableStream
import typings.node.nodeColonconsoleMod.global.Console_
import typings.node.nodeColonconsoleMod.global.console.ConsoleConstructorOptions
import typings.node.processMod.global.NodeJS.Process
import typings.std.Error
import typings.std.EventTarget
import typings.undiciTypes.mod.Request_
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object globalsMod {
  
  // #endregion Fetch and friends
  object global {
    
    // #region borrowed
    // from https://github.com/microsoft/TypeScript/blob/38da7c600c83e7b31193a62495239a0fe478cb67/lib/lib.webworker.d.ts#L633 until moved to separate lib
    /** A controller object that allows you to abort one or more DOM requests as and when desired. */
    @js.native
    trait AbortController extends StObject {
      
      /**
        * Invoking this method will set this object's AbortSignal's aborted flag and signal to any observers that the associated activity is to be aborted.
        */
      def abort(): Unit = js.native
      def abort(reason: Any): Unit = js.native
      
      /**
        * Returns the AbortSignal object associated with this object.
        */
      val signal: AbortSignal = js.native
    }
    @JSGlobal("AbortController")
    @js.native
    def AbortController: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   AbortController :infer T} ? T : {new (): node.node/globals.<global>.AbortController} */ js.Any = js.native
    inline def AbortController_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   AbortController :infer T} ? T : {new (): node.node/globals.<global>.AbortController} */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("AbortController")(x.asInstanceOf[js.Any])
    
    /** A signal object that allows you to communicate with a DOM request (such as a Fetch) and abort it if required via an AbortController object. */
    @js.native
    trait AbortSignal
      extends StObject
         with EventTarget {
      
      /**
        * Returns true if this AbortSignal's AbortController has signaled to abort, and false otherwise.
        */
      val aborted: Boolean = js.native
      
      var onabort: Null | (js.ThisFunction1[/* this */ this.type, /* event */ Event, Any]) = js.native
      
      val reason: Any = js.native
      
      def throwIfAborted(): Unit = js.native
    }
    @JSGlobal("AbortSignal")
    @js.native
    def AbortSignal: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   AbortSignal :infer T} ? T : {new (): node.node/globals.<global>.AbortSignal, abort (): node.node/globals.<global>.AbortSignal, abort (reason : any): node.node/globals.<global>.AbortSignal, timeout (milliseconds : number): node.node/globals.<global>.AbortSignal, any (signals : std.Array<node.node/globals.<global>.AbortSignal>): node.node/globals.<global>.AbortSignal} */ js.Any = js.native
    inline def AbortSignal_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   AbortSignal :infer T} ? T : {new (): node.node/globals.<global>.AbortSignal, abort (): node.node/globals.<global>.AbortSignal, abort (reason : any): node.node/globals.<global>.AbortSignal, timeout (milliseconds : number): node.node/globals.<global>.AbortSignal, any (signals : std.Array<node.node/globals.<global>.AbortSignal>): node.node/globals.<global>.AbortSignal} */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("AbortSignal")(x.asInstanceOf[js.Any])
    
    /**
      * Only available through the [--experimental-eventsource](https://nodejs.org/api/cli.html#--experimental-eventsource) flag.
      */
    @JSGlobal("EventSource")
    @js.native
    def EventSource: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   EventSource :infer T} ? T : { readonly CLOSED :2,  readonly CONNECTING :0,  readonly OPEN :1} */ js.Any = js.native
    inline def EventSource_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   EventSource :infer T} ? T : { readonly CLOSED :2,  readonly CONNECTING :0,  readonly OPEN :1} */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("EventSource")(x.asInstanceOf[js.Any])
    
    @JSGlobal("File")
    @js.native
    def File: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   File :infer T} ? T : new (sources : std.Array<node.crypto.BinaryLike | node.buffer.Blob>, fileName : string, options : node.buffer.FileOptions | undefined): node.node:buffer.File */ js.Any = js.native
    inline def File_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   File :infer T} ? T : new (sources : std.Array<node.crypto.BinaryLike | node.buffer.Blob>, fileName : string, options : node.buffer.FileOptions | undefined): node.node:buffer.File */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("File")(x.asInstanceOf[js.Any])
    
    @JSGlobal("FormData")
    @js.native
    def FormData: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   FormData :infer T} ? T : new (): undici-types.undici-types.FormData */ js.Any = js.native
    inline def FormData_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   FormData :infer T} ? T : new (): undici-types.undici-types.FormData */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("FormData")(x.asInstanceOf[js.Any])
    
    @JSGlobal("Headers")
    @js.native
    def Headers: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   Headers :infer T} ? T : new (): undici-types.undici-types.Headers */ js.Any = js.native
    inline def Headers_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   Headers :infer T} ? T : new (): undici-types.undici-types.Headers */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("Headers")(x.asInstanceOf[js.Any])
    
    @JSGlobal("MessageEvent")
    @js.native
    def MessageEvent: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   MessageEvent :infer T} ? T : / * import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_undici-types.MessageEvent * / any */ js.Any = js.native
    inline def MessageEvent_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   MessageEvent :infer T} ? T : / * import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_undici-types.MessageEvent * / any */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("MessageEvent")(x.asInstanceOf[js.Any])
    
    @JSGlobal("Request")
    @js.native
    def Request: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   Request :infer T} ? T : new (input : undici-types.undici-types/fetch.RequestInfo): undici-types.undici-types.Request */ js.Any = js.native
    inline def Request_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   Request :infer T} ? T : new (input : undici-types.undici-types/fetch.RequestInfo): undici-types.undici-types.Request */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("Request")(x.asInstanceOf[js.Any])
    
    @JSGlobal("Response")
    @js.native
    def Response: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   Response :infer T} ? T : {error (): undici-types.undici-types/fetch.Response, json (data : any): undici-types.undici-types/fetch.Response, json (data : any, init : undici-types.undici-types/fetch.ResponseInit): undici-types.undici-types/fetch.Response, redirect (url : string, status : undici-types.undici-types/fetch.ResponseRedirectStatus): undici-types.undici-types/fetch.Response, redirect (url : std.URL, status : undici-types.undici-types/fetch.ResponseRedirectStatus): undici-types.undici-types/fetch.Response, new (): undici-types.undici-types.Response} */ js.Any = js.native
    inline def Response_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   Response :infer T} ? T : {error (): undici-types.undici-types/fetch.Response, json (data : any): undici-types.undici-types/fetch.Response, json (data : any, init : undici-types.undici-types/fetch.ResponseInit): undici-types.undici-types/fetch.Response, redirect (url : string, status : undici-types.undici-types/fetch.ResponseRedirectStatus): undici-types.undici-types/fetch.Response, redirect (url : std.URL, status : undici-types.undici-types/fetch.ResponseRedirectStatus): undici-types.undici-types/fetch.Response, new (): undici-types.undici-types.Response} */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("Response")(x.asInstanceOf[js.Any])
    
    @JSGlobal("WebSocket")
    @js.native
    def WebSocket: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   WebSocket :infer T} ? T : { readonly CLOSED :number,  readonly CLOSING :number,  readonly CONNECTING :number,  readonly OPEN :number} */ js.Any = js.native
    inline def WebSocket_=(
      x: /* import warning: importer.ImportType#apply Failed type conversion: / * globalThis * / any extends {  onmessage :any,   WebSocket :infer T} ? T : { readonly CLOSED :number,  readonly CLOSING :number,  readonly CONNECTING :number,  readonly OPEN :number} */ js.Any
    ): Unit = js.Dynamic.global.updateDynamic("WebSocket")(x.asInstanceOf[js.Any])
    
    object console extends Shortcut {
      
      @JSGlobal("console")
      @js.native
      val ^ : Console_ = js.native
      
      /* This class was inferred from a value with a constructor. In rare cases (like HTMLElement in the DOM) it might not work as you expect. */
      @JSGlobal("console.Console")
      @js.native
      open class Console protected ()
        extends StObject
           with Console_ {
        def this(options: ConsoleConstructorOptions) = this()
        def this(stdout: WritableStream) = this()
        def this(stdout: WritableStream, stderr: WritableStream) = this()
        def this(stdout: WritableStream, stderr: Unit, ignoreErrors: Boolean) = this()
        def this(stdout: WritableStream, stderr: WritableStream, ignoreErrors: Boolean) = this()
      }
      
      type _To = Console_
      
      /* This means you don't have to write `^`, but can instead just say `console.foo` */
      override def _to: Console_ = ^
    }
    
    @JSGlobal("__dirname")
    @js.native
    def dirname: String = js.native
    
    inline def dirname_=(x: String): Unit = js.Dynamic.global.updateDynamic("__dirname")(x.asInstanceOf[js.Any])
    
    // Same as module.exports
    @JSGlobal("exports")
    @js.native
    def exports: Any = js.native
    inline def exports_=(x: Any): Unit = js.Dynamic.global.updateDynamic("exports")(x.asInstanceOf[js.Any])
    
    inline def fetch(input: String): js.Promise[org.scalajs.dom.Response] = js.Dynamic.global.applyDynamic("fetch")(input.asInstanceOf[js.Any]).asInstanceOf[js.Promise[org.scalajs.dom.Response]]
    inline def fetch(input: String, init: org.scalajs.dom.RequestInit): js.Promise[org.scalajs.dom.Response] = (js.Dynamic.global.applyDynamic("fetch")(input.asInstanceOf[js.Any], init.asInstanceOf[js.Any])).asInstanceOf[js.Promise[org.scalajs.dom.Response]]
    inline def fetch(input: URL): js.Promise[org.scalajs.dom.Response] = js.Dynamic.global.applyDynamic("fetch")(input.asInstanceOf[js.Any]).asInstanceOf[js.Promise[org.scalajs.dom.Response]]
    inline def fetch(input: URL, init: org.scalajs.dom.RequestInit): js.Promise[org.scalajs.dom.Response] = (js.Dynamic.global.applyDynamic("fetch")(input.asInstanceOf[js.Any], init.asInstanceOf[js.Any])).asInstanceOf[js.Promise[org.scalajs.dom.Response]]
    inline def fetch(
      input: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify globalThis.Request */ Any
    ): js.Promise[org.scalajs.dom.Response] = js.Dynamic.global.applyDynamic("fetch")(input.asInstanceOf[js.Any]).asInstanceOf[js.Promise[org.scalajs.dom.Response]]
    inline def fetch(
      input: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify globalThis.Request */ Any,
      init: org.scalajs.dom.RequestInit
    ): js.Promise[org.scalajs.dom.Response] = (js.Dynamic.global.applyDynamic("fetch")(input.asInstanceOf[js.Any], init.asInstanceOf[js.Any])).asInstanceOf[js.Promise[org.scalajs.dom.Response]]
    
    @JSGlobal("__filename")
    @js.native
    def filename: String = js.native
    
    inline def filename_=(x: String): Unit = js.Dynamic.global.updateDynamic("__filename")(x.asInstanceOf[js.Any])
    
    /**
      * Only available if `--expose-gc` is passed to the process.
      */
    @JSGlobal("gc")
    @js.native
    def gc: js.UndefOr[js.Function0[Unit]] = js.native
    inline def gc_=(x: js.UndefOr[js.Function0[Unit]]): Unit = js.Dynamic.global.updateDynamic("gc")(x.asInstanceOf[js.Any])
    
    @JSGlobal("module")
    @js.native
    def module: NodeModule = js.native
    inline def module_=(x: NodeModule): Unit = js.Dynamic.global.updateDynamic("module")(x.asInstanceOf[js.Any])
    
    @JSGlobal("process")
    @js.native
    def process: Process = js.native
    inline def process_=(x: Process): Unit = js.Dynamic.global.updateDynamic("process")(x.asInstanceOf[js.Any])
    
    @JSGlobal("require")
    @js.native
    def require: NodeRequire = js.native
    inline def require_=(x: NodeRequire): Unit = js.Dynamic.global.updateDynamic("require")(x.asInstanceOf[js.Any])
    
    // #endregion ArrayLike.at() end
    /**
      * @since v17.0.0
      *
      * Creates a deep clone of an object.
      */
    inline def structuredClone[T](value: T): T = js.Dynamic.global.applyDynamic("structuredClone")(value.asInstanceOf[js.Any]).asInstanceOf[T]
    inline def structuredClone[T](value: T, transfer: Transfer): T = (js.Dynamic.global.applyDynamic("structuredClone")(value.asInstanceOf[js.Any], transfer.asInstanceOf[js.Any])).asInstanceOf[T]
    
    @js.native
    trait AsyncDisposable extends StObject
    
    type BigInt64Array = RelativeIndexable[js.BigInt]
    
    type BigUint64Array = RelativeIndexable[js.BigInt]
    
    @js.native
    trait Disposable extends StObject
    
    // Declare "static" methods in Error
    @js.native
    trait ErrorConstructor extends StObject {
      
      /** Create .stack property on a target object */
      def captureStackTrace(targetObject: js.Object): Unit = js.native
      def captureStackTrace(targetObject: js.Object, constructorOpt: js.Function): Unit = js.native
      
      /**
        * Optional override for formatting stack traces
        *
        * @see https://v8.dev/docs/stack-trace-api#customizing-stack-traces
        */
      var prepareStackTrace: js.UndefOr[js.Function2[/* err */ js.Error, /* stackTraces */ js.Array[CallSite], Any]] = js.native
      
      var stackTraceLimit: Double = js.native
    }
    
    /*----------------------------------------------*
      *                                               *
      *               GLOBAL INTERFACES               *
      *                                               *
      *-----------------------------------------------*/
    object NodeJS {
      
      type ArrayBufferView = TypedArray | js.typedarray.DataView
      
      trait CallSite extends StObject {
        
        /**
          * Current column number [if this function was defined in a script]
          */
        def getColumnNumber(): Double | Null
        
        def getEnclosingColumnNumber(): Double
        
        def getEnclosingLineNumber(): Double
        
        /**
          * A call site object representing the location where eval was called
          * [if this function was created using a call to eval]
          */
        def getEvalOrigin(): js.UndefOr[String]
        
        /**
          * Name of the script [if this function was defined in a script]
          */
        def getFileName(): js.UndefOr[String]
        
        /**
          * Current function
          */
        def getFunction(): js.UndefOr[js.Function]
        
        /**
          * Name of the current function, typically its name property.
          * If a name property is not available an attempt will be made to try
          * to infer a name from the function's context.
          */
        def getFunctionName(): String | Null
        
        /**
          * Current line number [if this function was defined in a script]
          */
        def getLineNumber(): Double | Null
        
        /**
          * Name of the property [of "this" or one of its prototypes] that holds
          * the current function
          */
        def getMethodName(): String | Null
        
        def getPosition(): Double
        
        /**
          * returns the index of the promise element that was followed in
          * Promise.all() or Promise.any() for async stack traces, or null
          * if the CallSite is not an async
          */
        def getPromiseIndex(): Double | Null
        
        def getScriptHash(): String
        
        def getScriptNameOrSourceURL(): String
        
        /**
          * Value of "this"
          */
        def getThis(): Any
        
        /**
          * Type of "this" as a string.
          * This is the name of the function stored in the constructor field of
          * "this", if available.  Otherwise the object's [[Class]] internal
          * property.
          */
        def getTypeName(): String | Null
        
        /**
          * is this an async call (i.e. await, Promise.all(), or Promise.any())?
          */
        def isAsync(): Boolean
        
        /**
          * Is this a constructor call?
          */
        def isConstructor(): Boolean
        
        /**
          * Does this call take place in code defined by a call to eval?
          */
        def isEval(): Boolean
        
        /**
          * Is this call in native V8 code?
          */
        def isNative(): Boolean
        
        /**
          * is this an async call to Promise.all()?
          */
        def isPromiseAll(): Boolean
        
        /**
          * Is this a toplevel invocation, that is, is "this" the global object?
          */
        def isToplevel(): Boolean
      }
      object CallSite {
        
        inline def apply(
          getColumnNumber: () => Double | Null,
          getEnclosingColumnNumber: () => Double,
          getEnclosingLineNumber: () => Double,
          getEvalOrigin: () => js.UndefOr[String],
          getFileName: () => js.UndefOr[String],
          getFunction: () => js.UndefOr[js.Function],
          getFunctionName: () => String | Null,
          getLineNumber: () => Double | Null,
          getMethodName: () => String | Null,
          getPosition: () => Double,
          getPromiseIndex: () => Double | Null,
          getScriptHash: () => String,
          getScriptNameOrSourceURL: () => String,
          getThis: () => Any,
          getTypeName: () => String | Null,
          isAsync: () => Boolean,
          isConstructor: () => Boolean,
          isEval: () => Boolean,
          isNative: () => Boolean,
          isPromiseAll: () => Boolean,
          isToplevel: () => Boolean
        ): CallSite = {
          val __obj = js.Dynamic.literal(getColumnNumber = js.Any.fromFunction0(getColumnNumber), getEnclosingColumnNumber = js.Any.fromFunction0(getEnclosingColumnNumber), getEnclosingLineNumber = js.Any.fromFunction0(getEnclosingLineNumber), getEvalOrigin = js.Any.fromFunction0(getEvalOrigin), getFileName = js.Any.fromFunction0(getFileName), getFunction = js.Any.fromFunction0(getFunction), getFunctionName = js.Any.fromFunction0(getFunctionName), getLineNumber = js.Any.fromFunction0(getLineNumber), getMethodName = js.Any.fromFunction0(getMethodName), getPosition = js.Any.fromFunction0(getPosition), getPromiseIndex = js.Any.fromFunction0(getPromiseIndex), getScriptHash = js.Any.fromFunction0(getScriptHash), getScriptNameOrSourceURL = js.Any.fromFunction0(getScriptNameOrSourceURL), getThis = js.Any.fromFunction0(getThis), getTypeName = js.Any.fromFunction0(getTypeName), isAsync = js.Any.fromFunction0(isAsync), isConstructor = js.Any.fromFunction0(isConstructor), isEval = js.Any.fromFunction0(isEval), isNative = js.Any.fromFunction0(isNative), isPromiseAll = js.Any.fromFunction0(isPromiseAll), isToplevel = js.Any.fromFunction0(isToplevel))
          __obj.asInstanceOf[CallSite]
        }
        
        @scala.inline
        implicit open class MutableBuilder[Self <: CallSite] (val x: Self) extends AnyVal {
          
          inline def setGetColumnNumber(value: () => Double | Null): Self = StObject.set(x, "getColumnNumber", js.Any.fromFunction0(value))
          
          inline def setGetEnclosingColumnNumber(value: () => Double): Self = StObject.set(x, "getEnclosingColumnNumber", js.Any.fromFunction0(value))
          
          inline def setGetEnclosingLineNumber(value: () => Double): Self = StObject.set(x, "getEnclosingLineNumber", js.Any.fromFunction0(value))
          
          inline def setGetEvalOrigin(value: () => js.UndefOr[String]): Self = StObject.set(x, "getEvalOrigin", js.Any.fromFunction0(value))
          
          inline def setGetFileName(value: () => js.UndefOr[String]): Self = StObject.set(x, "getFileName", js.Any.fromFunction0(value))
          
          inline def setGetFunction(value: () => js.UndefOr[js.Function]): Self = StObject.set(x, "getFunction", js.Any.fromFunction0(value))
          
          inline def setGetFunctionName(value: () => String | Null): Self = StObject.set(x, "getFunctionName", js.Any.fromFunction0(value))
          
          inline def setGetLineNumber(value: () => Double | Null): Self = StObject.set(x, "getLineNumber", js.Any.fromFunction0(value))
          
          inline def setGetMethodName(value: () => String | Null): Self = StObject.set(x, "getMethodName", js.Any.fromFunction0(value))
          
          inline def setGetPosition(value: () => Double): Self = StObject.set(x, "getPosition", js.Any.fromFunction0(value))
          
          inline def setGetPromiseIndex(value: () => Double | Null): Self = StObject.set(x, "getPromiseIndex", js.Any.fromFunction0(value))
          
          inline def setGetScriptHash(value: () => String): Self = StObject.set(x, "getScriptHash", js.Any.fromFunction0(value))
          
          inline def setGetScriptNameOrSourceURL(value: () => String): Self = StObject.set(x, "getScriptNameOrSourceURL", js.Any.fromFunction0(value))
          
          inline def setGetThis(value: () => Any): Self = StObject.set(x, "getThis", js.Any.fromFunction0(value))
          
          inline def setGetTypeName(value: () => String | Null): Self = StObject.set(x, "getTypeName", js.Any.fromFunction0(value))
          
          inline def setIsAsync(value: () => Boolean): Self = StObject.set(x, "isAsync", js.Any.fromFunction0(value))
          
          inline def setIsConstructor(value: () => Boolean): Self = StObject.set(x, "isConstructor", js.Any.fromFunction0(value))
          
          inline def setIsEval(value: () => Boolean): Self = StObject.set(x, "isEval", js.Any.fromFunction0(value))
          
          inline def setIsNative(value: () => Boolean): Self = StObject.set(x, "isNative", js.Any.fromFunction0(value))
          
          inline def setIsPromiseAll(value: () => Boolean): Self = StObject.set(x, "isPromiseAll", js.Any.fromFunction0(value))
          
          inline def setIsToplevel(value: () => Boolean): Self = StObject.set(x, "isToplevel", js.Any.fromFunction0(value))
        }
      }
      
      type Dict[T] = StringDictionary[js.UndefOr[T]]
      
      trait ErrnoException
        extends StObject
           with Error {
        
        var code: js.UndefOr[String] = js.undefined
        
        var errno: js.UndefOr[Double] = js.undefined
        
        var path: js.UndefOr[String] = js.undefined
        
        var syscall: js.UndefOr[String] = js.undefined
      }
      object ErrnoException {
        
        inline def apply(message: String, name: String): ErrnoException = {
          val __obj = js.Dynamic.literal(message = message.asInstanceOf[js.Any], name = name.asInstanceOf[js.Any])
          __obj.asInstanceOf[ErrnoException]
        }
        
        @scala.inline
        implicit open class MutableBuilder[Self <: ErrnoException] (val x: Self) extends AnyVal {
          
          inline def setCode(value: String): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
          
          inline def setCodeUndefined: Self = StObject.set(x, "code", js.undefined)
          
          inline def setErrno(value: Double): Self = StObject.set(x, "errno", value.asInstanceOf[js.Any])
          
          inline def setErrnoUndefined: Self = StObject.set(x, "errno", js.undefined)
          
          inline def setPath(value: String): Self = StObject.set(x, "path", value.asInstanceOf[js.Any])
          
          inline def setPathUndefined: Self = StObject.set(x, "path", js.undefined)
          
          inline def setSyscall(value: String): Self = StObject.set(x, "syscall", value.asInstanceOf[js.Any])
          
          inline def setSyscallUndefined: Self = StObject.set(x, "syscall", js.undefined)
        }
      }
      
      trait Module extends StObject {
        
        var children: js.Array[Module]
        
        var exports: Any
        
        var filename: String
        
        var id: String
        
        /**
          * `true` if the module is running during the Node.js preload
          */
        var isPreloading: Boolean
        
        var loaded: Boolean
        
        /** @deprecated since v14.6.0 Please use `require.main` and `module.children` instead. */
        var parent: js.UndefOr[Module | Null] = js.undefined
        
        /**
          * @since v11.14.0
          *
          * The directory name of the module. This is usually the same as the path.dirname() of the module.id.
          */
        var path: String
        
        var paths: js.Array[String]
        
        def require(id: String): Any
        @JSName("require")
        var require_Original: Require
      }
      object Module {
        
        inline def apply(
          children: js.Array[Module],
          exports: Any,
          filename: String,
          id: String,
          isPreloading: Boolean,
          loaded: Boolean,
          path: String,
          paths: js.Array[String],
          require: Require
        ): Module = {
          val __obj = js.Dynamic.literal(children = children.asInstanceOf[js.Any], exports = exports.asInstanceOf[js.Any], filename = filename.asInstanceOf[js.Any], id = id.asInstanceOf[js.Any], isPreloading = isPreloading.asInstanceOf[js.Any], loaded = loaded.asInstanceOf[js.Any], path = path.asInstanceOf[js.Any], paths = paths.asInstanceOf[js.Any], require = require.asInstanceOf[js.Any])
          __obj.asInstanceOf[Module]
        }
        
        @scala.inline
        implicit open class MutableBuilder[Self <: Module] (val x: Self) extends AnyVal {
          
          inline def setChildren(value: js.Array[Module]): Self = StObject.set(x, "children", value.asInstanceOf[js.Any])
          
          inline def setChildrenVarargs(value: Module*): Self = StObject.set(x, "children", js.Array(value*))
          
          inline def setExports(value: Any): Self = StObject.set(x, "exports", value.asInstanceOf[js.Any])
          
          inline def setFilename(value: String): Self = StObject.set(x, "filename", value.asInstanceOf[js.Any])
          
          inline def setId(value: String): Self = StObject.set(x, "id", value.asInstanceOf[js.Any])
          
          inline def setIsPreloading(value: Boolean): Self = StObject.set(x, "isPreloading", value.asInstanceOf[js.Any])
          
          inline def setLoaded(value: Boolean): Self = StObject.set(x, "loaded", value.asInstanceOf[js.Any])
          
          inline def setParent(value: Module): Self = StObject.set(x, "parent", value.asInstanceOf[js.Any])
          
          inline def setParentNull: Self = StObject.set(x, "parent", null)
          
          inline def setParentUndefined: Self = StObject.set(x, "parent", js.undefined)
          
          inline def setPath(value: String): Self = StObject.set(x, "path", value.asInstanceOf[js.Any])
          
          inline def setPaths(value: js.Array[String]): Self = StObject.set(x, "paths", value.asInstanceOf[js.Any])
          
          inline def setPathsVarargs(value: String*): Self = StObject.set(x, "paths", js.Array(value*))
          
          inline def setRequire(value: Require): Self = StObject.set(x, "require", value.asInstanceOf[js.Any])
        }
      }
      
      type ReadOnlyDict[T] = StringDictionary[js.UndefOr[T]]
      
      @js.native
      trait ReadWriteStream
        extends StObject
           with ReadableStream
           with WritableStream
      
      @js.native
      trait ReadableStream
        extends StObject
           with EventEmitter[DefaultEventMap] {
        
        def isPaused(): Boolean = js.native
        
        def pause(): this.type = js.native
        
        def pipe[T /* <: WritableStream */](destination: T): T = js.native
        def pipe[T /* <: WritableStream */](destination: T, options: End): T = js.native
        
        def read(): String | Buffer = js.native
        def read(size: Double): String | Buffer = js.native
        
        var readable: Boolean = js.native
        
        def resume(): this.type = js.native
        
        def setEncoding(encoding: BufferEncoding): this.type = js.native
        
        def unpipe(): this.type = js.native
        def unpipe(destination: WritableStream): this.type = js.native
        
        def unshift(chunk: String): Unit = js.native
        def unshift(chunk: String, encoding: BufferEncoding): Unit = js.native
        def unshift(chunk: js.typedarray.Uint8Array): Unit = js.native
        def unshift(chunk: js.typedarray.Uint8Array, encoding: BufferEncoding): Unit = js.native
        
        def wrap(oldStream: ReadableStream): this.type = js.native
      }
      
      trait RefCounted extends StObject {
        
        def ref(): this.type
        
        def unref(): this.type
      }
      object RefCounted {
        
        inline def apply(ref: () => RefCounted, unref: () => RefCounted): RefCounted = {
          val __obj = js.Dynamic.literal(ref = js.Any.fromFunction0(ref), unref = js.Any.fromFunction0(unref))
          __obj.asInstanceOf[RefCounted]
        }
        
        @scala.inline
        implicit open class MutableBuilder[Self <: RefCounted] (val x: Self) extends AnyVal {
          
          inline def setRef(value: () => RefCounted): Self = StObject.set(x, "ref", js.Any.fromFunction0(value))
          
          inline def setUnref(value: () => RefCounted): Self = StObject.set(x, "unref", js.Any.fromFunction0(value))
        }
      }
      
      @js.native
      trait Require extends StObject {
        
        def apply(id: String): Any = js.native
        
        var cache: Dict[NodeModule] = js.native
        
        /**
          * @deprecated
          */
        var extensions: RequireExtensions = js.native
        
        var main: js.UndefOr[Module] = js.native
        
        def resolve(id: String): String = js.native
        def resolve(id: String, options: Paths): String = js.native
        @JSName("resolve")
        var resolve_Original: typings.node.globalsMod.global.NodeJS.RequireResolve = js.native
      }
      
      trait RequireExtensions
        extends StObject
           with Dict[js.Function2[/* m */ Module, /* filename */ String, Any]] {
        
        @JSName(".js")
        def Dotjs(m: Module, filename: String): Any
        
        @JSName(".json")
        def Dotjson(m: Module, filename: String): Any
        
        @JSName(".node")
        def Dotnode(m: Module, filename: String): Any
      }
      object RequireExtensions {
        
        inline def apply(Dotjs: (Module, String) => Any, Dotjson: (Module, String) => Any, Dotnode: (Module, String) => Any): RequireExtensions = {
          val __obj = js.Dynamic.literal()
          __obj.updateDynamic(".js")(js.Any.fromFunction2(Dotjs))
          __obj.updateDynamic(".json")(js.Any.fromFunction2(Dotjson))
          __obj.updateDynamic(".node")(js.Any.fromFunction2(Dotnode))
          __obj.asInstanceOf[RequireExtensions]
        }
        
        @scala.inline
        implicit open class MutableBuilder[Self <: RequireExtensions] (val x: Self) extends AnyVal {
          
          inline def setDotjs(value: (Module, String) => Any): Self = StObject.set(x, ".js", js.Any.fromFunction2(value))
          
          inline def setDotjson(value: (Module, String) => Any): Self = StObject.set(x, ".json", js.Any.fromFunction2(value))
          
          inline def setDotnode(value: (Module, String) => Any): Self = StObject.set(x, ".node", js.Any.fromFunction2(value))
        }
      }
      
      @js.native
      trait RequireResolve extends StObject {
        
        def apply(id: String): String = js.native
        def apply(id: String, options: Paths): String = js.native
        
        def paths(request: String): js.Array[String] | Null = js.native
      }
      
      type TypedArray = js.typedarray.Uint8Array | js.typedarray.Uint8ClampedArray | js.typedarray.Uint16Array | js.typedarray.Uint32Array | js.typedarray.Int8Array | js.typedarray.Int16Array | js.typedarray.Int32Array | BigUint64Array | BigInt64Array | js.typedarray.Float32Array | js.typedarray.Float64Array
      
      @js.native
      trait WritableStream
        extends StObject
           with EventEmitter[DefaultEventMap] {
        
        def end(): this.type = js.native
        def end(cb: js.Function0[Unit]): this.type = js.native
        def end(data: String): this.type = js.native
        def end(data: String, cb: js.Function0[Unit]): this.type = js.native
        def end(data: js.typedarray.Uint8Array): this.type = js.native
        def end(data: js.typedarray.Uint8Array, cb: js.Function0[Unit]): this.type = js.native
        def end(str: String, encoding: Unit, cb: js.Function0[Unit]): this.type = js.native
        def end(str: String, encoding: BufferEncoding): this.type = js.native
        def end(str: String, encoding: BufferEncoding, cb: js.Function0[Unit]): this.type = js.native
        
        var writable: Boolean = js.native
        
        def write(buffer: String): Boolean = js.native
        def write(buffer: String, cb: js.Function1[/* err */ js.UndefOr[js.Error | Null], Unit]): Boolean = js.native
        def write(buffer: js.typedarray.Uint8Array): Boolean = js.native
        def write(buffer: js.typedarray.Uint8Array, cb: js.Function1[/* err */ js.UndefOr[js.Error | Null], Unit]): Boolean = js.native
        def write(str: String, encoding: Unit, cb: js.Function1[/* err */ js.UndefOr[js.Error | Null], Unit]): Boolean = js.native
        def write(str: String, encoding: BufferEncoding): Boolean = js.native
        def write(
          str: String,
          encoding: BufferEncoding,
          cb: js.Function1[/* err */ js.UndefOr[js.Error | Null], Unit]
        ): Boolean = js.native
      }
    }
    
    type NodeModule = Module
    
    /*-----------------------------------------------*
      *                                               *
      *                   GLOBAL                      *
      *                                               *
      ------------------------------------------------*/
    // For backwards compability
    type NodeRequire = Require
    
    // #endregion Disposable
    // #region ArrayLike.at()
    trait RelativeIndexable[T] extends StObject {
      
      /**
        * Takes an integer value and returns the item at that index,
        * allowing for positive and negative integers.
        * Negative integers count back from the last item in the array.
        */
      def at(index: Double): js.UndefOr[T]
    }
    object RelativeIndexable {
      
      inline def apply[T](at: Double => js.UndefOr[T]): RelativeIndexable[T] = {
        val __obj = js.Dynamic.literal(at = js.Any.fromFunction1(at))
        __obj.asInstanceOf[RelativeIndexable[T]]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: RelativeIndexable[?], T] (val x: Self & RelativeIndexable[T]) extends AnyVal {
        
        inline def setAt(value: Double => js.UndefOr[T]): Self = StObject.set(x, "at", js.Any.fromFunction1(value))
      }
    }
    
    type RequireResolve = typings.node.globalsMod.global.NodeJS.RequireResolve
    
    // #endregion borrowed
    // #region Disposable
    trait SymbolConstructor extends StObject {
      
      /**
        * A method that is used to asynchronously release resources held by an object. Called by the semantics of the `await using` statement.
        */
      val asyncDispose: js.Symbol
      
      /**
        * A method that is used to release resources held by an object. Called by the semantics of the `using` statement.
        */
      val dispose: js.Symbol
    }
    object SymbolConstructor {
      
      inline def apply(asyncDispose: js.Symbol, dispose: js.Symbol): SymbolConstructor = {
        val __obj = js.Dynamic.literal(asyncDispose = asyncDispose.asInstanceOf[js.Any], dispose = dispose.asInstanceOf[js.Any])
        __obj.asInstanceOf[SymbolConstructor]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: SymbolConstructor] (val x: Self) extends AnyVal {
        
        inline def setAsyncDispose(value: js.Symbol): Self = StObject.set(x, "asyncDispose", value.asInstanceOf[js.Any])
        
        inline def setDispose(value: js.Symbol): Self = StObject.set(x, "dispose", value.asInstanceOf[js.Any])
      }
    }
  }
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types/eventsource.EventSource
    }}}
    */
  type EventSource = typings.undiciTypes.eventsourceMod.EventSource
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : node.node:buffer.File
    }}}
    */
  type File = typings.node.nodeColonbufferMod.File
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types.FormData
    }}}
    */
  type FormData = typings.undiciTypes.mod.FormData
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types.Headers
    }}}
    */
  type Headers = typings.undiciTypes.mod.Headers
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types/websocket.MessageEvent<any>
    }}}
    */
  type MessageEvent = typings.undiciTypes.websocketMod.MessageEvent[Any]
  
  // Make this a module
  // #region Fetch and friends
  // Conditional type aliases, used at the end of this file.
  // Will either be empty if lib-dom is included, or the undici version otherwise.
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types.Request
    }}}
    */
  type Request = Request_
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types/fetch.RequestInit
    }}}
    */
  type RequestInit = typings.undiciTypes.fetchMod.RequestInit
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types.Response
    }}}
    */
  type Response = typings.undiciTypes.mod.Response
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types/fetch.ResponseInit
    }}}
    */
  type ResponseInit = typings.undiciTypes.fetchMod.ResponseInit
  
  /** NOTE: Conditional type definitions are impossible to translate to Scala.
    * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
    * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
    * TS definition: {{{
    / * globalThis * / any extends {  onmessage :any} ? {} : undici-types.undici-types/websocket.WebSocket
    }}}
    */
  type WebSocket = typings.undiciTypes.websocketMod.WebSocket
}
