package typings.xtermXterm.mod

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

@js.native
trait ILogger extends StObject {
  
  /**
    * Log a debug message, this will only be called if
    * {@link ITerminalOptions.logLevel} is set to debug or below.
    */
  def debug(message: String, args: Any*): Unit = js.native
  
  /**
    * Log a debug message, this will only be called if
    * {@link ITerminalOptions.logLevel} is set to error or below.
    */
  def error(message: String, args: Any*): Unit = js.native
  def error(message: js.Error, args: Any*): Unit = js.native
  
  /**
    * Log a debug message, this will only be called if
    * {@link ITerminalOptions.logLevel} is set to info or below.
    */
  def info(message: String, args: Any*): Unit = js.native
  
  /**
    * Log a trace message, this will only be called if
    * {@link ITerminalOptions.logLevel} is set to trace.
    */
  def trace(message: String, args: Any*): Unit = js.native
  
  /**
    * Log a debug message, this will only be called if
    * {@link ITerminalOptions.logLevel} is set to warn or below.
    */
  def warn(message: String, args: Any*): Unit = js.native
}
