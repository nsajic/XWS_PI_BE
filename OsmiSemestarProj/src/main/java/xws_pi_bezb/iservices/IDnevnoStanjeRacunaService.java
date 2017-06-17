package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.DnevnoStanjeRacuna;

public interface IDnevnoStanjeRacunaService {

	DnevnoStanjeRacuna findOne(Long id);
	
	List<DnevnoStanjeRacuna> findByRacunId(Long id);
	
}
