package xws_pi_bezb.irepositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.DnevnoStanjeRacuna;

@Repository
@Transactional
public interface IDnevnoStanjeRacunaRepository extends JpaRepository<DnevnoStanjeRacuna, Long> {
	DnevnoStanjeRacuna findOne(Long id);

	List<DnevnoStanjeRacuna> findByRacunId(Long id);
	
	
}
