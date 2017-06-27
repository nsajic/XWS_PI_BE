package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.MT102;
import xws_pi_bezb.models.PojedinacnoPlacanje;

public interface IMT102Services {

	
	
	
	void save(MT102 mt102);
	void delete(MT102 mt102);
	
	List<MT102> findAll();
	MT102 findOne(Long id);
	
}
