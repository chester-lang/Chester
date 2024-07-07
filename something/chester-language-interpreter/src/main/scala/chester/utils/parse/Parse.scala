package chester.utils.parse

import fastparse.*
import fastparse.NoWhitespace.*

import java.lang.Character.{isDigit, isLetter}

type Character = Int


def CharacterPred(p: Character => Boolean)(implicit ctx: P[_]): P[Unit] =
  P(CharPred(c => (!Character.isSurrogate(c) && p(c.toInt))) |
    (CharPred(Character.isHighSurrogate) ~ CharPred(Character.isLowSurrogate)).!.flatMap(c => {
      val codePoint = Character.codePointAt(c, 0)
      assert(c.codePointCount(0, c.length) == 1)
      if (p(codePoint)) Pass else Fail
    }))