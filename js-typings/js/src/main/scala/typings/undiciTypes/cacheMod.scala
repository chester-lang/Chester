package typings.undiciTypes

import typings.undiciTypes.fetchMod.Request
import typings.undiciTypes.fetchMod.RequestInfo
import typings.undiciTypes.fetchMod.Response
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

object cacheMod {
  
  @JSImport("undici-types/cache", "caches")
  @js.native
  val caches: CacheStorage = js.native
  
  @js.native
  trait Cache extends StObject {
    
    def add(request: RequestInfo): js.Promise[Unit] = js.native
    
    def addAll(requests: js.Array[RequestInfo]): js.Promise[Unit] = js.native
    
    def delete(request: RequestInfo): js.Promise[Boolean] = js.native
    def delete(request: RequestInfo, options: CacheQueryOptions): js.Promise[Boolean] = js.native
    
    def keys(): js.Promise[js.Array[Request]] = js.native
    def keys(request: Unit, options: CacheQueryOptions): js.Promise[js.Array[Request]] = js.native
    def keys(request: RequestInfo): js.Promise[js.Array[Request]] = js.native
    def keys(request: RequestInfo, options: CacheQueryOptions): js.Promise[js.Array[Request]] = js.native
    
    def `match`(request: RequestInfo): js.Promise[js.UndefOr[Response]] = js.native
    def `match`(request: RequestInfo, options: CacheQueryOptions): js.Promise[js.UndefOr[Response]] = js.native
    
    def matchAll(): js.Promise[js.Array[Response]] = js.native
    def matchAll(request: Unit, options: CacheQueryOptions): js.Promise[js.Array[Response]] = js.native
    def matchAll(request: RequestInfo): js.Promise[js.Array[Response]] = js.native
    def matchAll(request: RequestInfo, options: CacheQueryOptions): js.Promise[js.Array[Response]] = js.native
    
    def put(request: RequestInfo, response: Response): js.Promise[Unit] = js.native
  }
  
  trait CacheQueryOptions extends StObject {
    
    var ignoreMethod: js.UndefOr[Boolean] = js.undefined
    
    var ignoreSearch: js.UndefOr[Boolean] = js.undefined
    
    var ignoreVary: js.UndefOr[Boolean] = js.undefined
  }
  object CacheQueryOptions {
    
    inline def apply(): CacheQueryOptions = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[CacheQueryOptions]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: CacheQueryOptions] (val x: Self) extends AnyVal {
      
      inline def setIgnoreMethod(value: Boolean): Self = StObject.set(x, "ignoreMethod", value.asInstanceOf[js.Any])
      
      inline def setIgnoreMethodUndefined: Self = StObject.set(x, "ignoreMethod", js.undefined)
      
      inline def setIgnoreSearch(value: Boolean): Self = StObject.set(x, "ignoreSearch", value.asInstanceOf[js.Any])
      
      inline def setIgnoreSearchUndefined: Self = StObject.set(x, "ignoreSearch", js.undefined)
      
      inline def setIgnoreVary(value: Boolean): Self = StObject.set(x, "ignoreVary", value.asInstanceOf[js.Any])
      
      inline def setIgnoreVaryUndefined: Self = StObject.set(x, "ignoreVary", js.undefined)
    }
  }
  
  @js.native
  trait CacheStorage extends StObject {
    
    def delete(cacheName: String): js.Promise[Boolean] = js.native
    
    def has(cacheName: String): js.Promise[Boolean] = js.native
    
    def keys(): js.Promise[js.Array[String]] = js.native
    
    def `match`(request: RequestInfo): js.Promise[js.UndefOr[Response]] = js.native
    def `match`(request: RequestInfo, options: MultiCacheQueryOptions): js.Promise[js.UndefOr[Response]] = js.native
    
    def open(cacheName: String): js.Promise[Cache] = js.native
  }
  
  trait MultiCacheQueryOptions
    extends StObject
       with CacheQueryOptions {
    
    var cacheName: js.UndefOr[String] = js.undefined
  }
  object MultiCacheQueryOptions {
    
    inline def apply(): MultiCacheQueryOptions = {
      val __obj = js.Dynamic.literal()
      __obj.asInstanceOf[MultiCacheQueryOptions]
    }
    
    @scala.inline
    implicit open class MutableBuilder[Self <: MultiCacheQueryOptions] (val x: Self) extends AnyVal {
      
      inline def setCacheName(value: String): Self = StObject.set(x, "cacheName", value.asInstanceOf[js.Any])
      
      inline def setCacheNameUndefined: Self = StObject.set(x, "cacheName", js.undefined)
    }
  }
}
