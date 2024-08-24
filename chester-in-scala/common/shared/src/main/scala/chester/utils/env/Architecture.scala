package chester.utils.env

enum Architecture {
  case Arm, Arm64, X86, Amd64, Other
}

sealed trait OS {
  def useCRLF: Boolean
  def isUNIX: Boolean = this match {
    case _: OS.UNIX => true
    case _          => false
  }
}

val isUNIX = getOS.isUNIX

object OS {
  sealed trait UNIX extends OS {
    def useCRLF: Boolean = false
  }

  case object Mac extends UNIX

  sealed trait Linux extends UNIX

  case object GNULinux extends Linux

  case object Termux extends Linux

  case object Windows extends OS {
    def useCRLF: Boolean = true
  }
  case object Other extends OS {
    def useCRLF: Boolean = false
  }
}

sealed trait RunningOn

object RunningOn {
  case class Nodejs(version: String) extends RunningOn

  sealed trait JavaLike extends RunningOn

  case class JVM(version: String) extends JavaLike

  case class NativeImage(version: String) extends JavaLike

  case class Native(version: String) extends RunningOn
}
