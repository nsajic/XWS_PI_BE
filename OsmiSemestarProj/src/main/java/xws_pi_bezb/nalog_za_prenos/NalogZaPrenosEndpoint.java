package xws_pi_bezb.nalog_za_prenos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosRequest;
import bezbednost.poslovna.xml.ws.nalogzaprenos.NalogZaPrenosResponse;



@Endpoint
@org.apache.cxf.annotations.EndpointProperty(key = "soap.no.validate.parts", value = "true")
public class NalogZaPrenosEndpoint {
	private static final String NAMESPACE_URI = "http://nalogzaprenos.ws.xml.poslovna.bezbednost/";

	@Autowired
	public NalogZaPrenosEndpoint() {

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "NalogZaPrenosRequest")
	@ResponsePayload
	public NalogZaPrenosResponse nalogZaPrenos(@RequestPayload NalogZaPrenosRequest request) {
		System.out.println("Usao endpoint - banka");
		NalogZaPrenosResponse response = new NalogZaPrenosResponse();
		response.setOdgovor("Povratna poruka!");
		return response;
	}

}
