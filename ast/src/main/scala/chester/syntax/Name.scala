package chester.syntax

import cats.data.NonEmptyVector
import chester.utils.*

type Name = String

inline def Name(inline name: String): Name = name

type UnresolvedID = Vector[Name]

type QualifiedIDString = Vector[Name]

extension (x: QualifiedIDString) {
  def name: Name = x.last
}


object QualifiedIDString {
  def from(id: Name*): QualifiedIDString = id.toVector
}