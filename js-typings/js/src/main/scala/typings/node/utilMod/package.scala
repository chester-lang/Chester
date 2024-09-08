package typings.node.utilMod

import org.scalablytyped.runtime.StringDictionary
import typings.node.anon.Index
import typings.node.anon.Kind
import typings.node.anon.Positionals
import typings.node.anon.Values
import typings.node.globalsMod.global.AbortController
import typings.node.globalsMod.global.AbortSignal
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.utilMod.^
import typings.std.Map
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}


inline def aborted(signal: AbortSignal, resource: Any): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("aborted")(signal.asInstanceOf[js.Any], resource.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]

inline def callbackify(fn: js.Function0[js.Promise[Unit]]): js.Function1[/* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function1[/* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit]]
inline def callbackify[T1](fn: js.Function1[/* arg1 */ T1, js.Promise[Unit]]): js.Function2[/* arg1 */ T1, /* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function2[/* arg1 */ T1, /* callback */ js.Function1[/* err */ ErrnoException, Unit], Unit]]
inline def callbackify[T1, T2](fn: js.Function2[/* arg1 */ T1, /* arg2 */ T2, js.Promise[Unit]]): js.Function3[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* callback */ js.Function1[/* err */ ErrnoException, Unit], 
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function3[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* callback */ js.Function1[/* err */ ErrnoException, Unit], 
Unit]]
inline def callbackify[T1, T2, T3](fn: js.Function3[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, js.Promise[Unit]]): js.Function4[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* callback */ js.Function1[/* err */ ErrnoException, Unit], 
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function4[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* callback */ js.Function1[/* err */ ErrnoException, Unit], 
Unit]]
inline def callbackify[T1, T2, T3, T4](fn: js.Function4[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, /* arg4 */ T4, js.Promise[Unit]]): js.Function5[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* arg4 */ T4, 
/* callback */ js.Function1[/* err */ ErrnoException, Unit], 
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function5[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* arg4 */ T4, 
/* callback */ js.Function1[/* err */ ErrnoException, Unit], 
Unit]]
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
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function6[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* arg4 */ T4, 
/* arg5 */ T5, 
/* callback */ js.Function1[/* err */ ErrnoException, Unit], 
Unit]]
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
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function7[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* arg4 */ T4, 
/* arg5 */ T5, 
/* arg6 */ T6, 
/* callback */ js.Function1[/* err */ ErrnoException, Unit], 
Unit]]

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
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function7[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* arg4 */ T4, 
/* arg5 */ T5, 
/* arg6 */ T6, 
/* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
Unit]]

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
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function6[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* arg4 */ T4, 
/* arg5 */ T5, 
/* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
Unit]]

inline def callbackify_T1T2T3T4TResult[T1, T2, T3, T4, TResult](fn: js.Function4[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, /* arg4 */ T4, js.Promise[TResult]]): js.Function5[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* arg4 */ T4, 
/* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function5[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* arg4 */ T4, 
/* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
Unit]]

inline def callbackify_T1T2T3TResult[T1, T2, T3, TResult](fn: js.Function3[/* arg1 */ T1, /* arg2 */ T2, /* arg3 */ T3, js.Promise[TResult]]): js.Function4[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function4[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* arg3 */ T3, 
/* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
Unit]]

inline def callbackify_T1T2TResult[T1, T2, TResult](fn: js.Function2[/* arg1 */ T1, /* arg2 */ T2, js.Promise[TResult]]): js.Function3[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function3[
/* arg1 */ T1, 
/* arg2 */ T2, 
/* callback */ js.Function2[/* err */ ErrnoException | Null, /* result */ TResult, Unit], 
Unit]]

inline def callbackify_T1TResult[T1, TResult](fn: js.Function1[/* arg1 */ T1, js.Promise[TResult]]): js.Function2[
/* arg1 */ T1, 
/* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function2[
/* arg1 */ T1, 
/* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
Unit]]

inline def callbackify_TResult[TResult](fn: js.Function0[js.Promise[TResult]]): js.Function1[
/* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("callbackify")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Function1[
/* callback */ js.Function2[/* err */ ErrnoException, /* result */ TResult, Unit], 
Unit]]

