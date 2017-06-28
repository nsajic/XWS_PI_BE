package xws_pi_bezb.irepositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xws_pi_bezb.models.MT103;

@Repository
@Transactional
public interface IMT103Repository extends JpaRepository<MT103, Long> {

	List<MT103> findAll();

	MT103 findById(Long id);

	MT103 findByIdPoruke(String idPorukeNaloga);

}
