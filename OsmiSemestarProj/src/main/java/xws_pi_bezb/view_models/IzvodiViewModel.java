package xws_pi_bezb.view_models;

import java.util.Date;

public class IzvodiViewModel {

	private Long iDrac;
	private Date datumOd;
	private Date datumDo;
	
	public IzvodiViewModel(){}

	public Long getiDrac() {
		return iDrac;
	}

	public void setiDrac(Long iDrac) {
		this.iDrac = iDrac;
	}

	public Date getDatumOd() {
		return datumOd;
	}

	public void setDatumOd(Date datumOd) {
		this.datumOd = datumOd;
	}

	public Date getDatumDo() {
		return datumDo;
	}

	public void setDatumDo(Date datumDo) {
		this.datumDo = datumDo;
	}
}