inline def debug: js.Function2[
/* section */ String, 
/* callback */ js.UndefOr[js.Function1[/* fn */ DebugLoggerFunction, Unit]], 
DebugLogger] = ^.asInstanceOf[js.Dynamic].selectDynamic("debug").asInstanceOf[js.Function2[
/* section */ String, 
/* callback */ js.UndefOr[js.Function1[/* fn */ DebugLoggerFunction, Unit]], 
DebugLogger]]

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

inline def stripVTControlCharacters(str: String): String = ^.asInstanceOf[js.Dynamic].applyDynamic("stripVTControlCharacters")(str.asInstanceOf[js.Any]).asInstanceOf[String]

inline def styleText(format: js.Array[ForegroundColors | BackgroundColors | Modifiers], text: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("styleText")(format.asInstanceOf[js.Any], text.asInstanceOf[js.Any])).asInstanceOf[String]
inline def styleText(format: BackgroundColors, text: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("styleText")(format.asInstanceOf[js.Any], text.asInstanceOf[js.Any])).asInstanceOf[String]
inline def styleText(format: ForegroundColors, text: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("styleText")(format.asInstanceOf[js.Any], text.asInstanceOf[js.Any])).asInstanceOf[String]
inline def styleText(format: Modifiers, text: String): String = (^.asInstanceOf[js.Dynamic].applyDynamic("styleText")(format.asInstanceOf[js.Any], text.asInstanceOf[js.Any])).asInstanceOf[String]

inline def toUSVString(string: String): String = ^.asInstanceOf[js.Dynamic].applyDynamic("toUSVString")(string.asInstanceOf[js.Any]).asInstanceOf[String]

inline def transferableAbortController(): AbortController = ^.asInstanceOf[js.Dynamic].applyDynamic("transferableAbortController")().asInstanceOf[AbortController]

inline def transferableAbortSignal(signal: AbortSignal): AbortSignal = ^.asInstanceOf[js.Dynamic].applyDynamic("transferableAbortSignal")(signal.asInstanceOf[js.Any]).asInstanceOf[AbortSignal]

type CustomInspectFunction = js.Function2[/* depth */ Double, /* options */ InspectOptionsStylized, Any]

type ExtractOptionValue[T /* <: ParseArgsConfig */, O /* <: ParseArgsOptionConfig */] = IfDefaultsTrue[
/* import warning: importer.ImportType#apply Failed type conversion: T['strict'] */ js.Any, 
/* import warning: importer.ImportType#apply Failed type conversion: O['type'] extends 'string' ? string : O['type'] extends 'boolean' ? boolean : string | boolean */ js.Any, 
String | Boolean]

// we put the `extends false` condition first here because `undefined` compares like `any` when `strictNullChecks: false`
/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  T extends false ? IfFalse : T extends true ? IfTrue : IfFalse
  }}}
  */
type IfDefaultsFalse[T, IfTrue, IfFalse] = IfFalse

/*
  IfDefaultsTrue and IfDefaultsFalse are helpers to handle default values for missing boolean properties.
  TypeScript does not have exact types for objects: https://github.com/microsoft/TypeScript/issues/12936
  This means it is impossible to distinguish between "field X is definitely not present" and "field X may or may not be present".
  But we expect users to generally provide their config inline or `as const`, which means TS will always know whether a given field is present.
  So this helper treats "not definitely present" (i.e., not `extends boolean`) as being "definitely not present", i.e. it should have its default value.
  This is technically incorrect but is a much nicer UX for the common case.
  The IfDefaultsTrue version is for things which default to true; the IfDefaultsFalse version is for things which default to false.
  */
/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  T extends true ? IfTrue : T extends false ? IfFalse : IfTrue
  }}}
  */
type IfDefaultsTrue[T, IfTrue, IfFalse] = IfTrue

type ParseArgsOptionsConfig = StringDictionary[ParseArgsOptionConfig]

