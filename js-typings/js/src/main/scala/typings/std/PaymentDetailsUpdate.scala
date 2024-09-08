package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait PaymentDetailsUpdate
  extends StObject
     with PaymentDetailsBase {
  
  /* standard dom */
  var error: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var payerErrors: js.UndefOr[PayerErrors] = js.undefined
  
  /* standard dom */
  var paymentMethodErrors: js.UndefOr[Any] = js.undefined
  
  /* standard dom */
  var shippingAddressErrors: js.UndefOr[AddressErrors] = js.undefined
  
  /* standard dom */
  var total: js.UndefOr[PaymentItem] = js.undefined
}
object PaymentDetailsUpdate {
  
  inline def apply(): PaymentDetailsUpdate = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[PaymentDetailsUpdate]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PaymentDetailsUpdate] (val x: Self) extends AnyVal {
    
    inline def setError(value: java.lang.String): Self = StObject.set(x, "error", value.asInstanceOf[js.Any])
    
    inline def setErrorUndefined: Self = StObject.set(x, "error", js.undefined)
    
    inline def setPayerErrors(value: PayerErrors): Self = StObject.set(x, "payerErrors", value.asInstanceOf[js.Any])
    
    inline def setPayerErrorsUndefined: Self = StObject.set(x, "payerErrors", js.undefined)
    
    inline def setPaymentMethodErrors(value: Any): Self = StObject.set(x, "paymentMethodErrors", value.asInstanceOf[js.Any])
    
    inline def setPaymentMethodErrorsUndefined: Self = StObject.set(x, "paymentMethodErrors", js.undefined)
    
    inline def setShippingAddressErrors(value: AddressErrors): Self = StObject.set(x, "shippingAddressErrors", value.asInstanceOf[js.Any])
    
    inline def setShippingAddressErrorsUndefined: Self = StObject.set(x, "shippingAddressErrors", js.undefined)
    
    inline def setTotal(value: PaymentItem): Self = StObject.set(x, "total", value.asInstanceOf[js.Any])
    
    inline def setTotalUndefined: Self = StObject.set(x, "total", js.undefined)
  }
}
