package xws_pi_bezb.models.korisnici;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.Rola;
@Entity
@Table(name = "bankarski_sluzbenik")
public class BankarskiSluzbenik implements Serializable {

	private static final long serialVersionUID = 2127543503576018306L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;

	@NotNull
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "sifra", nullable = false)
	private String sifra;
	
	@Column(name = "telefon", nullable = false)
	private String telefon;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@Column(name = "jmbg", nullable = true)
	private String jmbg;
	
	@ManyToOne
	private Banka banka;
	
	@ManyToOne
	private Rola rola;
	
	public BankarskiSluzbenik() {}
	
	public Banka getBanka() {
		return banka;
	}
	public void setBanka(Banka banka) {
		this.banka = banka;
	}
	
	public String getAdresa() {
		return adresa;
	}
	public String getEmail() {
		return email;
	}
	public Long getId() {
		return id;
	}
	
	public String getIme() {
		return ime;
	}
	public String getJmbg() {
		return jmbg;
	}
	
	public String getPrezime() {
		return prezime;
	}
	
	public String getTelefon() {
		return telefon;
	}
	
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getSifra() {
		return sifra;
	}
	
	public Rola getRola() {
		return rola;
	}
	
	public void setRola(Rola rola) {
		this.rola = rola;
	}
	

	
}
