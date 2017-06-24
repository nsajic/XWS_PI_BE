package xws_pi_bezb.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private FizickoLice fizickoLice;
	
	@ManyToOne
	private PravnoLice pravnoLice;
	
	@ManyToOne
	private Banka banka;
	
	@ManyToOne
	private Valuta valuta;
	
	@OneToMany(mappedBy = "racun")
	private Set<ZatvaranjeRacuna> zatvaranjaRacuna; 
	
	@OneToMany(mappedBy = "racun")
	private Set<DnevnoStanjeRacuna> dnevnjaStanjaRacuna;

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

	public FizickoLice getFizickoLice() {
		return fizickoLice;
	}

	public void setFizickoLice(FizickoLice fizickoLice) {
		this.fizickoLice = fizickoLice;
	}

	public PravnoLice getPravnoLice() {
		return pravnoLice;
	}

	public void setPravnoLice(PravnoLice pravnoLice) {
		this.pravnoLice = pravnoLice;
	} 
}
