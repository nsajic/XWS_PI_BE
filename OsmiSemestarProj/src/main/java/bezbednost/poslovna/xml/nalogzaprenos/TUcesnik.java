
package bezbednost.poslovna.xml.nalogzaprenos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TUcesnik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TUcesnik">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Lice" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TPravno_lice"/>
 *         &lt;element name="newElement" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TUcesnik", propOrder = {
    "lice",
    "newElement"
})
public class TUcesnik {

    @XmlElement(name = "Lice", required = true)
    protected TPravnoLice lice;
    @XmlElement(required = true)
    protected Object newElement;

    /**
     * Gets the value of the lice property.
     * 
     * @return
     *     possible object is
     *     {@link TPravnoLice }
     *     
     */
    public TPravnoLice getLice() {
        return lice;
    }

    /**
     * Sets the value of the lice property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPravnoLice }
     *     
     */
    public void setLice(TPravnoLice value) {
        this.lice = value;
    }

    /**
     * Gets the value of the newElement property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getNewElement() {
        return newElement;
    }

    /**
     * Sets the value of the newElement property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setNewElement(Object value) {
        this.newElement = value;
    }

}
