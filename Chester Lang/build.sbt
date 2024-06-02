ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  //.enablePlugins(ScalaJSPlugin)
  .settings(
    //scalaJSUseMainModuleInitializer := true,
  )
  .settings(
    name := "Chester Lang",
    idePackagePrefix := Some("chester.lang"),
    libraryDependencies += "com.lihaoyi" %%% "fastparse" % "3.1.0",
    libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.18" % "test"
  )
