package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "drzava")
public class Drzava  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv_drzave", nullable = false)
	private String nazivDrzave;

	//TODO: Odkomentarisati kad se otkomentarisu klase
/*
	@OneToMany(mappedBy = "drzava", fetch = FetchType.EAGER)
	private Set<Valuta> valute;
	*/
	@OneToMany(mappedBy = "drzava", fetch = FetchType.EAGER)
	private Set<NaseljenoMesto> naseljenaMesta;
	
	public Drzava(){}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivDrzave() {
		return nazivDrzave;
	}

	public void setNazivDrzave(String nazivDrzave) {
		this.nazivDrzave = nazivDrzave;
	}
	

	
	
}
