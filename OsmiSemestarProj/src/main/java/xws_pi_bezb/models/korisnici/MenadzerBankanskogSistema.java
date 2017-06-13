package xws_pi_bezb.models.korisnici;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class MenadzerBankanskogSistema extends Korisnik implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2127543503576018306L;

	
	
}
