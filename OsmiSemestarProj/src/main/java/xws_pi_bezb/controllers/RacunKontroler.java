package xws_pi_bezb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xws_pi_bezb.iservices.IBankaService;
import xws_pi_bezb.iservices.IDnevnoStanjeRacunaService;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.Racun;
import xws_pi_bezb.models.Valuta;
import xws_pi_bezb.models.korisnici.Korisnik;


@Controller
@RequestMapping("/racunKontroler")
public class RacunKontroler {

	@Autowired
	public IRacunService racunService;
	@Autowired
	public IKlijentService klijentService;
	@Autowired
	public IValutaService valutaService;
	@Autowired
	public IBankaService bankaService;
	@Autowired
	private IDnevnoStanjeRacunaService dnevnoStanjeRacunaService;

	@RequestMapping(value = "/dodajRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> dodajRacun(@RequestBody Racun racun) {
		racunService.save(racun);
		return new ResponseEntity<Object>(racunService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> izmeniRacun(@RequestBody Racun racun) {
		racunService.save(racun);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> izbrisiRacun(@RequestBody Long racunId) {
		racunService.delete(racunId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajRacune", method = RequestMethod.GET)
	public ResponseEntity<List<Racun>> izlistajRacune() {
		return new ResponseEntity<List<Racun>>(racunService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretraziRacune", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Racun>> pretraziPravnaLica(@RequestBody Racun racun) {
		return new ResponseEntity<List<Racun>>(racunService.getRacunBySearch(racun), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/ucitajRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Racun> ucitajPravnoLice(@RequestBody Racun racun) {
		return new ResponseEntity<Racun>(racunService.findOne(racun.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajBanke", method = RequestMethod.GET)
	public ResponseEntity<List<Banka>> ucitajBanke() {
		return new ResponseEntity<List<Banka>>(bankaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajValute", method = RequestMethod.GET)
	public ResponseEntity<List<Valuta>> ucitajValute() {
		return new ResponseEntity<List<Valuta>>(valutaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajKlijente", method = RequestMethod.GET)
	public ResponseEntity<List<Korisnik>> ucitajKlijente() {
		return new ResponseEntity<List<Korisnik>>(klijentService.findAll(), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/ucitajDnevnaStanjaOdabranogRacuna", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DnevnoStanjeRacuna>> ucitajDnevnaStanjaOdabranogRacuna(@RequestBody Long racunId) {
		return new ResponseEntity<List<DnevnoStanjeRacuna>>(dnevnoStanjeRacunaService.findByRacunId(racunId), HttpStatus.OK);
	}
}
