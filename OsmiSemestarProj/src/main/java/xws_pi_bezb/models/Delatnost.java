package xws_pi_bezb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delatnost")
public class Delatnost {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "sifra_delatnosti", nullable = false)
	private int sifraDelatnosti;
	
	@Column(name = "naziv_delatnosti", nullable = false)
	private String nazivDelatnosti;
	
	public Delatnost(){}

	public Delatnost(Long id, int sifraDelatnosti, String nazivDelatnosti) {
		super();
		this.id = id;
		this.sifraDelatnosti = sifraDelatnosti;
		this.nazivDelatnosti = nazivDelatnosti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSifraDelatnosti() {
		return sifraDelatnosti;
	}

	public void setSifraDelatnosti(int sifraDelatnosti) {
		this.sifraDelatnosti = sifraDelatnosti;
	}

	public String getNazivDelatnosti() {
		return nazivDelatnosti;
	}

	public void setNazivDelatnosti(String nazivDelatnosti) {
		this.nazivDelatnosti = nazivDelatnosti;
	}
}