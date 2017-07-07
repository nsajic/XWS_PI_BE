package xws_pi_bezb.irepositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.MedjubankarskiPrenos;

@Repository
@Transactional
public interface IMedjubankarskiPrenosRepository extends JpaRepository<MedjubankarskiPrenos, Long> {

	List<MedjubankarskiPrenos> findByBankaPrimalacOrBankaPosiljalac(Banka banka, Banka banka2);

	List<MedjubankarskiPrenos> findByBankaPrimalac(Banka banka);

	List<MedjubankarskiPrenos> findByBankaPosiljalac(Banka banka);


}
