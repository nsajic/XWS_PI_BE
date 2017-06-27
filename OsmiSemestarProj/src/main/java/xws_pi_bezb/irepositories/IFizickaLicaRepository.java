package xws_pi_bezb.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.Klijent;



@Repository
@Transactional
public interface IFizickaLicaRepository extends JpaRepository<FizickoLice, Long>{
	FizickoLice findById(Long id);
	
	FizickoLice findByEmail(String email);


}

