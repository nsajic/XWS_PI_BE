
package bezbednost.poslovna.xml.presekizvoda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import bezbednost.poslovna.xml.nalogzaprenos.TPodaciORacunu;


/**
 * <p>Java class for PresekIzvodaRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PresekIzvodaRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Zaglavlje_preseka">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Zahtev" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TZahtev"/>
 *                   &lt;element name="Prethodno_stanje" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TStanje_ili_suma"/>
 *                   &lt;element name="Broj_promena_u_korist" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TBroj_promena"/>
 *                   &lt;element name="Ukupno_u_korist" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TStanje_ili_suma"/>
 *                   &lt;element name="Broj_promena_na_teret" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TBroj_promena"/>
 *                   &lt;element name="Ukupno_na_teret" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TStanje_ili_suma"/>
 *                   &lt;element name="Novo_stanje" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TStanje_ili_suma"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Stavka_preseka" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Duznik" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
 *                   &lt;element name="Svrha_placanja" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
 *                   &lt;element name="Primalac" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
 *                   &lt;element name="Datum_nalog" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="Duznik_racun" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TPodaci_o_racunu"/>
 *                   &lt;element name="Poverilac_racun" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TPodaci_o_racunu"/>
 *                   &lt;element name="Iznos" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TIznos"/>
 *                   &lt;element name="Smer" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TSmer"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PresekIzvodaRequest", propOrder = {
    "zaglavljePreseka",
    "stavkaPreseka"
})
public class PresekIzvodaRequest {

    @XmlElement(name = "Zaglavlje_preseka", required = true)
    protected PresekIzvodaRequest.ZaglavljePreseka zaglavljePreseka;
    @XmlElement(name = "Stavka_preseka", required = true)
    protected List<PresekIzvodaRequest.StavkaPreseka> stavkaPreseka;

    /**
     * Gets the value of the zaglavljePreseka property.
     * 
     * @return
     *     possible object is
     *     {@link PresekIzvodaRequest.ZaglavljePreseka }
     *     
     */
    public PresekIzvodaRequest.ZaglavljePreseka getZaglavljePreseka() {
        return zaglavljePreseka;
    }

    /**
     * Sets the value of the zaglavljePreseka property.
     * 
     * @param value
     *     allowed object is
     *     {@link PresekIzvodaRequest.ZaglavljePreseka }
     *     
     */
    public void setZaglavljePreseka(PresekIzvodaRequest.ZaglavljePreseka value) {
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
     * {@link PresekIzvodaRequest.StavkaPreseka }
     * 
     * 
     */
    public List<PresekIzvodaRequest.StavkaPreseka> getStavkaPreseka() {
        if (stavkaPreseka == null) {
            stavkaPreseka = new ArrayList<PresekIzvodaRequest.StavkaPreseka>();
        }
        return this.stavkaPreseka;
    }


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
     *         &lt;element name="Duznik" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
     *         &lt;element name="Svrha_placanja" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
     *         &lt;element name="Primalac" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TOpis"/>
     *         &lt;element name="Datum_nalog" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="Duznik_racun" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TPodaci_o_racunu"/>
     *         &lt;element name="Poverilac_racun" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TPodaci_o_racunu"/>
     *         &lt;element name="Iznos" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TIznos"/>
     *         &lt;element name="Smer" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TSmer"/>
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
        "duznik",
        "svrhaPlacanja",
        "primalac",
        "datumNalog",
        "datumValute",
        "duznikRacun",
        "poverilacRacun",
        "iznos",
        "smer"
    })
    public static class StavkaPreseka {

        @XmlElement(name = "Duznik", required = true)
        protected String duznik;
        @XmlElement(name = "Svrha_placanja", required = true)
        protected String svrhaPlacanja;
        @XmlElement(name = "Primalac", required = true)
        protected String primalac;
        @XmlElement(name = "Datum_nalog", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datumNalog;
        @XmlElement(name = "Datum_valute", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datumValute;
        @XmlElement(name = "Duznik_racun", required = true)
        protected TPodaciORacunu duznikRacun;
        @XmlElement(name = "Poverilac_racun", required = true)
        protected TPodaciORacunu poverilacRacun;
        @XmlElement(name = "Iznos", required = true)
        protected BigDecimal iznos;
        @XmlElement(name = "Smer", required = true)
        protected String smer;

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
         * Gets the value of the datumNalog property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDatumNalog() {
            return datumNalog;
        }

        /**
         * Sets the value of the datumNalog property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDatumNalog(XMLGregorianCalendar value) {
            this.datumNalog = value;
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
         * Gets the value of the smer property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSmer() {
            return smer;
        }

        /**
         * Sets the value of the smer property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSmer(String value) {
            this.smer = value;
        }

    }


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
     *         &lt;element name="Zahtev" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TZahtev"/>
     *         &lt;element name="Prethodno_stanje" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TStanje_ili_suma"/>
     *         &lt;element name="Broj_promena_u_korist" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TBroj_promena"/>
     *         &lt;element name="Ukupno_u_korist" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TStanje_ili_suma"/>
     *         &lt;element name="Broj_promena_na_teret" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TBroj_promena"/>
     *         &lt;element name="Ukupno_na_teret" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TStanje_ili_suma"/>
     *         &lt;element name="Novo_stanje" type="{http://xml.poslovna.bezbednost/PresekIzvoda}TStanje_ili_suma"/>
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
        "zahtev",
        "prethodnoStanje",
        "brojPromenaUKorist",
        "ukupnoUKorist",
        "brojPromenaNaTeret",
        "ukupnoNaTeret",
        "novoStanje"
    })
    public static class ZaglavljePreseka {

        @XmlElement(name = "Zahtev", required = true)
        protected TZahtev zahtev;
        @XmlElement(name = "Prethodno_stanje", required = true)
        protected BigDecimal prethodnoStanje;
        @XmlElement(name = "Broj_promena_u_korist")
        protected int brojPromenaUKorist;
        @XmlElement(name = "Ukupno_u_korist", required = true)
        protected BigDecimal ukupnoUKorist;
        @XmlElement(name = "Broj_promena_na_teret")
        protected int brojPromenaNaTeret;
        @XmlElement(name = "Ukupno_na_teret", required = true)
        protected BigDecimal ukupnoNaTeret;
        @XmlElement(name = "Novo_stanje", required = true)
        protected BigDecimal novoStanje;

        /**
         * Gets the value of the zahtev property.
         * 
         * @return
         *     possible object is
         *     {@link TZahtev }
         *     
         */
        public TZahtev getZahtev() {
            return zahtev;
        }

        /**
         * Sets the value of the zahtev property.
         * 
         * @param value
         *     allowed object is
         *     {@link TZahtev }
         *     
         */
        public void setZahtev(TZahtev value) {
            this.zahtev = value;
        }

        /**
         * Gets the value of the prethodnoStanje property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getPrethodnoStanje() {
            return prethodnoStanje;
        }

        /**
         * Sets the value of the prethodnoStanje property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setPrethodnoStanje(BigDecimal value) {
            this.prethodnoStanje = value;
        }

        /**
         * Gets the value of the brojPromenaUKorist property.
         * 
         */
        public int getBrojPromenaUKorist() {
            return brojPromenaUKorist;
        }

        /**
         * Sets the value of the brojPromenaUKorist property.
         * 
         */
        public void setBrojPromenaUKorist(int value) {
            this.brojPromenaUKorist = value;
        }

        /**
         * Gets the value of the ukupnoUKorist property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUkupnoUKorist() {
            return ukupnoUKorist;
        }

        /**
         * Sets the value of the ukupnoUKorist property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUkupnoUKorist(BigDecimal value) {
            this.ukupnoUKorist = value;
        }

        /**
         * Gets the value of the brojPromenaNaTeret property.
         * 
         */
        public int getBrojPromenaNaTeret() {
            return brojPromenaNaTeret;
        }

        /**
         * Sets the value of the brojPromenaNaTeret property.
         * 
         */
        public void setBrojPromenaNaTeret(int value) {
            this.brojPromenaNaTeret = value;
        }

        /**
         * Gets the value of the ukupnoNaTeret property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUkupnoNaTeret() {
            return ukupnoNaTeret;
        }

        /**
         * Sets the value of the ukupnoNaTeret property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUkupnoNaTeret(BigDecimal value) {
            this.ukupnoNaTeret = value;
        }

        /**
         * Gets the value of the novoStanje property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getNovoStanje() {
            return novoStanje;
        }

        /**
         * Sets the value of the novoStanje property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setNovoStanje(BigDecimal value) {
            this.novoStanje = value;
        }

    }

}
