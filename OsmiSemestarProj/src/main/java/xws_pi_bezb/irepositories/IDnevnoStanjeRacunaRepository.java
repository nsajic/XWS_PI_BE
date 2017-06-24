package xws_pi_bezb.irepositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.Racun;

@Repository
@Transactional
public interface IDnevnoStanjeRacunaRepository extends JpaRepository<DnevnoStanjeRacuna, Long> {
	DnevnoStanjeRacuna findOne(Long id);

	List<DnevnoStanjeRacuna> findByRacun(Racun racun);
	
	DnevnoStanjeRacuna findTopByRacunOrderByDatum(Racun racun);

	DnevnoStanjeRacuna findByRacunAndDatum(Racun racunD, Date date);
}
