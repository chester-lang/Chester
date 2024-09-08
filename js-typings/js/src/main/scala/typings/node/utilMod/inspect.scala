package typings.node.utilMod

import typings.node.globalsMod.global.NodeJS.Dict
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object inspect {
  
  inline def apply(`object`: Any): String = ^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any]).asInstanceOf[String]
  inline def apply(`object`: Any, options: InspectOptions): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], options.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Boolean, depth: Double): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Boolean, depth: Double, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Boolean, depth: Null, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Boolean, depth: Unit, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Unit, depth: Double): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Unit, depth: Double, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Unit, depth: Null, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
  inline def apply(`object`: Any, showHidden: Unit, depth: Unit, color: Boolean): String = (^.asInstanceOf[js.Dynamic].apply(`object`.asInstanceOf[js.Any], showHidden.asInstanceOf[js.Any], depth.asInstanceOf[js.Any], color.asInstanceOf[js.Any])).asInstanceOf[String]
  
  @JSImport("util", "inspect")
  @js.native
  val ^ : js.Any = js.native
  
  @JSImport("util", "inspect.colors")
  @js.native
  def colors: Dict[js.Tuple2[Double, Double]] = js.native
  inline def colors_=(x: Dict[js.Tuple2[Double, Double]]): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("colors")(x.asInstanceOf[js.Any])
  
  /**
    * That can be used to declare custom inspect functions.
    */
  @JSImport("util", "inspect.custom")
  @js.native
  val custom: js.Symbol = js.native
  
  @JSImport("util", "inspect.defaultOptions")
  @js.native
  def defaultOptions: InspectOptions = js.native
  inline def defaultOptions_=(x: InspectOptions): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("defaultOptions")(x.asInstanceOf[js.Any])
  
  /**
    * Allows changing inspect settings from the repl.
    */
  @JSImport("util", "inspect.replDefaults")
  @js.native
  def replDefaults: InspectOptions = js.native
  inline def replDefaults_=(x: InspectOptions): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("replDefaults")(x.asInstanceOf[js.Any])
  
  /* Inlined {[ K in node.util.Style ]: string} */
  object styles {
    
    @JSImport("util", "inspect.styles")
    @js.native
    val ^ : js.Any = js.native
    
    @JSImport("util", "inspect.styles.bigint")
    @js.native
    def bigint: String = js.native
    inline def bigint_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("bigint")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.boolean")
    @js.native
    def boolean: String = js.native
    inline def boolean_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("boolean")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.date")
    @js.native
    def date: String = js.native
    inline def date_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("date")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.module")
    @js.native
    def module: String = js.native
    inline def module_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("module")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.null")
    @js.native
    val `null`: String = js.native
    
    @JSImport("util", "inspect.styles.number")
    @js.native
    def number: String = js.native
    inline def number_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("number")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.regexp")
    @js.native
    def regexp: String = js.native
    inline def regexp_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("regexp")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.special")
    @js.native
    def special: String = js.native
    inline def special_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("special")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.string")
    @js.native
    def string: String = js.native
    inline def string_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("string")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.symbol")
    @js.native
    def symbol: String = js.native
    inline def symbol_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("symbol")(x.asInstanceOf[js.Any])
    
    @JSImport("util", "inspect.styles.undefined")
    @js.native
    def undefined: String = js.native
    inline def undefined_=(x: String): Unit = ^.asInstanceOf[js.Dynamic].updateDynamic("undefined")(x.asInstanceOf[js.Any])
  }
}
