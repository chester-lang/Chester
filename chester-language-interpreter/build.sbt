val scala3Version = "3.4.2"
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", "versions", xs @ _*) => MergeStrategy.first
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

lazy val root = (project in file("."))
  .aggregate(common, cli, lsp)
  .settings(
    name := "Chester",
    scalaVersion := scala3Version
  )

lazy val common = (project in file("common"))
  .settings(
    assembly / assemblyJarName := "common.jar",
    name := "ChesterCommon",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "com.lihaoyi" %% "fastparse" % "3.1.0",
      "com.lihaoyi" %% "pprint" % "0.9.0"
    )
  )

lazy val cli = (project in file("chester"))
  .enablePlugins(NativeImagePlugin)
  .dependsOn(common)
  .settings(
    assembly / assemblyJarName := "chester.jar",
    name := "ChesterCLI",
    scalaVersion := scala3Version,
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
    assembly / assemblyJarName := "lsp.jar",
    name := "ChesterLanguageServer",
    scalaVersion := scala3Version,
    Compile / mainClass := Some("chester.lsp.Main"),
    libraryDependencies += "org.eclipse.lsp4j" % "org.eclipse.lsp4j" % "0.23.1"
  )