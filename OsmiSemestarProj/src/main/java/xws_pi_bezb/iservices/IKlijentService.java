package xws_pi_bezb.iservices;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.PravnoLice;

public interface IKlijentService extends UserDetailsService {
	
	List<Klijent> findAll();
	
	List<PravnoLice> getPravnaLica();
	
	List<FizickoLice> getFizickaLica();
	
	Klijent findOne(Long id);

	void save(Klijent klijent);

	void delete(Long id);

	List<PravnoLice> getPravnaLicaBySearch(PravnoLice pravnoLice);
	
	List<FizickoLice> getFizickaLicaBySearch(FizickoLice fizickoLice);

	Klijent findByEmail(String email);

	List<PravnoLice> findByDelatnost(Long id);

	List<PravnoLice> getPravnaLicaBySearchAndDelatnost(PravnoLice pravnoLice, Delatnost delatnost);
	
	UserDetails loadUserByUsername(String username);
	
	void logoutKorisnik();
	
}
