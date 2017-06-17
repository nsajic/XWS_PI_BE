package xws_pi_bezb.webservis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import bezbednost.poslovna.xml.ws.izvod.IzvodRequest;
import bezbednost.poslovna.xml.ws.izvod.IzvodResponse;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosRequest;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosResponse;

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
		System.out.println(request.toString());
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		response.setOdgovor("Povratna poruka!");
		return response;
	}
	
	@PayloadRoot(namespace = HTTP + "izvod." + NAMESPACE_URI, localPart = "IzvodRequest")
	@ResponsePayload
	public IzvodResponse izvod(@RequestPayload IzvodRequest request) {
		System.out.println("Usao endpoint - izvod");
		IzvodResponse response = new IzvodResponse();
		
		
		//response.
		return response;
	}
	/*
	@PayloadRoot(namespace = HTTP + "mt102." + NAMESPACE_URI, localPart = "NalogZaPrenosRequest")
	@ResponsePayload
	public NalogZaPrenosResponse mt102(@RequestPayload NalogZaPrenosRequest request) {
		System.out.println("Usao endpoint - banka");
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		response.setOdgovor("Povratna poruka!");
		return response;
	}
	
	@PayloadRoot(namespace = HTTP + "mt103." + NAMESPACE_URI, localPart = "NalogZaPrenosRequest")
	@ResponsePayload
	public NalogZaPrenosResponse mt103(@RequestPayload NalogZaPrenosRequest request) {
		System.out.println("Usao endpoint - banka");
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		response.setOdgovor("Povratna poruka!");
		return response;
	}
	
	@PayloadRoot(namespace = HTTP + "mt900." + NAMESPACE_URI, localPart = "NalogZaPrenosRequest")
	@ResponsePayload
	public NalogZaPrenosResponse mt900(@RequestPayload NalogZaPrenosRequest request) {
		System.out.println("Usao endpoint - banka");
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		response.setOdgovor("Povratna poruka!");
		return response;
	}
	
	@PayloadRoot(namespace = HTTP + "mt910." + NAMESPACE_URI, localPart = "NalogZaPrenosRequest")
	@ResponsePayload
	public NalogZaPrenosResponse mt910(@RequestPayload NalogZaPrenosRequest request) {
		System.out.println("Usao endpoint - banka");
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		response.setOdgovor("Povratna poruka!");
		return response;
	}
	*/
}
