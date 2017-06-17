//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.16 at 10:33:40 PM CEST 
//


package bezbednost.poslovna.xml.ws.nalogzaprenos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NalogZaPrenosRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NalogZaPrenosRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDPoruke" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIDPoruke"/>
 *         &lt;element name="Nalog" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TNalog"/>
 *         &lt;element name="OznakaValute" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOznakaValute"/>
 *         &lt;element name="Hitno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name="NalogZaPrenosRequest", namespace="http://nalogzaprenos.ws.xml.poslovna.bezbednost/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NalogZaPrenosRequest", propOrder = {
    "idPoruke",
    "nalog",
    "oznakaValute",
    "hitno"
}, namespace="http://nalogzaprenos.ws.xml.poslovna.bezbednost/")
public class NalogZaPrenosRequest {

    @XmlElement(name = "IDPoruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "Nalog", required = true)
    protected TNalog nalog;
    @XmlElement(name = "OznakaValute", required = true)
    protected String oznakaValute;
    @XmlElement(name = "Hitno")
    protected boolean hitno;

    /**
     * Gets the value of the idPoruke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPoruke() {
        return idPoruke;
    }

    /**
     * Sets the value of the idPoruke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPoruke(String value) {
        this.idPoruke = value;
    }

    /**
     * Gets the value of the nalog property.
     * 
     * @return
     *     possible object is
     *     {@link TNalog }
     *     
     */
    public TNalog getNalog() {
        return nalog;
    }

    /**
     * Sets the value of the nalog property.
     * 
     * @param value
     *     allowed object is
     *     {@link TNalog }
     *     
     */
    public void setNalog(TNalog value) {
        this.nalog = value;
    }

    /**
     * Gets the value of the oznakaValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOznakaValute() {
        return oznakaValute;
    }

    /**
     * Sets the value of the oznakaValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOznakaValute(String value) {
        this.oznakaValute = value;
    }

    /**
     * Gets the value of the hitno property.
     * 
     */
    public boolean isHitno() {
        return hitno;
    }

    /**
     * Sets the value of the hitno property.
     * 
     */
    public void setHitno(boolean value) {
        this.hitno = value;
    }

	@Override
	public String toString() {
		return "NalogZaPrenosRequest [idPoruke=" + idPoruke + ", nalog=" + nalog + ", oznakaValute=" + oznakaValute
				+ ", hitno=" + hitno + "]";
	}

}
