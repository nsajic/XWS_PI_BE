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

import xws_pi_bezb.iservices.IFizickoLiceService;
import xws_pi_bezb.models.FizickoLice;


@Controller
@RequestMapping("/fizickoLiceKontroler")
public class FizickoLiceKontroler {

	@Autowired
	public IFizickoLiceService fizickoLiceService;
	
	@RequestMapping(value = "/dodajFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FizickoLice>> dodajFizickoLice(@RequestBody FizickoLice fizickoLice) {
		fizickoLiceService.save(fizickoLice);
		return new ResponseEntity<List<FizickoLice>>(fizickoLiceService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FizickoLice>> izmeniFizickoLice(@RequestBody FizickoLice fizickoLice) {
		fizickoLiceService.save(fizickoLice);
		return new ResponseEntity<List<FizickoLice>>(fizickoLiceService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiFizickoLice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FizickoLice>> izbrisiFizickoLice(@RequestBody Long pravnoLiceId) {
		fizickoLiceService.delete(pravnoLiceId);
		return new ResponseEntity<List<FizickoLice>>(fizickoLiceService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajFizickoLice", method = RequestMethod.GET)
	public ResponseEntity<List<FizickoLice>> izlistajFizickoLice() {
		return new ResponseEntity<List<FizickoLice>>(fizickoLiceService.findAll(), HttpStatus.OK);
	}
	
}
