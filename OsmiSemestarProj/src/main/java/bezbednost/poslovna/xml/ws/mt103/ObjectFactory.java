
package bezbednost.poslovna.xml.ws.mt103;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bezbednost.poslovna.xml.ws.mt103 package. 
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

    private final static QName _MT103Request_QNAME = new QName("http://xml.poslovna.bezbednost/ws/MT103", "MT103Request");
    private final static QName _MT103Response_QNAME = new QName("http://xml.poslovna.bezbednost/ws/MT103", "MT103Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bezbednost.poslovna.xml.ws.mt103
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MT103Request }
     * 
     */
    public MT103Request createMT103Request() {
        return new MT103Request();
    }

    /**
     * Create an instance of {@link MT103Response }
     * 
     */
    public MT103Response createMT103Response() {
        return new MT103Response();
    }

    /**
     * Create an instance of {@link TSWIFTIRacun }
     * 
     */
    public TSWIFTIRacun createTSWIFTIRacun() {
        return new TSWIFTIRacun();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT103Request }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/MT103", name = "MT103Request")
    public JAXBElement<MT103Request> createMT103Request(MT103Request value) {
        return new JAXBElement<MT103Request>(_MT103Request_QNAME, MT103Request.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT103Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/MT103", name = "MT103Response")
    public JAXBElement<MT103Response> createMT103Response(MT103Response value) {
        return new JAXBElement<MT103Response>(_MT103Response_QNAME, MT103Response.class, null, value);
    }

}
