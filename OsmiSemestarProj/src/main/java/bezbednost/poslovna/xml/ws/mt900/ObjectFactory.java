//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.19 at 02:58:00 PM CEST 
//


package bezbednost.poslovna.xml.ws.mt900;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bezbednost.poslovna.xml.ws.mt900 package. 
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

    private final static QName _MT900Response_QNAME = new QName("http://xml.poslovna.bezbednost/ws/MT900", "MT900Response");
    private final static QName _MT900Request_QNAME = new QName("http://xml.poslovna.bezbednost/ws/MT900", "MT900Request");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bezbednost.poslovna.xml.ws.mt900
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MT900Request }
     * 
     */
    public MT900Request createMT900Request() {
        return new MT900Request();
    }

    /**
     * Create an instance of {@link MT900Response }
     * 
     */
    public MT900Response createMT900Response() {
        return new MT900Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT900Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/MT900", name = "MT900Response")
    public JAXBElement<MT900Response> createMT900Response(MT900Response value) {
        return new JAXBElement<MT900Response>(_MT900Response_QNAME, MT900Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT900Request }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xml.poslovna.bezbednost/ws/MT900", name = "MT900Request")
    public JAXBElement<MT900Request> createMT900Request(MT900Request value) {
        return new JAXBElement<MT900Request>(_MT900Request_QNAME, MT900Request.class, null, value);
    }

}
