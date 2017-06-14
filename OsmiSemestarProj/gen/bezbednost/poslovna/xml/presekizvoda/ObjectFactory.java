
package bezbednost.poslovna.xml.presekizvoda;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bezbednost.poslovna.xml.presekizvoda package. 
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

    private final static QName _PresekIzvodaRequest_QNAME = new QName("http://xml.poslovna.bezbednost/PresekIzvoda", "PresekIzvodaRequest");
    private final static QName _PresekIzvodaResponse_QNAME = new QName("http://xml.poslovna.bezbednost/PresekIzvoda", "PresekIzvodaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bezbednost.poslovna.xml.presekizvoda
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PresekIzvodaRequest }
     * 
     */
    public PresekIzvodaRequest createPresekIzvodaRequest() {
        return new PresekIzvodaRequest();
    }

    /**
     * Create an instance of {@link PresekIzvodaResponse }
     * 
     */
    public PresekIzvodaResponse createPresekIzvodaResponse() {
        return new PresekIzvodaResponse();
    }

    /**
     * Create an instance of {@link TZahtev }
     * 
     */
    public TZahtev createTZahtev() {
        return new TZahtev();
    }

    /**
     * Create an instance of {@link PresekIzvodaRequest.ZaglavljePreseka }
     * 
     */
    public PresekIzvodaRequest.ZaglavljePreseka createPresekIzvodaRequestZaglavljePreseka() {
        return new PresekIzvodaRequest.ZaglavljePreseka();
    }

    /**
     * Create an instance of {@link PresekIzvodaRequest.StavkaPreseka }
     * 
     */
    public PresekIzvodaRequest.StavkaPreseka createPresekIzvodaRequestStavkaPreseka() {
        return new PresekIzvodaRequest.StavkaPreseka();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PresekIzvodaRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/PresekIzvoda", name = "PresekIzvodaRequest")
    public JAXBElement<PresekIzvodaRequest> createPresekIzvodaRequest(PresekIzvodaRequest value) {
        return new JAXBElement<PresekIzvodaRequest>(_PresekIzvodaRequest_QNAME, PresekIzvodaRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PresekIzvodaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/PresekIzvoda", name = "PresekIzvodaResponse")
    public JAXBElement<PresekIzvodaResponse> createPresekIzvodaResponse(PresekIzvodaResponse value) {
        return new JAXBElement<PresekIzvodaResponse>(_PresekIzvodaResponse_QNAME, PresekIzvodaResponse.class, null, value);
    }

}
