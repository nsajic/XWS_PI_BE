
package bezbednost.poslovna.xml.ws.nalogzaprenos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bezbednost.poslovna.xml.ws.nalogzaprenos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NalogZaPrenosRequest_QNAME = new QName("http://xml.poslovna.bezbednost/ws/NalogZaPrenos", "NalogZaPrenosRequest");
    private final static QName _NalogZaPrenosResponse_QNAME = new QName("http://xml.poslovna.bezbednost/ws/NalogZaPrenos", "NalogZaPrenosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bezbednost.poslovna.xml.ws.nalogzaprenos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NalogZaPrenosResponse }
     * 
     */
    public NalogZaPrenosResponse createNalogZaPrenosResponse() {
        return new NalogZaPrenosResponse();
    }

    /**
     * Create an instance of {@link NalogZaPrenosRequest }
     * 
     */
    public NalogZaPrenosRequest createNalogZaPrenosRequest() {
        return new NalogZaPrenosRequest();
    }

    /**
     * Create an instance of {@link TNalog }
     * 
     */
    public TNalog createTNalog() {
        return new TNalog();
    }

    /**
     * Create an instance of {@link TPodaciORacunu }
     * 
     */
    public TPodaciORacunu createTPodaciORacunu() {
        return new TPodaciORacunu();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NalogZaPrenosRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/NalogZaPrenos", name = "NalogZaPrenosRequest")
    public JAXBElement<NalogZaPrenosRequest> createNalogZaPrenosRequest(NalogZaPrenosRequest value) {
        return new JAXBElement<NalogZaPrenosRequest>(_NalogZaPrenosRequest_QNAME, NalogZaPrenosRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NalogZaPrenosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/NalogZaPrenos", name = "NalogZaPrenosResponse")
    public JAXBElement<NalogZaPrenosResponse> createNalogZaPrenosResponse(NalogZaPrenosResponse value) {
        return new JAXBElement<NalogZaPrenosResponse>(_NalogZaPrenosResponse_QNAME, NalogZaPrenosResponse.class, null, value);
    }

}