type ParsedOptionToken[T /* <: ParseArgsConfig */] = IfDefaultsTrue[
/* import warning: importer.ImportType#apply Failed type conversion: T['strict'] */ js.Any, 
TokenForOptions[
  T, 
  /* import warning: importer.ImportType#apply Failed type conversion: keyof T['options'] */ js.Any
], 
OptionToken]

type ParsedPositionalToken[T /* <: ParseArgsConfig */] = IfDefaultsTrue[
/* import warning: importer.ImportType#apply Failed type conversion: T['strict'] */ js.Any, 
IfDefaultsFalse[
  /* import warning: importer.ImportType#apply Failed type conversion: T['allowPositionals'] */ js.Any, 
  Index, 
  scala.Nothing
], 
IfDefaultsTrue[
  /* import warning: importer.ImportType#apply Failed type conversion: T['allowPositionals'] */ js.Any, 
  Index, 
  scala.Nothing
]]

type ParsedPositionals[T /* <: ParseArgsConfig */] = IfDefaultsTrue[
/* import warning: importer.ImportType#apply Failed type conversion: T['strict'] */ js.Any, 
IfDefaultsFalse[
  /* import warning: importer.ImportType#apply Failed type conversion: T['allowPositionals'] */ js.Any, 
  js.Array[String], 
  js.Array[Any]
], 
IfDefaultsTrue[
  /* import warning: importer.ImportType#apply Failed type conversion: T['allowPositionals'] */ js.Any, 
  js.Array[String], 
  js.Array[Any]
]]

// If ParseArgsConfig extends T, then the user passed config constructed elsewhere.
// So we can't rely on the `"not definitely present" implies "definitely not present"` assumption mentioned above.
/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  node.util.ParseArgsConfig extends T ? {  values :{[longOption: string] : undefined | string | boolean | std.Array<string | boolean>},   positionals :std.Array<string>,   tokens :std.Array<node.util.Token> | undefined} : node.util.PreciseParsedResults<T>
  }}}
  */
type ParsedResults[T /* <: ParseArgsConfig */] = Values[T]

type ParsedTokens[T /* <: ParseArgsConfig */] = js.Array[ParsedOptionToken[T] | ParsedPositionalToken[T] | Kind]

type ParsedValues[T /* <: ParseArgsConfig */] = (IfDefaultsTrue[
/* import warning: importer.ImportType#apply Failed type conversion: T['strict'] */ js.Any, 
Any, 
StringDictionary[js.UndefOr[String | Boolean]]]) & (/* import warning: importer.ImportType#apply Failed type conversion: T['options'] extends node.util.ParseArgsOptionsConfig ? {-readonly [ LongOption in keyof T['options'] ]: node.util.IfDefaultsFalse<T['options'][LongOption]['multiple'], undefined | std.Array<node.util.ExtractOptionValue<T, T['options'][LongOption]>>, undefined | node.util.ExtractOptionValue<T, T['options'][LongOption]>>} : {} */ js.Any)

type PreciseParsedResults[T /* <: ParseArgsConfig */] = IfDefaultsFalse[
/* import warning: importer.ImportType#apply Failed type conversion: T['tokens'] */ js.Any, 
Positionals[T], 
Values[T]]

/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  O['type'] extends 'string' ? {  kind :'option',   index :number,   name :K,   rawName :string,   value :string,   inlineValue :boolean} : O['type'] extends 'boolean' ? {  kind :'option',   index :number,   name :K,   rawName :string,   value :undefined,   inlineValue :undefined} : node.util.OptionToken & {  name :K}
  }}}
  */
type PreciseTokenForOptions[K /* <: String */, O /* <: ParseArgsOptionConfig */] = OptionToken & (/* import warning: importer.ImportType#apply Failed type conversion: {  name :K} */ js.Any)

/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  K extends unknown ? T['options'] extends node.util.ParseArgsOptionsConfig ? node.util.PreciseTokenForOptions<K & string, T['options'][K]> : node.util.OptionToken : never
  }}}
  */
type TokenForOptions[T /* <: ParseArgsConfig */, K /* <: /* import warning: importer.ImportType#apply Failed type conversion: keyof T['options'] */ js.Any */] = OptionToken
