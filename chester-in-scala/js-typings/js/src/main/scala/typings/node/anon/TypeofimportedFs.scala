package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import org.scalajs.dom.Blob
import typings.node.bufferMod.global.BufferEncoding
import typings.node.fsMod.BigIntStats
import typings.node.fsMod.BigIntStatsFs
import typings.node.fsMod.BigIntStatsListener
import typings.node.fsMod.BufferEncodingOption
import typings.node.fsMod.CopyOptions
import typings.node.fsMod.CopySyncOptions
import typings.node.fsMod.Dir
import typings.node.fsMod.Dirent
import typings.node.fsMod.EncodingOption
import typings.node.fsMod.FSWatcher
import typings.node.fsMod.GlobOptions
import typings.node.fsMod.GlobOptionsWithFileTypes
import typings.node.fsMod.GlobOptionsWithoutFileTypes
import typings.node.fsMod.MakeDirectoryOptions
import typings.node.fsMod.NoParamCallback
import typings.node.fsMod.OpenAsBlobOptions
import typings.node.fsMod.OpenDirOptions
import typings.node.fsMod.OpenMode
import typings.node.fsMod.PathLike
import typings.node.fsMod.PathOrFileDescriptor
import typings.node.fsMod.ReadAsyncOptions
import typings.node.fsMod.ReadPosition
import typings.node.fsMod.ReadStream
import typings.node.fsMod.ReadStreamOptions
import typings.node.fsMod.ReadSyncOptions
import typings.node.fsMod.RmDirOptions
import typings.node.fsMod.RmOptions
import typings.node.fsMod.StatFsOptions
import typings.node.fsMod.StatOptions
import typings.node.fsMod.StatSyncFn
import typings.node.fsMod.StatSyncOptions
import typings.node.fsMod.StatWatcher
import typings.node.fsMod.Stats
import typings.node.fsMod.StatsFs
import typings.node.fsMod.StatsListener
import typings.node.fsMod.TimeLike
import typings.node.fsMod.WatchListener
import typings.node.fsMod.WatchOptions
import typings.node.fsMod.WriteFileOptions
import typings.node.fsMod.WriteStream
import typings.node.fsMod.WriteStreamOptions
import typings.node.globalsMod.global.NodeJS.ArrayBufferView
import typings.node.globalsMod.global.NodeJS.ErrnoException
import typings.node.globalsMod.global.RelativeIndexable
import typings.node.nodeColonurlMod.URL
import typings.node.nodeStrings.buffer_
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait TypeofimportedFs extends StObject {
  
  var Dir: Instantiable0[typings.node.fsMod.Dir] = js.native
  
  var Dirent: Instantiable0[typings.node.fsMod.Dirent] = js.native
  
  var ReadStream: Instantiable0[typings.node.fsMod.ReadStream] = js.native
  
  var Stats: Instantiable0[typings.node.fsMod.Stats] = js.native
  
  var StatsFs: Instantiable0[typings.node.fsMod.StatsFs] = js.native
  
  var WriteStream: Instantiable0[typings.node.fsMod.WriteStream] = js.native
  
  val access: Typeofaccess = js.native
  def access(path: PathLike, callback: NoParamCallback): Unit = js.native
  
  def accessSync(path: PathLike): Unit = js.native
  def accessSync(path: PathLike, mode: Double): Unit = js.native
  
  val appendFile: TypeofappendFile = js.native
  def appendFile(file: PathOrFileDescriptor, data: String, callback: NoParamCallback): Unit = js.native
  def appendFile(file: PathOrFileDescriptor, data: js.typedarray.Uint8Array, callback: NoParamCallback): Unit = js.native
  
  def appendFileSync(path: PathOrFileDescriptor, data: String): Unit = js.native
  def appendFileSync(path: PathOrFileDescriptor, data: String, options: WriteFileOptions): Unit = js.native
  def appendFileSync(path: PathOrFileDescriptor, data: js.typedarray.Uint8Array): Unit = js.native
  def appendFileSync(path: PathOrFileDescriptor, data: js.typedarray.Uint8Array, options: WriteFileOptions): Unit = js.native
  
  val chmod: Typeofchmod = js.native
  
  def chmodSync(path: PathLike, mode: typings.node.fsMod.Mode): Unit = js.native
  
  val chown: Typeofchown = js.native
  
  def chownSync(path: PathLike, uid: Double, gid: Double): Unit = js.native
  
  val close: Typeofclose = js.native
  
  def closeSync(fd: Double): Unit = js.native
  
  val constants: TypeofconstantsCOPYFILEEXCL = js.native
  
  val copyFile: TypeofcopyFile = js.native
  def copyFile(src: PathLike, dest: PathLike, mode: Double, callback: NoParamCallback): Unit = js.native
  
  def copyFileSync(src: PathLike, dest: PathLike): Unit = js.native
  def copyFileSync(src: PathLike, dest: PathLike, mode: Double): Unit = js.native
  
  def cp(source: String, destination: String, callback: js.Function1[/* err */ ErrnoException | Null, Unit]): Unit = js.native
  def cp(
    source: String,
    destination: String,
    opts: CopyOptions,
    callback: js.Function1[/* err */ ErrnoException | Null, Unit]
  ): Unit = js.native
  def cp(source: String, destination: URL, callback: js.Function1[/* err */ ErrnoException | Null, Unit]): Unit = js.native
  def cp(
    source: String,
    destination: URL,
    opts: CopyOptions,
    callback: js.Function1[/* err */ ErrnoException | Null, Unit]
  ): Unit = js.native
  def cp(source: URL, destination: String, callback: js.Function1[/* err */ ErrnoException | Null, Unit]): Unit = js.native
  def cp(
    source: URL,
    destination: String,
    opts: CopyOptions,
    callback: js.Function1[/* err */ ErrnoException | Null, Unit]
  ): Unit = js.native
  def cp(source: URL, destination: URL, callback: js.Function1[/* err */ ErrnoException | Null, Unit]): Unit = js.native
  def cp(
    source: URL,
    destination: URL,
    opts: CopyOptions,
    callback: js.Function1[/* err */ ErrnoException | Null, Unit]
  ): Unit = js.native
  
  def cpSync(source: String, destination: String): Unit = js.native
  def cpSync(source: String, destination: String, opts: CopySyncOptions): Unit = js.native
  def cpSync(source: String, destination: URL): Unit = js.native
  def cpSync(source: String, destination: URL, opts: CopySyncOptions): Unit = js.native
  def cpSync(source: URL, destination: String): Unit = js.native
  def cpSync(source: URL, destination: String, opts: CopySyncOptions): Unit = js.native
  def cpSync(source: URL, destination: URL): Unit = js.native
  def cpSync(source: URL, destination: URL, opts: CopySyncOptions): Unit = js.native
  
  def createReadStream(path: PathLike): ReadStream = js.native
  def createReadStream(path: PathLike, options: BufferEncoding): ReadStream = js.native
  def createReadStream(path: PathLike, options: ReadStreamOptions): ReadStream = js.native
  
  def createWriteStream(path: PathLike): WriteStream = js.native
  def createWriteStream(path: PathLike, options: BufferEncoding): WriteStream = js.native
  def createWriteStream(path: PathLike, options: WriteStreamOptions): WriteStream = js.native
  
  val exists: Typeofexists = js.native
  
  def existsSync(path: PathLike): Boolean = js.native
  
  val fchmod: Typeoffchmod = js.native
  
  def fchmodSync(fd: Double, mode: typings.node.fsMod.Mode): Unit = js.native
  
  val fchown: Typeoffchown = js.native
  
  def fchownSync(fd: Double, uid: Double, gid: Double): Unit = js.native
  
  val fdatasync: Typeoffdatasync = js.native
  
  def fdatasyncSync(fd: Double): Unit = js.native
  
  val fstat: Typeoffstat = js.native
  def fstat(
    fd: Double,
    options: Unit,
    callback: js.Function2[ErrnoException | Null, BigIntStats | (/* stats */ Stats), Unit]
  ): Unit = js.native
  def fstat(
    fd: Double,
    options: StatOptionsbigintfalseund,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats, Unit]
  ): Unit = js.native
  def fstat(
    fd: Double,
    options: StatOptionsbiginttrue,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ BigIntStats, Unit]
  ): Unit = js.native
  def fstat(
    fd: Double,
    options: StatOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats | BigIntStats, Unit]
  ): Unit = js.native
  
  def fstatSync(fd: Double): Stats | BigIntStats = js.native
  def fstatSync(fd: Double, options: StatOptionsbigintfalseund): Stats = js.native
  def fstatSync(fd: Double, options: StatOptionsbiginttrue): BigIntStats = js.native
  def fstatSync(fd: Double, options: StatOptions): Stats | BigIntStats = js.native
  @JSName("fstatSync")
  def fstatSync_Stats(fd: Double): Stats = js.native
  
  val fsync: Typeoffsync = js.native
  
  def fsyncSync(fd: Double): Unit = js.native
  
  val ftruncate: Typeofftruncate = js.native
  def ftruncate(fd: Double, callback: NoParamCallback): Unit = js.native
  
  def ftruncateSync(fd: Double): Unit = js.native
  def ftruncateSync(fd: Double, len: Double): Unit = js.native
  
  val futimes: Typeoffutimes = js.native
  
  def futimesSync(fd: Double, atime: TimeLike, mtime: TimeLike): Unit = js.native
  
  def glob(
    pattern: String,
    callback: js.Function2[/* err */ ErrnoException | Null, /* matches */ js.Array[String], Unit]
  ): Unit = js.native
  def glob(
    pattern: String,
    options: GlobOptionsWithFileTypes,
    callback: js.Function2[/* err */ ErrnoException | Null, /* matches */ js.Array[Dirent], Unit]
  ): Unit = js.native
  def glob(
    pattern: String,
    options: GlobOptionsWithoutFileTypes,
    callback: js.Function2[/* err */ ErrnoException | Null, /* matches */ js.Array[String], Unit]
  ): Unit = js.native
  def glob(
    pattern: String,
    options: GlobOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* matches */ js.Array[Dirent | String], Unit]
  ): Unit = js.native
  def glob(
    pattern: js.Array[String],
    callback: js.Function2[/* err */ ErrnoException | Null, /* matches */ js.Array[String], Unit]
  ): Unit = js.native
  def glob(
    pattern: js.Array[String],
    options: GlobOptionsWithFileTypes,
    callback: js.Function2[/* err */ ErrnoException | Null, /* matches */ js.Array[Dirent], Unit]
  ): Unit = js.native
  def glob(
    pattern: js.Array[String],
    options: GlobOptionsWithoutFileTypes,
    callback: js.Function2[/* err */ ErrnoException | Null, /* matches */ js.Array[String], Unit]
  ): Unit = js.native
  def glob(
    pattern: js.Array[String],
    options: GlobOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* matches */ js.Array[Dirent | String], Unit]
  ): Unit = js.native
  
  def globSync(pattern: String): js.Array[String] = js.native
  def globSync(pattern: String, options: GlobOptions): js.Array[Dirent | String] = js.native
  def globSync(pattern: String, options: GlobOptionsWithFileTypes): js.Array[Dirent] = js.native
  def globSync(pattern: String, options: GlobOptionsWithoutFileTypes): js.Array[String] = js.native
  def globSync(pattern: js.Array[String]): js.Array[String] = js.native
  def globSync(pattern: js.Array[String], options: GlobOptions): js.Array[Dirent | String] = js.native
  def globSync(pattern: js.Array[String], options: GlobOptionsWithFileTypes): js.Array[Dirent] = js.native
  def globSync(pattern: js.Array[String], options: GlobOptionsWithoutFileTypes): js.Array[String] = js.native
  
  val lchmod: Typeoflchmod = js.native
  
  def lchmodSync(path: PathLike, mode: typings.node.fsMod.Mode): Unit = js.native
  
  val lchown: Typeoflchown = js.native
  
  def lchownSync(path: PathLike, uid: Double, gid: Double): Unit = js.native
  
  val link: Typeoflink = js.native
  
  def linkSync(existingPath: PathLike, newPath: PathLike): Unit = js.native
  
  val lstat: Typeoflstat = js.native
  def lstat(
    path: PathLike,
    options: Unit,
    callback: js.Function2[ErrnoException | Null, BigIntStats | (/* stats */ Stats), Unit]
  ): Unit = js.native
  def lstat(
    path: PathLike,
    options: StatOptionsbigintfalseund,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats, Unit]
  ): Unit = js.native
  def lstat(
    path: PathLike,
    options: StatOptionsbiginttrue,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ BigIntStats, Unit]
  ): Unit = js.native
  def lstat(
    path: PathLike,
    options: StatOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats | BigIntStats, Unit]
  ): Unit = js.native
  
  def lstatSync(path: PathLike): js.UndefOr[Stats] = js.native
  def lstatSync(path: PathLike, options: Unit): Stats = js.native
  def lstatSync(path: PathLike, options: StatSyncOptionsbigintbool): Stats | BigIntStats = js.native
  def lstatSync(path: PathLike, options: StatSyncOptionsbigintfals): js.UndefOr[Stats] = js.native
  def lstatSync(path: PathLike, options: StatSyncOptionsbigintfalsBigint): Stats = js.native
  def lstatSync(path: PathLike, options: StatSyncOptionsbiginttrue): js.UndefOr[BigIntStats] = js.native
  def lstatSync(path: PathLike, options: StatSyncOptionsbiginttrueBigint): BigIntStats = js.native
  def lstatSync(path: PathLike, options: StatSyncOptions): js.UndefOr[Stats | BigIntStats] = js.native
  @JSName("lstatSync")
  val lstatSync_Original: StatSyncFn = js.native
  @JSName("lstatSync")
  def lstatSync_Stats(path: PathLike): Stats = js.native
  
  val lutimes: Typeoflutimes = js.native
  
  def lutimesSync(path: PathLike, atime: TimeLike, mtime: TimeLike): Unit = js.native
  
  val mkdir: Typeofmkdir = js.native
  def mkdir(path: PathLike, callback: NoParamCallback): Unit = js.native
  def mkdir(
    path: PathLike,
    options: Null,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def mkdir(path: PathLike, options: Null, callback: NoParamCallback): Unit = js.native
  def mkdir(
    path: PathLike,
    options: Unit,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def mkdir(path: PathLike, options: Unit, callback: NoParamCallback): Unit = js.native
  def mkdir(path: PathLike, options: MakeDirectoryOptionsrecurMode, callback: NoParamCallback): Unit = js.native
  def mkdir(
    path: PathLike,
    options: MakeDirectoryOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def mkdir(
    path: PathLike,
    options: typings.node.fsMod.Mode,
    callback: js.Function2[/* err */ ErrnoException | Null, /* path */ js.UndefOr[String], Unit]
  ): Unit = js.native
  def mkdir(path: PathLike, options: typings.node.fsMod.Mode, callback: NoParamCallback): Unit = js.native
  
  def mkdirSync(path: PathLike): js.UndefOr[String] = js.native
  def mkdirSync(path: PathLike, options: MakeDirectoryOptionsrecur): js.UndefOr[String] = js.native
  def mkdirSync(path: PathLike, options: MakeDirectoryOptionsrecurMode): Unit = js.native
  def mkdirSync(path: PathLike, options: MakeDirectoryOptions): js.UndefOr[String] = js.native
  def mkdirSync(path: PathLike, options: typings.node.fsMod.Mode): js.UndefOr[String] = js.native
  @JSName("mkdirSync")
  def mkdirSync_Unit(path: PathLike): Unit = js.native
  @JSName("mkdirSync")
  def mkdirSync_Unit(path: PathLike, options: typings.node.fsMod.Mode): Unit = js.native
  
  val mkdtemp: Typeofmkdtemp = js.native
  def mkdtemp(prefix: String, callback: js.Function2[/* err */ ErrnoException | Null, /* folder */ String, Unit]): Unit = js.native
  def mkdtemp(
    prefix: String,
    options: `2`,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* folder */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def mkdtemp(
    prefix: String,
    options: EncodingOption,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* folder */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  
  def mkdtempSync(prefix: String): String = js.native
  def mkdtempSync(prefix: String, options: BufferEncodingOption): typings.node.bufferMod.global.Buffer = js.native
  def mkdtempSync(prefix: String, options: EncodingOption): String = js.native
  @JSName("mkdtempSync")
  def mkdtempSync_Union(prefix: String): String | typings.node.bufferMod.global.Buffer = js.native
  @JSName("mkdtempSync")
  def mkdtempSync_Union(prefix: String, options: EncodingOption): String | typings.node.bufferMod.global.Buffer = js.native
  
  @JSName("mkdtemp")
  def mkdtemp_buffer(
    prefix: String,
    options: buffer_,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* folder */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  
  val open: Typeofopen = js.native
  def open(path: PathLike, callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]): Unit = js.native
  def open(
    path: PathLike,
    flags: Unit,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  def open(
    path: PathLike,
    flags: OpenMode,
    callback: js.Function2[/* err */ ErrnoException | Null, /* fd */ Double, Unit]
  ): Unit = js.native
  
  def openAsBlob(path: PathLike): js.Promise[Blob] = js.native
  def openAsBlob(path: PathLike, options: OpenAsBlobOptions): js.Promise[Blob] = js.native
  
  def openSync(path: PathLike, flags: OpenMode): Double = js.native
  def openSync(path: PathLike, flags: OpenMode, mode: typings.node.fsMod.Mode): Double = js.native
  
  val opendir: Typeofopendir = js.native
  def opendir(
    path: PathLike,
    options: OpenDirOptions,
    cb: js.Function2[/* err */ ErrnoException | Null, /* dir */ Dir, Unit]
  ): Unit = js.native
  
  def opendirSync(path: PathLike): Dir = js.native
  def opendirSync(path: PathLike, options: OpenDirOptions): Dir = js.native
  
  val promises: TypeofpromisesAccess = js.native
  
  val read: Typeofread = js.native
  def read(
    fd: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* bytesRead */ Double, 
      /* buffer */ ArrayBufferView, 
      Unit
    ]
  ): Unit = js.native
  def read[TBuffer /* <: ArrayBufferView */](
    fd: Double,
    options: ReadAsyncOptions[TBuffer],
    callback: js.Function3[/* err */ ErrnoException | Null, /* bytesRead */ Double, /* buffer */ TBuffer, Unit]
  ): Unit = js.native
  
  val readFile: TypeofreadFile = js.native
  def readFile(
    path: PathOrFileDescriptor,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* data */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def readFile(
    path: PathOrFileDescriptor,
    options: Null,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* data */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def readFile(
    path: PathOrFileDescriptor,
    options: Unit,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* data */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def readFile(
    path: PathOrFileDescriptor,
    options: ObjectEncodingOptionsflag,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* data */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def readFile(
    path: PathOrFileDescriptor,
    options: encodingBufferEncodingflaEncoding,
    callback: js.Function2[/* err */ ErrnoException | Null, /* data */ String, Unit]
  ): Unit = js.native
  def readFile(
    path: PathOrFileDescriptor,
    options: BufferEncoding,
    callback: js.Function2[
      ErrnoException | Null, 
      typings.node.bufferMod.global.Buffer | (/* data */ String), 
      Unit
    ]
  ): Unit = js.native
  
  def readFileSync(path: PathOrFileDescriptor): String | typings.node.bufferMod.global.Buffer = js.native
  def readFileSync(path: PathOrFileDescriptor, options: EncodingFlag): String = js.native
  def readFileSync(path: PathOrFileDescriptor, options: Flag): typings.node.bufferMod.global.Buffer = js.native
  def readFileSync(path: PathOrFileDescriptor, options: ObjectEncodingOptionsflagEncoding): String | typings.node.bufferMod.global.Buffer = js.native
  def readFileSync(path: PathOrFileDescriptor, options: BufferEncoding): String = js.native
  @JSName("readFileSync")
  def readFileSync_Buffer(path: PathOrFileDescriptor): typings.node.bufferMod.global.Buffer = js.native
  @JSName("readFileSync")
  def readFileSync_Union(path: PathOrFileDescriptor, options: BufferEncoding): String | typings.node.bufferMod.global.Buffer = js.native
  
  def readSync(fd: Double, buffer: ArrayBufferView): Double = js.native
  def readSync(fd: Double, buffer: ArrayBufferView, offset: Double, length: Double): Double = js.native
  def readSync(fd: Double, buffer: ArrayBufferView, offset: Double, length: Double, position: ReadPosition): Double = js.native
  def readSync(fd: Double, buffer: ArrayBufferView, opts: ReadSyncOptions): Double = js.native
  
  val readdir: Typeofreaddir = js.native
  def readdir(
    path: PathLike,
    callback: js.Function2[/* err */ ErrnoException | Null, /* files */ js.Array[String], Unit]
  ): Unit = js.native
  def readdir(
    path: PathLike,
    options: Null,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer | String], 
      Unit
    ]
  ): Unit = js.native
  def readdir(
    path: PathLike,
    options: Unit,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer | String], 
      Unit
    ]
  ): Unit = js.native
  def readdir(
    path: PathLike,
    options: Encoding,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer], 
      Unit
    ]
  ): Unit = js.native
  def readdir(
    path: PathLike,
    options: ObjectEncodingOptionswithEncoding,
    callback: js.Function2[/* err */ ErrnoException | Null, /* files */ js.Array[Dirent], Unit]
  ): Unit = js.native
  def readdir(
    path: PathLike,
    options: ObjectEncodingOptionswith,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer | String], 
      Unit
    ]
  ): Unit = js.native
  def readdir(
    path: PathLike,
    options: BufferEncoding,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer | String], 
      Unit
    ]
  ): Unit = js.native
  
  def readdirSync(path: PathLike): js.Array[String] = js.native
  def readdirSync(path: PathLike, options: Encoding): js.Array[typings.node.bufferMod.global.Buffer] = js.native
  def readdirSync(path: PathLike, options: ObjectEncodingOptionswith): js.Array[typings.node.bufferMod.global.Buffer | String] = js.native
  def readdirSync(path: PathLike, options: ObjectEncodingOptionswithEncoding): js.Array[Dirent] = js.native
  def readdirSync(path: PathLike, options: Recursive): js.Array[String] = js.native
  def readdirSync(path: PathLike, options: BufferEncoding): js.Array[String] = js.native
  @JSName("readdirSync")
  def readdirSync_buffer(path: PathLike, options: buffer_): js.Array[typings.node.bufferMod.global.Buffer] = js.native
  
  @JSName("readdir")
  def readdir_buffer(
    path: PathLike,
    options: buffer_,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* files */ js.Array[typings.node.bufferMod.global.Buffer], 
      Unit
    ]
  ): Unit = js.native
  
  val readlink: Typeofreadlink = js.native
  def readlink(
    path: PathLike,
    callback: js.Function2[/* err */ ErrnoException | Null, /* linkString */ String, Unit]
  ): Unit = js.native
  def readlink(
    path: PathLike,
    options: BufferEncodingOption,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* linkString */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def readlink(
    path: PathLike,
    options: EncodingOption,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* linkString */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  
  def readlinkSync(path: PathLike): String = js.native
  def readlinkSync(path: PathLike, options: BufferEncodingOption): typings.node.bufferMod.global.Buffer = js.native
  def readlinkSync(path: PathLike, options: EncodingOption): String = js.native
  @JSName("readlinkSync")
  def readlinkSync_Union(path: PathLike): String | typings.node.bufferMod.global.Buffer = js.native
  @JSName("readlinkSync")
  def readlinkSync_Union(path: PathLike, options: EncodingOption): String | typings.node.bufferMod.global.Buffer = js.native
  
  val readv: Typeofreadv = js.native
  def readv(
    fd: Double,
    buffers: js.Array[ArrayBufferView],
    position: Double,
    cb: js.Function3[
      /* err */ ErrnoException | Null, 
      /* bytesRead */ Double, 
      /* buffers */ js.Array[ArrayBufferView], 
      Unit
    ]
  ): Unit = js.native
  
  def readvSync(fd: Double, buffers: js.Array[ArrayBufferView]): Double = js.native
  def readvSync(fd: Double, buffers: js.Array[ArrayBufferView], position: Double): Double = js.native
  
  val realpath: Typeofrealpath = js.native
  def realpath(
    path: PathLike,
    callback: js.Function2[/* err */ ErrnoException | Null, /* resolvedPath */ String, Unit]
  ): Unit = js.native
  def realpath(
    path: PathLike,
    options: BufferEncodingOption,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* resolvedPath */ typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  def realpath(
    path: PathLike,
    options: EncodingOption,
    callback: js.Function2[
      /* err */ ErrnoException | Null, 
      /* resolvedPath */ String | typings.node.bufferMod.global.Buffer, 
      Unit
    ]
  ): Unit = js.native
  
  val realpathSync: TypeofrealpathSync = js.native
  def realpathSync(path: PathLike): String | typings.node.bufferMod.global.Buffer = js.native
  def realpathSync(path: PathLike, options: BufferEncodingOption): typings.node.bufferMod.global.Buffer = js.native
  def realpathSync(path: PathLike, options: EncodingOption): String | typings.node.bufferMod.global.Buffer = js.native
  
  val rename: Typeofrename = js.native
  
  def renameSync(oldPath: PathLike, newPath: PathLike): Unit = js.native
  
  val rm: Typeofrm = js.native
  def rm(path: PathLike, options: RmOptions, callback: NoParamCallback): Unit = js.native
  
  def rmSync(path: PathLike): Unit = js.native
  def rmSync(path: PathLike, options: RmOptions): Unit = js.native
  
  val rmdir: Typeofrmdir = js.native
  def rmdir(path: PathLike, options: RmDirOptions, callback: NoParamCallback): Unit = js.native
  
  def rmdirSync(path: PathLike): Unit = js.native
  def rmdirSync(path: PathLike, options: RmDirOptions): Unit = js.native
  
  val stat: Typeofstat = js.native
  def stat(
    path: PathLike,
    options: Unit,
    callback: js.Function2[ErrnoException | Null, BigIntStats | (/* stats */ Stats), Unit]
  ): Unit = js.native
  def stat(
    path: PathLike,
    options: StatOptionsbigintfalseund,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats, Unit]
  ): Unit = js.native
  def stat(
    path: PathLike,
    options: StatOptionsbiginttrue,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ BigIntStats, Unit]
  ): Unit = js.native
  def stat(
    path: PathLike,
    options: StatOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ Stats | BigIntStats, Unit]
  ): Unit = js.native
  
  def statSync(path: PathLike): js.UndefOr[Stats] = js.native
  def statSync(path: PathLike, options: Unit): Stats = js.native
  def statSync(path: PathLike, options: StatSyncOptionsbigintbool): Stats | BigIntStats = js.native
  def statSync(path: PathLike, options: StatSyncOptionsbigintfals): js.UndefOr[Stats] = js.native
  def statSync(path: PathLike, options: StatSyncOptionsbigintfalsBigint): Stats = js.native
  def statSync(path: PathLike, options: StatSyncOptionsbiginttrue): js.UndefOr[BigIntStats] = js.native
  def statSync(path: PathLike, options: StatSyncOptionsbiginttrueBigint): BigIntStats = js.native
  def statSync(path: PathLike, options: StatSyncOptions): js.UndefOr[Stats | BigIntStats] = js.native
  @JSName("statSync")
  val statSync_Original: StatSyncFn = js.native
  @JSName("statSync")
  def statSync_Stats(path: PathLike): Stats = js.native
  
  val statfs: Typeofstatfs = js.native
  def statfs(
    path: PathLike,
    options: Unit,
    callback: js.Function2[ErrnoException | Null, BigIntStatsFs | (/* stats */ StatsFs), Unit]
  ): Unit = js.native
  def statfs(
    path: PathLike,
    options: StatFsOptionsbigintfalseu,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ StatsFs, Unit]
  ): Unit = js.native
  def statfs(
    path: PathLike,
    options: StatFsOptionsbiginttrue,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ BigIntStatsFs, Unit]
  ): Unit = js.native
  def statfs(
    path: PathLike,
    options: StatFsOptions,
    callback: js.Function2[/* err */ ErrnoException | Null, /* stats */ StatsFs | BigIntStatsFs, Unit]
  ): Unit = js.native
  
  def statfsSync(path: PathLike): StatsFs = js.native
  def statfsSync(path: PathLike, options: StatFsOptionsbigintfalseu): StatsFs = js.native
  def statfsSync(path: PathLike, options: StatFsOptionsbiginttrue): BigIntStatsFs = js.native
  def statfsSync(path: PathLike, options: StatFsOptions): StatsFs | BigIntStatsFs = js.native
  @JSName("statfsSync")
  def statfsSync_Union(path: PathLike): StatsFs | BigIntStatsFs = js.native
  
  val symlink: Typeofsymlink = js.native
  def symlink(target: PathLike, path: PathLike, callback: NoParamCallback): Unit = js.native
  
  def symlinkSync(target: PathLike, path: PathLike): Unit = js.native
  def symlinkSync(target: PathLike, path: PathLike, `type`: typings.node.fsMod.symlink.Type): Unit = js.native
  
  val truncate: Typeoftruncate = js.native
  def truncate(path: PathLike, callback: NoParamCallback): Unit = js.native
  
  def truncateSync(path: PathLike): Unit = js.native
  def truncateSync(path: PathLike, len: Double): Unit = js.native
  
  val unlink: Typeofunlink = js.native
  
  def unlinkSync(path: PathLike): Unit = js.native
  
  def unwatchFile(filename: PathLike): Unit = js.native
  def unwatchFile(filename: PathLike, listener: BigIntStatsListener | StatsListener): Unit = js.native
  
  val utimes: Typeofutimes = js.native
  
  def utimesSync(path: PathLike, atime: TimeLike, mtime: TimeLike): Unit = js.native
  
  def watch(filename: PathLike): FSWatcher = js.native
  def watch(filename: PathLike, listener: WatchListener[String]): FSWatcher = js.native
  def watch(filename: PathLike, options: String): FSWatcher = js.native
  def watch(
    filename: PathLike,
    options: String,
    listener: WatchListener[String | typings.node.bufferMod.global.Buffer]
  ): FSWatcher = js.native
  def watch(filename: PathLike, options: Null, listener: WatchListener[String]): FSWatcher = js.native
  def watch(filename: PathLike, options: Unit, listener: WatchListener[String]): FSWatcher = js.native
  def watch(filename: PathLike, options: WatchOptionsencodingbuffe): FSWatcher = js.native
  def watch(
    filename: PathLike,
    options: WatchOptionsencodingbuffe,
    listener: WatchListener[typings.node.bufferMod.global.Buffer]
  ): FSWatcher = js.native
  def watch(filename: PathLike, options: BufferEncoding): FSWatcher = js.native
  def watch(filename: PathLike, options: BufferEncoding, listener: WatchListener[String]): FSWatcher = js.native
  def watch(filename: PathLike, options: WatchOptions): FSWatcher = js.native
  def watch(
    filename: PathLike,
    options: WatchOptions,
    listener: WatchListener[typings.node.bufferMod.global.Buffer | String]
  ): FSWatcher = js.native
  
  def watchFile(filename: PathLike, listener: StatsListener): StatWatcher = js.native
  def watchFile(filename: PathLike, options: Unit, listener: BigIntStatsListener | StatsListener): StatWatcher = js.native
  def watchFile(filename: PathLike, options: WatchFileOptionsbigintfal, listener: StatsListener): StatWatcher = js.native
  def watchFile(filename: PathLike, options: WatchFileOptionsbiginttru, listener: BigIntStatsListener): StatWatcher = js.native
  
  @JSName("watch")
  def watch_buffer(filename: PathLike, options: buffer_): FSWatcher = js.native
  @JSName("watch")
  def watch_buffer(
    filename: PathLike,
    options: buffer_,
    listener: WatchListener[typings.node.bufferMod.global.Buffer]
  ): FSWatcher = js.native
  
  val write: Typeofwrite = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.DataView,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.DataView, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float32Array,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Float64Array,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Float64Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int16Array,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int32Array,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Int8Array,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Int8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint16Array,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint16Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint32Array,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint32Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8Array,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8Array, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: js.typedarray.Uint8ClampedArray,
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ js.typedarray.Uint8ClampedArray, 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Double,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Double,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Double,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Null,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Null,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Null,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Unit,
    length: Double,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Unit,
    length: Null,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    buffer: RelativeIndexable[js.BigInt],
    offset: Unit,
    length: Unit,
    callback: js.Function3[
      /* err */ ErrnoException | Null, 
      /* written */ Double, 
      /* buffer */ RelativeIndexable[js.BigInt], 
      Unit
    ]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Double,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Double,
    encoding: Null,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Double,
    encoding: Unit,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Double,
    encoding: BufferEncoding,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Null,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Null,
    encoding: Null,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Null,
    encoding: Unit,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Null,
    encoding: BufferEncoding,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Unit,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Unit,
    encoding: Null,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Unit,
    encoding: Unit,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  def write(
    fd: Double,
    string: String,
    position: Unit,
    encoding: BufferEncoding,
    callback: js.Function3[/* err */ ErrnoException | Null, /* written */ Double, /* str */ String, Unit]
  ): Unit = js.native
  
  val writeFile: TypeofwriteFile = js.native
  def writeFile(path: PathOrFileDescriptor, data: String, callback: NoParamCallback): Unit = js.native
  def writeFile(path: PathOrFileDescriptor, data: ArrayBufferView, callback: NoParamCallback): Unit = js.native
  
  def writeFileSync(file: PathOrFileDescriptor, data: String): Unit = js.native
  def writeFileSync(file: PathOrFileDescriptor, data: String, options: WriteFileOptions): Unit = js.native
  def writeFileSync(file: PathOrFileDescriptor, data: ArrayBufferView): Unit = js.native
  def writeFileSync(file: PathOrFileDescriptor, data: ArrayBufferView, options: WriteFileOptions): Unit = js.native
  
  def writeSync(fd: Double, buffer: ArrayBufferView): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Double, length: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Double, length: Double, position: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Double, length: Null, position: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Double, length: Unit, position: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Null, length: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Null, length: Double, position: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Null, length: Null, position: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Null, length: Unit, position: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Unit, length: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Unit, length: Double, position: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Unit, length: Null, position: Double): Double = js.native
  def writeSync(fd: Double, buffer: ArrayBufferView, offset: Unit, length: Unit, position: Double): Double = js.native
  def writeSync(fd: Double, string: String): Double = js.native
  def writeSync(fd: Double, string: String, position: Double): Double = js.native
  def writeSync(fd: Double, string: String, position: Double, encoding: BufferEncoding): Double = js.native
  def writeSync(fd: Double, string: String, position: Null, encoding: BufferEncoding): Double = js.native
  def writeSync(fd: Double, string: String, position: Unit, encoding: BufferEncoding): Double = js.native
  
  val writev: Typeofwritev = js.native
  def writev(
    fd: Double,
    buffers: js.Array[ArrayBufferView],
    position: Double,
    cb: js.Function3[
      /* err */ ErrnoException | Null, 
      /* bytesWritten */ Double, 
      /* buffers */ js.Array[ArrayBufferView], 
      Unit
    ]
  ): Unit = js.native
  
  def writevSync(fd: Double, buffers: js.Array[ArrayBufferView]): Double = js.native
  def writevSync(fd: Double, buffers: js.Array[ArrayBufferView], position: Double): Double = js.native
}
