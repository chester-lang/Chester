package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PaymentValidationErrors extends StObject {
  
  /* standard dom */
  var error: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var payer: js.UndefOr[PayerErrors] = js.undefined
  
  /* standard dom */
  var paymentMethod: js.UndefOr[Any] = js.undefined
  
  /* standard dom */
  var shippingAddress: js.UndefOr[AddressErrors] = js.undefined
}
object PaymentValidationErrors {
  
  inline def apply(): PaymentValidationErrors = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[PaymentValidationErrors]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PaymentValidationErrors] (val x: Self) extends AnyVal {
    
    inline def setError(value: java.lang.String): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setErrorUndefined: Self = StObject.set(x, "error", js.undefined)
    
    inline def setPayer(value: PayerErrors): Self = StObject.set(x, "payer", value.asInstanceOf[js.Any])
    
    inline def setPayerUndefined: Self = StObject.set(x, "payer", js.undefined)
    
    inline def setPaymentMethod(value: Any): Self = StObject.set(x, "paymentMethod", value.asInstanceOf[js.Any])
    
    inline def setPaymentMethodUndefined: Self = StObject.set(x, "paymentMethod", js.undefined)
    
    inline def setShippingAddress(value: AddressErrors): Self = StObject.set(x, "shippingAddress", value.asInstanceOf[js.Any])
    
    inline def setShippingAddressUndefined: Self = StObject.set(x, "shippingAddress", js.undefined)
  }
}
