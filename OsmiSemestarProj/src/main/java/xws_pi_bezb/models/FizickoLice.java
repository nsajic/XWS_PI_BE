package xws_pi_bezb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class FizickoLice extends Klijent implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "jmbg", nullable = false)
	private int jmbg;
	
	@Column(name = "ime_roditelja", nullable = false)
	private int imeRoditelja;

	public FizickoLice(){}

	public FizickoLice(int jmbg, int imeRoditelja) {
		super();
		this.jmbg = jmbg;
		this.imeRoditelja = imeRoditelja;
	}

	public int getJmbg() {
		return jmbg;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public int getImeRoditelja() {
		return imeRoditelja;
	}

	public void setImeRoditelja(int imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}
}
