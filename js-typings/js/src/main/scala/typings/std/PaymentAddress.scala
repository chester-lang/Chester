package typings.std

import org.scalablytyped.runtime.StObject
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobalScope, JSGlobal, JSImport, JSName, JSBracketAccess}

/** This Payment Request API interface is used to store shipping or payment address information. */
trait PaymentAddress extends StObject {
  
  /* standard dom */
  val addressLine: js.Array[java.lang.String]
  
  /* standard dom */
  val city: java.lang.String
  
  /* standard dom */
  val country: java.lang.String
  
  /* standard dom */
  val dependentLocality: java.lang.String
  
  /* standard dom */
  val organization: java.lang.String
  
  /* standard dom */
  val phone: java.lang.String
  
  /* standard dom */
  val postalCode: java.lang.String
  
  /* standard dom */
  val recipient: java.lang.String
  
  /* standard dom */
  val region: java.lang.String
  
  /* standard dom */
  val sortingCode: java.lang.String
  
  /* standard dom */
  def toJSON(): Any
}
object PaymentAddress {
  
  inline def apply(
    addressLine: js.Array[java.lang.String],
    city: java.lang.String,
    country: java.lang.String,
    dependentLocality: java.lang.String,
    organization: java.lang.String,
    phone: java.lang.String,
    postalCode: java.lang.String,
    recipient: java.lang.String,
    region: java.lang.String,
    sortingCode: java.lang.String,
    toJSON: () => Any
  ): PaymentAddress = {
    val __obj = js.Dynamic.literal(addressLine = addressLine.asInstanceOf[js.Any], city = city.asInstanceOf[js.Any], country = country.asInstanceOf[js.Any], dependentLocality = dependentLocality.asInstanceOf[js.Any], organization = organization.asInstanceOf[js.Any], phone = phone.asInstanceOf[js.Any], postalCode = postalCode.asInstanceOf[js.Any], recipient = recipient.asInstanceOf[js.Any], region = region.asInstanceOf[js.Any], sortingCode = sortingCode.asInstanceOf[js.Any], toJSON = js.Any.fromFunction0(toJSON))
    __obj.asInstanceOf[PaymentAddress]
  }
  
  @scala.inline
  implicit open class MutableBuilder[Self <: PaymentAddress] (val x: Self) extends AnyVal {
    
    inline def setAddressLine(value: js.Array[java.lang.String]): Self = StObject.set(x, "addressLine", value.asInstanceOf[js.Any])
    
    inline def setAddressLineVarargs(value: java.lang.String*): Self = StObject.set(x, "addressLine", js.Array(value*))
    
    inline def setCity(value: java.lang.String): Self = StObject.set(x, "city", value.asInstanceOf[js.Any])
    
    inline def setCountry(value: java.lang.String): Self = StObject.set(x, "country", value.asInstanceOf[js.Any])
    
    inline def setDependentLocality(value: java.lang.String): Self = StObject.set(x, "dependentLocality", value.asInstanceOf[js.Any])
    
    inline def setOrganization(value: java.lang.String): Self = StObject.set(x, "organization", value.asInstanceOf[js.Any])
    
    inline def setPhone(value: java.lang.String): Self = StObject.set(x, "phone", value.asInstanceOf[js.Any])
    
    inline def setPostalCode(value: java.lang.String): Self = StObject.set(x, "postalCode", value.asInstanceOf[js.Any])
    
    inline def setRecipient(value: java.lang.String): Self = StObject.set(x, "recipient", value.asInstanceOf[js.Any])
    
    inline def setRegion(value: java.lang.String): Self = StObject.set(x, "region", value.asInstanceOf[js.Any])
    
    inline def setSortingCode(value: java.lang.String): Self = StObject.set(x, "sortingCode", value.asInstanceOf[js.Any])
    
    inline def setToJSON(value: () => Any): Self = StObject.set(x, "toJSON", js.Any.fromFunction0(value))
  }
}
