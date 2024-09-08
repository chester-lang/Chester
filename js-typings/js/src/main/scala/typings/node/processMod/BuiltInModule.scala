package typings.node.processMod

import org.scalablytyped.runtime.Instantiable0
import org.scalablytyped.runtime.Instantiable1
import typings.node.anon.ADDRGETNETWORKPARAMS
import typings.node.anon.ArrayBuffer
import typings.node.anon.ByteLengthQueuingStrategy
import typings.node.anon.Call
import typings.node.anon.IsAnyArrayBuffer
import typings.node.anon.TypeofimportedAsyncHook
import typings.node.anon.TypeofimportedBuffer
import typings.node.anon.TypeofimportedChildProc
import typings.node.anon.TypeofimportedCluster
import typings.node.anon.TypeofimportedCrypto
import typings.node.anon.TypeofimportedDgram
import typings.node.anon.TypeofimportedDiagnostic
import typings.node.anon.TypeofimportedDns
import typings.node.anon.TypeofimportedDomain
import typings.node.anon.TypeofimportedEvents
import typings.node.anon.TypeofimportedFs
import typings.node.anon.TypeofimportedHttp
import typings.node.anon.TypeofimportedHttp2
import typings.node.anon.TypeofimportedHttps
import typings.node.anon.TypeofimportedInspector
import typings.node.anon.TypeofimportedNet
import typings.node.anon.TypeofimportedNodeAsync
import typings.node.anon.TypeofimportedNodeBuffe
import typings.node.anon.TypeofimportedNodeChild
import typings.node.anon.TypeofimportedNodeClust
import typings.node.anon.TypeofimportedNodeCrypt
import typings.node.anon.TypeofimportedNodeDgram
import typings.node.anon.TypeofimportedNodeDiagn
import typings.node.anon.TypeofimportedNodeDns
import typings.node.anon.TypeofimportedNodeDomai
import typings.node.anon.TypeofimportedNodeFs
import typings.node.anon.TypeofimportedNodeHttp
import typings.node.anon.TypeofimportedNodeHttp2
import typings.node.anon.TypeofimportedNodeHttps
import typings.node.anon.TypeofimportedNodeInspe
import typings.node.anon.TypeofimportedNodeNet
import typings.node.anon.TypeofimportedNodeOs
import typings.node.anon.TypeofimportedNodePerf
import typings.node.anon.TypeofimportedNodePunyc
import typings.node.anon.TypeofimportedNodeQuery
import typings.node.anon.TypeofimportedNodeReadl
import typings.node.anon.TypeofimportedNodeRepl
import typings.node.anon.TypeofimportedNodeSea
import typings.node.anon.TypeofimportedNodeStrea
import typings.node.anon.TypeofimportedNodeStrin
import typings.node.anon.TypeofimportedNodeTest
import typings.node.anon.TypeofimportedNodeTimer
import typings.node.anon.TypeofimportedNodeTls
import typings.node.anon.TypeofimportedNodeTrace
import typings.node.anon.TypeofimportedNodeTty
import typings.node.anon.TypeofimportedNodeUrl
import typings.node.anon.TypeofimportedNodeUtil
import typings.node.anon.TypeofimportedNodeV8
import typings.node.anon.TypeofimportedNodeVm
import typings.node.anon.TypeofimportedNodeWasi
import typings.node.anon.TypeofimportedNodeWorke
import typings.node.anon.TypeofimportedNodeZlib
import typings.node.anon.TypeofimportedOs
import typings.node.anon.TypeofimportedPerfHooks
import typings.node.anon.TypeofimportedPunycode
import typings.node.anon.TypeofimportedQuerystrin
import typings.node.anon.TypeofimportedReadline
import typings.node.anon.TypeofimportedRepl
import typings.node.anon.TypeofimportedReporters
import typings.node.anon.TypeofimportedStream
import typings.node.anon.TypeofimportedStringDec
import typings.node.anon.TypeofimportedTimers
import typings.node.anon.TypeofimportedTls
import typings.node.anon.TypeofimportedTraceEven
import typings.node.anon.TypeofimportedTty
import typings.node.anon.TypeofimportedUrl
import typings.node.anon.TypeofimportedUtil
import typings.node.anon.TypeofimportedV8
import typings.node.anon.TypeofimportedVm
import typings.node.anon.TypeofimportedWasi
import typings.node.anon.TypeofimportedWorkerThr
import typings.node.anon.TypeofimportedZlib
import typings.node.eventsMod.EventEmitterOptions
import typings.node.eventsMod.EventMap
import typings.node.nodeStrings.deepEqual
import typings.node.nodeStrings.deepStrictEqual
import typings.node.nodeStrings.equal
import typings.node.nodeStrings.ifError
import typings.node.nodeStrings.notDeepEqual
import typings.node.nodeStrings.notEqual
import typings.node.nodeStrings.ok
import typings.node.nodeStrings.strict
import typings.node.nodeStrings.strictEqual
import typings.node.osMod.SignalConstants
import typings.node.pathMod.PlatformPath
import typings.node.processMod.global.NodeJS.Process
import typings.std.Omit
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait BuiltInModule extends StObject {
  
  def assert(value: Any): /* asserts value */ Boolean
  def assert(value: Any, message: String): /* asserts value */ Boolean
  def assert(value: Any, message: js.Error): /* asserts value */ Boolean
  
  @JSName("assert/strict")
  def assertSlashstrict(value: Any): /* asserts value */ Boolean
  @JSName("assert/strict")
  def assertSlashstrict(value: Any, message: String): /* asserts value */ Boolean
  @JSName("assert/strict")
  def assertSlashstrict(value: Any, message: js.Error): /* asserts value */ Boolean
  @JSName("assert/strict")
  var assertSlashstrict_Original: (Omit[
    /* import warning: importer.ImportType#apply Failed type conversion: typeof assert */ js.Any, 
    equal | notEqual | deepEqual | notDeepEqual | ok | strictEqual | deepStrictEqual | ifError | strict
  ]) & Call
  
  @JSName("assert")
  var assert_Original: js.Function2[
    /* value */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    /* asserts value */ Boolean
  ]
  
  var async_hooks: TypeofimportedAsyncHook
  
  var buffer: TypeofimportedBuffer
  
  var child_process: TypeofimportedChildProc
  
  var cluster: TypeofimportedCluster
  
  var console: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_console */ Any
  
  var constants: (/* import warning: importer.ImportType#apply Failed type conversion: typeof osConstants.errno */ js.Any) & SignalConstants
  
  var crypto: TypeofimportedCrypto
  
  var dgram: TypeofimportedDgram
  
  var diagnostics_channel: TypeofimportedDiagnostic
  
  var dns: TypeofimportedDns
  
  @JSName("dns/promises")
  var dnsSlashpromises: ADDRGETNETWORKPARAMS
  
  var domain: TypeofimportedDomain
  
  var events: (Instantiable1[
    /* options */ js.UndefOr[EventEmitterOptions], 
    typings.node.eventsMod.^[EventMap[/* import warning: RewrittenClass.unapply cls was tparam T */ Any]]
  ]) & TypeofimportedEvents
  
  var fs: TypeofimportedFs
  
  @JSName("fs/promises")
  var fsSlashpromises: ADDRGETNETWORKPARAMS
  
  var http: TypeofimportedHttp
  
  var http2: TypeofimportedHttp2
  
  var https: TypeofimportedHttps
  
  var inspector: TypeofimportedInspector
  
  // FIXME: module is missed
  // "inspector/promises": typeof import("inspector/promises");
  // "node:inspector/promises": typeof import("node:inspector/promises");
  var module: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof Module */ Any
  
  var net: TypeofimportedNet
  
  @JSName("node:assert")
  def nodeColonassert(value: Any): /* asserts value */ Boolean
  @JSName("node:assert")
  def nodeColonassert(value: Any, message: String): /* asserts value */ Boolean
  @JSName("node:assert")
  def nodeColonassert(value: Any, message: js.Error): /* asserts value */ Boolean
  
  @JSName("node:assert/strict")
  def nodeColonassertSlashstrict(value: Any): /* asserts value */ Boolean
  @JSName("node:assert/strict")
  def nodeColonassertSlashstrict(value: Any, message: String): /* asserts value */ Boolean
  @JSName("node:assert/strict")
  def nodeColonassertSlashstrict(value: Any, message: js.Error): /* asserts value */ Boolean
  @JSName("node:assert/strict")
  var nodeColonassertSlashstrict_Original: (Omit[
    /* import warning: importer.ImportType#apply Failed type conversion: typeof assert */ js.Any, 
    equal | notEqual | deepEqual | notDeepEqual | ok | strictEqual | deepStrictEqual | ifError | strict
  ]) & Call
  
  @JSName("node:assert")
  var nodeColonassert_Original: js.Function2[
    /* value */ Any, 
    /* message */ js.UndefOr[String | js.Error], 
    /* asserts value */ Boolean
  ]
  
  @JSName("node:async_hooks")
  var nodeColonasync_hooks: TypeofimportedNodeAsync
  
  @JSName("node:buffer")
  var nodeColonbuffer: TypeofimportedNodeBuffe
  
  @JSName("node:child_process")
  var nodeColonchild_process: TypeofimportedNodeChild
  
  @JSName("node:cluster")
  var nodeColoncluster: TypeofimportedNodeClust
  
  @JSName("node:console")
  var nodeColonconsole: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_node:console */ Any
  
  @JSName("node:constants")
  var nodeColonconstants: (/* import warning: importer.ImportType#apply Failed type conversion: typeof osConstants.errno */ js.Any) & SignalConstants
  
  @JSName("node:crypto")
  var nodeColoncrypto: TypeofimportedNodeCrypt
  
  @JSName("node:dgram")
  var nodeColondgram: TypeofimportedNodeDgram
  
  @JSName("node:diagnostics_channel")
  var nodeColondiagnostics_channel: TypeofimportedNodeDiagn
  
  @JSName("node:dns")
  var nodeColondns: TypeofimportedNodeDns
  
  @JSName("node:dns/promises")
  var nodeColondnsSlashpromises: ADDRGETNETWORKPARAMS
  
  @JSName("node:domain")
  var nodeColondomain: TypeofimportedNodeDomai
  
  @JSName("node:events")
  var nodeColonevents: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_node:events */ Any
  
  @JSName("node:fs")
  var nodeColonfs: TypeofimportedNodeFs
  
  @JSName("node:fs/promises")
  var nodeColonfsSlashpromises: ADDRGETNETWORKPARAMS
  
  @JSName("node:http")
  var nodeColonhttp: TypeofimportedNodeHttp
  
  @JSName("node:http2")
  var nodeColonhttp2: TypeofimportedNodeHttp2
  
  @JSName("node:https")
  var nodeColonhttps: TypeofimportedNodeHttps
  
  @JSName("node:inspector")
  var nodeColoninspector: TypeofimportedNodeInspe
  
  @JSName("node:module")
  var nodeColonmodule: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof Module */ Any
  
  @JSName("node:net")
  var nodeColonnet: TypeofimportedNodeNet
  
  @JSName("node:os")
  var nodeColonos: TypeofimportedNodeOs
  
  @JSName("node:path")
  var nodeColonpath: PlatformPath
  
  @JSName("node:path/posix")
  var nodeColonpathSlashposix: PlatformPath
  
  @JSName("node:path/win32")
  var nodeColonpathSlashwin32: PlatformPath
  
  @JSName("node:perf_hooks")
  var nodeColonperf_hooks: TypeofimportedNodePerf
  
  @JSName("node:process")
  var nodeColonprocess: Process
  
  @JSName("node:punycode")
  var nodeColonpunycode: TypeofimportedNodePunyc
  
  @JSName("node:querystring")
  var nodeColonquerystring: TypeofimportedNodeQuery
  
  @JSName("node:readline")
  var nodeColonreadline: TypeofimportedNodeReadl
  
  @JSName("node:readline/promises")
  var nodeColonreadlineSlashpromises: ADDRGETNETWORKPARAMS
  
  @JSName("node:repl")
  var nodeColonrepl: TypeofimportedNodeRepl
  
  @JSName("node:sea")
  var nodeColonsea: TypeofimportedNodeSea
  
  @JSName("node:stream")
  var nodeColonstream: Instantiable0[typings.node.nodeColonstreamMod.^] & TypeofimportedNodeStrea
  
  @JSName("node:stream/consumers")
  var nodeColonstreamSlashconsumers: ArrayBuffer
  
  @JSName("node:stream/promises")
  var nodeColonstreamSlashpromises: ADDRGETNETWORKPARAMS
  
  @JSName("node:stream/web")
  var nodeColonstreamSlashweb: ByteLengthQueuingStrategy
  
  @JSName("node:string_decoder")
  var nodeColonstring_decoder: TypeofimportedNodeStrin
  
  @JSName("node:sys")
  var nodeColonsys: TypeofimportedNodeUtil
  
  @JSName("node:test")
  var nodeColontest: TypeofimportedNodeTest
  
  @JSName("node:test/reporters")
  var nodeColontestSlashreporters: TypeofimportedReporters
  
  @JSName("node:timers")
  var nodeColontimers: TypeofimportedNodeTimer
  
  @JSName("node:timers/promises")
  var nodeColontimersSlashpromises: ADDRGETNETWORKPARAMS
  
  @JSName("node:tls")
  var nodeColontls: TypeofimportedNodeTls
  
  @JSName("node:trace_events")
  var nodeColontrace_events: TypeofimportedNodeTrace
  
  @JSName("node:tty")
  var nodeColontty: TypeofimportedNodeTty
  
  @JSName("node:url")
  var nodeColonurl: TypeofimportedNodeUrl
  
  @JSName("node:util")
  var nodeColonutil: TypeofimportedNodeUtil
  
  @JSName("node:util/types")
  var nodeColonutilSlashtypes: IsAnyArrayBuffer
  
  @JSName("node:v8")
  var nodeColonv8: TypeofimportedNodeV8
  
  @JSName("node:vm")
  var nodeColonvm: TypeofimportedNodeVm
  
  @JSName("node:wasi")
  var nodeColonwasi: TypeofimportedNodeWasi
  
  @JSName("node:worker_threads")
  var nodeColonworker_threads: TypeofimportedNodeWorke
  
  @JSName("node:zlib")
  var nodeColonzlib: TypeofimportedNodeZlib
  
  var os: TypeofimportedOs
  
  var path: PlatformPath
  
  @JSName("path/posix")
  var pathSlashposix: PlatformPath
  
  @JSName("path/win32")
  var pathSlashwin32: PlatformPath
  
  var perf_hooks: TypeofimportedPerfHooks
  
  var process: Process
  
  var punycode: TypeofimportedPunycode
  
  var querystring: TypeofimportedQuerystrin
  
  var readline: TypeofimportedReadline
  
  @JSName("readline/promises")
  var readlineSlashpromises: ADDRGETNETWORKPARAMS
  
  var repl: TypeofimportedRepl
  
  var stream: Instantiable0[typings.node.streamMod.^] & TypeofimportedStream
  
  @JSName("stream/consumers")
  var streamSlashconsumers: ArrayBuffer
  
  @JSName("stream/promises")
  var streamSlashpromises: ADDRGETNETWORKPARAMS
  
  @JSName("stream/web")
  var streamSlashweb: ByteLengthQueuingStrategy
  
  var string_decoder: TypeofimportedStringDec
  
  var sys: TypeofimportedUtil
  
  var timers: TypeofimportedTimers
  
  @JSName("timers/promises")
  var timersSlashpromises: ADDRGETNETWORKPARAMS
  
  var tls: TypeofimportedTls
  
  var trace_events: TypeofimportedTraceEven
  
  var tty: TypeofimportedTty
  
  var url: TypeofimportedUrl
  
  var util: TypeofimportedUtil
  
  @JSName("util/types")
  var utilSlashtypes: IsAnyArrayBuffer
  
  var v8: TypeofimportedV8
  
  var vm: TypeofimportedVm
  
  var wasi: TypeofimportedWasi
  
  var worker_threads: TypeofimportedWorkerThr
  
  var zlib: TypeofimportedZlib
}
object BuiltInModule {
  
