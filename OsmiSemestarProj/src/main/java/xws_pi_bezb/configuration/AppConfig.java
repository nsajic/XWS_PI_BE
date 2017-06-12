package xws_pi_bezb.configuration;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import xws_pi_bezb.aspects.BankaAspect;

@Configuration
@ComponentScan(basePackages = "xws_pi_bezb")
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
	public BankaAspect interceptorBankaAspect() {
		BankaAspect aspect = Aspects.aspectOf(BankaAspect.class);
	    return aspect;
	}

}
