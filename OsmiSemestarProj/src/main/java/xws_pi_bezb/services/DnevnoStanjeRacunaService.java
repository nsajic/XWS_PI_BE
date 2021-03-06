package xws_pi_bezb.services;

import java.util.Date;
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

	@Override
	public DnevnoStanjeRacuna findTopByRacunOrderByDatum(Racun racun) {
		return dnevnoStanjeRacunaRepository.findTopByRacunOrderByDatum(racun);
	}

	@Override
	public DnevnoStanjeRacuna findByRacunAndDatum(Racun racunD, Date date) {
		return dnevnoStanjeRacunaRepository.findByRacunAndDatum(racunD, date);
	}

	@Override
	public List<DnevnoStanjeRacuna> findByRacunAndDatumBetween(Racun getiDrac, Date datumOd, Date datumDo) {
		return dnevnoStanjeRacunaRepository.findByRacunAndDatumBetween(getiDrac, datumOd, datumDo);
	}

}
