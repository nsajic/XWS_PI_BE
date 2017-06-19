
package bezbednost.poslovna.xml.ws.nalogzaprenos;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for TNalog complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="TNalog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Duznik" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOpis"/>
 *         &lt;element name="SvrhaPlacanja" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOpis"/>
 *         &lt;element name="Primalac" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TOpis"/>
 *         &lt;element name="DatumNaloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DatumValute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DuznikRacun" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TPodaciORacunu"/>
 *         &lt;element name="PoverilacRacun" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TPodaciORacunu"/>
 *         &lt;element name="Iznos" type="{http://xml.poslovna.bezbednost/ws/NalogZaPrenos}TIznos"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TNalog", propOrder = { "duznik", "svrhaPlacanja", "primalac", "datumNaloga", "datumValute",
		"duznikRacun", "poverilacRacun", "iznos" })
public class TNalog {

	@XmlElement(name = "Duznik", required = true)
	protected String duznik;
	@XmlElement(name = "SvrhaPlacanja", required = true)
	protected String svrhaPlacanja;
	@XmlElement(name = "Primalac", required = true)
	protected String primalac;
	@XmlElement(name = "DatumNaloga", required = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar datumNaloga;
	@XmlElement(name = "DatumValute", required = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar datumValute;
	@XmlElement(name = "DuznikRacun", required = true)
	protected TPodaciORacunu duznikRacun;
	@XmlElement(name = "PoverilacRacun", required = true)
	protected TPodaciORacunu poverilacRacun;
	@XmlElement(name = "Iznos", required = true)
	protected BigDecimal iznos;

	/**
	 * Gets the value of the duznik property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDuznik() {
		return duznik;
	}

	/**
	 * Sets the value of the duznik property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDuznik(String value) {
		this.duznik = value;
	}

	/**
	 * Gets the value of the svrhaPlacanja property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}

	/**
	 * Sets the value of the svrhaPlacanja property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSvrhaPlacanja(String value) {
		this.svrhaPlacanja = value;
	}

	/**
	 * Gets the value of the primalac property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPrimalac() {
		return primalac;
	}

	/**
	 * Sets the value of the primalac property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPrimalac(String value) {
		this.primalac = value;
	}

	/**
	 * Gets the value of the datumNaloga property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getDatumNaloga() {
		return datumNaloga;
	}

	/**
	 * Sets the value of the datumNaloga property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setDatumNaloga(XMLGregorianCalendar value) {
		this.datumNaloga = value;
	}

	/**
	 * Gets the value of the datumValute property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getDatumValute() {
		return datumValute;
	}

	/**
	 * Sets the value of the datumValute property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setDatumValute(XMLGregorianCalendar value) {
		this.datumValute = value;
	}

	/**
	 * Gets the value of the duznikRacun property.
	 * 
	 * @return possible object is {@link TPodaciORacunu }
	 * 
	 */
	public TPodaciORacunu getDuznikRacun() {
		return duznikRacun;
	}

	/**
	 * Sets the value of the duznikRacun property.
	 * 
	 * @param value
	 *            allowed object is {@link TPodaciORacunu }
	 * 
	 */
	public void setDuznikRacun(TPodaciORacunu value) {
		this.duznikRacun = value;
	}

	/**
	 * Gets the value of the poverilacRacun property.
	 * 
	 * @return possible object is {@link TPodaciORacunu }
	 * 
	 */
	public TPodaciORacunu getPoverilacRacun() {
		return poverilacRacun;
	}

	/**
	 * Sets the value of the poverilacRacun property.
	 * 
	 * @param value
	 *            allowed object is {@link TPodaciORacunu }
	 * 
	 */
	public void setPoverilacRacun(TPodaciORacunu value) {
		this.poverilacRacun = value;
	}

	/**
	 * Gets the value of the iznos property.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getIznos() {
		return iznos;
	}

	/**
	 * Sets the value of the iznos property.
	 * 
	 * @param value
	 *            allowed object is {@link BigDecimal }
	 * 
	 */
	public void setIznos(BigDecimal value) {
		this.iznos = value;
	}

	@Override
	public String toString() {
		return "TNalog [duznik=" + duznik + ", svrhaPlacanja=" + svrhaPlacanja + ", primalac=" + primalac
				+ ", datumNaloga=" + datumNaloga + ", datumValute=" + datumValute + ", duznikRacun=" + duznikRacun
				+ ", poverilacRacun=" + poverilacRacun + ", iznos=" + iznos + "]";
	}

}
