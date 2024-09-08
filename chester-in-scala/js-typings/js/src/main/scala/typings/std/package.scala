package typings.std

import org.scalablytyped.runtime.Instantiable1
import org.scalablytyped.runtime.StringDictionary
import typings.std.anon.LookupNamespaceURI
import typings.std.stdStrings.FIDO_2_0
import typings.std.stdStrings.`public-key`
import typings.std.stdStrings.auto
import typings.std.stdStrings.password
import typings.std.stdStrings.require
import typings.std.stdStrings.vibration
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}


type AlgorithmIdentifier = java.lang.String | org.scalajs.dom.Algorithm

type ArrayBufferLike = js.typedarray.ArrayBuffer

type AudioWorklet = Worklet

type AutoKeyword = auto

type BigInteger = js.typedarray.Uint8Array

type BlobCallback = js.Function1[/* blob */ org.scalajs.dom.Blob | Null, Unit]

type BlobPart = BufferSource | org.scalajs.dom.Blob | java.lang.String

type BodyInit = org.scalajs.dom.Blob | BufferSource | org.scalajs.dom.FormData | URLSearchParams | org.scalajs.dom.ReadableStream[js.typedarray.Uint8Array] | java.lang.String

type BufferSource = js.typedarray.ArrayBufferView | js.typedarray.ArrayBuffer

/** A CDATA section that can be used within XML to include extended portions of unescaped text. The symbols < and & don’t need escaping as they normally do when inside a CDATA section. */
type CDATASection = org.scalajs.dom.Text

type COSEAlgorithmIdentifier = Double

/** An object representing a single CSS @supports at-rule. It implements the CSSConditionRule interface, and therefore the CSSRule and CSSGroupingRule interfaces with a type value of 12 (CSSRule.SUPPORTS_RULE). */
type CSSSupportsRule = CSSConditionRule

/* Rewritten from type alias, can be one of: 
  - typings.std.HTMLOrSVGImageElement
  - org.scalajs.dom.HTMLVideoElement
  - org.scalajs.dom.HTMLCanvasElement
  - typings.std.ImageBitmap
  - typings.std.OffscreenCanvas
*/
type CanvasImageSource = _CanvasImageSource | HTMLOrSVGImageElement | org.scalajs.dom.HTMLVideoElement | org.scalajs.dom.HTMLCanvasElement

/**
  * Convert first character of string literal type to uppercase
  */
type Capitalize[S /* <: java.lang.String */] = /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify intrinsic */ Any

/** The ChannelMergerNode interface, often used in conjunction with its opposite, ChannelSplitterNode, reunites different mono inputs into a single output. Each input is used to fill a channel of the output. This is useful for accessing each channels separately, e.g. for performing channel mixing where gain must be separately controlled on each channel. */
type ChannelMergerNode = org.scalajs.dom.AudioNode

/** The ChannelSplitterNode interface, often used in conjunction with its opposite, ChannelMergerNode, separates the different channels of an audio source into a set of mono outputs. This is useful for accessing each channel separately, e.g. for performing channel mixing where gain must be separately controlled on each channel. */
type ChannelSplitterNode = org.scalajs.dom.AudioNode

type ClassDecorator = js.Function1[/* target */ js.Function, js.Function | Unit]

/** Textual notations within markup; although it is generally not visually shown, such comments are available to be read in the source view. */
type Comment = org.scalajs.dom.CharacterData

type ConstrainBoolean = scala.Boolean | ConstrainBooleanParameters

type ConstrainDOMString = java.lang.String | js.Array[java.lang.String] | ConstrainDOMStringParameters

type ConstrainDouble = Double | ConstrainDoubleRange

type ConstrainULong = Double | ConstrainULongRange

type DOMHighResTimeStamp = Double

/** Used by the dataset HTML attribute to represent data for custom attributes added to elements. */
type DOMStringMap = /* standard dom */
StringDictionary[js.UndefOr[java.lang.String]]

type DOMTimeStamp = Double

type DecodeErrorCallback = js.Function1[/* error */ org.scalajs.dom.DOMException, Unit]

