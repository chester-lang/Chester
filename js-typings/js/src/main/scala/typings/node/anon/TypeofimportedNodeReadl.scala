package typings.node.anon

import org.scalablytyped.runtime.Instantiable4
import typings.node.globalsMod.global.NodeJS.ReadableStream
import typings.node.globalsMod.global.NodeJS.WritableStream
import typings.node.readlineMod.AsyncCompleter
import typings.node.readlineMod.Completer
import typings.node.readlineMod.Direction
import typings.node.readlineMod.Interface
import typings.node.readlineMod.ReadLineOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedNodeReadl extends StObject {
  
  var Interface: Instantiable4[
    /* input */ ReadableStream, 
    /* output */ js.UndefOr[WritableStream], 
    /* completer */ js.UndefOr[Completer | AsyncCompleter], 
    /* terminal */ js.UndefOr[Boolean], 
    typings.node.nodeColonreadlineMod.Interface
  ] = js.native
  
  def clearLine(stream: WritableStream, dir: Direction): Boolean = js.native
  def clearLine(stream: WritableStream, dir: Direction, callback: js.Function0[Unit]): Boolean = js.native
  
  def clearScreenDown(stream: WritableStream): Boolean = js.native
  def clearScreenDown(stream: WritableStream, callback: js.Function0[Unit]): Boolean = js.native
  
  def createInterface(input: ReadableStream): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: Unit, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: AsyncCompleter): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: AsyncCompleter, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: Completer): Interface = js.native
  def createInterface(input: ReadableStream, output: Unit, completer: Completer, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: Unit, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: AsyncCompleter): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: AsyncCompleter, terminal: Boolean): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: Completer): Interface = js.native
  def createInterface(input: ReadableStream, output: WritableStream, completer: Completer, terminal: Boolean): Interface = js.native
  def createInterface(options: ReadLineOptions): Interface = js.native
  
  def cursorTo(stream: WritableStream, x: Double): Boolean = js.native
  def cursorTo(stream: WritableStream, x: Double, y: Double): Boolean = js.native
  def cursorTo(stream: WritableStream, x: Double, y: Double, callback: js.Function0[Unit]): Boolean = js.native
  def cursorTo(stream: WritableStream, x: Double, y: Unit, callback: js.Function0[Unit]): Boolean = js.native
  
  def emitKeypressEvents(stream: ReadableStream): Unit = js.native
  def emitKeypressEvents(stream: ReadableStream, readlineInterface: Interface): Unit = js.native
  
  def moveCursor(stream: WritableStream, dx: Double, dy: Double): Boolean = js.native
  def moveCursor(stream: WritableStream, dx: Double, dy: Double, callback: js.Function0[Unit]): Boolean = js.native
  
  val promises: TypeofpromisesInterface = js.native
}
