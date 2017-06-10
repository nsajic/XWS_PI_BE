/*package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//NE TREBA CRUD
@Entity
@Table(name = "zatvaranje_racuna")
public class ZatvaranjeRacuna implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "datum_zatvaranja", nullable = false)
	private Date datumZatvaranja;

	@Column(name = "prebaceno_na_racun", nullable = false)
	private String prebacenoNaRacun;
	
	@ManyToOne
	private Racun racun;
	
	@ManyToOne
	private AnalitikaIzvoda analitikaIzvoda;
}*/
