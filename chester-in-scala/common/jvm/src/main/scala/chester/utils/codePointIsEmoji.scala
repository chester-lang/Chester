package chester.utils

import com.eed3si9n.ifdef._

@ifndef("jdk17")
def codePointIsEmoji(codePoint: Int): Boolean = {
  (codePoint >= 0x1F600 && codePoint <= 0x1F64F) || // Emoticons
    (codePoint >= 0x1F300 && codePoint <= 0x1F5FF) || // Miscellaneous Symbols and Pictographs
    (codePoint >= 0x1F680 && codePoint <= 0x1F6FF) || // Transport and Map Symbols
    (codePoint >= 0x1F900 && codePoint <= 0x1F9FF) || // Supplemental Symbols and Pictographs
    (codePoint >= 0xE000 && codePoint <= 0xF8FF) || // Supplementary Private Use Area A
    (codePoint >= 0xF0000 && codePoint <= 0xFFFFF) || // Supplementary Private Use Area B
    (codePoint >= 0x100000 && codePoint <= 0x10FFFF) // Supplementary Private Use Area B continuation
}

@ifdef("jdk17")
def codePointIsEmoji(codePoint: Int): Boolean = {
  val block = Character.UnicodeBlock.of(codePoint)

  block == Character.UnicodeBlock.EMOTICONS ||
    block == Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS ||
    block == Character.UnicodeBlock.TRANSPORT_AND_MAP_SYMBOLS ||
    block == Character.UnicodeBlock.SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS ||
    block == Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A ||
    block == Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B
}
