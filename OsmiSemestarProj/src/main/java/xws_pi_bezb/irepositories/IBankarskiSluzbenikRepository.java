

package xws_pi_bezb.irepositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;

@Repository
@Transactional
public interface IBankarskiSluzbenikRepository extends JpaRepository<BankarskiSluzbenik, Long>{
	
	BankarskiSluzbenik findById(Long id);

	BankarskiSluzbenik findByEmail(String email);
}