package xws_pi_bezb.irepositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.korisnici.PravnoLice;

@Repository
@Transactional
public interface IPravnoLiceRepository extends JpaRepository<PravnoLice,Long> {

	List<PravnoLice> findByDelatnostId(Long id);

}