package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.PravnoLice;

public interface IKlijentService {
	
	List<Klijent> findAll();
	
	List<PravnoLice> getPravnaLica();
	
	List<FizickoLice> getFizickaLica();
	
	Klijent findOne(Long id);

	void save(Klijent klijent);

	void delete(Long id);

	List<PravnoLice> getPravnaLicaBySearch(PravnoLice pravnoLice);
	
	List<PravnoLice> getPravnaLicaBySearch(FizickoLice fizickoLice);
	
}
