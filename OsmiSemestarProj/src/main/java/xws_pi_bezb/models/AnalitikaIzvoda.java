package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// NE TREBA CRUD
@Entity
@Table(name = "analitika_izvoda")
public class AnalitikaIzvoda implements Serializable {

	private static final long serialVersionUID = -6764947838323803049L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "datum_analitike", nullable = false)
	private Date datumAnalitike;

	@Column(name = "smer", nullable = false)
	private String smer;

	@Column(name = "duznik_nalogodavac", nullable = false)
	private String duznikNalogodavac;

	@Column(name = "svrha_placanja", nullable = false)
	private String svrhaPlacanja;

	@Column(name = "primalac_poverilac", nullable = false)
	private String primalacPoverilac;

	@Column(name = "datum_naloga", nullable = false)
	private Date datumNaloga;

	@Column(name = "datum_valute", nullable = false)
	private Date datumValute;

	@Column(name = "racun_duznika", nullable = true)
	private String racunDuznika;

	@Column(name = "model_zaduzenja", nullable = true)
	private int modelZaduzenja;

	@Column(name = "poziv_na_broj_zaduzenja", nullable = true)
	private String pozivNaBrojZaduzenja;

	@Column(name = "racun_poverioca", nullable = true)
	private String racunPoverioca;

	@Column(name = "model_odobrenja", nullable = true)
	private int modelOdobrenja;

	@Column(name = "poziv_na_broj_odobrenja", nullable = true)
	private String pozivNaBrojOdobrenja;

	@Column(name = "iznos", nullable = false)
	private double iznos;

	@ManyToOne
	private DnevnoStanjeRacuna dnevnoStanjeRacuna;

	@ManyToOne
	private Valuta valuta;

	@OneToMany(mappedBy = "analitikaIzvoda")
	private Set<StavkaPrenosa> stavkePrenosa;

	@OneToMany(mappedBy = "analitikaIzvoda")
	private Set<ZatvaranjeRacuna> zatvaranjeRacuna;

	public AnalitikaIzvoda() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumAnalitike() {
		return datumAnalitike;
	}

	public void setDatumAnalitike(Date datumAnalitike) {
		this.datumAnalitike = datumAnalitike;
	}

	public String getSmer() {
		return smer;
	}

	public void setSmer(String smer) {
		this.smer = smer;
	}

	public String getDuznikNalogodavac() {
		return duznikNalogodavac;
	}

	public void setDuznikNalogodavac(String duznikNalogodavac) {
		this.duznikNalogodavac = duznikNalogodavac;
	}

	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}

	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}

	public String getPrimalacPoverilac() {
		return primalacPoverilac;
	}

	public void setPrimalacPoverilac(String primalacPoverilac) {
		this.primalacPoverilac = primalacPoverilac;
	}

	public Date getDatumNaloga() {
		return datumNaloga;
	}

	public void setDatumNaloga(Date datumNaloga) {
		this.datumNaloga = datumNaloga;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public String getRacunDuznika() {
		return racunDuznika;
	}

	public void setRacunDuznika(String racunDuznika) {
		this.racunDuznika = racunDuznika;
	}

	public int getModelZaduzenja() {
		return modelZaduzenja;
	}

	public void setModelZaduzenja(int modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}

	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}

	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}

	public String getRacunPoverioca() {
		return racunPoverioca;
	}

	public void setRacunPoverioca(String racunPoverioca) {
		this.racunPoverioca = racunPoverioca;
	}

	public int getModelOdobrenja() {
		return modelOdobrenja;
	}

	public void setModelOdobrenja(int modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}

	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}

	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public DnevnoStanjeRacuna getDnevnoStanjeRacuna() {
		return dnevnoStanjeRacuna;
	}

	public void setDnevnoStanjeRacuna(DnevnoStanjeRacuna dnevnoStanjeRacuna) {
		this.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}

}
