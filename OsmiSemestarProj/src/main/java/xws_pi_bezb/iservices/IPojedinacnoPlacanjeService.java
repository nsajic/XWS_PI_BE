package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.MT102;
import xws_pi_bezb.models.PojedinacnoPlacanje;

public interface IPojedinacnoPlacanjeService {

	PojedinacnoPlacanje save(PojedinacnoPlacanje pp);

	void delete(PojedinacnoPlacanje pp);

	List<PojedinacnoPlacanje> findAll();

	PojedinacnoPlacanje findOne(Long id);

	List<PojedinacnoPlacanje> findByMt102(MT102 mt102);

}
