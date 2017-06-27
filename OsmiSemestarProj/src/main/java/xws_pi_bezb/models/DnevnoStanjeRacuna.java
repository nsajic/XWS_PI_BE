package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// NE TREBA CRUD

@Entity
@Table(name = "dnevno_stanje_racuna")
public class DnevnoStanjeRacuna implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	//TODO: Da kombinacija datuma(danasnjeg dana) i racuna bude unique
	@Column(name = "datum", nullable = false)
	private Date datum;

	@Column(name = "prethodno_stanje", nullable = false)
	private double prethodnoStanje;

	@Column(name = "promet_na_teret", nullable = false)
	private double prometNaTeret;

	@Column(name = "promet_u_korist", nullable = false)
	private double prometUKorist;

	@Column(name = "novo_stanje", nullable = false)
	private double novoStanje;

	@ManyToOne
	private Racun racun;

	@OneToMany(mappedBy = "dnevnoStanjeRacuna")
	private Set<AnalitikaIzvoda> analitikeIzvoda;

	
	
	public DnevnoStanjeRacuna() {
		super();
	}

	
	public Date getDatum() {
		return datum;
	}

	public Long getId() {
		return id;
	}

	public double getNovoStanje() {
		return novoStanje;
	}

	public double getPrethodnoStanje() {
		return prethodnoStanje;
	}

	public double getPrometNaTeret() {
		return prometNaTeret;
	}

	public double getPrometUKorist() {
		return prometUKorist;
	}

	public Racun getRacun() {
		return racun;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNovoStanje(double novoStanje) {
		this.novoStanje = novoStanje;
	}

	public void setPrethodnoStanje(double prethodnoStanje) {
		this.prethodnoStanje = prethodnoStanje;
	}

	public void setPrometNaTeret(double prometNaTeret) {
		this.prometNaTeret = prometNaTeret;
	}

	public void setPrometUKorist(double prometUKorist) {
		this.prometUKorist = prometUKorist;
	}

	public void setRacun(Racun racun) {
		this.racun = racun;
	}
}
