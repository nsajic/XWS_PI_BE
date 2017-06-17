package xws_pi_bezb.irepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xws_pi_bezb.models.korisnici.Korisnik;
import xws_pi_bezb.models.korisnici.PravnoLice;

@Repository
@Transactional
public interface IKlijentRepository extends JpaRepository<Korisnik, Long>{
	Korisnik findById(Long id);
	
	Korisnik findByEmail(String email);


}
