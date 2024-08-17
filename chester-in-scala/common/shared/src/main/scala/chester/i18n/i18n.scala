package chester.i18n

case class Language(tag: LanguageTag, region: RegionTag) {
  def name: String = s"${tag.name}_${region.name}"

  override def toString: String = name
}

object Language {
  def apply(tag: LanguageTag): Language = new Language(tag, tag.defaultRegion)

  private val languages = LanguageTag.values
  private val regions = RegionTag.values

  def from(x: String): Option[Language] = {
    // split by _ or -
    val parts = x.split("[_\\-]")
    if (parts.length == 1) {
      languages.find(_.is(parts(0))).map(Language(_))
    } else if (parts.length == 2) {
      for {
        l <- languages.find(_.is(parts(0)))
        r <- regions.find(_.is(parts(1)))
      } yield Language(l, r)
    } else {
      None
    }
  }
}

enum LanguageTag {
  case EN, ZH

  def name: String = toString.toLowerCase()

  def is(x: String): Boolean = x.toLowerCase() == name

  def defaultRegion: RegionTag = this match {
    case EN => RegionTag.NZ
    case ZH => RegionTag.TW
  }
}

enum RegionTag {
  case NZ, AU, TW, HK, US, BG

  def name: String = toString.toUpperCase()

  def is(x: String): Boolean = x.toUpperCase() == name
}

case class RegionTable(table: Map[RegionTag, Map[StringContext, StringContext]]) {
  val best: RegionTag = table.maxBy((_, map) => map.size)._1

  def get(region: RegionTag, context: StringContext): StringContext = {
    table.get(region).flatMap(_.get(context)).getOrElse(table.get(best).flatMap(_.get(context)).getOrElse(context))
  }
}

case class TranslationTable(table: Map[LanguageTag, RegionTable]) {
  def get(context: StringContext)(implicit lang: Language): StringContext = {
    table.get(lang.tag).map(_.get(lang.region, context)).getOrElse(context)
  }
}


extension (sc: StringContext)
  def t(args: Any*): String = {
    sc.s(args: _*)
  }

