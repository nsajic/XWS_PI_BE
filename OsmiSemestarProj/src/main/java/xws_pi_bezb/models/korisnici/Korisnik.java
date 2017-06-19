package xws_pi_bezb.models.korisnici;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import xws_pi_bezb.models.Rola;

@Entity
@Table(name = "korisnik")
@Component
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tip_korisnika", discriminatorType=DiscriminatorType.STRING)
public class Korisnik {
	
	@Id
	@GeneratedValue
	private Long id;

	
	@Column(name = "ime", nullable = false)
	private String ime;
	
	@Column(name = "prezime", nullable = false)
	private String prezime;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@NotNull
	@Column(name = "email", nullable = false)
	private String email;
	
	
	@Size(min=6, max=20)
	@NotNull
	@Column(name = "sifra", length = 60, nullable = false)
	private String sifra;
	
	@Column(name = "broj_licne_karte", nullable = false)
	private int brojLicneKarte;
	
	@Column(name = "telefon", nullable = false)
	private String telefon;
	
	@Column(name = "adresa", nullable = false)
	private String adresa;
	
	@Column(name = "logovao_se", nullable = false)
	private boolean logovaoSe;
	
	@ManyToOne
	private Rola rola;
	
	
	public Korisnik() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@JsonIgnore
	public Rola getRola() {
		return rola;
	}

	@JsonProperty
	public void setRola(Rola rola) {
		this.rola = rola;
	}
	
	public boolean isLogovaoSe() {
		return logovaoSe;
	}
	
	public void setLogovaoSe(boolean logovaoSe) {
		this.logovaoSe = logovaoSe;
	}
}
