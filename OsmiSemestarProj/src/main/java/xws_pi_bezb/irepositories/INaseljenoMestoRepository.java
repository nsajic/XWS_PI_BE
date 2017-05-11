package xws_pi_bezb.irepositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.Drzava;
import xws_pi_bezb.models.NaseljenoMesto;

@Repository
@Transactional
public interface INaseljenoMestoRepository extends JpaRepository<NaseljenoMesto, Long>{
	
	NaseljenoMesto findById(Long id);
	List<NaseljenoMesto> findByDrzava(Drzava drzavaId);

}