  inline def apply(
    assert: (/* value */ Any, /* message */ js.UndefOr[String | js.Error]) => /* asserts value */ Boolean,
    assertSlashstrict: (Omit[
      /* import warning: importer.ImportType#apply Failed type conversion: typeof assert */ js.Any, 
      equal | notEqual | deepEqual | notDeepEqual | ok | strictEqual | deepStrictEqual | ifError | strict
    ]) & Call,
    async_hooks: TypeofimportedAsyncHook,
    buffer: TypeofimportedBuffer,
    child_process: TypeofimportedChildProc,
    cluster: TypeofimportedCluster,
    console: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_console */ Any,
    constants: (/* import warning: importer.ImportType#apply Failed type conversion: typeof osConstants.errno */ js.Any) & SignalConstants,
    crypto: TypeofimportedCrypto,
    dgram: TypeofimportedDgram,
    diagnostics_channel: TypeofimportedDiagnostic,
    dns: TypeofimportedDns,
    dnsSlashpromises: ADDRGETNETWORKPARAMS,
    domain: TypeofimportedDomain,
    events: (Instantiable1[
      /* options */ js.UndefOr[EventEmitterOptions], 
      typings.node.eventsMod.^[EventMap[/* import warning: RewrittenClass.unapply cls was tparam T */ Any]]
    ]) & TypeofimportedEvents,
    fs: TypeofimportedFs,
    fsSlashpromises: ADDRGETNETWORKPARAMS,
    http: TypeofimportedHttp,
    http2: TypeofimportedHttp2,
    https: TypeofimportedHttps,
    inspector: TypeofimportedInspector,
    module: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof Module */ Any,
    net: TypeofimportedNet,
    nodeColonassert: (/* value */ Any, /* message */ js.UndefOr[String | js.Error]) => /* asserts value */ Boolean,
    nodeColonassertSlashstrict: (Omit[
      /* import warning: importer.ImportType#apply Failed type conversion: typeof assert */ js.Any, 
      equal | notEqual | deepEqual | notDeepEqual | ok | strictEqual | deepStrictEqual | ifError | strict
    ]) & Call,
    nodeColonasync_hooks: TypeofimportedNodeAsync,
    nodeColonbuffer: TypeofimportedNodeBuffe,
    nodeColonchild_process: TypeofimportedNodeChild,
    nodeColoncluster: TypeofimportedNodeClust,
    nodeColonconsole: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_node:console */ Any,
    nodeColonconstants: (/* import warning: importer.ImportType#apply Failed type conversion: typeof osConstants.errno */ js.Any) & SignalConstants,
    nodeColoncrypto: TypeofimportedNodeCrypt,
    nodeColondgram: TypeofimportedNodeDgram,
    nodeColondiagnostics_channel: TypeofimportedNodeDiagn,
    nodeColondns: TypeofimportedNodeDns,
    nodeColondnsSlashpromises: ADDRGETNETWORKPARAMS,
    nodeColondomain: TypeofimportedNodeDomai,
    nodeColonevents: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_node:events */ Any,
    nodeColonfs: TypeofimportedNodeFs,
    nodeColonfsSlashpromises: ADDRGETNETWORKPARAMS,
    nodeColonhttp: TypeofimportedNodeHttp,
    nodeColonhttp2: TypeofimportedNodeHttp2,
    nodeColonhttps: TypeofimportedNodeHttps,
    nodeColoninspector: TypeofimportedNodeInspe,
    nodeColonmodule: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof Module */ Any,
    nodeColonnet: TypeofimportedNodeNet,
    nodeColonos: TypeofimportedNodeOs,
    nodeColonpath: PlatformPath,
    nodeColonpathSlashposix: PlatformPath,
    nodeColonpathSlashwin32: PlatformPath,
    nodeColonperf_hooks: TypeofimportedNodePerf,
    nodeColonprocess: Process,
    nodeColonpunycode: TypeofimportedNodePunyc,
    nodeColonquerystring: TypeofimportedNodeQuery,
    nodeColonreadline: TypeofimportedNodeReadl,
    nodeColonreadlineSlashpromises: ADDRGETNETWORKPARAMS,
    nodeColonrepl: TypeofimportedNodeRepl,
    nodeColonsea: TypeofimportedNodeSea,
    nodeColonstream: Instantiable0[typings.node.nodeColonstreamMod.^] & TypeofimportedNodeStrea,
    nodeColonstreamSlashconsumers: ArrayBuffer,
    nodeColonstreamSlashpromises: ADDRGETNETWORKPARAMS,
    nodeColonstreamSlashweb: ByteLengthQueuingStrategy,
    nodeColonstring_decoder: TypeofimportedNodeStrin,
    nodeColonsys: TypeofimportedNodeUtil,
    nodeColontest: TypeofimportedNodeTest,
    nodeColontestSlashreporters: TypeofimportedReporters,
    nodeColontimers: TypeofimportedNodeTimer,
    nodeColontimersSlashpromises: ADDRGETNETWORKPARAMS,
    nodeColontls: TypeofimportedNodeTls,
    nodeColontrace_events: TypeofimportedNodeTrace,
    nodeColontty: TypeofimportedNodeTty,
    nodeColonurl: TypeofimportedNodeUrl,
    nodeColonutil: TypeofimportedNodeUtil,
    nodeColonutilSlashtypes: IsAnyArrayBuffer,
    nodeColonv8: TypeofimportedNodeV8,
    nodeColonvm: TypeofimportedNodeVm,
    nodeColonwasi: TypeofimportedNodeWasi,
    nodeColonworker_threads: TypeofimportedNodeWorke,
    nodeColonzlib: TypeofimportedNodeZlib,
    os: TypeofimportedOs,
    path: PlatformPath,
    pathSlashposix: PlatformPath,
    pathSlashwin32: PlatformPath,
    perf_hooks: TypeofimportedPerfHooks,
    process: Process,
    punycode: TypeofimportedPunycode,
    querystring: TypeofimportedQuerystrin,
    readline: TypeofimportedReadline,
    readlineSlashpromises: ADDRGETNETWORKPARAMS,
    repl: TypeofimportedRepl,
    stream: Instantiable0[typings.node.streamMod.^] & TypeofimportedStream,
    streamSlashconsumers: ArrayBuffer,
    streamSlashpromises: ADDRGETNETWORKPARAMS,
    streamSlashweb: ByteLengthQueuingStrategy,
    string_decoder: TypeofimportedStringDec,
    sys: TypeofimportedUtil,
    timers: TypeofimportedTimers,
    timersSlashpromises: ADDRGETNETWORKPARAMS,
    tls: TypeofimportedTls,
    trace_events: TypeofimportedTraceEven,
    tty: TypeofimportedTty,
    url: TypeofimportedUrl,
    util: TypeofimportedUtil,
    utilSlashtypes: IsAnyArrayBuffer,
    v8: TypeofimportedV8,
    vm: TypeofimportedVm,
    wasi: TypeofimportedWasi,
    worker_threads: TypeofimportedWorkerThr,
    zlib: TypeofimportedZlib
  ): BuiltInModule = {
    val __obj = js.Dynamic.literal(assert = js.Any.fromFunction2(assert), async_hooks = async_hooks.asInstanceOf[js.Any], buffer = buffer.asInstanceOf[js.Any], child_process = child_process.asInstanceOf[js.Any], cluster = cluster.asInstanceOf[js.Any], console = console.asInstanceOf[js.Any], constants = constants.asInstanceOf[js.Any], crypto = crypto.asInstanceOf[js.Any], dgram = dgram.asInstanceOf[js.Any], diagnostics_channel = diagnostics_channel.asInstanceOf[js.Any], dns = dns.asInstanceOf[js.Any], domain = domain.asInstanceOf[js.Any], events = events.asInstanceOf[js.Any], fs = fs.asInstanceOf[js.Any], http = http.asInstanceOf[js.Any], http2 = http2.asInstanceOf[js.Any], https = https.asInstanceOf[js.Any], inspector = inspector.asInstanceOf[js.Any], module = module.asInstanceOf[js.Any], net = net.asInstanceOf[js.Any], os = os.asInstanceOf[js.Any], path = path.asInstanceOf[js.Any], perf_hooks = perf_hooks.asInstanceOf[js.Any], process = process.asInstanceOf[js.Any], punycode = punycode.asInstanceOf[js.Any], querystring = querystring.asInstanceOf[js.Any], readline = readline.asInstanceOf[js.Any], repl = repl.asInstanceOf[js.Any], stream = stream.asInstanceOf[js.Any], string_decoder = string_decoder.asInstanceOf[js.Any], sys = sys.asInstanceOf[js.Any], timers = timers.asInstanceOf[js.Any], tls = tls.asInstanceOf[js.Any], trace_events = trace_events.asInstanceOf[js.Any], tty = tty.asInstanceOf[js.Any], url = url.asInstanceOf[js.Any], util = util.asInstanceOf[js.Any], v8 = v8.asInstanceOf[js.Any], vm = vm.asInstanceOf[js.Any], wasi = wasi.asInstanceOf[js.Any], worker_threads = worker_threads.asInstanceOf[js.Any], zlib = zlib.asInstanceOf[js.Any])
    __obj.updateDynamic("assert/strict")(assertSlashstrict.asInstanceOf[js.Any])
    __obj.updateDynamic("dns/promises")(dnsSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("fs/promises")(fsSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("node:assert")(js.Any.fromFunction2(nodeColonassert))
    __obj.updateDynamic("node:assert/strict")(nodeColonassertSlashstrict.asInstanceOf[js.Any])
    __obj.updateDynamic("node:async_hooks")(nodeColonasync_hooks.asInstanceOf[js.Any])
    __obj.updateDynamic("node:buffer")(nodeColonbuffer.asInstanceOf[js.Any])
    __obj.updateDynamic("node:child_process")(nodeColonchild_process.asInstanceOf[js.Any])
    __obj.updateDynamic("node:cluster")(nodeColoncluster.asInstanceOf[js.Any])
    __obj.updateDynamic("node:console")(nodeColonconsole.asInstanceOf[js.Any])
    __obj.updateDynamic("node:constants")(nodeColonconstants.asInstanceOf[js.Any])
    __obj.updateDynamic("node:crypto")(nodeColoncrypto.asInstanceOf[js.Any])
    __obj.updateDynamic("node:dgram")(nodeColondgram.asInstanceOf[js.Any])
    __obj.updateDynamic("node:diagnostics_channel")(nodeColondiagnostics_channel.asInstanceOf[js.Any])
    __obj.updateDynamic("node:dns")(nodeColondns.asInstanceOf[js.Any])
    __obj.updateDynamic("node:dns/promises")(nodeColondnsSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("node:domain")(nodeColondomain.asInstanceOf[js.Any])
    __obj.updateDynamic("node:events")(nodeColonevents.asInstanceOf[js.Any])
    __obj.updateDynamic("node:fs")(nodeColonfs.asInstanceOf[js.Any])
    __obj.updateDynamic("node:fs/promises")(nodeColonfsSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("node:http")(nodeColonhttp.asInstanceOf[js.Any])
    __obj.updateDynamic("node:http2")(nodeColonhttp2.asInstanceOf[js.Any])
    __obj.updateDynamic("node:https")(nodeColonhttps.asInstanceOf[js.Any])
    __obj.updateDynamic("node:inspector")(nodeColoninspector.asInstanceOf[js.Any])
    __obj.updateDynamic("node:module")(nodeColonmodule.asInstanceOf[js.Any])
    __obj.updateDynamic("node:net")(nodeColonnet.asInstanceOf[js.Any])
    __obj.updateDynamic("node:os")(nodeColonos.asInstanceOf[js.Any])
    __obj.updateDynamic("node:path")(nodeColonpath.asInstanceOf[js.Any])
    __obj.updateDynamic("node:path/posix")(nodeColonpathSlashposix.asInstanceOf[js.Any])
    __obj.updateDynamic("node:path/win32")(nodeColonpathSlashwin32.asInstanceOf[js.Any])
    __obj.updateDynamic("node:perf_hooks")(nodeColonperf_hooks.asInstanceOf[js.Any])
    __obj.updateDynamic("node:process")(nodeColonprocess.asInstanceOf[js.Any])
    __obj.updateDynamic("node:punycode")(nodeColonpunycode.asInstanceOf[js.Any])
    __obj.updateDynamic("node:querystring")(nodeColonquerystring.asInstanceOf[js.Any])
    __obj.updateDynamic("node:readline")(nodeColonreadline.asInstanceOf[js.Any])
    __obj.updateDynamic("node:readline/promises")(nodeColonreadlineSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("node:repl")(nodeColonrepl.asInstanceOf[js.Any])
    __obj.updateDynamic("node:sea")(nodeColonsea.asInstanceOf[js.Any])
    __obj.updateDynamic("node:stream")(nodeColonstream.asInstanceOf[js.Any])
    __obj.updateDynamic("node:stream/consumers")(nodeColonstreamSlashconsumers.asInstanceOf[js.Any])
    __obj.updateDynamic("node:stream/promises")(nodeColonstreamSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("node:stream/web")(nodeColonstreamSlashweb.asInstanceOf[js.Any])
    __obj.updateDynamic("node:string_decoder")(nodeColonstring_decoder.asInstanceOf[js.Any])
    __obj.updateDynamic("node:sys")(nodeColonsys.asInstanceOf[js.Any])
    __obj.updateDynamic("node:test")(nodeColontest.asInstanceOf[js.Any])
    __obj.updateDynamic("node:test/reporters")(nodeColontestSlashreporters.asInstanceOf[js.Any])
    __obj.updateDynamic("node:timers")(nodeColontimers.asInstanceOf[js.Any])
    __obj.updateDynamic("node:timers/promises")(nodeColontimersSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("node:tls")(nodeColontls.asInstanceOf[js.Any])
    __obj.updateDynamic("node:trace_events")(nodeColontrace_events.asInstanceOf[js.Any])
    __obj.updateDynamic("node:tty")(nodeColontty.asInstanceOf[js.Any])
    __obj.updateDynamic("node:url")(nodeColonurl.asInstanceOf[js.Any])
    __obj.updateDynamic("node:util")(nodeColonutil.asInstanceOf[js.Any])
    __obj.updateDynamic("node:util/types")(nodeColonutilSlashtypes.asInstanceOf[js.Any])
    __obj.updateDynamic("node:v8")(nodeColonv8.asInstanceOf[js.Any])
    __obj.updateDynamic("node:vm")(nodeColonvm.asInstanceOf[js.Any])
    __obj.updateDynamic("node:wasi")(nodeColonwasi.asInstanceOf[js.Any])
    __obj.updateDynamic("node:worker_threads")(nodeColonworker_threads.asInstanceOf[js.Any])
    __obj.updateDynamic("node:zlib")(nodeColonzlib.asInstanceOf[js.Any])
    __obj.updateDynamic("path/posix")(pathSlashposix.asInstanceOf[js.Any])
    __obj.updateDynamic("path/win32")(pathSlashwin32.asInstanceOf[js.Any])
    __obj.updateDynamic("readline/promises")(readlineSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("stream/consumers")(streamSlashconsumers.asInstanceOf[js.Any])
    __obj.updateDynamic("stream/promises")(streamSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("stream/web")(streamSlashweb.asInstanceOf[js.Any])
    __obj.updateDynamic("timers/promises")(timersSlashpromises.asInstanceOf[js.Any])
    __obj.updateDynamic("util/types")(utilSlashtypes.asInstanceOf[js.Any])
    __obj.asInstanceOf[BuiltInModule]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: BuiltInModule] (val x: Self) extends AnyVal {
    
    inline def setAssert(
      value: (/* value */ Any, /* message */ js.UndefOr[String | js.Error]) => /* asserts value */ Boolean
    ): Self = StObject.set(x, "assert", js.Any.fromFunction2(value))
    
    inline def setAssertSlashstrict(
      value: (Omit[
          /* import warning: importer.ImportType#apply Failed type conversion: typeof assert */ js.Any, 
          equal | notEqual | deepEqual | notDeepEqual | ok | strictEqual | deepStrictEqual | ifError | strict
        ]) & Call
    ): Self = StObject.set(x, "assert/strict", value.asInstanceOf[js.Any])
    
    inline def setAsync_hooks(value: TypeofimportedAsyncHook): Self = StObject.set(x, "async_hooks", value.asInstanceOf[js.Any])
    
    inline def setBuffer(value: TypeofimportedBuffer): Self = StObject.set(x, "buffer", value.asInstanceOf[js.Any])
    
    inline def setChild_process(value: TypeofimportedChildProc): Self = StObject.set(x, "child_process", value.asInstanceOf[js.Any])
    
    inline def setCluster(value: TypeofimportedCluster): Self = StObject.set(x, "cluster", value.asInstanceOf[js.Any])
    
    inline def setConsole(
      value: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_console */ Any
    ): Self = StObject.set(x, "console", value.asInstanceOf[js.Any])
    
    inline def setConstants(
      value: (/* import warning: importer.ImportType#apply Failed type conversion: typeof osConstants.errno */ js.Any) & SignalConstants
    ): Self = StObject.set(x, "constants", value.asInstanceOf[js.Any])
    
    inline def setCrypto(value: TypeofimportedCrypto): Self = StObject.set(x, "crypto", value.asInstanceOf[js.Any])
    
    inline def setDgram(value: TypeofimportedDgram): Self = StObject.set(x, "dgram", value.asInstanceOf[js.Any])
    
    inline def setDiagnostics_channel(value: TypeofimportedDiagnostic): Self = StObject.set(x, "diagnostics_channel", value.asInstanceOf[js.Any])
    
    inline def setDns(value: TypeofimportedDns): Self = StObject.set(x, "dns", value.asInstanceOf[js.Any])
    
    inline def setDnsSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "dns/promises", value.asInstanceOf[js.Any])
    
    inline def setDomain(value: TypeofimportedDomain): Self = StObject.set(x, "domain", value.asInstanceOf[js.Any])
    
    inline def setEvents(
      value: (Instantiable1[
          /* options */ js.UndefOr[EventEmitterOptions], 
          typings.node.eventsMod.^[EventMap[/* import warning: RewrittenClass.unapply cls was tparam T */ Any]]
        ]) & TypeofimportedEvents
    ): Self = StObject.set(x, "events", value.asInstanceOf[js.Any])
    
    inline def setFs(value: TypeofimportedFs): Self = StObject.set(x, "fs", value.asInstanceOf[js.Any])
    
    inline def setFsSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "fs/promises", value.asInstanceOf[js.Any])
    
    inline def setHttp(value: TypeofimportedHttp): Self = StObject.set(x, "http", value.asInstanceOf[js.Any])
    
    inline def setHttp2(value: TypeofimportedHttp2): Self = StObject.set(x, "http2", value.asInstanceOf[js.Any])
    
    inline def setHttps(value: TypeofimportedHttps): Self = StObject.set(x, "https", value.asInstanceOf[js.Any])
    
    inline def setInspector(value: TypeofimportedInspector): Self = StObject.set(x, "inspector", value.asInstanceOf[js.Any])
    
    inline def setModule(value: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof Module */ Any): Self = StObject.set(x, "module", value.asInstanceOf[js.Any])
    
    inline def setNet(value: TypeofimportedNet): Self = StObject.set(x, "net", value.asInstanceOf[js.Any])
    
    inline def setNodeColonassert(
      value: (/* value */ Any, /* message */ js.UndefOr[String | js.Error]) => /* asserts value */ Boolean
    ): Self = StObject.set(x, "node:assert", js.Any.fromFunction2(value))
    
    inline def setNodeColonassertSlashstrict(
      value: (Omit[
          /* import warning: importer.ImportType#apply Failed type conversion: typeof assert */ js.Any, 
          equal | notEqual | deepEqual | notDeepEqual | ok | strictEqual | deepStrictEqual | ifError | strict
        ]) & Call
    ): Self = StObject.set(x, "node:assert/strict", value.asInstanceOf[js.Any])
    
    inline def setNodeColonasync_hooks(value: TypeofimportedNodeAsync): Self = StObject.set(x, "node:async_hooks", value.asInstanceOf[js.Any])
    
    inline def setNodeColonbuffer(value: TypeofimportedNodeBuffe): Self = StObject.set(x, "node:buffer", value.asInstanceOf[js.Any])
    
    inline def setNodeColonchild_process(value: TypeofimportedNodeChild): Self = StObject.set(x, "node:child_process", value.asInstanceOf[js.Any])
    
    inline def setNodeColoncluster(value: TypeofimportedNodeClust): Self = StObject.set(x, "node:cluster", value.asInstanceOf[js.Any])
    
    inline def setNodeColonconsole(
      value: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_node:console */ Any
    ): Self = StObject.set(x, "node:console", value.asInstanceOf[js.Any])
    
    inline def setNodeColonconstants(
      value: (/* import warning: importer.ImportType#apply Failed type conversion: typeof osConstants.errno */ js.Any) & SignalConstants
    ): Self = StObject.set(x, "node:constants", value.asInstanceOf[js.Any])
    
    inline def setNodeColoncrypto(value: TypeofimportedNodeCrypt): Self = StObject.set(x, "node:crypto", value.asInstanceOf[js.Any])
    
    inline def setNodeColondgram(value: TypeofimportedNodeDgram): Self = StObject.set(x, "node:dgram", value.asInstanceOf[js.Any])
    
    inline def setNodeColondiagnostics_channel(value: TypeofimportedNodeDiagn): Self = StObject.set(x, "node:diagnostics_channel", value.asInstanceOf[js.Any])
    
    inline def setNodeColondns(value: TypeofimportedNodeDns): Self = StObject.set(x, "node:dns", value.asInstanceOf[js.Any])
    
    inline def setNodeColondnsSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "node:dns/promises", value.asInstanceOf[js.Any])
    
    inline def setNodeColondomain(value: TypeofimportedNodeDomai): Self = StObject.set(x, "node:domain", value.asInstanceOf[js.Any])
    
    inline def setNodeColonevents(
      value: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof imported_node:events */ Any
    ): Self = StObject.set(x, "node:events", value.asInstanceOf[js.Any])
    
    inline def setNodeColonfs(value: TypeofimportedNodeFs): Self = StObject.set(x, "node:fs", value.asInstanceOf[js.Any])
    
    inline def setNodeColonfsSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "node:fs/promises", value.asInstanceOf[js.Any])
    
    inline def setNodeColonhttp(value: TypeofimportedNodeHttp): Self = StObject.set(x, "node:http", value.asInstanceOf[js.Any])
    
    inline def setNodeColonhttp2(value: TypeofimportedNodeHttp2): Self = StObject.set(x, "node:http2", value.asInstanceOf[js.Any])
    
    inline def setNodeColonhttps(value: TypeofimportedNodeHttps): Self = StObject.set(x, "node:https", value.asInstanceOf[js.Any])
    
    inline def setNodeColoninspector(value: TypeofimportedNodeInspe): Self = StObject.set(x, "node:inspector", value.asInstanceOf[js.Any])
    
    inline def setNodeColonmodule(value: /* import warning: ResolveTypeQueries.resolve Couldn't resolve typeof Module */ Any): Self = StObject.set(x, "node:module", value.asInstanceOf[js.Any])
    
    inline def setNodeColonnet(value: TypeofimportedNodeNet): Self = StObject.set(x, "node:net", value.asInstanceOf[js.Any])
    
    inline def setNodeColonos(value: TypeofimportedNodeOs): Self = StObject.set(x, "node:os", value.asInstanceOf[js.Any])
    
    inline def setNodeColonpath(value: PlatformPath): Self = StObject.set(x, "node:path", value.asInstanceOf[js.Any])
    
    inline def setNodeColonpathSlashposix(value: PlatformPath): Self = StObject.set(x, "node:path/posix", value.asInstanceOf[js.Any])
    
    inline def setNodeColonpathSlashwin32(value: PlatformPath): Self = StObject.set(x, "node:path/win32", value.asInstanceOf[js.Any])
    
    inline def setNodeColonperf_hooks(value: TypeofimportedNodePerf): Self = StObject.set(x, "node:perf_hooks", value.asInstanceOf[js.Any])
    
    inline def setNodeColonprocess(value: Process): Self = StObject.set(x, "node:process", value.asInstanceOf[js.Any])
    
    inline def setNodeColonpunycode(value: TypeofimportedNodePunyc): Self = StObject.set(x, "node:punycode", value.asInstanceOf[js.Any])
    
    inline def setNodeColonquerystring(value: TypeofimportedNodeQuery): Self = StObject.set(x, "node:querystring", value.asInstanceOf[js.Any])
    
    inline def setNodeColonreadline(value: TypeofimportedNodeReadl): Self = StObject.set(x, "node:readline", value.asInstanceOf[js.Any])
    
    inline def setNodeColonreadlineSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "node:readline/promises", value.asInstanceOf[js.Any])
    
    inline def setNodeColonrepl(value: TypeofimportedNodeRepl): Self = StObject.set(x, "node:repl", value.asInstanceOf[js.Any])
    
    inline def setNodeColonsea(value: TypeofimportedNodeSea): Self = StObject.set(x, "node:sea", value.asInstanceOf[js.Any])
    
    inline def setNodeColonstream(value: Instantiable0[typings.node.nodeColonstreamMod.^] & TypeofimportedNodeStrea): Self = StObject.set(x, "node:stream", value.asInstanceOf[js.Any])
    
    inline def setNodeColonstreamSlashconsumers(value: ArrayBuffer): Self = StObject.set(x, "node:stream/consumers", value.asInstanceOf[js.Any])
    
    inline def setNodeColonstreamSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "node:stream/promises", value.asInstanceOf[js.Any])
    
    inline def setNodeColonstreamSlashweb(value: ByteLengthQueuingStrategy): Self = StObject.set(x, "node:stream/web", value.asInstanceOf[js.Any])
    
    inline def setNodeColonstring_decoder(value: TypeofimportedNodeStrin): Self = StObject.set(x, "node:string_decoder", value.asInstanceOf[js.Any])
    
    inline def setNodeColonsys(value: TypeofimportedNodeUtil): Self = StObject.set(x, "node:sys", value.asInstanceOf[js.Any])
    
    inline def setNodeColontest(value: TypeofimportedNodeTest): Self = StObject.set(x, "node:test", value.asInstanceOf[js.Any])
    
    inline def setNodeColontestSlashreporters(value: TypeofimportedReporters): Self = StObject.set(x, "node:test/reporters", value.asInstanceOf[js.Any])
    
    inline def setNodeColontimers(value: TypeofimportedNodeTimer): Self = StObject.set(x, "node:timers", value.asInstanceOf[js.Any])
    
    inline def setNodeColontimersSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "node:timers/promises", value.asInstanceOf[js.Any])
    
    inline def setNodeColontls(value: TypeofimportedNodeTls): Self = StObject.set(x, "node:tls", value.asInstanceOf[js.Any])
    
    inline def setNodeColontrace_events(value: TypeofimportedNodeTrace): Self = StObject.set(x, "node:trace_events", value.asInstanceOf[js.Any])
    
    inline def setNodeColontty(value: TypeofimportedNodeTty): Self = StObject.set(x, "node:tty", value.asInstanceOf[js.Any])
    
    inline def setNodeColonurl(value: TypeofimportedNodeUrl): Self = StObject.set(x, "node:url", value.asInstanceOf[js.Any])
    
    inline def setNodeColonutil(value: TypeofimportedNodeUtil): Self = StObject.set(x, "node:util", value.asInstanceOf[js.Any])
    
    inline def setNodeColonutilSlashtypes(value: IsAnyArrayBuffer): Self = StObject.set(x, "node:util/types", value.asInstanceOf[js.Any])
    
    inline def setNodeColonv8(value: TypeofimportedNodeV8): Self = StObject.set(x, "node:v8", value.asInstanceOf[js.Any])
    
    inline def setNodeColonvm(value: TypeofimportedNodeVm): Self = StObject.set(x, "node:vm", value.asInstanceOf[js.Any])
    
    inline def setNodeColonwasi(value: TypeofimportedNodeWasi): Self = StObject.set(x, "node:wasi", value.asInstanceOf[js.Any])
    
    inline def setNodeColonworker_threads(value: TypeofimportedNodeWorke): Self = StObject.set(x, "node:worker_threads", value.asInstanceOf[js.Any])
    
    inline def setNodeColonzlib(value: TypeofimportedNodeZlib): Self = StObject.set(x, "node:zlib", value.asInstanceOf[js.Any])
    
    inline def setOs(value: TypeofimportedOs): Self = StObject.set(x, "os", value.asInstanceOf[js.Any])
    
    inline def setPath(value: PlatformPath): Self = StObject.set(x, "path", value.asInstanceOf[js.Any])
    
    inline def setPathSlashposix(value: PlatformPath): Self = StObject.set(x, "path/posix", value.asInstanceOf[js.Any])
    
    inline def setPathSlashwin32(value: PlatformPath): Self = StObject.set(x, "path/win32", value.asInstanceOf[js.Any])
    
    inline def setPerf_hooks(value: TypeofimportedPerfHooks): Self = StObject.set(x, "perf_hooks", value.asInstanceOf[js.Any])
    
    inline def setProcess(value: Process): Self = StObject.set(x, "process", value.asInstanceOf[js.Any])
    
    inline def setPunycode(value: TypeofimportedPunycode): Self = StObject.set(x, "punycode", value.asInstanceOf[js.Any])
    
    inline def setQuerystring(value: TypeofimportedQuerystrin): Self = StObject.set(x, "querystring", value.asInstanceOf[js.Any])
    
    inline def setReadline(value: TypeofimportedReadline): Self = StObject.set(x, "readline", value.asInstanceOf[js.Any])
    
    inline def setReadlineSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "readline/promises", value.asInstanceOf[js.Any])
    
    inline def setRepl(value: TypeofimportedRepl): Self = StObject.set(x, "repl", value.asInstanceOf[js.Any])
    
    inline def setStream(value: Instantiable0[typings.node.streamMod.^] & TypeofimportedStream): Self = StObject.set(x, "stream", value.asInstanceOf[js.Any])
    
    inline def setStreamSlashconsumers(value: ArrayBuffer): Self = StObject.set(x, "stream/consumers", value.asInstanceOf[js.Any])
    
    inline def setStreamSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "stream/promises", value.asInstanceOf[js.Any])
    
    inline def setStreamSlashweb(value: ByteLengthQueuingStrategy): Self = StObject.set(x, "stream/web", value.asInstanceOf[js.Any])
    
    inline def setString_decoder(value: TypeofimportedStringDec): Self = StObject.set(x, "string_decoder", value.asInstanceOf[js.Any])
    
    inline def setSys(value: TypeofimportedUtil): Self = StObject.set(x, "sys", value.asInstanceOf[js.Any])
    
    inline def setTimers(value: TypeofimportedTimers): Self = StObject.set(x, "timers", value.asInstanceOf[js.Any])
    
    inline def setTimersSlashpromises(value: ADDRGETNETWORKPARAMS): Self = StObject.set(x, "timers/promises", value.asInstanceOf[js.Any])
    
    inline def setTls(value: TypeofimportedTls): Self = StObject.set(x, "tls", value.asInstanceOf[js.Any])
    
    inline def setTrace_events(value: TypeofimportedTraceEven): Self = StObject.set(x, "trace_events", value.asInstanceOf[js.Any])
    
    inline def setTty(value: TypeofimportedTty): Self = StObject.set(x, "tty", value.asInstanceOf[js.Any])
    
    inline def setUrl(value: TypeofimportedUrl): Self = StObject.set(x, "url", value.asInstanceOf[js.Any])
    
    inline def setUtil(value: TypeofimportedUtil): Self = StObject.set(x, "util", value.asInstanceOf[js.Any])
    
    inline def setUtilSlashtypes(value: IsAnyArrayBuffer): Self = StObject.set(x, "util/types", value.asInstanceOf[js.Any])
    
    inline def setV8(value: TypeofimportedV8): Self = StObject.set(x, "v8", value.asInstanceOf[js.Any])
    
    inline def setVm(value: TypeofimportedVm): Self = StObject.set(x, "vm", value.asInstanceOf[js.Any])
    
    inline def setWasi(value: TypeofimportedWasi): Self = StObject.set(x, "wasi", value.asInstanceOf[js.Any])
    
    inline def setWorker_threads(value: TypeofimportedWorkerThr): Self = StObject.set(x, "worker_threads", value.asInstanceOf[js.Any])
    
    inline def setZlib(value: TypeofimportedZlib): Self = StObject.set(x, "zlib", value.asInstanceOf[js.Any])
  }
}
