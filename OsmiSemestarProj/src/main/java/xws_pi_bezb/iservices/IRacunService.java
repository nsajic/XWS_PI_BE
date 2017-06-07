package xws_pi_bezb.iservices;
import java.util.List;

import xws_pi_bezb.models.Racun;


public interface IRacunService {
	
	List<Racun> findAll();

	//List<Racun> getBySearchText(String serachText);
	
	//List<Racun> getByDrzava(Drzava drzava);
	
	Racun findOne(Long id);

	void save(Racun racun);

	void delete(Long id);
}
