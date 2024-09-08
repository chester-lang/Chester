package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PaymentOptions extends StObject {
  
  /* standard dom */
  var requestBillingAddress: js.UndefOr[scala.Boolean] = js.undefined
  
  /* standard dom */
  var requestPayerEmail: js.UndefOr[scala.Boolean] = js.undefined
  
  /* standard dom */
  var requestPayerName: js.UndefOr[scala.Boolean] = js.undefined
  
  /* standard dom */
  var requestPayerPhone: js.UndefOr[scala.Boolean] = js.undefined
  
  /* standard dom */
  var requestShipping: js.UndefOr[scala.Boolean] = js.undefined
  
  /* standard dom */
  var shippingType: js.UndefOr[PaymentShippingType] = js.undefined
}
object PaymentOptions {
  
  inline def apply(): PaymentOptions = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[PaymentOptions]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PaymentOptions] (val x: Self) extends AnyVal {
    
    inline def setRequestBillingAddress(value: scala.Boolean): Self = StObject.set(x, "requestBillingAddress", value.asInstanceOf[js.Any])
    
    inline def setRequestBillingAddressUndefined: Self = StObject.set(x, "requestBillingAddress", js.undefined)
    
    inline def setRequestPayerEmail(value: scala.Boolean): Self = StObject.set(x, "requestPayerEmail", value.asInstanceOf[js.Any])
    
    inline def setRequestPayerEmailUndefined: Self = StObject.set(x, "requestPayerEmail", js.undefined)
    
    inline def setRequestPayerName(value: scala.Boolean): Self = StObject.set(x, "requestPayerName", value.asInstanceOf[js.Any])
    
    inline def setRequestPayerNameUndefined: Self = StObject.set(x, "requestPayerName", js.undefined)
    
    inline def setRequestPayerPhone(value: scala.Boolean): Self = StObject.set(x, "requestPayerPhone", value.asInstanceOf[js.Any])
    
    inline def setRequestPayerPhoneUndefined: Self = StObject.set(x, "requestPayerPhone", js.undefined)
    
    inline def setRequestShipping(value: scala.Boolean): Self = StObject.set(x, "requestShipping", value.asInstanceOf[js.Any])
    
    inline def setRequestShippingUndefined: Self = StObject.set(x, "requestShipping", js.undefined)
    
    inline def setShippingType(value: PaymentShippingType): Self = StObject.set(x, "shippingType", value.asInstanceOf[js.Any])
    
    inline def setShippingTypeUndefined: Self = StObject.set(x, "shippingType", js.undefined)
  }
}
