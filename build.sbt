import org.scalajs.linker.interface.OutputPatterns
import sbt.librarymanagement.InclExclRule

import scala.scalanative.build.*

val scala3Version = "3.5.2-RC1"
val scala2Version = "2.13.15"

val graalVm = "graalvm-java23"
val graalJdkVersion = "23.0.0"
val graalvmVersion = "24.1.0"

val nativeImageOption = Seq(
  "-H:-CheckToolchain",
  "--verbose",
  "--no-fallback",
  "-enablesystemassertions",
  // runtime: org.jline
  "--initialize-at-build-time=org.slf4j,org.typelevel,os,scalax,sbt,ujson,upack,upickle,algebra,cps,com.oracle,spire,org.graalvm,scopt,fastparse,scala,java,chester,org.eclipse,cats,fansi,sourcecode,com.monovore.decline,geny,pprint",
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
      "-experimental",
    ),
  scalacOptions ++= Seq("-rewrite", "-source", "3.4-migration"),
  libraryDependencies ++= Seq(
    "org.scalameta" %%% "munit" % "1.0.2" % Test,
    "org.scalatest" %%% "scalatest" % "3.2.19" % Test,
    "org.scalatest" %%% "scalatest-funsuite" % "3.2.19" % Test,
    "org.scalatest" %%% "scalatest-shouldmatchers" % "3.2.19" % Test,
    "org.scalatestplus" %%% "scalacheck-1-18" % "3.2.19.0" % Test,
    "org.scalacheck" %%% "scalacheck" % "1.18.1" % Test,
    "com.lihaoyi" %%% "pprint" % "0.9.0" % Test,
  ),
  // https://stackoverflow.com/questions/73822653/scala-2-artifact-in-scala-3-project-conflicting-cross-version-suffixes/73824811#73824811
  excludeDependencies ++= Seq(
    "org.scalacheck" % "scalacheck_native0.5_2.13",
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
  nativeImageVersion := graalJdkVersion,
  nativeImageOptions := nativeImageOption,
  nativeImageJvm := graalVm,
)

val baseDeps = Seq(
  libraryDependencies ++= Seq(
    "org.typelevel" %%% "cats-core" % "2.12.0",
    "org.typelevel" %%% "cats-free" % "2.12.0",
    "com.lihaoyi" %%% "upickle" % "4.0.2",
    "com.lihaoyi" %%% "fansi" % "0.5.0",
    "com.lihaoyi" %%% "fastparse" % "3.1.1",
    //"com.lihaoyi" %%% "scalatags" % "0.13.1",
    //"com.github.rssh" %%% "dotty-cps-async" % "0.9.22",
    //"io.getkyo" %%% "kyo-prelude" % "0.12.2",
    //"io.getkyo" %%% "kyo-data" % "0.12.2",
    //"io.getkyo" %%% "kyo-tag" % "0.12.2",
  ),
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

val supportNativeBuildForTermux = true

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

ThisBuild / nativeConfig ~= (if (supportNativeBuildForTermux) {
  _.withMultithreading(false).withGC(GC.immix)
} else (x => x))

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
      "com.lihaoyi" %%% "upickle" % "4.0.2",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "com.lihaoyi" %%% "utest" % "0.8.4" % Test,
      "org.typelevel" %%% "kittens" % "3.4.0" % Test,
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
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
// find . -name '*.scala-' -delete
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
    "org.scalacheck" %%% "scalacheck" % "1.18.1" % Test cross (CrossVersion.for2_13Use3),
  ),
  */
  )
  .jvmSettings(commonJvmLibSettings)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % "1.18.1" cross (CrossVersion.for2_13Use3),
    ),
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "org.scalacheck" % "scalacheck_native0.5_2.13" % "1.18.1" % Compile,
    ),
  ).jsSettings(
    libraryDependencies ++= Seq(
      "org.scalacheck" %%% "scalacheck" % "1.18.1" cross (CrossVersion.for2_13Use3),
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
    baseDeps,
  )
  .jvmConfigure(_.dependsOn(scalaGraph.jvm))
  .jvmSettings(
    commonJvmLibSettings,
    libraryDependencies ++= Seq(
      //"org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.1", // for scala-graph
      "io.github.iltotore" %%% "iron" % "2.6.0",
      "io.github.iltotore" %%% "iron-cats" % "2.6.0",
      "io.github.iltotore" %%% "iron-upickle" % "2.6.0" exclude("com.lihaoyi", "upickle_3"),
    ),
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-stubs" % "1.1.0",
    ),
    libraryDependencies ++= Seq(
      //"it.unimi.dsi" % "fastutil" % "8.5.14",
    )
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      //"org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.1", // for scala-graph
    ),
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-stubs" % "1.1.0",
    ),
  )
  .nativeConfigure(_.dependsOn(ironNative.native, scalaGraph.native))
  .jsSettings(
    libraryDependencies ++= Seq(
      "org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.1", // for scala-graph
      "io.github.iltotore" %%% "iron" % "2.6.0",
      "io.github.iltotore" %%% "iron-cats" % "2.6.0",
      "io.github.iltotore" %%% "iron-upickle" % "2.6.0" exclude("com.lihaoyi", "upickle_3"),
    ),
  )


