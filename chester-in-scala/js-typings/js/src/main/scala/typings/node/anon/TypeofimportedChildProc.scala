package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import typings.node.childProcessMod.ChildProcess
import typings.node.childProcessMod.ChildProcessByStdio
import typings.node.childProcessMod.ChildProcessWithoutNullStreams
import typings.node.childProcessMod.ExecFileSyncOptions
import typings.node.childProcessMod.ExecFileSyncOptionsWithBufferEncoding
import typings.node.childProcessMod.ExecFileSyncOptionsWithStringEncoding
import typings.node.childProcessMod.ExecSyncOptions
import typings.node.childProcessMod.ExecSyncOptionsWithBufferEncoding
import typings.node.childProcessMod.ExecSyncOptionsWithStringEncoding
import typings.node.childProcessMod.ForkOptions
import typings.node.childProcessMod.SpawnOptions
import typings.node.childProcessMod.SpawnOptionsWithStdioTuple
import typings.node.childProcessMod.SpawnOptionsWithoutStdio
import typings.node.childProcessMod.SpawnSyncOptions
import typings.node.childProcessMod.SpawnSyncOptionsWithBufferEncoding
import typings.node.childProcessMod.SpawnSyncOptionsWithStringEncoding
import typings.node.childProcessMod.SpawnSyncReturns
import typings.node.childProcessMod.StdioNull
import typings.node.childProcessMod.StdioPipe
import typings.node.nodeColonstreamMod.Writable
import typings.node.nodeColonurlMod.URL
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedChildProc extends StObject {
  
  /**
    * Instances of the `ChildProcess` represent spawned child processes.
    *
    * Instances of `ChildProcess` are not intended to be created directly. Rather,
    * use the {@link spawn}, {@link exec},{@link execFile}, or {@link fork} methods to create
    * instances of `ChildProcess`.
    * @since v2.2.0
    */
  var ChildProcess: Instantiable0[typings.node.childProcessMod.ChildProcess] = js.native
  
  val exec: Typeofexec = js.native
  
  val execFile: TypeofexecFile = js.native
  
  def execFileSync(file: String): String | typings.node.bufferMod.global.Buffer = js.native
  def execFileSync(file: String, args: js.Array[String]): String | typings.node.bufferMod.global.Buffer = js.native
  def execFileSync(file: String, args: js.Array[String], options: ExecFileSyncOptions): String | typings.node.bufferMod.global.Buffer = js.native
  def execFileSync(file: String, args: js.Array[String], options: ExecFileSyncOptionsWithBufferEncoding): typings.node.bufferMod.global.Buffer = js.native
  def execFileSync(file: String, args: js.Array[String], options: ExecFileSyncOptionsWithStringEncoding): String = js.native
  def execFileSync(file: String, args: Unit, options: ExecFileSyncOptions): String | typings.node.bufferMod.global.Buffer = js.native
  def execFileSync(file: String, options: ExecFileSyncOptions): String | typings.node.bufferMod.global.Buffer = js.native
  def execFileSync(file: String, options: ExecFileSyncOptionsWithBufferEncoding): typings.node.bufferMod.global.Buffer = js.native
  def execFileSync(file: String, options: ExecFileSyncOptionsWithStringEncoding): String = js.native
  /**
    * The `child_process.execFileSync()` method is generally identical to {@link execFile} with the exception that the method will not
    * return until the child process has fully closed. When a timeout has been
    * encountered and `killSignal` is sent, the method won't return until the process
    * has completely exited.
    *
    * If the child process intercepts and handles the `SIGTERM` signal and
    * does not exit, the parent process will still wait until the child process has
    * exited.
    *
    * If the process times out or has a non-zero exit code, this method will throw an `Error` that will include the full result of the underlying {@link spawnSync}.
    *
    * **If the `shell` option is enabled, do not pass unsanitized user input to this**
    * **function. Any input containing shell metacharacters may be used to trigger**
    * **arbitrary command execution.**
    * @since v0.11.12
    * @param file The name or path of the executable file to run.
    * @param args List of string arguments.
    * @return The stdout from the command.
    */
  @JSName("execFileSync")
  def execFileSync_Buffer(file: String): typings.node.bufferMod.global.Buffer = js.native
  @JSName("execFileSync")
  def execFileSync_Buffer(file: String, args: js.Array[String]): typings.node.bufferMod.global.Buffer = js.native
  
  def execSync(command: String): String | typings.node.bufferMod.global.Buffer = js.native
  def execSync(command: String, options: ExecSyncOptions): String | typings.node.bufferMod.global.Buffer = js.native
  def execSync(command: String, options: ExecSyncOptionsWithBufferEncoding): typings.node.bufferMod.global.Buffer = js.native
  def execSync(command: String, options: ExecSyncOptionsWithStringEncoding): String = js.native
  /**
    * The `child_process.execSync()` method is generally identical to {@link exec} with the exception that the method will not return
    * until the child process has fully closed. When a timeout has been encountered
    * and `killSignal` is sent, the method won't return until the process has
    * completely exited. If the child process intercepts and handles the `SIGTERM` signal and doesn't exit, the parent process will wait until the child process
    * has exited.
    *
    * If the process times out or has a non-zero exit code, this method will throw.
    * The `Error` object will contain the entire result from {@link spawnSync}.
    *
    * **Never pass unsanitized user input to this function. Any input containing shell**
    * **metacharacters may be used to trigger arbitrary command execution.**
    * @since v0.11.12
    * @param command The command to run.
    * @return The stdout from the command.
    */
  @JSName("execSync")
  def execSync_Buffer(command: String): typings.node.bufferMod.global.Buffer = js.native
  
  /**
    * The `child_process.fork()` method is a special case of {@link spawn} used specifically to spawn new Node.js processes.
    * Like {@link spawn}, a `ChildProcess` object is returned. The
    * returned `ChildProcess` will have an additional communication channel
    * built-in that allows messages to be passed back and forth between the parent and
    * child. See `subprocess.send()` for details.
    *
    * Keep in mind that spawned Node.js child processes are
    * independent of the parent with exception of the IPC communication channel
    * that is established between the two. Each process has its own memory, with
    * their own V8 instances. Because of the additional resource allocations
    * required, spawning a large number of child Node.js processes is not
    * recommended.
    *
    * By default, `child_process.fork()` will spawn new Node.js instances using the `process.execPath` of the parent process. The `execPath` property in the `options` object allows for an alternative
    * execution path to be used.
    *
    * Node.js processes launched with a custom `execPath` will communicate with the
    * parent process using the file descriptor (fd) identified using the
    * environment variable `NODE_CHANNEL_FD` on the child process.
    *
    * Unlike the [`fork(2)`](http://man7.org/linux/man-pages/man2/fork.2.html) POSIX system call, `child_process.fork()` does not clone the
    * current process.
    *
    * The `shell` option available in {@link spawn} is not supported by `child_process.fork()` and will be ignored if set.
    *
    * If the `signal` option is enabled, calling `.abort()` on the corresponding `AbortController` is similar to calling `.kill()` on the child process except
    * the error passed to the callback will be an `AbortError`:
    *
    * ```js
    * if (process.argv[2] === 'child') {
    *   setTimeout(() => {
    *     console.log(`Hello from ${process.argv[2]}!`);
    *   }, 1_000);
    * } else {
    *   const { fork } = require('node:child_process');
    *   const controller = new AbortController();
    *   const { signal } = controller;
    *   const child = fork(__filename, ['child'], { signal });
    *   child.on('error', (err) => {
    *     // This will be called with err being an AbortError if the controller aborts
    *   });
    *   controller.abort(); // Stops the child process
    * }
    * ```
    * @since v0.5.0
    * @param modulePath The module to run in the child.
    * @param args List of string arguments.
    */
  def fork(modulePath: String): ChildProcess = js.native
  def fork(modulePath: String, args: js.Array[String]): ChildProcess = js.native
  def fork(modulePath: String, args: js.Array[String], options: ForkOptions): ChildProcess = js.native
  def fork(modulePath: String, args: Unit, options: ForkOptions): ChildProcess = js.native
  def fork(modulePath: String, options: ForkOptions): ChildProcess = js.native
  def fork(modulePath: URL): ChildProcess = js.native
  def fork(modulePath: URL, args: js.Array[String]): ChildProcess = js.native
  def fork(modulePath: URL, args: js.Array[String], options: ForkOptions): ChildProcess = js.native
  def fork(modulePath: URL, args: Unit, options: ForkOptions): ChildProcess = js.native
  def fork(modulePath: URL, options: ForkOptions): ChildProcess = js.native
  
  /**
    * The `child_process.spawn()` method spawns a new process using the given `command`, with command-line arguments in `args`. If omitted, `args` defaults
    * to an empty array.
    *
    * **If the `shell` option is enabled, do not pass unsanitized user input to this**
    * **function. Any input containing shell metacharacters may be used to trigger**
    * **arbitrary command execution.**
    *
    * A third argument may be used to specify additional options, with these defaults:
    *
    * ```js
    * const defaults = {
    *   cwd: undefined,
    *   env: process.env,
    * };
    * ```
    *
    * Use `cwd` to specify the working directory from which the process is spawned.
    * If not given, the default is to inherit the current working directory. If given,
    * but the path does not exist, the child process emits an `ENOENT` error
    * and exits immediately. `ENOENT` is also emitted when the command
    * does not exist.
    *
    * Use `env` to specify environment variables that will be visible to the new
    * process, the default is `process.env`.
    *
    * `undefined` values in `env` will be ignored.
    *
    * Example of running `ls -lh /usr`, capturing `stdout`, `stderr`, and the
    * exit code:
    *
    * ```js
    * const { spawn } = require('node:child_process');
    * const ls = spawn('ls', ['-lh', '/usr']);
    *
    * ls.stdout.on('data', (data) => {
    *   console.log(`stdout: ${data}`);
    * });
    *
    * ls.stderr.on('data', (data) => {
    *   console.error(`stderr: ${data}`);
    * });
    *
    * ls.on('close', (code) => {
    *   console.log(`child process exited with code ${code}`);
    * });
    * ```
    *
    * Example: A very elaborate way to run `ps ax | grep ssh`
    *
    * ```js
    * const { spawn } = require('node:child_process');
    * const ps = spawn('ps', ['ax']);
    * const grep = spawn('grep', ['ssh']);
    *
    * ps.stdout.on('data', (data) => {
    *   grep.stdin.write(data);
    * });
    *
    * ps.stderr.on('data', (data) => {
    *   console.error(`ps stderr: ${data}`);
    * });
    *
    * ps.on('close', (code) => {
    *   if (code !== 0) {
    *     console.log(`ps process exited with code ${code}`);
    *   }
    *   grep.stdin.end();
    * });
    *
    * grep.stdout.on('data', (data) => {
    *   console.log(data.toString());
    * });
    *
    * grep.stderr.on('data', (data) => {
    *   console.error(`grep stderr: ${data}`);
    * });
    *
    * grep.on('close', (code) => {
    *   if (code !== 0) {
    *     console.log(`grep process exited with code ${code}`);
    *   }
    * });
    * ```
    *
    * Example of checking for failed `spawn`:
    *
    * ```js
    * const { spawn } = require('node:child_process');
    * const subprocess = spawn('bad_command');
    *
    * subprocess.on('error', (err) => {
    *   console.error('Failed to start subprocess.');
    * });
    * ```
    *
    * Certain platforms (macOS, Linux) will use the value of `argv[0]` for the process
    * title while others (Windows, SunOS) will use `command`.
    *
    * Node.js overwrites `argv[0]` with `process.execPath` on startup, so `process.argv[0]` in a Node.js child process will not match the `argv0` parameter passed to `spawn` from the parent. Retrieve
    * it with the `process.argv0` property instead.
    *
    * If the `signal` option is enabled, calling `.abort()` on the corresponding `AbortController` is similar to calling `.kill()` on the child process except
    * the error passed to the callback will be an `AbortError`:
    *
    * ```js
    * const { spawn } = require('node:child_process');
    * const controller = new AbortController();
    * const { signal } = controller;
    * const grep = spawn('grep', ['ssh'], { signal });
    * grep.on('error', (err) => {
    *   // This will be called with err being an AbortError if the controller aborts
    * });
    * controller.abort(); // Stops the child process
    * ```
    * @since v0.1.90
    * @param command The command to run.
    * @param args List of string arguments.
    */
  def spawn(command: String): ChildProcessWithoutNullStreams = js.native
  def spawn(command: String, args: js.Array[String]): ChildProcessWithoutNullStreams = js.native
  def spawn(command: String, args: js.Array[String], options: SpawnOptions): ChildProcess = js.native
  def spawn(
    command: String,
    args: js.Array[String],
    options: SpawnOptionsWithStdioTuple[StdioNull | StdioPipe, StdioNull | StdioPipe, StdioNull | StdioPipe]
  ): ChildProcessByStdio[
    Writable, 
    typings.node.nodeColonstreamMod.Readable, 
    typings.node.nodeColonstreamMod.Readable
  ] = js.native
  def spawn(command: String, args: js.Array[String], options: SpawnOptionsWithoutStdio): ChildProcessWithoutNullStreams = js.native
  def spawn(command: String, args: Unit, options: SpawnOptionsWithoutStdio): ChildProcessWithoutNullStreams = js.native
  def spawn(command: String, options: SpawnOptions): ChildProcess = js.native
  def spawn(
    command: String,
    options: SpawnOptionsWithStdioTuple[StdioNull | StdioPipe, StdioNull | StdioPipe, StdioNull | StdioPipe]
  ): ChildProcessByStdio[
    Writable, 
    typings.node.nodeColonstreamMod.Readable, 
    typings.node.nodeColonstreamMod.Readable
  ] = js.native
  def spawn(command: String, options: SpawnOptionsWithoutStdio): ChildProcessWithoutNullStreams = js.native
  
  /**
    * The `child_process.spawnSync()` method is generally identical to {@link spawn} with the exception that the function will not return
    * until the child process has fully closed. When a timeout has been encountered
    * and `killSignal` is sent, the method won't return until the process has
    * completely exited. If the process intercepts and handles the `SIGTERM` signal
    * and doesn't exit, the parent process will wait until the child process has
    * exited.
    *
    * **If the `shell` option is enabled, do not pass unsanitized user input to this**
    * **function. Any input containing shell metacharacters may be used to trigger**
    * **arbitrary command execution.**
    * @since v0.11.12
    * @param command The command to run.
    * @param args List of string arguments.
    */
  def spawnSync(command: String): SpawnSyncReturns[typings.node.bufferMod.global.Buffer] = js.native
  def spawnSync(command: String, args: js.Array[String]): SpawnSyncReturns[typings.node.bufferMod.global.Buffer] = js.native
  def spawnSync(command: String, args: js.Array[String], options: SpawnSyncOptions): SpawnSyncReturns[String | typings.node.bufferMod.global.Buffer] = js.native
  def spawnSync(command: String, args: js.Array[String], options: SpawnSyncOptionsWithBufferEncoding): SpawnSyncReturns[typings.node.bufferMod.global.Buffer] = js.native
  def spawnSync(command: String, args: js.Array[String], options: SpawnSyncOptionsWithStringEncoding): SpawnSyncReturns[String] = js.native
  def spawnSync(command: String, args: Unit, options: SpawnSyncOptions): SpawnSyncReturns[String | typings.node.bufferMod.global.Buffer] = js.native
  def spawnSync(command: String, options: SpawnSyncOptions): SpawnSyncReturns[String | typings.node.bufferMod.global.Buffer] = js.native
  def spawnSync(command: String, options: SpawnSyncOptionsWithBufferEncoding): SpawnSyncReturns[typings.node.bufferMod.global.Buffer] = js.native
  def spawnSync(command: String, options: SpawnSyncOptionsWithStringEncoding): SpawnSyncReturns[String] = js.native
}
