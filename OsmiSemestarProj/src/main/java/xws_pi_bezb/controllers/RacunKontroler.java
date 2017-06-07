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

import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.models.Racun;


@Controller
@RequestMapping("/racunKontroler")
public class RacunKontroler {

	@Autowired
	public IRacunService racunService;

	@RequestMapping(value = "/dodajRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Racun>> dodajRacun(@RequestBody Racun racun) {
		racunService.save(racun);
		return new ResponseEntity<List<Racun>>(racunService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Racun>> izmeniRacun(@RequestBody Racun racun) {
		racunService.save(racun);
		return new ResponseEntity<List<Racun>>(racunService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Racun>> izbrisiRacun(@RequestBody Long racunId) {
		racunService.delete(racunId);
		return new ResponseEntity<List<Racun>>(racunService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajRacune", method = RequestMethod.GET)
	public ResponseEntity<List<Racun>> izlistajRacune() {
		return new ResponseEntity<List<Racun>>(racunService.findAll(), HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/pretraziRacune", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Racun>> pretraziRacune(@RequestBody String searchText) {
		return new ResponseEntity<List<Racun>>(racunService.getBySearchText(searchText), HttpStatus.OK);
	}*/
}
