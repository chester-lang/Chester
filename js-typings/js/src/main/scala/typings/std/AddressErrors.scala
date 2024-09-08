package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

trait AddressErrors extends StObject {
  
  /* standard dom */
  var addressLine: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var city: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var country: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var dependentLocality: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var organization: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var phone: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var postalCode: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var recipient: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var region: js.UndefOr[java.lang.String] = js.undefined
  
  /* standard dom */
  var sortingCode: js.UndefOr[java.lang.String] = js.undefined
}
object AddressErrors {
  
  inline def apply(): AddressErrors = {
    val __obj = js.Dynamic.literal()
    __obj.asInstanceOf[AddressErrors]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: AddressErrors] (val x: Self) extends AnyVal {
    
    inline def setAddressLine(value: java.lang.String): Self = StObject.set(x, "addressLine", value.asInstanceOf[js.Any])
    
    inline def setAddressLineUndefined: Self = StObject.set(x, "addressLine", js.undefined)
    
    inline def setCity(value: java.lang.String): Self = StObject.set(x, "city", value.asInstanceOf[js.Any])
    
    inline def setCityUndefined: Self = StObject.set(x, "city", js.undefined)
    
    inline def setCountry(value: java.lang.String): Self = StObject.set(x, "country", value.asInstanceOf[js.Any])
    
    inline def setCountryUndefined: Self = StObject.set(x, "country", js.undefined)
    
    inline def setDependentLocality(value: java.lang.String): Self = StObject.set(x, "dependentLocality", value.asInstanceOf[js.Any])
    
    inline def setDependentLocalityUndefined: Self = StObject.set(x, "dependentLocality", js.undefined)
    
    inline def setOrganization(value: java.lang.String): Self = StObject.set(x, "organization", value.asInstanceOf[js.Any])
    
    inline def setOrganizationUndefined: Self = StObject.set(x, "organization", js.undefined)
    
    inline def setPhone(value: java.lang.String): Self = StObject.set(x, "phone", value.asInstanceOf[js.Any])
    
    inline def setPhoneUndefined: Self = StObject.set(x, "phone", js.undefined)
    
    inline def setPostalCode(value: java.lang.String): Self = StObject.set(x, "postalCode", value.asInstanceOf[js.Any])
    
    inline def setPostalCodeUndefined: Self = StObject.set(x, "postalCode", js.undefined)
    
    inline def setRecipient(value: java.lang.String): Self = StObject.set(x, "recipient", value.asInstanceOf[js.Any])
    
    inline def setRecipientUndefined: Self = StObject.set(x, "recipient", js.undefined)
    
    inline def setRegion(value: java.lang.String): Self = StObject.set(x, "region", value.asInstanceOf[js.Any])
    
    inline def setRegionUndefined: Self = StObject.set(x, "region", js.undefined)
    
    inline def setSortingCode(value: java.lang.String): Self = StObject.set(x, "sortingCode", value.asInstanceOf[js.Any])
    
    inline def setSortingCodeUndefined: Self = StObject.set(x, "sortingCode", js.undefined)
  }
}
