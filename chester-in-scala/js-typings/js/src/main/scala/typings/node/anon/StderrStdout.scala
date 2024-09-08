package typings.node.anon

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait StderrStdout extends StObject {
  
  var stderr: String | typings.node.bufferMod.global.Buffer
  
  var stdout: String | typings.node.bufferMod.global.Buffer
}
object StderrStdout {
  
  inline def apply(
    stderr: String | typings.node.bufferMod.global.Buffer,
    stdout: String | typings.node.bufferMod.global.Buffer
  ): StderrStdout = {
    val __obj = js.Dynamic.literal(stderr = stderr.asInstanceOf[js.Any], stdout = stdout.asInstanceOf[js.Any])
    __obj.asInstanceOf[StderrStdout]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: StderrStdout] (val x: Self) extends AnyVal {
    
    inline def setStderr(value: String | typings.node.bufferMod.global.Buffer): Self = StObject.set(x, "stderr", value.asInstanceOf[js.Any])
    
    inline def setStdout(value: String | typings.node.bufferMod.global.Buffer): Self = StObject.set(x, "stdout", value.asInstanceOf[js.Any])
  }
}
