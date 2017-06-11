package xws_pi_bezb.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "privilegijе")
public class Privilegija {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	/*@ManyToOne
	private Rola rola;*/
	
	@ManyToMany(mappedBy = "privilegije")
    private Collection<Rola> role;
	
	public Privilegija() {}

	public Privilegija(String naziv) {
		super();
		this.naziv = naziv;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
