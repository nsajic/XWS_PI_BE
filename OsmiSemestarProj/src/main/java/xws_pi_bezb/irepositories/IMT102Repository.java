package xws_pi_bezb.irepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xws_pi_bezb.models.MT102;
import xws_pi_bezb.models.PojedinacnoPlacanje;

public interface IMT102Repository extends JpaRepository<MT102, Long>{

	List<MT102> findAll();
	MT102 findById(Long id);
	
	
	
}
