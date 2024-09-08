package typings.node.nodeColontestMod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object describe {
  
  /**
    * Alias for {@link suite}.
    *
    * The `describe()` function is imported from the `node:test` module.
    */
  inline def apply(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].apply().asInstanceOf[js.Promise[Unit]]
  inline def apply(fn: SuiteFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].apply(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String, options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: String, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: Unit, options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(name: Unit, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def apply(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].apply(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def apply(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].apply(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  
  @JSImport("node:test", "describe")
  @js.native
  val ^ : js.Any = js.native
  
  /**
    * Shorthand for marking a suite as `only`. This is the same as calling {@link describe} with `options.only` set to `true`.
    * @since v18.15.0
    */
  inline def only(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")().asInstanceOf[js.Promise[Unit]]
  inline def only(fn: SuiteFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String, options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: String, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: Unit, options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(name: Unit, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def only(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("only")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def only(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("only")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  
  /**
    * Shorthand for skipping a suite. This is the same as calling {@link describe} with `options.skip` set to `true`.
    * @since v18.15.0
    */
  inline def skip(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")().asInstanceOf[js.Promise[Unit]]
  inline def skip(fn: SuiteFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String, options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: String, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: Unit, options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(name: Unit, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def skip(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("skip")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def skip(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("skip")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  
  /**
    * Shorthand for marking a suite as `TODO`. This is the same as calling {@link describe} with `options.todo` set to `true`.
    * @since v18.15.0
    */
  inline def todo(): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")().asInstanceOf[js.Promise[Unit]]
  inline def todo(fn: SuiteFn): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(fn.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String, options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: String, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: Unit, options: Unit, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: Unit, options: TestOptions): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(name: Unit, options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(name.asInstanceOf[js.Any], options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
  inline def todo(options: TestOptions): js.Promise[Unit] = ^.asInstanceOf[js.Dynamic].applyDynamic("todo")(options.asInstanceOf[js.Any]).asInstanceOf[js.Promise[Unit]]
  inline def todo(options: TestOptions, fn: SuiteFn): js.Promise[Unit] = (^.asInstanceOf[js.Dynamic].applyDynamic("todo")(options.asInstanceOf[js.Any], fn.asInstanceOf[js.Any])).asInstanceOf[js.Promise[Unit]]
}
