package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.Racun;

public interface IKlijentService {
	
	List<Klijent> findAll();

	//List<Racun> getBySearchText(String serachText);
	
	//List<Racun> getByDrzava(Drzava drzava);
	
	Klijent findOne(Long id);

	void save(Klijent klijent);

	void delete(Long id);
	
}
