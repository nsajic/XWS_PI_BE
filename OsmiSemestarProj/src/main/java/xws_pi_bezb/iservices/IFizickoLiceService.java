package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.PravnoLice;

public interface IFizickoLiceService {

	List<FizickoLice> findAll();

	//List<Racun> getBySearchText(String serachText);
	
	//List<Racun> getByDrzava(Drzava drzava);
	
	FizickoLice findOne(Long id);

	void save(FizickoLice fizickoLice);

	void delete(Long id);
}
