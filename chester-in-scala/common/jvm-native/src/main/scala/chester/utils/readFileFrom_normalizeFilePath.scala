package chester.utils

import java.nio.file.{Files, Paths}

def readFileFrom(path: String): String = new String(Files.readAllBytes(Paths.get(path)))

def normalizeFilePath(path: String): String = Paths.get(path).getFileName.toString