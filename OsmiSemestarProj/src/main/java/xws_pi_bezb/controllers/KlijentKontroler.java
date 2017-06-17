package xws_pi_bezb.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xws_pi_bezb.helpers.Helpers;
import xws_pi_bezb.helpers.Strings;
import xws_pi_bezb.iservices.IDelatnostService;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.iservices.IPrivilegijaService;
import xws_pi_bezb.iservices.IRolaService;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.Privilegija;
import xws_pi_bezb.models.korisnici.FizickoLice;
import xws_pi_bezb.models.korisnici.Korisnik;
import xws_pi_bezb.models.korisnici.PravnoLice;
import xws_pi_bezb.password_security.SendMail;
import xws_pi_bezb.view_models.PretragaPravnihLicaViewModel;

@Controller
@RequestMapping("/klijentKontroler")
public class KlijentKontroler {

	@Autowired
	public IKlijentService klijentService;

	@Autowired
	public IDelatnostService delatnostService;
	
	@Autowired
	public IRolaService rolaService;
	
	@Autowired
	private IPrivilegijaService prvilegijaService;

	@RequestMapping(value = "/dodajPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> dodajPravnoLice(@RequestBody PravnoLice pravnoLice) {
		pravnoLice.setRola(rolaService.findByNaziv(Strings.pravnoLice));
		klijentService.save(pravnoLice);
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLica(), HttpStatus.OK);

	}

	@RequestMapping(value = "/izmeniPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> izmeniKlijenta(@RequestBody PravnoLice pravnoLice) {
		pravnoLice.setEmail(klijentService.findOne(pravnoLice.getId()).getEmail());
		pravnoLice.setUsername(klijentService.findOne(pravnoLice.getId()).getUsername());
		pravnoLice.setSifra(klijentService.findOne(pravnoLice.getId()).getSifra());
		klijentService.save(pravnoLice);
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> izbrisiPravnoLice(@RequestBody Long klijentId) {
		klijentService.delete(klijentId);
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajPravnaLica", method = RequestMethod.GET)
	public ResponseEntity<List<PravnoLice>> izlistajPravnaLica() {
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretraziPravnaLica", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> pretraziPravnaLica(@RequestBody PravnoLice pravnoLice) {
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLicaBySearch(pravnoLice), HttpStatus.OK);
	}

	@RequestMapping(value = "/ucitajPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PravnoLice> ucitajPravnoLice(@RequestBody PravnoLice pravnoLice) {
		return new ResponseEntity<PravnoLice>((PravnoLice) klijentService.findOne(pravnoLice.getId()), HttpStatus.OK);
	}

	@RequestMapping(value = "/ucitajDelatnosti", method = RequestMethod.GET)
	public ResponseEntity<List<Delatnost>> ucitajDelatnosti() {
		return new ResponseEntity<List<Delatnost>>(delatnostService.findAll(), HttpStatus.OK);
	}
	
	//ZA DELATNOST MI TREBALO
	@RequestMapping(value = "/izlistajPravnaLicaDelatnosti", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<PravnoLice>> izlistajPravnaLicaDelatnosti(@RequestBody Long id) {
		return new ResponseEntity<List<PravnoLice>>(klijentService.findByDelatnost(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretragaPravnihLicaDelatnosti", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<PravnoLice>> pretragaPravnihLicaDelatnosti(@RequestBody PretragaPravnihLicaViewModel viewModel) {
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLicaBySearchAndDelatnost(viewModel.getPravnoLice(), viewModel.getDelatnost()), HttpStatus.OK);
	}
	
	// ISPOD FIZICKA

	@RequestMapping(value = "/dodajFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> dodajFizickoLice(@RequestBody FizickoLice fizickoLice) {
		fizickoLice.setLogovaoSe(false);
		fizickoLice.setSifra(Helpers.generatePassword());	
		fizickoLice.setRola(rolaService.findByNaziv(Strings.fizickoLice));
		klijentService.save(fizickoLice);
		SendMail sm = new SendMail("unesite.svoj@mejl.com","Aktivirajte nalog klikom na link: " + "http://localhost:9000/contr/activate/onezerobeatz@gmail.com/");
		
		
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/activate/{email}")
	public ResponseEntity<String> activateAccount(@PathVariable String email) {			
		
		/*try{
			Gost gost = servis.findByEmail(email);
			servis.activateAccount(gost.getEmail());
			return new ResponseEntity<String>("Uspesno ste aktivirali nalog!", HttpStatus.ACCEPTED);			
		}catch(Exception ex){}*/
		return new ResponseEntity<String>("Neuspesna aktivacija naloga.", HttpStatus.ACCEPTED);
		
	}

	@RequestMapping(value = "/izmeniFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FizickoLice>> izmeniKlijenta(@RequestBody FizickoLice fizickoLice) {
		fizickoLice.setEmail(klijentService.findOne(fizickoLice.getId()).getEmail());
		fizickoLice.setUsername(klijentService.findOne(fizickoLice.getId()).getUsername());
		fizickoLice.setSifra(klijentService.findOne(fizickoLice.getId()).getSifra());
		klijentService.save(fizickoLice);
		return new ResponseEntity<List<FizickoLice>>(klijentService.getFizickaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FizickoLice>> izbrisiFizickoLice(@RequestBody Long klijentId) {
		klijentService.delete(klijentId);
		return new ResponseEntity<List<FizickoLice>>(klijentService.getFizickaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajFizickaLica", method = RequestMethod.GET)
	public ResponseEntity<List<FizickoLice>> izlistajFizickaLica() {
		return new ResponseEntity<List<FizickoLice>>(klijentService.getFizickaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretraziFizickaLica", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FizickoLice>> pretraziFizickaLica(@RequestBody FizickoLice fizickoLice) {
		return new ResponseEntity<List<FizickoLice>>(klijentService.getFizickaLicaBySearch(fizickoLice), HttpStatus.OK);
	}

	@RequestMapping(value = "/ucitajFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FizickoLice> ucitajFizickoLice(@RequestBody FizickoLice fizickoLice) {
		return new ResponseEntity<FizickoLice>((FizickoLice) klijentService.findOne(fizickoLice.getId()),HttpStatus.OK);
	}

	@RequestMapping(value = "/ucitajPrivilegije", method = RequestMethod.GET)
	public ResponseEntity<List<String>> ucitajPrivilegije(HttpSession session) {
		Korisnik kor = (Korisnik) session.getAttribute("ulogovanKorisnik");
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
