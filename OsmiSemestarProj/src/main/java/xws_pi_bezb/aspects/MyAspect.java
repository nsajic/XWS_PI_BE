package xws_pi_bezb.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import xws_pi_bezb.annotations.MyAnotation;

@Aspect
public class MyAspect {

	//@Before("execution(* xws_pi_bezb.controllers.BankaKontroler.dodajBanku(..))")
	//@Before("@annotation(xws_pi_bezb.annotations.MyAnotation)")

	@Before("execution(@xws_pi_bezb.annotations.MyAnotation * *(..)) && @annotation(myAnotationAnnotation)")
	public void aspMeth(MyAnotation myAnotationAnnotation){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		Object str = attr.getRequest().getSession(true).getAttribute("user");
		System.out.println(str);
		System.out.println(myAnotationAnnotation.value());
		//return result;
	}
}
