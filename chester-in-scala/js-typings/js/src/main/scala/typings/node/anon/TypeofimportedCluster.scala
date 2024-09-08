package typings.node.anon

import org.scalablytyped.runtime.Instantiable0
import typings.node.clusterMod.Cluster
import typings.node.clusterMod.Worker
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait TypeofimportedCluster extends StObject {
  
  val default: Cluster
  
  var Worker: Instantiable0[typings.node.clusterMod.Worker]
}
object TypeofimportedCluster {
  
  inline def apply(Worker: Instantiable0[Worker], default: Cluster): TypeofimportedCluster = {
    val __obj = js.Dynamic.literal(Worker = Worker.asInstanceOf[js.Any], default = default.asInstanceOf[js.Any])
    __obj.asInstanceOf[TypeofimportedCluster]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: TypeofimportedCluster] (val x: Self) extends AnyVal {
    
    inline def setDefault(value: Cluster): Self = StObject.set(x, "default", value.asInstanceOf[js.Any])
    
    inline def setWorker(value: Instantiable0[Worker]): Self = StObject.set(x, "Worker", value.asInstanceOf[js.Any])
  }
}
