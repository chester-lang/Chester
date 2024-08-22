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

  case object GNULinux extends UNIX

  case object Windows extends OS {
    def useCRLF: Boolean = true
  }
}