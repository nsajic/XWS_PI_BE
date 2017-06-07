package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IFizickoLiceRepository;
import xws_pi_bezb.iservices.IFizickoLiceService;
import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.PravnoLice;
@Service
public class FizickoLiceService implements IFizickoLiceService{

	@Autowired
	private IFizickoLiceRepository fizickoLiceRepository;
	
	@Override
	public List<FizickoLice> findAll() {
		// TODO Auto-generated method stub
		return fizickoLiceRepository.findAll();
	}

	@Override
	public FizickoLice findOne(Long id) {
		// TODO Auto-generated method stub
		return fizickoLiceRepository.findById(id);
	}

	@Override
	public void save(FizickoLice fizickoLice) {
		fizickoLiceRepository.save(fizickoLice);
		
	}

	@Override
	public void delete(Long id) {
		fizickoLiceRepository.delete(id);
		
	}

}