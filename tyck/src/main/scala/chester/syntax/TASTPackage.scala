package chester.syntax

import chester.syntax.*
import chester.syntax.core.{BlockTerm, Effects, Term}
import chester.tyck.SeverityMap
import chester.utils.*
import com.eed3si9n.ifdef.*
import upickle.default.*
import upickle.default as upickle

object TASTPackage {
  onNativeImageBuildTime {
    // it will be lazy val in the JVM bytecode, if we are building a native image, it will be calculated at build time.
    readwriter[TAST]
  }

  // Typed Abstract Syntax Trees
  // files
  // TODO: handle SourcePos for performance and file size, especially avoid duplicated SourceOffset
  case class TAST(fileName: String, module: ModuleRef, ast: BlockTerm, ty: Term, effects: Effects, problems: SeverityMap)derives ReadWriter {
    def writeBinary: Array[Byte] = upickle.writeBinary[TAST](this)

    def readBinary(bytes: Array[Byte]): TAST = upickle.readBinary[TAST](bytes)

    def writeString: String = upickle.write[TAST](this)

    def readString(str: String): TAST = upickle.read[TAST](str)
  }

}

export TASTPackage.TAST