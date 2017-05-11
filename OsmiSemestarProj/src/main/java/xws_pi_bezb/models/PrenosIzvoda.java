/*package xws_pi_bezb.models;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prenos_izvoda")
public class PrenosIzvoda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long prenosIzvodaId; // sifra mesta

	@Column(name = "datum_naloga", nullable = false)
	private ZonedDateTime datumNaloga;

	@Column(name = "broj_preseka", nullable = false)
	private int brojPreseka;

	@Column(name = "promena_u_korist", nullable = false)
	private int promenaUKorist;

	@Column(name = "ukupno_u_korist", nullable = false)
	private double ukupnoUKorist;

	@Column(name = "broj_promena_teret", nullable = false)
	private int brojPromenaTeret;

	@Column(name = "ukupno_na_teret", nullable = false)
	private double ukupnoNaTeret;

	@Column(name = "broj_pogresnih_stavki_u_korist", nullable = false)
	private int brojPogresnihStavkiUKorist;

	@Column(name = "broj_pogresnih_stavki_na_teret", nullable = false)
	private int brojPogresnihStavkiNaTeret;

	@Column(name = "status_naloga", nullable = false)
	private String statusNaloga;

	@OneToMany(mappedBy = "prenosIzvoda")
	private Set<AnalitikaPreseka> analitikePreseka;

	@ManyToOne
	private DnevnoStanjeRacuna dnevnoStanjeRacuna;

	@ManyToOne
	private PravnoLice pravnoLice;

	@ManyToOne
	private RacunPravnihLica racunPravnihLica;

	public PrenosIzvoda() {
	}

	public Long getId() {
		return prenosIzvodaId;
	}

	public void setId(Long id) {
		this.prenosIzvodaId = id;
	}

	public ZonedDateTime getDatumNaloga() {
		return datumNaloga;
	}

	public void setDatumNaloga(ZonedDateTime datumNaloga) {
		this.datumNaloga = datumNaloga;
	}

	public int getBrojPreseka() {
		return brojPreseka;
	}

	public void setBrojPreseka(int brojPreseka) {
		this.brojPreseka = brojPreseka;
	}

	public int getPromenaUKorist() {
		return promenaUKorist;
	}

	public void setPromenaUKorist(int promenaUKorist) {
		this.promenaUKorist = promenaUKorist;
	}

	public double getUkupnoUKorist() {
		return ukupnoUKorist;
	}

	public void setUkupnoUKorist(double ukupnoUKorist) {
		this.ukupnoUKorist = ukupnoUKorist;
	}

	public int getBrojPromenaTeret() {
		return brojPromenaTeret;
	}

	public void setBrojPromenaTeret(int brojPromenanaTeret) {
		this.brojPromenaTeret = brojPromenanaTeret;
	}

	public double getUkupnoNaTeret() {
		return ukupnoNaTeret;
	}

	public void setUkupnoNaTeret(double ukupnoNaTeret) {
		this.ukupnoNaTeret = ukupnoNaTeret;
	}

	public int getBrojPogresnihStavkiUKorist() {
		return brojPogresnihStavkiUKorist;
	}

	public void setBrojPogresnihStavkiUKorist(int brojPogresnihStavkiUKorist) {
		this.brojPogresnihStavkiUKorist = brojPogresnihStavkiUKorist;
	}

	public int getBrojPogresnihStavkiNaTeret() {
		return brojPogresnihStavkiNaTeret;
	}

	public void setBrojPogresnihStavkiNaTeret(int brojPogresnihStavkiNaTeret) {
		this.brojPogresnihStavkiNaTeret = brojPogresnihStavkiNaTeret;
	}

	public String getStatusNaloga() {
		return statusNaloga;
	}

	public void setStatusNaloga(String statusNaloga) {
		this.statusNaloga = statusNaloga;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DnevnoStanjeRacuna getDnevnoStanjeRacuna() {
		return dnevnoStanjeRacuna;
	}

	public void setDnevnoStanjeRacuna(DnevnoStanjeRacuna dnevnoStanjeRacuna) {
		this.dnevnoStanjeRacuna = dnevnoStanjeRacuna;
	}

	public PravnoLice getPravnoLice() {
		return pravnoLice;
	}

	public void setPravnoLice(PravnoLice pravnoLice) {
		this.pravnoLice = pravnoLice;
	}

	public RacunPravnihLica getRacunPravnihLica() {
		return racunPravnihLica;
	}

	public void setRacunPravnihLica(RacunPravnihLica racunPravnihLica) {
		this.racunPravnihLica = racunPravnihLica;
	}

}*/
