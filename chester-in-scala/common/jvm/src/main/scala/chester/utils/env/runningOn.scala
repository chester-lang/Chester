package chester.utils.env

import org.graalvm.nativeimage.ImageInfo

lazy val getRunningOn: RunningOn = if(ImageInfo.inImageCode) RunningOn.NativeImage("") else RunningOn.JVM("")