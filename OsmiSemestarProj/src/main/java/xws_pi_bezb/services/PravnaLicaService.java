package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IPravnoLiceRepository;
import xws_pi_bezb.irepositories.IRacunRepository;
import xws_pi_bezb.iservices.IPravnaLicaService;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.PravnoLice;
import xws_pi_bezb.models.Racun;



@Service
public class PravnaLicaService implements IPravnaLicaService {
	
	@Autowired
	private IPravnoLiceRepository pravnoLiceRepository;
	
	@Autowired
	private IRacunRepository racunRepository;

	public List<PravnoLice> getPravnaLicaBySearch(PravnoLice pravnoLice) {
		
		List<PravnoLice> pravnaLica = new ArrayList<PravnoLice>();

		String brojLicneKarteString = (pravnoLice.getBrojLicneKarte() == 0) ? null: Integer.toString(pravnoLice.getBrojLicneKarte());
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

			
			if (!Helpers.isNullOrEmpty(pravnoLice.getEmail())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getEmail().toLowerCase().contains(pravnoLice.getEmail().toLowerCase())) {
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
			
			if (!Helpers.isNullOrEmpty(pravnoLice.getPIB())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getPIB().toLowerCase().contains(pravnoLice.getPIB().toLowerCase())) {
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
	public List<PravnoLice> getPravnaLica() {
		List<PravnoLice> pravnaLica = new ArrayList<PravnoLice>();
		pravnaLica = pravnoLiceRepository.findAll();
		return pravnaLica;
	}
	
	@Override
	public List<PravnoLice> getPravnaLicaByBanka(Long bankId) {
		
		List<PravnoLice> pravnaLica = new ArrayList<PravnoLice>();
		
		
		List<Racun> racuni = new ArrayList<Racun>();
		racuni.addAll(racunRepository.findByBankaId(bankId));
		
		for(Racun racun: racuni){
			PravnoLice pLice = pravnoLiceRepository.findById(racun.getKlijent().getId());
			
			if(pLice != null ){
				if(!pravnaLica.contains(pLice))
					pravnaLica.add(pLice);
			}
			
		}
		
		return pravnaLica;
	}


	@Override
	public List<PravnoLice> getPravnaLicaBySearchAndDelatnost(PravnoLice pravnoLice, Delatnost delatnost) {
		List<PravnoLice> pravnaLica = new ArrayList<PravnoLice>();

		String brojLicneKarteString = (pravnoLice.getBrojLicneKarte() == 0) ? null: Integer.toString(pravnoLice.getBrojLicneKarte());
		String maticniBrojString = (pravnoLice.getMaticniBroj() == 0) ? null: Integer.toString(pravnoLice.getMaticniBroj());

		boolean praznaPretraga = true;

		for (PravnoLice pravnoLiceFor : pravnoLiceRepository.findByDelatnostId(delatnost.getId())) {
			
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
			

			
			if (!Helpers.isNullOrEmpty(pravnoLice.getEmail())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getEmail().toLowerCase().contains(pravnoLice.getEmail().toLowerCase())) {
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
			
			if (!Helpers.isNullOrEmpty(pravnoLice.getPIB())) {
				praznaPretraga = false;
				if (pravnoLiceFor.getPIB().toLowerCase().contains(pravnoLice.getPIB().toLowerCase())) {
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
				pravnaLica = pravnoLiceRepository.findByDelatnostId(delatnost.getId());
			}
		}
		return pravnaLica;
	}

	@Override
	public void delete(Long id) {
		pravnoLiceRepository.delete(id);
		
	}

	@Override
	public void save(PravnoLice pravnoLice) {
		pravnoLiceRepository.save(pravnoLice);
		
	}
	
	@Override
	public PravnoLice findOne(Long id) {
		return pravnoLiceRepository.findOne(id);
	}
	

	@Override
	public List<PravnoLice> findByDelatnost(Long id) {
		return pravnoLiceRepository.findByDelatnostId(id);
	}

	


}
