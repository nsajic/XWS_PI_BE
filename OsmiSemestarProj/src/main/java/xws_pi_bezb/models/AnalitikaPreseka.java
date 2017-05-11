/*package xws_pi_bezb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "analitika_preseka")
public class AnalitikaPreseka implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "redni_broj_promene", nullable = false)
	private int redniBrojPromene;

	@ManyToOne // TODO: PK
	private PrenosIzvoda prenosIzvoda;

	@ManyToOne
	private RacunPravnihLica racunPravnihLica;

	@ManyToOne
	private AnalitikaIzvoda analitikaIzvoda;

	public AnalitikaPreseka() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRedniBrojPromene() {
		return redniBrojPromene;
	}

	public void setRedniBrojPromene(int redniBrojPromene) {
		this.redniBrojPromene = redniBrojPromene;
	}

	public PrenosIzvoda getPrenosIzvoda() {
		return prenosIzvoda;
	}

	public RacunPravnihLica getRacunPravnihLica() {
		return racunPravnihLica;
	}

	public AnalitikaIzvoda getAnalitikaIzvoda() {
		return analitikaIzvoda;
	}

	public void setPrenosIzvoda(PrenosIzvoda prenosIzvoda) {
		this.prenosIzvoda = prenosIzvoda;
	}

	public void setRacunPravnihLica(RacunPravnihLica racunPravnihLica) {
		this.racunPravnihLica = racunPravnihLica;
	}

	public void setAnalitikaIzvoda(AnalitikaIzvoda analitikaIzvoda) {
		this.analitikaIzvoda = analitikaIzvoda;
	}
	
	
}
*/