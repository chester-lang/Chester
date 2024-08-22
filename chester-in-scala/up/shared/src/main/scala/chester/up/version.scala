package chester.up

sealed trait Version {
  def install: Unit = ???

  def uninstall: Unit = ???
}

case object NodeJS extends Version

case object Jar extends Version

case object NativeImage extends Version


def getInstalled: Vector[Version] = ???

def getRecommended: Vector[Version] = ???

