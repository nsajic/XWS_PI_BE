package xws_pi_bezb.controllers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bezbednost.poslovna.xml.ws.mt103.MT103Request;
import bezbednost.poslovna.xml.ws.mt103.TSWIFTIRacun;
import bezbednost.poslovna.xml.ws.nalogzaprenos.TNalog;
import bezbednost.poslovna.xml.ws.nalogzaprenos.TPodaciORacunu;
import xws_pi_bezb.BankaKlijentSamoTest;
import xws_pi_bezb.annotations.InterceptorAnnotation;
import xws_pi_bezb.iservices.IAnalitikaIzvodaService;
import xws_pi_bezb.iservices.IBankaService;
import xws_pi_bezb.iservices.IBankarskiSluzbenikService;
import xws_pi_bezb.iservices.IDnevnoStanjeRacunaService;
import xws_pi_bezb.iservices.IKlijentService;
import xws_pi_bezb.iservices.IMT102Services;
import xws_pi_bezb.iservices.IMT103Services;
import xws_pi_bezb.iservices.IPojedinacnoPlacanjeService;
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.AnalitikaIzvoda;
import xws_pi_bezb.models.Banka;
import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.Klijent;
import xws_pi_bezb.models.MT102;
import xws_pi_bezb.models.MT103;
import xws_pi_bezb.models.PojedinacnoPlacanje;
import xws_pi_bezb.models.Racun;
import xws_pi_bezb.models.Valuta;
import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;
import xws_pi_bezb.view_models.ZatvoriRacunViewModel;


@Controller
@RequestMapping("/racunKontroler")
public class RacunKontroler {

	@Autowired
	public IRacunService racunService;
	@Autowired
	public IKlijentService klijentService;
	@Autowired
	public IValutaService valutaService;
	@Autowired
	public IBankaService bankaService;
	@Autowired
	private IDnevnoStanjeRacunaService dnevnoStanjeRacunaService;
	@Autowired
	private IBankarskiSluzbenikService bankarskiSluzbenikService;
	@Autowired
	private IAnalitikaIzvodaService analitikaIzvodaService;

	@Autowired
	private IMT102Services mt102Servis;

	@Autowired
	private IMT103Services mt103Servis;
	
