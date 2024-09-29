package chester.idea

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object ChesterFileType extends LanguageFileType(ChesterLanguage) {
  override def getName: String = "Chester File"
  override def getDescription: String = "Chester language file"
  override def getDefaultExtension: String = "chester"
  override def getIcon: Icon = null // Provide an icon if available
}
