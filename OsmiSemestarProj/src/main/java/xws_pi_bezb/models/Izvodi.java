package xws_pi_bezb.models;

import java.util.List;

public class Izvodi {

	private List<AnalitikaIzvoda> analitike;
	
	public Izvodi(){
		
	}

	public List<AnalitikaIzvoda> getAnalitike() {
		return analitike;
	}

	public void setAnalitike(List<AnalitikaIzvoda> analitike) {
		this.analitike = analitike;
	}
	
}
