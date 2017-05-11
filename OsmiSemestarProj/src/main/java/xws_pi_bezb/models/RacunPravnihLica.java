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
@Table(name = "racun_pravnih_lica")
public class RacunPravnihLica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long racunPravnihLicaId; // broj racuna
	
	@Column(name = "datum_otvaranja", nullable = false)
	private Date datumOtvaranja;
	
	@Column(name = "vazeci", nullable = false)
	private boolean vazeci;
	
	@ManyToOne
	private PravnoLice pravnoLicePoslovnaBanka;
	
	@ManyToOne
	private PravnoLice pravnoLiceVlasnikRacuna;
	
	@ManyToOne
	private Valuta valuta;
	
	@OneToMany(mappedBy="racunPravnihLica")
	private Set<Ukidanje> ukidanja;
	
	@OneToMany(mappedBy="racunPravnihLica")
	private Set<PrenosIzvoda> prenosiIzvoda;
	
	@OneToMany(mappedBy="racunPravnihLica")
	private Set<AnalitikaPreseka> analitikePreseka;
	
	@OneToMany(mappedBy="racunPravnihLica")
	private Set<DnevnoStanjeRacuna> dnevnaStanjaRacuna;
	
	
	
	public RacunPravnihLica(){}

	public Long getId() {
		return racunPravnihLicaId;
	}

	public void setId(Long id) {
		this.racunPravnihLicaId = id;
	}

	public Date getDatumOtvaranja() {
		return datumOtvaranja;
	}

	public void setDatumOtvaranja(Date datumOtvaranja) {
		this.datumOtvaranja = datumOtvaranja;
	}

	public boolean isVazeci() {
		return vazeci;
	}

	public void setVazeci(boolean vazeci) {
		this.vazeci = vazeci;
	}

	public PravnoLice getPravnoLicePoslovnaBanka() {
		return pravnoLicePoslovnaBanka;
	}

	public void setPravnoLicePoslovnaBanka(PravnoLice pravnoLicePoslovnaBanka) {
		this.pravnoLicePoslovnaBanka = pravnoLicePoslovnaBanka;
	}

	public PravnoLice getPravnoLiceVlasnikRacuna() {
		return pravnoLiceVlasnikRacuna;
	}

	public void setPravnoLiceVlasnikRacuna(PravnoLice pravnoLiceVlasnikRacuna) {
		this.pravnoLiceVlasnikRacuna = pravnoLiceVlasnikRacuna;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}	
	
	
}*/
