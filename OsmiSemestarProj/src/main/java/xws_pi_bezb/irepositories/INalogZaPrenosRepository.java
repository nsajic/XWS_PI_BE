package xws_pi_bezb.irepositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.NalogZaPrenos;
@Repository
@Transactional
public interface INalogZaPrenosRepository extends JpaRepository<NalogZaPrenos, Long>{

	List<NalogZaPrenos> findAll();
	NalogZaPrenos findById(Long id);
	
	
	
}
