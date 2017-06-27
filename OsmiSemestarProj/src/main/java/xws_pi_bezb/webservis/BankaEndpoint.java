package xws_pi_bezb.webservis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.AnalitikaIzvoda;
import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.MT102;
import xws_pi_bezb.models.MT103;
import xws_pi_bezb.models.MedjubankarskiPrenos;
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
	public BankaEndpoint() {

	}

	@PayloadRoot(namespace = HTTP + "nalogzaprenos." + NAMESPACE_URI, localPart = "NalogZaPrenosRequest")
	@ResponsePayload
	public NalogZaPrenosResponse nalogZaPrenos(@RequestPayload NalogZaPrenosRequest request1) {
		// System.out.println(request.toString());
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		NalogZaPrenosRequest request = checkSignAndEnc(request1);
		if (request == null) {
			response.setOdgovor("Potpis ili enkripcija nije u redu!");
			return response;
		}

		TPodaciORacunu racunDuznik = request.getNalog().getDuznikRacun();
		TPodaciORacunu racunPoverilac = request.getNalog().getPoverilacRacun();

		if (racunDuznik != null && racunPoverilac != null) {

			String brojRacunaDuznik = racunDuznik.getRacun();
			Racun racunDuz = racunService.findByBrojRacuna(brojRacunaDuznik);
			String brojRacunaPoverilac = racunPoverilac.getRacun();
			Racun racunPov = racunService.findByBrojRacuna(brojRacunaPoverilac);
			
			if(racunDuz == null){
				response.setOdgovor("Racun duznika ne postoji!");
				return response;
			}
			
			if(racunPov == null){
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
			if ((poslednjeDnevnoStanje.getNovoStanje() - racunDuz.getRezervisano()) >= request.getNalog().getIznos().doubleValue()) {
			
				// provera da li su racuni iz iste banke
				if(racunDuz.getBanka().getId().equals(racunPov.getBanka().getId())){
					
					// pravimo analitiku za oba
					int godina = Calendar.getInstance().get(Calendar.YEAR);
					int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
					int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));
					
					AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
					analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
					analitikaIzvoda.setSmer("P");
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
						// dnevnoStanjeRacuna.setPrethodnoStanje(dnevnoStanjeRacuna.getNovoStanje());
						dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje() + dnevnoStanjeRacunaDuznika.getPrometUKorist() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
					}
					
					DnevnoStanjeRacuna dnevnoStanjeRacunaPoverioca = dnevnoStanjeRacunaService.findByRacunAndDatum(racunPov, danasnjiDatum);
					DnevnoStanjeRacuna poslednjeDnevnoStanjePoverilac = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunPov);
					if (dnevnoStanjeRacunaPoverioca == null) {
						dnevnoStanjeRacunaPoverioca = new DnevnoStanjeRacuna();
						dnevnoStanjeRacunaPoverioca.setDatum(danasnjiDatum);
						dnevnoStanjeRacunaPoverioca.setRacun(racunPov);
						dnevnoStanjeRacunaPoverioca.setPrometNaTeret(0);
						dnevnoStanjeRacunaPoverioca.setPrometUKorist(request.getNalog().getIznos().doubleValue());
						if(poslednjeDnevnoStanjePoverilac != null)
							dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
						else
							dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(0);
						
						dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
					} else {
						dnevnoStanjeRacunaPoverioca.setPrometUKorist(dnevnoStanjeRacunaPoverioca.getPrometUKorist() + request.getNalog().getIznos().doubleValue());
						// dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
						dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
					}
					
					dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaDuznika);
					dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaPoverioca);
					
					analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaDuznika);
					analitikaIzvodaService.save(analitikaIzvoda);
					analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaPoverioca);
					analitikaIzvodaService.save(analitikaIzvoda);

				}else{	
					
					// OVO RADIMO KADA STIGNE ODGOVOR CB
					/* 
					// pravimo analitiku za racun u nasoj
					int godina = Calendar.getInstance().get(Calendar.YEAR);
					int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
					int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));
					
					AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
					analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
					analitikaIzvoda.setSmer("P");
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

					// menjamo dnevno stanje za racun u nasoj
					
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
						// dnevnoStanjeRacuna.setPrethodnoStanje(dnevnoStanjeRacuna.getNovoStanje());
						dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje() + dnevnoStanjeRacunaDuznika.getPrometUKorist() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
					}
					
					dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaDuznika);
					
					analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaDuznika);
					analitikaIzvodaService.save(analitikaIzvoda);
					*/

					// provera da li je preko 250000 ili hitno
					
					if(request.getNalog().getIznos().doubleValue() >= 250000 || request.isHitno()){
						// rezervisemo sredstva
						racunDuz.setRezervisano(request.getNalog().getIznos().doubleValue());
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
						
						mt103Request.setIDPoruke("MT103");
						mt103Request.setBankaDuznika(swiftDuznik);
						mt103Request.setBankaPoverioca(swiftPoverilac);
						mt103Request.setNalog(nalog);
						mt103Request.setSifraValute(request.getOznakaValute());
						
						//TODO: proveriti da li treba rezervacija sredstava
						
						BankaKlijentSamoTest bankaKlijent = new BankaKlijentSamoTest();
						bankaKlijent.posaljiMT103(mt103Request);
					}else{
						// mt102 - clearing...
						
						
						
					}

				}
			
			}

		} else if (racunDuznik != null) { // isplata
			String brojRacuna = racunDuznik.getRacun();
			Racun racunD = racunService.findByBrojRacuna(brojRacuna);

			// TODO: if(racunD.getBanka() == ulogovanaBanka)
			
			if(racunD == null){
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
				if (poslednjeDnevnoStanje.getNovoStanje() >= request.getNalog().getIznos().doubleValue()) {
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
					//analitikaIzvoda.setPrimalacPoverilac(request.getNalog().getPrimalac());
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
						// dnevnoStanjeRacuna.setPrethodnoStanje(dnevnoStanjeRacuna.getNovoStanje());
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
				DnevnoStanjeRacuna poslednjeDnevnoStanje = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunPov);

				// pravimo analitiku
				int godina = Calendar.getInstance().get(Calendar.YEAR);
				int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
				int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

				AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
				analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
				analitikaIzvoda.setSmer("U");
				//analitikaIzvoda.setDuznikNalogodavac(request.getNalog().getDuznik());
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
				DnevnoStanjeRacuna dnevnoStanjeRacuna = dnevnoStanjeRacunaService.findByRacunAndDatum(racunPov, danasnjiDatum);
				if (dnevnoStanjeRacuna == null) {
					dnevnoStanjeRacuna = new DnevnoStanjeRacuna();
					dnevnoStanjeRacuna.setDatum(danasnjiDatum);
					dnevnoStanjeRacuna.setRacun(racunPov);
					dnevnoStanjeRacuna.setPrometNaTeret(0);
					dnevnoStanjeRacuna.setPrometUKorist(request.getNalog().getIznos().doubleValue());
					if(poslednjeDnevnoStanje != null)
						dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanje.getNovoStanje());
					else
						dnevnoStanjeRacuna.setPrethodnoStanje(0);
					dnevnoStanjeRacuna.setNovoStanje(dnevnoStanjeRacuna.getPrethodnoStanje() + dnevnoStanjeRacuna.getPrometUKorist());
				} else {
					dnevnoStanjeRacuna.setPrometUKorist(dnevnoStanjeRacuna.getPrometNaTeret() + request.getNalog().getIznos().doubleValue());
					dnevnoStanjeRacuna.setNovoStanje(dnevnoStanjeRacuna.getPrethodnoStanje() + dnevnoStanjeRacuna.getPrometUKorist() - dnevnoStanjeRacuna.getPrometNaTeret());
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

	@PayloadRoot(namespace = HTTP + "izvod." + NAMESPACE_URI, localPart = "IzvodRequest")
	@ResponsePayload
	public IzvodResponse izvod(@RequestPayload IzvodRequest request) {
		System.out.println("Usao endpoint - izvod");
		IzvodResponse response = new IzvodResponse();

		Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(request.getZahtev().getDatum().getYear(), request.getZahtev().getDatum().getMonth(), request.getZahtev().getDatum().getDay()));
		Racun racunDuz = racunService.findByBrojRacuna(request.getZahtev().getBrojRacuna());
		DnevnoStanjeRacuna dnevnoStanjeRacunaDuznika = dnevnoStanjeRacunaService.findByRacunAndDatum(racunDuz, danasnjiDatum);
		
		if(dnevnoStanjeRacunaDuznika != null){
			
		}else{
			System.out.println("Nema dnevnog stanja.");
		}
		// response.
		return response;
	}

	@PayloadRoot(namespace = HTTP + "MT102." + NAMESPACE_URI, localPart = "MT102Request")
	@ResponsePayload
	public MT102Response mt102(@RequestPayload MT102Request request) {
		MT102Response response = new MT102Response();
		response.setOdgovor("PB: MT102 - odgovor");
		return response;
	}

	@PayloadRoot(namespace = HTTP + "MT103." + NAMESPACE_URI, localPart = "MT103Request")
	@ResponsePayload
	public MT103Response mt103(@RequestPayload MT103Request request) {
		MT103Response response = new MT103Response();
		response.setOdgovor("PB: MT103 - odgovor");
		return response;
	}

	@PayloadRoot(namespace = HTTP + "MT900." + NAMESPACE_URI, localPart = "MT900Request")
	@ResponsePayload
	public MT900Response mt900(@RequestPayload MT900Request request) {
		MT900Response response = new MT900Response();
		String odgovor = "";
		MT103Request mt103 = null;//mt103Servis.findByIdPoruke(request.getIDPorukeNaloga());
		MT102Request mt102 = null;//mt102Servis.findByIdPoruke(request.getIDPorukeNaloga());
		if(mt103 != null){
			odgovor = uradiObracun900Mt103(mt103);
		}else if(mt102 != null){
			odgovor = uradiObracun900Mt102(mt102);
		}else{
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
		MT103 mt103 = null;//mt103Servis.findByIdPoruke(request.getIDPorukeNaloga());
		MT102 mt102 = null;//mt102Servis.findByIdPoruke(request.getIDPorukeNaloga());
		if(mt103 != null){
			odgovor = uradiObracun910Mt103(konvertujMT103(mt103));
		}else if(mt102 != null){
			odgovor = uradiObracun910Mt102(konvertujMT102(mt102));
		}else{
			odgovor = "MT910 - Nesto ne valja";
		}
		
		response.setOdgovor(odgovor);
		return response;
	}

	private MT102Request konvertujMT102(MT102 mt102) throws DatatypeConfigurationException {

		MT102Request req = new MT102Request();
		
		req.setIDPoruke(mt102.getIdPoruke());
		
		req.getBankaDuznika().setSWIFT(mt102.getSwiftDuznik());
		req.getBankaDuznika().setObracunskiRacun(mt102.getObracunskiRacunDuznik());
		
		req.getBankaPoverioca().setSWIFT(mt102.getSwiftPoverilac());
		req.getBankaPoverioca().setObracunskiRacun(mt102.getObracunskiRacunPoverilac());
		
		req.setUkupanIznos(mt102.getUkupanIznos());
		req.setSifraValute(mt102.getSifraValute());
		
		 GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		 gcal.setTime(mt102.getDatumValute());
		 
	      XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
	            .newXMLGregorianCalendar(gcal);

		
		req.setDatumValute(xgcal);
		
		gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		 gcal.setTime(mt102.getDatum());
		 
		  xgcal = DatatypeFactory.newInstance()
		            .newXMLGregorianCalendar(gcal);

		req.setDatum(xgcal);
		
	for(int i=0; i<mt102.getPojedinacnoPlacanje().size(); i++){
		req.getPojedinacnoPlacanje().get(i).
		setIDNalogaZaPlacanje(mt102.getPojedinacnoPlacanje().get(i).getIdNalogaZaPlacanje());
		
		req.getPojedinacnoPlacanje().get(i).
		setDuznik(mt102.getPojedinacnoPlacanje().get(i).getDuznik());
		
		req.getPojedinacnoPlacanje().get(i).
		setSvrhaPlacanja(mt102.getPojedinacnoPlacanje().get(i).getSvrhaPlacanja());
		
		gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		 gcal.setTime(mt102.getPojedinacnoPlacanje().get(i).getDatumNaloga());
		 
		  xgcal = DatatypeFactory.newInstance()
		            .newXMLGregorianCalendar(gcal);
		
		
		req.getPojedinacnoPlacanje().get(i).setDatumNaloga(xgcal);
		
		req.getPojedinacnoPlacanje().get(i).getDuznikRacun().setPozivNaBroj(
				mt102.getPojedinacnoPlacanje().get(i).getPozivNaBrojDuznik());
		req.getPojedinacnoPlacanje().get(i).getDuznikRacun().setModel(
				mt102.getPojedinacnoPlacanje().get(i).getModelDuznik());
		req.getPojedinacnoPlacanje().get(i).getDuznikRacun().setRacun(
				mt102.getPojedinacnoPlacanje().get(i).getRacunDruznik());
		
		
		req.getPojedinacnoPlacanje().get(i).getPoverilacRacun().setPozivNaBroj(
				mt102.getPojedinacnoPlacanje().get(i).getPozivNaBrojPoverilac());
		req.getPojedinacnoPlacanje().get(i).getPoverilacRacun().setModel(
				mt102.getPojedinacnoPlacanje().get(i).getModelDuznik());
		req.getPojedinacnoPlacanje().get(i).getPoverilacRacun().setRacun(
				mt102.getPojedinacnoPlacanje().get(i).getRacunPoverilac());
		
		
	}
		
		
		
	
		
		
		
		
		return null;
	}

	private MT103Request konvertujMT103(MT103 mt103) throws DatatypeConfigurationException {
		
		MT103Request req = new MT103Request();
		
		req.setIDPoruke(mt103.getIdPoruke());
		
		req.getBankaDuznika().setSWIFT(mt103.getSwiftDuznik());
		req.getBankaDuznika().setObracunskiRacun(mt103.getObracunskiRacunDuznik());
		
		req.getBankaPoverioca().setSWIFT(mt103.getSwiftPoverilac());
		req.getBankaPoverioca().setObracunskiRacun(mt103.getObracunskiRacunPoverilac());
		
		req.getNalog().setDuznik(mt103.getDuznik());
		req.getNalog().setSvrhaPlacanja(mt103.getSvrhaPlacanja());
		req.getNalog().setPrimalac(mt103.getPrimalac());
		
		
		 GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		 gcal.setTime(mt103.getDatumValute());
		 
	      XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
	            .newXMLGregorianCalendar(gcal);
	      
	      req.getNalog().setDatumValute(xgcal);
	      
	      gcal = (GregorianCalendar) GregorianCalendar.getInstance();
			 gcal.setTime(mt103.getDatumNaloga());
			 
			  xgcal = DatatypeFactory.newInstance()
			            .newXMLGregorianCalendar(gcal);
			  
			  req.getNalog().setDatumValute(xgcal);
			  
			  req.getNalog().getDuznikRacun().setModel(mt103.getModelDuznik());
			  req.getNalog().getDuznikRacun().setPozivNaBroj(mt103.getPozivNaBrojDuznik());
			  req.getNalog().getDuznikRacun().setRacun(mt103.getRacunDuznik());
			  
			  req.getNalog().getPoverilacRacun().setModel(mt103.getModelPoverilac());
			  req.getNalog().getPoverilacRacun().setPozivNaBroj(mt103.getPozivNaBrojPoverilac());
			  req.getNalog().getPoverilacRacun().setRacun(mt103.getRacunDuznik());
			  
			  req.getNalog().setIznos(mt103.getIznos());
			  
			  req.setSifraValute(mt103.getSifraValute());

		return req;
	}

	private String uradiObracun910Mt102(MT102Request request) {
		MedjubankarskiPrenos kliring = new MedjubankarskiPrenos();
		// TODO:A  ... kliring.set ...
		// TODO:A  medjubankarskiPrenosServis.save(kliring);
		
		for(TPojedinacnoPlacanje pojedinacno : request.getPojedinacnoPlacanje()){
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
			DnevnoStanjeRacuna dnevnoStanjeRacunaPoverioca = dnevnoStanjeRacunaService.findByRacunAndDatum(racunPov, danasnjiDatum);
			DnevnoStanjeRacuna poslednjeDnevnoStanjePoverilac = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunPov);
			if (dnevnoStanjeRacunaPoverioca == null) {
				dnevnoStanjeRacunaPoverioca = new DnevnoStanjeRacuna();
				dnevnoStanjeRacunaPoverioca.setDatum(danasnjiDatum);
				dnevnoStanjeRacunaPoverioca.setRacun(racunPov);
				dnevnoStanjeRacunaPoverioca.setPrometNaTeret(0);
				dnevnoStanjeRacunaPoverioca.setPrometUKorist(pojedinacno.getIznos().doubleValue());
				if(poslednjeDnevnoStanjePoverilac != null)
					dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
				else
					dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(0);
				
				dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
			} else {
				dnevnoStanjeRacunaPoverioca.setPrometUKorist(dnevnoStanjeRacunaPoverioca.getPrometUKorist() + pojedinacno.getIznos().doubleValue());
				// dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
				dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
			}
			
			dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaPoverioca);
			
			analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaPoverioca);
			analitikaIzvodaService.save(analitikaIzvoda);
			
			StavkaPrenosa stavka = new StavkaPrenosa();
			stavka.setAnalitikaIzvoda(analitikaIzvoda);
			stavka.setMedjubankarskiPrenos(kliring);
			// TODO:A  stavkaServis.save(stavka);
		}

		return "Ok";
	}

	private String uradiObracun910Mt103(MT103Request request) {
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
		DnevnoStanjeRacuna dnevnoStanjeRacunaPoverioca = dnevnoStanjeRacunaService.findByRacunAndDatum(racunPov, danasnjiDatum);
		DnevnoStanjeRacuna poslednjeDnevnoStanjePoverilac = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunPov);
		if (dnevnoStanjeRacunaPoverioca == null) {
			dnevnoStanjeRacunaPoverioca = new DnevnoStanjeRacuna();
			dnevnoStanjeRacunaPoverioca.setDatum(danasnjiDatum);
			dnevnoStanjeRacunaPoverioca.setRacun(racunPov);
			dnevnoStanjeRacunaPoverioca.setPrometNaTeret(0);
			dnevnoStanjeRacunaPoverioca.setPrometUKorist(request.getNalog().getIznos().doubleValue());
			if(poslednjeDnevnoStanjePoverilac != null)
				dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
			else
				dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(0);
			
			dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
		} else {
			dnevnoStanjeRacunaPoverioca.setPrometUKorist(dnevnoStanjeRacunaPoverioca.getPrometUKorist() + request.getNalog().getIznos().doubleValue());
			// dnevnoStanjeRacuna.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
			dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
		}
		
		dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaPoverioca);
		
		analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaPoverioca);
		analitikaIzvodaService.save(analitikaIzvoda);
		
		return "Ok";
	}

	private String uradiObracun900Mt102(MT102Request request) {
		MedjubankarskiPrenos kliring = new MedjubankarskiPrenos();
		// TODO:A  ... kliring.set ...
		// TODO:A  medjubankarskiPrenosServis.save(kliring);
		
		for(TPojedinacnoPlacanje pojedinacno : request.getPojedinacnoPlacanje()){
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
				// dnevnoStanjeRacuna.setPrethodnoStanje(dnevnoStanjeRacuna.getNovoStanje());
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
			// TODO:A  stavkaServis.save(stavka);
		}
		
		return "Ok";
	}

	private String uradiObracun900Mt103(MT103Request request) {
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
			// dnevnoStanjeRacuna.setPrethodnoStanje(dnevnoStanjeRacuna.getNovoStanje());
			dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje() + dnevnoStanjeRacunaDuznika.getPrometUKorist() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
		}
		
		dnevnoStanjeRacunaService.save(dnevnoStanjeRacunaDuznika);
		
		analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanjeRacunaDuznika);
		analitikaIzvodaService.save(analitikaIzvoda);
		
		racunDuz.setRezervisano(racunDuz.getRezervisano() - request.getNalog().getIznos().doubleValue());
		racunService.save(racunDuz);
		
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
		//boolean res12 = sigUtility.verifySignature(document);

		Document doc = DocumentLoader.loadDocument("univerzitet_signed.xml");
		//boolean res = sigUtility.verifySignature(doc);

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
