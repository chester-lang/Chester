import java.nio.file.{Files, Paths}
import scala.scalanative.build.*

val scala3Version = "3.5.0-RC7"

val path2022 = Paths.get("C:\\Program Files\\Microsoft Visual Studio\\2022")
val path2019 = Paths.get("C:\\Program Files (x86)\\Microsoft Visual Studio\\2019")
val path2022Exists = Files.exists(path2022)
val path2019Exists = Files.exists(path2019)
val graalvm17 = path2019Exists && !path2022Exists
val graalVm = if(graalvm17) "graalvm-java17" else "graalvm-java22"
val graalVersion = if(graalvm17) "17.0.9" else "22.0.2"

val nativeImageOption = Seq(
  "--verbose",
  "--no-fallback",
  "-enablesystemassertions",
  // runtime: org.jline
  "--initialize-at-build-time=scopt,fastparse,scala,java,chester,org.eclipse,cats,fansi,sourcecode,com.monovore.decline,geny,pprint",
  "-O2",
)


val common0Settings = Seq(
  githubTokenSource := TokenSource.GitConfig("github.token") || TokenSource.Environment("GITHUB_TOKEN"),
)

val commonSettings = Seq(
  scalaVersion := scala3Version,
  githubTokenSource := TokenSource.GitConfig("github.token") || TokenSource.Environment("GITHUB_TOKEN"),
  resolvers += Resolver.githubPackages("edadma", "readline"),
)

commonSettings

ThisBuild / version := sys.env.getOrElse("VERSION", "0.0.1-RC0")
ThisBuild / organization := "com.github.chester-lang"

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", "versions", xs@_*) => MergeStrategy.first
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
  .settings(
    name := "chester",
    libraryDependencies ++= Seq(
      "org.scalameta" %%% "munit" % "1.0.0" % Test,
    ),
    assembly / assemblyJarName := "common.jar",
    commonSettings,
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "fansi" % "0.5.0",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "com.lihaoyi" %%% "fastparse" % "3.1.0",
      "com.lihaoyi" %%% "pprint" % "0.9.0"
    ),
    scalacOptions ++= (if (jdk17) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:jdk17") else Seq())
  )
  .jvmSettings(
  )
  .jsSettings(
  )
  .nativeSettings(
  )

// Blocking Scala Native 0.5:
// https://github.com/bkirwi/decline/issues/551
// https://github.com/edadma/readline/pull/1
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
    nativeImageVersion := graalVersion,
    nativeImageOptions := nativeImageOption,
    nativeImageJvm := graalVm,
    libraryDependencies ++= Seq(
      // https://github.com/jline/jline3/issues/954
      "org.jline" % "jline" % (if(graalvm17) "3.24.1" else "3.26.2"),
    )
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    libraryDependencies ++= Seq(
    ),
    Compile / npmDependencies ++= Seq(
      "@types/node" -> "22.3.0"
    ),
    stIgnore += "globals"
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      //"io.github.edadma" %%% "readline" % "0.1.3"
    ),
    scalacOptions ++= (if (unix && permitGPLcontamination) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:readline") else Seq())
  )
lazy val lsp = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
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
    nativeImageVersion := graalVersion,
    nativeImageOptions := nativeImageOption,
    nativeImageJvm := graalVm,
  )

lazy val root = project
  .in(file("."))
  .aggregate(common.jvm, common.js, common.native, cli.jvm, cli.js, cli.native, lsp.jvm)
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
