/*package xws_pi_bezb.models;

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

@Entity
@Table(name = "dnevno_stanje_racuna")
public class DnevnoStanjeRacuna implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id; // broj izvoda

	@Column(name = "datum_prometa", nullable = false)
	private Date datumPrometa;

	@Column(name = "prethodno_stanje", nullable = false)
	private double prethodnoStanje;

	@Column(name = "promet_u_korist", nullable = false)
	private double prometUKorist;

	@Column(name = "promet_na_teret", nullable = false)
	private double prometNaTeret;

	@Column(name = "novo_stanje", nullable = false)
	private double novoStanje;

	@OneToMany(mappedBy = "dnevnoStanjeRacuna")
	private Set<AnalitikaIzvoda> analitikeIzvoda;

	@OneToMany(mappedBy = "dnevnoStanjeRacuna")
	private Set<PrenosIzvoda> prenosiIzvoda;

	@ManyToOne // TODO: PK
	private RacunPravnihLica racunPravnihLica;

	public DnevnoStanjeRacuna() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumPrometa() {
		return datumPrometa;
	}

	public void setDatumPrometa(Date datumPrometa) {
		this.datumPrometa = datumPrometa;
	}

	public double getPrethodnoStanje() {
		return prethodnoStanje;
	}

	public void setPrethodnoStanje(double prethodnoStanje) {
		this.prethodnoStanje = prethodnoStanje;
	}

	public double getPrometUKorist() {
		return prometUKorist;
	}

	public void setPrometUKorist(double prometUKorist) {
		this.prometUKorist = prometUKorist;
	}

	public double getPrometNaTeret() {
		return prometNaTeret;
	}

	public void setPrometNaTeret(double prometNaTeret) {
		this.prometNaTeret = prometNaTeret;
	}

	public double getNovoStanje() {
		return novoStanje;
	}

	public void setNovoStanje(double novoStanje) {
		this.novoStanje = novoStanje;
	}

	public RacunPravnihLica getRacunPravnihLica() {
		return racunPravnihLica;
	}

	public void setRacunPravnihLica(RacunPravnihLica racunPravnihLica) {
		this.racunPravnihLica = racunPravnihLica;
	}
	
	
}*/
