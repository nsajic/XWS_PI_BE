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

import xws_pi_bezb.models.Drzava;
import xws_pi_bezb.services.DrzavaService;

@Controller
@RequestMapping("/drzavaKontroler")
public class DrzavaKontroler {

	@Autowired
	public DrzavaService drzavaService;

	@RequestMapping(value = "/dodajDrzavu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Drzava>> dodajDrzavu(@RequestBody Drzava drzava) {
		drzavaService.save(drzava);
		return new ResponseEntity<List<Drzava>>(drzavaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniDrzavu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Drzava>> izmeniDrzavu(@RequestBody Drzava drzava) {
		drzavaService.save(drzava);
		return new ResponseEntity<List<Drzava>>(drzavaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiDrzavu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Drzava>> izbrisiDrzavu(@RequestBody Long drzavaId) {
		drzavaService.delete(drzavaId);
		return new ResponseEntity<List<Drzava>>(drzavaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajDrzave", method = RequestMethod.GET)
	public ResponseEntity<List<Drzava>> izlistajDrzave() {
		return new ResponseEntity<List<Drzava>>(drzavaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pretraziDrzave", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Drzava>> pretraziDrzave(@RequestBody String searchText) {
		return new ResponseEntity<List<Drzava>>(drzavaService.getBySearchText(searchText), HttpStatus.OK);
	}


}