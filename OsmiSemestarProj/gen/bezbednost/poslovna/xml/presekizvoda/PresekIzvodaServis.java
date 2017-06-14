package bezbednost.poslovna.xml.presekizvoda;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2017-06-13T20:13:40.090+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebServiceClient(name = "PresekIzvodaServis", 
                  wsdlLocation = "file:/D:/aFAX IV/XML_Poslovna_Bezbednost/8Semestar/XWS_PI_BE/OsmiSemestarProj/src/main/resources/wsdl/PresekIzvoda.wsdl",
                  targetNamespace = "http://xml.poslovna.bezbednost/PresekIzvoda") 
public class PresekIzvodaServis extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://xml.poslovna.bezbednost/PresekIzvoda", "PresekIzvodaServis");
    public final static QName PresekIzvodaPort = new QName("http://xml.poslovna.bezbednost/PresekIzvoda", "PresekIzvodaPort");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/aFAX IV/XML_Poslovna_Bezbednost/8Semestar/XWS_PI_BE/OsmiSemestarProj/src/main/resources/wsdl/PresekIzvoda.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(PresekIzvodaServis.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/aFAX IV/XML_Poslovna_Bezbednost/8Semestar/XWS_PI_BE/OsmiSemestarProj/src/main/resources/wsdl/PresekIzvoda.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public PresekIzvodaServis(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public PresekIzvodaServis(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PresekIzvodaServis() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PresekIzvodaServis(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PresekIzvodaServis(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public PresekIzvodaServis(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns PresekIzvoda
     */
    @WebEndpoint(name = "PresekIzvodaPort")
    public PresekIzvoda getPresekIzvodaPort() {
        return super.getPort(PresekIzvodaPort, PresekIzvoda.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PresekIzvoda
     */
    @WebEndpoint(name = "PresekIzvodaPort")
    public PresekIzvoda getPresekIzvodaPort(WebServiceFeature... features) {
        return super.getPort(PresekIzvodaPort, PresekIzvoda.class, features);
    }

}
