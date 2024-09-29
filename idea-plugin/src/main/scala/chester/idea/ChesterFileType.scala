package chester.idea

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object ChesterFileType extends LanguageFileType(ChesterLanguage) {
  override def getName: String = "Chester File"
  override def getDescription: String = "Chester language file"
  override def getDefaultExtension: String = "chester"
  override def getIcon: Icon = IconLoader.getIcon("/icons/chester-icon.png", getClass)
}
