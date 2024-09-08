package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait Stdout extends StObject {
  
  var stderr: typings.node.bufferMod.global.Buffer
  
  var stdout: typings.node.bufferMod.global.Buffer
}
object Stdout {
  
  inline def apply(stderr: typings.node.bufferMod.global.Buffer, stdout: typings.node.bufferMod.global.Buffer): Stdout = {
    val __obj = js.Dynamic.literal(stderr = stderr.asInstanceOf[js.Any], stdout = stdout.asInstanceOf[js.Any])
    __obj.asInstanceOf[Stdout]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: Stdout] (val x: Self) extends AnyVal {
    
    inline def setStderr(value: typings.node.bufferMod.global.Buffer): Self = StObject.set(x, "stderr", value.asInstanceOf[js.Any])
    
    inline def setStdout(value: typings.node.bufferMod.global.Buffer): Self = StObject.set(x, "stdout", value.asInstanceOf[js.Any])
  }
}
