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

object OS {
  sealed trait UNIX extends OS {
    def useCRLF: Boolean = false
  }

  case object Mac extends UNIX

  sealed trait Linux extends UNIX
  sealed trait BSD extends UNIX
  case object FreeBSD extends BSD
  case object OpenBSD extends BSD
  case object NetBSD extends BSD

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

  case object Browser extends RunningOn
}

trait Environment {
  def getOS: OS
  def getArch: Architecture
  def getRunningOn: RunningOn
  def hasWindowsNarrator: Boolean = false
}

object BrowserEnv extends Environment {
  def getOS: OS = OS.Other
  def getArch: Architecture = Architecture.Other
  def getRunningOn: RunningOn = RunningOn.Browser
}

inline def getOS(using inline env: Environment): OS = env.getOS
inline def getArch(using inline env: Environment): Architecture = env.getArch
inline def getRunningOn(using inline env: Environment): RunningOn =
  env.getRunningOn
inline def hasWindowsNarrator(using inline env: Environment): Boolean =
  env.hasWindowsNarrator
