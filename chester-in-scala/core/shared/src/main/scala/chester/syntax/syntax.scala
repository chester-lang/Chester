package chester.syntax

type Id = String

type UnresolvedID = Vector[Id]

type QualifiedIDString = Vector[Id]


object QualifiedIDString {
  def from(id: Id*): QualifiedIDString = id.toVector
}