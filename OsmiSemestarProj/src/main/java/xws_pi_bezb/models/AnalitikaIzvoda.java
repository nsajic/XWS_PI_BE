package xws_pi_bezb.models;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "analitika_izvoda")
public class AnalitikaIzvoda implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlTransient
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @XmlElement
    @Column(name = "duznik", nullable = false)
    private String duznik;

    @XmlElement
    @Column(name = "svrha", nullable = false)
    private String svrha;

    @XmlElement
    @Column(name = "poverilac", nullable = false)
    private String poverilac;

    @XmlElement
    @Column(name = "datum_prijema", nullable = false)
    private ZonedDateTime datumPrijema;

    @XmlElement
    @Column(name = "datum_valute", nullable = false)
    private ZonedDateTime datumValute;

    @XmlElement
    @Column(name = "racun_duznika", nullable = false)
    private String racunDuznika;

    @XmlElement
    @Column(name = "model_zaduzenja")
    private Integer modelZaduzenja;

    @XmlElement
    @Column(name = "poziv_na_broj_zaduzenja")
    private String pozivNaBrojZaduzenja;

    @XmlElement
    @Column(name = "racun_poverioca")
    private String racunPoverioca;

    @XmlElement
    @Column(name = "model_odobrenja")
    private Integer modelOdobrenja;

    @XmlElement
    @Column(name = "poziv_na_broj_odobrenja")
    private String pozivNaBrojOdobrenja;

    @XmlElement
    @Column(name = "is_hitno", nullable = false)
    private Boolean isHitno;

    @XmlElement
    @Column(name = "iznos", nullable = false)
    private Double iznos;

    @XmlTransient
    @Column(name = "tip_greske", nullable = false)
    private Integer tipGreske;

    @XmlTransient
    @Column(name = "tip_greske", nullable = false)
    private String Smer;

    
    @XmlTransient
    @Column(name = "status")
    private String status;
    
    @ManyToOne //TODO:PK
    private DnevnoStanjeRacuna dnevnoStanjeRacuna;
    
    @ManyToOne
    private NaseljenoMesto naseljenoMesto;
    
    @ManyToOne
    private VrstaPlacanja vrstaPlacanja;
    
    @ManyToOne
    private Valuta valuta;
    
    @OneToMany(mappedBy="analitika_izvoda")
    private Set<AnalitikaPreseka> analitikePreseka;
    
    
    public AnalitikaIzvoda(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDuznik() {
		return duznik;
	}

	public void setDuznik(String duznik) {
		this.duznik = duznik;
	}

	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	public String getPoverilac() {
		return poverilac;
	}

	public void setPoverilac(String poverilac) {
		this.poverilac = poverilac;
	}

	public ZonedDateTime getDatumPrijema() {
		return datumPrijema;
	}

	public void setDatumPrijema(ZonedDateTime datumPrijema) {
		this.datumPrijema = datumPrijema;
	}

	public ZonedDateTime getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(ZonedDateTime datumValute) {
		this.datumValute = datumValute;
	}

	public String getRacunDuznika() {
		return racunDuznika;
	}

	public void setRacunDuznika(String racunDuznika) {
		this.racunDuznika = racunDuznika;
	}

	public Integer getModelZaduzenja() {
		return modelZaduzenja;
	}

	public void setModelZaduzenja(Integer modelZaduzenja) {
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

	public Integer getModelOdobrenja() {
		return modelOdobrenja;
	}

	public void setModelOdobrenja(Integer modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}

	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}

	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}

	public Boolean getIsHitno() {
		return isHitno;
	}

	public void setIsHitno(Boolean isHitno) {
		this.isHitno = isHitno;
	}

	public Double getIznos() {
		return iznos;
	}

	public void setIznos(Double iznos) {
		this.iznos = iznos;
	}

	public Integer getTipGreske() {
		return tipGreske;
	}

	public void setTipGreske(Integer tipGreske) {
		this.tipGreske = tipGreske;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSmer() {
		return Smer;
	}

	public void setSmer(String smer) {
		Smer = smer;
	}

	public DnevnoStanjeRacuna getDnevnoStanjeRacuna() {
		return dnevnoStanjeRacuna;
	}

	public void setDnevnoStanjeRacuna(DnevnoStanjeRacuna dnevnoStanjeRacuna) {
		this.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
	}

	public NaseljenoMesto getNaseljenoMesto() {
		return naseljenoMesto;
	}

	public void setNaseljenoMesto(NaseljenoMesto naseljenoMesto) {
		this.naseljenoMesto = naseljenoMesto;
	}

	public VrstaPlacanja getVrstaPlacanja() {
		return vrstaPlacanja;
	}

	public void setVrstaPlacanja(VrstaPlacanja vrstaPlacanja) {
		this.vrstaPlacanja = vrstaPlacanja;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}

	
	
}
