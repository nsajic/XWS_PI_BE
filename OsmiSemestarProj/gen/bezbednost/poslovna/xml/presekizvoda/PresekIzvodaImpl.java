
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package bezbednost.poslovna.xml.presekizvoda;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2017-06-13T20:13:40.060+02:00
 * Generated source version: 2.6.5
 * 
 */

@javax.jws.WebService(
                      serviceName = "PresekIzvodaServis",
                      portName = "PresekIzvodaPort",
                      targetNamespace = "http://xml.poslovna.bezbednost/PresekIzvoda",
                      wsdlLocation = "file:/D:/aFAX IV/XML_Poslovna_Bezbednost/8Semestar/XWS_PI_BE/OsmiSemestarProj/src/main/resources/wsdl/PresekIzvoda.wsdl",
                      endpointInterface = "bezbednost.poslovna.xml.presekizvoda.PresekIzvoda")
                      
public class PresekIzvodaImpl implements PresekIzvoda {

    private static final Logger LOG = Logger.getLogger(PresekIzvodaImpl.class.getName());

    /* (non-Javadoc)
     * @see bezbednost.poslovna.xml.presekizvoda.PresekIzvoda#presekIzvodaRequest(bezbednost.poslovna.xml.presekizvoda.PresekIzvodaRequest.ZaglavljePreseka  zaglavljePreseka ,)java.util.List<bezbednost.poslovna.xml.presekizvoda.PresekIzvodaRequest.StavkaPreseka>  stavkaPreseka )*
     */
    public java.lang.String presekIzvodaRequest(bezbednost.poslovna.xml.presekizvoda.PresekIzvodaRequest.ZaglavljePreseka zaglavljePreseka,java.util.List<bezbednost.poslovna.xml.presekizvoda.PresekIzvodaRequest.StavkaPreseka> stavkaPreseka) { 
        LOG.info("Executing operation presekIzvodaRequest");
        System.out.println(zaglavljePreseka);
        System.out.println(stavkaPreseka);
        try {
            java.lang.String _return = "";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
