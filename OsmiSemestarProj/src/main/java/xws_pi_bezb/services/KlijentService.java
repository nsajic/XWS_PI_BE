package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IKlijentRepository;
import xws_pi_bezb.irepositories.IRacunRepository;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.models.Klijent;
@Service
public class KlijentService implements IKlijentService{

	@Autowired
	private IKlijentRepository klijentRepository;
	
	@Override
	public List<Klijent> findAll() {
		// TODO Auto-generated method stub
		return klijentRepository.findAll();
	}

	@Override
	public Klijent findOne(Long id) {
		// TODO Auto-generated method stub
		return klijentRepository.findById(id);
	}

	@Override
	public void save(Klijent klijent) {
		klijentRepository.save(klijent);
		
	}

	@Override
	public void delete(Long id) {
		klijentRepository.delete(id);
		
	}

}
