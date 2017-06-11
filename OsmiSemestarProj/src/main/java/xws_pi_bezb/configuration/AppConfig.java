package xws_pi_bezb.configuration;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import xws_pi_bezb.aspects.MyAspect;

@Configuration
@ComponentScan(basePackages = "xws_pi_bezb")
@EnableAspectJAutoProxy
public class AppConfig {
	
	@Bean
	public MyAspect interceptorMyAspect() {
		MyAspect aspect = Aspects.aspectOf(MyAspect.class);
	    // ... inject dependencies here if not using @Autowired
	    return aspect;
	}

}
