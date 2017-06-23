package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Privilegija;
import xws_pi_bezb.models.Rola;

public interface IPrivilegijaService {
	
	Privilegija findOne(Long id);

	void save(Privilegija privilegija);

	void delete(Long id);
	
	List<Privilegija> getByRole(Rola role);

	Privilegija getByNaziv(String value);
	
}