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
import xws_pi_bezb.models.Delatnost;

@Controller
@RequestMapping("/delatnostKontroler")
public class DelatnostKontroler {
	
	@Autowired
	public IDelatnostService delatnostService;
	
	@RequestMapping(value = "/dodajDelatnost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Delatnost>> dodajDelatnost(@RequestBody Delatnost delatnost) {
		delatnostService.save(delatnost);
		return new ResponseEntity<List<Delatnost>>(delatnostService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniDelatnost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Delatnost>> izmeniDelatnost(@RequestBody Delatnost delatnost) {
		delatnostService.save(delatnost);
		return new ResponseEntity<List<Delatnost>>(delatnostService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiDelatnost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Delatnost>> izbrisiDelatnost(@RequestBody Long pravnoLiceId) {
		delatnostService.delete(pravnoLiceId);
		return new ResponseEntity<List<Delatnost>>(delatnostService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajDelatnost", method = RequestMethod.GET)
	public ResponseEntity<List<Delatnost>> izlistajDelatnost() {
		return new ResponseEntity<List<Delatnost>>(delatnostService.findAll(), HttpStatus.OK);
	}
	
}
