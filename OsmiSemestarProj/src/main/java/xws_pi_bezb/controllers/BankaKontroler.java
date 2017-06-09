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
import xws_pi_bezb.models.Banka;	

@Controller
@RequestMapping("/bankaKontroler")
public class BankaKontroler {

	@Autowired
	public IBankaService bankaService;

	@RequestMapping(value = "/dodajBanku", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Banka>> dodajBanku(@RequestBody Banka banka) {
		bankaService.save(banka);
		return new ResponseEntity<List<Banka>>(bankaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izmeniBanku", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Banka>> izmeniBanku(@RequestBody Banka banka) {
		bankaService.save(banka);
		return new ResponseEntity<List<Banka>>(bankaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiBanku", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Banka>> izbrisiBanku(@RequestBody Long bankaId) {
		bankaService.delete(bankaId);
		return new ResponseEntity<List<Banka>>(bankaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajBanke", method = RequestMethod.GET)
	public ResponseEntity<List<Banka>> izlistajBanku() {
		return new ResponseEntity<List<Banka>>(bankaService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretraziBanke", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Banka>> pretraziBanke(@RequestBody Banka banka) {
		return new ResponseEntity<List<Banka>>(bankaService.getBySearch(banka), HttpStatus.OK);
	}

}