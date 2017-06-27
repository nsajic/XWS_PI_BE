package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IDelatnostRepository;
import xws_pi_bezb.iservices.IDelatnostService;
import xws_pi_bezb.models.Delatnost;
@Service
public class DelatnostService implements IDelatnostService{

	@Autowired
	private IDelatnostRepository delatnostRepository;
	
	@Override
	public List<Delatnost> findAll() {
		return delatnostRepository.findAll();
	}

	@Override
	public Delatnost findOne(Long id) {
		return delatnostRepository.findById(id);
	}

	@Override
	public void save(Delatnost delatnost) {
		delatnostRepository.save(delatnost);
	}

	@Override
	public void delete(Long id) {
		delatnostRepository.delete(id);
	}

	@Override
	public List<Delatnost> getBySearch(Delatnost delatnost) {
		List<Delatnost> delatnosti = new ArrayList<Delatnost>();
		
		boolean praznaPretraga = true;
		
		for (Delatnost delatnostFor : delatnostRepository.findAll()) {
			if (!Helpers.isNullOrEmpty(delatnost.getNazivDelatnosti())) {
				praznaPretraga = false;
				if (delatnostFor.getNazivDelatnosti().toLowerCase().contains(delatnost.getNazivDelatnosti().toLowerCase())) {
					if(!delatnosti.contains(delatnostFor)){
						delatnosti.add(delatnostFor);
					}
				}
			}
			
		}
		
		if(praznaPretraga){
			delatnosti = delatnostRepository.findAll();
		}
		return delatnosti;
	}

}