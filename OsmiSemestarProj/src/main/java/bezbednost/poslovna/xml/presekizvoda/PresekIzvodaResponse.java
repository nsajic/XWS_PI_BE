
package bezbednost.poslovna.xml.presekizvoda;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PresekIzvodaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PresekIzvodaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="odgovor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PresekIzvodaResponse", propOrder = {
    "odgovor"
})
public class PresekIzvodaResponse {

    @XmlElement(required = true)
    protected String odgovor;

    /**
     * Gets the value of the odgovor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOdgovor() {
        return odgovor;
    }

    /**
     * Sets the value of the odgovor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOdgovor(String value) {
        this.odgovor = value;
    }

}
