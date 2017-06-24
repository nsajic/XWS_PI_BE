package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("P")
public class PravnoLice extends Klijent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "pib", nullable = true)
	private String PIB;

	@Column(name = "naziv", nullable = true)
	private String naziv;

	@Column(name = "web", nullable = true)
	private String web;

	@Column(name = "maticni_broj", nullable = true)
	private int maticniBroj;

	@Column(name = "fax", nullable = true)
	private String fax;
	/*
	@OneToMany(mappedBy = "pravnoLice")
	private Set<Racun> racuni;
*/
	@ManyToOne(optional = true)
	private Delatnost delatnost;

	public PravnoLice() {
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public int getMaticniBroj() {
		return maticniBroj;
	}

	public void setMaticniBroj(int maticniBroj) {
		this.maticniBroj = maticniBroj;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Delatnost getDelatnost() {
		return delatnost;
	}

	public void setDelatnost(Delatnost delatnost) {
		this.delatnost = delatnost;
	}
	
	public void setPIB(String pIB) {
		PIB = pIB;
	}
	
	public String getPIB() {
		return PIB;
	}

}
