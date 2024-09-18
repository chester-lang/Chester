import org.scalajs.linker.interface.OutputPatterns

import scala.scalanative.build.*

val scala3Version = "3.5.0"

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
  scalacOptions ++= Seq("-java-output-version", "8"),
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
)
val commonVendorSettings = Seq(
  scalaVersion := scala3Version,
  scalacOptions ++= Seq("-java-output-version", "8"),
  scalacOptions += "-nowarn",
)
val cpsSettings = Seq(
  autoCompilerPlugins := true,
  addCompilerPlugin("com.github.rssh" %% "dotty-cps-async-compiler-plugin" % "0.9.22"),
)
val commonJvmSettings = Seq(
  scalacOptions ++= (if (jdk17) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:jdk17") else Seq()),
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
  .in(file("kiama-core"))
  .settings(
    commonVendorSettings
  )

// kiama fork from effekt - https://github.com/effekt-lang/kiama
lazy val effektKiama = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("effekt-kiama"))
  .settings(
    commonVendorSettings
  )
  .jvmSettings(
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


lazy val core = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("core"))
  .dependsOn(kiamaCore)
  .settings(
    name := "core",
    assembly / assemblyJarName := "core.jar",
    commonSettings,
    //cpsSettings,
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "org.typelevel" %%% "cats-free" % "2.12.0",
      "com.lihaoyi" %%% "upickle" % "4.0.1",
      "com.lihaoyi" %%% "fansi" % "0.5.0",
      "com.lihaoyi" %%% "fastparse" % "3.1.0",
      "com.lihaoyi" %%% "scalatags" % "0.13.1",
      "com.github.rssh" %%% "dotty-cps-async" % "0.9.22",
    ),
  )
  .jvmSettings(
    commonJvmSettings,
    libraryDependencies ++= Seq(
      "org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.0", // for scala-graph
      "org.typelevel" %%% "spire" % "0.18.0",
    ),
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-stubs" % "1.1.0",
    ),
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.0", // for scala-graph
      "com.github.mio-19.spire" /*"org.typelevel"*/ %%% "spire" % "fcf7d67b61",
    ),
    libraryDependencies ++= Seq(
      "org.scala-js" %% "scalajs-stubs" % "1.1.0",
    ),
  )
  .jsSettings(
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withOutputPatterns(OutputPatterns.fromJSFile("%s.mjs"))
    },
    libraryDependencies ++= Seq(
      "org.scala-graph" %%% "graph-core" % "2.0.1" exclude("org.scalacheck", "scalacheck_2.13") cross (CrossVersion.for3Use2_13),
      "org.scalacheck" %%% "scalacheck" % "1.18.0", // for scala-graph
      "org.typelevel" %%% "spire" % "0.18.0",
    ),
  )

lazy val typednode = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .in(file("js-typings/typednode"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/node_sjs1_3/22.3.0-fa071c/srcs/node_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/node_sjs1_3/22.3.0-fa071c/jars/node_sjs1_3.jar"),
  )
lazy val typedstd = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .in(file("js-typings/typedstd"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/std_sjs1_3/4.3-5d95db/srcs/std_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/std_sjs1_3/4.3-5d95db/jars/std_sjs1_3.jar"),
  )
lazy val typedundici = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .in(file("js-typings/typedundici"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/undici-types_sjs1_3/6.18.2-4cf613/srcs/undici-types_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/undici-types_sjs1_3/6.18.2-4cf613/jars/undici-types_sjs1_3.jar"),
  )
lazy val typedxterm = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .in(file("js-typings/typedxterm"))
  .settings(commonVendorSettings)
  .jsSettings(
    Compile / packageSrc := file("js-typings/local/org.scalablytyped/xterm__xterm_sjs1_3/5.5.0-951203/srcs/xterm__xterm_sjs1_3-sources.jar"),
    Compile / packageBin := file("js-typings/local/org.scalablytyped/xterm__xterm_sjs1_3/5.5.0-951203/jars/xterm__xterm_sjs1_3.jar"),
  )
lazy val jsTypings = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("js-typings"))
  // Remove comments to generate typings again
  //.jsEnablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    commonVendorSettings,
  )
  .dependsOn(typednode, typedstd, typedundici, typedxterm)
  .jsSettings(
    resolvers += Resolver.file("local-ivy2", file("js-typings/local"))(Resolver.ivyStylePatterns),
    libraryDependencies ++= Seq(
      "org.scalablytyped" %%% "node" % "22.3.0-fa071c" % Compile,
      "org.scalablytyped" %%% "std" % "4.3-5d95db" % Compile,
      "org.scalablytyped" %%% "undici-types" % "6.18.2-4cf613" % Compile,
      "org.scalablytyped" %%% "xterm__xterm" % "5.5.0-951203" % Compile,
    ),
    libraryDependencies ++= Seq(
      "com.olvind" %%% "scalablytyped-runtime" % "2.4.2",
      "org.scala-js" %%% "scalajs-dom" % "2.3.0",
    ),
    /*
    Compile / npmDependencies ++= Seq(
      "@types/node" -> "22.3.0",
      "@xterm/xterm" -> "5.5.0",
    ),
    */
  )

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
    nativeImageOutput := file("target") / "chesterup",
    graalvmSettings,
  ).jsSettings(
    scalaJSUseMainModuleInitializer := true
  )

lazy val js = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .crossType(CrossType.Full)
  .in(file("js"))
  .dependsOn(common, jsTypings)
  .settings(
    name := "js",
    commonSettings
  )
  .jsSettings(
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
  )

lazy val site = crossProject(JSPlatform).withoutSuffixFor(JSPlatform)
  .crossType(CrossType.Pure)
  .in(file("site"))
  .dependsOn(js, common, jsTypings)
  .settings(
    name := "site",
    commonSettings
  )
  .jsSettings(
    libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "2.1.1",
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
  .in(file("."))
  .aggregate(
    typednode,
    typedstd,
    typedundici,
    typedxterm,
    kiamaCore,
    effektKiama,
    jsTypings,
    core,
    common,
    cli,
    lsp, up, truffle,
    js, site)
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
