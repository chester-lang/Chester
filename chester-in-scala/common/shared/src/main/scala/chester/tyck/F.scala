package chester.tyck

import cps.await

type F = [X] =>> Trying[TyckState, X]
implicit inline def convertF[T](inline x: Trying[TyckState, T]): F[T] = x
implicit inline def unconvertF[T](inline x: F[T]): Trying[TyckState, T] = x

extension [F[_], T, G[_]](f: F[T]) {
  transparent inline def !(using inline ctx: _root_.cps.CpsMonadContext[G], inline conversion: _root_.cps.CpsMonadConversion[F, G]): T = await(f)
}
