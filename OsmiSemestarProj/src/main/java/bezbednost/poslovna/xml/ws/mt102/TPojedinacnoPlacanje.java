
package bezbednost.poslovna.xml.ws.mt102;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import bezbednost.poslovna.xml.ws.nalogzaprenos.TPodaciORacunu;


/**
 * <p>Java class for TPojedinacnoPlacanje complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPojedinacnoPlacanje">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDNalogaZaPlacanje" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIDPoruke"/>
 *         &lt;element name="Duznik" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOpis"/>
 *         &lt;element name="SvrhaPlacanja" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOpis"/>
 *         &lt;element name="Primalac" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOpis"/>
 *         &lt;element name="DatumNaloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DuznikRacun" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TPodaciORacunu"/>
 *         &lt;element name="PoverilacRacun" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TPodaciORacunu"/>
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
@XmlType(name = "TPojedinacnoPlacanje", propOrder = {
    "idNalogaZaPlacanje",
    "duznik",
    "svrhaPlacanja",
    "primalac",
    "datumNaloga",
    "duznikRacun",
    "poverilacRacun",
    "iznos",
    "sifraValute"
})
public class TPojedinacnoPlacanje {

    @XmlElement(name = "IDNalogaZaPlacanje", required = true)
    protected String idNalogaZaPlacanje;
    @XmlElement(name = "Duznik", required = true)
    protected String duznik;
    @XmlElement(name = "SvrhaPlacanja", required = true)
    protected String svrhaPlacanja;
    @XmlElement(name = "Primalac", required = true)
    protected String primalac;
    @XmlElement(name = "DatumNaloga", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(name = "DuznikRacun", required = true)
    protected TPodaciORacunu duznikRacun;
    @XmlElement(name = "PoverilacRacun", required = true)
    protected TPodaciORacunu poverilacRacun;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
    @XmlElement(name = "SifraValute", required = true)
    protected String sifraValute;

    /**
     * Gets the value of the idNalogaZaPlacanje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDNalogaZaPlacanje() {
        return idNalogaZaPlacanje;
    }

    /**
     * Sets the value of the idNalogaZaPlacanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDNalogaZaPlacanje(String value) {
        this.idNalogaZaPlacanje = value;
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
