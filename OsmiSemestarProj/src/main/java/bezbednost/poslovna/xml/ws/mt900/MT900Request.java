//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.19 at 12:17:06 PM CEST 
//


package bezbednost.poslovna.xml.ws.mt900;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import bezbednost.poslovna.xml.ws.mt103.TSWIFTIRacun;


/**
 * <p>Java class for MT900Request complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MT900Request">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDPoruke" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIDPoruke"/>
 *         &lt;element name="BankaDuznika" type="{http://xml.poslovna.bezbednost/ws/MT103}TSWIFTIRacun"/>
 *         &lt;element name="IDPorukeNaloga" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIDPoruke"/>
 *         &lt;element name="DatumValute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Iznos" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIznos"/>
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
@XmlType(name = "MT900Request", propOrder = {
    "idPoruke",
    "bankaDuznika",
    "idPorukeNaloga",
    "datumValute",
    "iznos",
    "sifraValute"
})
public class MT900Request {

    @XmlElement(name = "IDPoruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "BankaDuznika", required = true)
    protected TSWIFTIRacun bankaDuznika;
    @XmlElement(name = "IDPorukeNaloga", required = true)
    protected String idPorukeNaloga;
    @XmlElement(name = "DatumValute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
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
     * Gets the value of the idPorukeNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPorukeNaloga() {
        return idPorukeNaloga;
    }

    /**
     * Sets the value of the idPorukeNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPorukeNaloga(String value) {
        this.idPorukeNaloga = value;
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
     * Gets the value of the iznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznos() {
        return iznos;
    }

    /**
     * Sets the value of the iznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznos(BigDecimal value) {
        this.iznos = value;
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
