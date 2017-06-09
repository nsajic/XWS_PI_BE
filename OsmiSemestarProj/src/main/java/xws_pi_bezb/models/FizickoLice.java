package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("F")
public class FizickoLice extends Klijent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "jmbg", nullable = true)
	private int jmbg;
	
	@Column(name = "ime_roditelja", nullable = true)
	private String imeRoditelja;
	
	@Column(name = "adresa", nullable = true)
	private String adresa;
	
	@Column(name = "broj_telefona", nullable = true)
	private String brojTelefona;
	
	public FizickoLice(){}
	
	public int getJmbg() {
		return jmbg;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}
/*
	public int getImeRoditelja() {
		return imeRoditelja;
	}

	public void setImeRoditelja(int imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}
	
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
*/

	
}
