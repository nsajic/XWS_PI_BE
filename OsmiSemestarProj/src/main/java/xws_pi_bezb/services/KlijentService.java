package xws_pi_bezb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.irepositories.IKlijentRepository;
import xws_pi_bezb.irepositories.IPravnoLiceRepository;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.PravnoLice;

@Service
public class KlijentService implements IKlijentService {

	@Autowired
	private IKlijentRepository klijentRepository;
	
	@Autowired
	private IPravnoLiceRepository pravnoLiceRepository;

	@Override
	public List<Klijent> findAll() {
		return klijentRepository.findAll();
	}

	@Override
	public Klijent findOne(Long id) {
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
		for (Klijent klijent : klijentRepository.findAll()) {
			if (klijent instanceof PravnoLice) {
				pravnaLica.add((PravnoLice) klijent);
			}
		}
		return pravnaLica;
	}

	@Override
	public List<FizickoLice> getFizickaLica() {
		List<FizickoLice> fizickaLica = new ArrayList<FizickoLice>();
		for (Klijent klijent : klijentRepository.findAll()) {
			if (klijent instanceof FizickoLice) {
				fizickaLica.add((FizickoLice) klijent);
			}
		}
		return fizickaLica;
	}

	@Override
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

	@Override
	public Klijent findByEmail(String email) {
		return klijentRepository.findByEmail(email);
	}

	@Override
	public List<PravnoLice> findByDelatnost(Long id) {
		return pravnoLiceRepository.findByDelatnostId(id);
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

	
	/*private Collection<? extends GrantedAuthority> getAuthorities(Rola rola) {
		return getGrantedAuthorities(getPrivilegije(rola));
	}*/
	
	@Override
	public void logoutKorisnik() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		if(privileges == null)
			return authorities;
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*private List<String> getPrivilegije(Rola rola) {
		List<String> privilegije = new ArrayList<>();
		if(rola == null)
			return privilegije;
		
		List<Privilegija> collection = new ArrayList<>();	
		collection.addAll(rola.getPrivilegije());
		
		for (Privilegija priv : collection) {
			privilegije.add(priv.getNaziv());
		}
		return privilegije;
	}
	*/
}
