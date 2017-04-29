package xws_pi_bezb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kurs_u_valuti")
public class KursUValuti implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id; // redni broj
	
	@Column(name = "kupovni", nullable = false)
	private double kupovni;
	
	@Column(name = "srednji", nullable = false)
	private double srednji;
	
	@Column(name = "prodajni", nullable = false)
	private double prodajni;
	
	@ManyToOne //TODO:PK
    private KursnaLista kursnaLista;
	
	@ManyToOne
    private Valuta osnovnaValuta;

    @ManyToOne
    private Valuta premaValuti;
	
	public KursUValuti(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getKupovni() {
		return kupovni;
	}

	public void setKupovni(double kupovni) {
		this.kupovni = kupovni;
	}

	public double getSrednji() {
		return srednji;
	}

	public void setSrednji(double srednji) {
		this.srednji = srednji;
	}

	public double getProdajni() {
		return prodajni;
	}

	public void setProdajni(double prodajni) {
		this.prodajni = prodajni;
	}

	public KursnaLista getKursnaLista() {
		return kursnaLista;
	}

	public void setKursnaLista(KursnaLista kursnaLista) {
		this.kursnaLista = kursnaLista;
	}

	public Valuta getOsnovnaValuta() {
		return osnovnaValuta;
	}

	public void setOsnovnaValuta(Valuta osnovnaValuta) {
		this.osnovnaValuta = osnovnaValuta;
	}

	public Valuta getPremaValuti() {
		return premaValuti;
	}

	public void setPremaValuti(Valuta premaValuti) {
		this.premaValuti = premaValuti;
	}
	
	
}
