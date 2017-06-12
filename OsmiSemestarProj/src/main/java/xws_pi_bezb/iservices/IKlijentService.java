package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.korisnici.FizickoLice;
import xws_pi_bezb.models.korisnici.Korisnik;
import xws_pi_bezb.models.korisnici.PravnoLice;

public interface IKlijentService {
	
	List<Korisnik> findAll();
	
	List<PravnoLice> getPravnaLica();
	
	List<FizickoLice> getFizickaLica();
	
	Korisnik findOne(Long id);

	void save(Korisnik klijent);

	void delete(Long id);

	List<PravnoLice> getPravnaLicaBySearch(PravnoLice pravnoLice);
	
	List<FizickoLice> getFizickaLicaBySearch(FizickoLice fizickoLice);

	Korisnik findByEmail(String email);

	List<PravnoLice> findByDelatnost(Long id);

	List<PravnoLice> getPravnaLicaBySearchAndDelatnost(PravnoLice pravnoLice, Delatnost delatnost);
	
}
