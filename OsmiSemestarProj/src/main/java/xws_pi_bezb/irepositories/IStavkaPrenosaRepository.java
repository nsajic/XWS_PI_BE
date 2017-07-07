package xws_pi_bezb.irepositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.StavkaPrenosa;

@Repository
@Transactional
public interface IStavkaPrenosaRepository extends JpaRepository<StavkaPrenosa, Long> {

	List<StavkaPrenosa> findByAnalitikaIzvodaId(Long id);

	
}
