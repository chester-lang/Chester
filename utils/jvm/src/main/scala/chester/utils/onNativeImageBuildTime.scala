package chester.utils

import org.graalvm.nativeimage.ImageInfo

inline def onNativeImageBuildTime(f: => Unit): Unit =
  if (ImageInfo.inImageBuildtimeCode) f else ()
