package typings.node.anon

import typings.node.nodeColontestMod.TestFn
import typings.node.nodeColontestMod.TestOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofit extends StObject {
  
  /**
    * Alias for {@link test}.
    *
    * The `it()` function is imported from the `node:test` module.
    * @since v18.6.0, v16.17.0
    */
  def apply(): js.Promise[Unit] = js.native
  def apply(fn: TestFn): js.Promise[Unit] = js.native
  def apply(name: String): js.Promise[Unit] = js.native
  def apply(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def apply(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def apply(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def apply(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def apply(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def apply(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  
  /**
    * Shorthand for marking a test as `only`. This is the same as calling {@link it} with `options.only` set to `true`.
    * @since v18.15.0
    */
  def only(): js.Promise[Unit] = js.native
  def only(fn: TestFn): js.Promise[Unit] = js.native
  def only(name: String): js.Promise[Unit] = js.native
  def only(name: String, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def only(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def only(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def only(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def only(options: TestOptions): js.Promise[Unit] = js.native
  def only(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  
  /**
    * Shorthand for skipping a test. This is the same as calling {@link it} with `options.skip` set to `true`.
    */
  def skip(): js.Promise[Unit] = js.native
  def skip(fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: String): js.Promise[Unit] = js.native
  def skip(name: String, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def skip(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def skip(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def skip(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def skip(options: TestOptions): js.Promise[Unit] = js.native
  def skip(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  
  /**
    * Shorthand for marking a test as `TODO`. This is the same as calling {@link it} with `options.todo` set to `true`.
    */
  def todo(): js.Promise[Unit] = js.native
  def todo(fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: String): js.Promise[Unit] = js.native
  def todo(name: String, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def todo(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = js.native
  def todo(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def todo(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
  def todo(options: TestOptions): js.Promise[Unit] = js.native
  def todo(options: TestOptions, fn: TestFn): js.Promise[Unit] = js.native
}
