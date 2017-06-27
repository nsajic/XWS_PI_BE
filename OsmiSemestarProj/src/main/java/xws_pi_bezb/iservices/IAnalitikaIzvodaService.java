package xws_pi_bezb.iservices;


import java.util.List;

import xws_pi_bezb.models.AnalitikaIzvoda;
import xws_pi_bezb.models.DnevnoStanjeRacuna;

public interface IAnalitikaIzvodaService{

	void save (AnalitikaIzvoda analitikaIzvoda);

	List<AnalitikaIzvoda> findByDnevnoStanjeRacuna(DnevnoStanjeRacuna dnevnoStanjeRacuna);

	List<AnalitikaIzvoda> findBySmer(String string);
}
