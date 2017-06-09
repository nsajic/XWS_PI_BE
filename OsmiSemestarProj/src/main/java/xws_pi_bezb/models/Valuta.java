/*package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "valuta")
public class Valuta  implements Serializable {


	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "sifra_valute", nullable = false)
	private String sifraValute;

	@Column(name = "naziv_valute", nullable = false)
	private String nazivValute;
	
	@OneToMany(mappedBy = "valuta")
	private Set<MedjubankarskiPrenos> medjubankarskiPrenosi; 
	
	@OneToMany(mappedBy = "valuta")
	private Set<AnalitikaIzvoda> analitikeIzvoda;
	
	@OneToMany(mappedBy = "valuta")
	private Set<Racun> racuni;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	public String getNazivValute() {
		return nazivValute;
	}

	public void setNazivValute(String nazivValute) {
		this.nazivValute = nazivValute;
	} 
	
	

	
}
*/