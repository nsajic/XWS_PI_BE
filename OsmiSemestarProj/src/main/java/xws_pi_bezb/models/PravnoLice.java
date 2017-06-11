package xws_pi_bezb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("P")
public class PravnoLice extends Klijent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "pib", nullable = true)
	private int PIB;

	@Column(name = "naziv", nullable = true)
	private String naziv;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "web", nullable = true)
	private String web;

	@Column(name = "maticni_broj", nullable = true)
	private int maticniBroj;

	@Column(name = "fax", nullable = true)
	private String fax;

	@Column(name = "apr", nullable = true)
	private boolean APR;

	@Column(name = "op", nullable = true)
	private boolean OP;

	@ManyToOne(optional = true)
	private Delatnost delatnost;

	public PravnoLice() {
	}

	public int getPIB() {
		return PIB;
	}

	public void setPIB(int PIB) {
		this.PIB = PIB;
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

	public boolean isAPR() {
		return APR;
	}

	public void setAPR(boolean aPR) {
		APR = aPR;
	}

	public boolean isOP() {
		return OP;
	}

	public void setOP(boolean oP) {
		OP = oP;
	}

	public Delatnost getDelatnost() {
		return delatnost;
	}

	public void setDelatnost(Delatnost delatnost) {
		this.delatnost = delatnost;
	}

}
