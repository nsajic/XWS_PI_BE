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

import xws_pi_bezb.helpers.Strings;
import xws_pi_bezb.iservices.IDelatnostService;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.iservices.IRolaService;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.korisnici.FizickoLice;
import xws_pi_bezb.models.korisnici.PravnoLice;

@Controller
@RequestMapping("/klijentKontroler")
public class KlijentKontroler {

	@Autowired
	public IKlijentService klijentService;

	@Autowired
	public IDelatnostService delatnostService;
	
	@Autowired
	public IRolaService rolaService;

	@RequestMapping(value = "/dodajPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> dodajPravnoLice(@RequestBody PravnoLice pravnoLice) {
		pravnoLice.setRola(rolaService.findByNaziv(Strings.pravnoLice));
		klijentService.save(pravnoLice);
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLica(), HttpStatus.OK);

	}

	@RequestMapping(value = "/izmeniPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> izmeniKlijenta(@RequestBody PravnoLice pravnoLice) {
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

	// ISPOD FIZICKA

	@RequestMapping(value = "/dodajFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FizickoLice>> dodajFizickoLice(@RequestBody FizickoLice fizickoLice) {
		fizickoLice.setRola(rolaService.findByNaziv(Strings.fizickoLice));
		klijentService.save(fizickoLice);
		return new ResponseEntity<List<FizickoLice>>(klijentService.getFizickaLica(), HttpStatus.OK);

	}

	@RequestMapping(value = "/izmeniFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FizickoLice>> izmeniKlijenta(@RequestBody FizickoLice fizickoLice) {
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
		return new ResponseEntity<FizickoLice>((FizickoLice) klijentService.findOne(fizickoLice.getId()),
				HttpStatus.OK);
	}

}
