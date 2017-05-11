package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IDrzavaRepository;
import xws_pi_bezb.iservices.IDrzavaService;
import xws_pi_bezb.models.Drzava;

@Service
public class DrzavaService implements IDrzavaService {

	@Autowired
	private IDrzavaRepository drzavaRepository;

	public List<Drzava> findAll() {
		return drzavaRepository.findAll();
	}

	public Drzava findOne(Long id) {
		return drzavaRepository.findById(id);
	}

	@Override
	public void save(Drzava drzava) {
		drzavaRepository.save(drzava);

	}

	@Override
	public void delete(Long id) {
		drzavaRepository.delete(id);

	}

}
