package xws_pi_bezb.irepositories;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.Valuta;

@Repository
@Transactional
public interface IValutaRepository extends JpaRepository<Valuta, Long>{
	Valuta findById(Long id);

	Valuta findBySifraValute(String oznakaValute);
}

