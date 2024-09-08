package typings.undiciTypes

import typings.undiciTypes.mockInterceptorMod.Interceptable
import typings.undiciTypes.mockPoolMod.MockPool.Options
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object mockPoolMod {
  
  /** MockPool extends the Pool API and allows one to mock requests. */
  @JSImport("undici-types/mock-pool", JSImport.Default)
  @js.native
  open class default protected () extends MockPool {
    def this(origin: String, options: Options) = this()
  }
  
  /** MockPool extends the Pool API and allows one to mock requests. */
  @js.native
  trait MockPool
    extends typings.undiciTypes.poolMod.default
       with Interceptable
  object MockPool {
    
    trait Options
      extends StObject
         with typings.undiciTypes.poolMod.Pool.Options {
      
      /** The agent to associate this MockPool with. */
      var agent: typings.undiciTypes.mockAgentMod.default[typings.undiciTypes.mockAgentMod.MockAgent.Options]
    }
    object Options {
      
      inline def apply(
        agent: typings.undiciTypes.mockAgentMod.default[typings.undiciTypes.mockAgentMod.MockAgent.Options]
      ): Options = {
        val __obj = js.Dynamic.literal(agent = agent.asInstanceOf[js.Any])
        __obj.asInstanceOf[Options]
      }
      
      @scala.inline
      implicit open class MutableBuilder[Self <: Options] (val x: Self) extends AnyVal {
        
        inline def setAgent(
          value: typings.undiciTypes.mockAgentMod.default[typings.undiciTypes.mockAgentMod.MockAgent.Options]
        ): Self = StObject.set(x, "agent", value.asInstanceOf[js.Any])
      }
    }
  }
}
