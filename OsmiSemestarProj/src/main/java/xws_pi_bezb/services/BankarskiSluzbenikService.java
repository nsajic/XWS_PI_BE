package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IBankarskiSluzbenikRepository;
import xws_pi_bezb.iservices.IBankarskiSluzbenikService;
import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;

@Service
public class BankarskiSluzbenikService implements IBankarskiSluzbenikService {

	@Autowired
	private IBankarskiSluzbenikRepository bankarskiSluzbenikRepository;
	
	@Override
	public List<BankarskiSluzbenik> findAll() {
		return bankarskiSluzbenikRepository.findAll();
	}

	@Override
	public BankarskiSluzbenik findOne(Long id) {
		return bankarskiSluzbenikRepository.findOne(id);
	}

	@Override
	public void save(BankarskiSluzbenik bankarskiSluzbenik) {
		bankarskiSluzbenikRepository.save(bankarskiSluzbenik);
	}

	@Override
	public void delete(Long id) {
		bankarskiSluzbenikRepository.delete(id);
	}

	@Override
	public BankarskiSluzbenik findByEmail(String email) {
		return bankarskiSluzbenikRepository.findByEmail(email);
	}

}
