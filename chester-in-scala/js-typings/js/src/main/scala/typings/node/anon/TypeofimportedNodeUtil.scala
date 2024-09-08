package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.Instantiable2
import typings.node.globalsMod.global.AbortController
import typings.node.globalsMod.global.AbortSignal
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.utilMod.BackgroundColors
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

@js.native
trait TypeofimportedNodeUtil extends StObject {
  
  var MIMEParams: Instantiable0[typings.node.nodeColonutilMod.MIMEParams] = js.native
  
  var MIMEType: Instantiable1[/* input */ String | ToString, typings.node.nodeColonutilMod.MIMEType] = js.native
  
  var TextDecoder: Instantiable2[
    /* encoding */ js.UndefOr[String], 
    /* options */ js.UndefOr[Fatal], 
    typings.node.nodeColonutilMod.TextDecoder
  ] = js.native
  
  var TextEncoder: Instantiable0[typings.node.nodeColonutilMod.TextEncoder] = js.native
  
  def aborted(signal: AbortSignal, resource: Any): js.Promise[Unit] = js.native
  
  def callbackify(fn: js.Function0[js.Promise[Unit]]): js.Function1[/* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit] = js.native
  def callbackify[T1](fn: js.Function1[/* arg1 */ T1, js.Promise[Unit]]): js.Function2[/* arg1 */ T1, /* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit] = js.native
  def callbackify[T1, T2](fn: js.Function2[/* arg1 */ T1, /* arg2 */ T2, js.Promise[Unit]]): js.Function3[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ] = js.native
  def callbackify[T1, T2, T3](fn: js.Function3[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, js.Promise[Unit]]): js.Function4[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ] = js.native
  def callbackify[T1, T2, T3, T4](fn: js.Function4[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, /* arg4 */ T4, js.Promise[Unit]]): js.Function5[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* callback */ js.Function1[/* err */ ErrnoException, Unit], 
    Unit
  ] = js.native
  def callbackify[T1, T2, T3, T4, T5](
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
  ] = js.native
  def callbackify[T1, T2, T3, T4, T5, T6](
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
  ] = js.native
  @JSName("callbackify")
  def callbackify_T1T2T3T4T5T6TResult[T1, T2, T3, T4, T5, T6, TResult](
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
  ] = js.native
  @JSName("callbackify")
  def callbackify_T1T2T3T4T5TResult[T1, T2, T3, T4, T5, TResult](
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
  ] = js.native
  @JSName("callbackify")
  def callbackify_T1T2T3T4TResult[T1, T2, T3, T4, TResult](fn: js.Function4[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, /* arg4 */ T4, js.Promise[TResult]]): js.Function5[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* arg4 */ T4, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ] = js.native
  @JSName("callbackify")
  def callbackify_T1T2T3TResult[T1, T2, T3, TResult](fn: js.Function3[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, js.Promise[TResult]]): js.Function4[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* arg3 */ T3, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ] = js.native
  @JSName("callbackify")
  def callbackify_T1T2TResult[T1, T2, TResult](fn: js.Function2[/* arg1 */ T1, /* arg2 */ T2, js.Promise[TResult]]): js.Function3[
    /* arg1 */ T1, 
    /* arg2 */ T2, 
    /* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
    Unit
  ] = js.native
  @JSName("callbackify")
  def callbackify_T1TResult[T1, TResult](fn: js.Function1[/* arg1 */ T1, js.Promise[TResult]]): js.Function2[
    /* arg1 */ T1, 
    /* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
    Unit
  ] = js.native
  @JSName("callbackify")
  def callbackify_TResult[TResult](fn: js.Function0[js.Promise[TResult]]): js.Function1[
    /* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
    Unit
  ] = js.native
  
  def debuglog(section: String): DebugLogger = js.native
  def debuglog(section: String, callback: js.Function1[/* fn */ DebugLoggerFunction, Unit]): DebugLogger = js.native
  
  def deprecate[T /* <: js.Function */](fn: T, msg: String): T = js.native
  def deprecate[T /* <: js.Function */](fn: T, msg: String, code: String): T = js.native
  
  def format(format: Any, param: Any*): String = js.native
  def format(format: Unit, param: Any*): String = js.native
  
  def formatWithOptions(inspectOptions: InspectOptions, format: Any, param: Any*): String = js.native
  def formatWithOptions(inspectOptions: InspectOptions, format: Unit, param: Any*): String = js.native
  
  def getSystemErrorMap(): Map[Double, js.Tuple2[String, String]] = js.native
  
  def getSystemErrorName(err: Double): String = js.native
  
  def inherits(constructor: Any, superConstructor: Any): Unit = js.native
  
  val inspect: TypeofinspectColors = js.native
  
  def isArray(`object`: Any): /* is std.Array<unknown> */ Boolean = js.native
  
  def isBoolean(`object`: Any): /* is boolean */ Boolean = js.native
  
  def isBuffer(`object`: Any): /* is node.buffer.<global>.Buffer */ Boolean = js.native
  
  def isDate(`object`: Any): /* is std.Date */ Boolean = js.native
  
  def isDeepStrictEqual(val1: Any, val2: Any): Boolean = js.native
  
  def isError(`object`: Any): /* is std.Error */ Boolean = js.native
  
  def isFunction(`object`: Any): Boolean = js.native
  
  def isNull(`object`: Any): /* is null */ Boolean = js.native
  
  def isNullOrUndefined(`object`: Any): Boolean = js.native
  
  def isNumber(`object`: Any): /* is number */ Boolean = js.native
  
  def isObject(`object`: Any): Boolean = js.native
  
  def isPrimitive(`object`: Any): Boolean = js.native
  
  def isRegExp(`object`: Any): /* is std.RegExp */ Boolean = js.native
  
  def isString(`object`: Any): /* is string */ Boolean = js.native
  
  def isSymbol(`object`: Any): /* is symbol */ Boolean = js.native
  
  def isUndefined(`object`: Any): /* is undefined */ Boolean = js.native
  
  def log(string: String): Unit = js.native
  
  def parseArgs[T /* <: ParseArgsConfig */](): ParsedResults[T] = js.native
  def parseArgs[T /* <: ParseArgsConfig */](config: T): ParsedResults[T] = js.native
  
  def parseEnv(content: String): js.Object = js.native
  
  val promisify: TypeofpromisifyCustom = js.native
  
  def stripVTControlCharacters(str: String): String = js.native
  
  def styleText(format: js.Array[ForegroundColors | BackgroundColors | Modifiers], text: String): String = js.native
  def styleText(format: BackgroundColors, text: String): String = js.native
  def styleText(format: ForegroundColors, text: String): String = js.native
  def styleText(format: Modifiers, text: String): String = js.native
  
  def toUSVString(string: String): String = js.native
  
  def transferableAbortController(): AbortController = js.native
  
  def transferableAbortSignal(signal: AbortSignal): AbortSignal = js.native
  
  val types: Typeoftypes = js.native
}
