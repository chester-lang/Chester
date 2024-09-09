import org.jetbrains.sbtidea.Keys._

lazy val myAwesomeFramework =
  project.in(file("."))
    .enablePlugins(SbtIdeaPlugin)
    .settings(
      version := "0.0.1-SNAPSHOT",
      scalaVersion := "3.5.0",
      ThisBuild / intellijPluginName := "My Awesome Framework",
      ThisBuild / intellijBuild      := "242.20224.300",
      ThisBuild / intellijPlatform   := IntelliJPlatform.IdeaCommunity,
      Global    / intellijAttachSources := true,
      Compile / javacOptions ++= "--release" :: "17" :: Nil,
      intellijPlugins += "com.intellij.properties".toPlugin,
      resolvers += "jitpack" at "https://jitpack.io",
      libraryDependencies += "com.github.chester-lang.chester" %% "chester" % "b6f9673fc4",
      libraryDependencies += "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.5" withSources(),
      Compile / unmanagedResourceDirectories += baseDirectory.value / "resources",
      Test / unmanagedResourceDirectories    += baseDirectory.value / "testResources"
    )
