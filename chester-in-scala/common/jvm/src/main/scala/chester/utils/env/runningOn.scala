package chester.utils.env

import org.graalvm.nativeimage.ImageInfo

val getRunningOn: RunningOn = if(ImageInfo.inImageCode) RunningOn.NativeImage(System.getProperty("org.graalvm.version")) else RunningOn.JVM(System.getProperty("java.version"))