package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IKlijentRepository;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.models.korisnici.FizickoLice;
import xws_pi_bezb.models.korisnici.Korisnik;
import xws_pi_bezb.models.korisnici.PravnoLice;

@Service
public class KlijentService implements IKlijentService {

	@Autowired
	private IKlijentRepository klijentRepository;

	@Override
	public List<Korisnik> findAll() {
		// TODO Auto-generated method stub
		return klijentRepository.findAll();
	}

	@Override
	public Korisnik findOne(Long id) {
		// TODO Auto-generated method stub
		return klijentRepository.findById(id);
	}

	@Override
	public void save(Korisnik klijent) {
		klijentRepository.save(klijent);

	}

	@Override
	public void delete(Long id) {
		klijentRepository.delete(id);

	}

	@Override
	public List<PravnoLice> getPravnaLica() {
		List<PravnoLice> pravnaLica = new ArrayList<PravnoLice>();
		for (Korisnik klijent : klijentRepository.findAll()) {
			if (klijent instanceof PravnoLice) {
				pravnaLica.add((PravnoLice) klijent);
			}
		}
		return pravnaLica;
	}

	@Override
	public List<FizickoLice> getFizickaLica() {
		List<FizickoLice> fizickaLica = new ArrayList<FizickoLice>();
		for (Korisnik klijent : klijentRepository.findAll()) {
			if (klijent instanceof FizickoLice) {
				fizickaLica.add((FizickoLice) klijent);
			}
		}
		return fizickaLica;
	}

	@Override
	public List<PravnoLice> getPravnaLicaBySearch(PravnoLice pravnoLice) {
		List<PravnoLice> pravnaLica = new ArrayList<PravnoLice>();

		System.out.println("apr = " + pravnoLice.isAPR());
		System.out.println("op  = " + pravnoLice.isOP());
		
		String brojLicneKarteString = (pravnoLice.getBrojLicneKarte() == 0) ? null: Integer.toString(pravnoLice.getBrojLicneKarte());
		String PIBString = (pravnoLice.getPIB() == 0) ? null : Integer.toString(pravnoLice.getPIB());
		String maticniBrojString = (pravnoLice.getMaticniBroj() == 0) ? null: Integer.toString(pravnoLice.getMaticniBroj());

		boolean praznaPretraga = true;

		for (PravnoLice pravnoLiceFor : getPravnaLica()) {
			
			if (!Helpers.isNullOrEmpty(pravnoLice.getIme())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getIme().toLowerCase().contains(pravnoLice.getIme().toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
			if (!Helpers.isNullOrEmpty(pravnoLice.getPrezime())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getPrezime().toLowerCase().contains(pravnoLice.getPrezime().toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(pravnoLice.getTelefon())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getTelefon().toLowerCase().contains(pravnoLice.getTelefon().toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(pravnoLice.getAdresa())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getAdresa().toLowerCase().contains(pravnoLice.getAdresa().toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(pravnoLice.getNaziv())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getNaziv().toLowerCase().contains(pravnoLice.getNaziv().toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(pravnoLice.getEmail())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getEmail().toLowerCase().contains(pravnoLice.getEmail().toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(pravnoLice.getFax())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getFax().toLowerCase().contains(pravnoLice.getFax().toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			// TODO: Pretraga za boolean-e i za delatnost

			if (!Helpers.isNullOrEmpty(brojLicneKarteString)) {
				praznaPretraga = false;
				if (Integer.toString(pravnoLiceFor.getBrojLicneKarte()).toLowerCase()
						.contains(brojLicneKarteString.toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(PIBString)) {
				praznaPretraga = false;
				if (Integer.toString(pravnoLiceFor.getPIB()).toLowerCase().contains(PIBString.toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (!Helpers.isNullOrEmpty(maticniBrojString)) {
				praznaPretraga = false;
				if (Integer.toString(pravnoLiceFor.getMaticniBroj()).toLowerCase()
						.contains(maticniBrojString.toLowerCase())) {
					if (!pravnaLica.contains(pravnoLiceFor)) {
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (praznaPretraga) {
				pravnaLica = getPravnaLica();
			}
		}
		return pravnaLica;
	}

	@Override
	public List<FizickoLice> getFizickaLicaBySearch(FizickoLice fizickoLice) {
		List<FizickoLice> fizickaLica = new ArrayList<FizickoLice>();

		String brojLicneKarteString = (fizickoLice.getBrojLicneKarte() == 0) ? null: Integer.toString(fizickoLice.getBrojLicneKarte());
		String JMBGString = (fizickoLice.getJmbg() == 0) ? null : Integer.toString(fizickoLice.getJmbg());

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

			if (!Helpers.isNullOrEmpty(JMBGString)) {
				praznaPretraga = false;
				if (Integer.toString(fizickoLiceFor.getJmbg()).toLowerCase().contains(JMBGString.toLowerCase())) {
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

	@Override
	public Korisnik findByEmail(String email) {
		return klijentRepository.findByEmail(email);
	}

}
