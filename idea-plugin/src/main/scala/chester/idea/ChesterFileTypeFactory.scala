package chester.idea

import com.intellij.openapi.fileTypes.{FileTypeConsumer, FileTypeFactory}

class ChesterFileTypeFactory extends FileTypeFactory {
  override def createFileTypes(consumer: FileTypeConsumer): Unit = {
    consumer.consume(ChesterFileType, "chester")
  }
}
