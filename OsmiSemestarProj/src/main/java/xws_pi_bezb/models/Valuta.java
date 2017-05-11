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

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long valutaId; // id valute
	
	@Column(name = "zvanicna_sifra", nullable = false)
	private String zvanicnaSifra; // TODO: proveriti koji je tip A3
	
	@Column(name = "naziv", nullable = false)
	private  String naziv;
	
	@Column(name = "domicilna", nullable = false)
	private boolean domicilna; // TODO: proveriti sta je domicilna
	
	@OneToMany(mappedBy = "osnovnaValuta")
	private Set<KursUValuti> kurseviUValutiOsnovni;
	
	@OneToMany(mappedBy = "premaValuti")
	private Set<KursUValuti> kurseviUValutiPrema;
	
	@OneToMany(mappedBy = "valuta")
	private Set<RacunPravnihLica> racuniPravnihLista;
	
	@OneToMany(mappedBy="valuta")
	private Set<AnalitikaIzvoda> analitikeIzvoda;
	
	@ManyToOne
	private Drzava drzava;
	
	
	public Valuta(){}
	
	public Long getId() {
		return valutaId;
	}
	
	public void setId(Long id) {
		this.valutaId = id;
	}
	
	public String getZvanicnaSifra() {
		return zvanicnaSifra;
	}
	
	public void setZvanicnaSifra(String zvanicnaSifra) {
		this.zvanicnaSifra = zvanicnaSifra;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public boolean isDomicilna() {
		return domicilna;
	}
	
	public void setDomicilna(boolean domicilna) {
		this.domicilna = domicilna;
	}

	public Drzava getDrzava() {
		return drzava;
	}

	public void setDrzava(Drzava drzava) {
		this.drzava = drzava;
	}
	
	
}*/
