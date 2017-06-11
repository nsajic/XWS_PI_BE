package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.Valuta;

public interface IValutaService {
	
	List<Valuta> findAll();
	
	Valuta findOne(Long id);

	void save(Valuta valuta);

	void delete(Long id);

	List<Valuta> getBySearch(Valuta valuta);
}