package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.PravnoLice;

public interface IPravnaLicaService {

	
	List<PravnoLice> getPravnaLicaBySearch(PravnoLice pravnoLice);

	List<PravnoLice> getPravnaLica();
	
	List<PravnoLice> getPravnaLicaBySearchAndDelatnost(PravnoLice pravnoLice, Delatnost delatnost);
	
	void delete(Long id);
	
	void save(PravnoLice pravnoLice);
	
	PravnoLice findOne(Long id);
	
	List<PravnoLice> findByDelatnost(Long id);
	
}
