import org.scalajs.linker.interface.OutputPatterns
import sbt.librarymanagement.InclExclRule
import scala.scalanative.build.*

val scala3Version = "3.5.0"
val scala2Version = "2.13.14"

val graalVm = "graalvm-java22"
val graalVersion = "22.0.2"
val graalvmVersion = "24.0.2"

val nativeImageOption = Seq(
  "-H:-CheckToolchain",
  "--verbose",
  "--no-fallback",
  "-enablesystemassertions",
  // runtime: org.jline
  "--initialize-at-build-time=org.typelevel,os,scalax,sbt,ujson,upack,upickle,algebra,cps,com.oracle,spire,org.graalvm,scopt,fastparse,scala,java,chester,org.eclipse,cats,fansi,sourcecode,com.monovore.decline,geny,pprint",
  "-O2",
)

val classVersion = java.lang.Float.parseFloat(System.getProperty("java.class.version"))
val jdk17ClassVersion = 61.0f
val jdk17: Boolean = false /* because of -java-output-version 8 */
// classVersion >= jdk17ClassVersion

val commonSettings = Seq(
  scalaVersion := scala3Version,
  //githubTokenSource := TokenSource.GitConfig("github.token") || TokenSource.Environment("GITHUB_TOKEN"),
  //resolvers += Resolver.githubPackages("edadma", "readline"),
  resolvers += "jitpack" at "https://jitpack.io",
  resolvers += Resolver.mavenLocal,
  // https://github.com/typelevel/sbt-tpolecat/commit/d4dd41451a9e9346cf8c0253018bc648f6527be3
  scalacOptions ++=
    Seq(
      "-encoding",
      "utf8",
      "-deprecation",
      "-feature",
      "-unchecked",
      "-language:experimental.macros",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-Xkind-projector",
      "-Wvalue-discard",
      "-Wnonunit-statement",
      "-Wunused:implicits",
      "-Wunused:explicits",
      //"-Wunused:imports",
      "-Wunused:locals",
      "-Wunused:params",
      "-Wunused:privates",
    ),
  scalacOptions ++= Seq("-rewrite", "-source", "3.4-migration"),
  libraryDependencies ++= Seq(
    "org.scalameta" %%% "munit" % "1.0.0" % Test,
    "org.scalatest" %%% "scalatest" % "3.2.19" % Test,
    "org.scalatest" %%% "scalatest-funsuite" % "3.2.19" % Test,
    "org.scalatest" %%% "scalatest-shouldmatchers" % "3.2.19" % Test,
    "org.scalatestplus" %%% "scalacheck-1-18" % "3.2.19.0" % Test,
    "org.scalacheck" %%% "scalacheck" % "1.18.0" % Test,
    "com.lihaoyi" %%% "pprint" % "0.9.0" % Test,
  ),
    excludeDependencies ++= Seq(
      "org.scalacheck"% "scalacheck_native0.5_2.13",
    ),
)
val commonVendorSettings = Seq(
  scalaVersion := scala3Version,
  scalacOptions ++= Seq("-java-output-version", "8"),
  scalacOptions += "-nowarn",
)
val scala2VendorSettings = Seq(
  scalaVersion := scala2Version,
  scalacOptions ++= Seq("-java-output-version", "8"),
  scalacOptions += "-nowarn",
)
val cpsSettings = Seq(
  autoCompilerPlugins := true,
  addCompilerPlugin("com.github.rssh" %% "dotty-cps-async-compiler-plugin" % "0.9.22"),
)
val commonJvmLibSettings = Seq(
  //scalacOptions ++= (if (jdk17) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:jdk17") else Seq()),
  scalacOptions ++= Seq("-java-output-version", "8"),
)
val graalvmSettings = Seq(
  nativeImageVersion := graalVersion,
  nativeImageOptions := nativeImageOption,
  nativeImageJvm := graalVm,
)

commonSettings

