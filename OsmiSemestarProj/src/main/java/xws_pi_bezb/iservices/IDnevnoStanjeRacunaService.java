package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.Racun;

public interface IDnevnoStanjeRacunaService {

	DnevnoStanjeRacuna findOne(Long id);
	
	void save(DnevnoStanjeRacuna dsr);

	List<DnevnoStanjeRacuna> findByRacun(Racun racun);
	
}
