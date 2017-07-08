package xws_pi_bezb.models;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="AnalitikeIzvod", namespace="http://analitikeizvod.ws.xml.poslovna.bezbednost/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalitikeIzvod", propOrder = { "prethodnoStanje", "ukupnoUKorist", "ukupnoNaTeret", "novoStanje",
		"analitike" }, namespace="http://analitikeizvod.ws.xml.poslovna.bezbednost/")
public class Izvodi {

	@XmlElement(name = "PrethodnoStanje", required = true)
	protected BigDecimal prethodnoStanje;

	@XmlElement(name = "UkupnoUKorist", required = true)
	protected BigDecimal ukupnoUKorist;

	@XmlElement(name = "UkupnoNaTeret", required = true)
	protected BigDecimal ukupnoNaTeret;

	@XmlElement(name = "NovoStanje", required = true)
	protected BigDecimal novoStanje;

	@XmlElement(name = "Analitike", required = true)
	private List<AnalitikaIzvodaXML> analitike;

	public Izvodi() {

	}

	public List<AnalitikaIzvodaXML> getAnalitike() {
		return analitike;
	}

	public void setAnalitike(List<AnalitikaIzvodaXML> analitike) {
		this.analitike = analitike;
	}

	public BigDecimal getPrethodnoStanje() {
		return prethodnoStanje;
	}

	public void setPrethodnoStanje(BigDecimal prethodnoStanje) {
		this.prethodnoStanje = prethodnoStanje;
	}

	public BigDecimal getUkupnoUKorist() {
		return ukupnoUKorist;
	}

	public void setUkupnoUKorist(BigDecimal ukupnoUKorist) {
		this.ukupnoUKorist = ukupnoUKorist;
	}

	public BigDecimal getUkupnoNaTeret() {
		return ukupnoNaTeret;
	}

	public void setUkupnoNaTeret(BigDecimal ukupnoNaTeret) {
		this.ukupnoNaTeret = ukupnoNaTeret;
	}

	public BigDecimal getNovoStanje() {
		return novoStanje;
	}

	public void setNovoStanje(BigDecimal novoStanje) {
		this.novoStanje = novoStanje;
	}

}
