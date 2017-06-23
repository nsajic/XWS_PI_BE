package xws_pi_bezb.irepositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.Racun;


@Repository
@Transactional
public interface IRacunRepository extends JpaRepository<Racun, Long>{
	
	Racun findById(Long id);

	List<Racun> findByBanka(Banka banka);
	
	List<Racun> findAll();

}
