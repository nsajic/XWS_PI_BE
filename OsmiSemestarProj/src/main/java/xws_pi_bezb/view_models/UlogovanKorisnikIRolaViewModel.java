package xws_pi_bezb.view_models;

import xws_pi_bezb.models.Rola;
import xws_pi_bezb.models.korisnici.Korisnik;

public class UlogovanKorisnikIRolaViewModel {

	private Korisnik korisnik;
	private Rola rola;
	
	public UlogovanKorisnikIRolaViewModel(){}
	
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	
	public Rola getRola() {
		return rola;
	}
	public void setRola(Rola rola) {
		this.rola = rola;
	}
	
}
