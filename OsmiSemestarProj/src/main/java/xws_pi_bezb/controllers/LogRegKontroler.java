package xws_pi_bezb.controllers;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xws_pi_bezb.helpers.Poruka;
import xws_pi_bezb.iservices.IBankaService;
import xws_pi_bezb.iservices.IBankarskiSluzbenikService;
import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;
import xws_pi_bezb.password_security.Password;
import xws_pi_bezb.password_security.PasswordValidator;
import xws_pi_bezb.view_models.PromenaLozinkeViewModel;

@Controller
@Scope("session")
@RequestMapping("/contr")
public class LogRegKontroler {

	@Autowired
	private IBankarskiSluzbenikService bankarskiSluzbenikService;

	@Autowired
	private IBankaService bankaService;

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 * 
	 * @Autowired private AuthenticationManager authenticationManager;
	 */

	// TODO: nsajic Logger
    Logger logger = LoggerFactory.getLogger(LogRegKontroler.class);

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.POST, consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public @ResponseBody
	 * ResponseEntity<Poruka> login(@RequestBody Korisnik newUser, HttpSession
	 * session){ Korisnik kor = (Korisnik)
	 * session.getAttribute("ulogovanKorisnik");
	 * 
	 * //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 * //String hashedPassword = passwordEncoder.encode(newUser.getSifra());
	 * //System.out.println(hashedPassword);
	 * 
	 * if(kor == null){ UserDetails userDetails =
	 * korisnikService.loadUserByUsername(newUser.getEmail());
	 * UsernamePasswordAuthenticationToken authenticationToken = new
	 * UsernamePasswordAuthenticationToken( userDetails, newUser.getSifra(),
	 * userDetails.getAuthorities());
	 * authenticationManager.authenticate(authenticationToken);
	 * 
	 * if (authenticationToken.isAuthenticated()){ Korisnik korisnik =
	 * korisnikService.findByEmail(newUser.getEmail());
	 * session.setAttribute("ulogovanKorisnik", korisnik);
	 * SecurityContextHolder.getContext().setAuthentication(authenticationToken)
	 * ; return new ResponseEntity<Poruka>(new Poruka(korisnik.getIme(), null),
	 * HttpStatus.ACCEPTED); }
	 * 
	 * return new ResponseEntity<Poruka>(new Poruka("NePostoji", null),
	 * HttpStatus.ACCEPTED); }else{ return new ResponseEntity<Poruka>(new
	 * Poruka("VecUlogovan", kor), HttpStatus.ACCEPTED); } }
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Poruka> login(@RequestBody BankarskiSluzbenik newUser, HttpSession session) {
		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");

		if (kor == null) {
			BankarskiSluzbenik korisnik = bankarskiSluzbenikService.findByEmail(newUser.getEmail());

			if (Password.checkPassword(newUser.getSifra(), korisnik.getSifra())) {
				session.setAttribute("ulogovanKorisnik", korisnik);
				
				return new ResponseEntity<Poruka>(new Poruka(korisnik.getIme(), null), HttpStatus.ACCEPTED);
			}
			logger.error("Neuspesan pokusaj logovanja sa email-om: " + korisnik.getEmail());
			return new ResponseEntity<Poruka>(new Poruka("NePostoji", null), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Poruka>(new Poruka("VecUlogovan", kor), HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Poruka> logout(HttpSession session) {

		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");
		if (kor != null) {
			// korisnikService.logoutKorisnik();
			session.invalidate();
			return new ResponseEntity<Poruka>(new Poruka("Izlogovan", null), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Poruka>(new Poruka("NijeIzlogovan", null), HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)

	public ResponseEntity<Poruka> resetPassword(@RequestBody PromenaLozinkeViewModel user, HttpSession session) throws InterruptedException{

		if(user.getNovaLozinka().equals(user.getNovaLozinka2())){

			PasswordValidator validator = new PasswordValidator();

			if (!validator.validate(user.getNovaLozinka())) {
				return new ResponseEntity<Poruka>(new Poruka(
						"Lozinka mora sadrzati mala i velika slova, brojeve i neke od specijalnih karaktera '@#$%' i mora biti duza od 6 karaktera.",
						null), HttpStatus.NOT_ACCEPTABLE);

			}
			BankarskiSluzbenik korisnik = bankarskiSluzbenikService.findByEmail(((BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik")).getEmail());

			if (Password.checkPassword(user.getStaraLozinka(), korisnik.getSifra())) {
				String hashedPassword = Password.hashPassword(user.getNovaLozinka());

				korisnik.setSifra(hashedPassword);
				bankarskiSluzbenikService.save(korisnik);

				session.setAttribute("ulogovanKorisnik", korisnik);
				return new ResponseEntity<Poruka>(new Poruka("Promenjeno", null), HttpStatus.ACCEPTED);
			}

			return new ResponseEntity<Poruka>(new Poruka("NePostoji", null), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Poruka>(new Poruka("RazliciteLozinke", null), HttpStatus.ACCEPTED);
		}
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResponseEntity<Poruka> checkSessions(HttpSession session) {
		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");
		if (kor != null) {
			return new ResponseEntity<Poruka>(new Poruka("NekoNaSesiji", kor), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Poruka>(new Poruka("NikoNaSesiji", null), HttpStatus.ACCEPTED);
		}

	}
	
	@RequestMapping(value = "/posaljiKliring", method = RequestMethod.POST)
	public ResponseEntity<Poruka> posaljiKliring(HttpSession session) {
		BankarskiSluzbenik kor = (BankarskiSluzbenik) session.getAttribute("ulogovanKorisnik");
		Banka banka = bankaService.findOne(kor.getBanka().getId());
		
		
		//TODO Coa kliring
		
		return new ResponseEntity<Poruka>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/spisakRacuna", method = RequestMethod.POST)
	public ResponseEntity<Poruka> spisakRacuna() {
		
		return new ResponseEntity<Poruka>(new Poruka("Spisak exportovan.", null), HttpStatus.ACCEPTED);
	}
	
}
