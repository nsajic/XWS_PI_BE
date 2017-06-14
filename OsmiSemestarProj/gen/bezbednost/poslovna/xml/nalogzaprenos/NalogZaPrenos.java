
package bezbednost.poslovna.xml.nalogzaprenos;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID_poruke" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TID_poruke"/>
 *         &lt;element name="Duznik" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
 *         &lt;element name="Svrha_placanja" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
 *         &lt;element name="Primalac" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
 *         &lt;element name="Datum_naloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Duznik_racun" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TPodaci_o_racunu"/>
 *         &lt;element name="Poverilac_racun" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TPodaci_o_racunu"/>
 *         &lt;element name="Iznos" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TIznos"/>
 *         &lt;element name="Oznaka_valute" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOznaka_valute"/>
 *         &lt;element name="Hitno" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idPoruke",
    "duznik",
    "svrhaPlacanja",
    "primalac",
    "datumNaloga",
    "datumValute",
    "duznikRacun",
    "poverilacRacun",
    "iznos",
    "oznakaValute",
    "hitno"
})
@XmlRootElement(name = "Nalog_za_prenos")
public class NalogZaPrenos {

    @XmlElement(name = "ID_poruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "Duznik", required = true)
    protected String duznik;
    @XmlElement(name = "Svrha_placanja", required = true)
    protected String svrhaPlacanja;
    @XmlElement(name = "Primalac", required = true)
    protected String primalac;
    @XmlElement(name = "Datum_naloga", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(name = "Datum_valute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "Duznik_racun", required = true)
    protected TPodaciORacunu duznikRacun;
    @XmlElement(name = "Poverilac_racun", required = true)
    protected TPodaciORacunu poverilacRacun;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
    @XmlElement(name = "Oznaka_valute", required = true)
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
     * Gets the value of the duznik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuznik() {
        return duznik;
    }

    /**
     * Sets the value of the duznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuznik(String value) {
        this.duznik = value;
    }

    /**
     * Gets the value of the svrhaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvrhaPlacanja() {
        return svrhaPlacanja;
    }

    /**
     * Sets the value of the svrhaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvrhaPlacanja(String value) {
        this.svrhaPlacanja = value;
    }

    /**
     * Gets the value of the primalac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimalac(String value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the datumNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumNaloga() {
        return datumNaloga;
    }

    /**
     * Sets the value of the datumNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumNaloga(XMLGregorianCalendar value) {
        this.datumNaloga = value;
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
     * Gets the value of the duznikRacun property.
     * 
     * @return
     *     possible object is
     *     {@link TPodaciORacunu }
     *     
     */
    public TPodaciORacunu getDuznikRacun() {
        return duznikRacun;
    }

    /**
     * Sets the value of the duznikRacun property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPodaciORacunu }
     *     
     */
    public void setDuznikRacun(TPodaciORacunu value) {
        this.duznikRacun = value;
    }

    /**
     * Gets the value of the poverilacRacun property.
     * 
     * @return
     *     possible object is
     *     {@link TPodaciORacunu }
     *     
     */
    public TPodaciORacunu getPoverilacRacun() {
        return poverilacRacun;
    }

    /**
     * Sets the value of the poverilacRacun property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPodaciORacunu }
     *     
     */
    public void setPoverilacRacun(TPodaciORacunu value) {
        this.poverilacRacun = value;
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

}
