package xws_pi_bezb.models.korisnici;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import xws_pi_bezb.models.Racun;

@Entity
@DiscriminatorValue("F")
public class FizickoLice extends Korisnik implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// TODO: Change to String, change pretraga and other linked code
	@Column(name = "jmbg", nullable = true)
	private int jmbg;
	
	@Column(name = "ime_roditelja", nullable = true)
	private String imeRoditelja;
	
	@OneToMany(mappedBy = "fizickoLice")
	private Set<Racun> racuni;
	
	public FizickoLice(){}
	
	public int getJmbg() {
		return jmbg;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public String getImeRoditelja() {
		return imeRoditelja;
	}

	public void setImeRoditelja(String imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}
}
