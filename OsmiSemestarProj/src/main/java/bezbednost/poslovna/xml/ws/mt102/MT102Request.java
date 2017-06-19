
package bezbednost.poslovna.xml.ws.mt102;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import bezbednost.poslovna.xml.ws.mt103.TSWIFTIRacun;


/**
 * <p>Java class for MT102Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MT102Request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDPoruke" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIDPoruke"/>
 *         &lt;element name="BankaDuznika" type="{http://xml.poslovna.bezbednost/ws/MT103}TSWIFTIRacun"/>
 *         &lt;element name="BankaPoverioca" type="{http://xml.poslovna.bezbednost/ws/MT103}TSWIFTIRacun"/>
 *         &lt;element name="UkupanIznos" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIznos"/>
 *         &lt;element name="SifraValute" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOznakaValute"/>
 *         &lt;element name="DatumValute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="PojedinacnoPlacanje" type="{http://xml.poslovna.bezbednost/ws/MT102}TPojedinacnoPlacanje" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MT102Request", propOrder = {
    "idPoruke",
    "bankaDuznika",
    "bankaPoverioca",
    "ukupanIznos",
    "sifraValute",
    "datumValute",
    "datum",
    "pojedinacnoPlacanje"
})
public class MT102Request {

    @XmlElement(name = "IDPoruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "BankaDuznika", required = true)
    protected TSWIFTIRacun bankaDuznika;
    @XmlElement(name = "BankaPoverioca", required = true)
    protected TSWIFTIRacun bankaPoverioca;
    @XmlElement(name = "UkupanIznos", required = true)
    protected BigDecimal ukupanIznos;
    @XmlElement(name = "SifraValute", required = true)
    protected String sifraValute;
    @XmlElement(name = "DatumValute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "Datum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "PojedinacnoPlacanje", required = true)
    protected List<TPojedinacnoPlacanje> pojedinacnoPlacanje;

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
     * Gets the value of the ukupanIznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Sets the value of the ukupanIznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupanIznos(BigDecimal value) {
        this.ukupanIznos = value;
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

    /**
     * Gets the value of the datumValute property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumValute() {
        return datumValute;
    }

    /**
     * Sets the value of the datumValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumValute(XMLGregorianCalendar value) {
        this.datumValute = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
    }

    /**
     * Gets the value of the pojedinacnoPlacanje property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pojedinacnoPlacanje property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPojedinacnoPlacanje().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TPojedinacnoPlacanje }
     * 
     * 
     */
    public List<TPojedinacnoPlacanje> getPojedinacnoPlacanje() {
        if (pojedinacnoPlacanje == null) {
            pojedinacnoPlacanje = new ArrayList<TPojedinacnoPlacanje>();
        }
        return this.pojedinacnoPlacanje;
    }

}
