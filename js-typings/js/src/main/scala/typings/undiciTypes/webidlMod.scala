package typings.undiciTypes

import org.scalablytyped.runtime.StringDictionary
import typings.std.ArrayBufferLike
import typings.std.IterableIterator
import typings.std.Record
import typings.std.ReturnType
import typings.undiciTypes.anon.AllowShared
import typings.undiciTypes.anon.AllowSharedBoolean
import typings.undiciTypes.anon.AllowedValues
import typings.undiciTypes.anon.Argument
import typings.undiciTypes.anon.Header
import typings.undiciTypes.anon.LegacyNullToEmptyString
import typings.undiciTypes.anon.Length
import typings.undiciTypes.anon.Prefix
import typings.undiciTypes.anon.Strict
import typings.undiciTypes.anon.StrictBoolean
import typings.undiciTypes.undiciTypesStrings.BigInt
import typings.undiciTypes.undiciTypesStrings.Number
import typings.undiciTypes.undiciTypesStrings.Object
import typings.undiciTypes.undiciTypesStrings.Symbol
import typings.undiciTypes.undiciTypesStrings.Undefined
import typings.undiciTypes.undiciTypesStrings.signed
import typings.undiciTypes.undiciTypesStrings.unsigned
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object webidlMod {
  
  trait ConvertToIntOpts extends StObject {
    
    var clamp: js.UndefOr[Boolean] = js.undefined
    
    var enforceRange: js.UndefOr[Boolean] = js.undefined
  }
  object ConvertToIntOpts {
    
    inline def apply(): ConvertToIntOpts = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[ConvertToIntOpts]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: ConvertToIntOpts] (val x: Self) extends AnyVal {
      
      inline def setClamp(value: Boolean): Self = StObject.set(x, "clamp", value.asInstanceOf[js.Any])
      
      inline def setClampUndefined: Self = StObject.set(x, "clamp", js.undefined)
      
      inline def setEnforceRange(value: Boolean): Self = StObject.set(x, "enforceRange", value.asInstanceOf[js.Any])
      
      inline def setEnforceRangeUndefined: Self = StObject.set(x, "enforceRange", js.undefined)
    }
  }
  
  type Converter[T] = js.Function1[/* object */ Any, T]
  
  type RecordConverter[K /* <: String */, V] = js.Function1[/* object */ Any, Record[K, V]]
  
  type SequenceConverter[T] = js.Function2[/* object */ Any, /* iterable */ js.UndefOr[IterableIterator[T]], js.Array[T]]
  
  @js.native
  trait Webidl extends StObject {
    
    def argumentLengthCheck(args: Length, min: Double, context: String): Unit = js.native
    
    /**
      * @description Performs a brand-check on {@param V} to ensure it is a
      * {@param cls} object.
      */
    def brandCheck[Interface](V: Any, cls: Interface): /* asserts V is TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(Interface))),IArray())*/ Boolean = js.native
    def brandCheck[Interface](V: Any, cls: Interface, opts: Strict): /* asserts V is TsTypeRef(NoComments,TsQIdent(IArray(TsIdentSimple(Interface))),IArray())*/ Boolean = js.native
    
    var converters: WebidlConverters = js.native
    
    // TODO(@KhafraDev): a type could likely be implemented that can infer the return type
    // from the converters given?
    /**
      * Converts a value, V, to a WebIDL dictionary types. Allows limiting which keys are
      * allowed, values allowed, optional and required keys. Auto converts the value to
      * a type given a converter.
      */
    def dictionaryConverter(converters: js.Array[AllowedValues]): js.Function1[/* V */ Any, Record[String, Any]] = js.native
    
    var errors: WebidlErrors = js.native
    
    def illegalConstructor(): scala.Nothing = js.native
    
    /**
      * Similar to {@link Webidl.brandCheck} but allows skipping the check if third party
      * interfaces are allowed.
      */
    def interfaceConverter[Interface](cls: Interface): js.Function2[
        /* V */ Any, 
        /* opts */ js.UndefOr[StrictBoolean], 
        /* asserts V is TsTypeRef(Comments(1),TsQIdent(IArray(TsIdentSimple(any))),IArray())*/ Boolean
      ] = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#idl-nullable-type
      * @description allows a type, V, to be null
      */
    def nullableConverter[T](converter: Converter[T]): js.Function1[
        /* V */ Any, 
        (ReturnType[
          /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof converter */ Any
        ]) | Null
      ] = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-to-record
      * @description Convert a value, V, to a WebIDL record type.
      */
    def recordConverter[K /* <: String */, V](keyConverter: Converter[K], valueConverter: Converter[V]): RecordConverter[K, V] = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-sequence
      * @description Convert a value, V, to a WebIDL sequence type.
      */
    def sequenceConverter[Type](C: Converter[Type]): SequenceConverter[Type] = js.native
    
    var util: WebidlUtil = js.native
  }
  
  @js.native
  trait WebidlConverters
    extends StObject
       with /* Key */ StringDictionary[js.Function1[/* repeated */ Any, Any]] {
    
    /**
      * @see https://webidl.spec.whatwg.org/#idl-ArrayBuffer
      */
    def ArrayBuffer(V: Any): ArrayBufferLike = js.native
    def ArrayBuffer(V: Any, opts: AllowShared): js.typedarray.ArrayBuffer = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#BufferSource
      */
    def BufferSource(V: Any): (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify NodeJS.TypedArray */ Any) | ArrayBufferLike | js.typedarray.DataView = js.native
    def BufferSource(V: Any, opts: AllowSharedBoolean): (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify NodeJS.TypedArray */ Any) | ArrayBufferLike | js.typedarray.DataView = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-ByteString
      */
    def ByteString(V: Any, prefix: String, argument: String): String = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-DOMString
      */
    def DOMString(V: Any, prefix: String, argument: String): String = js.native
    def DOMString(V: Any, prefix: String, argument: String, opts: LegacyNullToEmptyString): String = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-buffer-source-types
      */
    def DataView(V: Any): js.typedarray.DataView = js.native
    def DataView(V: Any, opts: AllowSharedBoolean): js.typedarray.DataView = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-buffer-source-types
      */
    def TypedArray(
      V: Any,
      TypedArray: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify NodeJS.TypedArray */ Any
    ): (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify NodeJS.TypedArray */ Any) | ArrayBufferLike = js.native
    def TypedArray(
      V: Any,
      TypedArray: /* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify NodeJS.TypedArray */ Any,
      opts: AllowShared
    ): (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify NodeJS.TypedArray */ Any) | js.typedarray.ArrayBuffer = js.native
    def TypedArray(V: Any, TypedArray: ArrayBufferLike): (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify NodeJS.TypedArray */ Any) | ArrayBufferLike = js.native
    def TypedArray(V: Any, TypedArray: ArrayBufferLike, opts: AllowShared): (/* import warning: transforms.QualifyReferences#resolveTypeRef many Couldn't qualify NodeJS.TypedArray */ Any) | js.typedarray.ArrayBuffer = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-USVString
      */
    def USVString(V: Any): String = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-any
      */
    def any[Value](V: Value): Value = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-boolean
      */
    def boolean(V: Any): Boolean = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-long-long
      */
    def `long long`(V: Any): Double = js.native
    
    @JSName("record<ByteString, ByteString>")
    def `recordLessthansignByteStringComma ByteStringGreaterthansign`(`object`: Any): Record[String, String] = js.native
    @JSName("record<ByteString, ByteString>")
    var `recordLessthansignByteStringComma ByteStringGreaterthansign_Original`: RecordConverter[String, String] = js.native
    
    @JSName("sequence<ByteString>")
    def sequenceLessthansignByteStringGreaterthansign(`object`: Any): js.Array[String] = js.native
    @JSName("sequence<ByteString>")
    def sequenceLessthansignByteStringGreaterthansign(`object`: Any, iterable: IterableIterator[String]): js.Array[String] = js.native
    @JSName("sequence<ByteString>")
    var sequenceLessthansignByteStringGreaterthansign_Original: SequenceConverter[String] = js.native
    
    @JSName("sequence<sequence<ByteString>>")
    def sequenceLessthansignsequenceLessthansignByteStringGreaterthansignGreaterthansign(`object`: Any): js.Array[js.Array[String]] = js.native
    @JSName("sequence<sequence<ByteString>>")
    def sequenceLessthansignsequenceLessthansignByteStringGreaterthansignGreaterthansign(`object`: Any, iterable: IterableIterator[js.Array[String]]): js.Array[js.Array[String]] = js.native
    @JSName("sequence<sequence<ByteString>>")
    var sequenceLessthansignsequenceLessthansignByteStringGreaterthansignGreaterthansign_Original: SequenceConverter[js.Array[String]] = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-unsigned-long
      */
    def `unsigned long`(V: Any): Double = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-unsigned-long-long
      */
    def `unsigned long long`(V: Any): Double = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#es-unsigned-short
      */
    def `unsigned short`(V: Any): Double = js.native
    def `unsigned short`(V: Any, opts: ConvertToIntOpts): Double = js.native
  }
  
  trait WebidlErrors extends StObject {
    
    /**
      * @description Throw an error when conversion from one type to another has failed
      */
    def conversionFailed(opts: Argument): js.TypeError
    
    def exception(opts: Header): js.TypeError
    
    /**
      * @description Throw an error when an invalid argument is provided
      */
    def invalidArgument(opts: Prefix): js.TypeError
  }
  object WebidlErrors {
    
    inline def apply(
      conversionFailed: Argument => js.TypeError,
      exception: Header => js.TypeError,
      invalidArgument: Prefix => js.TypeError
    ): WebidlErrors = {
      val __obj = js.Dynamic.literal(conversionFailed = js.Any.fromFunction1(conversionFailed), exception = js.Any.fromFunction1(exception), invalidArgument = js.Any.fromFunction1(invalidArgument))
      __obj.asInstanceOf[WebidlErrors]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: WebidlErrors] (val x: Self) extends AnyVal {
      
      inline def setConversionFailed(value: Argument => js.TypeError): Self = StObject.set(x, "conversionFailed", js.Any.fromFunction1(value))
      
      inline def setException(value: Header => js.TypeError): Self = StObject.set(x, "exception", js.Any.fromFunction1(value))
      
      inline def setInvalidArgument(value: Prefix => js.TypeError): Self = StObject.set(x, "invalidArgument", js.Any.fromFunction1(value))
    }
  }
  
  @js.native
  trait WebidlUtil extends StObject {
    
    def ConvertToInt(
      V: Any,
      bitLength: Double,
      signedness: signed | unsigned,
      opts: Unit,
      prefix: String,
      argument: String
    ): Double = js.native
    /**
      * @see https://webidl.spec.whatwg.org/#abstract-opdef-converttoint
      */
    def ConvertToInt(
      V: Any,
      bitLength: Double,
      signedness: signed | unsigned,
      opts: ConvertToIntOpts,
      prefix: String,
      argument: String
    ): Double = js.native
    
    /**
      * @see https://webidl.spec.whatwg.org/#abstract-opdef-converttoint
      */
    def IntegerPart(N: Double): Double = js.native
    
    /**
      * Stringifies {@param V}
      */
    def Stringify(V: Any): String = js.native
    
    /**
      * @see https://tc39.es/ecma262/#sec-ecmascript-data-types-and-values
      */
    def Type(`object`: Any): Undefined | typings.undiciTypes.undiciTypesStrings.Boolean | typings.undiciTypes.undiciTypesStrings.String | Symbol | Number | BigInt | typings.undiciTypes.undiciTypesStrings.Null | Object = js.native
  }
}
