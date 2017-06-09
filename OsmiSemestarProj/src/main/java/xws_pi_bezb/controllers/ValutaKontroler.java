/*package xws_pi_bezb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.Valuta;

@Controller
@RequestMapping("/valutaKontroler")
public class ValutaKontroler {

	@Autowired
	public IValutaService valutaService;
	
	@RequestMapping(value = "/dodajValutu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Valuta>> dodajValutu(@RequestBody Valuta valuta) {
		valutaService.save(valuta);
		return new ResponseEntity<List<Valuta>>(valutaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniValutu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Valuta>> izmeniValutu(@RequestBody Valuta valuta) {
		valutaService.save(valuta);
		return new ResponseEntity<List<Valuta>>(valutaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiValutu", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Valuta>> izbrisiValutu(@RequestBody Long valutaId) {
		valutaService.delete(valutaId);
		return new ResponseEntity<List<Valuta>>(valutaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajValute", method = RequestMethod.GET)
	public ResponseEntity<List<Valuta>> izlistajValute() {
		return new ResponseEntity<List<Valuta>>(valutaService.findAll(), HttpStatus.OK);
	}
	
}
*/