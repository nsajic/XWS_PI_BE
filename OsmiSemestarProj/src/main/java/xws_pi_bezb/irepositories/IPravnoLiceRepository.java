package xws_pi_bezb.irepositories;

import org.springframework.data.jpa.repository.JpaRepository;

import xws_pi_bezb.models.PravnoLice;

public interface IPravnoLiceRepository extends JpaRepository<PravnoLice, Long>{
	PravnoLice findById(Long id);
}
