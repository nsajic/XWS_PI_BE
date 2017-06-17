package xws_pi_bezb.controllers;

import javax.servlet.http.HttpSession;

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
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.models.korisnici.Korisnik;
import xws_pi_bezb.password_security.Password;
import xws_pi_bezb.view_models.PromenaLozinkeViewModel;


@Controller
@Scope("session")
@RequestMapping("/contr")
public class LogRegKontroler {

	@Autowired
	private IKlijentService korisnikService;
	
	/*@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;*/
	
	//TODO: nsajic Logger
	//Logger logger = LoggerFactory.getLogger(LogRegKontroler.class);
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Poruka> login(@RequestBody Korisnik newUser, HttpSession session){	
		Korisnik kor = (Korisnik) session.getAttribute("ulogovanKorisnik");
				
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode(newUser.getSifra());
		//System.out.println(hashedPassword);
		
		if(kor == null){
			UserDetails userDetails = korisnikService.loadUserByUsername(newUser.getEmail());
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, newUser.getSifra(), userDetails.getAuthorities());
			authenticationManager.authenticate(authenticationToken);
			
			if (authenticationToken.isAuthenticated()){
				Korisnik korisnik = korisnikService.findByEmail(newUser.getEmail());
				session.setAttribute("ulogovanKorisnik", korisnik);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				return new ResponseEntity<Poruka>(new Poruka(korisnik.getIme(), null), HttpStatus.ACCEPTED);
			}
			
			return new ResponseEntity<Poruka>(new Poruka("NePostoji", null), HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<Poruka>(new Poruka("VecUlogovan", kor), HttpStatus.ACCEPTED);
		}	
	}*/
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Poruka> login(@RequestBody Korisnik newUser, HttpSession session){	
		Korisnik kor = (Korisnik) session.getAttribute("ulogovanKorisnik");	
		
		if(kor == null){
			Korisnik korisnik = korisnikService.findByEmail(newUser.getEmail());
				
			if (Password.checkPassword(newUser.getSifra(), korisnik.getSifra())){
				session.setAttribute("ulogovanKorisnik", korisnik);
				
				return new ResponseEntity<Poruka>(new Poruka(korisnik.getIme(), null), HttpStatus.ACCEPTED);
			}
			
			return new ResponseEntity<Poruka>(new Poruka("NePostoji", null), HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<Poruka>(new Poruka("VecUlogovan", kor), HttpStatus.ACCEPTED);
		}	
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Poruka> logout(HttpSession session){	
		
		Korisnik kor = (Korisnik) session.getAttribute("ulogovanKorisnik");
		if(kor != null){
			//korisnikService.logoutKorisnik();
			session.invalidate();
			return new ResponseEntity<Poruka>(new Poruka("Izlogovan", null), HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<Poruka>(new Poruka("NijeIzlogovan", null), HttpStatus.ACCEPTED);			
		}
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<Poruka> resetPassword(@RequestBody PromenaLozinkeViewModel user, HttpSession session){	
		if(user.getNovaLozinka().equals(user.getNovaLozinka2())){
	
			Korisnik korisnik = korisnikService.findByEmail(((Korisnik) session.getAttribute("ulogovanKorisnik")).getEmail());
				
			if (Password.checkPassword(user.getStaraLozinka(), korisnik.getSifra())){			
				String hashedPassword = Password.hashPassword(user.getNovaLozinka());
				
				korisnik.setSifra(hashedPassword);
				korisnikService.save(korisnik);
				
				session.setAttribute("ulogovanKorisnik", korisnik);
				return new ResponseEntity<Poruka>(new Poruka("Promenjeno", null), HttpStatus.ACCEPTED);
			}
				
			return new ResponseEntity<Poruka>(new Poruka("NePostoji", null), HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<Poruka>(new Poruka("RazliciteLozinke", null), HttpStatus.ACCEPTED);
		}	
	}
}
