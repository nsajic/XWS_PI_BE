
package bezbednost.poslovna.xml.ws.izvod;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bezbednost.poslovna.xml.ws.izvod package. 
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

    private final static QName _IzvodResponse_QNAME = new QName("http://xml.poslovna.bezbednost/ws/Izvod", "IzvodResponse");
    private final static QName _IzvodRequest_QNAME = new QName("http://xml.poslovna.bezbednost/ws/Izvod", "IzvodRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bezbednost.poslovna.xml.ws.izvod
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IzvodRequest }
     * 
     */
    public IzvodRequest createIzvodRequest() {
        return new IzvodRequest();
    }

    /**
     * Create an instance of {@link IzvodResponse }
     * 
     */
    public IzvodResponse createIzvodResponse() {
        return new IzvodResponse();
    }

    /**
     * Create an instance of {@link TZaglavljePreseka }
     * 
     */
    public TZaglavljePreseka createTZaglavljePreseka() {
        return new TZaglavljePreseka();
    }

    /**
     * Create an instance of {@link TStavkaPreseka }
     * 
     */
    public TStavkaPreseka createTStavkaPreseka() {
        return new TStavkaPreseka();
    }

    /**
     * Create an instance of {@link TZahtev }
     * 
     */
    public TZahtev createTZahtev() {
        return new TZahtev();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IzvodResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/Izvod", name = "IzvodResponse")
    public JAXBElement<IzvodResponse> createIzvodResponse(IzvodResponse value) {
        return new JAXBElement<IzvodResponse>(_IzvodResponse_QNAME, IzvodResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IzvodRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/Izvod", name = "IzvodRequest")
    public JAXBElement<IzvodRequest> createIzvodRequest(IzvodRequest value) {
        return new JAXBElement<IzvodRequest>(_IzvodRequest_QNAME, IzvodRequest.class, null, value);
    }

}
