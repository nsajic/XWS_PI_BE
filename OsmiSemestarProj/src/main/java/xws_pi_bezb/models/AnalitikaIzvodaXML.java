package xws_pi_bezb.models;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import bezbednost.poslovna.xml.ws.nalogzaprenos.TNalog;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalitikaIzvodaXML", propOrder = {
		"datumAnalitike",
		"nalog",
		"smer"})
public class AnalitikaIzvodaXML {

	@XmlElement(name = "DatumAnalitike", required = true)
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar datumAnalitike;

	@XmlElement(name = "Nalog", required = true)
	protected TNalog nalog;

	@XmlElement(name = "Smer", required = true)
	protected String smer;

	public XMLGregorianCalendar getDatumAnalitike() {
		return datumAnalitike;
	}

	public void setDatumAnalitike(XMLGregorianCalendar datumAnalitike) {
		this.datumAnalitike = datumAnalitike;
	}

	public TNalog getNalog() {
		return nalog;
	}

	public void setNalog(TNalog nalog) {
		this.nalog = nalog;
	}

	public String getSmer() {
		return smer;
	}

	public void setSmer(String smer) {
		this.smer = smer;
	}

}
