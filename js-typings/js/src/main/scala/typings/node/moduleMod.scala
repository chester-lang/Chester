package typings.node

import org.scalablytyped.runtime.Instantiable2
import org.scalablytyped.runtime.Shortcut
import typings.node.globalsMod.global.NodeJS.Dict
import typings.node.globalsMod.global.NodeJS.Require
import typings.node.globalsMod.global.NodeJS.TypedArray
import typings.node.globalsMod.global.NodeModule
import typings.node.globalsMod.global.NodeRequire
import typings.node.nodeColonurlMod.URL
import typings.node.nodeColonworkerThreadsMod.MessagePort
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object moduleMod extends Shortcut {
  
  @JSImport("module", JSImport.Namespace)
  @js.native
  open class ^ protected ()
    extends StObject
       with typings.node.globalsMod.global.NodeJS.Module {
    def this(id: String) = this()
    def this(id: String, parent: Module) = this()
    
    /* CompleteClass */
    var children: js.Array[typings.node.globalsMod.global.NodeJS.Module] = js.native
    
    /* CompleteClass */
    var exports: Any = js.native
    
    /* CompleteClass */
    var filename: String = js.native
    
    /* CompleteClass */
    var id: String = js.native
    
    /**
      * `true` if the module is running during the Node.js preload
      */
    /* CompleteClass */
    var isPreloading: Boolean = js.native
    
    /* CompleteClass */
    var loaded: Boolean = js.native
    
    /**
      * @since v11.14.0
      *
      * The directory name of the module. This is usually the same as the path.dirname() of the module.id.
      */
    /* CompleteClass */
    var path: String = js.native
    
    /* CompleteClass */
    var paths: js.Array[String] = js.native
    
    /* CompleteClass */
    override def require(id: String): Any = js.native
    /* CompleteClass */
    @JSName("require")
    var require_Original: Require = js.native
  }
  @JSImport("module", JSImport.Namespace)
  @js.native
  val ^ : js.Object & (Instantiable2[/* id */ String, /* parent */ js.UndefOr[Module], Module]) = js.native
  
  /* static member */
  @JSImport("module", "Module")
  @js.native
  def Module: Instantiable2[/* id */ String, /* parent */ js.UndefOr[Module], Module] = js.native
  type Module = NodeModule
  
  /* This class was inferred from a value with a constructor, it was renamed because a distinct type already exists with the same name. */
  @JSImport("module", "Module")
  @js.native
  open class ModuleCls protected ()
    extends StObject
       with typings.node.globalsMod.global.NodeJS.Module {
    def this(id: String) = this()
    def this(id: String, parent: Module) = this()
    
    /* CompleteClass */
    var children: js.Array[typings.node.globalsMod.global.NodeJS.Module] = js.native
    
    /* CompleteClass */
    var exports: Any = js.native
    
    /* CompleteClass */
    var filename: String = js.native
    
    /* CompleteClass */
    var id: String = js.native
    
    /**
      * `true` if the module is running during the Node.js preload
      */
    /* CompleteClass */
    var isPreloading: Boolean = js.native
    
    /* CompleteClass */
    var loaded: Boolean = js.native
    
    /**
      * @since v11.14.0
      *
      * The directory name of the module. This is usually the same as the path.dirname() of the module.id.
      */
    /* CompleteClass */
    var path: String = js.native
    
    /* CompleteClass */
    var paths: js.Array[String] = js.native
    
    /* CompleteClass */
    override def require(id: String): Any = js.native
    /* CompleteClass */
    @JSName("require")
    var require_Original: Require = js.native
  }
  
  inline def Module_=(x: Instantiable2[/* id */ String, /* parent */ js.UndefOr[Module], Module]): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("Module")(x.asInstanceOf[js.Any])
  
  /**
    * @since v13.7.0, v12.17.0
    */
  @JSImport("module", "SourceMap")
  @js.native
  open class SourceMap protected () extends StObject {
    def this(payload: SourceMapPayload) = this()
    
    /**
      * Given a line offset and column offset in the generated source
      * file, returns an object representing the SourceMap range in the
      * original file if found, or an empty object if not.
      *
      * The object returned contains the following keys:
      *
      * The returned value represents the raw range as it appears in the
      * SourceMap, based on zero-indexed offsets, _not_ 1-indexed line and
      * column numbers as they appear in Error messages and CallSite
      * objects.
      *
      * To get the corresponding 1-indexed line and column numbers from a
      * lineNumber and columnNumber as they are reported by Error stacks
      * and CallSite objects, use `sourceMap.findOrigin(lineNumber, columnNumber)`
      * @param lineOffset The zero-indexed line number offset in the generated source
      * @param columnOffset The zero-indexed column number offset in the generated source
      */
    def findEntry(lineOffset: Double, columnOffset: Double): SourceMapping = js.native
    
    /**
      * Given a 1-indexed `lineNumber` and `columnNumber` from a call site in the generated source,
      * find the corresponding call site location in the original source.
      *
      * If the `lineNumber` and `columnNumber` provided are not found in any source map,
      * then an empty object is returned.
      * @param lineNumber The 1-indexed line number of the call site in the generated source
      * @param columnNumber The 1-indexed column number of the call site in the generated source
      */
    def findOrigin(lineNumber: Double, columnNumber: Double): SourceOrigin | js.Object = js.native
    
    /**
      * Getter for the payload used to construct the `SourceMap` instance.
      */
    val payload: SourceMapPayload = js.native
  }
  
  /* static member */
  @JSImport("module", "builtinModules")
  @js.native
  def builtinModules: js.Array[String] = js.native
  inline def builtinModules_=(x: js.Array[String]): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("builtinModules")(x.asInstanceOf[js.Any])
  
  /* static member */
  inline def createRequire(path: String): NodeRequire = ^.asInstanceOf[js.Dynamic].applyDynamic("createRequire")(path.asInstanceOf[js.Any]).asInstanceOf[NodeRequire]
  inline def createRequire(path: URL): NodeRequire = ^.asInstanceOf[js.Dynamic].applyDynamic("createRequire")(path.asInstanceOf[js.Any]).asInstanceOf[NodeRequire]
  
  /**
    * `path` is the resolved path for the file for which a corresponding source map
    * should be fetched.
    * @since v13.7.0, v12.17.0
    * @return Returns `module.SourceMap` if a source map is found, `undefined` otherwise.
    */
  inline def findSourceMap(path: String): SourceMap = ^.asInstanceOf[js.Dynamic].applyDynamic("findSourceMap")(path.asInstanceOf[js.Any]).asInstanceOf[SourceMap]
  inline def findSourceMap(path: String, error: js.Error): SourceMap = (^.asInstanceOf[js.Dynamic].applyDynamic("findSourceMap")(path.asInstanceOf[js.Any], error.asInstanceOf[js.Any])).asInstanceOf[SourceMap]
  
  /* static member */
  inline def isBuiltin(moduleName: String): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isBuiltin")(moduleName.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  /* static member */
  inline def register[Data](specifier: String): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any]).asInstanceOf[Unit]
  inline def register[Data](specifier: String, options: RegisterOptions[Data]): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: String, parentURL: String): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: String, parentURL: String, options: RegisterOptions[Data]): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: String, parentURL: Unit, options: RegisterOptions[Data]): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: String, parentURL: URL): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: String, parentURL: URL, options: RegisterOptions[Data]): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: URL): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any]).asInstanceOf[Unit]
  inline def register[Data](specifier: URL, options: RegisterOptions[Data]): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: URL, parentURL: String): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: URL, parentURL: String, options: RegisterOptions[Data]): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: URL, parentURL: Unit, options: RegisterOptions[Data]): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: URL, parentURL: URL): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any])).asInstanceOf[Unit]
  inline def register[Data](specifier: URL, parentURL: URL, options: RegisterOptions[Data]): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("register")(specifier.asInstanceOf[js.Any], parentURL.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[Unit]
  
  /* static member */
  inline def runMain(): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("runMain")().asInstanceOf[Unit]
  
  /**
    * The `module.syncBuiltinESMExports()` method updates all the live bindings for
    * builtin `ES Modules` to match the properties of the `CommonJS` exports. It
    * does not add or remove exported names from the `ES Modules`.
    *
    * ```js
    * const fs = require('node:fs');
    * const assert = require('node:assert');
    * const { syncBuiltinESMExports } = require('node:module');
    *
    * fs.readFile = newAPI;
    *
    * delete fs.readFileSync;
    *
    * function newAPI() {
    *   // ...
    * }
    *
    * fs.newAPI = newAPI;
    *
    * syncBuiltinESMExports();
    *
    * import('node:fs').then((esmFS) => {
    *   // It syncs the existing readFile property with the new value
    *   assert.strictEqual(esmFS.readFile, newAPI);
    *   // readFileSync has been deleted from the required fs
    *   assert.strictEqual('readFileSync' in fs, false);
    *   // syncBuiltinESMExports() does not remove readFileSync from esmFS
    *   assert.strictEqual('readFileSync' in esmFS, true);
    *   // syncBuiltinESMExports() does not add names
    *   assert.strictEqual(esmFS.newAPI, undefined);
    * });
    * ```
    * @since v12.12.0
    */
  inline def syncBuiltinESMExports(): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("syncBuiltinESMExports")().asInstanceOf[Unit]
  
  /* static member */
  inline def wrap(code: String): String = ^.asInstanceOf[js.Dynamic].applyDynamic("wrap")(code.asInstanceOf[js.Any]).asInstanceOf[String]
  
  trait GlobalPreloadContext extends StObject {
    
    var port: MessagePort
  }
  object GlobalPreloadContext {
    
    inline def apply(port: MessagePort): GlobalPreloadContext = {
      val __obj = js.Dynamic.literal(port = port.asInstanceOf[js.Any])
      __obj.asInstanceOf[GlobalPreloadContext]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: GlobalPreloadContext] (val x: Self) extends AnyVal {
      
      inline def setPort(value: MessagePort): Self = StObject.set(x, "port", value.asInstanceOf[js.Any])
    }
  }
  
  /**
    * @deprecated This hook will be removed in a future version.
    * Use `initialize` instead. When a loader has an `initialize` export, `globalPreload` will be ignored.
    *
    * Sometimes it might be necessary to run some code inside of the same global scope that the application runs in.
    * This hook allows the return of a string that is run as a sloppy-mode script on startup.
    *
    * @param context Information to assist the preload code
    * @return Code to run before application startup
    */
  type GlobalPreloadHook = js.Function1[/* context */ GlobalPreloadContext, String]
  
  trait ImportAttributes
    extends StObject
       with Dict[String] {
    
    var `type`: js.UndefOr[String] = js.undefined
  }
  object ImportAttributes {
    
    inline def apply(): ImportAttributes = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[ImportAttributes]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: ImportAttributes] (val x: Self) extends AnyVal {
      
      inline def setType(value: String): Self = StObject.set(x, "type", value.asInstanceOf[js.Any])
      
      inline def setTypeUndefined: Self = StObject.set(x, "type", js.undefined)
    }
  }
  
  /**
    * The `initialize` hook provides a way to define a custom function that runs in the hooks thread
    * when the hooks module is initialized. Initialization happens when the hooks module is registered via `register`.
    *
    * This hook can receive data from a `register` invocation, including ports and other transferrable objects.
    * The return value of `initialize` can be a `Promise`, in which case it will be awaited before the main application thread execution resumes.
    */
  type InitializeHook[Data] = js.Function1[/* data */ Data, Unit | js.Promise[Unit]]
  
  trait LoadFnOutput extends StObject {
    
    var format: ModuleFormat
    
    /**
      * A signal that this hook intends to terminate the chain of `resolve` hooks.
      * @default false
      */
    var shortCircuit: js.UndefOr[Boolean] = js.undefined
    
    /**
      * The source for Node.js to evaluate
      */
    var source: js.UndefOr[ModuleSource] = js.undefined
  }
  object LoadFnOutput {
    
    inline def apply(format: ModuleFormat): LoadFnOutput = {
      val __obj = js.Dynamic.literal(format = format.asInstanceOf[js.Any])
      __obj.asInstanceOf[LoadFnOutput]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: LoadFnOutput] (val x: Self) extends AnyVal {
      
      inline def setFormat(value: ModuleFormat): Self = StObject.set(x, "format", value.asInstanceOf[js.Any])
      
      inline def setShortCircuit(value: Boolean): Self = StObject.set(x, "shortCircuit", value.asInstanceOf[js.Any])
      
      inline def setShortCircuitUndefined: Self = StObject.set(x, "shortCircuit", js.undefined)
      
      inline def setSource(value: ModuleSource): Self = StObject.set(x, "source", value.asInstanceOf[js.Any])
      
      inline def setSourceUndefined: Self = StObject.set(x, "source", js.undefined)
    }
  }
  
  /**
    * The `load` hook provides a way to define a custom method of determining how a URL should be interpreted, retrieved, and parsed.
    * It is also in charge of validating the import assertion.
    *
    * @param url The URL/path of the module to be loaded
    * @param context Metadata about the module
    * @param nextLoad The subsequent `load` hook in the chain, or the Node.js default `load` hook after the last user-supplied `load` hook
    */
  type LoadHook = js.Function3[
    /* url */ String, 
    /* context */ LoadHookContext, 
    /* nextLoad */ js.Function2[
      /* url */ String, 
      /* context */ js.UndefOr[LoadHookContext], 
      LoadFnOutput | js.Promise[LoadFnOutput]
    ], 
    LoadFnOutput | js.Promise[LoadFnOutput]
  ]
  
  trait LoadHookContext extends StObject {
    
    /**
      * Export conditions of the relevant `package.json`
      */
    var conditions: js.Array[String]
    
    /**
      * The format optionally supplied by the `resolve` hook chain
      */
    var format: ModuleFormat
    
    /**
      *  An object whose key-value pairs represent the assertions for the module to import
      */
    var importAttributes: ImportAttributes
  }
  object LoadHookContext {
    
    inline def apply(conditions: js.Array[String], format: ModuleFormat, importAttributes: ImportAttributes): LoadHookContext = {
      val __obj = js.Dynamic.literal(conditions = conditions.asInstanceOf[js.Any], format = format.asInstanceOf[js.Any], importAttributes = importAttributes.asInstanceOf[js.Any])
      __obj.asInstanceOf[LoadHookContext]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: LoadHookContext] (val x: Self) extends AnyVal {
      
      inline def setConditions(value: js.Array[String]): Self = StObject.set(x, "conditions", value.asInstanceOf[js.Any])
      
      inline def setConditionsVarargs(value: String*): Self = StObject.set(x, "conditions", js.Array(value*))
      
      inline def setFormat(value: ModuleFormat): Self = StObject.set(x, "format", value.asInstanceOf[js.Any])
      
      inline def setImportAttributes(value: ImportAttributes): Self = StObject.set(x, "importAttributes", value.asInstanceOf[js.Any])
    }
  }
  
  /* Rewritten from type alias, can be one of: 
    - typings.node.nodeStrings.builtin
    - typings.node.nodeStrings.commonjs
    - typings.node.nodeStrings.json
    - typings.node.nodeStrings.module
    - typings.node.nodeStrings.wasm
  */
  trait ModuleFormat extends StObject
  object ModuleFormat {
    
    inline def builtin: typings.node.nodeStrings.builtin = "builtin".asInstanceOf[typings.node.nodeStrings.builtin]
    
    inline def commonjs: typings.node.nodeStrings.commonjs = "commonjs".asInstanceOf[typings.node.nodeStrings.commonjs]
    
    inline def json: typings.node.nodeStrings.json = "json".asInstanceOf[typings.node.nodeStrings.json]
    
    inline def module: typings.node.nodeStrings.module = "module".asInstanceOf[typings.node.nodeStrings.module]
    
    inline def wasm: typings.node.nodeStrings.wasm = "wasm".asInstanceOf[typings.node.nodeStrings.wasm]
  }
  
  type ModuleSource = String | js.typedarray.ArrayBuffer | TypedArray
  
  trait RegisterOptions[Data] extends StObject {
    
    var data: js.UndefOr[Data] = js.undefined
    
    var parentURL: String | org.scalajs.dom.URL
    
    var transferList: js.UndefOr[js.Array[Any]] = js.undefined
  }
  object RegisterOptions {
    
    inline def apply[Data](parentURL: String | org.scalajs.dom.URL): RegisterOptions[Data] = {
      val __obj = js.Dynamic.literal(parentURL = parentURL.asInstanceOf[js.Any])
      __obj.asInstanceOf[RegisterOptions[Data]]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: RegisterOptions[?], Data] (val x: Self & RegisterOptions[Data]) extends AnyVal {
      
      inline def setData(value: Data): Self = StObject.set(x, "data", value.asInstanceOf[js.Any])
      
      inline def setDataUndefined: Self = StObject.set(x, "data", js.undefined)
      
      inline def setParentURL(value: String | org.scalajs.dom.URL): Self = StObject.set(x, "parentURL", value.asInstanceOf[js.Any])
      
      inline def setTransferList(value: js.Array[Any]): Self = StObject.set(x, "transferList", value.asInstanceOf[js.Any])
      
      inline def setTransferListUndefined: Self = StObject.set(x, "transferList", js.undefined)
      
      inline def setTransferListVarargs(value: Any*): Self = StObject.set(x, "transferList", js.Array(value*))
    }
  }
  
  trait ResolveFnOutput extends StObject {
    
    /**
      * A hint to the load hook (it might be ignored)
      */
    var format: js.UndefOr[ModuleFormat | Null] = js.undefined
    
    /**
      * The import attributes to use when caching the module (optional; if excluded the input will be used)
      */
    var importAttributes: js.UndefOr[ImportAttributes] = js.undefined
    
    /**
      * A signal that this hook intends to terminate the chain of `resolve` hooks.
      * @default false
      */
    var shortCircuit: js.UndefOr[Boolean] = js.undefined
    
    /**
      * The absolute URL to which this input resolves
      */
    var url: String
  }
  object ResolveFnOutput {
    
    inline def apply(url: String): ResolveFnOutput = {
      val __obj = js.Dynamic.literal(url = url.asInstanceOf[js.Any])
      __obj.asInstanceOf[ResolveFnOutput]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: ResolveFnOutput] (val x: Self) extends AnyVal {
      
      inline def setFormat(value: ModuleFormat): Self = StObject.set(x, "format", value.asInstanceOf[js.Any])
      
      inline def setFormatNull: Self = StObject.set(x, "format", null)
      
      inline def setFormatUndefined: Self = StObject.set(x, "format", js.undefined)
      
      inline def setImportAttributes(value: ImportAttributes): Self = StObject.set(x, "importAttributes", value.asInstanceOf[js.Any])
      
      inline def setImportAttributesUndefined: Self = StObject.set(x, "importAttributes", js.undefined)
      
      inline def setShortCircuit(value: Boolean): Self = StObject.set(x, "shortCircuit", value.asInstanceOf[js.Any])
      
      inline def setShortCircuitUndefined: Self = StObject.set(x, "shortCircuit", js.undefined)
      
      inline def setUrl(value: String): Self = StObject.set(x, "url", value.asInstanceOf[js.Any])
    }
  }
  
  /**
    * The `resolve` hook chain is responsible for resolving file URL for a given module specifier and parent URL, and optionally its format (such as `'module'`) as a hint to the `load` hook.
    * If a format is specified, the load hook is ultimately responsible for providing the final `format` value (and it is free to ignore the hint provided by `resolve`);
    * if `resolve` provides a format, a custom `load` hook is required even if only to pass the value to the Node.js default `load` hook.
    *
    * @param specifier The specified URL path of the module to be resolved
    * @param context
    * @param nextResolve The subsequent `resolve` hook in the chain, or the Node.js default `resolve` hook after the last user-supplied resolve hook
    */
  type ResolveHook = js.Function3[
    /* specifier */ String, 
    /* context */ ResolveHookContext, 
    /* nextResolve */ js.Function2[
      /* specifier */ String, 
      /* context */ js.UndefOr[ResolveHookContext], 
      ResolveFnOutput | js.Promise[ResolveFnOutput]
    ], 
    ResolveFnOutput | js.Promise[ResolveFnOutput]
  ]
  
  trait ResolveHookContext extends StObject {
    
    /**
      * Export conditions of the relevant `package.json`
      */
    var conditions: js.Array[String]
    
    /**
      *  An object whose key-value pairs represent the assertions for the module to import
      */
    var importAttributes: ImportAttributes
    
    /**
      * The module importing this one, or undefined if this is the Node.js entry point
      */
    var parentURL: js.UndefOr[String] = js.undefined
  }
  object ResolveHookContext {
    
    inline def apply(conditions: js.Array[String], importAttributes: ImportAttributes): ResolveHookContext = {
      val __obj = js.Dynamic.literal(conditions = conditions.asInstanceOf[js.Any], importAttributes = importAttributes.asInstanceOf[js.Any])
      __obj.asInstanceOf[ResolveHookContext]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: ResolveHookContext] (val x: Self) extends AnyVal {
      
      inline def setConditions(value: js.Array[String]): Self = StObject.set(x, "conditions", value.asInstanceOf[js.Any])
      
      inline def setConditionsVarargs(value: String*): Self = StObject.set(x, "conditions", js.Array(value*))
      
      inline def setImportAttributes(value: ImportAttributes): Self = StObject.set(x, "importAttributes", value.asInstanceOf[js.Any])
      
      inline def setParentURL(value: String): Self = StObject.set(x, "parentURL", value.asInstanceOf[js.Any])
      
      inline def setParentURLUndefined: Self = StObject.set(x, "parentURL", js.undefined)
    }
  }
  
  trait SourceMapPayload extends StObject {
    
    var file: String
    
    var mappings: String
    
    var names: js.Array[String]
    
    var sourceRoot: String
    
    var sources: js.Array[String]
    
    var sourcesContent: js.Array[String]
    
    var version: Double
  }
  object SourceMapPayload {
    
    inline def apply(
      file: String,
      mappings: String,
      names: js.Array[String],
      sourceRoot: String,
      sources: js.Array[String],
      sourcesContent: js.Array[String],
      version: Double
    ): SourceMapPayload = {
      val __obj = js.Dynamic.literal(file = file.asInstanceOf[js.Any], mappings = mappings.asInstanceOf[js.Any], names = names.asInstanceOf[js.Any], sourceRoot = sourceRoot.asInstanceOf[js.Any], sources = sources.asInstanceOf[js.Any], sourcesContent = sourcesContent.asInstanceOf[js.Any], version = version.asInstanceOf[js.Any])
      __obj.asInstanceOf[SourceMapPayload]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: SourceMapPayload] (val x: Self) extends AnyVal {
      
      inline def setFile(value: String): Self = StObject.set(x, "file", value.asInstanceOf[js.Any])
      
      inline def setMappings(value: String): Self = StObject.set(x, "mappings", value.asInstanceOf[js.Any])
      
      inline def setNames(value: js.Array[String]): Self = StObject.set(x, "names", value.asInstanceOf[js.Any])
      
      inline def setNamesVarargs(value: String*): Self = StObject.set(x, "names", js.Array(value*))
      
      inline def setSourceRoot(value: String): Self = StObject.set(x, "sourceRoot", value.asInstanceOf[js.Any])
      
      inline def setSources(value: js.Array[String]): Self = StObject.set(x, "sources", value.asInstanceOf[js.Any])
      
      inline def setSourcesContent(value: js.Array[String]): Self = StObject.set(x, "sourcesContent", value.asInstanceOf[js.Any])
      
      inline def setSourcesContentVarargs(value: String*): Self = StObject.set(x, "sourcesContent", js.Array(value*))
      
      inline def setSourcesVarargs(value: String*): Self = StObject.set(x, "sources", js.Array(value*))
      
      inline def setVersion(value: Double): Self = StObject.set(x, "version", value.asInstanceOf[js.Any])
    }
  }
  
  trait SourceMapping extends StObject {
    
    var generatedColumn: Double
    
    var generatedLine: Double
    
    var originalColumn: Double
    
    var originalLine: Double
    
    var originalSource: String
  }
  object SourceMapping {
    
    inline def apply(
      generatedColumn: Double,
      generatedLine: Double,
      originalColumn: Double,
      originalLine: Double,
      originalSource: String
    ): SourceMapping = {
      val __obj = js.Dynamic.literal(generatedColumn = generatedColumn.asInstanceOf[js.Any], generatedLine = generatedLine.asInstanceOf[js.Any], originalColumn = originalColumn.asInstanceOf[js.Any], originalLine = originalLine.asInstanceOf[js.Any], originalSource = originalSource.asInstanceOf[js.Any])
      __obj.asInstanceOf[SourceMapping]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: SourceMapping] (val x: Self) extends AnyVal {
      
      inline def setGeneratedColumn(value: Double): Self = StObject.set(x, "generatedColumn", value.asInstanceOf[js.Any])
      
      inline def setGeneratedLine(value: Double): Self = StObject.set(x, "generatedLine", value.asInstanceOf[js.Any])
      
      inline def setOriginalColumn(value: Double): Self = StObject.set(x, "originalColumn", value.asInstanceOf[js.Any])
      
      inline def setOriginalLine(value: Double): Self = StObject.set(x, "originalLine", value.asInstanceOf[js.Any])
      
      inline def setOriginalSource(value: String): Self = StObject.set(x, "originalSource", value.asInstanceOf[js.Any])
    }
  }
  
  trait SourceOrigin extends StObject {
    
    /**
      * The 1-indexed columnNumber of the corresponding call site in the original source
      */
    var columnNumber: Double
    
    /**
      * The file name of the original source, as reported in the SourceMap
      */
    var fileName: String
    
    /**
      * The 1-indexed lineNumber of the corresponding call site in the original source
      */
    var lineNumber: Double
    
    /**
      * The name of the range in the source map, if one was provided
      */
    var name: js.UndefOr[String] = js.undefined
  }
  object SourceOrigin {
    
    inline def apply(columnNumber: Double, fileName: String, lineNumber: Double): SourceOrigin = {
      val __obj = js.Dynamic.literal(columnNumber = columnNumber.asInstanceOf[js.Any], fileName = fileName.asInstanceOf[js.Any], lineNumber = lineNumber.asInstanceOf[js.Any])
      __obj.asInstanceOf[SourceOrigin]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: SourceOrigin] (val x: Self) extends AnyVal {
      
      inline def setColumnNumber(value: Double): Self = StObject.set(x, "columnNumber", value.asInstanceOf[js.Any])
      
      inline def setFileName(value: String): Self = StObject.set(x, "fileName", value.asInstanceOf[js.Any])
      
      inline def setLineNumber(value: Double): Self = StObject.set(x, "lineNumber", value.asInstanceOf[js.Any])
      
      inline def setName(value: String): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
      
      inline def setNameUndefined: Self = StObject.set(x, "name", js.undefined)
    }
  }
  
  type _To = js.Object & (Instantiable2[/* id */ String, /* parent */ js.UndefOr[Module], Module])
  
  /* This means you don't have to write `^`, but can instead just say `moduleMod.foo` */
  override def _to: js.Object & (Instantiable2[/* id */ String, /* parent */ js.UndefOr[Module], Module]) = ^
  
  object global {
    
    @js.native
    trait ImportMeta extends StObject {
      
      /**
        * The directory name of the current module. This is the same as the `path.dirname()` of the `import.meta.filename`.
        * **Caveat:** only present on `file:` modules.
        */
      var dirname: String = js.native
      
      /**
        * The full absolute path and filename of the current module, with symlinks resolved.
        * This is the same as the `url.fileURLToPath()` of the `import.meta.url`.
        * **Caveat:** only local modules support this property. Modules not using the `file:` protocol will not provide it.
        */
      var filename: String = js.native
      
      /**
        * Provides a module-relative resolution function scoped to each module, returning
        * the URL string.
        *
        * Second `parent` parameter is only used when the `--experimental-import-meta-resolve`
        * command flag enabled.
        *
        * @since v20.6.0
        *
        * @param specifier The module specifier to resolve relative to `parent`.
        * @param parent The absolute parent module URL to resolve from.
        * @returns The absolute (`file:`) URL string for the resolved module.
        */
      def resolve(specifier: String): String = js.native
      def resolve(specifier: String, parent: String): String = js.native
      def resolve(specifier: String, parent: org.scalajs.dom.URL): String = js.native
      
      /**
        * The absolute `file:` URL of the module.
        */
      var url: String = js.native
    }
  }
}
