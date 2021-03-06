package xws_pi_bezb.webservis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;

import bezbednost.poslovna.xml.ws.izvod.IzvodRequest;
import bezbednost.poslovna.xml.ws.izvod.IzvodResponse;
import bezbednost.poslovna.xml.ws.izvod.TStavkaPreseka;
import bezbednost.poslovna.xml.ws.izvod.TZaglavljePreseka;
import bezbednost.poslovna.xml.ws.izvod.TZahtev;
import bezbednost.poslovna.xml.ws.mt102.MT102Request;
import bezbednost.poslovna.xml.ws.mt102.MT102Response;
import bezbednost.poslovna.xml.ws.mt102.TPojedinacnoPlacanje;
import bezbednost.poslovna.xml.ws.mt103.MT103Request;
import bezbednost.poslovna.xml.ws.mt103.MT103Response;
import bezbednost.poslovna.xml.ws.mt103.TSWIFTIRacun;
import bezbednost.poslovna.xml.ws.mt900.MT900Request;
import bezbednost.poslovna.xml.ws.mt900.MT900Response;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosRequest;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosResponse;
import bezbednost.poslovna.xml.ws.nalogzaprenos.TNalog;
import bezbednost.poslovna.xml.ws.nalogzaprenos.TPodaciORacunu;
import xws_pi_bezb.BankaKlijentSamoTest;
import xws_pi_bezb.controllers.LogRegKontroler;
import xws_pi_bezb.iservices.IAnalitikaIzvodaService;
import xws_pi_bezb.iservices.IBankaService;
import xws_pi_bezb.iservices.IDnevnoStanjeRacunaService;
import xws_pi_bezb.iservices.IMT102Services;
import xws_pi_bezb.iservices.IMT103Services;
import xws_pi_bezb.iservices.IMedjubankarskiPrenosService;
import xws_pi_bezb.iservices.IPojedinacnoPlacanjeService;
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.iservices.IStavkaPrenosaService;
import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.AnalitikaIzvoda;
import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.MT102;
import xws_pi_bezb.models.MT103;
import xws_pi_bezb.models.MedjubankarskiPrenos;
import xws_pi_bezb.models.PojedinacnoPlacanje;
import xws_pi_bezb.models.Racun;
import xws_pi_bezb.models.StavkaPrenosa;
import xws_pi_bezb.xml.secutiry.DocumentLoader;
import xws_pi_bezb.xml.secutiry.KeyStoreReader;
import xws_pi_bezb.xml.secutiry.XMLEncryptionUtility;
import xws_pi_bezb.xml.secutiry.XMLSigningUtility;

@Endpoint
public class BankaEndpoint {
	private static final String HTTP = "http://";
	private static final String NAMESPACE_URI = "ws.xml.poslovna.bezbednost/";
	private Logger logger = LoggerFactory.getLogger(LogRegKontroler.class);
	private static final int presek = 2;

	@Autowired
	private IRacunService racunService;

	@Autowired
	private IDnevnoStanjeRacunaService dnevnoStanjeRacunaService;

	@Autowired
	private IValutaService valutaService;

	@Autowired
	private IBankaService bankaService;

	@Autowired
	private IAnalitikaIzvodaService analitikaIzvodaService;

	@Autowired
	private IMT102Services mt102Servis;

	@Autowired
	private IMT103Services mt103Servis;

	@Autowired
	private IPojedinacnoPlacanjeService pojedinacnoPlacanjeServis;

	@Autowired
	private IMedjubankarskiPrenosService medjubankarskiPrenosService;

	@Autowired
	private IStavkaPrenosaService stavkaServis;

	@Autowired
	public BankaEndpoint() {

	}

