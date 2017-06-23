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

import xws_pi_bezb.annotations.InterceptorAnnotation;
import xws_pi_bezb.iservices.IDelatnostService;
import xws_pi_bezb.models.Delatnost;	

@Controller
@RequestMapping("/delatnostKontroler")
public class DelatnostKontroler {

	@Autowired
	public IDelatnostService delatnostService;

	@RequestMapping(value = "/dodajDelatnost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Delatnost:Dodaj")
	public ResponseEntity<Object> dodajDelatnost(@RequestBody Delatnost delatnost) {
		delatnostService.save(delatnost);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/izmeniDelatnost", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Delatnost:Izmeni")
	public ResponseEntity<Object> izmeniDelatnost(@RequestBody Delatnost delatnost) {
		delatnostService.save(delatnost);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiDelatnost", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Delatnost:Obrisi")
	public ResponseEntity<Object> izbrisiDelatnost(@RequestBody Long delatnostId) {
		delatnostService.delete(delatnostId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajDelatnosti", method = RequestMethod.GET)
	//@InterceptorAnnotation("Delatnost:IzlistajPretrazi")
	public ResponseEntity<List<Delatnost>> izlistajDelatnosti() {
		return new ResponseEntity<List<Delatnost>>(delatnostService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretraziDelatnosti", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Delatnost:IzlistajPretrazi")
	public ResponseEntity<List<Delatnost>> pretraziDelatnosti(@RequestBody Delatnost delatnost) {
		return new ResponseEntity<List<Delatnost>>(delatnostService.getBySearch(delatnost), HttpStatus.OK);
	}

}