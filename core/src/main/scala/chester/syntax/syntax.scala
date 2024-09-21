package chester.syntax

type Name = String

type UnresolvedID = Vector[Name]

type QualifiedIDString = Vector[Name]


object QualifiedIDString {
  def from(id: Name*): QualifiedIDString = id.toVector
}