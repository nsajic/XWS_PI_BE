/*package xws_pi_bezb.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "vrsta_placanja")
public class VrstaPlacanja implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long vrstaPlacanjaId; // oznaka vrste
	
	@NotNull
    @Column(name = "oznaka_vrste_placanja", nullable = false)
    private Integer oznakaVrstePlacanja;

    @NotNull
    @Column(name = "naziv_vrste_placanja", nullable = false)
    private String nazivVrstePlacanja;
    
    @OneToMany(mappedBy="vrstaPlacanja")
    private Set<AnalitikaIzvoda> analitikeIzvoda;
    
    public VrstaPlacanja(){}
	
    public Long getId() {
        return vrstaPlacanjaId;
    }

    public void setId(Long id) {
        this.vrstaPlacanjaId = id;
    }

    public Integer getOznakaVrstePlacanja() {
        return oznakaVrstePlacanja;
    }

    public void setOznakaVrstePlacanja(Integer oznakaVrstePlacanja) {
        this.oznakaVrstePlacanja = oznakaVrstePlacanja;
    }

    public String getNazivVrstePlacanja() {
        return nazivVrstePlacanja;
    }

    public void setNazivVrstePlacanja(String nazivVrstePlacanja) {
        this.nazivVrstePlacanja = nazivVrstePlacanja;
    }
    
    
}*/
