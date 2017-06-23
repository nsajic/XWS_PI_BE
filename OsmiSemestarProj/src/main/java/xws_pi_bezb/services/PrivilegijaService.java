/*package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IPrivilegijaRepository;
import xws_pi_bezb.iservices.IPrivilegijaService;
import xws_pi_bezb.models.Privilegija;
import xws_pi_bezb.models.Rola;

@Service
@Component
public class PrivilegijaService implements IPrivilegijaService{

	@Autowired
	private IPrivilegijaRepository privilegijaService;
	
	
	@Override
	public Privilegija findOne(Long id) {
		return privilegijaService.findOne(id);
	}

	@Override
	public void save(Privilegija privilegija) {
		privilegijaService.save (privilegija);
		
	}

	@Override
	public void delete(Long id) {
		privilegijaService.delete(id);
	}

	@Override
	public List<Privilegija> getByRole(Rola role) {
		return privilegijaService.findByRole(role);
	}

	@Override
	public Privilegija getByNaziv(String value) {
		return privilegijaService.findByNaziv(value);
	}

}
*/
