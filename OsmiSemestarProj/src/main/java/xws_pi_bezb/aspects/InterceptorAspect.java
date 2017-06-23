package xws_pi_bezb.aspects;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xws_pi_bezb.annotations.InterceptorAnnotation;
import xws_pi_bezb.iservices.IPrivilegijaService;
import xws_pi_bezb.models.korisnici.BankarskiSluzbenik;

@Aspect
public class InterceptorAspect{

	@Autowired
	public IPrivilegijaService privilegijaService;
	
	private HttpServletResponse response;	
	
	@Around("execution(@xws_pi_bezb.annotations.InterceptorAnnotation * *(..)) && @annotation(interceptorAnnotation)")
	public Object aspMeth(ProceedingJoinPoint joinPoint, InterceptorAnnotation interceptorAnnotation) throws Throwable{
		
		Object returnObject = null;
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		BankarskiSluzbenik korisnik = (BankarskiSluzbenik) attr.getRequest().getSession().getAttribute("ulogovanKorisnik");

		// TODO: Ko je radio ovo, kad zakomentarisujem role, jer mi ne trebaju nzm sta u ifu da stavim i ima li svrhe ova klasa
		if(!privilegijaService.getByRole(korisnik.getRola()).contains(privilegijaService.getByNaziv(interceptorAnnotation.value()))){
			response.sendError(401, "Unauthorized request");
			//throw new Throwable();			
		}else
			returnObject = joinPoint.proceed();
					
		return returnObject;
	}
}
