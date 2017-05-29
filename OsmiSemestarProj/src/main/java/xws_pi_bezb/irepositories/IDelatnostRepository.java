package xws_pi_bezb.irepositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.Delatnost;

@Repository
@Transactional
public interface IDelatnostRepository extends JpaRepository<Delatnost, Long>{
	Delatnost findById(Long id);
}