type DecodeSuccessCallback = js.Function1[/* decodedData */ org.scalajs.dom.AudioBuffer, Unit]

type DocumentTimeline = AnimationTimeline

/** @deprecated Directly use HTMLElementTagNameMap or SVGElementTagNameMap as appropriate, instead. */
type ElementTagNameMap = HTMLElementTagNameMap & (Pick[
SVGElementTagNameMap, 
Exclude[
  /* import warning: LimitUnionLength.leaveTypeRef Was union type with length 57, starting with typings.std.stdStrings.a, typings.std.stdStrings.circle, typings.std.stdStrings.clipPath */ Any, 
  /* import warning: LimitUnionLength.leaveTypeRef Was union type with length 118, starting with typings.std.stdStrings.a, typings.std.stdStrings.abbr, typings.std.stdStrings.address */ Any
]])

type EvalError = js.Error

type EventHandlerNonNull = js.Function1[/* event */ org.scalajs.dom.Event, Any]

type EventListener = js.Function1[/* evt */ org.scalajs.dom.Event, Unit]

type EventListenerOrEventListenerObject = EventListener | EventListenerObject

/**
  * Exclude from T those types that are assignable to U
  */
/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  T extends U ? never : T
  }}}
  */
type Exclude[T, U] = T

/**
  * Extract from T those types that are assignable to U
  */
/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  T extends U ? T : never
  }}}
  */
type Extract[T, U] = T

type Float32List = js.typedarray.Float32Array | js.Array[GLfloat]

type ForEachCallback = js.Function2[
/* keyId */ js.typedarray.Int8Array | js.typedarray.Int16Array | js.typedarray.Int32Array | js.typedarray.Uint8Array | js.typedarray.Uint16Array | js.typedarray.Uint32Array | js.typedarray.Uint8ClampedArray | js.typedarray.Float32Array | js.typedarray.Float64Array | js.typedarray.DataView | js.typedarray.ArrayBuffer | Null, 
/* status */ MediaKeyStatus, 
Unit]

type FormDataEntryValue = org.scalajs.dom.File | java.lang.String

type FrameRequestCallback = js.Function1[/* time */ Double, Unit]

type FunctionStringCallback = js.Function1[/* data */ java.lang.String, Unit]

type GLbitfield = Double

type GLboolean = scala.Boolean

type GLclampf = Double

type GLenum = Double

type GLfloat = Double

type GLint = Double

type GLint64 = Double

type GLintptr = Double

type GLsizei = Double

type GLsizeiptr = Double

type GLuint = Double

type GLuint64 = Double

type GamepadHapticActuatorType = vibration

type HTMLOrSVGImageElement = org.scalajs.dom.HTMLImageElement | org.scalajs.dom.SVGImageElement

type HTMLOrSVGScriptElement = org.scalajs.dom.HTMLScriptElement | org.scalajs.dom.SVGScriptElement

type HashAlgorithmIdentifier = AlgorithmIdentifier

type HeadersInit = org.scalajs.dom.Headers | js.Array[js.Array[java.lang.String]] | (Record[java.lang.String, java.lang.String])

type IDBArrayKey = js.Array[IDBValidKey]

type IDBKeyPath = java.lang.String

/** 
NOTE: Rewritten from type alias:
{{{
type IDBValidKey = number | string | std.Date | std.BufferSource | std.IDBArrayKey
}}}
to avoid circular code involving: 
- std.IDBArrayKey
- std.IDBValidKey
*/
type IDBValidKey = Double | java.lang.String | js.Date | BufferSource | Any

type ImageBitmapSource = CanvasImageSource | org.scalajs.dom.Blob | org.scalajs.dom.ImageData

type Int32List = js.typedarray.Int32Array | js.Array[GLint]

type IntersectionObserverCallback = js.Function2[
/* entries */ js.Array[IntersectionObserverEntry], 
/* observer */ IntersectionObserver, 
Unit]

type LineAndPositionSetting = Double | AutoKeyword

/**
  * Convert string literal type to lowercase
  */
