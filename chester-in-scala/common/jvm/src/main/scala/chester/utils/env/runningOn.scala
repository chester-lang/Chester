package chester.utils.env

//import org.graalvm.nativeimage.ImageInfo

lazy val getRunningOn: RunningOn = if(false/*ImageInfo.inImageCode*/) RunningOn.NativeImage("") else RunningOn.JVM("")