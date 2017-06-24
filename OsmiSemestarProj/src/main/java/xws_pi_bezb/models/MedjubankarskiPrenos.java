package xws_pi_bezb.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//NE TREBA CRUD

@Entity
@Table(name = "medjubankarski_prenos")
public class MedjubankarskiPrenos {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "vrsta_poruke", nullable = false)
	private String vrstaPoruke;

	@Column(name = "datum", nullable = false)
	private Date datum;

	@Column(name = "iznos", nullable = false)
	private Double iznos;

	@Column(name = "swift_kod_banke_duznika", nullable = false)
	private String swiftKodBankeDuznika;

	@Column(name = "obracunski_period_banke_duznika", nullable = false)
	private String obracunskiPeriodBankeDuznika;

	@Column(name = "swift_kod_banke_poverioca", nullable = false)
	private String swiftKodBankePoverioca;

	@Column(name = "obracunski_period_banke_poverioca", nullable = false)
	private String obracunskiPeriodBankePoverioca;

	@Column(name = "datum_valute", nullable = false)
	private Date datumValute;

	@ManyToOne
	private Banka bankaPrimalac;

	@ManyToOne
	private Banka bankaPosiljalac;

	@ManyToOne
	private Valuta valuta;

	@OneToMany(mappedBy = "medjubankarskiPrenos")
	private Set<StavkaPrenosa> stavkePrenosa;
	
	public MedjubankarskiPrenos(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVrstaPoruke() {
		return vrstaPoruke;
	}

	public void setVrstaPoruke(String vrstaPoruke) {
		this.vrstaPoruke = vrstaPoruke;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Double getIznos() {
		return iznos;
	}

	public void setIznos(Double iznos) {
		this.iznos = iznos;
	}

	public String getSwiftKodBankeDuznika() {
		return swiftKodBankeDuznika;
	}

	public void setSwiftKodBankeDuznika(String swiftKodBankeDuznika) {
		this.swiftKodBankeDuznika = swiftKodBankeDuznika;
	}

	public String getObracunskiPeriodBankeDuznika() {
		return obracunskiPeriodBankeDuznika;
	}

	public void setObracunskiPeriodBankeDuznika(String obracunskiPeriodBankeDuznika) {
		this.obracunskiPeriodBankeDuznika = obracunskiPeriodBankeDuznika;
	}

	public String getSwiftKodBankePoverioca() {
		return swiftKodBankePoverioca;
	}

	public void setSwiftKodBankePoverioca(String swiftKodBankePoverioca) {
		this.swiftKodBankePoverioca = swiftKodBankePoverioca;
	}

	public String getObracunskiPeriodBankePoverioca() {
		return obracunskiPeriodBankePoverioca;
	}

	public void setObracunskiPeriodBankePoverioca(String obracunskiPeriodBankePoverioca) {
		this.obracunskiPeriodBankePoverioca = obracunskiPeriodBankePoverioca;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public Banka getBankaPrimalac() {
		return bankaPrimalac;
	}

	public void setBankaPrimalac(Banka bankaPrimalac) {
		this.bankaPrimalac = bankaPrimalac;
	}

	public Banka getBankaPosiljalac() {
		return bankaPosiljalac;
	}

	public void setBankaPosiljalac(Banka bankaPosiljalac) {
		this.bankaPosiljalac = bankaPosiljalac;
	}

	public Valuta getValuta() {
		return valuta;
	}

	public void setValuta(Valuta valuta) {
		this.valuta = valuta;
	}

}
