package chester.parser

import typings.node.fsMod

implicit object FilePathImplNode extends FilePathImpl {
  override def readContent(fileName: String): Either[ParseError, String] =
    Right(fsMod.readFileSync(fileName))

  override def absolute(fileName: String): String = fsMod.realpathSync(fileName)
}
