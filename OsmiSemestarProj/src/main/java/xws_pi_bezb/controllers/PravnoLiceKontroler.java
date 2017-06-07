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

import xws_pi_bezb.iservices.IPravnoLiceService;
import xws_pi_bezb.models.PravnoLice;

@Controller
@RequestMapping("/pravnoLiceKontroler")
public class PravnoLiceKontroler {
	
	@Autowired
	public IPravnoLiceService pravnoLiceService;
	
	@RequestMapping(value = "/dodajPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> dodajPravnoLice(@RequestBody PravnoLice pravnoLice) {
		pravnoLiceService.save(pravnoLice);
		return new ResponseEntity<List<PravnoLice>>(pravnoLiceService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> izmeniPravnoLice(@RequestBody PravnoLice pravnoLice) {
		pravnoLiceService.save(pravnoLice);
		return new ResponseEntity<List<PravnoLice>>(pravnoLiceService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiPravnoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PravnoLice>> izbrisiPravnoLice(@RequestBody Long pravnoLiceId) {
		pravnoLiceService.delete(pravnoLiceId);
		return new ResponseEntity<List<PravnoLice>>(pravnoLiceService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajPravnoLice", method = RequestMethod.GET)
	public ResponseEntity<List<PravnoLice>> izlistajPravnoLice() {
		return new ResponseEntity<List<PravnoLice>>(pravnoLiceService.findAll(), HttpStatus.OK);
	}
}
