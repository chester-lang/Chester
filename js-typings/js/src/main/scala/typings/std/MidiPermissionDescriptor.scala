package typings.std

import typings.std.stdStrings.midi
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait MidiPermissionDescriptor
  extends StObject
     with PermissionDescriptor {
  
  /* standard dom */
  @JSName("name")
  var name_MidiPermissionDescriptor: midi
  
  /* standard dom */
  var sysex: js.UndefOr[scala.Boolean] = js.undefined
}
object MidiPermissionDescriptor {
  
  inline def apply(): MidiPermissionDescriptor = {
    val __obj = js.Dynamic.literal(name = "midi")
    __obj.asInstanceOf[MidiPermissionDescriptor]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: MidiPermissionDescriptor] (val x: Self) extends AnyVal {
    
    inline def setName(value: midi): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
    
    inline def setSysex(value: scala.Boolean): Self = StObject.set(x, "sysex", value.asInstanceOf[js.Any])
    
    inline def setSysexUndefined: Self = StObject.set(x, "sysex", js.undefined)
  }
}
