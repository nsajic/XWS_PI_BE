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
	
	
	UserDetails loadUserByUsername(String username);
	
	void logoutKorisnik();
	
	


	Klijent findByEmail(String email);

	

	//ide i ovo
	//List<PravnoLice> getPravnaLicaBySearchAndDelatnost(PravnoLice pravnoLice, Delatnost delatnost);
	


	
}
