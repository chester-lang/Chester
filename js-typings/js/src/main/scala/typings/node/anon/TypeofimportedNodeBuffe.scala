package typings.node.anon

import org.scalablytyped.runtime.Instantiable2
import org.scalablytyped.runtime.Instantiable3
import typings.node.bufferMod.Blob
import typings.node.bufferMod.BlobOptions
import typings.node.bufferMod.FileOptions
import typings.node.bufferMod.TranscodeEncoding
import typings.node.bufferMod.global.BufferConstructor
import typings.node.cryptoMod.BinaryLike
import typings.node.globalsMod.global.NodeJS.TypedArray
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedNodeBuffe extends StObject {
  
  var Blob: Instantiable2[
    /* sources */ js.Array[js.typedarray.ArrayBuffer | BinaryLike | typings.node.bufferMod.Blob], 
    /* options */ js.UndefOr[BlobOptions], 
    typings.node.nodeColonbufferMod.Blob
  ] = js.native
  
  var Buffer: BufferConstructor = js.native
  
  var File: Instantiable3[
    /* sources */ js.Array[BinaryLike | Blob], 
    /* fileName */ String, 
    /* options */ js.UndefOr[FileOptions], 
    typings.node.nodeColonbufferMod.File
  ] = js.native
  
  val INSPECT_MAX_BYTES: Double = js.native
  
  val SlowBuffer: Instantiable = js.native
  
  val constants: MAXLENGTH = js.native
  
  def isAscii(input: js.typedarray.ArrayBuffer): Boolean = js.native
  def isAscii(input: typings.node.bufferMod.global.Buffer): Boolean = js.native
  def isAscii(input: TypedArray): Boolean = js.native
  
  def isUtf8(input: js.typedarray.ArrayBuffer): Boolean = js.native
  def isUtf8(input: typings.node.bufferMod.global.Buffer): Boolean = js.native
  def isUtf8(input: TypedArray): Boolean = js.native
  
  val kMaxLength: Double = js.native
  
  val kStringMaxLength: Double = js.native
  
  def resolveObjectURL(id: String): js.UndefOr[Blob] = js.native
  
  def transcode(source: js.typedarray.Uint8Array, fromEnc: TranscodeEncoding, toEnc: TranscodeEncoding): typings.node.bufferMod.global.Buffer = js.native
}
