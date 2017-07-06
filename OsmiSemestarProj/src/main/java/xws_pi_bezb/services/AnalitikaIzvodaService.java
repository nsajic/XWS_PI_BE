package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IAnalitikaIzvodaRepository;
import xws_pi_bezb.iservices.IAnalitikaIzvodaService;
import xws_pi_bezb.models.AnalitikaIzvoda;
import xws_pi_bezb.models.DnevnoStanjeRacuna;

@Service
public class AnalitikaIzvodaService implements IAnalitikaIzvodaService
{
	@Autowired
	private IAnalitikaIzvodaRepository analitikaRepository;

	@Override
	public void save(AnalitikaIzvoda analitikaIzvoda) {
		analitikaRepository.save(analitikaIzvoda);
		
	}

	@Override
	public List<AnalitikaIzvoda> findByDnevnoStanjeRacuna(DnevnoStanjeRacuna dnevnoStanjeRacuna) {
		return analitikaRepository.findByDnevnoStanjeRacuna(dnevnoStanjeRacuna);
	}

	@Override
	public List<AnalitikaIzvoda> findBySmer(String string) {
		
		return analitikaRepository.findBySmer(string);
	}

}
