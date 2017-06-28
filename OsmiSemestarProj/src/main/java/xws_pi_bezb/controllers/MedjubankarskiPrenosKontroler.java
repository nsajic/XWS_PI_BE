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

import xws_pi_bezb.annotations.InterceptorAnnotation;
import xws_pi_bezb.iservices.IBankaService;
import xws_pi_bezb.iservices.IDelatnostService;
import xws_pi_bezb.iservices.IMedjubankarskiPrenosService;
import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.MedjubankarskiPrenos;
import xws_pi_bezb.models.Racun;
import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;	

@Controller
@RequestMapping("/medjubankarskiPrenosKontroler")
public class MedjubankarskiPrenosKontroler {

	@Autowired
	public IMedjubankarskiPrenosService medjubankarskiPrenosService;
	@Autowired
	public IBankaService bankaService;

	@RequestMapping(value = "/izlistajMedjubankarskePrenose", method = RequestMethod.GET)
	//@InterceptorAnnotation("Delatnost:IzlistajPretrazi")
	public ResponseEntity<List<MedjubankarskiPrenos>> izlistajDelatnosti(HttpSession session) {
		
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<List<MedjubankarskiPrenos>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<MedjubankarskiPrenos>>(medjubankarskiPrenosService.findByBanke(bankaService.findOne(sluzbenik.getBanka().getId())), HttpStatus.OK);
	}


}