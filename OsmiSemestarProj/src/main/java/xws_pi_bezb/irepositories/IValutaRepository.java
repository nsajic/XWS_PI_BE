package xws_pi_bezb.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xws_pi_bezb.models.Valuta;

public interface IValutaRepository extends JpaRepository<Valuta, Long>{
	Valuta findById(Long id);

	Valuta findBySifraValute(String oznakaValute);
}

