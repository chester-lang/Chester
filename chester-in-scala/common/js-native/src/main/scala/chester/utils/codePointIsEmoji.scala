package chester.utils

def codePointIsEmoji(codePoint: Int): Boolean = {
  (codePoint >= 0x1F600 && codePoint <= 0x1F64F) || // Emoticons
    (codePoint >= 0x1F300 && codePoint <= 0x1F5FF) || // Miscellaneous Symbols and Pictographs
    (codePoint >= 0x1F680 && codePoint <= 0x1F6FF) || // Transport and Map Symbols
    (codePoint >= 0x1F900 && codePoint <= 0x1F9FF) || // Supplemental Symbols and Pictographs
    (codePoint >= 0xE000 && codePoint <= 0xF8FF) ||   // Supplementary Private Use Area A
    (codePoint >= 0xF0000 && codePoint <= 0xFFFFF) || // Supplementary Private Use Area B
    (codePoint >= 0x100000 && codePoint <= 0x10FFFF)  // Supplementary Private Use Area B continuation
}
