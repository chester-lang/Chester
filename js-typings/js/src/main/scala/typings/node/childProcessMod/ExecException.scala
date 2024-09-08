package typings.node.childProcessMod

import typings.node.processMod.global.NodeJS.Signals
import typings.std.Error
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait ExecException
  extends StObject
     with Error {
  
  var cmd: js.UndefOr[String] = js.undefined
  
  var code: js.UndefOr[Double] = js.undefined
  
  var killed: js.UndefOr[Boolean] = js.undefined
  
  var signal: js.UndefOr[Signals] = js.undefined
  
  var stderr: js.UndefOr[String] = js.undefined
  
  var stdout: js.UndefOr[String] = js.undefined
}
object ExecException {
  
  inline def apply(message: String, name: String): ExecException = {
    val __obj = js.Dynamic.literal(message = message.asInstanceOf[js.Any], name = name.asInstanceOf[js.Any])
    __obj.asInstanceOf[ExecException]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: ExecException] (val x: Self) extends AnyVal {
    
    inline def setCmd(value: String): Self = StObject.set(x, "cmd", value.asInstanceOf[js.Any])
    
    inline def setCmdUndefined: Self = StObject.set(x, "cmd", js.undefined)
    
    inline def setCode(value: Double): Self = StObject.set(x, "code", value.asInstanceOf[js.Any])
    
    inline def setCodeUndefined: Self = StObject.set(x, "code", js.undefined)
    
    inline def setKilled(value: Boolean): Self = StObject.set(x, "killed", value.asInstanceOf[js.Any])
    
    inline def setKilledUndefined: Self = StObject.set(x, "killed", js.undefined)
    
    inline def setSignal(value: Signals): Self = StObject.set(x, "signal", value.asInstanceOf[js.Any])
    
    inline def setSignalUndefined: Self = StObject.set(x, "signal", js.undefined)
    
    inline def setStderr(value: String): Self = StObject.set(x, "stderr", value.asInstanceOf[js.Any])
    
    inline def setStderrUndefined: Self = StObject.set(x, "stderr", js.undefined)
    
    inline def setStdout(value: String): Self = StObject.set(x, "stdout", value.asInstanceOf[js.Any])
    
    inline def setStdoutUndefined: Self = StObject.set(x, "stdout", js.undefined)
  }
}
