package typings.node

import typings.node.anon.Fatal
import typings.node.anon.ToString
import typings.node.globalsMod.global.AbortController
import typings.node.globalsMod.global.AbortSignal
import typings.node.globalsMod.global.NodeJS.Dict
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.utilMod.BackgroundColors
import typings.node.utilMod.CustomPromisify
import typings.node.utilMod.DebugLogger
import typings.node.utilMod.DebugLoggerFunction
import typings.node.utilMod.ForegroundColors
import typings.node.utilMod.InspectOptions
import typings.node.utilMod.Modifiers
import typings.node.utilMod.ParseArgsConfig
import typings.node.utilMod.ParsedResults
import typings.std.Map
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object nodeColonutilMod {
  
  @JSImport("node:util", JSImport.Namespace)
  @js.native
  val ^ : js.Any = js.native
  
  @JSImport("node:util", "MIMEParams")
  @js.native
  open class MIMEParams ()
    extends typings.node.utilMod.MIMEParams
  
  @JSImport("node:util", "MIMEType")
  @js.native
  open class MIMEType protected ()
    extends typings.node.utilMod.MIMEType {
    /**
      * Creates a new MIMEType object by parsing the input.
      *
      * A `TypeError` will be thrown if the `input` is not a valid MIME.
      * Note that an effort will be made to coerce the given values into strings.
      * @param input The input MIME to parse.
      */
    def this(input: String) = this()
    def this(input: ToString) = this()
  }
  
  @JSImport("node:util", "TextDecoder")
  @js.native
  open class TextDecoder ()
    extends typings.node.utilMod.TextDecoder {
    def this(encoding: String) = this()
    def this(encoding: String, options: Fatal) = this()
    def this(encoding: Unit, options: Fatal) = this()
  }
  
  @JSImport("node:util", "TextEncoder")
  @js.native
  open class TextEncoder ()
    extends typings.node.utilMod.TextEncoder
  
  inline def aborted(signal: AbortSignal, resource: Any): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("aborted")(signal.asInstanceOf[js.Any], resource.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  
  inline def callbackify(fn: js.Function0[js.Promise[Unit]]): js.Function1[/* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function1[/* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit]]
  inline def callbackify[T1](fn: js.Function1[/* arg1 */ T1, js.Promise[Unit]]): js.Function2[/* arg1 */ T1, /* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function2[/* arg1 */ T1, /* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit]]
  inline def callbackify[T1, T2](fn: js.Function2[/* arg1 */ T1, /* arg2 */ T2, js.Promise[Unit]]): js.Function3[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function3[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ]]
  inline def callbackify[T1, T2, T3](fn: js.Function3[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, js.Promise[Unit]]): js.Function4[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function4[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ]]
  inline def callbackify[T1, T2, T3, T4](fn: js.Function4[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, /* arg4 */ T4, js.Promise[Unit]]): js.Function5[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function5[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ]]
  inline def callbackify[T1, T2, T3, T4, T5](
    fn: js.Function5[
      /* arg1 */ T1, 
      /* arg2 */ T2, 
      /* arg3 */ T3, 
      /* arg4 */ T4, 
      /* arg5 */ T5, 
      js.Promise[Unit]
    ]
  ): js.Function6[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* arg5 */ T5, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function6[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* arg5 */ T5, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ]]
  inline def callbackify[T1, T2, T3, T4, T5, T6](
    fn: js.Function6[
      /* arg1 */ T1, 
      /* arg2 */ T2, 
      /* arg3 */ T3, 
      /* arg4 */ T4, 
      /* arg5 */ T5, 
      /* arg6 */ T6, 
      js.Promise[Unit]
    ]
  ): js.Function7[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* arg5 */ T5, 
    /* arg6 */ T6, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function7[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* arg5 */ T5, 
    /* arg6 */ T6, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ]]
  
  inline def callbackify_T1T2T3T4T5T6TResult[T1, T2, T3, T4, T5, T6, TResult](
    fn: js.Function6[
      /* arg1 */ T1, 
      /* arg2 */ T2, 
      /* arg3 */ T3, 
      /* arg4 */ T4, 
      /* arg5 */ T5, 
      /* arg6 */ T6, 
      js.Promise[TResult]
    ]
  ): js.Function7[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* arg5 */ T5, 
    /* arg6 */ T6, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function7[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* arg5 */ T5, 
    /* arg6 */ T6, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ]]
  
  inline def callbackify_T1T2T3T4T5TResult[T1, T2, T3, T4, T5, TResult](
    fn: js.Function5[
      /* arg1 */ T1, 
      /* arg2 */ T2, 
      /* arg3 */ T3, 
      /* arg4 */ T4, 
      /* arg5 */ T5, 
      js.Promise[TResult]
    ]
  ): js.Function6[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* arg5 */ T5, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function6[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* arg5 */ T5, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ]]
  
  inline def callbackify_T1T2T3T4TResult[T1, T2, T3, T4, TResult](fn: js.Function4[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, /* arg4 */ T4, js.Promise[TResult]]): js.Function5[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function5[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ]]
  
  inline def callbackify_T1T2T3TResult[T1, T2, T3, TResult](fn: js.Function3[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, js.Promise[TResult]]): js.Function4[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function4[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ]]
  
  inline def callbackify_T1T2TResult[T1, T2, TResult](fn: js.Function2[/* arg1 */ T1, /* arg2 */ T2, js.Promise[TResult]]): js.Function3[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function3[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ]]
  
  inline def callbackify_T1TResult[T1, TResult](fn: js.Function1[/* arg1 */ T1, js.Promise[TResult]]): js.Function2[
    /* arg1 */ T1, 
    /* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function2[
    /* arg1 */ T1, 
    /* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
    Unit
  ]]
  
  inline def callbackify_TResult[TResult](fn: js.Function0[js.Promise[TResult]]): js.Function1[
    /* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
    Unit
  ] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function1[
    /* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
    Unit
  ]]
  
  @JSImport("node:util", "debug")
  @js.native
  val debug: js.Function2[
    /* section */ String, 
    /* callback */ js.UndefOr[js.Function1[/* fn */ DebugLoggerFunction, Unit]], 
    DebugLogger
  ] = js.native
  
  inline def debuglog(section: String): DebugLogger = ^.asInstanceOf[js.Dynamic].applyDynamic("debuglog")(section.asInstanceOf[js.Any]).asInstanceOf[DebugLogger]
  inline def debuglog(section: String, callback: js.Function1[/* fn */ DebugLoggerFunction, Unit]): DebugLogger = (^.asInstanceOf[js.Dynamic].applyDynamic("debuglog")(section.asInstanceOf[js.Any], callback.asInstanceOf[js.Any])).asInstanceOf[DebugLogger]
  
  inline def deprecate[T /* <: js.Function */](fn: T, msg: String): T = (^.asInstanceOf[js.Dynamic].applyDynamic("deprecate")(fn.asInstanceOf[js.Any], msg.asInstanceOf[js.Any])).asInstanceOf[T]
  inline def deprecate[T /* <: js.Function */](fn: T, msg: String, code: String): T = (^.asInstanceOf[js.Dynamic].applyDynamic("deprecate")(fn.asInstanceOf[js.Any], msg.asInstanceOf[js.Any], code.asInstanceOf[js.Any])).asInstanceOf[T]
  
  inline def format(format: Any, param: Any*): String = ^.asInstanceOf[js.Dynamic].applyDynamic("format")(scala.List(format.asInstanceOf[js.Any]).`++`(param.asInstanceOf[Seq[js.Any]])*).asInstanceOf[String]
  inline def format(format: Unit, param: Any*): String = ^.asInstanceOf[js.Dynamic].applyDynamic("format")(scala.List(format.asInstanceOf[js.Any]).`++`(param.asInstanceOf[Seq[js.Any]])*).asInstanceOf[String]
  
  inline def formatWithOptions(inspectOptions: InspectOptions, format: Any, param: Any*): String = (^.asInstanceOf[js.Dynamic].applyDynamic("formatWithOptions")((scala.List(inspectOptions.asInstanceOf[js.Any], format.asInstanceOf[js.Any])).`++`(param.asInstanceOf[Seq[js.Any]])*)).asInstanceOf[String]
  inline def formatWithOptions(inspectOptions: InspectOptions, format: Unit, param: Any*): String = (^.asInstanceOf[js.Dynamic].applyDynamic("formatWithOptions")((scala.List(inspectOptions.asInstanceOf[js.Any], format.asInstanceOf[js.Any])).`++`(param.asInstanceOf[Seq[js.Any]])*)).asInstanceOf[String]
  
  inline def getSystemErrorMap(): Map[Double, js.Tuple2[String, String]] = ^.asInstanceOf[js.Dynamic].applyDynamic("getSystemErrorMap")().asInstanceOf[Map[Double, js.Tuple2[String, String]]]
  
  inline def getSystemErrorName(err: Double): String = ^.asInstanceOf[js.Dynamic].applyDynamic("getSystemErrorName")(err.asInstanceOf[js.Any]).asInstanceOf[String]
  
  inline def inherits(constructor: Any, superConstructor: Any): Unit = (^.asInstanceOf[js.Dynamic].applyDynamic("inherits")(constructor.asInstanceOf[js.Any], superConstructor.asInstanceOf[js.Any])).asInstanceOf[Unit]
  
  object inspect {
    
    inline def apply(`object`: Any): String = ^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any]).asInstanceOf[String]
    inline def apply(`object`: Any, options: InspectOptions): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Boolean, depth: Double): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Boolean, depth: Double, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Boolean, depth: Null, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Boolean, depth: Unit, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Unit, depth: Double): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Unit, depth: Double, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Unit, depth: Null, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
    inline def apply(`object`: Any, showHidden: Unit, depth: Unit, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
    
    @JSImport("node:util", "inspect")
    @js.native
    val ^ : js.Any = js.native
    
    @JSImport("node:util", "inspect.colors")
    @js.native
    def colors: Dict[js.Tuple2[Double, Double]] = js.native
    inline def colors_=(x: Dict[js.Tuple2[Double, Double]]): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("colors")(x.asInstanceOf[js.Any])
    
    /**
      * That can be used to declare custom inspect functions.
      */
    @JSImport("node:util", "inspect.custom")
    @js.native
    val custom: js.Symbol = js.native
    
    @JSImport("node:util", "inspect.defaultOptions")
    @js.native
    def defaultOptions: InspectOptions = js.native
    inline def defaultOptions_=(x: InspectOptions): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("defaultOptions")(x.asInstanceOf[js.Any])
    
    /**
      * Allows changing inspect settings from the repl.
      */
    @JSImport("node:util", "inspect.replDefaults")
    @js.native
    def replDefaults: InspectOptions = js.native
    inline def replDefaults_=(x: InspectOptions): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("replDefaults")(x.asInstanceOf[js.Any])
    
    /* Inlined {[ K in node.util.Style ]: string} */
    object styles {
      
      @JSImport("node:util", "inspect.styles")
      @js.native
      val ^ : js.Any = js.native
      
      @JSImport("node:util", "inspect.styles.bigint")
      @js.native
      def bigint: String = js.native
      inline def bigint_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("bigint")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.boolean")
      @js.native
      def boolean: String = js.native
      inline def boolean_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("boolean")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.date")
      @js.native
      def date: String = js.native
      inline def date_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("date")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.module")
      @js.native
      def module: String = js.native
      inline def module_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("module")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.null")
      @js.native
      val `null`: String = js.native
      
      @JSImport("node:util", "inspect.styles.number")
      @js.native
      def number: String = js.native
      inline def number_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("number")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.regexp")
      @js.native
      def regexp: String = js.native
      inline def regexp_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("regexp")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.special")
      @js.native
      def special: String = js.native
      inline def special_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("special")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.string")
      @js.native
      def string: String = js.native
      inline def string_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("string")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.symbol")
      @js.native
      def symbol: String = js.native
      inline def symbol_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("symbol")(x.asInstanceOf[js.Any])
      
      @JSImport("node:util", "inspect.styles.undefined")
      @js.native
      def undefined: String = js.native
      inline def undefined_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("undefined")(x.asInstanceOf[js.Any])
    }
  }
  
  inline def isArray(`object`: Any): /* is std.Array<unknown> */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isArray")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Array<unknown> */ Boolean]
  
  inline def isBoolean(`object`: Any): /* is boolean */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isBoolean")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is boolean */ Boolean]
  
  inline def isBuffer(`object`: Any): /* is node.buffer.<global>.Buffer */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isBuffer")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is node.buffer.<global>.Buffer */ Boolean]
  
  inline def isDate(`object`: Any): /* is std.Date */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isDate")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Date */ Boolean]
  
  inline def isDeepStrictEqual(val1: Any, val2: Any): Boolean = (^.asInstanceOf[js.Dynamic].applyDynamic("isDeepStrictEqual")(val1.asInstanceOf[js.Any], val2.asInstanceOf[js.Any])).asInstanceOf[Boolean]
  
  inline def isError(`object`: Any): /* is std.Error */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isError")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.Error */ Boolean]
  
  inline def isFunction(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isFunction")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  inline def isNull(`object`: Any): /* is null */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isNull")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is null */ Boolean]
  
  inline def isNullOrUndefined(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isNullOrUndefined")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  inline def isNumber(`object`: Any): /* is number */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isNumber")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is number */ Boolean]
  
  inline def isObject(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isObject")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  inline def isPrimitive(`object`: Any): Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isPrimitive")(`object`.asInstanceOf[js.Any]).asInstanceOf[Boolean]
  
  inline def isRegExp(`object`: Any): /* is std.RegExp */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isRegExp")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is std.RegExp */ Boolean]
  
  inline def isString(`object`: Any): /* is string */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isString")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is string */ Boolean]
  
  inline def isSymbol(`object`: Any): /* is symbol */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isSymbol")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is symbol */ Boolean]
  
  inline def isUndefined(`object`: Any): /* is undefined */ Boolean = ^.asInstanceOf[js.Dynamic].applyDynamic("isUndefined")(`object`.asInstanceOf[js.Any]).asInstanceOf[/* is undefined */ Boolean]
  
  inline def log(string: String): Unit = ^.asInstanceOf[js.Dynamic].applyDynamic("log")(string.asInstanceOf[js.Any]).asInstanceOf[Unit]
  
  inline def parseArgs[T /* <: ParseArgsConfig */](): ParsedResults[T] = ^.asInstanceOf[js.Dynamic].applyDynamic("parseArgs")().asInstanceOf[ParsedResults[T]]
  inline def parseArgs[T /* <: ParseArgsConfig */](config: T): ParsedResults[T] = ^.asInstanceOf[js.Dynamic].applyDynamic("parseArgs")(config.asInstanceOf[js.Any]).asInstanceOf[ParsedResults[T]]
  
  inline def parseEnv(content: String): js.Object = ^.asInstanceOf[js.Dynamic].applyDynamic("parseEnv")(content.asInstanceOf[js.Any]).asInstanceOf[js.Object]
  
  object promisify {
    
    inline def apply(fn: js.Function): js.Function = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function]
    inline def apply(fn: js.Function1[/* callback */ js.Function1[/* err */ js.UndefOr[Any], Unit], Unit]): js.Function0[js.Promise[Unit]] = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function0[js.Promise[Unit]]]
    inline def apply[T1](
      fn: js.Function2[/* arg1 */ T1, /* callback */ js.Function1[/* err */ js.UndefOr[Any], Unit], Unit]
    ): js.Function1[/* arg1 */ T1, js.Promise[Unit]] = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function1[/* arg1 */ T1, js.Promise[Unit]]]
    inline def apply[TCustom /* <: js.Function */](fn: CustomPromisify[TCustom]): TCustom = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[TCustom]
    inline def apply[T1, T2](
      fn: js.Function3[
          /* arg1 */ T1, 
          /* arg2 */ T2, 
          /* callback */ js.Function1[/* err */ js.UndefOr[Any], Unit], 
          Unit
        ]
    ): js.Function2[/* arg1 */ T1, /* arg2 */ T2, js.Promise[Unit]] = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function2[/* arg1 */ T1, /* arg2 */ T2, js.Promise[Unit]]]
    inline def apply[T1, T2, T3](
      fn: js.Function4[
          /* arg1 */ T1, 
          /* arg2 */ T2, 
          /* arg3 */ T3, 
          /* callback */ js.Function1[/* err */ js.UndefOr[Any], Unit], 
          Unit
        ]
    ): js.Function3[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, js.Promise[Unit]] = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function3[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, js.Promise[Unit]]]
    inline def apply[T1, T2, T3, T4](
      fn: js.Function5[
          /* arg1 */ T1, 
          /* arg2 */ T2, 
          /* arg3 */ T3, 
          /* arg4 */ T4, 
          /* callback */ js.Function1[/* err */ js.UndefOr[Any], Unit], 
          Unit
        ]
    ): js.Function4[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, /* arg4 */ T4, js.Promise[Unit]] = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function4[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, /* arg4 */ T4, js.Promise[Unit]]]
    inline def apply[T1, T2, T3, T4, T5](
      fn: js.Function6[
          /* arg1 */ T1, 
          /* arg2 */ T2, 
          /* arg3 */ T3, 
          /* arg4 */ T4, 
          /* arg5 */ T5, 
          /* callback */ js.Function1[/* err */ js.UndefOr[Any], Unit], 
          Unit
        ]
    ): js.Function5[
        /* arg1 */ T1, 
        /* arg2 */ T2, 
        /* arg3 */ T3, 
        /* arg4 */ T4, 
        /* arg5 */ T5, 
        js.Promise[Unit]
      ] = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function5[
        /* arg1 */ T1, 
        /* arg2 */ T2, 
        /* arg3 */ T3, 
        /* arg4 */ T4, 
        /* arg5 */ T5, 
        js.Promise[Unit]
      ]]
    
    @JSImport("node:util", "promisify")
    @js.native
    val ^ : js.Any = js.native
    
    /**
      * That can be used to declare custom promisified variants of functions.
      */
    @JSImport("node:util", "promisify.custom")
    @js.native
    val custom: js.Symbol = js.native
  }
  
  inline def stripVTControlCharacters(str: String): String = ^.asInstanceOf[js.Dynamic].applyDynamic("stripVTControlCharacters")(str.asInstanceOf[js.Any]).asInstanceOf[String]
  
  inline def styleText(format: js.Array[ForegroundColors | BackgroundColors | Modifiers], text: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("styleText")(format.asInstanceOf[js.Any], text.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def styleText(format: BackgroundColors, text: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("styleText")(format.asInstanceOf[js.Any], text.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def styleText(format: ForegroundColors, text: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("styleText")(format.asInstanceOf[js.Any], text.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def styleText(format: Modifiers, text: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("styleText")(format.asInstanceOf[js.Any], text.asInstanceOf[js.Any])).asInstanceOf[String]
  
  inline def toUSVString(string: String): String = ^.asInstanceOf[js.Dynamic].applyDynamic("toUSVString")(string.asInstanceOf[js.Any]).asInstanceOf[String]
  
  inline def transferableAbortController(): AbortController = ^.asInstanceOf[js.Dynamic].applyDynamic("transferableAbortController")().asInstanceOf[AbortController]
  
  inline def transferableAbortSignal(signal: AbortSignal): AbortSignal = ^.asInstanceOf[js.Dynamic].applyDynamic("transferableAbortSignal")(signal.asInstanceOf[js.Any]).asInstanceOf[AbortSignal]
  
  object types {
    
    @JSImport("node:util", "types")
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
}