type Lowercase[S /* <: java.lang.String */] = /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify intrinsic */ Any

type MSCredentialType = FIDO_2_0

type MSLaunchUriCallback = js.Function0[Unit]

type MediaProvider = org.scalajs.dom.MediaStream | MediaSource | org.scalajs.dom.Blob

type MediaStreamTrackAudioSourceNode = org.scalajs.dom.AudioNode

type MessageEventSource = org.scalajs.dom.Window | org.scalajs.dom.MessagePort | org.scalajs.dom.ServiceWorker

type MethodDecorator = js.Function3[
/* target */ js.Object, 
/* propertyKey */ java.lang.String | js.Symbol, 
/* descriptor */ TypedPropertyDescriptor[Any], 
TypedPropertyDescriptor[Any] | Unit]

/** @deprecated */
type MouseWheelEvent = org.scalajs.dom.WheelEvent

type MutationCallback = js.Function2[
/* mutations */ js.Array[org.scalajs.dom.MutationRecord], 
/* observer */ org.scalajs.dom.MutationObserver, 
Unit]

type NamedCurve = java.lang.String

type NavigatorUserMediaErrorCallback = js.Function1[/* error */ MediaStreamError, Unit]

type NavigatorUserMediaSuccessCallback = js.Function1[/* stream */ org.scalajs.dom.MediaStream, Unit]

/**
  * Exclude null and undefined from T
  */
/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  T extends null | undefined ? never : T
  }}}
  */
type NonNullable[T] = T

type NotificationPermissionCallback = js.Function1[/* permission */ NotificationPermission, Unit]

/* Rewritten from type alias, can be one of: 
  - typings.std.OffscreenCanvasRenderingContext2D
  - typings.std.ImageBitmapRenderingContext
  - org.scalajs.dom.WebGLRenderingContext
  - typings.std.WebGL2RenderingContext
*/
type OffscreenRenderingContext = _OffscreenRenderingContext | org.scalajs.dom.WebGLRenderingContext

/**
  * Construct a type with the properties of T except for those in type K.
  */
type Omit[T, K /* <: /* keyof any */ java.lang.String */] = Pick[T, Exclude[/* keyof T */ java.lang.String, K]]

/**
  * Removes the 'this' parameter from a function type.
  */
/** NOTE: Conditional type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/conditional-types.html for an intro.
  * This RHS of the type alias is guess work. You should cast if it's not correct in your case.
  * TS definition: {{{
  unknown extends std.ThisParameterType<T> ? T : T extends (args : infer A): infer R ? (args : A): R : T
  }}}
  */
type OmitThisParameter[T] = T

type OnBeforeUnloadEventHandler = OnBeforeUnloadEventHandlerNonNull | Null

type OnBeforeUnloadEventHandlerNonNull = js.Function1[/* event */ org.scalajs.dom.Event, java.lang.String | Null]

type OnErrorEventHandler = OnErrorEventHandlerNonNull | Null

type OnErrorEventHandlerNonNull = js.Function5[
/* event */ org.scalajs.dom.Event | java.lang.String, 
/* source */ js.UndefOr[java.lang.String], 
/* lineno */ js.UndefOr[Double], 
/* colno */ js.UndefOr[Double], 
/* error */ js.UndefOr[js.Error], 
Any]

type ParameterDecorator = js.Function3[
/* target */ js.Object, 
/* propertyKey */ java.lang.String | js.Symbol, 
/* parameterIndex */ Double, 
Unit]

/**
  * Make all properties in T optional
  */
/** NOTE: Mapped type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/mapped-types.html for an intro.
  * This translation is imprecise and ignores the effect of the type mapping. 
  * TS definition: {{{
  {[ P in keyof T ]:? T[P]}
  }}}
  */
type Partial[T] = T

type PaymentRequestUpdateEventInit = EventInit

type PerformanceEntryList = js.Array[org.scalajs.dom.PerformanceEntry]

/** PerformanceMark is an abstract interface for PerformanceEntry objects with an entryType of "mark". Entries of this type are created by calling performance.mark() to add a named DOMHighResTimeStamp (the mark) to the browser's performance timeline. */
type PerformanceMark = org.scalajs.dom.PerformanceEntry

