package xws_pi_bezb.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}
	
	@Bean(name = "NalogZaPrenos")
	public DefaultWsdl11Definition nalogZaPrenosWsdl11Definition(XsdSchema nalogZaPlacanjeSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("NalogZaPlacanjePort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml.poslovna.bezbednost/ws/NalogZaPrenos");
		wsdl11Definition.setSchema(nalogZaPlacanjeSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema nalogZaPlacanjeSchema() {
		return new SimpleXsdSchema(new ClassPathResource("seme/NalogZaPrenos.xsd"));
	}
	
	@Bean(name = "Izvod")
	public DefaultWsdl11Definition izvodWsdl11Definition(XsdSchema izvodSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("IzvodPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://xml.poslovna.bezbednost/ws/izvod");
		wsdl11Definition.setSchema(izvodSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema izvodSchema() {
		return new SimpleXsdSchema(new ClassPathResource("seme/PresekIzvoda.xsd"));
	}
	
	
	//Bezbednost
/*
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();

        // validate incoming request
        securityInterceptor.setValidationActions("Signature Encrypt");
        securityInterceptor.setValidationSignatureCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setValidationDecryptionCrypto(getCryptoFactoryBean().getObject());
        //securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());

        // encrypt the response
        //securityInterceptor.setSecurementEncryptionUser("client-public");
        //securityInterceptor.setSecurementEncryptionParts("{Content}{http://nalogzaprenos.ws.xml.poslovna.bezbednost/}NalogZaPrenosResponse");
        //securityInterceptor.setSecurementEncryptionCrypto(getCryptoFactoryBean().getObject());

        // sign the response
        //securityInterceptor.setSecurementActions("Signature Encrypt");
        //securityInterceptor.setSecurementUsername("primer");
        //securityInterceptor.setSecurementPassword("primer");
        //securityInterceptor.setSecurementSignatureCrypto(getCryptoFactoryBean().getObject());

        return securityInterceptor;
    }
   
    @Bean
    public CryptoFactoryBean getCryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("primer");
        cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("primer.jks"));
        return cryptoFactoryBean;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        try {
            interceptors.add(securityInterceptor());
        } catch (Exception e) {
            throw new RuntimeException("could not initialize security interceptor");
        }
    }
*/ 
}