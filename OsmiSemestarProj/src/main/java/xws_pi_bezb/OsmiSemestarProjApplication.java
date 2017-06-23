package xws_pi_bezb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bezbednost.poslovna.xml.ws.mt102.MT102Request;
import bezbednost.poslovna.xml.ws.mt103.MT103Request;

@SpringBootApplication
@EnableTransactionManagement
public class OsmiSemestarProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsmiSemestarProjApplication.class, args);
		
		MT102Request request102 = new MT102Request();
		MT103Request request103 = new MT103Request();
		
		BankaKlijentSamoTest klijentTest = new BankaKlijentSamoTest();
		klijentTest.posaljiMT102(request102);
		klijentTest.posaljiMT103(request103);
		
	}
}
