package chester.utils

import munit.FunSuite

class StringPosSuite extends FunSuite {
  test("charIndexToUnicodeIndex for simple characters") {
    val sp = StringPos("hello")
    assertEquals(sp.charIndexToUnicodeIndex(0), 0)
    assertEquals(sp.charIndexToUnicodeIndex(4), 4)
  }

  test("charIndexToUnicodeIndex for characters with surrogate pairs") {
    val sp = StringPos("a\uD834\uDD1Eb")
    assertEquals(sp.charIndexToUnicodeIndex(0), 0)
    assertEquals(sp.charIndexToUnicodeIndex(1), 1)
    assertEquals(sp.charIndexToUnicodeIndex(2), 2)
    assertEquals(sp.charIndexToUnicodeIndex(3), 2)
  }

  test("unicodeIndexToCharIndex for simple characters") {
    val sp = StringPos("hello")
    assertEquals(sp.unicodeIndexToCharIndex(0), 0)
    assertEquals(sp.unicodeIndexToCharIndex(4), 4)
  }

  test("unicodeIndexToCharIndex for characters with surrogate pairs") {
    val sp = StringPos("a\uD834\uDD1Eb")
    assertEquals(sp.unicodeIndexToCharIndex(0), 0)
    assertEquals(sp.unicodeIndexToCharIndex(1), 1)
    assertEquals(sp.unicodeIndexToCharIndex(2), 3)
  }

  test("unicodeLength for simple characters") {
    val sp = StringPos("hello")
    assertEquals(sp.unicodeLength, 5)
  }

  test("unicodeLength for characters with surrogate pairs") {
    val sp = StringPos("a\uD834\uDD1Eb")
    assertEquals(sp.unicodeLength, 3)
  }

  test("charIndexToCharLineAndColumn for single line string") {
    val sp = StringPos("hello")
    assertEquals(sp.charIndexToCharLineAndColumn(0), LineAndColumn(0, 0))
    assertEquals(sp.charIndexToCharLineAndColumn(4), LineAndColumn(0, 4))
  }

  test("charIndexToCharLineAndColumn for multi-line string") {
    val sp = StringPos("hello\nworld")
    assertEquals(sp.charIndexToCharLineAndColumn(0), LineAndColumn(0, 0))
    assertEquals(sp.charIndexToCharLineAndColumn(5), LineAndColumn(0, 5))
    assertEquals(sp.charIndexToCharLineAndColumn(6), LineAndColumn(1, 0))
    assertEquals(sp.charIndexToCharLineAndColumn(11), LineAndColumn(1, 5))
  }

  test("charIndexToUnicodeLineAndColumn for single line string") {
    val sp = StringPos("hello")
    assertEquals(sp.charIndexToUnicodeLineAndColumn(0), LineAndColumn(0, 0))
    assertEquals(sp.charIndexToUnicodeLineAndColumn(4), LineAndColumn(0, 4))
  }

  test("charIndexToUnicodeLineAndColumn for multi-line string with surrogate pairs") {
    val sp = StringPos("hello\n\uD834\uDD1Eworld")
    assertEquals(sp.charIndexToUnicodeLineAndColumn(0), LineAndColumn(0, 0))
    assertEquals(sp.charIndexToUnicodeLineAndColumn(5), LineAndColumn(0, 5))
    assertEquals(sp.charIndexToUnicodeLineAndColumn(6), LineAndColumn(1, 0))
    assertEquals(sp.charIndexToUnicodeLineAndColumn(8), LineAndColumn(1, 1))
  }
}
