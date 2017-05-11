package xws_pi_bezb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class OsmiSemestarProjApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsmiSemestarProjApplication.class, args);
	}
}
