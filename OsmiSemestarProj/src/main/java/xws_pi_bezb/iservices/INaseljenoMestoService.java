package xws_pi_bezb.iservices;
import java.util.List;

import xws_pi_bezb.models.NaseljenoMesto;


public interface INaseljenoMestoService {
	
	List<NaseljenoMesto> findAll();

	List<NaseljenoMesto> getBySearchText(String serachText);
	
	NaseljenoMesto findOne(Long id);

	void save(NaseljenoMesto naseljenoMesto);

	void delete(Long id);
}
