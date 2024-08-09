val scala3Version = "3.4.2"
val graalVm = "graalvm-java22"
val graalVersion = "22.0.2"
val nativeImageOption = Seq(
  "--verbose",
  "--no-fallback",
  "--initialize-at-build-time=scopt,fastparse,scala,java,chester,org.eclipse,cats,fansi,sourcecode", // runtime: org.jline
)

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", "versions", xs@_*) => MergeStrategy.first
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

lazy val root = (project in file("."))
  .aggregate(common, cli, lsp)
  .settings(
    name := "Chester",
    scalaVersion := scala3Version,
    nativeImageVersion := graalVersion,
    nativeImageOptions := nativeImageOption,
    nativeImageJvm := graalVm,
  )

lazy val common = (project in file("common"))
  .settings(
    assembly / assemblyJarName := "common.jar",
    name := "ChesterCommon",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "fansi" % "0.5.0",
      "org.typelevel" %% "cats-core" % "2.12.0",
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "com.lihaoyi" %% "fastparse" % "3.1.0",
      "com.lihaoyi" %% "pprint" % "0.9.0"
    )
  )

lazy val cli = (project in file("cli"))
  .enablePlugins(NativeImagePlugin)
  .dependsOn(common)
  .settings(
    assembly / assemblyJarName := "chester.jar",
    nativeImageOutput := file("target") / "chester",
    name := "ChesterCLI",
    scalaVersion := scala3Version,
    nativeImageVersion := graalVersion,
    nativeImageOptions := nativeImageOption,
    nativeImageJvm := graalVm,
    Compile / mainClass := Some("chester.cli.Main"),
    libraryDependencies ++= Seq(
      "org.jline" % "jline" % "3.26.2",
      "com.github.scopt" %% "scopt" % "4.1.0"
    )
  )

lazy val lsp = (project in file("lsp"))
  .enablePlugins(NativeImagePlugin)
  .dependsOn(common)
  .settings(
    assembly / assemblyJarName := "chester-lsp.jar",
    nativeImageOutput := file("target") / "chester-lsp",
    name := "ChesterLanguageServer",
    scalaVersion := scala3Version,
    nativeImageVersion := graalVersion,
    nativeImageOptions := nativeImageOption,
    nativeImageJvm := graalVm,
    Compile / mainClass := Some("chester.lsp.Main"),
    libraryDependencies += "org.eclipse.lsp4j" % "org.eclipse.lsp4j" % "0.23.1"
  )