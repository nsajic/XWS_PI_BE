package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IAnalitikaIzvodaRepository;
import xws_pi_bezb.irepositories.IMedjubankarskiPrenosRepository;
import xws_pi_bezb.irepositories.IStavkaPrenosaRepository;
import xws_pi_bezb.iservices.IMedjubankarskiPrenosService;
import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.MedjubankarskiPrenos;
import xws_pi_bezb.models.StavkaPrenosa;


@Service
public class MedjubankarskiPrenosService implements IMedjubankarskiPrenosService {
	
	@Autowired
	private IMedjubankarskiPrenosRepository medjubankarskiPrenosRepository;
	
	@Autowired
	private IAnalitikaIzvodaRepository analitikaIzvodaRepository;
	
	@Autowired
	private IStavkaPrenosaRepository stavkaPrenosaRepository;

	@Override
	public void save(MedjubankarskiPrenos medjubankarskiPrenos) {
		medjubankarskiPrenosRepository.save(medjubankarskiPrenos);
		
	}

	@Override
	public List<MedjubankarskiPrenos> getAll() {
		
		return medjubankarskiPrenosRepository.findAll();
	}

	@Override
	public List<MedjubankarskiPrenos> findByBanke(Banka banka) {
		List<MedjubankarskiPrenos> retVal = medjubankarskiPrenosRepository.findByBankaPrimalac(banka);
		
		for(MedjubankarskiPrenos medjubankarski: medjubankarskiPrenosRepository.findByBankaPosiljalac(banka)){
			if(!retVal.contains(banka)){
				retVal.add(medjubankarski);
			}
			
		}	
		return retVal;
	}

	@Override
	public List<MedjubankarskiPrenos> findByAnalitikaIzvodaId(Long id) {
		List<MedjubankarskiPrenos> retVal = new ArrayList<MedjubankarskiPrenos>();
		
		List<StavkaPrenosa> stavke = stavkaPrenosaRepository.findByAnalitikaIzvodaId(id);
		for (StavkaPrenosa stavka : stavke)
			retVal.add(medjubankarskiPrenosRepository.findOne(stavka.getMedjubankarskiPrenos().getId()));
		
		return retVal;
	}	
}
