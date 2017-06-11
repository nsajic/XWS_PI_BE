package xws_pi_bezb.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.pomocni.Poruka;


@Controller
@Scope("session")
@RequestMapping("/contr")
public class LogRegKontroler {

	
	/*@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poruka> getNew(Model model, @RequestBody Korisnik newGuest) {
		
		if(newGuest.getSifra().equals(newGuest.getSifraStara())){
			newGuest.setTipKorisnika(TipKorisnika.GOST);
			
			if(servis.findByEmail(newGuest.getEmail()) != null){
				return new ResponseEntity<Poruka>(new Poruka("ZauzetEmail", null), HttpStatus.ACCEPTED);
			}
			
			newGuest.setIsActivated(false);
			servis.save(newGuest);	
			SendMail sm = new SendMail("nikola9n@gmail.com","Aktivirajte nalog klikom na link: " + "http://localhost:9000/contr/activate/"+newGuest.getEmail()+"/");
			
			return new ResponseEntity<Poruka>(new Poruka("Registrovan", newGuest), HttpStatus.ACCEPTED);	
		}else{
			return new ResponseEntity<Poruka>(new Poruka("RazliciteSifre", newGuest), HttpStatus.ACCEPTED);
		}
	}*/
	
	/*@Transactional
	@RequestMapping(value = "/activate/{email}")
	public ResponseEntity<String> activateAccount(@PathVariable String email) {			
		
		try{
			Gost gost = servis.findByEmail(email);
			servis.activateAccount(gost.getEmail());
			return new ResponseEntity<String>("Uspesno ste aktivirali nalog!", HttpStatus.ACCEPTED);			
		}catch(Exception ex){}
		return new ResponseEntity<String>("Neuspesna aktivacija naloga.", HttpStatus.ACCEPTED);
		
	}*/


	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Poruka> getKorisnik(@RequestBody Klijent newGuest, HttpSession session){	
		Klijent kor = (Klijent) session.getAttribute("ulogovanKorisnik");
		
		
		
		if(kor == null){
			//Gost korisnik = servis.findByEmail(newGuest.getEmail());			
			//if(korisnik != null && korisnik.getSifra().equals(newGuest.getSifra())){
				
				//if(korisnik.getTipKorisnika().equals(TipKorisnika.GOST) && korisnik.getIsActivated()){
					//model.addAttribute("korisnik", korisnik);
					//session.setAttribute("ulogovanKorisnik", korisnik);
					//return new ResponseEntity<Poruka>(new Poruka("Ulogovan", korisnik), HttpStatus.ACCEPTED);
				//}else if(korisnik.getTipKorisnika().equals(TipKorisnika.GOST) && !korisnik.getIsActivated()){
				//	return new ResponseEntity<Poruka>(new Poruka("NijeAktiviran", korisnik), HttpStatus.ACCEPTED);
				//}else{}
				//if (!model.containsAttribute("korisnik")) {
					//model.addAttribute("korisnik", korisnik);
				//}

			//}else{
				//Korisnik otherKoris = korServis.findByEmail(newGuest.getEmail());
				//if(otherKoris != null && otherKoris.getSifra().equals(newGuest.getSifra())){
					//model.addAttribute("korisnik", otherKoris);
					//session.setAttribute("ulogovanKorisnik", otherKoris);
					//return new ResponseEntity<Poruka>(new Poruka("Ulogovan", otherKoris), HttpStatus.ACCEPTED);
				//}
			//}
			return new ResponseEntity<Poruka>(new Poruka("NePostoji", null), HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<Poruka>(new Poruka("VecUlogovan", kor), HttpStatus.ACCEPTED);
		}
		
	}
	
	/*@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Poruka> logout(HttpSession session){	
		
		Korisnik kor = (Korisnik) session.getAttribute("ulogovanKorisnik");
		if(kor != null){
			session.invalidate();
			return new ResponseEntity<Poruka>(new Poruka("Izlogovan", null), HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<Poruka>(new Poruka("NijeIzlogovan", null), HttpStatus.ACCEPTED);			
		}
	}*/
	
	/*@RequestMapping(value = "/check2", method = RequestMethod.POST)
	public ResponseEntity<Korisnik> checkSession(){
		Map<String,Object> map = modelAndView.getModel();
		Korisnik kor = (Korisnik) map.get("ulogovanKorisnik");
		if(kor != null){
			return new ResponseEntity<Korisnik>(kor, HttpStatus.ACCEPTED);
		}else{
			return null;
		}	
			
	}*/
	
	
	/*@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ResponseEntity<Poruka> checkSessions(@ModelAttribute Korisnik naSesiji, HttpSession session){
		Korisnik kor = (Korisnik) session.getAttribute("ulogovanKorisnik");
		if(kor != null){
			return new ResponseEntity<Poruka>(new Poruka("NekoNaSesiji", kor), HttpStatus.ACCEPTED);
		}else{
			return new ResponseEntity<Poruka>(new Poruka("NikoNaSesiji", null), HttpStatus.ACCEPTED);
		}	
			
	}*/
	
	
	
}
