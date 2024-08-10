package chester.utils

extension (x: String) {
  def getCodePoints: Seq[Int] = x.codePoints().toArray
}