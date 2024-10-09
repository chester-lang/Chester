#!/bin/sh
exec sbt scalafmtAll scalafmtSbt 'rootJVM / scalafix RemoveUnused'