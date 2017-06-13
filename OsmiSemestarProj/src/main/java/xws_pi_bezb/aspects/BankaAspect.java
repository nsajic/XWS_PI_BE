package xws_pi_bezb.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xws_pi_bezb.annotations.BankaAnnotation;
import xws_pi_bezb.iservices.IPrivilegijaService;
import xws_pi_bezb.models.korisnici.Korisnik;

@Aspect
public class BankaAspect{

	@Autowired
	public IPrivilegijaService privilegijaService;
	
	@Around("execution(@xws_pi_bezb.annotations.BankaAnnotation * *(..)) && @annotation(bankaAnnotation)")
	public Object aspMeth(ProceedingJoinPoint joinPoint, BankaAnnotation bankaAnnotation) throws Throwable{
		
		Object returnObject = null;
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Korisnik korisnik = (Korisnik) attr.getRequest().getSession().getAttribute("ulogovanKorisnik");
		System.out.println("caoooocaococaocaocaoac");
		if(!privilegijaService.getByRole(korisnik.getRola()).contains(privilegijaService.getByNaziv(bankaAnnotation.value())))
			throw new Throwable();			
		else
			returnObject = joinPoint.proceed();
					
		return returnObject;
	}
}
