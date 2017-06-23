package xws_pi_bezb.view_models;

import xws_pi_bezb.models.Delatnost;
import xws_pi_bezb.models.PravnoLice;

public class PretragaPravnihLicaViewModel {

	private PravnoLice pravnoLice;
	private Delatnost delatnost;
	
	public PretragaPravnihLicaViewModel (){}
	
	public Delatnost getDelatnost() {
		return delatnost;
	}
	public PravnoLice getPravnoLice() {
		return pravnoLice;
	}
	public void setDelatnost(Delatnost delatnost) {
		this.delatnost = delatnost;
	}
	public void setPravnoLice(PravnoLice pravnoLice) {
		this.pravnoLice = pravnoLice;
	}
	
}
