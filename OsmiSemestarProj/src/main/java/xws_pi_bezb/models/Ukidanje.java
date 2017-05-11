/*package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ukidanje")
public class Ukidanje  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long ukidanjeId;
	
	@Column(name = "datum_ukidanja", nullable = false)
	private Date datumUkidanja;
	
	@Column(name = "sredstva_se_prenose_na_racun", nullable = false)
	private String sredstvaSePrenoseNaRacun;
	
	@ManyToOne
	private RacunPravnihLica racunPravnihLica;
	
	public Ukidanje(){}

	public Date getDatumUkidanja() {
		return datumUkidanja;
	}

	public void setDatumUkidanja(Date datumUkidanja) {
		this.datumUkidanja = datumUkidanja;
	}

	public String getSredstvaSePrenoseNaRacun() {
		return sredstvaSePrenoseNaRacun;
	}

	public void setSredstvaSePrenoseNaRacun(String sredstvaSePrenoseNaRacun) {
		this.sredstvaSePrenoseNaRacun = sredstvaSePrenoseNaRacun;
	}

	public Long getId() {
		return ukidanjeId;
	}

	public void setId(Long id) {
		this.ukidanjeId = id;
	}

	public RacunPravnihLica getRacunPravnihLica() {
		return racunPravnihLica;
	}

	public void setRacunPravnihLica(RacunPravnihLica racunPravnihLica) {
		this.racunPravnihLica = racunPravnihLica;
	}
	
	
}*/
