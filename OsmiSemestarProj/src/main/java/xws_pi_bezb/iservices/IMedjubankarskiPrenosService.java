package xws_pi_bezb.iservices;

import org.springframework.stereotype.Service;

import xws_pi_bezb.models.MedjubankarskiPrenos;

@Service
public interface IMedjubankarskiPrenosService {

	void save(MedjubankarskiPrenos medjubankarskiPrenos);
	
}
