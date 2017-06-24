package xws_pi_bezb.iservices;

import java.util.List;

import org.springframework.stereotype.Service;

import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.PravnoLice;


@Service
public interface IFizickaLicaService  {
	
	FizickoLice findById(Long id);
	
	FizickoLice findByEmail(String email);
	
	void delete(Long id);
	
	void save(FizickoLice fizickoLice);
	
	List<FizickoLice> getFizickaLica();
	
	List<FizickoLice> getFizickaLicaByBanka(Long bankId);
	
	FizickoLice findOne(Long id);
	
	List<FizickoLice> getFizickaLicaBySearch(FizickoLice fizickoLice);
	
	
	


}
