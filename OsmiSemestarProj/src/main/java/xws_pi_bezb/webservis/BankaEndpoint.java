package xws_pi_bezb.webservis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
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
import bezbednost.poslovna.xml.ws.mt103.MT103Request;
import bezbednost.poslovna.xml.ws.mt103.MT103Response;
import bezbednost.poslovna.xml.ws.mt900.MT900Request;
import bezbednost.poslovna.xml.ws.mt900.MT900Response;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosRequest;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosResponse;
import bezbednost.poslovna.xml.ws.nalogzaprenos.TPodaciORacunu;
import xws_pi_bezb.controllers.LogRegKontroler;
import xws_pi_bezb.iservices.IDnevnoStanjeRacunaService;
import xws_pi_bezb.iservices.IRacunService;
import xws_pi_bezb.iservices.IValutaService;
import xws_pi_bezb.models.AnalitikaIzvoda;
import xws_pi_bezb.models.DnevnoStanjeRacuna;
import xws_pi_bezb.models.Racun;
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
	public BankaEndpoint() {

	}

	@PayloadRoot(namespace = HTTP + "nalogzaprenos." + NAMESPACE_URI, localPart = "NalogZaPrenosRequest")
	@ResponsePayload
	public NalogZaPrenosResponse nalogZaPrenos(@RequestPayload NalogZaPrenosRequest request) {
		// System.out.println(request.toString());
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		if (!checkSignAndEnc(request)) {
			response.setOdgovor("Potpis ili enkripcija nije u redu!");
			return response;
		}
		
		TPodaciORacunu racunDuznik = request.getNalog().getDuznikRacun();
		TPodaciORacunu racunPoverilac = request.getNalog().getPoverilacRacun();
		
		if(racunDuznik != null && racunPoverilac != null){
			
		}else if(racunDuznik != null){	// isplata
			String brojRacuna = racunDuznik.getRacun();
			Racun racunD = racunService.findByBrojRacuna(brojRacuna);
			
			//TODO: if(racunD.getBanka() == ulogovanaBanka)
			
			if(racunD.getStatusRacuna() == 1){
				DnevnoStanjeRacuna dnevnoStanje = dnevnoStanjeRacunaService.findTopByRacunOrderByDatum(racunD);
				
				// provera da li ima sredstava
				if(dnevnoStanje.getNovoStanje() > request.getNalog().getIznos().doubleValue()){
					// pravimo analitiku
					AnalitikaIzvoda analitikaIzvoda = new AnalitikaIzvoda();
					analitikaIzvoda.setDatumAnalitike(new Date());
					analitikaIzvoda.setSmer("L");
					analitikaIzvoda.setDuznikNalogodavac(request.getNalog().getDuznik());
					analitikaIzvoda.setSvrhaPlacanja(request.getNalog().getSvrhaPlacanja());
					analitikaIzvoda.setPrimalacPoverilac(request.getNalog().getPrimalac());
					analitikaIzvoda.setDatumNaloga(request.getNalog().getDatumNaloga().toGregorianCalendar().getTime());
					analitikaIzvoda.setDatumValute(request.getNalog().getDatumValute().toGregorianCalendar().getTime());
					analitikaIzvoda.setRacunDuznika(brojRacuna);
					analitikaIzvoda.setModelZaduzenja(racunDuznik.getModel());
					analitikaIzvoda.setPozivNaBrojZaduzenja(racunDuznik.getPozivNaBroj());
					analitikaIzvoda.setIznos(request.getNalog().getIznos().doubleValue());
					analitikaIzvoda.setValuta(valutaService.findBySifraValute(request.getOznakaValute()));
					
					// umanjujemo dnevno stanje -> NEJASNO zasto umanjujemo???
					
					
					
					//analitikaIzvoda.setDnevnoStanjeRacuna(dnevnoStanje);
					
				}else{
					response.setOdgovor("Nema se para!");
					return response;
				}
			}else{
				response.setOdgovor("Racun je zatvoren!");
				return response;
			}
			
		}else if(racunPoverilac != null){	// uplata
			
		}else{
			response.setOdgovor("Nije unet racun!");
			return response;
		}
		// provera da li je nasa banka
		
		// provera da li je unet i racun o poveriocu i o duzniku
		
		// if(duznik) -> isplata
		
			// provera da li ima sredstava
		
				// pravimo analitiku
		
				// umanjujemo dnevno stanje -> NEJASNO
		
		// else if(poverilac) -> uplata
		
			// pravimo analitiku

			// povecavamo dnevno stanje -> NEJASNO
		
		// else if(oba)
		
			// provera da li ima sredstava
			
				// provera da li su racuni iz iste banke
		
				// if(jesu)
		
					// pravimo analitiku za oba
		
					// menjamo dnevno stanje za oba
		
				// else
		
					// pravimo analitiku za racun u nasoj
		
					// menjamo dnevno stanje za racun u nasoj
		
					// provera da li je preko 250000 ili hitno
		
					// if(jeste)
		
						// mt103 - rtgs...
		
					// else
				
						// mt102 - clearing...
		
		// else -> ne valja!

		response.setOdgovor("Povratna poruka!");
		return response;
	}

	@PayloadRoot(namespace = HTTP + "izvod." + NAMESPACE_URI, localPart = "IzvodRequest")
	@ResponsePayload
	public IzvodResponse izvod(@RequestPayload IzvodRequest request) {
		System.out.println("Usao endpoint - izvod");
		IzvodResponse response = new IzvodResponse();

		logger.info("[Zahtev izvoda: " + request.toString());
		
		//response.
		return response;
	}
	
	@PayloadRoot(namespace = HTTP + "MT102." + NAMESPACE_URI, localPart = "MT102Request")
	@ResponsePayload
	public MT102Response mt102(@RequestPayload MT102Request request){
		MT102Response response = new MT102Response();
		response.setOdgovor("MT102 - odgovor");
		return response;
	}
	
	@PayloadRoot(namespace = HTTP + "MT103." + NAMESPACE_URI, localPart = "MT103Request")
	@ResponsePayload
	public MT103Response mt103(@RequestPayload MT103Request request){
		MT103Response response = new MT103Response();
		response.setOdgovor("MT103 - odgovor");
		return response;
	}
	
	@PayloadRoot(namespace = HTTP + "MT900." + NAMESPACE_URI, localPart = "MT900Request")
	@ResponsePayload
	public MT900Response mt900(@RequestPayload MT900Request request){
		MT900Response response = new MT900Response();
		response.setOdgovor("MT900 - odgovor");
		return response;
	}
	
	@PayloadRoot(namespace = HTTP + "MT910." + NAMESPACE_URI, localPart = "MT910Request")
	@ResponsePayload
	public MT900Response mt910(@RequestPayload MT900Request request){
		MT900Response response = new MT900Response();
		response.setOdgovor("MT910 - odgovor");
		return response;
	}

	public boolean checkSignAndEnc(NalogZaPrenosRequest request) {
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
		boolean res12 = sigUtility.verifySignature(document);
		
		Document doc = DocumentLoader.loadDocument("univerzitet_signed.xml");
		boolean res = sigUtility.verifySignature(doc);

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
			return false;
		}
		
		NalogZaPrenosRequest nalogZaPrenosRequest = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(NalogZaPrenosRequest.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			nalogZaPrenosRequest = (NalogZaPrenosRequest) jaxbUnmarshaller.unmarshal(new File("testKraj.xml"));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(nalogZaPrenosRequest != null){
			logger.info("[Nalog za prenos: " + nalogZaPrenosRequest.toString());
			System.out.println("[Nalog za prenos: " + nalogZaPrenosRequest.toString());
		}
		

		return true;
	}
	
	/*
	 * @PayloadRoot(namespace = HTTP + "mt102." + NAMESPACE_URI, localPart =
	 * "NalogZaPrenosRequest")
	 * 
	 * @ResponsePayload public NalogZaPrenosResponse mt102(@RequestPayload
	 * NalogZaPrenosRequest request) {
	 * System.out.println("Usao endpoint - banka"); NalogZaPrenosResponse
	 * response = new NalogZaPrenosResponse();
	 * response.setOdgovor("Povratna poruka!"); return response; }
	 * 
	 * @PayloadRoot(namespace = HTTP + "mt103." + NAMESPACE_URI, localPart =
	 * "NalogZaPrenosRequest")
	 * 
	 * @ResponsePayload public NalogZaPrenosResponse mt103(@RequestPayload
	 * NalogZaPrenosRequest request) {
	 * System.out.println("Usao endpoint - banka"); NalogZaPrenosResponse
	 * response = new NalogZaPrenosResponse();
	 * response.setOdgovor("Povratna poruka!"); return response; }
	 * 
	 * @PayloadRoot(namespace = HTTP + "mt900." + NAMESPACE_URI, localPart =
	 * "NalogZaPrenosRequest")
	 * 
	 * @ResponsePayload public NalogZaPrenosResponse mt900(@RequestPayload
	 * NalogZaPrenosRequest request) {
	 * System.out.println("Usao endpoint - banka"); NalogZaPrenosResponse
	 * response = new NalogZaPrenosResponse();
	 * response.setOdgovor("Povratna poruka!"); return response; }
	 * 
	 * @PayloadRoot(namespace = HTTP + "mt910." + NAMESPACE_URI, localPart =
	 * "NalogZaPrenosRequest")
	 * 
	 * @ResponsePayload public NalogZaPrenosResponse mt910(@RequestPayload
	 * NalogZaPrenosRequest request) {
	 * System.out.println("Usao endpoint - banka"); NalogZaPrenosResponse
	 * response = new NalogZaPrenosResponse();
	 * response.setOdgovor("Povratna poruka!"); return response; }
	 */
}
