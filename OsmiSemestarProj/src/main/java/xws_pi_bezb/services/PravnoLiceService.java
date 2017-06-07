package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IKlijentRepository;
import xws_pi_bezb.irepositories.IPravnoLiceRepository;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.iservices.IPravnoLiceService;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.PravnoLice;
@Service
public class PravnoLiceService implements IPravnoLiceService{

	@Autowired
	private IPravnoLiceRepository pravnoLiceRepository;
	
	@Override
	public List<PravnoLice> findAll() {
		// TODO Auto-generated method stub
		return pravnoLiceRepository.findAll();
	}

	@Override
	public PravnoLice findOne(Long id) {
		// TODO Auto-generated method stub
		return pravnoLiceRepository.findById(id);
	}

	@Override
	public void save(PravnoLice pravnoLice) {
		pravnoLiceRepository.save(pravnoLice);
		
	}

	@Override
	public void delete(Long id) {
		pravnoLiceRepository.delete(id);
		
	}

}