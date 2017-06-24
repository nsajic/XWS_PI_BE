package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IKlijentRepository;
import xws_pi_bezb.irepositories.IPravnoLiceRepository;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.PravnoLice;

@Service
public class KlijentService implements IKlijentService {

	@Autowired
	private IKlijentRepository klijentRepository;
	
	@Autowired
	private IPravnoLiceRepository pravnoLiceRepository;

	@Override
	public List<Klijent> findAll() {
		return klijentRepository.findAll();
	}

	@Override
	public Klijent findByEmail(String email) {
		return klijentRepository.findByEmail(email);
	}

	

	
	/*private Collection<? extends GrantedAuthority> getAuthorities(Rola rola) {
		return getGrantedAuthorities(getPrivilegije(rola));
	}*/
	
	@Override
	public void logoutKorisnik() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(privileges == null)
			return authorities;
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return null;
	}
	
	/*private List<String> getPrivilegije(Rola rola) {
		List<String> privilegije = new ArrayList<>();
		if(rola == null)
			return privilegije;
		
		List<Privilegija> collection = new ArrayList<>();	
		collection.addAll(rola.getPrivilegije());
		
		for (Privilegija priv : collection) {
			privilegije.add(priv.getNaziv());
		}
		return privilegije;
	}
	*/

	

	
}
