package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "naseljeno_mesto")
public class NaseljenoMesto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id; // TODO: Konceptualni

	
	@Column(name = "naziv", nullable = false)
	private String naziv;

	@Column(name = "ptt_oznaka", nullable = false)
	private String pttOznaka;

	@ManyToOne(fetch = FetchType.EAGER)
	private Drzava drzava;

	@OneToMany(mappedBy="naseljeno_mesto")
	private Set<AnalitikaIzvoda> analitikeIzvoda;
	
	public NaseljenoMesto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPttOznaka() {
		return pttOznaka;
	}

	public void setPttOznaka(String pttOznaka) {
		this.pttOznaka = pttOznaka;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}
	
	
}
