package xws_pi_bezb.iservices;

import java.util.List;

import org.springframework.stereotype.Service;

import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.MedjubankarskiPrenos;

public interface IMedjubankarskiPrenosService {

	void save(MedjubankarskiPrenos medjubankarskiPrenos);
	List<MedjubankarskiPrenos> getAll();
	List<MedjubankarskiPrenos> findByBanke(Banka banka);
}
