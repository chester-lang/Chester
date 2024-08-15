import org.jetbrains.sbtidea.Keys._

lazy val myAwesomeFramework =
  project.in(file("."))
    .enablePlugins(SbtIdeaPlugin)
    .settings(
      version := "0.0.1-SNAPSHOT",
      scalaVersion := "3.5.0-RC7",
      ThisBuild / intellijPluginName := "My Awesome Framework",
      ThisBuild / intellijBuild      := "231.9011.34",
      ThisBuild / intellijPlatform   := IntelliJPlatform.IdeaCommunity,
      Global    / intellijAttachSources := true,
      Compile / javacOptions ++= "--release" :: "17" :: Nil,
      intellijPlugins += "com.intellij.properties".toPlugin,
      resolvers += "jitpack" at "https://jitpack.io",
      libraryDependencies += "com.github.chester-lang" % "chester" % "6d66c57fe1",
      libraryDependencies += "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.5" withSources(),
      Compile / unmanagedResourceDirectories += baseDirectory.value / "resources",
      Test / unmanagedResourceDirectories    += baseDirectory.value / "testResources"
    )
