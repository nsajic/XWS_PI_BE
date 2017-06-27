package xws_pi_bezb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xws_pi_bezb.annotations.InterceptorAnnotation;
import xws_pi_bezb.helpers.KlijentTip;
import xws_pi_bezb.iservices.IBankarskiSluzbenikService;
import xws_pi_bezb.iservices.IDelatnostService;
import xws_pi_bezb.iservices.IFizickaLicaService;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.iservices.IPravnaLicaService;
import xws_pi_bezb.iservices.IPrivilegijaService;
import xws_pi_bezb.iservices.IRolaService;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.FizickoLice;
import xws_pi_bezb.models.PravnoLice;
import xws_pi_bezb.models.Privilegija;
import xws_pi_bezb.models.Rola;
import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;
import xws_pi_bezb.view_models.PretragaPravnihLicaViewModel;
import xws_pi_bezb.view_models.UlogovanKorisnikIRolaViewModel;

@Controller
@RequestMapping("/klijentKontroler")
public class KlijentKontroler {

	@Autowired
	public IKlijentService klijentService;

	@Autowired
	public IBankarskiSluzbenikService bankarskiSluzbenikService;

	@Autowired
	public IDelatnostService delatnostService;
	
	@Autowired
	public IRolaService rolaService;
	
	@Autowired
	private IPrivilegijaService prvilegijaService;
	
	@Autowired
	public IPravnaLicaService pravnaLicaService;
	