def useSpire(project: _root_.sbtcrossproject.CrossProject): _root_.sbtcrossproject.CrossProject =
  project.jvmSettings(
      libraryDependencies ++= Seq(
        "org.typelevel" %%% "spire" % "0.18.0",
      )
    )
    .nativeSettings(
      libraryDependencies ++= Seq(
        //"com.github.mio-19.spire" /*"org.typelevel"*/ %%% "spire" % "fcf7d67b61",
      )
    )
    .nativeConfigure(_.dependsOn(spireNative.native))
    .jsSettings(
      libraryDependencies ++= Seq(
        "org.typelevel" %%% "spire" % "0.18.0",
      )
    )

lazy val utils2 = useSpire(crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("utils2"))
  .settings(
    name := "utils2",
    commonSettings,
    baseDeps,
  )
  .jvmSettings(commonJvmLibSettings))

lazy val base = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("base"))
  .settings(
    name := "base",
    commonSettings,
    baseDeps,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val pretty = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("pretty"))
  .dependsOn(utils, utils2)
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

lazy val ast = useSpire(crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("ast"))
  .dependsOn(base, pretty)
  .settings(
    name := "ast",
    commonSettings,
  )
  .jvmSettings(commonJvmLibSettings))

lazy val err = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("err"))
  .dependsOn(ast)
  .settings(
    name := "err",
    commonSettings,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val tyck = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("tyck"))
  .dependsOn(base, ast, err)
  .settings(
    name := "tyck",
    commonSettings,
  )
  .jvmSettings(commonJvmLibSettings)

lazy val core = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("core"))
  .dependsOn(base, parser, ast, pretty, tyck, utils2)
  .settings(
    name := "core",
    assembly / assemblyOutputPath := file("target") / "chester-core.jar",
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
lazy val typedvscodeJsonrpc = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedvscodeJsonrpc"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/vscode-jsonrpc_sjs1_3/8.2.0-224d90/srcs/vscode-jsonrpc_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/vscode-jsonrpc_sjs1_3/8.2.0-224d90/jars/vscode-jsonrpc_sjs1_3.jar"),
  )

lazy val typedvscodeLanguageserverProtocol = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedvscodeLanguageserverProtocol"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/vscode-languageserver-protocol_sjs1_3/3.17.5-a68af5/srcs/vscode-languageserver-protocol_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/vscode-languageserver-protocol_sjs1_3/3.17.5-a68af5/jars/vscode-languageserver-protocol_sjs1_3.jar"),
  )

lazy val typedvscodeLanguageserverTextdocument = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedvscodeLanguageserverTextdocument"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/vscode-languageserver-textdocument_sjs1_3/1.0.12-0aa4d4/srcs/vscode-languageserver-textdocument_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/vscode-languageserver-textdocument_sjs1_3/1.0.12-0aa4d4/jars/vscode-languageserver-textdocument_sjs1_3.jar"),
  )

lazy val typedvscodeLanguageserverTypes = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedvscodeLanguageserverTypes"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/vscode-languageserver-types_sjs1_3/3.17.5-6935e7/srcs/vscode-languageserver-types_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/vscode-languageserver-types_sjs1_3/3.17.5-6935e7/jars/vscode-languageserver-types_sjs1_3.jar"),
  )

