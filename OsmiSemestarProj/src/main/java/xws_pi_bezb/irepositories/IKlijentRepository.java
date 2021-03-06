package xws_pi_bezb.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xws_pi_bezb.models.Klijent;

@Repository
@Transactional
public interface IKlijentRepository extends JpaRepository<Klijent, Long>{
	Klijent findById(Long id);
	
	Klijent findByEmail(String email);


}
