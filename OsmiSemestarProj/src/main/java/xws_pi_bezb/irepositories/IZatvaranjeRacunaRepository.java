package xws_pi_bezb.irepositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.ZatvaranjeRacuna;

@Transactional
@Repository
public interface IZatvaranjeRacunaRepository extends JpaRepository<ZatvaranjeRacuna, Long> {

}
