

package bezbednost.poslovna.xml.ws.izvod;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IzvodResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IzvodResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZaglavljePreseka" type="{http://xml.poslovna.bezbednost/ws/Izvod}TZaglavljePreseka"/>
 *         &lt;element name="StavkaPreseka" type="{http://xml.poslovna.bezbednost/ws/Izvod}TStavkaPreseka" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IzvodResponse", propOrder = {
    "zaglavljePreseka",
    "stavkaPreseka"
})
public class IzvodResponse {

    @XmlElement(name = "ZaglavljePreseka", required = true)
    protected TZaglavljePreseka zaglavljePreseka;
    @XmlElement(name = "StavkaPreseka", required = true)
    protected List<TStavkaPreseka> stavkaPreseka;

    /**
     * Gets the value of the zaglavljePreseka property.
     * 
     * @return
     *     possible object is
     *     {@link TZaglavljePreseka }
     *     
     */
    public TZaglavljePreseka getZaglavljePreseka() {
        return zaglavljePreseka;
    }

    /**
     * Sets the value of the zaglavljePreseka property.
     * 
     * @param value
     *     allowed object is
     *     {@link TZaglavljePreseka }
     *     
     */
    public void setZaglavljePreseka(TZaglavljePreseka value) {
        this.zaglavljePreseka = value;
    }

    /**
     * Gets the value of the stavkaPreseka property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stavkaPreseka property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStavkaPreseka().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TStavkaPreseka }
     * 
     * 
     */
    public List<TStavkaPreseka> getStavkaPreseka() {
        if (stavkaPreseka == null) {
            stavkaPreseka = new ArrayList<TStavkaPreseka>();
        }
        return this.stavkaPreseka;
    }

}
