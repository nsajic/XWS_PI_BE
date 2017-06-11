package xws_pi_bezb.aspects;

import java.sql.SQLException;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xws_pi_bezb.annotations.MyAnotation;
import xws_pi_bezb.iservices.IPrivilegijaService;
import xws_pi_bezb.models.korisnici.Korisnik;

@Aspect
@Component
public class MyAspect{

	//@Before("execution(* xws_pi_bezb.controllers.BankaKontroler.dodajBanku(..))")
	//@Before("@annotation(xws_pi_bezb.annotations.MyAnotation)")

	@Autowired
	public IPrivilegijaService privilegijaService;
	
	@Before("execution(@xws_pi_bezb.annotations.MyAnotation * *(..)) && @annotation(myAnotationAnnotation)")
	public void aspMeth(MyAnotation myAnotationAnnotation) throws SQLException{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Object str = attr.getRequest().getSession(true).getAttribute("user");
		Korisnik korisnik = (Korisnik) attr.getRequest().getSession().getAttribute("ulogovanKorisnik");
		System.out.println(str);
		System.out.println(myAnotationAnnotation.value());
		
//		if(myAnotationAnnotation.value().equals("Banka:Dodaj")){
//			
//		}
		
		if(!privilegijaService.getByRole(korisnik.getRola()).contains(privilegijaService.getByNaziv(myAnotationAnnotation.value()))){
			System.out.println(myAnotationAnnotation.value());
			throw new SQLException();
		}
		
		//return result;
	}

}
