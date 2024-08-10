package chester.utils

def codePointIsEmoji(codePoint: Int): Boolean = {
  val block = Character.UnicodeBlock.of(codePoint)

  block == Character.UnicodeBlock.EMOTICONS ||
    block == Character.UnicodeBlock.MISCELLANEOUS_SYMBOLS_AND_PICTOGRAPHS ||
    block == Character.UnicodeBlock.TRANSPORT_AND_MAP_SYMBOLS ||
    block == Character.UnicodeBlock.SUPPLEMENTAL_SYMBOLS_AND_PICTOGRAPHS ||
    block == Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_A ||
    block == Character.UnicodeBlock.SUPPLEMENTARY_PRIVATE_USE_AREA_B
}
