package hu.rft.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class KonyvtarServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KonyvtarServerApplication.class, args);
	}
}
