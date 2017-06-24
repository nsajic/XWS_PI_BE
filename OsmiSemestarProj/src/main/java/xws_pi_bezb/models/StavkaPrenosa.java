package xws_pi_bezb.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "stavka_prenosa")
public class StavkaPrenosa implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private MedjubankarskiPrenos medjubankarskiPrenos;
	
	@ManyToOne
	private AnalitikaIzvoda analitikaIzvoda;
	
	public AnalitikaIzvoda getAnalitikaIzvoda() {
		return analitikaIzvoda;
	}
	
	public void setAnalitikaIzvoda(AnalitikaIzvoda analitikaIzvoda) {
		this.analitikaIzvoda = analitikaIzvoda;
	}
}


