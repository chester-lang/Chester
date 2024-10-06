package chester.syntax

import chester.syntax.*
import chester.syntax.core.{BlockTerm, Effects, Term}
import chester.syntax.tyck.TyckMeta
import com.eed3si9n.ifdef.*
import upickle.default.*
import upickle.default as upickle

object TASTPackage {
  // TODO: actually use this?
  @ifdef("graalvm-upickle-init")
  private def useYou:ReadWriter[TAST] = readwriter[TAST]
  @ifndef("graalvm-upickle-init")
  private def useYou:ReadWriter[TAST] = null

  useYou
  // Typed Abstract Syntax Trees
  // files
  // TODO: handle SourcePos for performance and file size, especially avoid duplicated SourceOffset
  case class TAST(fileName: String, module: ModuleRef, ast: BlockTerm, meta: TyckMeta, ty: Term, effects: Effects)derives ReadWriter {
    def writeBinary: Array[Byte] = upickle.writeBinary[TAST](this)

    def readBinary(bytes: Array[Byte]): TAST = upickle.readBinary[TAST](bytes)

    def writeString: String = upickle.write[TAST](this)

    def readString(str: String): TAST = upickle.read[TAST](str)
  }

}

export TASTPackage.TAST