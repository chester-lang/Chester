package typings.node.anon

import typings.node.fsMod.BufferEncodingOption
import typings.node.fsMod.EncodingOption
import typings.node.fsMod.PathLike
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofrealpathSyncNative extends StObject {
  
  def apply(path: PathLike): String = js.native
  def apply(path: PathLike, options: BufferEncodingOption): typings.node.bufferMod.global.Buffer = js.native
  def apply(path: PathLike, options: EncodingOption): String = js.native
  
  def native(path: PathLike): String = js.native
  def native(path: PathLike, options: BufferEncodingOption): typings.node.bufferMod.global.Buffer = js.native
  def native(path: PathLike, options: EncodingOption): String = js.native
  @JSName("native")
  def native_Union(path: PathLike): String | typings.node.bufferMod.global.Buffer = js.native
  @JSName("native")
  def native_Union(path: PathLike, options: EncodingOption): String | typings.node.bufferMod.global.Buffer = js.native
}
