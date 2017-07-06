package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IPojedinacnoPlacanje;
import xws_pi_bezb.iservices.IPojedinacnoPlacanjeService;
import xws_pi_bezb.models.MT102;
import xws_pi_bezb.models.PojedinacnoPlacanje;

@Service
public class PojedinacnoPlacanjeService implements IPojedinacnoPlacanjeService{

	
	@Autowired
	private IPojedinacnoPlacanje repo;
	
	
	@Override
	public PojedinacnoPlacanje save(PojedinacnoPlacanje pp) {
		return repo.save(pp);
	}

	@Override
	public void delete(PojedinacnoPlacanje pp) {
		repo.delete(pp);
		
	}

	@Override
	public List<PojedinacnoPlacanje> findAll() {
		
		return repo.findAll();
	}

	@Override
	public PojedinacnoPlacanje findOne(Long id) {
		
		return repo.findById(id);
	}

	@Override
	public List<PojedinacnoPlacanje> findByMt102(MT102 mt102) {
		return repo.findByMt102(mt102);
	}

}
