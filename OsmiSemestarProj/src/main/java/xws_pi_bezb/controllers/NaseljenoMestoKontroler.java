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

import xws_pi_bezb.models.NaseljenoMesto;
import xws_pi_bezb.services.NaseljenoMestoService;

@Controller
@RequestMapping("/naseljenoMestoKontroler")
public class NaseljenoMestoKontroler {

	@Autowired
	public NaseljenoMestoService naseljenoMestoService;

	@RequestMapping(value = "/dodajNaseljenoMesto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NaseljenoMesto>> dodajNaseljenoMesto(@RequestBody NaseljenoMesto naseljenoMesto) {
		naseljenoMestoService.save(naseljenoMesto);
		return new ResponseEntity<List<NaseljenoMesto>>(naseljenoMestoService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniNaseljenoMesto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NaseljenoMesto>> izmeniNaseljenoMesto(@RequestBody NaseljenoMesto naseljenoMesto) {
		naseljenoMestoService.save(naseljenoMesto);
		return new ResponseEntity<List<NaseljenoMesto>>(naseljenoMestoService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiNaseljenoMesto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NaseljenoMesto>> izbrisiNaseljenoMesto(@RequestBody Long naseljenoMestoId) {
		naseljenoMestoService.delete(naseljenoMestoId);
		return new ResponseEntity<List<NaseljenoMesto>>(naseljenoMestoService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajNaseljenaMesta", method = RequestMethod.GET)
	public ResponseEntity<List<NaseljenoMesto>> izlistajNaseljenaMesta() {
		return new ResponseEntity<List<NaseljenoMesto>>(naseljenoMestoService.findAll(), HttpStatus.OK);
	}

}
