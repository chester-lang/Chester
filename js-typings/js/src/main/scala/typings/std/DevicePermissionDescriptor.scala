package typings.std

import typings.std.stdStrings.camera
import typings.std.stdStrings.microphone
import typings.std.stdStrings.speaker
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait DevicePermissionDescriptor
  extends StObject
     with PermissionDescriptor {
  
  /* standard dom */
  var deviceId: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  @JSName("name")
  var name_DevicePermissionDescriptor: camera | microphone | speaker
}
object DevicePermissionDescriptor {
  
  inline def apply(name: camera | microphone | speaker): DevicePermissionDescriptor = {
    val __obj = js.Dynamic.literal(name = name.asInstanceOf[js.Any])
    __obj.asInstanceOf[DevicePermissionDescriptor]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: DevicePermissionDescriptor] (val x: Self) extends AnyVal {
    
    inline def setDeviceId(value: java.lang.String): Self = StObject.set(x, "deviceId", value.asInstanceOf[js.Any])
    
    inline def setDeviceIdUndefined: Self = StObject.set(x, "deviceId", js.undefined)
    
    inline def setName(value: camera | microphone | speaker): Self = StObject.set(x, "name", value.asInstanceOf[js.Any])
  }
}
