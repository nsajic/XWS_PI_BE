package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IMT103Repository;
import xws_pi_bezb.iservices.IMT103Services;
import xws_pi_bezb.models.MT103;

@Service
public class MT103Services implements IMT103Services {

	@Autowired
	private IMT103Repository repo;

	@Override
	public void save(MT103 mt103) {
		repo.save(mt103);

	}

	@Override
	public void delete(MT103 mt103) {
		repo.delete(mt103);

	}

	@Override
	public List<MT103> findAll() {

		return repo.findAll();
	}

	@Override
	public MT103 findOne(Long id) {

		return repo.findById(id);
	}

	@Override
	public MT103 findByIdPoruke(String idPorukeNaloga) {
		// TODO Auto-generated method stub
		return null;
	}

}
