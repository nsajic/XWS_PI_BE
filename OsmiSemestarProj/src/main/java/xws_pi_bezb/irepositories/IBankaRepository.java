package xws_pi_bezb.irepositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.Racun;

@Repository
@Transactional
public interface IBankaRepository extends JpaRepository<Banka, Long>{
	Banka findById(Long id);

	Banka findByRacuni(Racun racuni);

	Banka findBySwiftKod(String swift);
}