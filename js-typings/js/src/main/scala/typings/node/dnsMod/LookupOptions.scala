package typings.node.dnsMod

import typings.node.nodeStrings.IPv4
import typings.node.nodeStrings.IPv6
import typings.node.nodeStrings.ipv4first
import typings.node.nodeStrings.ipv6first
import typings.node.nodeStrings.verbatim
import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait LookupOptions extends StObject {
  
  /**
    * When `true`, the callback returns all resolved addresses in an array. Otherwise, returns a single address.
    * @default false
    */
  var all: js.UndefOr[Boolean] = js.undefined
  
  /**
    * The record family. Must be `4`, `6`, or `0`. For backward compatibility reasons, `'IPv4'` and `'IPv6'` are interpreted
    * as `4` and `6` respectively. The value 0 indicates that either an IPv4 or IPv6 address is returned. If the value `0` is used
    * with `{ all: true } (see below)`, both IPv4 and IPv6 addresses are returned.
    * @default 0
    */
  var family: js.UndefOr[Double | IPv4 | IPv6] = js.undefined
  
  /**
    * One or more [supported `getaddrinfo`](https://nodejs.org/docs/latest-v22.x/api/dns.html#supported-getaddrinfo-flags) flags. Multiple flags may be
    * passed by bitwise `OR`ing their values.
    */
  var hints: js.UndefOr[Double] = js.undefined
  
  /**
    * When `verbatim`, the resolved addresses are return unsorted. When `ipv4first`, the resolved addresses are sorted
    * by placing IPv4 addresses before IPv6 addresses. When `ipv6first`, the resolved addresses are sorted by placing IPv6
    * addresses before IPv4 addresses. Default value is configurable using
    * {@link setDefaultResultOrder} or [`--dns-result-order`](https://nodejs.org/docs/latest-v22.x/api/cli.html#--dns-result-orderorder).
    * @default `verbatim` (addresses are not reordered)
    * @since v22.1.0
    */
  var order: js.UndefOr[ipv4first | ipv6first | verbatim] = js.undefined
  
  /**
    * When `true`, the callback receives IPv4 and IPv6 addresses in the order the DNS resolver returned them. When `false`, IPv4
    * addresses are placed before IPv6 addresses. This option will be deprecated in favor of `order`. When both are specified,
    * `order` has higher precedence. New code should only use `order`. Default value is configurable using {@link setDefaultResultOrder}
    * @default true (addresses are not reordered)
    * @deprecated Please use `order` option
    */
  var verbatim: js.UndefOr[Boolean] = js.undefined
}
object LookupOptions {
  
  inline def apply(): LookupOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[LookupOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: LookupOptions] (val x: Self) extends AnyVal {
    
    inline def setAll(value: Boolean): Self = StObject.set(x, "all", value.asInstanceOf[js.Any])
    
    inline def setAllUndefined: Self = StObject.set(x, "all", js.undefined)
    
    inline def setFamily(value: Double | IPv4 | IPv6): Self = StObject.set(x, "family", value.asInstanceOf[js.Any])
    
    inline def setFamilyUndefined: Self = StObject.set(x, "family", js.undefined)
    
    inline def setHints(value: Double): Self = StObject.set(x, "hints", value.asInstanceOf[js.Any])
    
    inline def setHintsUndefined: Self = StObject.set(x, "hints", js.undefined)
    
    inline def setOrder(value: ipv4first | ipv6first | verbatim): Self = StObject.set(x, "order", value.asInstanceOf[js.Any])
    
    inline def setOrderUndefined: Self = StObject.set(x, "order", js.undefined)
    
    inline def setVerbatim(value: Boolean): Self = StObject.set(x, "verbatim", value.asInstanceOf[js.Any])
    
    inline def setVerbatimUndefined: Self = StObject.set(x, "verbatim", js.undefined)
  }
}
