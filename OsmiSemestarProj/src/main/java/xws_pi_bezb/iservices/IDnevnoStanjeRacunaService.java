package xws_pi_bezb.iservices;

import java.util.Date;
import java.util.List;

import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.Racun;

public interface IDnevnoStanjeRacunaService {

	DnevnoStanjeRacuna findOne(Long id);
	
	void save(DnevnoStanjeRacuna dsr);

	List<DnevnoStanjeRacuna> findByRacun(Racun racun);
	
	DnevnoStanjeRacuna findTopByRacunOrderByDatum(Racun racun);

	DnevnoStanjeRacuna findByRacunAndDatum(Racun racunD, Date date);

	List<DnevnoStanjeRacuna> findByRacunAndDatumBetween(Racun getiDrac, Date datumOd, Date datumDo);
}