ThisBuild / version := sys.env.getOrElse("VERSION", "0.0.1-RC0")
ThisBuild / organization := "com.github.chester-lang"

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", "versions", xs@_*) => MergeStrategy.first
  case PathList("module-info.class") => MergeStrategy.first
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

ThisBuild / nativeConfig ~= (System.getProperty("os.name").toLowerCase match {
  case mac if mac.contains("mac") => { // mac has some bugs with optimizations
    _.withGC(GC.commix)
  }
  case _ => {
    _.withLTO(LTO.thin)
      .withMode(Mode.releaseFast)
      .withGC(GC.commix)
  }
})

// original kiama-core
lazy val kiamaCore = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("vendor/kiama-core"))
  .settings(
    commonVendorSettings
  )
  .jvmSettings(commonJvmLibSettings)

// kiama fork from effekt - https://github.com/effekt-lang/kiama
lazy val effektKiama = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("vendor/effekt-kiama"))
  .settings(
    commonVendorSettings
  )
  .jvmSettings(
    commonJvmLibSettings,
    libraryDependencies ++= Seq(
      "jline" % "jline" % "2.14.6",
      "org.rogach" %% "scallop" % "5.1.0",
      "org.eclipse.lsp4j" % "org.eclipse.lsp4j" % "0.23.1",
      "com.google.code.gson" % "gson" % "2.11.0",
    )
  )
  .nativeSettings(
    // https://github.com/scala-native/scala-native/issues/4044#issuecomment-2329088930
    scalacOptions ++= Seq(
      "-Ylegacy-lazy-vals",
    ),
  )

// iron & iron-cats & iron-upickle, commit 86fbe48e8c9b0f6e5d2f7261ddefaa7c671341ae, built against Scala Native 0.5
// removed RefinedTypeOpsSuite.scala because of compilation error
lazy val ironNative = crossProject(NativePlatform).withoutSuffixFor(NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("vendor/iron-native"))
  .settings(
    commonVendorSettings
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "upickle" % "4.0.1",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "com.lihaoyi" %%% "utest" % "0.8.4" % Test,
      "org.typelevel" %%% "kittens" % "3.4.0" % Test,
    ),
  )

// commit 52b3692bdfe01ef6c645380b02595a9c60a9725b, core & util & platform & macros, main only, no tests
// rewrite by scalac with 3.4-migration
// needed project/GenProductTypes.scala
lazy val genProductTypes = TaskKey[Seq[File]]("gen-product-types", "Generates several type classes for Tuple2-22.")
lazy val spireNative = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(NativePlatform)
  .crossType(CrossType.Full)
  .in(file("vendor/spire-native"))
  .settings(
    scalacOptions ++= Seq("-rewrite", "-source", "3.4-migration"),
    commonVendorSettings,
    Compile / sourceGenerators += (Compile / genProductTypes).taskValue,
    genProductTypes := {
      val scalaSource = (Compile / sourceManaged).value
      val s = streams.value
      s.log.info("Generating spire/std/tuples.scala")
      val algebraSource = ProductTypes.algebraProductTypes
      val algebraFile = (scalaSource / "spire" / "std" / "tuples.scala").asFile
      IO.write(algebraFile, algebraSource)

      Seq[File](algebraFile)
    },
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "algebra-laws" % "2.12.0",
    )
  )
  .nativeSettings(
  )
  .jvmSettings(commonJvmLibSettings)

