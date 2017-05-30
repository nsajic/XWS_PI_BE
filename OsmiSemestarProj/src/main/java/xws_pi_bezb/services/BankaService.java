package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IBankaRepository;
import xws_pi_bezb.iservices.IBankaService;
import xws_pi_bezb.models.Banka;

@Service
public class BankaService implements IBankaService {

	@Autowired
	private IBankaRepository bankaRepository;

	public List<Banka> findAll() {
		return bankaRepository.findAll();
	}

	public Banka findOne(Long id) {
		return bankaRepository.findById(id);
	}

	@Override
	public void save(Banka banka) {
		bankaRepository.save(banka);

	}

	@Override
	public void delete(Long id) {
		bankaRepository.delete(id);

	}



}