#!/bin/sh
cd "$(dirname "$0")"
cd scala-native
sbt "publish-local-dev 3.5.0"
