package xws_pi_bezb.irepositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.Racun;


@Repository
@Transactional
public interface IRacunRepository extends JpaRepository<Racun, Long>{
	
	Racun findById(Long id);
	
	Racun findByBrojRacuna(String brojRacuna);
	//List<NaseljenoMesto> findByDrzava(Drzava drzavaId);

}
