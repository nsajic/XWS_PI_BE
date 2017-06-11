package xws_pi_bezb.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import xws_pi_bezb.models.korisnici.PravnoLice;

@Entity
@Table(name = "delatnost")
public class Delatnost {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv_delatnosti", nullable = false)
	private String nazivDelatnosti;
	
	
	@OneToMany(mappedBy = "delatnost")
	private Set<PravnoLice> pravnaLica;
	
	public Delatnost(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivDelatnosti() {
		return nazivDelatnosti;
	}

	public void setNazivDelatnosti(String nazivDelatnosti) {
		this.nazivDelatnosti = nazivDelatnosti;
	}
}