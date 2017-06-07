package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Delatnost;

public interface IDelatnostService {
	
	List<Delatnost> findAll();

	//List<Racun> getBySearchText(String serachText);
	
	//List<Racun> getByDrzava(Drzava drzava);
	
	Delatnost findOne(Long id);

	void save(Delatnost delatnost);

	void delete(Long id);
	
}
