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

import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.models.Klijent;


@Controller
@RequestMapping("/klijentKontroler")
public class KlijentKontroler {

	
	@Autowired
	public IKlijentService klijentService;
	
	@RequestMapping(value = "/dodajKlijenta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Klijent>> dodajKlijenta(@RequestBody Klijent klijent) {
		klijentService.save(klijent);
		return new ResponseEntity<List<Klijent>>(klijentService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniKlijenta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Klijent>> izmeniKlijenta(@RequestBody Klijent klijent) {
		klijentService.save(klijent);
		return new ResponseEntity<List<Klijent>>(klijentService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiKlijenta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Klijent>> izbrisiKlijenta(@RequestBody Long klijentId) {
		klijentService.delete(klijentId);
		return new ResponseEntity<List<Klijent>>(klijentService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajKlijente", method = RequestMethod.GET)
	public ResponseEntity<List<Klijent>> izlistajKlijente() {
		return new ResponseEntity<List<Klijent>>(klijentService.findAll(), HttpStatus.OK);
	}
	
}
