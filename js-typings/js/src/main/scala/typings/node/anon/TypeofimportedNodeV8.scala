package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import typings.node.globalsMod.global.NodeJS.TypedArray
import typings.node.v8Mod.HeapCodeStatistics
import typings.node.v8Mod.HeapInfo
import typings.node.v8Mod.HeapSnapshotOptions
import typings.node.v8Mod.HeapSpaceInfo
import typings.node.v8Mod.PromiseHooks_
import typings.node.v8Mod.StartupSnapshot_
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedNodeV8 extends StObject {
  
  /**
    * A subclass of `Deserializer` corresponding to the format written by `DefaultSerializer`.
    * @since v8.0.0
    */
  var DefaultDeserializer: Instantiable0[typings.node.nodeColonv8Mod.DefaultDeserializer] = js.native
  
  /**
    * A subclass of `Serializer` that serializes `TypedArray`(in particular `Buffer`) and `DataView` objects as host objects, and only
    * stores the part of their underlying `ArrayBuffer`s that they are referring to.
    * @since v8.0.0
    */
  var DefaultSerializer: Instantiable0[typings.node.nodeColonv8Mod.DefaultSerializer] = js.native
  
  /**
    * @since v8.0.0
    */
  var Deserializer: Instantiable1[/* data */ TypedArray, typings.node.nodeColonv8Mod.Deserializer] = js.native
  
  /**
    * This API collects GC data in current thread.
    * @since v19.6.0, v18.15.0
    */
  var GCProfiler: Instantiable0[typings.node.nodeColonv8Mod.GCProfiler] = js.native
  
  /**
    * @since v8.0.0
    */
  var Serializer: Instantiable0[typings.node.nodeColonv8Mod.Serializer] = js.native
  
  /**
    * Returns an integer representing a version tag derived from the V8 version,
    * command-line flags, and detected CPU features. This is useful for determining
    * whether a `vm.Script` `cachedData` buffer is compatible with this instance
    * of V8.
    *
    * ```js
    * console.log(v8.cachedDataVersionTag()); // 3947234607
    * // The value returned by v8.cachedDataVersionTag() is derived from the V8
    * // version, command-line flags, and detected CPU features. Test that the value
    * // does indeed update when flags are toggled.
    * v8.setFlagsFromString('--allow_natives_syntax');
    * console.log(v8.cachedDataVersionTag()); // 183726201
    * ```
    * @since v8.0.0
    */
  def cachedDataVersionTag(): Double = js.native
  
  /**
    * Uses a `DefaultDeserializer` with default options to read a JS value
    * from a buffer.
    * @since v8.0.0
    * @param buffer A buffer returned by {@link serialize}.
    */
  def deserialize(buffer: TypedArray): Any = js.native
  
  /**
    * Get statistics about code and its metadata in the heap, see
    * V8 [`GetHeapCodeAndMetadataStatistics`](https://v8docs.nodesource.com/node-13.2/d5/dda/classv8_1_1_isolate.html#a6079122af17612ef54ef3348ce170866) API. Returns an object with the
    * following properties:
    *
    * ```js
    * {
    *   code_and_metadata_size: 212208,
    *   bytecode_and_metadata_size: 161368,
    *   external_script_source_size: 1410794,
    *   cpu_profiler_metadata_size: 0,
    * }
    * ```
    * @since v12.8.0
    */
  def getHeapCodeStatistics(): HeapCodeStatistics = js.native
  
  /**
    * Generates a snapshot of the current V8 heap and returns a Readable
    * Stream that may be used to read the JSON serialized representation.
    * This JSON stream format is intended to be used with tools such as
    * Chrome DevTools. The JSON schema is undocumented and specific to the
    * V8 engine. Therefore, the schema may change from one version of V8 to the next.
    *
    * Creating a heap snapshot requires memory about twice the size of the heap at
    * the time the snapshot is created. This results in the risk of OOM killers
    * terminating the process.
    *
    * Generating a snapshot is a synchronous operation which blocks the event loop
    * for a duration depending on the heap size.
    *
    * ```js
    * // Print heap snapshot to the console
    * const v8 = require('node:v8');
    * const stream = v8.getHeapSnapshot();
    * stream.pipe(process.stdout);
    * ```
    * @since v11.13.0
    * @return A Readable containing the V8 heap snapshot.
    */
  def getHeapSnapshot(): typings.node.nodeColonstreamMod.Readable = js.native
  def getHeapSnapshot(options: HeapSnapshotOptions): typings.node.nodeColonstreamMod.Readable = js.native
  
  /**
    * Returns statistics about the V8 heap spaces, i.e. the segments which make up
    * the V8 heap. Neither the ordering of heap spaces, nor the availability of a
    * heap space can be guaranteed as the statistics are provided via the
    * V8 [`GetHeapSpaceStatistics`](https://v8docs.nodesource.com/node-13.2/d5/dda/classv8_1_1_isolate.html#ac673576f24fdc7a33378f8f57e1d13a4) function and may change from one V8 version to the
    * next.
    *
    * The value returned is an array of objects containing the following properties:
    *
    * ```json
    * [
    *   {
    *     "space_name": "new_space",
    *     "space_size": 2063872,
    *     "space_used_size": 951112,
    *     "space_available_size": 80824,
    *     "physical_space_size": 2063872
    *   },
    *   {
    *     "space_name": "old_space",
    *     "space_size": 3090560,
    *     "space_used_size": 2493792,
    *     "space_available_size": 0,
    *     "physical_space_size": 3090560
    *   },
    *   {
    *     "space_name": "code_space",
    *     "space_size": 1260160,
    *     "space_used_size": 644256,
    *     "space_available_size": 960,
    *     "physical_space_size": 1260160
    *   },
    *   {
    *     "space_name": "map_space",
    *     "space_size": 1094160,
    *     "space_used_size": 201608,
    *     "space_available_size": 0,
    *     "physical_space_size": 1094160
    *   },
    *   {
    *     "space_name": "large_object_space",
    *     "space_size": 0,
    *     "space_used_size": 0,
    *     "space_available_size": 1490980608,
    *     "physical_space_size": 0
    *   }
    * ]
    * ```
    * @since v6.0.0
    */
  def getHeapSpaceStatistics(): js.Array[HeapSpaceInfo] = js.native
  
  /**
    * Returns an object with the following properties:
    *
    * `does_zap_garbage` is a 0/1 boolean, which signifies whether the `--zap_code_space` option is enabled or not. This makes V8 overwrite heap
    * garbage with a bit pattern. The RSS footprint (resident set size) gets bigger
    * because it continuously touches all heap pages and that makes them less likely
    * to get swapped out by the operating system.
    *
    * `number_of_native_contexts` The value of native\_context is the number of the
    * top-level contexts currently active. Increase of this number over time indicates
    * a memory leak.
    *
    * `number_of_detached_contexts` The value of detached\_context is the number
    * of contexts that were detached and not yet garbage collected. This number
    * being non-zero indicates a potential memory leak.
    *
    * `total_global_handles_size` The value of total\_global\_handles\_size is the
    * total memory size of V8 global handles.
    *
    * `used_global_handles_size` The value of used\_global\_handles\_size is the
    * used memory size of V8 global handles.
    *
    * `external_memory` The value of external\_memory is the memory size of array
    * buffers and external strings.
    *
    * ```js
    * {
    *   total_heap_size: 7326976,
    *   total_heap_size_executable: 4194304,
    *   total_physical_size: 7326976,
    *   total_available_size: 1152656,
    *   used_heap_size: 3476208,
    *   heap_size_limit: 1535115264,
    *   malloced_memory: 16384,
    *   peak_malloced_memory: 1127496,
    *   does_zap_garbage: 0,
    *   number_of_native_contexts: 1,
    *   number_of_detached_contexts: 0,
    *   total_global_handles_size: 8192,
    *   used_global_handles_size: 3296,
    *   external_memory: 318824
    * }
    * ```
    * @since v1.0.0
    */
  def getHeapStatistics(): HeapInfo = js.native
  
  /**
    * The `promiseHooks` interface can be used to track promise lifecycle events.
    * @since v17.1.0, v16.14.0
    */
  val promiseHooks: PromiseHooks_ = js.native
  
  /**
    * This is similar to the [`queryObjects()` console API](https://developer.chrome.com/docs/devtools/console/utilities#queryObjects-function)
    * provided by the Chromium DevTools console. It can be used to search for objects that have the matching constructor on its prototype chain
    * in the heap after a full garbage collection, which can be useful for memory leak regression tests. To avoid surprising results, users should
    * avoid using this API on constructors whose implementation they don't control, or on constructors that can be invoked by other parties in the
    * application.
    *
    * To avoid accidental leaks, this API does not return raw references to the objects found. By default, it returns the count of the objects
    * found. If `options.format` is `'summary'`, it returns an array containing brief string representations for each object. The visibility provided
    * in this API is similar to what the heap snapshot provides, while users can save the cost of serialization and parsing and directly filter the
    * target objects during the search.
    *
    * Only objects created in the current execution context are included in the results.
    *
    * ```js
    * import { queryObjects } from 'node:v8';
    * class A { foo = 'bar'; }
    * console.log(queryObjects(A)); // 0
    * const a = new A();
    * console.log(queryObjects(A)); // 1
    * // [ "A { foo: 'bar' }" ]
    * console.log(queryObjects(A, { format: 'summary' }));
    *
    * class B extends A { bar = 'qux'; }
    * const b = new B();
    * console.log(queryObjects(B)); // 1
    * // [ "B { foo: 'bar', bar: 'qux' }" ]
    * console.log(queryObjects(B, { format: 'summary' }));
    *
    * // Note that, when there are child classes inheriting from a constructor,
    * // the constructor also shows up in the prototype chain of the child
    * // classes's prototoype, so the child classes's prototoype would also be
    * // included in the result.
    * console.log(queryObjects(A));  // 3
    * // [ "B { foo: 'bar', bar: 'qux' }", 'A {}', "A { foo: 'bar' }" ]
    * console.log(queryObjects(A, { format: 'summary' }));
    * ```
    * @param ctor The constructor that can be used to search on the prototype chain in order to filter target objects in the heap.
    * @since v20.13.0
    * @experimental
    */
  def queryObjects(ctor: js.Function): Double | js.Array[String] = js.native
  def queryObjects(ctor: js.Function, options: `5`): Double = js.native
  def queryObjects(ctor: js.Function, options: `6`): js.Array[String] = js.native
  
  /**
    * Uses a `DefaultSerializer` to serialize `value` into a buffer.
    *
    * `ERR_BUFFER_TOO_LARGE` will be thrown when trying to
    * serialize a huge object which requires buffer
    * larger than `buffer.constants.MAX_LENGTH`.
    * @since v8.0.0
    */
  def serialize(value: Any): typings.node.bufferMod.global.Buffer = js.native
  
  /**
    * The `v8.setFlagsFromString()` method can be used to programmatically set
    * V8 command-line flags. This method should be used with care. Changing settings
    * after the VM has started may result in unpredictable behavior, including
    * crashes and data loss; or it may simply do nothing.
    *
    * The V8 options available for a version of Node.js may be determined by running `node --v8-options`.
    *
    * Usage:
    *
    * ```js
    * // Print GC events to stdout for one minute.
    * const v8 = require('node:v8');
    * v8.setFlagsFromString('--trace_gc');
    * setTimeout(() => { v8.setFlagsFromString('--notrace_gc'); }, 60e3);
    * ```
    * @since v1.0.0
    */
  def setFlagsFromString(flags: String): Unit = js.native
  
  /**
    * The API is a no-op if `--heapsnapshot-near-heap-limit` is already set from the command line or the API is called more than once.
    * `limit` must be a positive integer. See [`--heapsnapshot-near-heap-limit`](https://nodejs.org/docs/latest-v22.x/api/cli.html#--heapsnapshot-near-heap-limitmax_count) for more information.
    * @experimental
    * @since v18.10.0, v16.18.0
    */
  def setHeapSnapshotNearHeapLimit(limit: Double): Unit = js.native
  
  /**
    * The `v8.startupSnapshot` interface can be used to add serialization and deserialization hooks for custom startup snapshots.
    *
    * ```bash
    * $ node --snapshot-blob snapshot.blob --build-snapshot entry.js
    * # This launches a process with the snapshot
    * $ node --snapshot-blob snapshot.blob
    * ```
    *
    * In the example above, `entry.js` can use methods from the `v8.startupSnapshot` interface to specify how to save information for custom objects
    * in the snapshot during serialization and how the information can be used to synchronize these objects during deserialization of the snapshot.
    * For example, if the `entry.js` contains the following script:
    *
    * ```js
    * 'use strict';
    *
    * const fs = require('node:fs');
    * const zlib = require('node:zlib');
    * const path = require('node:path');
    * const assert = require('node:assert');
    *
    * const v8 = require('node:v8');
    *
    * class BookShelf {
    *   storage = new Map();
    *
    *   // Reading a series of files from directory and store them into storage.
    *   constructor(directory, books) {
    *     for (const book of books) {
    *       this.storage.set(book, fs.readFileSync(path.join(directory, book)));
    *     }
    *   }
    *
    *   static compressAll(shelf) {
    *     for (const [ book, content ] of shelf.storage) {
    *       shelf.storage.set(book, zlib.gzipSync(content));
    *     }
    *   }
    *
    *   static decompressAll(shelf) {
    *     for (const [ book, content ] of shelf.storage) {
    *       shelf.storage.set(book, zlib.gunzipSync(content));
    *     }
    *   }
    * }
    *
    * // __dirname here is where the snapshot script is placed
    * // during snapshot building time.
    * const shelf = new BookShelf(__dirname, [
    *   'book1.en_US.txt',
    *   'book1.es_ES.txt',
    *   'book2.zh_CN.txt',
    * ]);
    *
    * assert(v8.startupSnapshot.isBuildingSnapshot());
    * // On snapshot serialization, compress the books to reduce size.
    * v8.startupSnapshot.addSerializeCallback(BookShelf.compressAll, shelf);
    * // On snapshot deserialization, decompress the books.
    * v8.startupSnapshot.addDeserializeCallback(BookShelf.decompressAll, shelf);
    * v8.startupSnapshot.setDeserializeMainFunction((shelf) => {
    *   // process.env and process.argv are refreshed during snapshot
    *   // deserialization.
    *   const lang = process.env.BOOK_LANG || 'en_US';
    *   const book = process.argv[1];
    *   const name = `${book}.${lang}.txt`;
    *   console.log(shelf.storage.get(name));
    * }, shelf);
    * ```
    *
    * The resulted binary will get print the data deserialized from the snapshot during start up, using the refreshed `process.env` and `process.argv` of the launched process:
    *
    * ```bash
    * $ BOOK_LANG=es_ES node --snapshot-blob snapshot.blob book1
    * # Prints content of book1.es_ES.txt deserialized from the snapshot.
    * ```
    *
    * Currently the application deserialized from a user-land snapshot cannot be snapshotted again, so these APIs are only available to applications that are not deserialized from a user-land snapshot.
    *
    * @experimental
    * @since v18.6.0, v16.17.0
    */
  val startupSnapshot: StartupSnapshot_ = js.native
  
  /**
    * The `v8.stopCoverage()` method allows the user to stop the coverage collection
    * started by `NODE_V8_COVERAGE`, so that V8 can release the execution count
    * records and optimize code. This can be used in conjunction with {@link takeCoverage} if the user wants to collect the coverage on demand.
    * @since v15.1.0, v14.18.0, v12.22.0
    */
  def stopCoverage(): Unit = js.native
  
  /**
    * The `v8.takeCoverage()` method allows the user to write the coverage started by `NODE_V8_COVERAGE` to disk on demand. This method can be invoked multiple
    * times during the lifetime of the process. Each time the execution counter will
    * be reset and a new coverage report will be written to the directory specified
    * by `NODE_V8_COVERAGE`.
    *
    * When the process is about to exit, one last coverage will still be written to
    * disk unless {@link stopCoverage} is invoked before the process exits.
    * @since v15.1.0, v14.18.0, v12.22.0
    */
  def takeCoverage(): Unit = js.native
  
  /**
    * Generates a snapshot of the current V8 heap and writes it to a JSON
    * file. This file is intended to be used with tools such as Chrome
    * DevTools. The JSON schema is undocumented and specific to the V8
    * engine, and may change from one version of V8 to the next.
    *
    * A heap snapshot is specific to a single V8 isolate. When using `worker threads`, a heap snapshot generated from the main thread will
    * not contain any information about the workers, and vice versa.
    *
    * Creating a heap snapshot requires memory about twice the size of the heap at
    * the time the snapshot is created. This results in the risk of OOM killers
    * terminating the process.
    *
    * Generating a snapshot is a synchronous operation which blocks the event loop
    * for a duration depending on the heap size.
    *
    * ```js
    * const { writeHeapSnapshot } = require('node:v8');
    * const {
    *   Worker,
    *   isMainThread,
    *   parentPort,
    * } = require('node:worker_threads');
    *
    * if (isMainThread) {
    *   const worker = new Worker(__filename);
    *
    *   worker.once('message', (filename) => {
    *     console.log(`worker heapdump: ${filename}`);
    *     // Now get a heapdump for the main thread.
    *     console.log(`main thread heapdump: ${writeHeapSnapshot()}`);
    *   });
    *
    *   // Tell the worker to create a heapdump.
    *   worker.postMessage('heapdump');
    * } else {
    *   parentPort.once('message', (message) => {
    *     if (message === 'heapdump') {
    *       // Generate a heapdump for the worker
    *       // and return the filename to the parent.
    *       parentPort.postMessage(writeHeapSnapshot());
    *     }
    *   });
    * }
    * ```
    * @since v11.13.0
    * @param filename The file path where the V8 heap snapshot is to be saved. If not specified, a file name with the pattern `'Heap-${yyyymmdd}-${hhmmss}-${pid}-${thread_id}.heapsnapshot'` will be
    * generated, where `{pid}` will be the PID of the Node.js process, `{thread_id}` will be `0` when `writeHeapSnapshot()` is called from the main Node.js thread or the id of a
    * worker thread.
    * @return The filename where the snapshot was saved.
    */
  def writeHeapSnapshot(): String = js.native
  def writeHeapSnapshot(filename: String): String = js.native
  def writeHeapSnapshot(filename: String, options: HeapSnapshotOptions): String = js.native
  def writeHeapSnapshot(filename: Unit, options: HeapSnapshotOptions): String = js.native
}
