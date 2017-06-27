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
	private String jmbg;
	
	@Column(name = "ime_roditelja", nullable = true)
	private String imeRoditelja;
	/*
	@OneToMany(mappedBy = "fizickoLice")
	private Set<Racun> racuni;
	*/
	public FizickoLice(){}
	
	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getImeRoditelja() {
		return imeRoditelja;
	}

	public void setImeRoditelja(String imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}
}
