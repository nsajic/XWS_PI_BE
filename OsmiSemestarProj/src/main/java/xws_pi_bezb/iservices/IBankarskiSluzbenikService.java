package xws_pi_bezb.iservices;

import java.util.List;

import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;

public interface IBankarskiSluzbenikService {

	
	List<BankarskiSluzbenik> findAll();
	
	BankarskiSluzbenik findOne(Long id);

	void save(BankarskiSluzbenik banka);

	void delete(Long id);

	BankarskiSluzbenik findByEmail(String email);
}
