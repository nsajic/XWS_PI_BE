package xws_pi_bezb.services;

import org.springframework.beans.factory.annotation.Autowired;

import xws_pi_bezb.irepositories.IZatvaranjeRacunaRepository;
import xws_pi_bezb.iservices.IZatvaranjeRacunaService;
import xws_pi_bezb.models.ZatvaranjeRacuna;


public class ZatvaranjeRacunaService implements IZatvaranjeRacunaService {

	@Autowired
	private IZatvaranjeRacunaRepository zatvaranjeRacunaRepository;

	@Override
	public void save(ZatvaranjeRacuna zatvaranjeRacuna) {
		zatvaranjeRacunaRepository.save(zatvaranjeRacuna);
		
	}
}
