package xws_pi_bezb.models;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "kursna_lista")
public class KursnaLista implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "datum", nullable = false)
	private ZonedDateTime datum;

	@NotNull
	@Column(name = "broj_kursne_liste", nullable = false)
	private Integer brojKursneListe;

	@NotNull
	@Column(name = "datum_primene", nullable = false)
	private ZonedDateTime primenjujeSeOd;
	
	@OneToMany(mappedBy = "kursna_lista")
    private Set<KursUValuti> kurseviUValuti;
	
	@ManyToOne //TODO:PK
	private PravnoLice pravnoLice;
	
	public KursnaLista() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDatum() {
		return datum;
	}

	public void setDatum(ZonedDateTime datum) {
		this.datum = datum;
	}

	public int getBrojKursneListe() {
		return brojKursneListe;
	}

	public void setBrojKursneListe(int brojKursneListe) {
		this.brojKursneListe = brojKursneListe;
	}

	public ZonedDateTime getPrimenjujeSeOd() {
		return primenjujeSeOd;
	}

	public void setPrimenjujeSeOd(ZonedDateTime primenjujeSeOd) {
		this.primenjujeSeOd = primenjujeSeOd;
	}

	public PravnoLice getPravnoLice() {
		return pravnoLice;
	}

	public void setPravnoLice(PravnoLice pravnoLice) {
		this.pravnoLice = pravnoLice;
	}

	public void setBrojKursneListe(Integer brojKursneListe) {
		this.brojKursneListe = brojKursneListe;
	}
	
	
}