/** PerformanceMeasure is an abstract interface for PerformanceEntry objects with an entryType of "measure". Entries of this type are created by calling performance.measure() to add a named DOMHighResTimeStamp (the measure) between two marks to the browser's performance timeline. */
type PerformanceMeasure = org.scalajs.dom.PerformanceEntry

type PerformanceObserverCallback = js.Function2[/* entries */ PerformanceObserverEntryList, /* observer */ PerformanceObserver, Unit]

/**
  * From T, pick a set of properties whose keys are in the union K
  */
/** NOTE: Mapped type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/mapped-types.html for an intro.
  * This translation is imprecise and ignores the effect of the type mapping. 
  * TS definition: {{{
  {[ P in K ]: T[P]}
  }}}
  */
type Pick[T, K /* <: /* keyof T */ java.lang.String */] = T

type PositionCallback = js.Function1[/* position */ GeolocationPosition, Unit]

type PositionErrorCallback = js.Function1[/* positionError */ GeolocationPositionError, Unit]

type PromiseConstructorLike = Instantiable1[
/* executor */ js.Function2[
  /* resolve */ js.Function1[/* value */ js.Object | PromiseLike[js.Object], Unit], 
  /* reject */ js.Function1[/* reason */ js.UndefOr[Any], Unit], 
  Unit
], 
PromiseLike[js.Object]]

type PropertyDecorator = js.Function2[/* target */ js.Object, /* propertyKey */ java.lang.String | js.Symbol, Unit]

type PropertyDescriptorMap = /* standard es5 */
StringDictionary[js.PropertyDescriptor]

type PropertyKey = java.lang.String | Double | js.Symbol

type PublicKeyCredentialType = `public-key`

type QueuingStrategySize[T] = js.Function1[/* chunk */ T, Double]

type RTCAnswerOptions = RTCOfferAnswerOptions

type RTCIceCredentialType = password

type RTCPeerConnectionErrorCallback = js.Function1[/* error */ org.scalajs.dom.DOMException, Unit]

type RTCRtcpMuxPolicy = require

type RTCRtpReceiveParameters = RTCRtpParameters

type RTCSessionDescriptionCallback = js.Function1[/* description */ org.scalajs.dom.RTCSessionDescriptionInit, Unit]

type RTCStatsCallback = js.Function1[/* report */ org.scalajs.dom.RTCStatsReport, Unit]

type RangeError = js.Error

type ReadableStreamController[T] = ReadableStreamDefaultController[T]

type ReadableStreamReader[T] = ReadableStreamDefaultReader[T]

/**
  * Make all properties in T readonly
  */
/** NOTE: Mapped type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/mapped-types.html for an intro.
  * This translation is imprecise and ignores the effect of the type mapping. 
  * TS definition: {{{
  {readonly [ P in keyof T ]: T[P]}
  }}}
  */
type Readonly[T] = T

/**
  * Construct a type with a set of properties K of type T
  */
/** NOTE: Mapped type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/mapped-types.html for an intro.
  * This translation throws away the known field names. 
  * TS definition: {{{
  {[ P in K ]: T}
  }}}
  */
type Record[K /* <: /* keyof any */ java.lang.String */, T] = StringDictionary[T]

type ReferenceError = js.Error

/* Rewritten from type alias, can be one of: 
  - org.scalajs.dom.CanvasRenderingContext2D
  - typings.std.ImageBitmapRenderingContext
  - org.scalajs.dom.WebGLRenderingContext
  - typings.std.WebGL2RenderingContext
*/
type RenderingContext = _RenderingContext | org.scalajs.dom.CanvasRenderingContext2D | org.scalajs.dom.WebGLRenderingContext

type RequestInfo = org.scalajs.dom.Request | java.lang.String

/**
  * Make all properties in T required
  */
/** NOTE: Mapped type definitions are impossible to translate to Scala.
  * See https://www.typescriptlang.org/docs/handbook/2/mapped-types.html for an intro.
  * This translation is imprecise and ignores the effect of the type mapping. 
  * TS definition: {{{
  {[ P in keyof T ]: -? T[P]}
  }}}
  */
