package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.PojedinacnoPlacanje;

public interface IPojedinacnoPlacanjeService {

	
	
	void save(PojedinacnoPlacanje pp);
	void delete(PojedinacnoPlacanje pp);
	
	List<PojedinacnoPlacanje> findAll();
	PojedinacnoPlacanje findOne(Long id);
	
}
