package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.MT103;

public interface IMT103Services {

	
	
	
	void save(MT103 mt102);
	void delete(MT103 mt102);
	
	List<MT103> findAll();
	MT103 findOne(Long id);
}
