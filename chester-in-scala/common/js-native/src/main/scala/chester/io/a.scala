package chester.io

import java.io.IOException
import java.net.URL
import java.nio.file.{Files, Path, StandardCopyOption}

object FileDownloader {

  @throws[IOException]
  def downloadFile(urlString: String, targetPath: Path): Unit = ???
}