// commit 9ee80c0d887acdcf700252a2ff66d08ced2472c5, core
// add polyfill for java8 StringOpsForJava8 in ToString.Scala
// EqHashMap & EqHashSet this() patched with () to compile with scala3
// `protected[this] type Coll = CC[_, Nothing]` commented out
// causing Scala3 compiler crashing
// deleted MappingTypedSpec.scala from test because of compilation error
// deleted scalax.collection.EditingSpec & scalax.time.MicroBenchmarkTest beacuse of failing
// test moved to _test because of for2_13Use3 breaking the tests
lazy val scalaGraph = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("vendor/scala-graph"))
  .settings(
    scala2VendorSettings,
    scalacOptions ++= Seq(
      "-Ytasty-reader",
    ),
    /*
  libraryDependencies ++= Seq(
    "org.scalatest" %%% "scalatest" % "3.2.19" % Test cross (CrossVersion.for2_13Use3),
    "org.scalatest" %%% "scalatest-funsuite" % "3.2.19" % Test cross (CrossVersion.for2_13Use3),
    "org.scalatest" %%% "scalatest-shouldmatchers" % "3.2.19" % Test cross (CrossVersion.for2_13Use3),
    "org.scalatestplus" %%% "scalacheck-1-18" % "3.2.19.0" % Test cross (CrossVersion.for2_13Use3),
    "org.scalacheck" %%% "scalacheck" % "1.18.0" % Test cross (CrossVersion.for2_13Use3),
  ),
  */
  )
  .jvmSettings(commonJvmLibSettings)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % "1.18.0" cross (CrossVersion.for2_13Use3),
    ),
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "org.scalacheck"% "scalacheck_native0.5_2.13"%"1.18.0" % Compile,
    ),
  )
// https://stackoverflow.com/questions/52224680/buildt-sbt-exclude-dependencies-from-dependson-submodule/52229391#52229391
def excl(m: ModuleID): InclExclRule = InclExclRule(m.organization, m.name)
// split modules trying to increase incremental compilation speed
lazy val utils = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("utils"))
  .settings(
    name := "utils",
    commonSettings,
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "org.typelevel" %%% "cats-free" % "2.12.0",
      "com.lihaoyi" %%% "upickle" % "4.0.1",
      "com.lihaoyi" %%% "fansi" % "0.5.0",
      "com.lihaoyi" %%% "fastparse" % "3.1.0",
      "com.lihaoyi" %%% "scalatags" % "0.13.1",
      "com.github.rssh" %%% "dotty-cps-async" % "0.9.22",
      "io.getkyo" %%% "kyo-prelude" % "0.12.0",
      "io.getkyo" %%% "kyo-data" % "0.12.0",
      "io.getkyo" %%% "kyo-tag" % "0.12.0",
    ),
  )
  .jvmConfigure(_.dependsOn(scalaGraph.jvm))
  .jvmSettings(
    commonJvmLibSettings,
    libraryDependencies ++= Seq(
      //"org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.0", // for scala-graph
      "org.typelevel" %%% "spire" % "0.18.0",
      "io.github.iltotore" %%% "iron" % "2.6.0",
      "io.github.iltotore" %%% "iron-cats" % "2.6.0",
      "io.github.iltotore" %%% "iron-upickle" % "2.6.0" exclude("com.lihaoyi", "upickle_3"),
    ),
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-stubs" % "1.1.0",
    ),
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      //"org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.0", // for scala-graph
      //"com.github.mio-19.spire" /*"org.typelevel"*/ %%% "spire" % "fcf7d67b61",
    ),
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-stubs" % "1.1.0",
    ),
  )
  .nativeConfigure(_.dependsOn(ironNative.native, spireNative.native, scalaGraph.native))
  .jsSettings(
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withOutputPatterns(OutputPatterns.fromJSFile("%s.mjs"))
    },
    libraryDependencies ++= Seq(
      "org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.0", // for scala-graph
      "org.typelevel" %%% "spire" % "0.18.0",
      "io.github.iltotore" %%% "iron" % "2.6.0",
      "io.github.iltotore" %%% "iron-cats" % "2.6.0",
      "io.github.iltotore" %%% "iron-upickle" % "2.6.0" exclude("com.lihaoyi", "upickle_3"),
    ),
  )

