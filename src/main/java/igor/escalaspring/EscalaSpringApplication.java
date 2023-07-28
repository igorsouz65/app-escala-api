package igor.escalaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class EscalaSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscalaSpringApplication.class, args);
	}

}
