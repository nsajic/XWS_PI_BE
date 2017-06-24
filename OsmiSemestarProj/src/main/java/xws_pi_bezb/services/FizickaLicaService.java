package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IFizickaLicaRepository;
import xws_pi_bezb.iservices.IFizickaLicaService;
import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.Klijent;


@Service
public class FizickaLicaService implements IFizickaLicaService {

	@Autowired
	public IFizickaLicaRepository fizickaLicaRepository;

	@Override
	public FizickoLice findById(Long id) {
		
		return fizickaLicaRepository.findOne(id);
	}

	@Override
	public FizickoLice findByEmail(String email) {
		
		return fizickaLicaRepository.findByEmail(email);
	}

	@Override
	public void delete(Long id) {
		fizickaLicaRepository.delete(id);
		
	}

	@Override
	public void save(FizickoLice fizickoLice) {
		fizickaLicaRepository.save(fizickoLice);
		
	}

	@Override
	public List<FizickoLice> getFizickaLica() {
		List<FizickoLice> fizickaLica = new ArrayList<FizickoLice>();
		for (FizickoLice flice : fizickaLicaRepository.findAll()) {
			
				fizickaLica.add(flice);
			
		}
		return fizickaLica;
	}

	@Override
	public FizickoLice findOne(Long id) {
	
		return fizickaLicaRepository.findOne(id);
	}

	
	
	@Override
	public List<FizickoLice> getFizickaLicaBySearch(FizickoLice fizickoLice) {
		List<FizickoLice> fizickaLica = new ArrayList<FizickoLice>();

		String brojLicneKarteString = (fizickoLice.getBrojLicneKarte() == 0) ? null: Integer.toString(fizickoLice.getBrojLicneKarte());

		boolean praznaPretraga = true;

		for (FizickoLice fizickoLiceFor : getFizickaLica()) {
			if (!Helpers.isNullOrEmpty(fizickoLice.getIme())) {
				praznaPretraga = false;
				if (fizickoLiceFor.getIme().toLowerCase().contains(fizickoLice.getIme().toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}
			if (!Helpers.isNullOrEmpty(fizickoLice.getPrezime())) {
				praznaPretraga = false;
				if (fizickoLiceFor.getPrezime().toLowerCase().contains(fizickoLice.getPrezime().toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(fizickoLice.getJmbg())) {
				praznaPretraga = false;
				if (fizickoLiceFor.getJmbg().toLowerCase().contains(fizickoLice.getJmbg().toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}

			
			if (!Helpers.isNullOrEmpty(fizickoLice.getEmail())) {
				praznaPretraga = false;
				if (fizickoLiceFor.getEmail().toLowerCase().contains(fizickoLice.getEmail().toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(fizickoLice.getTelefon())) {
				praznaPretraga = false;
				if (fizickoLiceFor.getTelefon().toLowerCase().contains(fizickoLice.getTelefon().toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(fizickoLice.getAdresa())) {
				praznaPretraga = false;
				if (fizickoLiceFor.getAdresa().toLowerCase().contains(fizickoLice.getAdresa().toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(fizickoLice.getImeRoditelja())) {
				praznaPretraga = false;
				if (fizickoLiceFor.getImeRoditelja().toLowerCase().contains(fizickoLice.getImeRoditelja().toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(brojLicneKarteString)) {
				praznaPretraga = false;
				if (Integer.toString(fizickoLiceFor.getBrojLicneKarte()).toLowerCase()
						.contains(brojLicneKarteString.toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(fizickoLice.getJmbg())) {
				praznaPretraga = false;
				if (fizickoLiceFor.getJmbg().toLowerCase().contains(fizickoLice.getJmbg().toLowerCase())) {
					if (!fizickaLica.contains(fizickoLiceFor)) {
						fizickaLica.add(fizickoLiceFor);
					}
				}
			}

			if (praznaPretraga) {
				fizickaLica = getFizickaLica();
			}
		}
		return fizickaLica;
	}
	

}
