package chester.utils

import cats.data.*
import upickle.default.*

import scala.language.implicitConversions

inline implicit def elimNonEmptySeq[T](x: NonEmptySeq[T]): Seq[T] = x.toSeq
inline implicit def elimNonEmptyVector[T](x: NonEmptyVector[T]): Vector[T] = x.toVector

extension [T](xs: NonEmptySeq[T]) {
  inline def toVector: NonEmptyVector[T] = NonEmptyVector.fromVectorUnsafe(xs.toSeq.toVector)
}

extension [T](x: Seq[T]) {
  inline def assumeNonEmpty: NonEmptySeq[T] = NonEmptySeq.fromSeqUnsafe(x)
}
extension [T](x: Vector[T]) {
  inline def assumeNonEmpty: NonEmptyVector[T] = NonEmptyVector.fromVectorUnsafe(x)
}

implicit def nonEmptySeqRW[T: ReadWriter]: ReadWriter[NonEmptySeq[T]] = readwriter[Seq[T]].bimap(_.toSeq, NonEmptySeq.fromSeqUnsafe)
implicit def nonEmptyVectorRW[T: ReadWriter]: ReadWriter[NonEmptyVector[T]] = readwriter[Vector[T]].bimap(_.toVector, NonEmptyVector.fromVectorUnsafe)