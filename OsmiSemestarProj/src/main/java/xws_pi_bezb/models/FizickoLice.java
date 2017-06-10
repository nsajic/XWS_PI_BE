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
