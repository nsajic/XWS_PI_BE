//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.17 at 11:05:41 AM CEST 
//


package bezbednost.poslovna.xml.ws.mt102;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bezbednost.poslovna.xml.ws.mt102 package. 
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

    private final static QName _MT102Request_QNAME = new QName("http://xml.poslovna.bezbednost/ws/MT102", "MT102Request");
    private final static QName _MT102Response_QNAME = new QName("http://xml.poslovna.bezbednost/ws/MT102", "MT102Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bezbednost.poslovna.xml.ws.mt102
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MT102Response }
     * 
     */
    public MT102Response createMT102Response() {
        return new MT102Response();
    }

    /**
     * Create an instance of {@link MT102Request }
     * 
     */
    public MT102Request createMT102Request() {
        return new MT102Request();
    }

    /**
     * Create an instance of {@link TPojedinacnoPlacanje }
     * 
     */
    public TPojedinacnoPlacanje createTPojedinacnoPlacanje() {
        return new TPojedinacnoPlacanje();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT102Request }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/MT102", name = "MT102Request")
    public JAXBElement<MT102Request> createMT102Request(MT102Request value) {
        return new JAXBElement<MT102Request>(_MT102Request_QNAME, MT102Request.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT102Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/MT102", name = "MT102Response")
    public JAXBElement<MT102Response> createMT102Response(MT102Response value) {
        return new JAXBElement<MT102Response>(_MT102Response_QNAME, MT102Response.class, null, value);
    }

}
