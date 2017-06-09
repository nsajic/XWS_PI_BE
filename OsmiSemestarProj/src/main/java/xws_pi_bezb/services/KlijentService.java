package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xws_pi_bezb.irepositories.IKlijentRepository;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.PravnoLice;
@Service
public class KlijentService implements IKlijentService{

	@Autowired
	private IKlijentRepository klijentRepository;
	
	@Override
	public List<Klijent> findAll() {
		// TODO Auto-generated method stub
		return klijentRepository.findAll();
	}

	@Override
	public Klijent findOne(Long id) {
		// TODO Auto-generated method stub
		return klijentRepository.findById(id);
	}

	@Override
	public void save(Klijent klijent) {
		klijentRepository.save(klijent);
		
	}

	@Override
	public void delete(Long id) {
		klijentRepository.delete(id);
		
	}

	@Override
	public List<PravnoLice> getPravnaLica() {
		List<PravnoLice> pravnaLica = new ArrayList<PravnoLice>();
		for(Klijent klijent : klijentRepository.findAll()){
			if (klijent instanceof PravnoLice){
				pravnaLica.add((PravnoLice)klijent);
			}
		}
		return pravnaLica;
	}

	@Override
	public List<FizickoLice> getFizickaLica() {
		List<FizickoLice> fizickaLica = new ArrayList<FizickoLice>();
		for(Klijent klijent : klijentRepository.findAll()){
			if (klijent instanceof FizickoLice){
				fizickaLica.add((FizickoLice)klijent);
			}
		}
		return fizickaLica;
	}

	@Override
	public List<PravnoLice> getPravnaLicaBySearch(PravnoLice pravnoLice) {
		List<PravnoLice> pravnaLica = new ArrayList<PravnoLice>();

		String brojLicneKarteString = (pravnoLice.getBrojLicneKarte() == 0) ? null : Integer.toString(pravnoLice.getBrojLicneKarte());
		String PIBString = (pravnoLice.getPIB() == 0) ? null : Integer.toString(pravnoLice.getPIB());
		String maticniBrojString = (pravnoLice.getMaticniBroj() == 0) ? null : Integer.toString(pravnoLice.getMaticniBroj());


		boolean praznaPretraga = true;
		
		for (PravnoLice pravnoLiceFor : getPravnaLica()) {
			if (pravnoLice.getIme() != null) {
				praznaPretraga = false;
				if (pravnoLiceFor.getIme().toLowerCase().contains(pravnoLice.getIme().toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
			if (pravnoLice.getPrezime() != null ) {
				praznaPretraga = false;
				if (pravnoLiceFor.getPrezime().toLowerCase().contains(pravnoLice.getPrezime().toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
			
			if (pravnoLice.getTelefon() != null ) {
				praznaPretraga = false;
				if (pravnoLiceFor.getTelefon().toLowerCase().contains(pravnoLice.getTelefon().toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
			
			if (pravnoLice.getAdresa() != null ) {
				praznaPretraga = false;
				if (pravnoLiceFor.getAdresa().toLowerCase().contains(pravnoLice.getAdresa().toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (pravnoLice.getNaziv() != null ) {
				praznaPretraga = false;
				if (pravnoLiceFor.getNaziv().toLowerCase().contains(pravnoLice.getNaziv().toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
			
			if (pravnoLice.getEmail() != null ) {
				praznaPretraga = false;
				if (pravnoLiceFor.getEmail().toLowerCase().contains(pravnoLice.getEmail().toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
			
			if (pravnoLice.getFax() != null ) {
				praznaPretraga = false;
				if (pravnoLiceFor.getFax().toLowerCase().contains(pravnoLice.getFax().toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
	
			//TODO: Pretraga za boolean-e i za delatnost
			


			if (brojLicneKarteString != null) {
				praznaPretraga = false;
				if (Integer.toString(pravnoLiceFor.getBrojLicneKarte()).toLowerCase().contains(brojLicneKarteString.toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
			
			if (PIBString != null) {
				praznaPretraga = false;
				if (Integer.toString(pravnoLiceFor.getPIB()).toLowerCase().contains(PIBString.toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}

			if (maticniBrojString != null) {
				praznaPretraga = false;
				if (Integer.toString(pravnoLiceFor.getMaticniBroj()).toLowerCase().contains(maticniBrojString.toLowerCase())) {
					if(!pravnaLica.contains(pravnoLiceFor)){
						pravnaLica.add(pravnoLiceFor);
					}
				}
			}
			
			
			if(praznaPretraga){
				pravnaLica = getPravnaLica();
			}
		}
		return pravnaLica;
	}

	@Override
	public List<PravnoLice> getPravnaLicaBySearch(FizickoLice fizickoLice) {
		// TODO Auto-generated method stub
		return null;
	}

}