	@PayloadRoot(namespace = HTTP + "nalogzaprenos." + NAMESPACE_URI, localPart = "NalogZaPrenosRequest")
	@ResponsePayload
	public NalogZaPrenosResponse nalogZaPrenos(@RequestPayload NalogZaPrenosRequest request1) {
		
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		NalogZaPrenosRequest request = checkSignAndEnc(request1);
		if (request == null) {
			response.setOdgovor("Potpis ili enkripcija nije u redu!");
			return response;
		}
		//System.out.println("*** Stigao je nalog za prenos u PB");
		//System.out.println(request.toString());

		TPodaciORacunu racunDuznik = request.getNalog().getDuznikRacun();
		TPodaciORacunu racunPoverilac = request.getNalog().getPoverilacRacun();

		if (racunDuznik != null && racunPoverilac != null) {

			String brojRacunaDuznik = racunDuznik.getRacun();
			Racun racunDuz = racunService.findByBrojRacuna(brojRacunaDuznik);
			String brojRacunaPoverilac = racunPoverilac.getRacun();
			Racun racunPov = racunService.findByBrojRacuna(brojRacunaPoverilac);

			if (racunDuz == null) {
				response.setOdgovor("Racun duznika ne postoji!");
				return response;
			}

			if (racunPov == null) {
				response.setOdgovor("Racun poverioca ne postoji!");
				return response;
			}

			if (racunDuz.getStatusRacuna() == 2) {
				response.setOdgovor("Racun duznika je zatvoren!");
				return response;
			}

			if (racunPov.getStatusRacuna() == 2) {
				response.setOdgovor("Racun poverioca je zatvoren!");
				return response;
			}

			// provera da li ima sredstava
			DnevnoStanjeRacuna poslednjeDnevnoStanje = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunDuz);

			if (poslednjeDnevnoStanje == null) {
				response.setOdgovor("Na racunu 0 - duznik!");
				return response;
			}

			// provera da li ima dovoljno sredstava
			if ((poslednjeDnevnoStanje.getNovoStanje() - racunDuz.getRezervisano()) >= request.getNalog().getIznos()
					.doubleValue()) {

				// provera da li su racuni iz iste banke
				if (racunDuz.getBanka().getId().equals(racunPov.getBanka().getId())) {

					// pravimo analitiku za oba
					int godina = Calendar.getInstance().get(Calendar.YEAR);
					int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
					int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

					AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
					analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
					analitikaIzvoda.setSmer("I");
					analitikaIzvoda.setDuznikNalogodavac(request.getNalog().getDuznik());
					analitikaIzvoda.setSvrhaPlacanja(request.getNalog().getSvrhaPlacanja());
					analitikaIzvoda.setPrimalacPoverilac(request.getNalog().getPrimalac());
					analitikaIzvoda.setDatumNaloga(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
					analitikaIzvoda.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
					analitikaIzvoda.setRacunDuznika(brojRacunaDuznik);
					analitikaIzvoda.setModelZaduzenja(racunDuznik.getModel());
					analitikaIzvoda.setPozivNaBrojZaduzenja(racunDuznik.getPozivNaBroj());
					analitikaIzvoda.setRacunPoverioca(brojRacunaPoverilac);
					analitikaIzvoda.setModelOdobrenja(racunPoverilac.getModel());
					analitikaIzvoda.setPozivNaBrojOdobrenja(racunPoverilac.getPozivNaBroj());
					analitikaIzvoda.setIznos(request.getNalog().getIznos().doubleValue());
					analitikaIzvoda.setValuta(valutaService.findBySifraValute(request.getOznakaValute()));

					// menjamo dnevno stanje za oba

					DnevnoStanjeRacuna dnevnoStanjeRacunaDuznika = dnevnoStanjeRacunaService
							.findByRacunAndDatum(racunDuz, danasnjiDatum);
					if (dnevnoStanjeRacunaDuznika == null) {
						dnevnoStanjeRacunaDuznika = new DnevnoStanjeRacuna();
						dnevnoStanjeRacunaDuznika.setDatum(danasnjiDatum);
						dnevnoStanjeRacunaDuznika.setRacun(racunDuz);
						dnevnoStanjeRacunaDuznika.setPrometNaTeret(request.getNalog().getIznos().doubleValue());
						dnevnoStanjeRacunaDuznika.setPrometUKorist(0);
						dnevnoStanjeRacunaDuznika.setPrethodnoStanje(poslednjeDnevnoStanje.getNovoStanje());
						dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje()
								- dnevnoStanjeRacunaDuznika.getPrometNaTeret());
					} else {
						dnevnoStanjeRacunaDuznika.setPrometNaTeret(dnevnoStanjeRacunaDuznika.getPrometNaTeret()
								+ request.getNalog().getIznos().doubleValue());
						// dnevnoStanjeRacuna.setPrethodnoStanje(dnevnoStanjeRacuna.getNovoStanje());
						dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje()
								+ dnevnoStanjeRacunaDuznika.getPrometUKorist()
								- dnevnoStanjeRacunaDuznika.getPrometNaTeret());
					}

					DnevnoStanjeRacuna dnevnoStanjeRacunaPoverioca = dnevnoStanjeRacunaService
							.findByRacunAndDatum(racunPov, danasnjiDatum);
					DnevnoStanjeRacuna poslednjeDnevnoStanjePoverilac = dnevnoStanjeRacunaService
							.findTopByRacunOrderByDatum(racunPov);
					if (dnevnoStanjeRacunaPoverioca == null) {
						dnevnoStanjeRacunaPoverioca = new DnevnoStanjeRacuna();
						dnevnoStanjeRacunaPoverioca.setDatum(danasnjiDatum);
						dnevnoStanjeRacunaPoverioca.setRacun(racunPov);
						dnevnoStanjeRacunaPoverioca.setPrometNaTeret(0);
						dnevnoStanjeRacunaPoverioca.setPrometUKorist(request.getNalog().getIznos().doubleValue());

						if (poslednjeDnevnoStanjePoverilac != null)
							dnevnoStanjeRacunaPoverioca
									.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
						else
							dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(0);

						dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje()
								+ dnevnoStanjeRacunaPoverioca.getPrometUKorist());
					} else {
						dnevnoStanjeRacunaPoverioca.setPrometUKorist(dnevnoStanjeRacunaPoverioca.getPrometUKorist()
								+ request.getNalog().getIznos().doubleValue());

						dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje()
								+ dnevnoStanjeRacunaPoverioca.getPrometUKorist()
								- dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
					}

					dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaDuznika);
					dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaPoverioca);

					analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaDuznika);
					analitikaIzvodaService.save(analitikaIzvoda);
					analitikaIzvoda.setSmer("U");
					analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaPoverioca);
					analitikaIzvodaService.save(analitikaIzvoda);

				} else {
					int godina = Calendar.getInstance().get(Calendar.YEAR);
					int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
					int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

					// provera da li je preko 250000 ili hitno

					if (request.getNalog().getIznos().doubleValue() >= 250000 || request.isHitno()) {
						// rezervisemo sredstva
						racunDuz.setRezervisano(racunDuz.getRezervisano() + request.getNalog().getIznos().doubleValue());
						racunService.save(racunDuz);

						// mt103 - rtgs...
						MT103Request mt103Request = new MT103Request();

						Banka bankaDuznika = bankaService.findByRacun(racunDuz);
						Banka bankaPoverilac = bankaService.findByRacun(racunPov);

						TSWIFTIRacun swiftDuznik = new TSWIFTIRacun();
						swiftDuznik.setObracunskiRacun(bankaDuznika.getObracunskiRacun());
						swiftDuznik.setSWIFT(bankaDuznika.getSwiftKod());

						TSWIFTIRacun swiftPoverilac = new TSWIFTIRacun();
						swiftPoverilac.setObracunskiRacun(bankaPoverilac.getObracunskiRacun());
						swiftPoverilac.setSWIFT(bankaPoverilac.getSwiftKod());

						TNalog nalog = new TNalog();
						nalog.setDuznik(request.getNalog().getDuznik());
						nalog.setPrimalac(request.getNalog().getPrimalac());
						nalog.setSvrhaPlacanja(request.getNalog().getSvrhaPlacanja());
						nalog.setDuznikRacun(request.getNalog().getDuznikRacun());
						nalog.setPoverilacRacun(request.getNalog().getPoverilacRacun());
						nalog.setIznos(request.getNalog().getIznos());
						nalog.setDatumNaloga(request.getNalog().getDatumNaloga());
						nalog.setDatumValute(request.getNalog().getDatumValute());

						mt103Request.setIDPoruke("MT103-" + request.getIDPoruke());

						mt103Request.setBankaDuznika(swiftDuznik);
						mt103Request.setBankaPoverioca(swiftPoverilac);
						mt103Request.setNalog(nalog);
						mt103Request.setSifraValute(request.getOznakaValute());

						mt103Servis.save(konvertujMT103Request(mt103Request));

						BankaKlijentSamoTest bankaKlijent = new BankaKlijentSamoTest();
						bankaKlijent.posaljiMT103(mt103Request);
					} else {
						// mt102 - clearing...
						MT102 mt102 = mt102Servis.findBySwiftDuznikAndSwiftPoverilacAndPoslat(
								racunDuz.getBanka().getSwiftKod(), racunPov.getBanka().getSwiftKod(), false);
						if (mt102 == null) {
							mt102 = new MT102();
							mt102.setIdPoruke("MT102-" + request.getIDPoruke());
							mt102.setObracunskiRacunDuznik(racunDuz.getBanka().getObracunskiRacun());
							mt102.setObracunskiRacunPoverilac(racunPov.getBanka().getObracunskiRacun());
							mt102.setPoslat(false);
							mt102.setSifraValute(request.getOznakaValute());
							mt102.setSwiftDuznik(racunDuz.getBanka().getSwiftKod());
							mt102.setSwiftPoverilac(racunPov.getBanka().getSwiftKod());
							mt102.setUkupanIznos(request.getNalog().getIznos());
							mt102.setDatum(danasnjiDatum);
							mt102.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
						} else {
							BigDecimal rezultat = mt102.getUkupanIznos().add(request.getNalog().getIznos());
							mt102.setUkupanIznos(rezultat);
						}
						mt102Servis.save(mt102);

						racunDuz.setRezervisano(
								racunDuz.getRezervisano() + request.getNalog().getIznos().doubleValue());
						racunService.save(racunDuz);

						PojedinacnoPlacanje pojedinacnoPlacanje = new PojedinacnoPlacanje();
						pojedinacnoPlacanje.setIdNalogaZaPlacanje(request.getIDPoruke());
						pojedinacnoPlacanje
								.setDatumNaloga(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
						pojedinacnoPlacanje.setDuznik(request.getNalog().getDuznik());
						pojedinacnoPlacanje.setModelDuznik(request.getNalog().getDuznikRacun().getModel());
						pojedinacnoPlacanje.setModelPoverilac(request.getNalog().getPoverilacRacun().getModel());
						pojedinacnoPlacanje.setIznos(request.getNalog().getIznos());
						pojedinacnoPlacanje.setPozivNaBrojDuznik(request.getNalog().getDuznikRacun().getPozivNaBroj());
						pojedinacnoPlacanje
								.setPozivNaBrojPoverilac(request.getNalog().getPoverilacRacun().getPozivNaBroj());
						pojedinacnoPlacanje.setPrimalac(request.getNalog().getPrimalac());
						pojedinacnoPlacanje.setRacunDruznik(request.getNalog().getDuznikRacun().getRacun());
						pojedinacnoPlacanje.setRacunPoverilac(request.getNalog().getPoverilacRacun().getRacun());
						pojedinacnoPlacanje.setSifraValute(request.getOznakaValute());
						pojedinacnoPlacanje.setSvrhaPlacanja(request.getNalog().getSvrhaPlacanja());
						pojedinacnoPlacanje.setMt102(mt102);
						pojedinacnoPlacanjeServis.save(pojedinacnoPlacanje);

					}

				}

			}

		} else if (racunDuznik != null) { // isplata
			String brojRacuna = racunDuznik.getRacun();
			Racun racunD = racunService.findByBrojRacuna(brojRacuna);

			// TODO: if(racunD.getBanka() == ulogovanaBanka)

			if (racunD == null) {
				response.setOdgovor("Racun ne postoji!");
				return response;
			}

			if (racunD.getStatusRacuna() == 1) {
				DnevnoStanjeRacuna poslednjeDnevnoStanje = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunD);

				if (poslednjeDnevnoStanje == null) {
					response.setOdgovor("Na racunu 0...!");
					return response;
				}

				// provera da li ima sredstava
				if ((poslednjeDnevnoStanje.getNovoStanje() - racunD.getRezervisano()) >= request.getNalog().getIznos()
						.doubleValue()) {
					// pravimo analitiku
					int godina = Calendar.getInstance().get(Calendar.YEAR);
					int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
					int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

					AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
					analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
					analitikaIzvoda.setSmer("I");
					analitikaIzvoda.setDuznikNalogodavac(request.getNalog().getDuznik());
					analitikaIzvoda.setSvrhaPlacanja(request.getNalog().getSvrhaPlacanja());
					// analitikaIzvoda.setPrimalacPoverilac(request.getNalog().getPrimalac());
					analitikaIzvoda.setDatumNaloga(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
					analitikaIzvoda.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
					analitikaIzvoda.setRacunDuznika(brojRacuna);
					analitikaIzvoda.setModelZaduzenja(racunDuznik.getModel());
					analitikaIzvoda.setPozivNaBrojZaduzenja(racunDuznik.getPozivNaBroj());
					analitikaIzvoda.setIznos(request.getNalog().getIznos().doubleValue());
					analitikaIzvoda.setValuta(valutaService.findBySifraValute(request.getOznakaValute()));

					// umanjujemo dnevno stanje
					DnevnoStanjeRacuna dnevnoStanjeRacuna = dnevnoStanjeRacunaService.findByRacunAndDatum(racunD,
							danasnjiDatum);
					if (dnevnoStanjeRacuna == null) {
						dnevnoStanjeRacuna = new DnevnoStanjeRacuna();
						dnevnoStanjeRacuna.setDatum(danasnjiDatum);
						dnevnoStanjeRacuna.setRacun(racunD);
						dnevnoStanjeRacuna.setPrometNaTeret(request.getNalog().getIznos().doubleValue());
						dnevnoStanjeRacuna.setPrometUKorist(0);
						dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanje.getNovoStanje());
						dnevnoStanjeRacuna.setNovoStanje(
								dnevnoStanjeRacuna.getPrethodnoStanje() - dnevnoStanjeRacuna.getPrometNaTeret());
					} else {
						dnevnoStanjeRacuna.setPrometNaTeret(
								dnevnoStanjeRacuna.getPrometNaTeret() + request.getNalog().getIznos().doubleValue());
						dnevnoStanjeRacuna.setNovoStanje(dnevnoStanjeRacuna.getPrethodnoStanje()
								+ dnevnoStanjeRacuna.getPrometUKorist() - dnevnoStanjeRacuna.getPrometNaTeret());
					}
					dnevnoStanjeRacunaService.save(dnevnoStanjeRacuna);

					analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacuna);

					analitikaIzvodaService.save(analitikaIzvoda);

				} else {
					response.setOdgovor("Nema se para!");
					return response;
				}
			} else {
				response.setOdgovor("Racun je zatvoren!");
				return response;
			}

		} else if (racunPoverilac != null) { // uplata

			String brojRacuna = racunPoverilac.getRacun();
			Racun racunPov = racunService.findByBrojRacuna(brojRacuna);

			// TODO: if(racunD.getBanka() == ulogovanaBanka)

			if (racunPov.getStatusRacuna() == 1) {
				DnevnoStanjeRacuna poslednjeDnevnoStanje = dnevnoStanjeRacunaService
						.findTopByRacunOrderByDatum(racunPov);

				// pravimo analitiku
				int godina = Calendar.getInstance().get(Calendar.YEAR);
				int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
				int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

				AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
				analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
				analitikaIzvoda.setSmer("U");
				// analitikaIzvoda.setDuznikNalogodavac(request.getNalog().getDuznik());
				analitikaIzvoda.setSvrhaPlacanja(request.getNalog().getSvrhaPlacanja());
				analitikaIzvoda.setPrimalacPoverilac(request.getNalog().getPrimalac());
				analitikaIzvoda.setDatumNaloga(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
				analitikaIzvoda.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
				analitikaIzvoda.setRacunPoverioca(brojRacuna);
				analitikaIzvoda.setModelOdobrenja(racunPoverilac.getModel());
				analitikaIzvoda.setPozivNaBrojOdobrenja(racunPoverilac.getPozivNaBroj());
				analitikaIzvoda.setIznos(request.getNalog().getIznos().doubleValue());
				analitikaIzvoda.setValuta(valutaService.findBySifraValute(request.getOznakaValute()));

				// uvecavamo dnevno stanje
				DnevnoStanjeRacuna dnevnoStanjeRacuna = dnevnoStanjeRacunaService.findByRacunAndDatum(racunPov,
						danasnjiDatum);

				if (dnevnoStanjeRacuna == null) {
					dnevnoStanjeRacuna = new DnevnoStanjeRacuna();
					dnevnoStanjeRacuna.setDatum(danasnjiDatum);
					dnevnoStanjeRacuna.setRacun(racunPov);
					dnevnoStanjeRacuna.setPrometNaTeret(0);
					dnevnoStanjeRacuna.setPrometUKorist(request.getNalog().getIznos().doubleValue());

					if (poslednjeDnevnoStanje != null)
						dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanje.getNovoStanje());
					else
						dnevnoStanjeRacuna.setPrethodnoStanje(0);

					dnevnoStanjeRacuna.setNovoStanje(
							dnevnoStanjeRacuna.getPrethodnoStanje() + dnevnoStanjeRacuna.getPrometUKorist());
				} else {
					dnevnoStanjeRacuna.setPrometUKorist(
							dnevnoStanjeRacuna.getPrometUKorist() + request.getNalog().getIznos().doubleValue());
					dnevnoStanjeRacuna.setNovoStanje(dnevnoStanjeRacuna.getPrethodnoStanje()
							+ dnevnoStanjeRacuna.getPrometUKorist() - dnevnoStanjeRacuna.getPrometNaTeret());
				}
				dnevnoStanjeRacunaService.save(dnevnoStanjeRacuna);

				analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacuna);

				analitikaIzvodaService.save(analitikaIzvoda);

			} else {
				response.setOdgovor("Racun je zatvoren!");
				return response;
			}

		} else {
			response.setOdgovor("Nije unet racun!");
			return response;
		}

		response.setOdgovor("Povratna poruka!");
		return response;
	}

	@SuppressWarnings("deprecation")
	@PayloadRoot(namespace = HTTP + "izvod." + NAMESPACE_URI, localPart = "IzvodRequest")
	@ResponsePayload
	public IzvodResponse izvod(@RequestPayload IzvodRequest request) {
		System.out.println("Usao endpoint - izvod");
		IzvodResponse response = new IzvodResponse();

		Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(request.getZahtev().getDatum().getYear(),
				request.getZahtev().getDatum().getMonth(), request.getZahtev().getDatum().getDay()));
		Racun racunDuz = racunService.findByBrojRacuna(request.getZahtev().getBrojRacuna());

		DnevnoStanjeRacuna dnevnoStanjeRacunaDuznika = null;

		if (racunDuz != null) {
			dnevnoStanjeRacunaDuznika = dnevnoStanjeRacunaService.findByRacunAndDatum(racunDuz, danasnjiDatum);
		}

		if (dnevnoStanjeRacunaDuznika != null) {

			List<AnalitikaIzvoda> analitike = analitikaIzvodaService
					.findByDnevnoStanjeRacuna(dnevnoStanjeRacunaDuznika);

			int brPreseka = presek * request.getZahtev().getRedniBrojPreseka();

			for (int i = brPreseka; i <= brPreseka + 1; i++) {
				AnalitikaIzvoda tempAnalitika = null;
				try {

					tempAnalitika = analitike.get(i);
				} catch (Exception ex) {
				}

				if (tempAnalitika != null) {
					TStavkaPreseka tempStavka = new TStavkaPreseka();
					tempStavka.setSmer(tempAnalitika.getSmer());
					TNalog tempNalog = new TNalog();
					tempNalog.setDuznik(tempAnalitika.getDuznikNalogodavac());
					tempNalog.setSvrhaPlacanja(tempAnalitika.getSvrhaPlacanja());
					tempNalog.setPrimalac(tempAnalitika.getPrimalacPoverilac());

					try {
						tempNalog.setDatumNaloga(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
								tempAnalitika.getDatumNaloga().getYear(), tempAnalitika.getDatumNaloga().getMonth(),
								tempAnalitika.getDatumNaloga().getDay(), 1));
					} catch (DatatypeConfigurationException e) {
						e.printStackTrace();
					}

					try {
						tempNalog.setDatumValute(DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
								tempAnalitika.getDatumValute().getYear(), tempAnalitika.getDatumValute().getMonth(),
								tempAnalitika.getDatumValute().getDay(), 1));
					} catch (DatatypeConfigurationException e) {
						e.printStackTrace();
					}

					TPodaciORacunu tPodaciORacunuDuzn = new TPodaciORacunu();
					tPodaciORacunuDuzn.setRacun(tempAnalitika.getRacunDuznika());
					tPodaciORacunuDuzn.setModel(tempAnalitika.getModelZaduzenja());
					tPodaciORacunuDuzn.setPozivNaBroj(tempAnalitika.getPozivNaBrojZaduzenja());

					tempNalog.setDuznikRacun(tPodaciORacunuDuzn);

					TPodaciORacunu tPodaciORacunuPov = new TPodaciORacunu();
					tPodaciORacunuPov.setRacun(tempAnalitika.getRacunPoverioca());
					tPodaciORacunuPov.setModel(tempAnalitika.getModelOdobrenja());
					tPodaciORacunuPov.setPozivNaBroj(tempAnalitika.getPozivNaBrojOdobrenja());

					tempNalog.setPoverilacRacun(tPodaciORacunuPov);

					tempNalog.setIznos(new BigDecimal(tempAnalitika.getIznos()));

					tempStavka.setNalog(tempNalog);

					response.getStavkaPreseka().add(tempStavka);
				}
			}

			TZaglavljePreseka zaglavlje = new TZaglavljePreseka();

			TZahtev zahtev = new TZahtev();
			zahtev.setBrojRacuna(request.getZahtev().getBrojRacuna());
			zahtev.setDatum(request.getZahtev().getDatum());
			zahtev.setRedniBrojPreseka(request.getZahtev().getRedniBrojPreseka());

			zaglavlje.setZahtev(zahtev);
			zaglavlje.setPrethodnoStanje(new BigDecimal(dnevnoStanjeRacunaDuznika.getPrethodnoStanje()));

			if (analitikaIzvodaService.findBySmer("U") != null)
				zaglavlje.setBrojPromenaUKorist(analitikaIzvodaService.findBySmer("U").size());
			else
				zaglavlje.setBrojPromenaUKorist(0);

			zaglavlje.setUkupnoUKorist(new BigDecimal(dnevnoStanjeRacunaDuznika.getPrometUKorist()));

			if (analitikaIzvodaService.findBySmer("I") != null)
				zaglavlje.setBrojPromenaNaTeret(new BigDecimal(analitikaIzvodaService.findBySmer("I").size()));
			else
				zaglavlje.setBrojPromenaNaTeret(new BigDecimal(0));

			zaglavlje.setUkupnoNaTeret(new BigDecimal(dnevnoStanjeRacunaDuznika.getPrometNaTeret()));
			zaglavlje.setNovoStanje(new BigDecimal(dnevnoStanjeRacunaDuznika.getNovoStanje()));

			response.setZaglavljePreseka(zaglavlje);

		} else {
			return null;
		}
		// response.
		return response;
	}

	@PayloadRoot(namespace = HTTP + "MT102." + NAMESPACE_URI, localPart = "MT102Request")
	@ResponsePayload
	public MT102Response mt102(@RequestPayload MT102Request request) {
		MT102Response response = new MT102Response();
		
		System.out.println("*** Stigao je MT102 u PB poverioca");
		System.out.println(request.toString());
		
		response.setOdgovor("PB: MT102 - odgovor");
		return response;
	}

	@PayloadRoot(namespace = HTTP + "MT103." + NAMESPACE_URI, localPart = "MT103Request")
	@ResponsePayload
	public MT103Response mt103(@RequestPayload MT103Request request) {
		MT103Response response = new MT103Response();
		
		System.out.println("*** Stigao je MT103 u PB poverioca");
		System.out.println(request.toString());
		
		response.setOdgovor("PB: MT103 - odgovor");
		return response;
	}

	@PayloadRoot(namespace = HTTP + "MT900." + NAMESPACE_URI, localPart = "MT900Request")
	@ResponsePayload
	public MT900Response mt900(@RequestPayload MT900Request request) throws DatatypeConfigurationException {
		
		MT900Response response = new MT900Response();
		String odgovor = "";
		MT103 mt103 = mt103Servis.findByIdPoruke(request.getIDPorukeNaloga());
		MT102 mt102 = mt102Servis.findByIdPoruke(request.getIDPorukeNaloga());
		
		if (mt103 != null) {
			if(request.getIDPoruke().contains("MT900"))
				odgovor = uradiObracun900Mt103(konvertujMT103(mt103));
			else
				odgovor = uradiObracun910Mt103(konvertujMT103(mt103));
		} else if (mt102 != null) {
			if(request.getIDPoruke().contains("MT900"))
				odgovor = uradiObracun900Mt102(konvertujMT102(mt102));
			else
				odgovor = uradiObracun910Mt102(konvertujMT102(mt102));
			
			if (odgovor == "Ok") { 
				mt102.setPoslat(true);
				mt102Servis.save(mt102);
			}
		} else {
			odgovor = "MT900 - Nesto ne valja";
		}

		response.setOdgovor(odgovor);
		return response;
	}

	@PayloadRoot(namespace = HTTP + "MT910." + NAMESPACE_URI, localPart = "MT910Request")
	@ResponsePayload
	public MT900Response mt910(@RequestPayload MT900Request request) throws DatatypeConfigurationException {
		
		MT900Response response = new MT900Response();
		String odgovor = "";
		MT103 mt103 = mt103Servis.findByIdPoruke(request.getIDPorukeNaloga());
		MT102 mt102 = mt102Servis.findByIdPoruke(request.getIDPorukeNaloga());

		if (mt103 != null) {
			odgovor = uradiObracun910Mt103(konvertujMT103(mt103));
		} else if (mt102 != null) {
			odgovor = uradiObracun910Mt102(konvertujMT102(mt102));
			if (odgovor == "Ok") {
				mt102.setPoslat(true);
				mt102Servis.save(mt102);
			}
		} else {
			odgovor = "MT910 - Nesto ne valja";
		}

		response.setOdgovor(odgovor);
		return response;
	}

	private MT102Request konvertujMT102(MT102 mt102) {

		MT102Request req = new MT102Request();

		req.setIDPoruke(mt102.getIdPoruke());

		TSWIFTIRacun sw = new TSWIFTIRacun();
		sw.setSWIFT(mt102.getSwiftDuznik());
		sw.setObracunskiRacun(mt102.getObracunskiRacunDuznik());
		req.setBankaDuznika(sw);

		TSWIFTIRacun swPov = new TSWIFTIRacun();
		swPov.setSWIFT(mt102.getSwiftPoverilac());
		swPov.setObracunskiRacun(mt102.getObracunskiRacunPoverilac());
		req.setBankaPoverioca(swPov);

		req.setUkupanIznos(mt102.getUkupanIznos());
		req.setSifraValute(mt102.getSifraValute());

		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		gcal.setTime(mt102.getDatumValute());

		XMLGregorianCalendar xgcal;
		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			req.setDatumValute(xgcal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		gcal.setTime(mt102.getDatum());

		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			req.setDatum(xgcal);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		List<PojedinacnoPlacanje> listaPP = mt102.getPojedinacnoPlacanje();
		if(listaPP == null)
			listaPP = pojedinacnoPlacanjeServis.findByMt102(mt102);
		if(listaPP == null)
			return req;
		listaPP = pojedinacnoPlacanjeServis.findByMt102(mt102);
		for (int i = 0; i < listaPP.size(); i++) {
			TPojedinacnoPlacanje pojed = new TPojedinacnoPlacanje();

			pojed.setIDNalogaZaPlacanje(listaPP.get(i).getIdNalogaZaPlacanje());
			
			pojed.setDuznik(listaPP.get(i).getDuznik());
			pojed.setSvrhaPlacanja(listaPP.get(i).getSvrhaPlacanja());
			pojed.setPrimalac(listaPP.get(i).getPrimalac());
			
			gcal = (GregorianCalendar) GregorianCalendar.getInstance();
			gcal.setTime(listaPP.get(i).getDatumNaloga());

			try {
				xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
				pojed.setDatumNaloga(xgcal);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}

			TPodaciORacunu rac = new TPodaciORacunu();
			rac.setPozivNaBroj(listaPP.get(i).getPozivNaBrojDuznik());
			rac.setModel(listaPP.get(i).getModelDuznik());
			rac.setRacun(listaPP.get(i).getRacunDruznik());
			pojed.setDuznikRacun(rac);

			TPodaciORacunu rac1 = new TPodaciORacunu();
			rac1.setPozivNaBroj(listaPP.get(i).getPozivNaBrojPoverilac());
			rac1.setModel(listaPP.get(i).getModelPoverilac());
			rac1.setRacun(listaPP.get(i).getRacunPoverilac());
			pojed.setPoverilacRacun(rac1);
			
			pojed.setIznos(listaPP.get(i).getIznos());
			pojed.setSifraValute(listaPP.get(i).getSifraValute());

			req.getPojedinacnoPlacanje().add(pojed);
		}

		return req;
	}

	
	private MT103 konvertujMT103Request(MT103Request mt103req) {

		MT103 mt103 = new MT103();

		mt103.setIdPoruke(mt103req.getIDPoruke());
		mt103.setSwiftDuznik(mt103req.getBankaDuznika().getSWIFT());
		mt103.setObracunskiRacunDuznik(mt103req.getBankaDuznika().getObracunskiRacun());
		mt103.setSwiftPoverilac(mt103req.getBankaPoverioca().getSWIFT());
		mt103.setObracunskiRacunPoverilac(mt103req.getBankaPoverioca().getObracunskiRacun());
		mt103.setDuznik(mt103req.getNalog().getDuznik());
		mt103.setSvrhaPlacanja(mt103req.getNalog().getSvrhaPlacanja());
		mt103.setPrimalac(mt103req.getNalog().getPrimalac());
		mt103.setDatumNaloga(mt103req.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
		mt103.setDatumValute(mt103req.getNalog().getDatumValute().toGregorianCalendar().getTime());
		mt103.setRacunDuznik(mt103req.getNalog().getDuznikRacun().getRacun());
		mt103.setModelDuznik(mt103req.getNalog().getDuznikRacun().getModel());
		mt103.setPozivNaBrojDuznik(mt103req.getNalog().getDuznikRacun().getPozivNaBroj());
		mt103.setRacunPoverilac(mt103req.getNalog().getPoverilacRacun().getRacun());
		mt103.setModelPoverilac(mt103req.getNalog().getPoverilacRacun().getModel());
		mt103.setPozivNaBrojPoverilac(mt103req.getNalog().getPoverilacRacun().getPozivNaBroj());
		mt103.setIznos(mt103req.getNalog().getIznos());
		mt103.setSifraValute(mt103req.getSifraValute());

		return mt103;
	}

	
	private MT103Request konvertujMT103(MT103 mt103) throws DatatypeConfigurationException {

		MT103Request req = new MT103Request();

		req.setIDPoruke(mt103.getIdPoruke());
		
		TSWIFTIRacun sw = new TSWIFTIRacun();
		sw.setSWIFT(mt103.getSwiftDuznik());
		sw.setObracunskiRacun(mt103.getObracunskiRacunDuznik());
		req.setBankaDuznika(sw);

		TSWIFTIRacun swPov = new TSWIFTIRacun();
		swPov.setSWIFT(mt103.getSwiftPoverilac());
		swPov.setObracunskiRacun(mt103.getObracunskiRacunPoverilac());
		req.setBankaPoverioca(swPov);
		
		TNalog nalog = new TNalog();

		nalog.setDuznik(mt103.getDuznik());
		nalog.setSvrhaPlacanja(mt103.getSvrhaPlacanja());
		nalog.setPrimalac(mt103.getPrimalac());

		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		gcal.setTime(mt103.getDatumValute());
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		nalog.setDatumValute(xgcal);

		gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		gcal.setTime(mt103.getDatumNaloga());
		xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		nalog.setDatumNaloga(xgcal);
		
		TPodaciORacunu duznikRacun = new TPodaciORacunu();
		duznikRacun.setModel(mt103.getModelDuznik());
		duznikRacun.setPozivNaBroj(mt103.getPozivNaBrojDuznik());
		duznikRacun.setRacun(mt103.getRacunDuznik());

		TPodaciORacunu poverilacRacun = new TPodaciORacunu();
		poverilacRacun.setModel(mt103.getModelPoverilac());
		poverilacRacun.setPozivNaBroj(mt103.getPozivNaBrojPoverilac());
		poverilacRacun.setRacun(mt103.getRacunPoverilac());

		nalog.setDuznikRacun(duznikRacun);
		nalog.setPoverilacRacun(poverilacRacun);
		
		nalog.setIznos(mt103.getIznos());
		
		req.setNalog(nalog);

		req.setSifraValute(mt103.getSifraValute());

		return req;
	}

	
	private MT102 konvertujMt102Request(MT102Request request) {
		MT102 mt102 = new MT102();
		mt102.setDatum(request.getDatum().toGregorianCalendar().getTime());
		mt102.setDatumValute(request.getDatumValute().toGregorianCalendar().getTime());
		mt102.setIdPoruke(request.getIDPoruke());
		mt102.setObracunskiRacunDuznik(request.getBankaDuznika().getObracunskiRacun());
		mt102.setObracunskiRacunPoverilac(request.getBankaPoverioca().getObracunskiRacun());
		mt102.setPoslat(false);

		List<PojedinacnoPlacanje> pojedinacnoPlacanje = new ArrayList<PojedinacnoPlacanje>();

		for (TPojedinacnoPlacanje pojPlacanje : request.getPojedinacnoPlacanje()) {
			PojedinacnoPlacanje pp = new PojedinacnoPlacanje();
			pp.setDatumNaloga(pojPlacanje.getDatumNaloga().toGregorianCalendar().getTime());
			pp.setDuznik(pojPlacanje.getDuznik());
			pp.setIdNalogaZaPlacanje(pojPlacanje.getIDNalogaZaPlacanje());
			pp.setIznos(pojPlacanje.getIznos());
			pp.setModelDuznik(pojPlacanje.getDuznikRacun().getModel());
			pp.setModelPoverilac(pojPlacanje.getPoverilacRacun().getModel());
			pp.setPozivNaBrojDuznik(pojPlacanje.getDuznikRacun().getPozivNaBroj());
			pp.setPozivNaBrojPoverilac(pojPlacanje.getPoverilacRacun().getPozivNaBroj());
			pp.setPrimalac(pojPlacanje.getPrimalac());
			pp.setRacunDruznik(pojPlacanje.getDuznikRacun().getRacun());
			pp.setRacunPoverilac(pojPlacanje.getPoverilacRacun().getRacun());
			pp.setSifraValute(pojPlacanje.getSifraValute());
			pp.setSvrhaPlacanja(pojPlacanje.getSvrhaPlacanja());

			pojedinacnoPlacanjeServis.save(pp);
			pojedinacnoPlacanje.add(pp);
		}

		mt102.setPojedinacnoPlacanje(pojedinacnoPlacanje);
		mt102.setSifraValute(request.getSifraValute());
		mt102.setSwiftDuznik(request.getBankaDuznika().getSWIFT());
		mt102.setSwiftPoverilac(request.getBankaPoverioca().getSWIFT());
		mt102.setUkupanIznos(request.getUkupanIznos());
		return mt102;
	}

	
	private String uradiObracun910Mt102(MT102Request request) {
		MedjubankarskiPrenos kliring = new MedjubankarskiPrenos();
		kliring.setVrstaPoruke("MT102");
		kliring.setBankaPosiljalac(bankaService.findBySwiftKod(request.getBankaDuznika().getSWIFT()));
		kliring.setBankaPrimalac(bankaService.findBySwiftKod(request.getBankaPoverioca().getSWIFT()));
		kliring.setDatum(request.getDatum().toGregorianCalendar().getTime());
		kliring.setDatumValute(request.getDatumValute().toGregorianCalendar().getTime());
		kliring.setIznos(request.getUkupanIznos().doubleValue());
		kliring.setObracunskiPeriodBankeDuznika(request.getBankaDuznika().getObracunskiRacun());
		kliring.setObracunskiPeriodBankePoverioca(request.getBankaPoverioca().getObracunskiRacun());
		kliring.setSwiftKodBankeDuznika(request.getBankaDuznika().getSWIFT());
		kliring.setSwiftKodBankePoverioca(request.getBankaPoverioca().getSWIFT());
		kliring.setValuta(valutaService.findBySifraValute(request.getSifraValute()));
		medjubankarskiPrenosService.save(kliring);

		for (TPojedinacnoPlacanje pojedinacno : request.getPojedinacnoPlacanje()) {
			TPodaciORacunu racunDuznik = pojedinacno.getDuznikRacun();
			TPodaciORacunu racunPoverilac = pojedinacno.getPoverilacRacun();

			String brojRacunaDuznik = racunDuznik.getRacun();
			String brojRacunaPoverilac = racunPoverilac.getRacun();
			Racun racunPov = racunService.findByBrojRacuna(brojRacunaPoverilac);

			// pravimo analitiku za racun u nasoj
			int godina = Calendar.getInstance().get(Calendar.YEAR);
			int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

			AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
			analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
			analitikaIzvoda.setSmer("U");
			analitikaIzvoda.setDuznikNalogodavac(pojedinacno.getDuznik());
			analitikaIzvoda.setSvrhaPlacanja(pojedinacno.getSvrhaPlacanja());
			analitikaIzvoda.setPrimalacPoverilac(pojedinacno.getPrimalac());
			analitikaIzvoda.setDatumNaloga(pojedinacno.getDatumNaloga().toGregorianCalendar().getTime());
			analitikaIzvoda.setDatumValute(request.getDatumValute().toGregorianCalendar().getTime());
			analitikaIzvoda.setRacunDuznika(brojRacunaDuznik);
			analitikaIzvoda.setModelZaduzenja(racunDuznik.getModel());
			analitikaIzvoda.setPozivNaBrojZaduzenja(racunDuznik.getPozivNaBroj());
			analitikaIzvoda.setRacunPoverioca(brojRacunaPoverilac);
			analitikaIzvoda.setModelOdobrenja(racunPoverilac.getModel());
			analitikaIzvoda.setPozivNaBrojOdobrenja(racunPoverilac.getPozivNaBroj());
			analitikaIzvoda.setIznos(pojedinacno.getIznos().doubleValue());
			analitikaIzvoda.setValuta(valutaService.findBySifraValute(request.getSifraValute()));

			// menjamo dnevno stanje poverioca
			DnevnoStanjeRacuna dnevnoStanjeRacunaPoverioca = dnevnoStanjeRacunaService.findByRacunAndDatum(racunPov,danasnjiDatum);
			DnevnoStanjeRacuna poslednjeDnevnoStanjePoverilac = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunPov);
			if (dnevnoStanjeRacunaPoverioca == null) {
				dnevnoStanjeRacunaPoverioca = new DnevnoStanjeRacuna();
				dnevnoStanjeRacunaPoverioca.setDatum(danasnjiDatum);
				dnevnoStanjeRacunaPoverioca.setRacun(racunPov);
				dnevnoStanjeRacunaPoverioca.setPrometNaTeret(0);
				dnevnoStanjeRacunaPoverioca.setPrometUKorist(pojedinacno.getIznos().doubleValue());
				if (poslednjeDnevnoStanjePoverilac != null)
					dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
				else
					dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(0);

				dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist());
			} else {
				dnevnoStanjeRacunaPoverioca.setPrometUKorist(dnevnoStanjeRacunaPoverioca.getPrometUKorist() + pojedinacno.getIznos().doubleValue());
				dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
			}

			dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaPoverioca);

			analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaPoverioca);
			analitikaIzvodaService.save(analitikaIzvoda);

			StavkaPrenosa stavka = new StavkaPrenosa();
			stavka.setAnalitikaIzvoda(analitikaIzvoda);
			stavka.setMedjubankarskiPrenos(kliring);
			stavkaServis.save(stavka);

		}

		return "Ok";
	}

	
	private String uradiObracun910Mt103(MT103Request request) {
		MedjubankarskiPrenos rtgs = new MedjubankarskiPrenos();
		rtgs.setVrstaPoruke("MT103");
		rtgs.setBankaPosiljalac(bankaService.findBySwiftKod(request.getBankaDuznika().getSWIFT()));
		rtgs.setBankaPrimalac(bankaService.findBySwiftKod(request.getBankaPoverioca().getSWIFT()));
		rtgs.setDatum(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
		rtgs.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
		rtgs.setIznos(request.getNalog().getIznos().doubleValue());
		rtgs.setObracunskiPeriodBankeDuznika(request.getBankaDuznika().getObracunskiRacun());
		rtgs.setObracunskiPeriodBankePoverioca(request.getBankaPoverioca().getObracunskiRacun());
		rtgs.setSwiftKodBankeDuznika(request.getBankaDuznika().getSWIFT());
		rtgs.setSwiftKodBankePoverioca(request.getBankaPoverioca().getSWIFT());
		rtgs.setValuta(valutaService.findBySifraValute(request.getSifraValute()));
		medjubankarskiPrenosService.save(rtgs);
		
		TPodaciORacunu racunDuznik = request.getNalog().getDuznikRacun();
		TPodaciORacunu racunPoverilac = request.getNalog().getPoverilacRacun();

		String brojRacunaDuznik = racunDuznik.getRacun();
		String brojRacunaPoverilac = racunPoverilac.getRacun();
		Racun racunPov = racunService.findByBrojRacuna(brojRacunaPoverilac);

		// pravimo analitiku za racun u nasoj
		int godina = Calendar.getInstance().get(Calendar.YEAR);
		int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

		AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
		analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
		analitikaIzvoda.setSmer("U");
		analitikaIzvoda.setDuznikNalogodavac(request.getNalog().getDuznik());
		analitikaIzvoda.setSvrhaPlacanja(request.getNalog().getSvrhaPlacanja());
		analitikaIzvoda.setPrimalacPoverilac(request.getNalog().getPrimalac());
		analitikaIzvoda.setDatumNaloga(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
		analitikaIzvoda.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
		analitikaIzvoda.setRacunDuznika(brojRacunaDuznik);
		analitikaIzvoda.setModelZaduzenja(racunDuznik.getModel());
		analitikaIzvoda.setPozivNaBrojZaduzenja(racunDuznik.getPozivNaBroj());
		analitikaIzvoda.setRacunPoverioca(brojRacunaPoverilac);
		analitikaIzvoda.setModelOdobrenja(racunPoverilac.getModel());
		analitikaIzvoda.setPozivNaBrojOdobrenja(racunPoverilac.getPozivNaBroj());
		analitikaIzvoda.setIznos(request.getNalog().getIznos().doubleValue());
		analitikaIzvoda.setValuta(valutaService.findBySifraValute(request.getSifraValute()));

		// menjamo dnevno stanje poverioca
		DnevnoStanjeRacuna dnevnoStanjeRacunaPoverioca = dnevnoStanjeRacunaService.findByRacunAndDatum(racunPov,
				danasnjiDatum);
		DnevnoStanjeRacuna poslednjeDnevnoStanjePoverilac = dnevnoStanjeRacunaService
				.findTopByRacunOrderByDatum(racunPov);
		if (dnevnoStanjeRacunaPoverioca == null) {
			dnevnoStanjeRacunaPoverioca = new DnevnoStanjeRacuna();
			dnevnoStanjeRacunaPoverioca.setDatum(danasnjiDatum);
			dnevnoStanjeRacunaPoverioca.setRacun(racunPov);
			dnevnoStanjeRacunaPoverioca.setPrometNaTeret(0);
			dnevnoStanjeRacunaPoverioca.setPrometUKorist(request.getNalog().getIznos().doubleValue());
			if (poslednjeDnevnoStanjePoverilac != null)
				dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
			else
				dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(0);

			dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist());
		} else {
			dnevnoStanjeRacunaPoverioca.setPrometUKorist(dnevnoStanjeRacunaPoverioca.getPrometUKorist() + request.getNalog().getIznos().doubleValue());
			// dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
			dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
		}

		dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaPoverioca);

		analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaPoverioca);
		analitikaIzvodaService.save(analitikaIzvoda);
		
		StavkaPrenosa stavka = new StavkaPrenosa();
		stavka.setAnalitikaIzvoda(analitikaIzvoda);
		stavka.setMedjubankarskiPrenos(rtgs);
		stavkaServis.save(stavka);

		return "Ok";
	}

	
	private String uradiObracun900Mt102(MT102Request request) {
		MedjubankarskiPrenos kliring = new MedjubankarskiPrenos();
		kliring.setVrstaPoruke("MT102");
		kliring.setBankaPosiljalac(bankaService.findBySwiftKod(request.getBankaDuznika().getSWIFT()));
		kliring.setBankaPrimalac(bankaService.findBySwiftKod(request.getBankaPoverioca().getSWIFT()));
		kliring.setDatum(request.getDatum().toGregorianCalendar().getTime());
		kliring.setDatumValute(request.getDatumValute().toGregorianCalendar().getTime());
		kliring.setIznos(request.getUkupanIznos().doubleValue());
		kliring.setObracunskiPeriodBankeDuznika(request.getBankaDuznika().getObracunskiRacun());
		kliring.setObracunskiPeriodBankePoverioca(request.getBankaPoverioca().getObracunskiRacun());
		kliring.setSwiftKodBankeDuznika(request.getBankaDuznika().getSWIFT());
		kliring.setSwiftKodBankePoverioca(request.getBankaPoverioca().getSWIFT());
		kliring.setValuta(valutaService.findBySifraValute(request.getSifraValute()));
		medjubankarskiPrenosService.save(kliring);

		for (TPojedinacnoPlacanje pojedinacno : request.getPojedinacnoPlacanje()) {
			TPodaciORacunu racunDuznik = pojedinacno.getDuznikRacun();
			TPodaciORacunu racunPoverilac = pojedinacno.getPoverilacRacun();

			String brojRacunaDuznik = racunDuznik.getRacun();
			Racun racunDuz = racunService.findByBrojRacuna(brojRacunaDuznik);
			String brojRacunaPoverilac = racunPoverilac.getRacun();

			// pravimo analitiku za racun u nasoj
			int godina = Calendar.getInstance().get(Calendar.YEAR);
			int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

			AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
			analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
			analitikaIzvoda.setSmer("I");
			analitikaIzvoda.setDuznikNalogodavac(pojedinacno.getDuznik());
			analitikaIzvoda.setSvrhaPlacanja(pojedinacno.getSvrhaPlacanja());
			analitikaIzvoda.setPrimalacPoverilac(pojedinacno.getPrimalac());
			analitikaIzvoda.setDatumNaloga(pojedinacno.getDatumNaloga().toGregorianCalendar().getTime());
			analitikaIzvoda.setDatumValute(request.getDatumValute().toGregorianCalendar().getTime());
			analitikaIzvoda.setRacunDuznika(brojRacunaDuznik);
			analitikaIzvoda.setModelZaduzenja(racunDuznik.getModel());
			analitikaIzvoda.setPozivNaBrojZaduzenja(racunDuznik.getPozivNaBroj());
			analitikaIzvoda.setRacunPoverioca(brojRacunaPoverilac);
			analitikaIzvoda.setModelOdobrenja(racunPoverilac.getModel());
			analitikaIzvoda.setPozivNaBrojOdobrenja(racunPoverilac.getPozivNaBroj());
			analitikaIzvoda.setIznos(pojedinacno.getIznos().doubleValue());
			analitikaIzvoda.setValuta(valutaService.findBySifraValute(pojedinacno.getSifraValute()));

			// menjamo dnevno stanje
			DnevnoStanjeRacuna poslednjeDnevnoStanje = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunDuz);
			DnevnoStanjeRacuna dnevnoStanjeRacunaDuznika = dnevnoStanjeRacunaService.findByRacunAndDatum(racunDuz, danasnjiDatum);
			if (dnevnoStanjeRacunaDuznika == null) {
				dnevnoStanjeRacunaDuznika = new DnevnoStanjeRacuna();
				dnevnoStanjeRacunaDuznika.setDatum(danasnjiDatum);
				dnevnoStanjeRacunaDuznika.setRacun(racunDuz);
				dnevnoStanjeRacunaDuznika.setPrometNaTeret(pojedinacno.getIznos().doubleValue());
				dnevnoStanjeRacunaDuznika.setPrometUKorist(0);
				dnevnoStanjeRacunaDuznika.setPrethodnoStanje(poslednjeDnevnoStanje.getNovoStanje());
				dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
			} else {
				dnevnoStanjeRacunaDuznika.setPrometNaTeret(dnevnoStanjeRacunaDuznika.getPrometNaTeret() + pojedinacno.getIznos().doubleValue());
				dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje() + dnevnoStanjeRacunaDuznika.getPrometUKorist() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
			}

			dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaDuznika);

			analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaDuznika);
			analitikaIzvodaService.save(analitikaIzvoda);

			racunDuz.setRezervisano(racunDuz.getRezervisano() - pojedinacno.getIznos().doubleValue());
			racunService.save(racunDuz);

			StavkaPrenosa stavka = new StavkaPrenosa();
			stavka.setAnalitikaIzvoda(analitikaIzvoda);
			stavka.setMedjubankarskiPrenos(kliring);
			stavkaServis.save(stavka);

		}

		return "Ok";
	}

	
	private String uradiObracun900Mt103(MT103Request request) {
		MedjubankarskiPrenos rtgs = new MedjubankarskiPrenos();
		rtgs.setVrstaPoruke("MT103");
		rtgs.setBankaPosiljalac(bankaService.findBySwiftKod(request.getBankaDuznika().getSWIFT()));
		rtgs.setBankaPrimalac(bankaService.findBySwiftKod(request.getBankaPoverioca().getSWIFT()));
		rtgs.setDatum(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
		rtgs.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
		rtgs.setIznos(request.getNalog().getIznos().doubleValue());
		rtgs.setObracunskiPeriodBankeDuznika(request.getBankaDuznika().getObracunskiRacun());
		rtgs.setObracunskiPeriodBankePoverioca(request.getBankaPoverioca().getObracunskiRacun());
		rtgs.setSwiftKodBankeDuznika(request.getBankaDuznika().getSWIFT());
		rtgs.setSwiftKodBankePoverioca(request.getBankaPoverioca().getSWIFT());
		rtgs.setValuta(valutaService.findBySifraValute(request.getSifraValute()));
		medjubankarskiPrenosService.save(rtgs);
		
		TPodaciORacunu racunDuznik = request.getNalog().getDuznikRacun();
		TPodaciORacunu racunPoverilac = request.getNalog().getPoverilacRacun();

		String brojRacunaDuznik = racunDuznik.getRacun();
		Racun racunDuz = racunService.findByBrojRacuna(brojRacunaDuznik);
		String brojRacunaPoverilac = racunPoverilac.getRacun();

		// pravimo analitiku za racun u nasoj
		int godina = Calendar.getInstance().get(Calendar.YEAR);
		int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

		AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
		analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
		analitikaIzvoda.setSmer("I");
		analitikaIzvoda.setDuznikNalogodavac(request.getNalog().getDuznik());
		analitikaIzvoda.setSvrhaPlacanja(request.getNalog().getSvrhaPlacanja());
		analitikaIzvoda.setPrimalacPoverilac(request.getNalog().getPrimalac());
		analitikaIzvoda.setDatumNaloga(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
		analitikaIzvoda.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
		analitikaIzvoda.setRacunDuznika(brojRacunaDuznik);
		analitikaIzvoda.setModelZaduzenja(racunDuznik.getModel());
		analitikaIzvoda.setPozivNaBrojZaduzenja(racunDuznik.getPozivNaBroj());
		analitikaIzvoda.setRacunPoverioca(brojRacunaPoverilac);
		analitikaIzvoda.setModelOdobrenja(racunPoverilac.getModel());
		analitikaIzvoda.setPozivNaBrojOdobrenja(racunPoverilac.getPozivNaBroj());
		analitikaIzvoda.setIznos(request.getNalog().getIznos().doubleValue());
		analitikaIzvoda.setValuta(valutaService.findBySifraValute(request.getSifraValute()));

		// menjamo dnevno stanje za racun u nasoj
		DnevnoStanjeRacuna poslednjeDnevnoStanje = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunDuz);
		DnevnoStanjeRacuna dnevnoStanjeRacunaDuznika = dnevnoStanjeRacunaService.findByRacunAndDatum(racunDuz, danasnjiDatum);
		if (dnevnoStanjeRacunaDuznika == null) {
			dnevnoStanjeRacunaDuznika = new DnevnoStanjeRacuna();
			dnevnoStanjeRacunaDuznika.setDatum(danasnjiDatum);
			dnevnoStanjeRacunaDuznika.setRacun(racunDuz);
			dnevnoStanjeRacunaDuznika.setPrometNaTeret(request.getNalog().getIznos().doubleValue());
			dnevnoStanjeRacunaDuznika.setPrometUKorist(0);
			dnevnoStanjeRacunaDuznika.setPrethodnoStanje(poslednjeDnevnoStanje.getNovoStanje());
			dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
		} else {
			dnevnoStanjeRacunaDuznika.setPrometNaTeret(dnevnoStanjeRacunaDuznika.getPrometNaTeret() + request.getNalog().getIznos().doubleValue());
			dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje()+ dnevnoStanjeRacunaDuznika.getPrometUKorist() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
		}

		dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaDuznika);

		analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaDuznika);
		analitikaIzvodaService.save(analitikaIzvoda);

		racunDuz.setRezervisano(racunDuz.getRezervisano() - request.getNalog().getIznos().doubleValue());
		racunService.save(racunDuz);
		
		StavkaPrenosa stavka = new StavkaPrenosa();
		stavka.setAnalitikaIzvoda(analitikaIzvoda);
		stavka.setMedjubankarskiPrenos(rtgs);
		stavkaServis.save(stavka);

		return "Ok";
	}

	
	public NalogZaPrenosRequest checkSignAndEnc(NalogZaPrenosRequest request) {
		KeyStoreReader ksReader = new KeyStoreReader();
		XMLEncryptionUtility encUtility = new XMLEncryptionUtility();
		XMLSigningUtility sigUtility = new XMLSigningUtility();

		File file = new File("trala.xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(NalogZaPrenosRequest.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(request, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		Document document = DocumentLoader.loadDocument(file);
		// boolean res12 = sigUtility.verifySignature(document);

		Document doc = DocumentLoader.loadDocument("univerzitet_signed.xml");
		// boolean res = sigUtility.verifySignature(doc);

		if (true) {
			PrivateKey privateKey = ksReader.readPrivateKey("./primer.jks", "primer", "primer", "primer");
			document = encUtility.decrypt(document, privateKey);
			TransformerFactory factory1 = TransformerFactory.newInstance();
			try {
				Transformer transformer = factory1.newTransformer();
				DOMSource source = new DOMSource(document);
				File outFile = new File("testKraj.xml");
				FileOutputStream f = new FileOutputStream(outFile);
				StreamResult result = new StreamResult(f);
				transformer.transform(source, result);
				f.close();
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}

		NalogZaPrenosRequest nalogZaPrenosRequest = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(NalogZaPrenosRequest.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			nalogZaPrenosRequest = (NalogZaPrenosRequest) jaxbUnmarshaller.unmarshal(new File("testKraj.xml"));
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		if (nalogZaPrenosRequest != null) {
			logger.info("[Nalog za prenos: " + nalogZaPrenosRequest.toString());
			System.out.println("[Nalog za prenos: " + nalogZaPrenosRequest.toString());
		}

		return nalogZaPrenosRequest;
	}

}
