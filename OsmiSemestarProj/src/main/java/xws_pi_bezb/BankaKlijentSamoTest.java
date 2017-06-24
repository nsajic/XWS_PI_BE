package xws_pi_bezb;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import bezbednost.poslovna.xml.ws.mt102.MT102Request;
import bezbednost.poslovna.xml.ws.mt102.MT102Response;
import bezbednost.poslovna.xml.ws.mt103.MT103Request;
import bezbednost.poslovna.xml.ws.mt103.MT103Response;

//OVO CE SE RADITI - POVEZIVATI IZ KONTROLERA!
public class BankaKlijentSamoTest extends WebServiceGatewaySupport {

	public void posaljiMT102(MT102Request request) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(MT102Request.class, MT102Response.class);
		setMarshaller(marshaller);
		setUnmarshaller(marshaller);

		String uri = "http://localhost:9001/ws/MT102";
		Object o = getWebServiceTemplate().marshalSendAndReceive(uri, request);
		MT102Response response = (MT102Response) o;
		System.out.println("CB primljen MT102:  " + response.getOdgovor());

	}

	public void posaljiMT103(MT103Request request) {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(MT103Request.class, MT103Response.class);
		setMarshaller(marshaller);
		setUnmarshaller(marshaller);

		String uri = "http://localhost:9001/ws/MT103";
		Object o = getWebServiceTemplate().marshalSendAndReceive(uri, request);
		MT103Response response = (MT103Response) o;
		System.out.println("CB primljen MT103:  " + response.getOdgovor());

	}
}
