package chester.syntax.core

import chester.syntax.*
import upickle.default.*
import upickle.default as upickle

// Typed Abstract Syntax Trees
// files
// TODO: handle SourcePos for performance and file size, especially avoid duplicated SourceOffset
case class TAST(fileName: String, module: ModuleRef, ast: BlockTerm, ty: Term, effects: Effects) derives ReadWriter {
  def writeBinary: Array[Byte] = upickle.writeBinary[TAST](this)
  def readBinary(bytes: Array[Byte]): TAST = upickle.readBinary[TAST](bytes)
  def writeString: String = upickle.write[TAST](this)
  def readString(str: String): TAST = upickle.read[TAST](str)
}
