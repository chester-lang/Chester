package typings.node.anon

import typings.node.nodeColontestMod.SuiteFn
import typings.node.nodeColontestMod.TestOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait Typeofdescribe extends StObject {
  
  /**
    * Alias for {@link suite}.
    *
    * The `describe()` function is imported from the `node:test` module.
    */
  def apply(): js.Promise[Unit] = js.native
  def apply(fn: SuiteFn): js.Promise[Unit] = js.native
  def apply(name: String): js.Promise[Unit] = js.native
  def apply(name: String, options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def apply(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def apply(name: String, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  def apply(name: Unit, options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def apply(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def apply(name: Unit, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  
  /**
    * Shorthand for marking a suite as `only`. This is the same as calling {@link describe} with `options.only` set to `true`.
    * @since v18.15.0
    */
  def only(): js.Promise[Unit] = js.native
  def only(fn: SuiteFn): js.Promise[Unit] = js.native
  def only(name: String): js.Promise[Unit] = js.native
  def only(name: String, fn: SuiteFn): js.Promise[Unit] = js.native
  def only(name: String, options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def only(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def only(name: String, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  def only(name: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def only(name: Unit, options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def only(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def only(name: Unit, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  def only(options: TestOptions): js.Promise[Unit] = js.native
  def only(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  
  /**
    * Shorthand for skipping a suite. This is the same as calling {@link describe} with `options.skip` set to `true`.
    * @since v18.15.0
    */
  def skip(): js.Promise[Unit] = js.native
  def skip(fn: SuiteFn): js.Promise[Unit] = js.native
  def skip(name: String): js.Promise[Unit] = js.native
  def skip(name: String, fn: SuiteFn): js.Promise[Unit] = js.native
  def skip(name: String, options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def skip(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def skip(name: String, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  def skip(name: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def skip(name: Unit, options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def skip(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def skip(name: Unit, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  def skip(options: TestOptions): js.Promise[Unit] = js.native
  def skip(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  
  /**
    * Shorthand for marking a suite as `TODO`. This is the same as calling {@link describe} with `options.todo` set to `true`.
    * @since v18.15.0
    */
  def todo(): js.Promise[Unit] = js.native
  def todo(fn: SuiteFn): js.Promise[Unit] = js.native
  def todo(name: String): js.Promise[Unit] = js.native
  def todo(name: String, fn: SuiteFn): js.Promise[Unit] = js.native
  def todo(name: String, options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def todo(name: String, options: TestOptions): js.Promise[Unit] = js.native
  def todo(name: String, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  def todo(name: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def todo(name: Unit, options: Unit, fn: SuiteFn): js.Promise[Unit] = js.native
  def todo(name: Unit, options: TestOptions): js.Promise[Unit] = js.native
  def todo(name: Unit, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
  def todo(options: TestOptions): js.Promise[Unit] = js.native
  def todo(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = js.native
}
