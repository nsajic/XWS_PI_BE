package xws_pi_bezb.view_models;

import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;

public class UlogovanKorisnikIRolaViewModel {

	private BankarskiSluzbenik korisnik;
	//private Rola rola;
	
	public UlogovanKorisnikIRolaViewModel(){}
	
	public void setKorisnik(BankarskiSluzbenik korisnik) {
		this.korisnik = korisnik;
	}
	public BankarskiSluzbenik getKorisnik() {
		return korisnik;
	}
	
	/*public Rola getRola() {
		return rola;
	}*/
	/*public void setRola(Rola rola) {
		this.rola = rola;
	}*/
	
}
