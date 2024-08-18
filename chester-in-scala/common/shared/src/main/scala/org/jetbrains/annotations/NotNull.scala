package org.jetbrains.annotations

class NotNull extends scala.annotation.Annotation {

}

class Nullable extends scala.annotation.Annotation {

}

class Contract extends scala.annotation.Annotation {
  def this(name: String) = this()
}
