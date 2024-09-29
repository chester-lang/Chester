import org.jetbrains.sbtidea.Keys._

lazy val chesterPlugin =
  project.in(file("."))
    .enablePlugins(SbtIdeaPlugin)
    .settings(
      name := "ChesterLanguageSupport",
      version := "1.0.0",
      scalaVersion := "3.5.1",
      ThisBuild / intellijPluginName := "Chester Language Support",
      ThisBuild / intellijBuild      := "242.23339.11",
      ThisBuild / intellijPlatform   := IntelliJPlatform.IdeaCommunity,
      Global    / intellijAttachSources := true,
      Compile / javacOptions ++= "--release" :: "17" :: Nil,
      intellijPlugins += "com.intellij.properties".toPlugin,
      intellijPlugins += "com.redhat.devtools.lsp4ij".toPlugin,
      resolvers += "jitpack" at "https://jitpack.io",
      // Exclude LSP4J dependencies
      libraryDependencies ++= Seq(
        ("com.github.chester-lang.chester" %% "lsp" % "eb1b9c26a7")
          .exclude("org.eclipse.lsp4j", "org.eclipse.lsp4j")
          .exclude("org.eclipse.lsp4j", "org.eclipse.lsp4j.jsonrpc"),
        "com.eclipsesource.minimal-json" % "minimal-json" % "0.9.5",
      ),
      Compile / unmanagedResourceDirectories += baseDirectory.value / "resources",
      Test / unmanagedResourceDirectories    += baseDirectory.value / "testResources"
    )
