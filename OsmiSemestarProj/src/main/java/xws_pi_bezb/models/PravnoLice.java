package xws_pi_bezb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class PravnoLice extends Klijent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "pib", nullable = false)
	private String PIB;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "web", nullable = true)
	private String web;
	
	@Column(name = "fax", nullable = true)
	private String fax;
	
	
	/*@OneToMany(mappedBy = "pravnoLice")
	private Set<KursnaLista> kursneListe;
	
	@OneToMany(mappedBy = "pravnoLice")
	private Set<KodBanke> kodoviBanke;
	
	@OneToMany(mappedBy = "pravnoLice")
	private Set<PrenosIzvoda> prenosiIzvoda;
	
	@OneToMany(mappedBy = "pravnoLicePoslovnaBanka")
	private Set<RacunPravnihLica> poslovneBanke;
	
	@OneToMany(mappedBy = "pravnoLiceVlasnikRacuna")
	private Set<RacunPravnihLica> vlasniciRacuna;
	*/
	
	public PravnoLice() {}

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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
