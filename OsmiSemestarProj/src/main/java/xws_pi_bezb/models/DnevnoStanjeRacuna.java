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

// NE TREBA CRUD

@Entity
@Table(name = "dnevno_stanje_racuna")
public class DnevnoStanjeRacuna implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
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
}
*/