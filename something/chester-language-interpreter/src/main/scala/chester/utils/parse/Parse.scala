package chester.utils.parse

import fastparse.*
import fastparse.NoWhitespace.*

import java.lang.Character.{isDigit, isLetter}

type Character = Int


inline def CharacterPred(inline p: Character => Boolean)(implicit ctx: P[_]): P[Unit] =
  CharPred(c => (!Character.isSurrogate(c) && p(c.toInt))) |
    (CharPred(Character.isHighSurrogate) ~ CharPred(Character.isLowSurrogate)).!.flatMap(c => {
      val codePoint = Character.codePointAt(c, 0)
      assert(c.codePointCount(0, c.length) == 1)
      if (p(codePoint)) Pass else Fail
    })

inline def CharactersWhile(inline p: Character => Boolean, inline min: Int = 1)(implicit ctx: P[_]): P[Unit] =
  CharacterPred(p).rep(min)