package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Delatnost;

public interface IDelatnostService {
	
	List<Delatnost> findAll();
	
	Delatnost findOne(Long id);

	void save(Delatnost delatnost);

	void delete(Long id);

	List<Delatnost> getBySearch(Delatnost delatnost);
}
