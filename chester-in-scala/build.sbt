import java.nio.file.{Files, Paths}
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
  "--initialize-at-build-time=algebra,cps,com.oracle,spire,org.graalvm,scopt,fastparse,scala,java,chester,org.eclipse,cats,fansi,sourcecode,com.monovore.decline,geny,pprint",
  "-O2",
)

val commonSettings = Seq(
  scalaVersion := scala3Version,
  //githubTokenSource := TokenSource.GitConfig("github.token") || TokenSource.Environment("GITHUB_TOKEN"),
  //resolvers += Resolver.githubPackages("edadma", "readline"),
  resolvers += "jitpack" at "https://jitpack.io"
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

val windows: Boolean = System.getProperty("os.name").toLowerCase.contains("win")
val unix: Boolean = !windows
val permitGPLcontamination: Boolean = false

val classVersion = java.lang.Float.parseFloat(System.getProperty("java.class.version"))
val jdk17ClassVersion = 61.0f
val jdk17: Boolean = classVersion >= jdk17ClassVersion

lazy val common = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("common"))
  .jsEnablePlugins(ScalablyTypedConverterPlugin)
  .settings(
    name := "chester",
    libraryDependencies ++= Seq(
      "org.scalameta" %%% "munit" % "1.0.0" % Test,
    ),
    assembly / assemblyJarName := "common.jar",
    commonSettings,
    autoCompilerPlugins := true,
    addCompilerPlugin("com.github.rssh" %% "dotty-cps-async-compiler-plugin" % "0.9.21")
  )
  .jvmSettings(
    libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0",
    libraryDependencies += "org.graalvm.sdk" % "nativeimage" % graalvmVersion,
    scalacOptions ++= (if (jdk17) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:jdk17") else Seq()),
    libraryDependencies ++= Seq(
      "com.github.rssh" %%% "dotty-cps-async" % "0.9.21",
      "org.typelevel" %%% "spire" % "0.18.0",
      "com.lihaoyi" %%% "fansi" % "0.5.0",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "org.typelevel" %%% "cats-free" % "2.12.0",
      "com.lihaoyi" %%% "fastparse" % "3.1.0",
      "com.lihaoyi" %%% "pprint" % "0.9.0" % Test
    ),
  )
  .jsSettings(
    Compile / npmDependencies ++= Seq(
      "@types/node" -> "22.3.0"
    ),
    //scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) }
    libraryDependencies ++= Seq(
      "com.github.rssh" %%% "dotty-cps-async" % "0.9.21",
      "org.typelevel" %%% "spire" % "0.18.0",
      "com.lihaoyi" %%% "fansi" % "0.5.0",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "org.typelevel" %%% "cats-free" % "2.12.0",
      "com.lihaoyi" %%% "fastparse" % "3.1.0",
      "com.lihaoyi" %%% "pprint" % "0.9.0" % Test
    ),
  )
  .nativeSettings(
    libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0",
    // temporary posixlib patch
    libraryDependencies ++= Seq(
      "com.github.mio-19" % "scala-native" % "acc07af027",
    ),
    libraryDependencies ++= Seq(
      "com.github.rssh.dotty-cps-async" %%% "dotty-cps-async-for34" % "29dc6f3bf8", // Scala Native 0.5 patch
      "com.github.mio-19.spire" /*"org.typelevel"*/ %%% "spire" % "fcf7d67b61", // Scala Native 0.5 patch
      "com.lihaoyi" %%% "fansi" % "0.5.0",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "org.typelevel" %%% "cats-free" % "2.12.0",
      "com.lihaoyi" %%% "fastparse" % "3.1.0",
      "com.lihaoyi" %%% "pprint" % "0.9.0" % Test
    ),
  )

lazy val cli = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("cli"))
  .jvmEnablePlugins(NativeImagePlugin)
  .jsEnablePlugins(ScalablyTypedConverterPlugin)
  .dependsOn(common)
  .settings(
    name := "chester-cli",
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
      // https://github.com/jline/jline3/issues/954
      "org.jline" % "jline" % "3.26.2",
    )
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    libraryDependencies ++= Seq(
    ),
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      //"io.github.edadma" %%% "readline" % "0.1.3"
    ),
    scalacOptions ++= (if (unix && permitGPLcontamination) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:readline") else Seq())
  )

lazy val up = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("up"))
  .jvmEnablePlugins(NativeImagePlugin)
  .jsEnablePlugins(ScalablyTypedConverterPlugin)
  .dependsOn(common)
  .settings(
    name := "chesterup",
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
  .jsEnablePlugins(ScalablyTypedConverterPlugin)
  .dependsOn(common)
  .settings(
    name := "chester-js",
    commonSettings
  )
  .jsSettings(
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    Compile / npmDependencies ++= Seq(
      "@xterm/xterm" -> "5.5.0"
    ),
  )

lazy val lsp = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("lsp"))
  .jvmEnablePlugins(NativeImagePlugin)
  .dependsOn(common)
  .settings(
    name := "chester-lsp",
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
    name := "chester-truffle",
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

lazy val root = project
  .in(file("."))
  .aggregate(common.jvm, common.js, common.native, cli.jvm, cli.js, cli.native, lsp.jvm, js.js)
  .settings(
    name := "Chester",
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
