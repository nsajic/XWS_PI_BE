//package xws_pi_bezb.irepositories;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import xws_pi_bezb.models.Privilegija;
//import xws_pi_bezb.models.Rola;
//
//@Repository
//@Transactional
//public interface IPrivilegijaRepository extends JpaRepository<Privilegija, Long> {
//	
//	Privilegija findById (Long id);
//	
//	List<Privilegija> findByRole(Rola rola);
//	
//	Privilegija findByNaziv(String naziv);
//	
//}