lazy val base = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("base"))
  .dependsOn(utils)
  .settings(
    name := "base",
    commonSettings,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val pretty = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("pretty"))
  .dependsOn(utils)
  .dependsOn(kiamaCore)
  .settings(
    name := "pretty",
    commonSettings,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val parser = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("parser"))
  .dependsOn(utils, ast)
  .settings(
    name := "parser",
    commonSettings,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val ast = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("ast"))
  .dependsOn(base, pretty)
  .settings(
    name := "ast",
    commonSettings,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val tyck = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("tyck"))
  .dependsOn(base, ast)
  .settings(
    name := "tyck",
    commonSettings,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val core = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .dependsOn(base, parser, ast, pretty, tyck)
  .settings(
    name := "core",
    assembly / assemblyJarName := "core.jar",
    commonSettings,
    //cpsSettings,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val typednode = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typednode"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/node_sjs1_3/22.5.5-6bc698/srcs/node_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/node_sjs1_3/22.5.5-6bc698/jars/node_sjs1_3.jar"),
  )
lazy val typedstd = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedstd"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/std_sjs1_3/4.3-5d95db/srcs/std_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/std_sjs1_3/4.3-5d95db/jars/std_sjs1_3.jar"),
  )
lazy val typedundici = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedundici"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/undici-types_sjs1_3/6.19.8-4dee3c/srcs/undici-types_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/undici-types_sjs1_3/6.19.8-4dee3c/jars/undici-types_sjs1_3.jar"),
  )
lazy val typedxterm = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedxterm"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/xterm__xterm_sjs1_3/5.5.0-951203/srcs/xterm__xterm_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/xterm__xterm_sjs1_3/5.5.0-951203/jars/xterm__xterm_sjs1_3.jar"),
  )
lazy val typedcsstype = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedcsstype"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/csstype_sjs1_3/3.1.3-3d3924/srcs/csstype_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/csstype_sjs1_3/3.1.3-3d3924/jars/csstype_sjs1_3.jar"),
  )
lazy val typednext = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .in(file("js-typings/typednext"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/next_sjs1_3/11.1.4-68204a/srcs/next_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/next_sjs1_3/11.1.4-68204a/jars/next_sjs1_3.jar"),
  )
lazy val typedproptypes = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedproptypes"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/prop-types_sjs1_3/15.7.13-49b294/srcs/prop-types_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/prop-types_sjs1_3/15.7.13-49b294/jars/prop-types_sjs1_3.jar"),
  )
lazy val typedreactdom = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedreactdom"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/react-dom_sjs1_3/18.3.0-d84423/srcs/react-dom_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/react-dom_sjs1_3/18.3.0-d84423/jars/react-dom_sjs1_3.jar"),
  )
lazy val typedreact = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedreact"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/react_sjs1_3/18.3.7-ca07dd/srcs/react_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/react_sjs1_3/18.3.7-ca07dd/jars/react_sjs1_3.jar"),
  )
lazy val typedxtermreadline = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedxtermreadline"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/xterm-readline_sjs1_3/1.1.1-a2b93f/srcs/xterm-readline_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/xterm-readline_sjs1_3/1.1.1-a2b93f/jars/xterm-readline_sjs1_3.jar"),
  )
lazy val typedxterm2 = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedxterm2"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/xterm_sjs1_3/5.3.0-80131f/srcs/xterm_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/xterm_sjs1_3/5.3.0-80131f/jars/xterm_sjs1_3.jar"),
  )
lazy val typedxtermpty = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedxtermpty"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/xterm-pty_sjs1_3/0.9.6-3b34b4/srcs/xterm-pty_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/xterm-pty_sjs1_3/0.9.6-3b34b4/jars/xterm-pty_sjs1_3.jar"),
  )
