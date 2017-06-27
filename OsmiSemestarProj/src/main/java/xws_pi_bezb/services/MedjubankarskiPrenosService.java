package xws_pi_bezb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IMedjubankarskiPrenosRepository;
import xws_pi_bezb.iservices.IMedjubankarskiPrenosService;
import xws_pi_bezb.models.MedjubankarskiPrenos;


@Service
public class MedjubankarskiPrenosService implements IMedjubankarskiPrenosService {
	
	@Autowired
	private IMedjubankarskiPrenosRepository medjubankarskiPrenosRepository;

	@Override
	public void save(MedjubankarskiPrenos medjubankarskiPrenos) {
		medjubankarskiPrenosRepository.save(medjubankarskiPrenos);
		
	}

	@Override
	public List<MedjubankarskiPrenos> getAll() {
		
		return medjubankarskiPrenosRepository.findAll();
	}	
}
