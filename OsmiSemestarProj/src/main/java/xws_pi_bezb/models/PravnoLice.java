package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pravno_lice")
public class PravnoLice implements Serializable {

	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "pib", nullable = false)
	private String PIB;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "web", nullable = true)
	private String web;
	
	@Column(name = "telefon", nullable = true)
	private String telefon;
	
	@Column(name = "fax", nullable = true)
	private String fax;
	
	@Column(name = "tip", nullable = false)
	int tip; //TODO: Tamo je boolean
	
	@OneToMany(mappedBy = "pravno_lice")
	private Set<KursnaLista> kursneListe;
	
	@OneToMany(mappedBy = "pravno_lice")
	private Set<KodBanke> kodoviBanke;
	
	@OneToMany(mappedBy = "pravno_lice")
	private Set<PrenosIzvoda> prenosiIzvoda;
	
	@OneToMany(mappedBy = "pravno_lice_poslovna_banka")
	private Set<RacunPravnihLica> poslovneBanke;
	
	@OneToMany(mappedBy = "pravno_lice_vlasnik_racuna")
	private Set<RacunPravnihLica> vlasniciRacuna;
	
	
	public PravnoLice() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPIB() {
		return PIB;
	}

	public void setPIB(String pIB) {
		PIB = pIB;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getTip() {
		return tip;
	}

	public void setTip(int tip) {
		this.tip = tip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
