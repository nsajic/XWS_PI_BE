

package bezbednost.poslovna.xml.ws.mt103;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import bezbednost.poslovna.xml.ws.nalogzaprenos.TNalog;


/**
 * <p>Java class for MT103Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MT103Request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDPoruke" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIDPoruke"/>
 *         &lt;element name="BankaDuznika" type="{http://xml.poslovna.bezbednost/ws/MT103}TSWIFTIRacun"/>
 *         &lt;element name="BankaPoverioca" type="{http://xml.poslovna.bezbednost/ws/MT103}TSWIFTIRacun"/>
 *         &lt;element name="Nalog" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TNalog"/>
 *         &lt;element name="SifraValute" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOznakaValute"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT103Request", propOrder = {
    "idPoruke",
    "bankaDuznika",
    "bankaPoverioca",
    "nalog",
    "sifraValute"
})
public class MT103Request {

    @XmlElement(name = "IDPoruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "BankaDuznika", required = true)
    protected TSWIFTIRacun bankaDuznika;
    @XmlElement(name = "BankaPoverioca", required = true)
    protected TSWIFTIRacun bankaPoverioca;
    @XmlElement(name = "Nalog", required = true)
    protected TNalog nalog;
    @XmlElement(name = "SifraValute", required = true)
    protected String sifraValute;

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
     * Gets the value of the bankaDuznika property.
     * 
     * @return
     *     possible object is
     *     {@link TSWIFTIRacun }
     *     
     */
    public TSWIFTIRacun getBankaDuznika() {
        return bankaDuznika;
    }

    /**
     * Sets the value of the bankaDuznika property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSWIFTIRacun }
     *     
     */
    public void setBankaDuznika(TSWIFTIRacun value) {
        this.bankaDuznika = value;
    }

    /**
     * Gets the value of the bankaPoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link TSWIFTIRacun }
     *     
     */
    public TSWIFTIRacun getBankaPoverioca() {
        return bankaPoverioca;
    }

    /**
     * Sets the value of the bankaPoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSWIFTIRacun }
     *     
     */
    public void setBankaPoverioca(TSWIFTIRacun value) {
        this.bankaPoverioca = value;
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
     * Gets the value of the sifraValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraValute() {
        return sifraValute;
    }

    /**
     * Sets the value of the sifraValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraValute(String value) {
        this.sifraValute = value;
    }

}
