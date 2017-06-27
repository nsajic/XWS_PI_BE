package xws_pi_bezb.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import bezbednost.poslovna.xml.ws.nalogzaprenos.TNalog;

@Entity
@Table(name = "nalog_za_prenos")
public class NalogZaPrenos {
	
	@Id
	@GeneratedValue
	private Long id;
	


	
	@Column(name = "idPoruke", nullable = false,unique=true)
    protected String idPoruke;
    
    @Column(name = "duznik", nullable = false)
    protected String duznik;
    
    @Column(name = "svrhaPlacanja", nullable = false)
    protected String svrhaPlacanja;
    
    @Column(name = "primalac", nullable = false)
    protected String primalac;
    
    @Column(name = "datumNaloga", nullable = false)
    protected Date datumNaloga;
    
    @Column(name = "datumValute", nullable = false)
    protected Date datumValute;
    
    @Column(name = "racunDuznik", nullable = false)
    protected String racunDuznik;
    
    @Column(name = "modelDuznik", nullable = false)
    protected int modelDuznik;
    
    @Column(name = "pozivNaBrojDuznik", nullable = false)
    protected String pozivNaBrojDuznik;
    
    
    @Column(name = "racunPoverilac", nullable = false)
    protected String racunPoverilac;
    
    @Column(name = "modelPoverilac", nullable = false)
    protected int modelPoverilac;
    
    @Column(name = "pozivNaBrojPoverilac", nullable = false)
    protected String pozivNaBrojPoverilac;
    
    @Column(name = "iznos", nullable = false)
    protected BigDecimal iznos;
	 
	@Column(name = "oznakaValute", nullable = false)
    protected String oznakaValute;
	 
	@Column(name = "hitno", nullable = false)
    protected boolean hitno;

	public NalogZaPrenos() {
		super();
	}

	public String getIdPoruke() {
		return idPoruke;
	}

	public void setIdPoruke(String idPoruke) {
		this.idPoruke = idPoruke;
	}

	public String getDuznik() {
		return duznik;
	}

	public void setDuznik(String duznik) {
		this.duznik = duznik;
	}

	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}

	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}

	public String getPrimalac() {
		return primalac;
	}

	public void setPrimalac(String primalac) {
		this.primalac = primalac;
	}

	public Date getDatumNaloga() {
		return datumNaloga;
	}

	public void setDatumNaloga(Date datumNaloga) {
		this.datumNaloga = datumNaloga;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public String getRacunDuznik() {
		return racunDuznik;
	}

	public void setRacunDuznik(String racunDuznik) {
		this.racunDuznik = racunDuznik;
	}

	public int getModelDuznik() {
		return modelDuznik;
	}

	public void setModelDuznik(int modelDuznik) {
		this.modelDuznik = modelDuznik;
	}

	public String getPozivNaBrojDuznik() {
		return pozivNaBrojDuznik;
	}

	public void setPozivNaBrojDuznik(String pozivNaBrojDuznik) {
		this.pozivNaBrojDuznik = pozivNaBrojDuznik;
	}

	public String getRacunPoverilac() {
		return racunPoverilac;
	}

	public void setRacunPoverilac(String racunPoverilac) {
		this.racunPoverilac = racunPoverilac;
	}

	public int getModelPoverilac() {
		return modelPoverilac;
	}

	public void setModelPoverilac(int modelPoverilac) {
		this.modelPoverilac = modelPoverilac;
	}

	public String getPozivNaBrojPoverilac() {
		return pozivNaBrojPoverilac;
	}

	public void setPozivNaBrojPoverilac(String pozivNaBrojPoverilac) {
		this.pozivNaBrojPoverilac = pozivNaBrojPoverilac;
	}

	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public String getOznakaValute() {
		return oznakaValute;
	}

	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}

	public boolean isHitno() {
		return hitno;
	}

	public void setHitno(boolean hitno) {
		this.hitno = hitno;
	}
}
