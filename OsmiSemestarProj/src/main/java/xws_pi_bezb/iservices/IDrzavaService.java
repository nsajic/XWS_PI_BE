package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.Drzava;

public interface IDrzavaService {

	List<Drzava> findAll();

	List<Drzava> getBySearchText(String searchText);
	
	Drzava findOne(Long id);

	void save(Drzava drzava);

	void delete(Long id);

	
}
