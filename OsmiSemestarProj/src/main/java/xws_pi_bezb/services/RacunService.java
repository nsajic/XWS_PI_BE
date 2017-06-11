package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IRacunRepository;
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.models.Racun;


@Service
public class RacunService implements IRacunService{
	
	@Autowired
	private IRacunRepository racunRepository;
	
	@Override
	public List<Racun> findAll() {
		// TODO Auto-generated method stub
		return racunRepository.findAll();
	}

	@Override
	public Racun findOne(Long id) {
		// TODO Auto-generated method stub
		return racunRepository.findById(id);
	}

	@Override
	public void save(Racun racun) {
		racunRepository.save(racun);
		
	}

	@Override
	public void delete(Long id) {
		racunRepository.delete(id);
		
	}

	@Override
	public List<Racun> getRacunBySearch(Racun racun) {
		List<Racun> racuni = new ArrayList<Racun>();

		boolean praznaPretraga = true;

		for (Racun racunFor : findAll()) {
			if (!Helpers.isNullOrEmpty(racun.getBrojRacuna())) {
				praznaPretraga = false;
				if (racunFor.getBrojRacuna().toLowerCase().contains(racun.getBrojRacuna().toLowerCase())) {
					if (!racuni.contains(racunFor)) {
						racuni.add(racunFor);
					}
				}
			}
			//TODO: Search for non text field attributes
			
			if (praznaPretraga) {
				racuni = findAll();
			}
		}
		
		
		return racuni;
	}

}