type Required[T] = T

type ResizeObserverCallback = js.Function2[/* entries */ js.Array[ResizeObserverEntry], /* observer */ ResizeObserver, Unit]

type SVGMatrix = DOMMatrix

type SVGPathSegClosePath = org.scalajs.dom.SVGPathSeg

type SVGPoint = DOMPoint

type SVGRect = org.scalajs.dom.DOMRect

type StaticRange = AbstractRange

type SyntaxError = js.Error

/* Rewritten from type alias, can be one of: 
  - typings.std.ImageBitmap
  - org.scalajs.dom.ImageData
  - org.scalajs.dom.HTMLImageElement
  - org.scalajs.dom.HTMLCanvasElement
  - org.scalajs.dom.HTMLVideoElement
  - typings.std.OffscreenCanvas
*/
type TexImageSource = _TexImageSource | org.scalajs.dom.ImageData | org.scalajs.dom.HTMLImageElement | org.scalajs.dom.HTMLCanvasElement | org.scalajs.dom.HTMLVideoElement

type TimerHandler = java.lang.String | js.Function

/* Rewritten from type alias, can be one of: 
  - js.typedarray.ArrayBuffer
  - org.scalajs.dom.MessagePort
  - typings.std.ImageBitmap
  - typings.std.OffscreenCanvas
*/
type Transferable = _Transferable | js.typedarray.ArrayBuffer | org.scalajs.dom.MessagePort

type TransformerFlushCallback[O] = js.Function1[/* controller */ TransformStreamDefaultController[O], Unit | PromiseLike[Unit]]

type TransformerStartCallback[O] = js.Function1[/* controller */ TransformStreamDefaultController[O], Unit | PromiseLike[Unit]]

type TransformerTransformCallback[I, O] = js.Function2[
/* chunk */ I, 
/* controller */ TransformStreamDefaultController[O], 
Unit | PromiseLike[Unit]]

type TypeError = js.Error

type URIError = js.Error

type Uint32List = js.typedarray.Uint32Array | js.Array[GLuint]

/**
  * Convert first character of string literal type to lowercase
  */
type Uncapitalize[S /* <: java.lang.String */] = /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify intrinsic */ Any

type UnderlyingSinkAbortCallback = js.Function1[/* reason */ Any, Unit | PromiseLike[Unit]]

type UnderlyingSinkCloseCallback = js.Function0[Unit | PromiseLike[Unit]]

type UnderlyingSinkStartCallback = js.Function1[/* controller */ WritableStreamDefaultController, Unit | PromiseLike[Unit]]

type UnderlyingSinkWriteCallback[W] = js.Function2[
/* chunk */ W, 
/* controller */ WritableStreamDefaultController, 
Unit | PromiseLike[Unit]]

type UnderlyingSourceCancelCallback = js.Function1[/* reason */ Any, Unit | PromiseLike[Unit]]

type UnderlyingSourcePullCallback[R] = js.Function1[/* controller */ ReadableStreamController[R], Unit | PromiseLike[Unit]]

type UnderlyingSourceStartCallback[R] = js.Function1[/* controller */ ReadableStreamController[R], Unit | PromiseLike[Unit]]

/**
  * Convert string literal type to uppercase
  */
type Uppercase[S /* <: java.lang.String */] = /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify intrinsic */ Any

type UvmEntries = js.Array[UvmEntry]

type UvmEntry = js.Array[Double]

type VibratePattern = Double | js.Array[Double]

type VoidFunction = js.Function0[Unit]

type WebKitCSSMatrix = DOMMatrix

type WindowProxy = org.scalajs.dom.Window

/** The XPathEvaluator interface allows to compile and evaluate XPath expressions. */
type XPathEvaluator = XPathEvaluatorBase

type XPathNSResolver = (js.Function1[/* prefix */ java.lang.String | Null, java.lang.String | Null]) | LookupNamespaceURI

type webkitURL = org.scalajs.dom.URL
