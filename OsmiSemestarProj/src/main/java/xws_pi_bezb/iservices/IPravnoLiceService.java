package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.PravnoLice;


public interface IPravnoLiceService {

	
	List<PravnoLice> findAll();

	//List<Racun> getBySearchText(String serachText);
	
	//List<Racun> getByDrzava(Drzava drzava);
	
	PravnoLice findOne(Long id);

	void save(PravnoLice pravnoLice);

	void delete(Long id);
}