lazy val jsTypings = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("js-typings"))
  // Remove comments to generate typings again
  //.jsEnablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    commonVendorSettings,
  )
  .jsConfigure(_.dependsOn(
    typednode.js,
    typedstd.js,
    typedundici.js,
    typedxterm.js,
    typedcsstype.js,
    typednext.js,
    typedproptypes.js,
    typedreactdom.js,
    typedreact.js,
    typedxtermreadline.js,
    typedxterm2.js,
    typedxtermpty.js))
  .jsSettings(
    resolvers += Resolver.file("local-ivy2", file("js-typings/local"))(Resolver.ivyStylePatterns),
    libraryDependencies ++= Seq(
      "com.olvind" %%% "scalablytyped-runtime" % "2.4.2",
      "org.scala-js" %%% "scalajs-dom" % "2.3.0",
    ),
    libraryDependencies ++= Seq(
      "org.scalablytyped" %%% "node" % "22.5.5-6bc698" % Compile,
      "org.scalablytyped" %%% "std" % "4.3-5d95db" % Compile,
      "org.scalablytyped" %%% "undici-types" % "6.19.8-4dee3c" % Compile,
      "org.scalablytyped" %%% "xterm__xterm" % "5.5.0-951203" % Compile,
      "org.scalablytyped" %%% "csstype" % "3.1.3-3d3924" % Compile,
      "org.scalablytyped" %%% "next" % "11.1.4-68204a" % Compile,
      "org.scalablytyped" %%% "prop-types" % "15.7.13-49b294" % Compile,
      "org.scalablytyped" %%% "react-dom" % "18.3.0-d84423" % Compile,
      "org.scalablytyped" %%% "react" % "18.3.7-ca07dd" % Compile,
      "org.scalablytyped" %%% "xterm-readline" % "1.1.1-a2b93f" % Compile,
      "org.scalablytyped" %%% "xterm" % "5.3.0-80131f" % Compile,
      "org.scalablytyped" %%% "xterm-pty" % "0.9.6-3b34b4" % Compile,
    ),
    /*
    Compile / npmDependencies ++= Seq(
      "@types/node" -> "22.5.5",
      "@xterm/xterm" -> "5.5.0",
      "@types/react" -> "18.3.7",
      "@types/react-dom" -> "18.3.0",
      "next" -> "11.1.4", // next.js 14/12 breaks scalablytyped
      "xterm-readline" -> "1.1.1",
      "xterm-pty" -> "0.9.6", // use old version because of https://github.com/mame/xterm-pty/issues/35
    ),
    */
  )
  .jvmSettings(commonJvmLibSettings)

lazy val common = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("common"))
  .dependsOn(core)
  .dependsOn(jsTypings)
  .settings(
    name := "chester",
    assembly / assemblyJarName := "common.jar",
    commonSettings,
  )
  .jvmSettings(
    commonJvmLibSettings,
    libraryDependencies += "org.graalvm.sdk" % "nativeimage" % graalvmVersion,
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "os-lib" % "0.10.4",
    ),
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "os-lib" % "0.10.4",
    ),
  )
  .jsSettings(
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
  )

addCommandAlias("cliReadline", "set ThisBuild / enableCliReadline := true;")
addCommandAlias("cliSimple", "set ThisBuild / enableCliReadline := false;")

val enableCliReadline = settingKey[Boolean]("Flag to enable or disable cliReadline")
ThisBuild / enableCliReadline := false
val windows: Boolean = System.getProperty("os.name").toLowerCase.contains("win")
val unix: Boolean = !windows

lazy val cli = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("cli"))
  .jvmEnablePlugins(NativeImagePlugin)
  .dependsOn(common, jsTypings)
  .settings(
    name := "cli",
    Compile / mainClass := Some("chester.cli.Main"),
    assembly / assemblyJarName := "chester.jar",
    libraryDependencies ++= Seq(
      "com.github.scopt" %%% "scopt" % "4.1.0"
    ),
    commonSettings
  )
  .jvmSettings(
    commonJvmLibSettings,
    nativeImageOutput := file("target") / "chester",
    graalvmSettings,
    libraryDependencies ++= Seq(
      "org.jline" % "jline" % "3.26.3",
    )
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      //"io.github.edadma" %%% "readline" % "0.1.3"
    ),
    scalacOptions ++= (if ((ThisBuild / enableCliReadline).value) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:readline") else Seq())
  )

