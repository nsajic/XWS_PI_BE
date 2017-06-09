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

import xws_pi_bezb.iservices.IDelatnostService;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.PravnoLice;


@Controller
@RequestMapping("/klijentKontroler")
public class KlijentKontroler {


	@Autowired
	public IKlijentService klijentService;
	
	@Autowired
	public IDelatnostService delatnostService;
	
	@RequestMapping(value = "/dodajPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> dodajPravnoLice(@RequestBody PravnoLice pravnoLice) {
		System.out.println(pravnoLice.getDelatnost()+" sss");
		klijentService.save(pravnoLice);
		
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLica(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/izmeniPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> izmeniKlijenta(@RequestBody PravnoLice pravnoLice) {
		klijentService.save(pravnoLice);
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> izbrisiKlijenta(@RequestBody Long klijentId) {
		klijentService.delete(klijentId);
		return new ResponseEntity<List<PravnoLice>>(klijentService.getPravnaLica(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajPravnaLica", method = RequestMethod.GET)
	public ResponseEntity<List<PravnoLice>> izlistajKlijente() {
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
}
