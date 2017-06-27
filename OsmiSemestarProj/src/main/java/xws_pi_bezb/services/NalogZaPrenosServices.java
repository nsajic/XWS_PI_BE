package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.INalogZaPrenosRepository;
import xws_pi_bezb.iservices.INalogZaPrenosServices;
import xws_pi_bezb.models.NalogZaPrenos;
@Service
public class NalogZaPrenosServices implements INalogZaPrenosServices{

	@Autowired
	private INalogZaPrenosRepository repo;
	
	@Override
	public void save(NalogZaPrenos nalog) {
		repo.save(nalog);
		
	}

	@Override
	public void delete(NalogZaPrenos nalog) {
		
		repo.delete(nalog);
	}

	@Override
	public List<NalogZaPrenos> findAll() {
		
		return repo.findAll();
	}

	@Override
	public NalogZaPrenos findOne(Long id) {
		
		return repo.findById(id);
	}

}
