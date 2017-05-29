package xws_pi_bezb.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "klijent")
@Component
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ctype", discriminatorType=DiscriminatorType.STRING)
public class Klijent {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "broj_licne_karte", nullable = false)
	private int brojLicneKarte;
	
	@Column(name = "datum_isteka_licne", nullable = false)
	private Date datumIstekaLicneKarte;
	
	@Column(name = "telefon", nullable = true)
	private String telefon;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	public Klijent(){}

	public Klijent(Long id, String ime, String prezime, int brojLicneKarte, Date datumIstekaLicneKarte, String telefon, String adresa) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.brojLicneKarte = brojLicneKarte;
		this.datumIstekaLicneKarte = datumIstekaLicneKarte;
		this.telefon = telefon;
		this.adresa = adresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getBrojLicneKarte() {
		return brojLicneKarte;
	}

	public void setBrojLicneKarte(int brojLicneKarte) {
		this.brojLicneKarte = brojLicneKarte;
	}

	public Date getDatumIstekaLicneKarte() {
		return datumIstekaLicneKarte;
	}

	public void setDatumIstekaLicneKarte(Date datumIstekaLicneKarte) {
		this.datumIstekaLicneKarte = datumIstekaLicneKarte;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

}
