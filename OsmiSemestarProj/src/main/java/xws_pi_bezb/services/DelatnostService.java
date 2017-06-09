package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IDelatnostRepository;
import xws_pi_bezb.iservices.IDelatnostService;
import xws_pi_bezb.models.Delatnost;
@Service
public class DelatnostService implements IDelatnostService{

	@Autowired
	private IDelatnostRepository delatnostRepository;
	
	@Override
	public List<Delatnost> findAll() {
		// TODO Auto-generated method stub
		return delatnostRepository.findAll();
	}

	@Override
	public Delatnost findOne(Long id) {
		// TODO Auto-generated method stub
		return delatnostRepository.findById(id);
	}

	@Override
	public void save(Delatnost delatnost) {
		// TODO Auto-generated method stub
		delatnostRepository.save(delatnost);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		delatnostRepository.delete(id);
	}

	@Override
	public List<Delatnost> getBySearch(Delatnost delatnost) {
		List<Delatnost> delatnosti = new ArrayList<Delatnost>();
		
		for (Delatnost delatnostFor : delatnostRepository.findAll()) {
			if (!delatnost.getNazivDelatnosti().isEmpty()) {
				if (delatnostFor.getNazivDelatnosti().toLowerCase().contains(delatnost.getNazivDelatnosti().toLowerCase())) {
					if(!delatnosti.contains(delatnostFor)){
						delatnosti.add(delatnostFor);
					}
				}
			}
		}
		return delatnosti;
	}

}