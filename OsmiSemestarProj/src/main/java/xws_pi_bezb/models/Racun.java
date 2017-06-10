package xws_pi_bezb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "racun")
public class Racun {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "broj_racuna", nullable = false)
	private String brojRacuna;

	@Column(name = "status_racuna", nullable = false)
	private int statusRacuna;
	
	@ManyToOne
	private Klijent klijent;
	
	@ManyToOne
	private Banka banka;
	
	@ManyToOne
	private Valuta valuta;
	/*
	@OneToMany(mappedBy = "racun")
	private Set<ZatvaranjeRacuna> zatvaranjaRacuna; 
	
	@OneToMany(mappedBy = "racun")
	private Set<DnevnoStanjeRacuna> dnevnjaStanjaRacuna;
*/
	public Racun()
	{
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public int getStatusRacuna() {
		return statusRacuna;
	}

	public void setStatusRacuna(int statusRacuna) {
		this.statusRacuna = statusRacuna;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	public Banka getBanka() {
		return banka;
	}

	public void setBanka(Banka banka) {
		this.banka = banka;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	} 
	
	
}
