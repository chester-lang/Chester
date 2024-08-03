val scala3Version = "3.4.2"
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", "versions", xs*) => MergeStrategy.first
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

lazy val root = (project in file("."))
  .aggregate(common, interpreter, lsp, repl)
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
      "org.eclipse.lsp4j" % "org.eclipse.lsp4j" % "0.23.1",
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "com.lihaoyi" %% "fastparse" % "3.1.0",
      "com.lihaoyi" %% "pprint" % "0.9.0"
    )
  )

lazy val interpreter = (project in file("interpreter"))
  .dependsOn(common)
  .settings(
    assembly / assemblyJarName := "interpreter.jar",
    name := "ChesterInterpreter",
    scalaVersion := scala3Version,
    mainClass in Compile := Some("chester.Main")
  )

lazy val lsp = (project in file("lsp"))
  .dependsOn(common)
  .settings(
    assembly / assemblyJarName := "lsp.jar",
    name := "ChesterLanguageServer",
    scalaVersion := scala3Version,
    mainClass in Compile := Some("chester.lsp.Main"),
    libraryDependencies += "org.eclipse.lsp4j" % "org.eclipse.lsp4j" % "0.23.1"
  )

lazy val repl = (project in file("repl"))
  .dependsOn(common)
  .settings(
    assembly / assemblyJarName := "repl.jar",
    name := "ChesterRepl",
    scalaVersion := scala3Version,
    mainClass in Compile := Some("chester.repl.Main"),
    libraryDependencies ++= Seq(
      "org.jline" % "jline" % "3.26.2"
    )
  )