lazy val up = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("up"))
  .jvmEnablePlugins(NativeImagePlugin)
  .dependsOn(common, jsTypings)
  .settings(
    name := "up",
    Compile / mainClass := Some("chester.up.Main"),
    assembly / assemblyJarName := "chesterup.jar",
    libraryDependencies ++= Seq(
      "com.github.scopt" %%% "scopt" % "4.1.0"
    ),
    commonSettings
  )
  .jvmSettings(
    commonJvmLibSettings,
    nativeImageOutput := file("target") / "chesterup",
    graalvmSettings,
  ).jsSettings(
    scalaJSUseMainModuleInitializer := true
  )

lazy val site = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("site"))
  .dependsOn(common, jsTypings)
  .settings(
    name := "site",
    commonSettings
  )
  .jsSettings(
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
    /*
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withOutputPatterns(OutputPatterns.fromJSFile("%s.mjs"))
    },
    */
    libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "2.1.1",
    libraryDependencies += "me.shadaj" %%% "slinky-core" % "0.7.4",
  )

lazy val lsp = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("lsp"))
  .jvmEnablePlugins(NativeImagePlugin)
  .dependsOn(common)
  .settings(
    name := "lsp",
    Compile / mainClass := Some("chester.lsp.Main"),
    libraryDependencies += "org.eclipse.lsp4j" % "org.eclipse.lsp4j" % "0.23.1",
    assembly / assemblyJarName := "chester-lsp.jar",
    nativeImageOutput := file("target") / "chester-lsp",
    commonSettings
  )
  .jvmSettings(
    graalvmSettings,
  )

lazy val truffle = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("truffle"))
  .dependsOn(common)
  .settings(commonSettings)
  // https://github.com/b-studios/scala-graal-truffle-example/blob/c2747a6eece156f878c5b934116aaa00a2cd6311/build.sbt
  .settings(
    name := "truffle",
    assembly / assemblyJarName := "chester-truffle.jar",
    assembly / test := {},
    assembly / assemblyExcludedJars := {
      val cp = (assembly / fullClasspath).value
      // https://stackoverflow.com/questions/41894055/how-to-exclude-jar-in-final-sbt-assembly-plugin
      cp filter { f =>
        val path = f.data.toString
        (path contains "com.oracle.truffle") ||
          (path contains "org.graalvm")
      }
    },
    // we fork the JVM to pass the Java Options
    Compile / run / fork := true,
    javaOptions ++= Seq(
      "-Dgraal.Dump=Truffle:1",
      "-Dgraal.TruffleBackgroundCompilation=false",
      "-Dgraal.TraceTruffleCompilation=true",
      "-Dgraal.TraceTruffleCompilationDetails=true",
      "-XX:-UseJVMCIClassLoader"
    ),

    libraryDependencies ++= Seq(
      "org.graalvm.truffle" % "truffle-api" % graalvmVersion,
      "org.graalvm.truffle" % "truffle-dsl-processor" % graalvmVersion,
      "org.graalvm.truffle" % "truffle-tck" % graalvmVersion,
      "org.graalvm.sdk" % "graal-sdk" % graalvmVersion
    )
  )

lazy val root = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .in(file("."))
  .aggregate(
    ironNative, spireNative, scalaGraph,
    typednode,
    typedstd,
    typedundici,
    typedxterm,
    typedcsstype,
    typednext,
    typedproptypes,
    typedreactdom,
    typedreact,
    typedxtermreadline,
    typedxterm2,
    typedxtermpty,
    kiamaCore,
    effektKiama,
    jsTypings,
    utils,
    base, parser, ast, pretty, tyck,
    core,
    common,
    cli,
    lsp, up, truffle, site)
  .settings(
    name := "ChesterRoot",
    scalaVersion := scala3Version
  )

Global / excludeLintKeys ++= Set[SettingKey[_]](
  cli.jvm / nativeImageJvm,
  cli.jvm / nativeImageVersion,
  cli.js / nativeImageJvm,
  cli.js / nativeImageVersion,
  cli.native / nativeImageJvm,
  cli.native / nativeImageVersion,
  lsp.jvm / nativeImageJvm,
  lsp.jvm / nativeImageVersion
)