	@Autowired
	public IFizickaLicaService fizickaLicaService;
	
	
	@RequestMapping(value = "/dodajPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:DodajPravnoFizicko")
	public ResponseEntity<List<PravnoLice>> dodajPravnoLice(@RequestBody PravnoLice pravnoLice) {
		pravnoLice.setKlijentTip(KlijentTip.PravnoLice);
		pravnaLicaService.save(pravnoLice);
		return new ResponseEntity<List<PravnoLice>>(pravnaLicaService.getPravnaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izmeniPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:IzmeniPravnoFizicko")
	public ResponseEntity<List<PravnoLice>> izmeniKlijenta(@RequestBody PravnoLice pravnoLice) {
		pravnoLice.setEmail(pravnaLicaService.findOne(pravnoLice.getId()).getEmail());
		pravnaLicaService.save(pravnoLice);
		return new ResponseEntity<List<PravnoLice>>(pravnaLicaService.getPravnaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:ObrisiPravnoFizicko")
	public ResponseEntity<List<PravnoLice>> izbrisiPravnoLice(@RequestBody Long klijentId) {
		pravnaLicaService.delete(klijentId);
		return new ResponseEntity<List<PravnoLice>>(pravnaLicaService.getPravnaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajPravnaLica", method = RequestMethod.GET)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<List<PravnoLice>> izlistajPravnaLica() {	
		return new ResponseEntity<List<PravnoLice>>(pravnaLicaService.getPravnaLica(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajPravnaLicaBanke", method = RequestMethod.GET)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<List<PravnoLice>> izlistajPravnaLicaBanke(HttpSession session) {	
		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");	
		return new ResponseEntity<List<PravnoLice>>(pravnaLicaService.getPravnaLicaByBanka(kor.getBanka().getId()), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretragaPravnihLica", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<List<PravnoLice>> pretragaPravnihLica(@RequestBody PravnoLice pravnoLice) {
		//return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLicaBySearch(pravnoLice), HttpStatus.OK);
		return new ResponseEntity<List<PravnoLice>>(pravnaLicaService.getPravnaLicaBySearch(pravnoLice), HttpStatus.OK);
		
	}

	@RequestMapping(value = "/ucitajPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<PravnoLice> ucitajPravnoLice(@RequestBody PravnoLice pravnoLice) {
		return new ResponseEntity<PravnoLice>((PravnoLice) pravnaLicaService.findOne(pravnoLice.getId()), HttpStatus.OK);
	}

	@RequestMapping(value = "/ucitajDelatnosti", method = RequestMethod.GET)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<List<Delatnost>> ucitajDelatnosti() {
		return new ResponseEntity<List<Delatnost>>(delatnostService.findAll(), HttpStatus.OK);
	}
	
	//ZA DELATNOST MI TREBALO
	@RequestMapping(value = "/izlistajPravnaLicaDelatnosti", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity <List<PravnoLice>> izlistajPravnaLicaDelatnosti(@RequestBody Long id) {
		return new ResponseEntity<List<PravnoLice>>(pravnaLicaService.findByDelatnost(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretragaPravnihLicaDelatnosti", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity <List<PravnoLice>> pretragaPravnihLicaDelatnosti(@RequestBody PretragaPravnihLicaViewModel viewModel) {
		System.out.println(viewModel.getPravnoLice().getMaticniBroj()+"   del: "+ viewModel.getDelatnost().getNazivDelatnosti());
		return new ResponseEntity<List<PravnoLice>>(pravnaLicaService.getPravnaLicaBySearchAndDelatnost(viewModel.getPravnoLice(), viewModel.getDelatnost()), HttpStatus.OK);
	}
	
	// ISPOD FIZICKA

	@RequestMapping(value = "/dodajFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Klijent:DodajPravnoFizicko")
	public ResponseEntity<Object> dodajFizickoLice(@RequestBody FizickoLice fizickoLice) {
		//String randomPassword = Helpers.generatePassword();
		
		//fizickoLice.setLogovaoSe(false);
		//fizickoLice.setSifra(Password.hashPassword(randomPassword));	
		//fizickoLice.setRola(rolaService.findByNaziv(Strings.fizickoLice));
		/*new SendMail(fizickoLice.getEmail(),"Postovani "+ fizickoLice.getIme() + ". \n\n" +  
				"Kreiran vam je nalog u banci.\n\nVasa sifra je " + randomPassword + ".\n"
				+ "Prilikom prvog logovanja cete morati da postavite novu sifru.\n\n" + "Pozdrav.");
				*/
		

		fizickoLice.setKlijentTip(KlijentTip.FizickoLice);
		fizickaLicaService.save(fizickoLice);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:IzmeniPravnoFizicko")
	public ResponseEntity<List<FizickoLice>> izmeniKlijenta(@RequestBody FizickoLice fizickoLice) {
		fizickoLice.setEmail(fizickaLicaService.findOne(fizickoLice.getId()).getEmail());
		//fizickoLice.setUsername(klijentService.findOne(fizickoLice.getId()).getUsername());
		//fizickoLice.setSifra(klijentService.findOne(fizickoLice.getId()).getSifra());
		fizickaLicaService.save(fizickoLice);
		return new ResponseEntity<List<FizickoLice>>(fizickaLicaService.getFizickaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:ObrisiPravnoFizicko")
	public ResponseEntity<List<FizickoLice>> izbrisiFizickoLice(@RequestBody Long klijentId) {
		fizickaLicaService.delete(klijentId);
		return new ResponseEntity<List<FizickoLice>>(fizickaLicaService.getFizickaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajFizickaLica", method = RequestMethod.GET)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<List<FizickoLice>> izlistajFizickaLica() {
		return new ResponseEntity<List<FizickoLice>>(fizickaLicaService.getFizickaLica(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izlistajFizickaLicaBanke", method = RequestMethod.GET)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<List<FizickoLice>> izlistajFizickaLicaBanke(HttpSession session) {
		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");	
		return new ResponseEntity<List<FizickoLice>>(fizickaLicaService.getFizickaLicaByBanka(kor.getBanka().getId()), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretraziFizickaLica", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<List<FizickoLice>> pretraziFizickaLica(@RequestBody FizickoLice fizickoLice) {
		return new ResponseEntity<List<FizickoLice>>(fizickaLicaService.getFizickaLicaBySearch(fizickoLice), HttpStatus.OK);
	}

	@RequestMapping(value = "/ucitajFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<FizickoLice> ucitajFizickoLice(@RequestBody FizickoLice fizickoLice) {
		return new ResponseEntity<FizickoLice>(fizickaLicaService.findOne(fizickoLice.getId()),HttpStatus.OK);
	}

	@RequestMapping(value = "/ucitajUlogovanogKorisnika", method = RequestMethod.GET)
	@InterceptorAnnotation("Klijent:IzlistajPretraziPravnoFizicko")
	public ResponseEntity<UlogovanKorisnikIRolaViewModel> ucitajUlogovanogKorisnika(HttpSession session){	
		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");	
		UlogovanKorisnikIRolaViewModel retVal = new UlogovanKorisnikIRolaViewModel();
		retVal.setKorisnik(bankarskiSluzbenikService.findOne(kor.getId()));
		retVal.setRola(bankarskiSluzbenikService.findOne(kor.getId()).getRola());
		return new ResponseEntity<UlogovanKorisnikIRolaViewModel>(retVal,HttpStatus.OK);	

	}
	
	@RequestMapping(value = "/ucitajRoluUlogovanogKorisnika", method = RequestMethod.GET)
	public ResponseEntity<Rola> ucitajRoluUlogovanogKorisnika(HttpSession session){	
		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");
		return new ResponseEntity<Rola>(bankarskiSluzbenikService.findOne(kor.getId()).getRola(),HttpStatus.OK);	

	}
	
	@RequestMapping(value = "/ucitajPrivilegije", method = RequestMethod.GET)
	public ResponseEntity<List<String>> ucitajPrivilegije(HttpSession session) {
		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");
		if(kor != null){
			List<String> privilegije = new ArrayList<>();
			if(kor.getRola() == null)
				return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.BAD_REQUEST);
			
			List<Privilegija> collection = new ArrayList<>();	
			//collection.addAll(kor.getRola().getPrivilegije());
			collection.addAll(prvilegijaService.getByRole(kor.getRola()));
			
			for (Privilegija priv : collection) {
				privilegije.add(priv.getNaziv());
			}
			return new ResponseEntity<List<String>>(privilegije, HttpStatus.OK);					
		}
		return new ResponseEntity<List<String>>(new ArrayList<String>(), HttpStatus.BAD_REQUEST);
	}
}