	@Autowired
	private IPojedinacnoPlacanjeService pojedinacnoPlacanjeServis;

	
	@RequestMapping(value = "/dodajRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:Dodaj")
	public ResponseEntity<Object> dodajRacun(HttpSession session,@RequestBody Racun racun ) throws ParseException {
		DnevnoStanjeRacuna dsr = new DnevnoStanjeRacuna();
		dsr.setDatum(getDateWithZeroTime());
		dsr.setNovoStanje(0);
		dsr.setPrethodnoStanje(0);
		dsr.setPrometNaTeret(0);
		dsr.setPrometUKorist(0);
		dsr.setRacun(racun);
		
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		
		racun.setStatusRacuna(1);
		racun.setBrojRacuna(racun.getBrojRacuna().trim());
		racun.setBanka(bankaService.findOne(sluzbenik.getBanka().getId()));

		racunService.save(racun);
		dnevnoStanjeRacunaService.save(dsr);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/izmeniRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:Izmeni")
	public ResponseEntity<Object> izmeniRacun(@RequestBody Racun racun, HttpSession session) {
		
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		racun.setBanka(bankaService.findOne(sluzbenik.getBanka().getId()));
		racunService.save(racun);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/izbrisiRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:Obrisi")
	public ResponseEntity<Object> izbrisiRacun(@RequestBody Long racunId) {
		racunService.delete(racunId);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/izlistajRacune", method = RequestMethod.GET)
	@InterceptorAnnotation("Racun:IzlistajPretrazi")
	public ResponseEntity<List<Racun>> izlistajRacune(HttpSession session) {
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<List<Racun>>(HttpStatus.BAD_REQUEST);
		}
		BankarskiSluzbenik sluzbenik2 = bankarskiSluzbenikService.findOne(sluzbenik.getId());
		return new ResponseEntity<List<Racun>>(racunService.findByBanka(sluzbenik2.getBanka()), HttpStatus.OK);
	}

	@RequestMapping(value = "/pretraziRacune", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:IzlistajPretrazi")
	public ResponseEntity<List<Racun>> pretraziPravnaLica(@RequestBody Racun racun, HttpSession session) {
		BankarskiSluzbenik sluzbenik = (BankarskiSluzbenik)session.getAttribute("ulogovanKorisnik");
		if(sluzbenik == null){
			return new ResponseEntity<List<Racun>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Racun>>(racunService.getRacunBySearch(racun, sluzbenik.getBanka()), HttpStatus.OK);
	}
	

	@RequestMapping(value = "/ucitajRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:IzlistajPretrazi")
	public ResponseEntity<Racun> ucitajPravnoLice(@RequestBody Racun racun) {
		return new ResponseEntity<Racun>(racunService.findOne(racun.getId()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajValute", method = RequestMethod.GET)
	@InterceptorAnnotation("Valuta:IzlistajPretrazi")
	public ResponseEntity<List<Valuta>> ucitajValute() {
		return new ResponseEntity<List<Valuta>>(valutaService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ucitajKlijente", method = RequestMethod.GET)
	@InterceptorAnnotation("Banka:IzlistajPretrazi")
	public ResponseEntity<List<Klijent>> ucitajKlijente() {
		return new ResponseEntity<List<Klijent>>(klijentService.findAll(), HttpStatus.OK);
	}
	

	// NEXTOVI
	
	@RequestMapping(value = "/ucitajDnevnaStanjaOdabranogRacuna", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@InterceptorAnnotation("Racun:DnevnoStanjeOdabranogRacuna")
	public ResponseEntity<List<DnevnoStanjeRacuna>> ucitajDnevnaStanjaOdabranogRacuna(@RequestBody Racun racun) {
		List<DnevnoStanjeRacuna> retVal = dnevnoStanjeRacunaService.findByRacun(racun);
		return new ResponseEntity<List<DnevnoStanjeRacuna>>(retVal, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/ucitajAnalitikeOdabranogDnevnogStanja", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Racun:DnevnoStanjeOdabranogRacuna")
	public ResponseEntity<List<AnalitikaIzvoda>> ucitajAnalitikeOdabranogDnevnogStanja(@RequestBody DnevnoStanjeRacuna dnevnoStanjeRacuna) {
		System.out.println(dnevnoStanjeRacuna.getRacun().getBrojRacuna() + " sasasasa");
		List<AnalitikaIzvoda> retVal = analitikaIzvodaService.findByDnevnoStanjeRacuna(dnevnoStanjeRacuna);
		return new ResponseEntity<List<AnalitikaIzvoda>>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/zatvoriRacun", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@InterceptorAnnotation("Racun:DnevnoStanjeOdabranogRacuna")
	public ResponseEntity<Object> zatvoriRacun(@RequestBody ZatvoriRacunViewModel zatvoriRacunViewModel) {

		Racun saKogPrebacujem = racunService.findOne(zatvoriRacunViewModel.getIdRacunKojiGasim());
		
		Racun naKojiPrebacujem = racunService.findByBrojRacuna(zatvoriRacunViewModel.getBrojRacunaNaKojiPrebacujem().trim());


		if(naKojiPrebacujem == null){
			return new ResponseEntity<Object>("Uneli ste nepostojeci racun." ,HttpStatus.BAD_REQUEST);
		}
		if(saKogPrebacujem.getId().equals(naKojiPrebacujem.getId())){
			return new ResponseEntity<Object>("Uneli ste racun koji zelite da zatvorite." ,HttpStatus.BAD_REQUEST);
		}
		if(naKojiPrebacujem.getStatusRacuna() == 2){
			return new ResponseEntity<Object>("Uneli ste racun koji je zatvoren." ,HttpStatus.BAD_REQUEST);
		}
		
		//TODO ovde coa ako imas neki kod 
		
		
		DnevnoStanjeRacuna poslednjeDnevnoStanje = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(saKogPrebacujem);

		if (poslednjeDnevnoStanje == null) {
			saKogPrebacujem.setStatusRacuna(2);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		
		if (poslednjeDnevnoStanje.getNovoStanje() == 0) {
			saKogPrebacujem.setStatusRacuna(2);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		
		if (saKogPrebacujem.getBanka().getId().equals(naKojiPrebacujem.getBanka().getId())) {
			
			// pravimo analitiku za oba
			int godina = Calendar.getInstance().get(Calendar.YEAR);
			int mesec = Calendar.getInstance().get(Calendar.MONTH) + 1;
			int dan = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			Date danasnjiDatum = java.sql.Date.valueOf(LocalDate.of(godina, mesec, dan));

			AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
			analitikaIzvoda.setDatumAnalitike(danasnjiDatum);
			analitikaIzvoda.setSmer("I");
			analitikaIzvoda.setDuznikNalogodavac(saKogPrebacujem.getKlijent().getIme());
			analitikaIzvoda.setSvrhaPlacanja("Zatvaranje racuna");
			analitikaIzvoda.setPrimalacPoverilac(naKojiPrebacujem.getKlijent().getIme());
			analitikaIzvoda.setDatumNaloga(danasnjiDatum);
			analitikaIzvoda.setDatumValute(danasnjiDatum);
			analitikaIzvoda.setRacunDuznika(saKogPrebacujem.getBrojRacuna());
			analitikaIzvoda.setModelZaduzenja(90);
			analitikaIzvoda.setPozivNaBrojZaduzenja("pnb - duz");
			analitikaIzvoda.setRacunPoverioca(naKojiPrebacujem.getBrojRacuna());
			analitikaIzvoda.setModelOdobrenja(90);
			analitikaIzvoda.setPozivNaBrojOdobrenja("pnb - pov");
			analitikaIzvoda.setIznos(poslednjeDnevnoStanje.getNovoStanje());
			analitikaIzvoda.setValuta(saKogPrebacujem.getValuta());

			// menjamo dnevno stanje za oba

			DnevnoStanjeRacuna dnevnoStanjeRacunaDuznika = dnevnoStanjeRacunaService.findByRacunAndDatum(saKogPrebacujem, danasnjiDatum);
			if (dnevnoStanjeRacunaDuznika == null) {
				dnevnoStanjeRacunaDuznika = new DnevnoStanjeRacuna();
				dnevnoStanjeRacunaDuznika.setDatum(danasnjiDatum);
				dnevnoStanjeRacunaDuznika.setRacun(saKogPrebacujem);
				dnevnoStanjeRacunaDuznika.setPrometNaTeret(poslednjeDnevnoStanje.getNovoStanje());
				dnevnoStanjeRacunaDuznika.setPrometUKorist(0);
				dnevnoStanjeRacunaDuznika.setPrethodnoStanje(poslednjeDnevnoStanje.getNovoStanje());
				dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
			} else {
				dnevnoStanjeRacunaDuznika.setPrometNaTeret(dnevnoStanjeRacunaDuznika.getPrometNaTeret() + poslednjeDnevnoStanje.getNovoStanje());
				dnevnoStanjeRacunaDuznika.setNovoStanje(dnevnoStanjeRacunaDuznika.getPrethodnoStanje() + dnevnoStanjeRacunaDuznika.getPrometUKorist() - dnevnoStanjeRacunaDuznika.getPrometNaTeret());
			}

			DnevnoStanjeRacuna dnevnoStanjeRacunaPoverioca = dnevnoStanjeRacunaService.findByRacunAndDatum(naKojiPrebacujem, danasnjiDatum);
			DnevnoStanjeRacuna poslednjeDnevnoStanjePoverilac = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(naKojiPrebacujem);
			if (dnevnoStanjeRacunaPoverioca == null) {
				dnevnoStanjeRacunaPoverioca = new DnevnoStanjeRacuna();
				dnevnoStanjeRacunaPoverioca.setDatum(danasnjiDatum);
				dnevnoStanjeRacunaPoverioca.setRacun(naKojiPrebacujem);
				dnevnoStanjeRacunaPoverioca.setPrometNaTeret(0);
				dnevnoStanjeRacunaPoverioca.setPrometUKorist(poslednjeDnevnoStanje.getNovoStanje());

				if (poslednjeDnevnoStanjePoverilac != null)
					dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(poslednjeDnevnoStanjePoverilac.getNovoStanje());
				else
					dnevnoStanjeRacunaPoverioca.setPrethodnoStanje(0);

				dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist());
			} else {
				dnevnoStanjeRacunaPoverioca.setPrometUKorist(dnevnoStanjeRacunaPoverioca.getPrometUKorist() + poslednjeDnevnoStanje.getNovoStanje());
				dnevnoStanjeRacunaPoverioca.setNovoStanje(dnevnoStanjeRacunaPoverioca.getPrethodnoStanje() + dnevnoStanjeRacunaPoverioca.getPrometUKorist() - dnevnoStanjeRacunaPoverioca.getPrometNaTeret());
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

			if (poslednjeDnevnoStanje.getNovoStanje() >= 250000) {
				// rezervisemo sredstva
				saKogPrebacujem.setRezervisano(saKogPrebacujem.getRezervisano() + poslednjeDnevnoStanje.getNovoStanje());
				racunService.save(saKogPrebacujem);

				// mt103 - rtgs...
				MT103Request mt103Request = new MT103Request();

				Banka bankaDuznika = bankaService.findByRacun(saKogPrebacujem);
				Banka bankaPoverilac = bankaService.findByRacun(naKojiPrebacujem);

				TSWIFTIRacun swiftDuznik = new TSWIFTIRacun();
				swiftDuznik.setObracunskiRacun(bankaDuznika.getObracunskiRacun());
				swiftDuznik.setSWIFT(bankaDuznika.getSwiftKod());

				TSWIFTIRacun swiftPoverilac = new TSWIFTIRacun();
				swiftPoverilac.setObracunskiRacun(bankaPoverilac.getObracunskiRacun());
				swiftPoverilac.setSWIFT(bankaPoverilac.getSwiftKod());
				
				
				TNalog nalog = new TNalog();
				nalog.setDuznik(saKogPrebacujem.getKlijent().getIme());
				nalog.setPrimalac(naKojiPrebacujem.getKlijent().getIme());
				nalog.setSvrhaPlacanja("Zatvaranje racuna");
				
				TPodaciORacunu racunDuznika = new TPodaciORacunu();
				racunDuznika.setRacun(saKogPrebacujem.getBrojRacuna());
				racunDuznika.setModel(90);
				racunDuznika.setPozivNaBroj("pnb - duz");
				TPodaciORacunu racunPoverioca = new TPodaciORacunu();
				racunPoverioca.setRacun(naKojiPrebacujem.getBrojRacuna());
				racunPoverioca.setModel(90);
				racunPoverioca.setPozivNaBroj("pnb - pov");
				
				nalog.setDuznikRacun(racunDuznika);
				nalog.setPoverilacRacun(racunPoverioca);
				nalog.setIznos(BigDecimal.valueOf(poslednjeDnevnoStanje.getNovoStanje()));
				
				GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
				gcal.setTime(danasnjiDatum);
				XMLGregorianCalendar xgcal;
				try {
					xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
					nalog.setDatumNaloga(xgcal);
					nalog.setDatumValute(xgcal);
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				mt103Request.setIDPoruke("MT103-" + new Random(danasnjiDatum.getTime()));

				mt103Request.setBankaDuznika(swiftDuznik);
				mt103Request.setBankaPoverioca(swiftPoverilac);
				mt103Request.setNalog(nalog);
				mt103Request.setSifraValute(saKogPrebacujem.getValuta().getSifraValute());

				mt103Servis.save(konvertujMT103Request(mt103Request));

				BankaKlijentSamoTest bankaKlijent = new BankaKlijentSamoTest();
				bankaKlijent.posaljiMT103(mt103Request);
			} else {
				// mt102 - clearing...
				MT102 mt102 = mt102Servis.findBySwiftDuznikAndSwiftPoverilacAndPoslat(
						saKogPrebacujem.getBanka().getSwiftKod(), naKojiPrebacujem.getBanka().getSwiftKod(), false);
				if (mt102 == null) {
					mt102 = new MT102();
					mt102.setIdPoruke("MT102-" + new Random(danasnjiDatum.getTime()));
					mt102.setObracunskiRacunDuznik(saKogPrebacujem.getBanka().getObracunskiRacun());
					mt102.setObracunskiRacunPoverilac(naKojiPrebacujem.getBanka().getObracunskiRacun());
					mt102.setPoslat(false);
					mt102.setSifraValute(saKogPrebacujem.getValuta().getSifraValute());
					mt102.setSwiftDuznik(saKogPrebacujem.getBanka().getSwiftKod());
					mt102.setSwiftPoverilac(naKojiPrebacujem.getBanka().getSwiftKod());
					mt102.setUkupanIznos(BigDecimal.valueOf(poslednjeDnevnoStanje.getNovoStanje()));
					mt102.setDatum(danasnjiDatum);
					mt102.setDatumValute(danasnjiDatum);
				} else {
					BigDecimal rezultat = mt102.getUkupanIznos().add(BigDecimal.valueOf(poslednjeDnevnoStanje.getNovoStanje()));
					mt102.setUkupanIznos(rezultat);
				}
				mt102Servis.save(mt102);

				saKogPrebacujem.setRezervisano(saKogPrebacujem.getRezervisano() + poslednjeDnevnoStanje.getNovoStanje());
				racunService.save(saKogPrebacujem);

				PojedinacnoPlacanje pojedinacnoPlacanje = new PojedinacnoPlacanje();
				pojedinacnoPlacanje.setIdNalogaZaPlacanje("NZP-" + new Random(danasnjiDatum.getTime()));
				pojedinacnoPlacanje.setDatumNaloga(danasnjiDatum);
				pojedinacnoPlacanje.setDuznik(saKogPrebacujem.getKlijent().getIme());
				pojedinacnoPlacanje.setModelDuznik(90);
				pojedinacnoPlacanje.setModelPoverilac(90);
				pojedinacnoPlacanje.setIznos(BigDecimal.valueOf(poslednjeDnevnoStanje.getNovoStanje()));
				pojedinacnoPlacanje.setPozivNaBrojDuznik("pnb - duz");
				pojedinacnoPlacanje.setPozivNaBrojPoverilac("pnb - pov");
				pojedinacnoPlacanje.setPrimalac(naKojiPrebacujem.getKlijent().getIme());
				pojedinacnoPlacanje.setRacunDruznik(saKogPrebacujem.getBrojRacuna());
				pojedinacnoPlacanje.setRacunPoverilac(naKojiPrebacujem.getBrojRacuna());
				pojedinacnoPlacanje.setSifraValute(saKogPrebacujem.getValuta().getSifraValute());
				pojedinacnoPlacanje.setSvrhaPlacanja("Zatvaranje racuna");
				pojedinacnoPlacanje.setMt102(mt102);
				pojedinacnoPlacanjeServis.save(pojedinacnoPlacanje);

				
				TPodaciORacunu racunDuznika = new TPodaciORacunu();
				racunDuznika.setRacun(saKogPrebacujem.getBrojRacuna());
				racunDuznika.setModel(90);
				racunDuznika.setPozivNaBroj("pnb - duz");
				TPodaciORacunu racunPoverioca = new TPodaciORacunu();
				racunPoverioca.setRacun(naKojiPrebacujem.getBrojRacuna());
				racunPoverioca.setModel(90);
				racunPoverioca.setPozivNaBroj("pnb - pov");
			}

		}
		
		saKogPrebacujem.setStatusRacuna(2);
		
		racunService.save(saKogPrebacujem);
		return new ResponseEntity<Object>(HttpStatus.OK);
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
	
	
	
	private Date getDateWithZeroTime () throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		return formatter.parse(formatter.format(today));
	}
	

}
