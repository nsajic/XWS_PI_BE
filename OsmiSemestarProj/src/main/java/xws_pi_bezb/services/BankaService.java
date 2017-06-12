package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IBankaRepository;
import xws_pi_bezb.iservices.IBankaService;
import xws_pi_bezb.models.Banka;

@Service
public class BankaService implements IBankaService {

	@Autowired
	private IBankaRepository bankaRepository;

	public List<Banka> findAll() {
		return bankaRepository.findAll();
	}

	public Banka findOne(Long id) {
		return bankaRepository.findById(id);
	}

	@Override
	public void save(Banka banka) {
		bankaRepository.save(banka);
	}

	@Override
	public void delete(Long id) {
		bankaRepository.delete(id);

	}



	@Override
	public List<Banka> getBySearch(Banka banka) {
		List<Banka> banke = new ArrayList<Banka>();

		String sifraString = (banka.getSifraBanke() == 0) ? null : Integer.toString(banka.getSifraBanke());

		System.out.println("NAZIV : " + banka.getNazivBanke());
		System.out.println("SIFRA : " + sifraString);
		System.out.println("SWIFT : " + banka.getSwiftKod());
		System.out.println("OBRAC : " + banka.getObracunskiRacun());
		
		
		boolean praznaPretraga = true;

		for (Banka bankaFor : bankaRepository.findAll()) {
			
			if (!Helpers.isNullOrEmpty(banka.getNazivBanke())) {
				praznaPretraga = false;
				if (bankaFor.getNazivBanke().toLowerCase().contains(banka.getNazivBanke().toLowerCase())) {
					if (!banke.contains(bankaFor)) {
						banke.add(bankaFor);
					}

				}
			}
			if (!Helpers.isNullOrEmpty(banka.getSwiftKod())) {
				praznaPretraga = false;
				if (bankaFor.getSwiftKod().toLowerCase().contains(banka.getSwiftKod().toLowerCase())) {
					if (!banke.contains(bankaFor)) {
						banke.add(bankaFor);
					}		
				}
			}
			
			if(!Helpers.isNullOrEmpty(banka.getObracunskiRacun())){
				praznaPretraga = false;
				if (bankaFor.getObracunskiRacun().toLowerCase().contains(banka.getObracunskiRacun().toLowerCase())) {
					if (!banke.contains(bankaFor)) {
						banke.add(bankaFor);
					}
				}
			}
		
			if (!Helpers.isNullOrEmpty(sifraString)) {
				praznaPretraga = false;
				if (Integer.toString(bankaFor.getSifraBanke()).toLowerCase().contains(sifraString.toLowerCase())) {
					if (!banke.contains(bankaFor)) {
						banke.add(bankaFor);
					}
				}
			}

			if (praznaPretraga) {
				banke = bankaRepository.findAll();
			}
		}return banke;

}

}