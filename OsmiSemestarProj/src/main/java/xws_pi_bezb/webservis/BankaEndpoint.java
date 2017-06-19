package xws_pi_bezb.webservis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PrivateKey;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;

import bezbednost.poslovna.xml.ws.izvod.IzvodRequest;
import bezbednost.poslovna.xml.ws.izvod.IzvodResponse;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosRequest;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosResponse;
import xws_pi_bezb.xml.secutiry.DocumentLoader;
import xws_pi_bezb.xml.secutiry.KeyStoreReader;
import xws_pi_bezb.xml.secutiry.XMLEncryptionUtility;
import xws_pi_bezb.xml.secutiry.XMLSigningUtility;

@Endpoint
public class BankaEndpoint {
	private static final String HTTP = "http://";
	private static final String NAMESPACE_URI = "ws.xml.poslovna.bezbednost/";

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

		response.setOdgovor("Povratna poruka!");
		return response;
	}

	@PayloadRoot(namespace = HTTP + "izvod." + NAMESPACE_URI, localPart = "IzvodRequest")
	@ResponsePayload
	public IzvodResponse izvod(@RequestPayload IzvodRequest request) {
		System.out.println("Usao endpoint - izvod");
		IzvodResponse response = new IzvodResponse();

		// response.
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
		} /*else {
			return false;
		}*/
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
