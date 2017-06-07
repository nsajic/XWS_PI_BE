package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IKlijentRepository;
import xws_pi_bezb.irepositories.IValutaRepository;
import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.Valuta;
@Service
public class ValutaService implements IValutaService{

	@Autowired
	private IValutaRepository valutaRepository;
	
	@Override
	public List<Valuta> findAll() {
		// TODO Auto-generated method stub
		return valutaRepository.findAll();
	}

	@Override
	public Valuta findOne(Long id) {
		// TODO Auto-generated method stub
		return valutaRepository.findById(id);
	}

	@Override
	public void save(Valuta valuta) {
		// TODO Auto-generated method stub
		valutaRepository.save(valuta);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		valutaRepository.delete(id);
	}

}
