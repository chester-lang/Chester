package typings.node.anon

import typings.node.childProcessMod.ChildProcess
import typings.node.childProcessMod.ExecFileException
import typings.node.childProcessMod.ExecFileOptions
import typings.node.childProcessMod.ExecFileOptionsWithBufferEncoding
import typings.node.childProcessMod.ExecFileOptionsWithOtherEncoding
import typings.node.childProcessMod.ExecFileOptionsWithStringEncoding
import typings.node.childProcessMod.PromiseWithChild
import typings.node.fsMod.ObjectEncodingOptions
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofexecFile extends StObject {
  
  /**
    * The `child_process.execFile()` function is similar to {@link exec} except that it does not spawn a shell by default. Rather, the specified
    * executable `file` is spawned directly as a new process making it slightly more
    * efficient than {@link exec}.
    *
    * The same options as {@link exec} are supported. Since a shell is
    * not spawned, behaviors such as I/O redirection and file globbing are not
    * supported.
    *
    * ```js
    * const { execFile } = require('node:child_process');
    * const child = execFile('node', ['--version'], (error, stdout, stderr) => {
    *   if (error) {
    *     throw error;
    *   }
    *   console.log(stdout);
    * });
    * ```
    *
    * The `stdout` and `stderr` arguments passed to the callback will contain the
    * stdout and stderr output of the child process. By default, Node.js will decode
    * the output as UTF-8 and pass strings to the callback. The `encoding` option
    * can be used to specify the character encoding used to decode the stdout and
    * stderr output. If `encoding` is `'buffer'`, or an unrecognized character
    * encoding, `Buffer` objects will be passed to the callback instead.
    *
    * If this method is invoked as its `util.promisify()` ed version, it returns
    * a `Promise` for an `Object` with `stdout` and `stderr` properties. The returned `ChildProcess` instance is attached to the `Promise` as a `child` property. In
    * case of an error (including any error resulting in an exit code other than 0), a
    * rejected promise is returned, with the same `error` object given in the
    * callback, but with two additional properties `stdout` and `stderr`.
    *
    * ```js
    * const util = require('node:util');
    * const execFile = util.promisify(require('node:child_process').execFile);
    * async function getVersion() {
    *   const { stdout } = await execFile('node', ['--version']);
    *   console.log(stdout);
    * }
    * getVersion();
    * ```
    *
    * **If the `shell` option is enabled, do not pass unsanitized user input to this**
    * **function. Any input containing shell metacharacters may be used to trigger**
    * **arbitrary command execution.**
    *
    * If the `signal` option is enabled, calling `.abort()` on the corresponding `AbortController` is similar to calling `.kill()` on the child process except
    * the error passed to the callback will be an `AbortError`:
    *
    * ```js
    * const { execFile } = require('node:child_process');
    * const controller = new AbortController();
    * const { signal } = controller;
    * const child = execFile('node', ['--version'], { signal }, (error) => {
    *   console.error(error); // an AbortError
    * });
    * controller.abort();
    * ```
    * @since v0.1.91
    * @param file The name or path of the executable file to run.
    * @param args List of string arguments.
    * @param callback Called with the output when process terminates.
    */
  def apply(file: String): ChildProcess = js.native
  def apply(file: String, args: js.Array[String]): ChildProcess = js.native
  def apply(
    file: String,
    args: js.Array[String],
    callback: js.Function3[/* error */ ExecFileException | Null, /* stdout */ String, /* stderr */ String, Unit]
  ): ChildProcess = js.native
  def apply(file: String, args: js.Array[String], options: ObjectEncodingOptions & ExecFileOptions): ChildProcess = js.native
  def apply(
    file: String,
    args: js.Array[String],
    options: (ObjectEncodingOptions & ExecFileOptions) | ExecFileOptions,
    callback: js.Function3[
      ExecFileException | Null, 
      typings.node.bufferMod.global.Buffer | (/* stdout */ String), 
      typings.node.bufferMod.global.Buffer | (/* stderr */ String), 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: js.Array[String],
    options: Null,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: js.Array[String],
    options: Unit,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: js.Array[String],
    options: ExecFileOptionsWithBufferEncoding,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ typings.node.bufferMod.global.Buffer, 
      /* stderr */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: js.Array[String],
    options: ExecFileOptionsWithOtherEncoding,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: js.Array[String],
    options: ExecFileOptionsWithStringEncoding,
    callback: js.Function3[/* error */ ExecFileException | Null, /* stdout */ String, /* stderr */ String, Unit]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Null,
    callback: js.Function3[
      ExecFileException | Null, 
      typings.node.bufferMod.global.Buffer | (/* stdout */ String), 
      typings.node.bufferMod.global.Buffer | (/* stderr */ String), 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(file: String, args: Null, options: ObjectEncodingOptions & ExecFileOptions): ChildProcess = js.native
  def apply(
    file: String,
    args: Null,
    options: (ObjectEncodingOptions & ExecFileOptions) | ExecFileOptions,
    callback: js.Function3[
      ExecFileException | Null, 
      typings.node.bufferMod.global.Buffer | (/* stdout */ String), 
      typings.node.bufferMod.global.Buffer | (/* stderr */ String), 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Null,
    options: Null,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Null,
    options: Unit,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Null,
    options: ExecFileOptionsWithBufferEncoding,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ typings.node.bufferMod.global.Buffer, 
      /* stderr */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Null,
    options: ExecFileOptionsWithOtherEncoding,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Null,
    options: ExecFileOptionsWithStringEncoding,
    callback: js.Function3[/* error */ ExecFileException | Null, /* stdout */ String, /* stderr */ String, Unit]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Unit,
    callback: js.Function3[
      ExecFileException | Null, 
      typings.node.bufferMod.global.Buffer | (/* stdout */ String), 
      typings.node.bufferMod.global.Buffer | (/* stderr */ String), 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(file: String, args: Unit, options: ObjectEncodingOptions & ExecFileOptions): ChildProcess = js.native
  def apply(
    file: String,
    args: Unit,
    options: (ObjectEncodingOptions & ExecFileOptions) | ExecFileOptions,
    callback: js.Function3[
      ExecFileException | Null, 
      typings.node.bufferMod.global.Buffer | (/* stdout */ String), 
      typings.node.bufferMod.global.Buffer | (/* stderr */ String), 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Unit,
    options: Null,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Unit,
    options: Unit,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Unit,
    options: ExecFileOptionsWithBufferEncoding,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ typings.node.bufferMod.global.Buffer, 
      /* stderr */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Unit,
    options: ExecFileOptionsWithOtherEncoding,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  def apply(
    file: String,
    args: Unit,
    options: ExecFileOptionsWithStringEncoding,
    callback: js.Function3[/* error */ ExecFileException | Null, /* stdout */ String, /* stderr */ String, Unit]
  ): ChildProcess = js.native
  // no `options` definitely means stdout/stderr are `string`.
  def apply(
    file: String,
    callback: js.Function3[/* error */ ExecFileException | Null, /* stdout */ String, /* stderr */ String, Unit]
  ): ChildProcess = js.native
  def apply(file: String, options: ObjectEncodingOptions & ExecFileOptions): ChildProcess = js.native
  // `options` without an `encoding` means stdout/stderr are definitely `string`.
  def apply(
    file: String,
    options: (ObjectEncodingOptions & ExecFileOptions) | ExecFileOptions,
    callback: js.Function3[
      ExecFileException | Null, 
      typings.node.bufferMod.global.Buffer | (/* stdout */ String), 
      typings.node.bufferMod.global.Buffer | (/* stderr */ String), 
      Unit
    ]
  ): ChildProcess = js.native
  // `options` with `"buffer"` or `null` for `encoding` means stdout/stderr are definitely `Buffer`.
  def apply(
    file: String,
    options: ExecFileOptionsWithBufferEncoding,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ typings.node.bufferMod.global.Buffer, 
      /* stderr */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  // `options` with an `encoding` whose type is `string` means stdout/stderr could either be `Buffer` or `string`.
  // There is no guarantee the `encoding` is unknown as `string` is a superset of `BufferEncoding`.
  def apply(
    file: String,
    options: ExecFileOptionsWithOtherEncoding,
    callback: js.Function3[
      /* error */ ExecFileException | Null, 
      /* stdout */ String | typings.node.bufferMod.global.Buffer, 
      /* stderr */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): ChildProcess = js.native
  // `options` with well known `encoding` means stdout/stderr are definitely `string`.
  def apply(
    file: String,
    options: ExecFileOptionsWithStringEncoding,
    callback: js.Function3[/* error */ ExecFileException | Null, /* stdout */ String, /* stderr */ String, Unit]
  ): ChildProcess = js.native
  
  def __promisify__(file: String): PromiseWithChild[Stderr] = js.native
  def __promisify__(file: String, args: js.Array[String]): PromiseWithChild[Stderr] = js.native
  def __promisify__(
    file: String,
    args: js.Array[String],
    options: (ObjectEncodingOptions & ExecFileOptions) | ExecFileOptions
  ): PromiseWithChild[Stderr] = js.native
  def __promisify__(file: String, args: js.Array[String], options: ExecFileOptionsWithBufferEncoding): PromiseWithChild[Stdout] = js.native
  def __promisify__(file: String, args: js.Array[String], options: ExecFileOptionsWithOtherEncoding): PromiseWithChild[StderrStdout] = js.native
  def __promisify__(file: String, args: js.Array[String], options: ExecFileOptionsWithStringEncoding): PromiseWithChild[Stderr] = js.native
  def __promisify__(file: String, args: Null, options: (ObjectEncodingOptions & ExecFileOptions) | ExecFileOptions): PromiseWithChild[Stderr] = js.native
  def __promisify__(file: String, args: Null, options: ExecFileOptionsWithBufferEncoding): PromiseWithChild[Stdout] = js.native
  def __promisify__(file: String, args: Null, options: ExecFileOptionsWithOtherEncoding): PromiseWithChild[StderrStdout] = js.native
  def __promisify__(file: String, args: Null, options: ExecFileOptionsWithStringEncoding): PromiseWithChild[Stderr] = js.native
  def __promisify__(file: String, args: Unit, options: (ObjectEncodingOptions & ExecFileOptions) | ExecFileOptions): PromiseWithChild[Stderr] = js.native
  def __promisify__(file: String, args: Unit, options: ExecFileOptionsWithBufferEncoding): PromiseWithChild[Stdout] = js.native
  def __promisify__(file: String, args: Unit, options: ExecFileOptionsWithOtherEncoding): PromiseWithChild[StderrStdout] = js.native
  def __promisify__(file: String, args: Unit, options: ExecFileOptionsWithStringEncoding): PromiseWithChild[Stderr] = js.native
  def __promisify__(file: String, options: (ObjectEncodingOptions & ExecFileOptions) | ExecFileOptions): PromiseWithChild[Stderr] = js.native
  def __promisify__(file: String, options: ExecFileOptionsWithBufferEncoding): PromiseWithChild[Stdout] = js.native
  def __promisify__(file: String, options: ExecFileOptionsWithOtherEncoding): PromiseWithChild[StderrStdout] = js.native
  def __promisify__(file: String, options: ExecFileOptionsWithStringEncoding): PromiseWithChild[Stderr] = js.native
}
