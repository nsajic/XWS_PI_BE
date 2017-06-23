package xws_pi_bezb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IRolaRepository;
import xws_pi_bezb.iservices.IRolaService;
import xws_pi_bezb.models.Rola;

@Service
public class RolaService implements IRolaService{

	@Autowired
	private IRolaRepository rolaRepository;
	
	@Override
	public Rola findByNaziv(String naziv) {
		return rolaRepository.findByNaziv(naziv);
	}




}
