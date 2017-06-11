package xws_pi_bezb.models;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rolе")
public class Rola {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv", nullable = false)
	private String naziv;
	
	/*@OneToMany(mappedBy = "rola")
	private Set<Privilegija> privilegijе;*/
	
	@ManyToMany
    @JoinTable(
        name = "role_privilegije", 
        joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "privilegije_id", referencedColumnName = "id"))
    private Collection<Privilegija> privilegije;  
	
	@OneToMany(mappedBy = "rola")
	private Set<Klijent> klijenti;
	
	public Rola() {}
	
	public Rola(String naziv) {
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
