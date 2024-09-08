package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PaymentRequestEventMap extends StObject {
  
  /* standard dom */
  var paymentmethodchange: org.scalajs.dom.Event
  
  /* standard dom */
  var shippingaddresschange: org.scalajs.dom.Event
  
  /* standard dom */
  var shippingoptionchange: org.scalajs.dom.Event
}
object PaymentRequestEventMap {
  
  inline def apply(
    paymentmethodchange: org.scalajs.dom.Event,
    shippingaddresschange: org.scalajs.dom.Event,
    shippingoptionchange: org.scalajs.dom.Event
  ): PaymentRequestEventMap = {
    val __obj = js.Dynamic.literal(paymentmethodchange = paymentmethodchange.asInstanceOf[js.Any], shippingaddresschange = shippingaddresschange.asInstanceOf[js.Any], shippingoptionchange = shippingoptionchange.asInstanceOf[js.Any])
    __obj.asInstanceOf[PaymentRequestEventMap]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PaymentRequestEventMap] (val x: Self) extends AnyVal {
    
    inline def setPaymentmethodchange(value: org.scalajs.dom.Event): Self = StObject.set(x, "paymentmethodchange", value.asInstanceOf[js.Any])
    
    inline def setShippingaddresschange(value: org.scalajs.dom.Event): Self = StObject.set(x, "shippingaddresschange", value.asInstanceOf[js.Any])
    
    inline def setShippingoptionchange(value: org.scalajs.dom.Event): Self = StObject.set(x, "shippingoptionchange", value.asInstanceOf[js.Any])
  }
}