lazy val typedvscodeLanguageserver = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .in(file("js-typings/typedvscodeLanguageserver"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/vscode-languageserver_sjs1_3/9.0.1-28aecf/srcs/vscode-languageserver_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/vscode-languageserver_sjs1_3/9.0.1-28aecf/jars/vscode-languageserver_sjs1_3.jar"),
  )

// Update the jsTypings project to include the new dependencies
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
    typedxtermpty.js,
    typedvscodeJsonrpc.js,
    typedvscodeLanguageserverProtocol.js,
    typedvscodeLanguageserverTextdocument.js,
    typedvscodeLanguageserverTypes.js,
    typedvscodeLanguageserver.js))
  .jsSettings(
    resolvers += Resolver.file("local-ivy2", file("js-typings/local"))(Resolver.ivyStylePatterns),
    libraryDependencies ++= Seq(
      "com.olvind" %%% "scalablytyped-runtime" % "2.4.2",
      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
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
      "org.scalablytyped" %%% "vscode-jsonrpc" % "8.2.0-224d90" % Compile,
      "org.scalablytyped" %%% "vscode-languageserver-protocol" % "3.17.5-a68af5" % Compile,
      "org.scalablytyped" %%% "vscode-languageserver-textdocument" % "1.0.12-0aa4d4" % Compile,
      "org.scalablytyped" %%% "vscode-languageserver-types" % "3.17.5-6935e7" % Compile,
      "org.scalablytyped" %%% "vscode-languageserver" % "9.0.1-28aecf" % Compile,
    ),
    /*
    Compile / npmDependencies ++= Seq(
      "vscode-languageserver" -> "9.0.1",
      "vscode-languageserver-textdocument" -> "1.0.12",
      "vscode-languageserver-protocol" -> "3.17.5",
    ),
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
    assembly / assemblyOutputPath := file("target") / "chester-common.jar",
    commonSettings,
  )
  .jvmSettings(
    commonJvmLibSettings,
    libraryDependencies += "org.graalvm.sdk" % "nativeimage" % graalvmVersion,
    libraryDependencies ++= Seq(
      //"com.lihaoyi" %% "os-lib" % "0.10.7",
    ),
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      //"com.lihaoyi" %% "os-lib" % "0.10.7",
    ),
    scalacOptions ++= (if (supportNativeBuildForTermux) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:scalaNativeForTermux") else Seq()),
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
    assembly / assemblyOutputPath := file("target") / "chester.jar",
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
      "org.jline" % "jline" % "3.27.0",
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
    assembly / assemblyOutputPath := file("target") / "chesterup.jar",
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

lazy val js = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("js"))
  .dependsOn(common, jsTypings)
  .settings(
    name := "js",
    commonSettings
  )
  .jsSettings(
  )

lazy val site = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("site"))
  .dependsOn(js)
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
    libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "2.1.2",
    libraryDependencies += "me.shadaj" %%% "slinky-core" % "0.7.4",
  )

lazy val docs = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .crossType(CrossType.Full)
  .in(file("docs"))
  .dependsOn(js)
  .settings(
    name := "docs",
    commonSettings
  ).jsSettings(

  )

lazy val lsp = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("lsp"))
  .jvmEnablePlugins(NativeImagePlugin)
  //.enablePlugins(SbtProguard)
  .dependsOn(common)
  .settings(
    libraryDependencies ++= Seq(
      "org.log4s" %%% "log4s" % "1.10.0",
      "org.slf4j" % "slf4j-api" % "2.0.16",
      "org.slf4j" % "slf4j-simple" % "2.0.16",
    ),
    name := "lsp",
    Compile / mainClass := Some("chester.lsp.Main"),
    libraryDependencies += "org.eclipse.lsp4j" % "org.eclipse.lsp4j" % "0.23.1",
    assembly / assemblyOutputPath := file("target") / "chester-lsp.jar",
    nativeImageOutput := file("target") / "chester-lsp",
    commonSettings,
    // proguard is breaking the build
    /*
    // https://stackoverflow.com/questions/39655207/how-to-obfuscate-fat-scala-jar-with-proguard-and-sbt/39663793#39663793
    // Proguard settings
    Proguard / proguardOptions ++= Seq(
      "-dontoptimize",
      "-keepattributes *Annotation*",
      "-keep public class * { public static void main(java.lang.String[]); }",
      "-keep public class chester.**,org.eclipse.lsp4j.** { *; }",
      "-dontnote", "-dontwarn", //"-ignorewarnings"
    ),
    Proguard / proguardVersion := "7.5.0",
    Proguard / proguard / javaOptions := Seq("-Xmx4G"),
    Proguard / proguardInputs := Seq((assembly / assemblyOutputPath).value),
    Proguard / proguardLibraries := (Proguard / proguard / javaHome).value.toSeq,
    Proguard / proguardInputFilter := (_ => None),
    Proguard / proguardMerge := false,
    Proguard / proguard := (Proguard / proguard).dependsOn(assembly).value,
    Proguard / artifactPath := file("target") / "chester-lsp.jar",
    */
  )
  .jvmSettings(
    graalvmSettings,
  )

lazy val buildTool = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("build-tool"))
  .jvmEnablePlugins(NativeImagePlugin)
  .dependsOn(common)
  .settings(
    name := "build-tool",
    Compile / mainClass := Some("chester.build.Main"),
    assembly / assemblyOutputPath := file("target") / "chester-build.jar",
    nativeImageOutput := file("target") / "chester-build",
    commonSettings,
    libraryDependencies ++= Seq(
      "ch.epfl.scala" %%% "bsp4s" % "2.2.0-M4.TEST" cross (CrossVersion.for3Use2_13) exclude("com.lihaoyi", "sourcecode_2.13") exclude("org.typelevel", "cats-core_2.13") exclude("org.typelevel", "cats-kernel_2.13"),
      "com.lihaoyi" %%% "sourcecode" % "0.4.3-M1",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "org.typelevel" %%% "cats-kernel" % "2.12.0",
    )
  )

lazy val truffle = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("truffle"))
  .dependsOn(common)
  .settings(commonSettings)
  // https://github.com/b-studios/scala-graal-truffle-example/blob/c2747a6eece156f878c5b934116aaa00a2cd6311/build.sbt
  .settings(
    name := "truffle",
    assembly / assemblyOutputPath := file("target") / "chester-truffle.jar",
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
    typedvscodeJsonrpc,
    typedvscodeLanguageserverProtocol,
    typedvscodeLanguageserverTextdocument,
    typedvscodeLanguageserverTypes,
    typedvscodeLanguageserver,
    kiamaCore,
    effektKiama,
    jsTypings,
    utils, utils2,
    base, parser, ast, err, pretty, tyck,
    core,
    common,
    cli,
    lsp, buildTool, up, truffle, js, site, docs)
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
