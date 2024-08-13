import scala.scalanative.build.*
import org.jetbrains.sbtidea.Keys._

val scala3Version = "3.4.2"
val graalVm = "graalvm-java22"
val graalVersion = "22.0.2"
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

ThisBuild / version := "0.1.0-SNAPSHOT"

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


lazy val common = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("common"))
  .settings(
    name := "ChesterCommon",
    libraryDependencies ++= Seq(
      "org.scalameta" %%% "munit" % "1.0.0" % Test,
    ),
    assembly / assemblyJarName := "common.jar",
    commonSettings
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "fansi" % "0.5.0",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "com.lihaoyi" %%% "fastparse" % "3.1.0",
      "com.lihaoyi" %%% "pprint" % "0.9.0"
    )
  )
  .jsSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "fansi" % "0.5.0",
      "org.typelevel" %%% "cats-core" % "2.12.0",
      "com.lihaoyi" %%% "fastparse" % "3.1.0",
      "com.lihaoyi" %%% "pprint" % "0.9.0"
    )
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "fansi" % "0.4.0",
      "org.typelevel" %%% "cats-core" % "2.11.0",
      "com.lihaoyi" %%% "fastparse" % "3.0.2",
      "com.lihaoyi" %%% "pprint" % "0.8.1"
    )
  )

// Blocking Scala Native 0.5:
// https://github.com/bkirwi/decline/issues/551
// https://github.com/edadma/readline/pull/1
lazy val cli = crossProject(JSPlatform, JVMPlatform, NativePlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("cli"))
  .enablePlugins(NativeImagePlugin)
  .dependsOn(common)
  .settings(
    name := "chester",
    Compile / mainClass := Some("chester.cli.Main"),
    assembly / assemblyJarName := "chester.jar",
    nativeImageOutput := file("target") / "chester",
    libraryDependencies ++= Seq(
      "com.monovore" %%% "decline" % "2.4.1"
    ),
    commonSettings
  )
  .jvmSettings(
    nativeImageVersion := graalVersion,
    nativeImageOptions := nativeImageOption,
    nativeImageJvm := graalVm,
    libraryDependencies ++= Seq(
      "org.jline" % "jline" % "3.26.2",
    )
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
    jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv(),
    libraryDependencies ++= Seq(
    )
  )
  .nativeSettings(
    libraryDependencies ++= Seq(
      "io.github.edadma" %%% "readline" % "0.1.3"
    ),
    scalacOptions ++= (if (unix) Seq("-Xmacro-settings:com.eed3si9n.ifdef.declare:readline") else Seq())
  )
lazy val lsp = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("lsp"))
  .enablePlugins(NativeImagePlugin)
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

lazy val idea = crossProject(JVMPlatform).withoutSuffixFor(JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("idea"))
  .enablePlugins(SbtIdeaPlugin)
  .settings(
    common0Settings,
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.13.10",
    ThisBuild / intellijPluginName := "My Awesome Framework",
    ThisBuild / intellijBuild := "231.9011.34",
    ThisBuild / intellijPlatform := IntelliJPlatform.IdeaCommunity,
    Global / intellijAttachSources := true,
    Compile / javacOptions ++= "--release" :: "17" :: Nil,
    intellijPlugins += "com.intellij.properties".toPlugin,
    libraryDependencies += "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.5" withSources(),
    Compile / unmanagedResourceDirectories += baseDirectory.value / "resources",
    Test / unmanagedResourceDirectories += baseDirectory.value / "testResources"
  )

lazy val root = project
  .in(file("."))
  .aggregate(common.jvm, common.js, common.native, cli.jvm, cli.js, cli.native, lsp.jvm, idea.jvm)
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
