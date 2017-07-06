package xws_pi_bezb.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xws_pi_bezb.iservices.IAnalitikaIzvodaService;
import xws_pi_bezb.iservices.IBankaService;
import xws_pi_bezb.iservices.IMedjubankarskiPrenosService;
import xws_pi_bezb.models.AnalitikaIzvoda;
import xws_pi_bezb.models.MedjubankarskiPrenos;
import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;	

@Controller
@RequestMapping("/medjubankarskiPrenosKontroler")
public class MedjubankarskiPrenosKontroler {

	@Autowired
	public IMedjubankarskiPrenosService medjubankarskiPrenosService;
	@Autowired
	public IBankaService bankaService;
	@Autowired
	public IAnalitikaIzvodaService analitikaIzvodaService;
	
	

	@RequestMapping(value = "/izlistajMedjubankarskePrenose", method = RequestMethod.GET)
	//@InterceptorAnnotation("Delatnost:IzlistajPretrazi")
	public ResponseEntity<List<MedjubankarskiPrenos>> izlistajMedjubankarskePrenose(HttpSession session) {
		
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<List<MedjubankarskiPrenos>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<MedjubankarskiPrenos>>(medjubankarskiPrenosService.findByBanke(bankaService.findOne(sluzbenik.getBanka().getId())), HttpStatus.OK);
	}

	
	
	@RequestMapping(value = "/ucitajMedjubankarskeOdabraneAnalitike", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Delatnost:IzlistajPretrazi")
	public  ResponseEntity<List<MedjubankarskiPrenos>> ucitajMedjubankarskeOdabraneAnalitike(@RequestBody AnalitikaIzvoda analitikaIzvoda, HttpSession session) {
		return new ResponseEntity<List<MedjubankarskiPrenos>>(medjubankarskiPrenosService.findByAnalitikaIzvodaId(analitikaIzvoda.getId()), HttpStatus.OK);
	}

}