package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		System.out.println(banka.getNazivBanke() + " naziv");
		System.out.println(banka.getObracunskiRacun() + " obr rac");
		System.out.println(sifraString + " sifra banke");
		System.out.println(banka.getSwiftKod() + " swift");


		for (Banka bankaFor : bankaRepository.findAll()) {
			if (!banka.getNazivBanke().isEmpty()) {
				if (bankaFor.getNazivBanke().toLowerCase().contains(banka.getNazivBanke().toLowerCase())) {
					banke.add(bankaFor);
				}
			} else if (banka.getSwiftKod() != null) {
				if (bankaFor.getSwiftKod().toLowerCase().contains(banka.getSwiftKod().toLowerCase())) {
					banke.add(bankaFor);
				}
			} else if (banka.getObracunskiRacun() != null) {
				if (bankaFor.getObracunskiRacun().toLowerCase().contains(banka.getObracunskiRacun().toLowerCase())) {
					banke.add(bankaFor);
				}
			} else if (sifraString != null) {
				if (Integer.toString(bankaFor.getSifraBanke()).toLowerCase().contains(sifraString.toLowerCase())) {
					banke.add(bankaFor);
				}
			} else {
				banke = bankaRepository.findAll();
			}
		}
		return banke;

	}

}