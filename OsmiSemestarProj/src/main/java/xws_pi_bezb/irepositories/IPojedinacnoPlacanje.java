package xws_pi_bezb.irepositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.PojedinacnoPlacanje;

@Repository
@Transactional
public interface IPojedinacnoPlacanje extends JpaRepository<PojedinacnoPlacanje, Long>{

	List<PojedinacnoPlacanje> findAll();
	PojedinacnoPlacanje findById(Long id);
	PojedinacnoPlacanje save(PojedinacnoPlacanje pp);
	
}
