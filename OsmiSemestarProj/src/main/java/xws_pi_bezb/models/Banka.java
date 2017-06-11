package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "banka")
public class Banka implements Serializable {

	private static final long serialVersionUID = -5328904002193386606L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "naziv_banke", nullable = false)
	private String nazivBanke;
	
	@Column(name = "sifra_banke", nullable = false)
	private int sifraBanke;
	
	@Column(name = "swift_kod", nullable = false)
	private String swiftKod;
	
	@Column(name = "obracunski_racun", nullable = false)
	private String obracunskiRacun;
	/*
	@OneToMany(mappedBy = "obracunskiPeriodBankeDuznika")
	private Set<MedjubankarskiPrenos> bankePosiljaoci; 
	
	@OneToMany(mappedBy = "obracunskiPeriodBankePoverioca")
	private Set<MedjubankarskiPrenos> bankePrimaoci; 
	*/
	@OneToMany(mappedBy = "banka")
	private Set<Racun> racuni;
	
	public Banka(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazivBanke() {
		return nazivBanke;
	}

	public void setNazivBanke(String nazivBanke) {
		this.nazivBanke = nazivBanke;
	}

	public int getSifraBanke() {
		return sifraBanke;
	}

	public void setSifraBanke(int sifraBanke) {
		this.sifraBanke = sifraBanke;
	}

	public String getSwiftKod() {
		return swiftKod;
	}

	public void setSwiftKod(String swiftKod) {
		this.swiftKod = swiftKod;
	}

	public String getObracunskiRacun() {
		return obracunskiRacun;
	}

	public void setObracunskiRacun(String obracunskiRacun) {
		this.obracunskiRacun = obracunskiRacun;
	} 
	
	
	
}
