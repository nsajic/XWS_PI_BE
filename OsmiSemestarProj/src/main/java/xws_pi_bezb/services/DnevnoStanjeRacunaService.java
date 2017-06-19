package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IDnevnoStanjeRacunaRepository;
import xws_pi_bezb.iservices.IDnevnoStanjeRacunaService;
import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.Racun;

@Service
public class DnevnoStanjeRacunaService implements IDnevnoStanjeRacunaService {

	@Autowired
	private IDnevnoStanjeRacunaRepository dnevnoStanjeRacunaRepository;
	
	@Override
	public DnevnoStanjeRacuna findOne(Long id) {
		return dnevnoStanjeRacunaRepository.findOne(id);
	}

	@Override
	public List<DnevnoStanjeRacuna> findByRacun(Racun racun) {
		return dnevnoStanjeRacunaRepository.findByRacun(racun);
	}

	@Override
	public void save(DnevnoStanjeRacuna dsr) {
		dnevnoStanjeRacunaRepository.save(dsr);
		
	}

}
