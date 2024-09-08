package typings.node.anon

import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import typings.node.zlibMod.BrotliCompress_
import typings.node.zlibMod.BrotliDecompress_
import typings.node.zlibMod.BrotliOptions
import typings.node.zlibMod.DeflateRaw_
import typings.node.zlibMod.Deflate_
import typings.node.zlibMod.Gunzip_
import typings.node.zlibMod.Gzip_
import typings.node.zlibMod.InflateRaw_
import typings.node.zlibMod.Inflate_
import typings.node.zlibMod.InputType
import typings.node.zlibMod.Unzip_
import typings.node.zlibMod.ZlibOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedNodeZlib extends StObject {
  
  /** @deprecated */
  val Z_ASCII: Double = js.native
  
  /** @deprecated Use `constants.Z_BEST_COMPRESSION` */
  val Z_BEST_COMPRESSION: Double = js.native
  
  /** @deprecated Use `constants.Z_BEST_SPEED` */
  val Z_BEST_SPEED: Double = js.native
  
  /** @deprecated */
  val Z_BINARY: Double = js.native
  
  /** @deprecated Use `constants.Z_BLOCK` */
  val Z_BLOCK: Double = js.native
  
  /** @deprecated Use `constants.Z_BUF_ERROR` */
  val Z_BUF_ERROR: Double = js.native
  
  /** @deprecated Use `constants.Z_DATA_ERROR` */
  val Z_DATA_ERROR: Double = js.native
  
  /** @deprecated Use `constants.Z_DEFAULT_COMPRESSION` */
  val Z_DEFAULT_COMPRESSION: Double = js.native
  
  /** @deprecated Use `constants.Z_DEFAULT_STRATEGY` */
  val Z_DEFAULT_STRATEGY: Double = js.native
  
  /** @deprecated */
  val Z_DEFLATED: Double = js.native
  
  /** @deprecated Use `constants.Z_ERRNO` */
  val Z_ERRNO: Double = js.native
  
  // Compression strategy.
  /** @deprecated Use `constants.Z_FILTERED` */
  val Z_FILTERED: Double = js.native
  
  /** @deprecated Use `constants.Z_FINISH` */
  val Z_FINISH: Double = js.native
  
  /** @deprecated Use `constants.Z_FIXED` */
  val Z_FIXED: Double = js.native
  
  /** @deprecated Use `constants.Z_FULL_FLUSH` */
  val Z_FULL_FLUSH: Double = js.native
  
  /** @deprecated Use `constants.Z_HUFFMAN_ONLY` */
  val Z_HUFFMAN_ONLY: Double = js.native
  
  /** @deprecated Use `constants.Z_MEM_ERROR` */
  val Z_MEM_ERROR: Double = js.native
  
  /** @deprecated Use `constants.Z_NEED_DICT` */
  val Z_NEED_DICT: Double = js.native
  
  // Compression levels.
  /** @deprecated Use `constants.Z_NO_COMPRESSION` */
  val Z_NO_COMPRESSION: Double = js.native
  
  // Allowed flush values.
  /** @deprecated Use `constants.Z_NO_FLUSH` */
  val Z_NO_FLUSH: Double = js.native
  
  // Return codes for the compression/decompression functions.
  // Negative values are errors, positive values are used for special but normal events.
  /** @deprecated Use `constants.Z_OK` */
  val Z_OK: Double = js.native
  
  /** @deprecated Use `constants.Z_PARTIAL_FLUSH` */
  val Z_PARTIAL_FLUSH: Double = js.native
  
  /** @deprecated Use `constants.Z_RLE` */
  val Z_RLE: Double = js.native
  
  /** @deprecated Use `constants.Z_STREAM_END` */
  val Z_STREAM_END: Double = js.native
  
  /** @deprecated Use `constants.Z_STREAM_ERROR` */
  val Z_STREAM_ERROR: Double = js.native
  
  /** @deprecated Use `constants.Z_SYNC_FLUSH` */
  val Z_SYNC_FLUSH: Double = js.native
  
  /** @deprecated */
  val Z_TEXT: Double = js.native
  
  /** @deprecated Use `constants.Z_TREES` */
  val Z_TREES: Double = js.native
  
  /** @deprecated  */
  val Z_UNKNOWN: Double = js.native
  
  /** @deprecated Use `constants.Z_VERSION_ERROR` */
  val Z_VERSION_ERROR: Double = js.native
  
  val brotliCompress: TypeofbrotliCompress = js.native
  
  /**
    * Compress a chunk of data with `BrotliCompress`.
    * @since v11.7.0, v10.16.0
    */
  def brotliCompressSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def brotliCompressSync(buf: InputType, options: BrotliOptions): typings.node.bufferMod.global.Buffer = js.native
  
  val brotliDecompress: TypeofbrotliDecompress = js.native
  
  /**
    * Decompress a chunk of data with `BrotliDecompress`.
    * @since v11.7.0, v10.16.0
    */
  def brotliDecompressSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def brotliDecompressSync(buf: InputType, options: BrotliOptions): typings.node.bufferMod.global.Buffer = js.native
  
  val constants: TypeofconstantsBROTLIDECODE = js.native
  
  /**
    * Computes a 32-bit [Cyclic Redundancy Check](https://en.wikipedia.org/wiki/Cyclic_redundancy_check) checksum of `data`.
    * If `value` is specified, it is used as the starting value of the checksum, otherwise, 0 is used as the starting value.
    * @param data When `data` is a string, it will be encoded as UTF-8 before being used for computation.
    * @param value An optional starting value. It must be a 32-bit unsigned integer. @default 0
    * @returns A 32-bit unsigned integer containing the checksum.
    * @since v22.2.0
    */
  def crc32(data: String): Double = js.native
  def crc32(data: String, value: Double): Double = js.native
  def crc32(data: typings.node.bufferMod.global.Buffer): Double = js.native
  def crc32(data: typings.node.bufferMod.global.Buffer, value: Double): Double = js.native
  def crc32(data: ArrayBufferView): Double = js.native
  def crc32(data: ArrayBufferView, value: Double): Double = js.native
  
  /**
    * Creates and returns a new `BrotliCompress` object.
    * @since v11.7.0, v10.16.0
    */
  def createBrotliCompress(): BrotliCompress_ = js.native
  def createBrotliCompress(options: BrotliOptions): BrotliCompress_ = js.native
  
  /**
    * Creates and returns a new `BrotliDecompress` object.
    * @since v11.7.0, v10.16.0
    */
  def createBrotliDecompress(): BrotliDecompress_ = js.native
  def createBrotliDecompress(options: BrotliOptions): BrotliDecompress_ = js.native
  
  /**
    * Creates and returns a new `Deflate` object.
    * @since v0.5.8
    */
  def createDeflate(): Deflate_ = js.native
  def createDeflate(options: ZlibOptions): Deflate_ = js.native
  
  /**
    * Creates and returns a new `DeflateRaw` object.
    *
    * An upgrade of zlib from 1.2.8 to 1.2.11 changed behavior when `windowBits` is set to 8 for raw deflate streams. zlib would automatically set `windowBits` to 9 if was initially set to 8. Newer
    * versions of zlib will throw an exception,
    * so Node.js restored the original behavior of upgrading a value of 8 to 9,
    * since passing `windowBits = 9` to zlib actually results in a compressed stream
    * that effectively uses an 8-bit window only.
    * @since v0.5.8
    */
  def createDeflateRaw(): DeflateRaw_ = js.native
  def createDeflateRaw(options: ZlibOptions): DeflateRaw_ = js.native
  
  /**
    * Creates and returns a new `Gunzip` object.
    * @since v0.5.8
    */
  def createGunzip(): Gunzip_ = js.native
  def createGunzip(options: ZlibOptions): Gunzip_ = js.native
  
  /**
    * Creates and returns a new `Gzip` object.
    * See `example`.
    * @since v0.5.8
    */
  def createGzip(): Gzip_ = js.native
  def createGzip(options: ZlibOptions): Gzip_ = js.native
  
  /**
    * Creates and returns a new `Inflate` object.
    * @since v0.5.8
    */
  def createInflate(): Inflate_ = js.native
  def createInflate(options: ZlibOptions): Inflate_ = js.native
  
  /**
    * Creates and returns a new `InflateRaw` object.
    * @since v0.5.8
    */
  def createInflateRaw(): InflateRaw_ = js.native
  def createInflateRaw(options: ZlibOptions): InflateRaw_ = js.native
  
  /**
    * Creates and returns a new `Unzip` object.
    * @since v0.5.8
    */
  def createUnzip(): Unzip_ = js.native
  def createUnzip(options: ZlibOptions): Unzip_ = js.native
  
  val deflate: Typeofdeflate = js.native
  
  val deflateRaw: TypeofdeflateRaw = js.native
  
  /**
    * Compress a chunk of data with `DeflateRaw`.
    * @since v0.11.12
    */
  def deflateRawSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def deflateRawSync(buf: InputType, options: ZlibOptions): typings.node.bufferMod.global.Buffer = js.native
  
  /**
    * Compress a chunk of data with `Deflate`.
    * @since v0.11.12
    */
  def deflateSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def deflateSync(buf: InputType, options: ZlibOptions): typings.node.bufferMod.global.Buffer = js.native
  
  val gunzip: Typeofgunzip = js.native
  
  /**
    * Decompress a chunk of data with `Gunzip`.
    * @since v0.11.12
    */
  def gunzipSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def gunzipSync(buf: InputType, options: ZlibOptions): typings.node.bufferMod.global.Buffer = js.native
  
  val gzip: Typeofgzip = js.native
  
  /**
    * Compress a chunk of data with `Gzip`.
    * @since v0.11.12
    */
  def gzipSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def gzipSync(buf: InputType, options: ZlibOptions): typings.node.bufferMod.global.Buffer = js.native
  
  val inflate: Typeofinflate = js.native
  
  val inflateRaw: TypeofinflateRaw = js.native
  
  /**
    * Decompress a chunk of data with `InflateRaw`.
    * @since v0.11.12
    */
  def inflateRawSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def inflateRawSync(buf: InputType, options: ZlibOptions): typings.node.bufferMod.global.Buffer = js.native
  
  /**
    * Decompress a chunk of data with `Inflate`.
    * @since v0.11.12
    */
  def inflateSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def inflateSync(buf: InputType, options: ZlibOptions): typings.node.bufferMod.global.Buffer = js.native
  
  val unzip: Typeofunzip = js.native
  
  /**
    * Decompress a chunk of data with `Unzip`.
    * @since v0.11.12
    */
  def unzipSync(buf: InputType): typings.node.bufferMod.global.Buffer = js.native
  def unzipSync(buf: InputType, options: ZlibOptions): typings.node.bufferMod.global.Buffer = js.native
}
