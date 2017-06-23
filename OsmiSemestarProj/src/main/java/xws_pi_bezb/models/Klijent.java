package xws_pi_bezb.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "klijent")
@Component
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tip_klijenta", discriminatorType=DiscriminatorType.STRING)
public class Klijent {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;

	@NotNull
	@Column(name = "email", nullable = false)
	private String email;
	
	
	@Column(name = "broj_licne_karte", nullable = false)
	private int brojLicneKarte;
	
	@Column(name = "telefon", nullable = false)
	private String telefon;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@Column(name = "logovao_se", nullable = false)
	private boolean logovaoSe;

	public Klijent() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public boolean isLogovaoSe() {
		return logovaoSe;
	}
	
	public void setLogovaoSe(boolean logovaoSe) {
		this.logovaoSe = logovaoSe;
	}
}
