package xws_pi_bezb.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import xws_pi_bezb.iservices.IBankarskiSluzbenikService;
import xws_pi_bezb.iservices.IDnevnoStanjeRacunaService;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.Racun;
import xws_pi_bezb.models.Valuta;
import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;


@Controller
@RequestMapping("/racunKontroler")
public class RacunKontroler {

	@Autowired
	public IRacunService racunService;
	@Autowired
	public IKlijentService klijentService;
	@Autowired
	public IValutaService valutaService;
	@Autowired
	public IBankaService bankaService;
	@Autowired
	private IDnevnoStanjeRacunaService dnevnoStanjeRacunaService;
	@Autowired
	private IBankarskiSluzbenikService bankarskiSluzbenikService;

	@RequestMapping(value = "/dodajRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:Dodaj")
	public ResponseEntity<Object> dodajRacun(HttpSession session,@RequestBody Racun racun ) throws ParseException {
		DnevnoStanjeRacuna dsr = new DnevnoStanjeRacuna();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		
		System.out.println(todayWithZeroTime);
		
		dsr.setDatum(todayWithZeroTime);
		dsr.setNovoStanje(0);
		dsr.setPrethodnoStanje(0);
		dsr.setPrometNaTeret(0);
		dsr.setPrometUKorist(0);
		dsr.setRacun(racun);
		
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
		racun.setBanka(bankaService.findOne(sluzbenik.getBanka().getId()));
		
		racunService.save(racun);
		System.out.println(racunService.findOne(racun.getId()).getBanka().getNazivBanke());
		dnevnoStanjeRacunaService.save(dsr);
		return new ResponseEntity<Object>(racunService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:Izmeni")
	public ResponseEntity<Object> izmeniRacun(@RequestBody Racun racun, HttpSession session) {
		
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
		racun.setBanka(bankaService.findOne(sluzbenik.getBanka().getId()));
		
		racunService.save(racun);
		System.out.println(racunService.findOne(racun.getId()).getBanka().getNazivBanke());
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:Obrisi")
	public ResponseEntity<Object> izbrisiRacun(@RequestBody Long racunId) {
		racunService.delete(racunId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajRacune", method = RequestMethod.GET)
	@InterceptorAnnotation("Racun:IzlistajPretrazi")
	public ResponseEntity<List<Racun>> izlistajRacune(HttpSession session) {
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<List<Racun>>(HttpStatus.BAD_REQUEST);
		}
		BankarskiSluzbenik sluzbenik2 = bankarskiSluzbenikService.findOne(sluzbenik.getId());
		
		
		return new ResponseEntity<List<Racun>>(racunService.findByBanka(sluzbenik2.getBanka()), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretraziRacune", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:IzlistajPretrazi")
	public ResponseEntity<List<Racun>> pretraziPravnaLica(@RequestBody Racun racun, HttpSession session) {
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<List<Racun>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Racun>>(racunService.getRacunBySearch(racun, sluzbenik.getBanka()), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/ucitajRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:IzlistajPretrazi")
	public ResponseEntity<Racun> ucitajPravnoLice(@RequestBody Racun racun) {
		return new ResponseEntity<Racun>(racunService.findOne(racun.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajValute", method = RequestMethod.GET)
	@InterceptorAnnotation("Valuta:IzlistajPretrazi")
	public ResponseEntity<List<Valuta>> ucitajValute() {
		return new ResponseEntity<List<Valuta>>(valutaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajKlijente", method = RequestMethod.GET)
	@InterceptorAnnotation("Banka:IzlistajPretrazi")
	public ResponseEntity<List<Klijent>> ucitajKlijente() {
		return new ResponseEntity<List<Klijent>>(klijentService.findAll(), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/ucitajDnevnaStanjaOdabranogRacuna", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:DnevnoStanjeOdabranogRacuna")
	public ResponseEntity<List<DnevnoStanjeRacuna>> ucitajDnevnaStanjaOdabranogRacuna(@RequestBody Racun racun) {
		List<DnevnoStanjeRacuna> retVal = dnevnoStanjeRacunaService.findByRacun(racun);
		return new ResponseEntity<List<DnevnoStanjeRacuna>>(retVal, HttpStatus.OK);
	}

}
