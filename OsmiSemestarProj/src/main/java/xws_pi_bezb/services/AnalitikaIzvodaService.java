package xws_pi_bezb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IAnalitikaIzvodaRepository;
import xws_pi_bezb.iservices.IAnalitikaIzvodaService;
import xws_pi_bezb.models.AnalitikaIzvoda;

@Service
public class AnalitikaIzvodaService implements IAnalitikaIzvodaService
{
	@Autowired
	private IAnalitikaIzvodaRepository analitikaRepository;

	@Override
	public void save(AnalitikaIzvoda analitikaIzvoda) {
		analitikaRepository.save(analitikaIzvoda);
		
	}

}
