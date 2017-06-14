
package bezbednost.poslovna.xml.nalogzaprenos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TPodaci_o_racunu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TPodaci_o_racunu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Racun" type="{http://xml.poslovna.bezbednost/nalogZaPrenos}TBroj_racuna"/>
 *         &lt;element name="Model">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="1"/>
 *               &lt;maxInclusive value="99"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Poziv_na_broj">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
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
@XmlType(name = "TPodaci_o_racunu", propOrder = {
    "racun",
    "model",
    "pozivNaBroj"
})
public class TPodaciORacunu {

    @XmlElement(name = "Racun", required = true)
    protected String racun;
    @XmlElement(name = "Model")
    protected int model;
    @XmlElement(name = "Poziv_na_broj", required = true)
    protected String pozivNaBroj;

    /**
     * Gets the value of the racun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRacun() {
        return racun;
    }

    /**
     * Sets the value of the racun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRacun(String value) {
        this.racun = value;
    }

    /**
     * Gets the value of the model property.
     * 
     */
    public int getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     */
    public void setModel(int value) {
        this.model = value;
    }

    /**
     * Gets the value of the pozivNaBroj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPozivNaBroj() {
        return pozivNaBroj;
    }

    /**
     * Sets the value of the pozivNaBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPozivNaBroj(String value) {
        this.pozivNaBroj = value;
    }

}
