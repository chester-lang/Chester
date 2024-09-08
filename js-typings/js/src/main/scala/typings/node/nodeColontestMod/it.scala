package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object it {
  
  /**
    * Alias for {@link test}.
    *
    * The `it()` function is imported from the `node:test` module.
    * @since v18.6.0, v16.17.0
    */
  inline def apply(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].apply().asInstanceOf[js.Promise[Unit]]
  inline def apply(fn: TestFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].apply(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def apply(options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  
  @JSImport("node:test", "it")
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * Shorthand for marking a test as `only`. This is the same as calling {@link it} with `options.only` set to `true`.
    * @since v18.15.0
    */
  inline def only(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")().asInstanceOf[js.Promise[Unit]]
  inline def only(fn: TestFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def only(options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  
  /**
    * Shorthand for skipping a test. This is the same as calling {@link it} with `options.skip` set to `true`.
    */
  inline def skip(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")().asInstanceOf[js.Promise[Unit]]
  inline def skip(fn: TestFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def skip(options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  
  /**
    * Shorthand for marking a test as `TODO`. This is the same as calling {@link it} with `options.todo` set to `true`.
    */
  inline def todo(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")().asInstanceOf[js.Promise[Unit]]
  inline def todo(fn: TestFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: Unit, options: Unit, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: Unit, options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def todo(options: TestOptions, fn: TestFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
}
