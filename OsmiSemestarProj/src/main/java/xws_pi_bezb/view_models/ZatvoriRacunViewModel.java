package xws_pi_bezb.view_models;

public class ZatvoriRacunViewModel {
	private Long idRacunKojiGasim;
	private String brojRacunaNaKojiPrebacujem;

	public ZatvoriRacunViewModel() {}

	public String getBrojRacunaNaKojiPrebacujem() {
		return brojRacunaNaKojiPrebacujem;
	}

	public Long getIdRacunKojiGasim() {
		return idRacunKojiGasim;
	}

	public void setBrojRacunaNaKojiPrebacujem(String brojRacunaNaKojiPrebacujem) {
		this.brojRacunaNaKojiPrebacujem = brojRacunaNaKojiPrebacujem;
	}

	public void setIdRacunKojiGasim(Long idRacunKojiGasim) {
		this.idRacunKojiGasim = idRacunKojiGasim;
	}

}
