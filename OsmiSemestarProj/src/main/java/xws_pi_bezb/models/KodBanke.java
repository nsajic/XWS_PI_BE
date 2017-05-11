/*package xws_pi_bezb.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kod_banke")
public class KodBanke  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long kodBankeId;
	
	@Column(name = "sifra_banke", nullable = false)
	private int sifraBanke;
	
	@Column(name = "swift_kod", nullable = false)
	private String swiftKod;
	
	@ManyToOne
	private PravnoLice pravnoLice;  //TODO:PK
	
	public KodBanke(){}

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

	public PravnoLice getPravnoLice() {
		return pravnoLice;
	}

	public void setPravnoLice(PravnoLice pravnoLice) {
		this.pravnoLice = pravnoLice;
	}
	
	
}*/
