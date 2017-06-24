package xws_pi_bezb.services;

import org.springframework.beans.factory.annotation.Autowired;

import xws_pi_bezb.irepositories.IStavkaPrenosaRepository;
import xws_pi_bezb.iservices.IStavkaPrenosaService;
import xws_pi_bezb.models.StavkaPrenosa;


public class StavkaPrenosaService implements IStavkaPrenosaService {

	@Autowired
	private IStavkaPrenosaRepository stavkaPrenosaRepository;

	@Override
	public void save(StavkaPrenosa analitikaIzvoda) {
		stavkaPrenosaRepository.save(analitikaIzvoda);
		
	}
	
}
