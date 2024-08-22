package chester.utils.env

enum Architecture {
  case Arm, Arm64, X86, Amd64
}

sealed trait OS {
  def useCRLF: Boolean
}

object OS {
  sealed trait UNIX extends OS {
    def useCRLF: Boolean = false
  }

  case object Mac extends UNIX
  
  sealed trait Linux extends UNIX

  case object GNULinux extends Linux
  
  case object AndroidOrTermux extends Linux

  case object Windows extends OS {
    def useCRLF: Boolean = true
  }
}